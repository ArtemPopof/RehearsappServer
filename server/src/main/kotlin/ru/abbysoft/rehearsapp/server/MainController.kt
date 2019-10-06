package ru.abbysoft.rehearsapp.server

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import ru.abbysoft.rehearsapp.model.Place
import ru.abbysoft.rehearsapp.server.data.PlacesRepository
import java.util.*

@RestController
class MainController {

    private val random = Random(System.currentTimeMillis())

    @Autowired
    private lateinit var placesRepository : PlacesRepository

    @RequestMapping("/")
    fun index() : String {
        val randId = random.nextLong()
        return """
            This is Rehearsapp REST api index page!
             <br>
             <a href='${APP_PAGE}'>about Rehearsapp</a>
             <br>
             <a href='/place/{$randId}/'>get Place</a>
        """
    }

    @GetMapping("/place/{id}/")
    fun getPlace(@PathVariable("id") id : Long) : Place? {
        return try {
            val place = placesRepository.findById(id).get()
            println("return place: $place")
            place
        } catch (e : NoSuchElementException) {
            return null
        }
    }

    @GetMapping("/place/getAll/")
    fun getAllPlaces() : Iterable<Place> {
        return placesRepository.findAll()
    }

    @PostMapping("/place/add/")
    fun addPlace(@RequestBody place: Place) : Long {
        println("place being added: $place")
        return placesRepository.save(place).id
    }
}