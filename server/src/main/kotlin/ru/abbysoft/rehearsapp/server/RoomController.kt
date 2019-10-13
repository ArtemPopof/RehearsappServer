package ru.abbysoft.rehearsapp.server

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.web.bind.annotation.*
import ru.abbysoft.rehearsapp.model.Room
import ru.abbysoft.rehearsapp.server.data.RoomRepository

@RestController
@RequestMapping("room")
class RoomController {

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
}