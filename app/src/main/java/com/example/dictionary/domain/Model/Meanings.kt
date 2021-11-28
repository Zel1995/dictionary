package com.example.dictionary.domain.Model

data class Meanings(
    val id: Int,
    val translation: Translation?,
    val imageUrl: String?,
    val previewUrl: String?
)