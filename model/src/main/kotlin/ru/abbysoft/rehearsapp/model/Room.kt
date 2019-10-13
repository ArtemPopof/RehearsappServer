package ru.abbysoft.rehearsapp.model

import java.util.*
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
data class Room(
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        var id: Long = -1,

        var name: String = "",
        var area: Float = 0f,
        var price: Float = 0f,
        var photos: List<String> = Collections.emptyList()
)