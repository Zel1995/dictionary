package com.example.dictionary.data.network.model

import com.google.gson.annotations.SerializedName

data class MeaningsResponse (
    @field:SerializedName("id")val id:Int,
    @field:SerializedName("translation") val translation: TranslationResponse?,
    @field:SerializedName("imageUrl") val imageUrl: String?,
    @field:SerializedName("previewUrl") val previewUrl:String?
)
