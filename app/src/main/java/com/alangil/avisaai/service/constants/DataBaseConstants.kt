package com.alangil.avisaai.service.constants

class DataBaseConstants private constructor(){

    /**
     * Tabelas disponíveis no banco de dados com suas colunas
     */

    object MEDICINE {
        const val TABLE_NAME = "Medicine"

        object COLUMNS {
            const val ID = "id"
            const val NAME = "name"
            const val QUANTITY = "quantity"
        }
    }

}