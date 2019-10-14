package ru.abbysoft.rehearsapp.server

import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.web.bind.annotation.*
import ru.abbysoft.rehearsapp.model.Room
import ru.abbysoft.rehearsapp.server.data.RoomRepository

@RestController
@RequestMapping("room")
class RoomController {

    private val logger = LoggerFactory.getLogger(RoomController::class.java)

    @Autowired
    private lateinit var repository: RoomRepository

    @GetMapping("{id}")
    fun getRoom(@PathVariable("id") id: Long): Room? {
        return repository.findByIdOrNull(id)
    }

    @PostMapping
    fun saveRoom(@RequestBody room: Room): Long {
        val returned = repository.save(room)
        return returned.id
    }

    @PutMapping("{id}")
    fun updateRoom(@PathVariable("id") id: Long, @RequestBody room: Room): Boolean {
        logger.debug("Updating $id room")

        if (!repository.existsById(id)) {
            logger.error("Cannot find room with id $id")
            return false
        }

        repository.save(room)

        logger.debug("room updated successfully $room")
        return true
    }
}