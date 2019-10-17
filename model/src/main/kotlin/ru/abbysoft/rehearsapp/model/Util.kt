package ru.abbysoft.rehearsapp.model

fun generateDefaultSlots(): List<TimeSlot> {
    val list = ArrayList<TimeSlot>(5)

    val sevenAm = 7 * 3600
    val slotLength = 3600 * 3

    for (i in 0 until 5) {
        list.add(TimeSlot().apply {
            this.timeStart = sevenAm + i * slotLength
            this.timeEnd = timeStart + slotLength
        })
    }

    return list
}