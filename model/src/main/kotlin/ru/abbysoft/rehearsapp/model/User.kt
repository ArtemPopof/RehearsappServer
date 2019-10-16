package ru.abbysoft.rehearsapp.model

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
data class User(
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        var id: Long = -1L,

        var firstName: String = "",
        var lastName: String = "",
        var login: String = "",

        var points: Long = 0,

        var role: Role = Role.USER
)

enum class Role {
    USER,
    PLACE_OWNER,
    ADMIN
}