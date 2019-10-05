package ru.abbysoft.rehearsapp.model

import java.util.*
import javax.persistence.*

@Entity
data class Place (
        @Id
        @GeneratedValue(strategy=GenerationType.AUTO)
        var id: Long = -1,

        var name: String = "",
        var headerImageId : Long = -1,
        var position : String = "",

        @OneToMany(cascade = [CascadeType.ALL])
        val rooms: List<Room> = Collections.emptyList()
)