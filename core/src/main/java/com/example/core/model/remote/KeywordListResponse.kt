package com.example.core.model.remote

import com.example.core.model.entity.Keyword


data class KeywordListResponse(val id: Int, val keywords: List<Keyword>)