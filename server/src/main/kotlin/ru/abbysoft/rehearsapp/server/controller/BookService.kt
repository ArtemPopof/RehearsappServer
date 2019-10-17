package ru.abbysoft.rehearsapp.server.controller

import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.web.bind.annotation.*
import ru.abbysoft.rehearsapp.server.data.TimeSlotRepository

@RestController
@RequestMapping("book")
class BookService {

    private val logger = LoggerFactory.getLogger(BookService::class.java)

    @Autowired
    private lateinit var repository: TimeSlotRepository

    @PostMapping("{id}")
    fun bookSlot(@PathVariable("id") slotId: Long): Boolean {
        logger.debug("Booking room slot with id $slotId")
        val slot = repository.findByIdOrNull(slotId)
        if (slot == null) {
            logger.debug("Cannot find slot with id $slotId")
            return false
        }

        slot.booked = true
        repository.save(slot)

        logger.debug("Booked room slot with id $slotId")

        return true
    }

    @PostMapping("{id}")
    fun unbookSlot(@PathVariable("id") slotId: Long): Boolean {
        logger.debug("Unbook room slot with id $slotId")
        val slot = repository.findByIdOrNull(slotId)
        if (slot == null) {
            logger.debug("Cannot find slot with id $slotId")
            return false
        }

        slot.booked = false
        repository.save(slot)

        logger.debug("Room slot with id $slotId has been booked")

        return true
    }
}