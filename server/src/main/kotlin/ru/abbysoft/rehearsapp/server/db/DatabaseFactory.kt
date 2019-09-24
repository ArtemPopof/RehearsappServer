package ru.abbysoft.rehearsapp.server.db

class DatabaseFactory private constructor() {

    companion object {
        private val instance = InMemoryDatabase()

        fun getDefaultDatabaseInstance() : Database {
            return instance
        }
    }
}