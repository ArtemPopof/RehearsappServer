package ru.abbysoft.rehearsapp.server

import org.slf4j.LoggerFactory
import org.springframework.beans.PropertyAccessorUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.util.ReflectionUtils
import org.springframework.web.bind.annotation.*
import ru.abbysoft.rehearsapp.model.Place
import ru.abbysoft.rehearsapp.server.data.PlacesRepository
import java.util.*
import kotlin.reflect.KMutableProperty
import kotlin.reflect.full.declaredMembers
import kotlin.reflect.full.memberProperties

@RestController
class PlaceController {

    private val logger = LoggerFactory.getLogger(PlaceController::class.java)

    @Autowired
    private lateinit var placesRepository : PlacesRepository

    @RequestMapping("/")
    fun index() : String {
        val place = getRandomPlaceFromRepo()
        return """
            This is Rehearsapp REST api index page!
             <br>
             <a href='${APP_PAGE}'>about Rehearsapp</a>
             <br>
             <a href='/place/{${place?.id}}/'>get Place</a>
             <a href='/place/getAll/'>get all places</a> 
        """
    }

    private fun getRandomPlaceFromRepo() : Place? {
        val places = getAllPlaces()

        for (place in places) {
            return place
        }

        return null
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

    @PutMapping("/place/update/")
    fun updatePlace(@RequestBody place: Place): Boolean {
        val updated = placesRepository.findById(place.id)
        if (updated.isEmpty) {
            throw PlaceNotFoundException()
        }

        placesRepository.save(place)
        return true
    }

    @PatchMapping("/place/{id}/")
    fun patchPlace(@PathVariable("id") id: Long, fields: Map<String, Any>) {
        val optional = placesRepository.findById(id)
        if (optional.isEmpty) {
            logger.error("Can't get place with id $id")
            return
        }

        val place = optional.get()

        for ((k, v) in fields) {
            logger.debug("trying to set $k field to $v on object with id $id")

            val field = ReflectionUtils.findField(Place::class.java, k)
            if (field == null) {
                logger.error("Cannot find field $k in class ${Place::class.simpleName}")
                return
            }

            val property = Place::class.memberProperties.find { it.name == k } as KMutableProperty<*>
            property.setter.call(place, v)

            logger.debug("field $k changed for ${Place::class.simpleName} with id $id")
        }

        placesRepository.save(place)
        logger.debug("saved place $place")
    }

    class PlaceNotFoundException : Exception()
}