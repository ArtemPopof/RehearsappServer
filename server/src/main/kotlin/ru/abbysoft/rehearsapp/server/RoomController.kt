package ru.abbysoft.rehearsapp.server

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
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
}