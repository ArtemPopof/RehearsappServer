package ru.abbysoft.rehearsapp.server.controller

import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.web.bind.annotation.*
import ru.abbysoft.rehearsapp.model.Room
import ru.abbysoft.rehearsapp.server.data.ImageRepository
import ru.abbysoft.rehearsapp.server.data.RoomRepository
import java.util.stream.Collectors

@RestController
@RequestMapping("room")
class RoomController {

    private val logger = LoggerFactory.getLogger(RoomController::class.java)

    @Autowired
    private lateinit var repository: RoomRepository
    @Autowired
    private lateinit var imageRepository: ImageRepository

    @GetMapping("{id}")
    fun getRoom(@PathVariable("id") id: Long): Room? {
        return repository.findByIdOrNull(id)
    }

    @PostMapping
    fun saveRoom(@RequestBody room: Room): Long {
        logger.debug("save room $room")

        // save all images first
        val savedImages = room.images.stream().map { imageRepository.save(it) }.collect(Collectors.toList())
        room.images = savedImages

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