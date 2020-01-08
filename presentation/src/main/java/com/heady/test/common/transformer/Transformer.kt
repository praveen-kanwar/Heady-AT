package com.heady.test.common.transformer

import com.google.gson.Gson
import javax.inject.Inject


class Transformer
@Inject
constructor(private val gson: Gson) {
    /**
     * Convert Presentation Model To Domain Bean.
     */
    fun <Model, Bean> toBean(model: Model, beanClass: Class<Bean>): Bean {
        return gson.fromJson(gson.toJson(model), beanClass)
    }

    /**
     * Convert Domain Bean To Presentation Model.
     */
    fun <Bean, Model> toModel(bean: Bean, modelClass: Class<Model>): Model {
        return gson.fromJson(gson.toJson(bean), modelClass)
    }
}