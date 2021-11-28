package com.example.dictionary.data.mappers

import com.example.dictionary.data.storage.model.DataModelEntity
import com.example.dictionary.data.storage.model.MeaningsEntity
import com.example.dictionary.data.storage.model.TranslationEntity
import com.example.dictionary.domain.Model.DataModel
import com.example.dictionary.domain.Model.Meanings
import com.example.dictionary.domain.Model.Translation

class EntityMapper {
    fun toDataModel(dataModelEntity: DataModelEntity): DataModel {
        return DataModel(
            dataModelEntity.text,
            toMeanings(dataModelEntity.meanings)
        )
    }

    private fun toMeanings(meaningsEntity: List<MeaningsEntity>?): List<Meanings>? {
        return meaningsEntity?.map{
            Meanings(
                it.id,
                toTranslation(it.translation),
                it.imageUrl,
                it.previewUrl
            )
        }
    }

    private fun toTranslation(translationEntity: TranslationEntity?): Translation {
        return Translation(
            translationEntity?.text,
            translationEntity?.note
        )
    }
}