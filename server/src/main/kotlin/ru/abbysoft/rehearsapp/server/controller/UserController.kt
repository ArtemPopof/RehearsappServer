package ru.abbysoft.rehearsapp.server.controller

import ru.abbysoft.rehearsapp.server.data.UserRepository

import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.web.bind.annotation.*
import ru.abbysoft.rehearsapp.model.Room
import ru.abbysoft.rehearsapp.model.User
import java.util.stream.Collectors

@RestController
@RequestMapping("user")
class UserController {

    private val logger = LoggerFactory.getLogger(UserController::class.java)

    @Autowired
    private lateinit var repository: UserRepository

    @GetMapping("{id}")
    fun getUser(@PathVariable("id") id: Long): User? {
        return repository.findByIdOrNull(id)
    }

    @PostMapping
    fun saveUser(@RequestBody user: User): Long {
        logger.debug("save user $user")

        val returned = repository.save(user)

        return returned.id
    }

    @PutMapping("{id}")
    fun updateUser(@PathVariable("id") id: Long, @RequestBody user: User): Boolean {
        logger.debug("Updating $id user")

        if (!repository.existsById(id)) {
            logger.error("Cannot find user with id $id")
            return false
        }

        repository.save(user)

        logger.debug("user updated successfully $user")
        return true
    }
}