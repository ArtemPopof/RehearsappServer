package ru.abbysoft.rehearsapp.model

import javax.persistence.*

@Entity
data class Place (
        @Id
        @GeneratedValue(strategy=GenerationType.AUTO)
        var id: Long = -1,

        var name: String = "",
        var headerImageId : String = "",
        var position : String = "",

        var telephone : String = "",
        var website : String = "",

        var averagePrice : Int = -1,

        @OneToMany(cascade = [CascadeType.ALL], fetch = FetchType.EAGER)
        var rooms: List<Room> = ArrayList(5)
)