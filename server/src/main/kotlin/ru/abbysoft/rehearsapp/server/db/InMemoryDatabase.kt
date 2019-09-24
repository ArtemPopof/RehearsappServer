package ru.abbysoft.rehearsapp.server.db

import ru.abbysoft.rehearsapp.model.Place

var counter = 0L

class InMemoryDatabase : Database {

    private val storage = HashMap<Long, Place>(20)

    override fun getPlace(id: Long): Place? {
        return storage[id]
    }

    override fun addPlace(place: Place) {
        storage[counter++] = place
    }
}