package ru.abbysoft.rehearsapp.model

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
data class Image(
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        var id: Long = -1L,
        var name: String = ""
)