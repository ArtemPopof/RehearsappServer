package ru.abbysoft.rehearsapp.server

import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.any
import org.mockito.BDDMockito.given
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.context.junit4.SpringRunner
import ru.abbysoft.rehearsapp.model.Place
import ru.abbysoft.rehearsapp.model.Room
import ru.abbysoft.rehearsapp.server.data.PlacesRepository
import java.util.*

private const val PLACE_ID = 5L

@RunWith(SpringRunner::class)
@SpringBootTest
class PlaceControllerTest {

    private var lastPlace : Place? = null

    @MockBean
    private lateinit var placesRepository : PlacesRepository

    @Autowired
    private lateinit var controller : PlaceController

    @Test
    fun testUnicodeSymbolsInFieldsShouldPersistCorrect() {
        Mockito.`when`<Optional<Place>>(placesRepository.findById(PLACE_ID)).then {
            println(it.arguments[0])
            println("lastPlace: $lastPlace")
            Optional.ofNullable(lastPlace)
        }

        Mockito.`when`<Place>(placesRepository.save(any())).then {
            lastPlace = it.arguments[0] as Place
            lastPlace?.id = PLACE_ID
            lastPlace
        }

        val place = createPlace()
        val id = controller.addPlace(place)
        val persistedPlace = controller.getPlace(id)

        assert(persistedPlace != null)
        assertEquals(place.name, persistedPlace?.name)
    }

    @Test
    fun testPatchMethodShouldPatchCorrectly() {
        val place = createPlace()
        val fields = mapOf<String, Any>("headerImageId" to 5L)
        var saved: Place? = null

        given(placesRepository.findById(place.id)).willReturn(Optional.of(place))
        given(placesRepository.save(any(Place::class.java))).willAnswer {
            saved = it.getArgument<Place>(0)

            null
        }

        controller.patchPlace(place.id, fields)

        assertEquals(saved?.headerImageId, 5L)
    }

    @Test
    fun testUpdateMethodShouldUpdateCorrectly() {
        val place = createPlace()
        val newRooms = createRooms()
        val newPlace = place.apply { rooms = newRooms }
        var saved: Place? = null

        given(placesRepository.findById(place.id)).willReturn(Optional.of(place))
        given(placesRepository.save(any(Place::class.java))).willAnswer {
            saved = it.getArgument<Place>(0)

            null
        }

        controller.updatePlace(newPlace)

        assertEquals(saved, newPlace)
    }

    private fun createPlace(): Place {
        val place = Place()
        place.name = "Название на русском"
        place.id = PLACE_ID
        return place
    }

    private fun createRooms(): List<Room> {
        return mutableListOf(
                Room(0, "firstRoom", 25.5f, 22.3f),
                Room(0, "secondRoom", 25.5f, 22.3f),
                Room(0, "thirdRoom", 25.5f, 22.3f),
                Room(0, "fourthRoom", 25.5f, 22.3f)
                )
    }

}