package com.example.datasource.utils

import androidx.paging.AsyncPagingDataDiffer
import androidx.paging.LOGGER
import androidx.paging.Logger
import androidx.paging.PagingData
import androidx.paging.map
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListUpdateCallback
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.withTimeout

private class SameActionListUpdateCallback(
    private val onChange: () -> Unit
) : ListUpdateCallback {

    override fun onInserted(position: Int, count: Int) {
        onChange()
    }

    override fun onRemoved(position: Int, count: Int) {
        onChange()
    }

    override fun onMoved(fromPosition: Int, toPosition: Int) {
        onChange()
    }

    override fun onChanged(position: Int, count: Int, payload: Any?) {
        onChange()
    }
}

private class NoDiffCallback<T : Any> : DiffUtil.ItemCallback<T>() {
    override fun areItemsTheSame(oldItem: T, newItem: T): Boolean = false
    override fun areContentsTheSame(oldItem: T, newItem: T): Boolean = false
}

suspend fun <T : Any> PagingData<T>.toPagination(logger: Logger): Pagination<T> {
    val pages: MutableList<List<T>> = mutableListOf()
    var currentPage: MutableList<T> = mutableListOf()

    val currentPosition = MutableStateFlow(0)
    val updateCallback = SameActionListUpdateCallback {
        pages.add(currentPage)
        currentPosition.value += currentPage.size
        currentPage = mutableListOf()
    }

    val differ = AsyncPagingDataDiffer<T>(
        diffCallback = NoDiffCallback(),
        updateCallback = updateCallback,
        mainDispatcher = Dispatchers.Main,
        workerDispatcher = Dispatchers.Default
    ).apply { LOGGER = logger }

    currentPosition.filter { it > 0 }.onEach { differ.getItem(it - 1) }

    try {
        withTimeout(5) {
            differ.submitData(this@toPagination.onEach { currentPage.add(it) })
        }
    } catch (e: TimeoutCancellationException) {
        // Ignore exception we just need it in order to stop
        // the underlying implementation blocking the main thread
    }

    return Pagination(pages = pages)
}

private fun <T : Any> PagingData<T>.onEach(action: (T) -> Unit): PagingData<T> = this.map {
    action(it)
    it
}

data class Pagination<T : Any>(private val pages: MutableList<List<T>>) {

    val isEmpty: Boolean = pages.isEmpty()

    val count: Int = pages.count()

    fun first(): List<T> = pages.first()

    fun last(): List<T> = pages.last()

    fun pageAt(position: Int): List<T> = pages[position]

    fun loadedAt(point: Int): List<T> = pages.take(point).flatten()

    fun fullyLoaded(): List<T> = pages.flatten()
}