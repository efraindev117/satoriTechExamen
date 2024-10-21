package com.xsoft.satori.core.model.pokemon

import com.xsoft.satori.core.model.BuildConfig

data class Result(
    val name: String,
    val url: String,
) {
    private fun getIdFromUrl(): String {
        return url.trimEnd('/').substringAfterLast('/')
    }

    fun sprite(): String {
        val id = getIdFromUrl()
        return "${BuildConfig.SPRITE_URL}$id.png"
    }
}