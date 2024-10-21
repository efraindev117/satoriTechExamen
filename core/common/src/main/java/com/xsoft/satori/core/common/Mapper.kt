package com.xsoft.satori.core.common

interface Mapper<F,T> {
    fun mapFrom(from:F):T
}