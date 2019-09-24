package ru.abbysoft.rehearsapp.model

import javax.persistence.*

@Entity
data class Place (
        @Id
        @GeneratedValue(strategy=GenerationType.AUTO)
        val id: Long,

        val name: String,
        val headerImageId : Long,
        val position : String
)