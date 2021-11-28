package com.example.dictionary.data.mappers

import com.example.dictionary.data.network.model.DataModelResponse
import com.example.dictionary.data.network.model.MeaningsResponse
import com.example.dictionary.data.network.model.TranslationResponse
import com.example.dictionary.domain.Model.DataModel
import com.example.dictionary.domain.Model.Meanings
import com.example.dictionary.domain.Model.Translation

class ResponseMapper {
    fun toDataModel(dataModelResponse: DataModelResponse): DataModel {
        return DataModel(
            dataModelResponse.text,
            toMeanings(dataModelResponse.meanings)
        )
    }

    private fun toMeanings(meaningsResponse: List<MeaningsResponse>?): List<Meanings>? {
        return meaningsResponse?.map{
            Meanings(
                it.id,
                toTranslation(it.translation),
                it.imageUrl,
                it.previewUrl
            )
        }
    }

    private fun toTranslation(translationResponse: TranslationResponse?): Translation {
        return Translation(
            translationResponse?.text,
            translationResponse?.note
        )
    }
}