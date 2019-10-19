package ru.abbysoft.rehearsapp.server.controller

import org.springframework.stereotype.Service

private const val SEVEN_PM = 3600 * 19
private const val TEN_AM = 3600 * 10

@Service
class PriceService {

    fun calcSlotPrice(roomPrice: Float, timeStart: Int): Float {
        if (timeStart >= SEVEN_PM) {
            return roomPrice * 1.5f
        }
        if (timeStart <= TEN_AM) {
            return roomPrice * 0.7f
        }

        return roomPrice
    }
}