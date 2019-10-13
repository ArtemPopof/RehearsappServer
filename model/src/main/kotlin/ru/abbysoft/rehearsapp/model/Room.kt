package ru.abbysoft.rehearsapp.model

import java.util.*
import javax.persistence.*

@Entity
data class Room(
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        var id: Long = -1,

        var name: String = "",
        var area: Float = 0f,
        var price: Float = 0f,

        @OneToMany(fetch = FetchType.EAGER)
        var images: List<Image> = ArrayList(5)
)