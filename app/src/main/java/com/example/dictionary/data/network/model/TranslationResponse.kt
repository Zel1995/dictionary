package com.example.dictionary.data.network.model

import com.google.gson.annotations.SerializedName

data class TranslationResponse(
    @field:SerializedName("text") val text: String?,
    @field:SerializedName("note") val note :String?
)
