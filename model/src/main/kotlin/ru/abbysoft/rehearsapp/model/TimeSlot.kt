package ru.abbysoft.rehearsapp.model

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
data class TimeSlot (
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long = -1L,

    // seconds from day start
    var timeStart: Int = 0,
    var timeEnd: Int = 0,

    // common price without any sales
    var price: Int = 0,

    // user id that booked this slot
    var bookedBy: Long = -1L
)