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
             <a href='${PLACE_GET_METHOD_PATH}/?id=${randId}'>get Place</a>
        """
    }

    @GetMapping(PLACE_GET_METHOD_PATH)
    fun getPlace(@RequestParam("id") id : Long) : Place? {
        return try {
            placesRepository.findById(id).get()
        } catch (e : NoSuchElementException) {
            return null
        }
    }

    @GetMapping(PLACE_GET_ALL_METHOD_PATH)
    fun getAllPlaces() : Iterable<Place> {
        return placesRepository.findAll()
    }

    @PostMapping(PLACE_ADD_METHOD_PATH)
    fun addPlace(@RequestBody place: Place) {
        placesRepository.save(place)
    }
}