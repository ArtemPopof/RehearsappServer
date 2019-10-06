package ru.abbysoft.rehearsapp.server

import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.any
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.context.junit4.SpringRunner
import ru.abbysoft.rehearsapp.model.Place
import ru.abbysoft.rehearsapp.server.data.PlacesRepository
import java.util.*

@RunWith(SpringRunner::class)
@SpringBootTest
class MainControllerTest {

    private var lastPlace : Place? = null

    @MockBean
    private lateinit var placesRepository : PlacesRepository

    @Autowired
    private lateinit var controller : MainController

    @Before
    fun mockRepository() {
        Mockito.`when`<Place>(placesRepository.save(any())).then {
            lastPlace = it.arguments[0] as Place
            lastPlace?.id = 5
            lastPlace
        }
        Mockito.`when`<Optional<Place>>(placesRepository.findById(5)).then {
            println(it.arguments[0])
            println("lastPlace: $lastPlace")
            Optional.ofNullable(lastPlace)
        }
    }

    @Test
    fun testUnicodeSymbolsInFieldsShouldPersistCorrect() {
        val place = createPlace()
        val id = controller.addPlace(place)
        val persistedPlace = controller.getPlace(id)

        assert(persistedPlace != null)
        assertEquals(place.name, persistedPlace?.name)
    }

    private fun createPlace(): Place {
        val place = Place()
        place.name = "Название на русском"
        return place
    }
}