package ru.abbysoft.rehearsapp.model

import javax.persistence.*

@Entity
data class Place (
        @Id
        @GeneratedValue(strategy=GenerationType.AUTO)
        var id: Long = -1,

        var name: String = "",
        var headerImageId : Long = -1,
        var position : String = "",

        @OneToMany(cascade = [CascadeType.ALL], fetch = FetchType.EAGER)
        val rooms: List<Room> = ArrayList(7)
)