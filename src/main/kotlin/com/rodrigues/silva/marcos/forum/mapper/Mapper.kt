package com.rodrigues.silva.marcos.forum.mapper

interface Mapper<T, U> {

    fun map(t: T): U
}
