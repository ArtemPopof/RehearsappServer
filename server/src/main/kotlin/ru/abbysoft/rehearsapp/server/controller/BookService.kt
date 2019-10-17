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
    fun bookSlot(@PathVariable("id") slotId: Long, @RequestBody userId: Long): Boolean {
        if (userId == -1L) {
            logger.debug("Unbooking room slot with id $slotId")
        } else {
            logger.debug("Set booking for room slot with id $slotId for user with id $userId")
        }
        val slot = repository.findByIdOrNull(slotId)
        if (slot == null) {
            logger.debug("Cannot find slot with id $slotId")
            return false
        }

        slot.bookedBy = userId
        repository.save(slot)

        logger.debug("Room slot with id $slotId has been changed")

        return true
    }
}