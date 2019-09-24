package ru.abbysoft.rehearsapp.server.db

import ru.abbysoft.rehearsapp.model.Place

interface Database {

    fun getPlace(id : Long) : Place?

    fun addPlace(place : Place)
}