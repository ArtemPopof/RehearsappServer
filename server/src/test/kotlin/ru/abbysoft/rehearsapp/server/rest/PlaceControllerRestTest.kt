package ru.abbysoft.rehearsapp.server.rest

import org.hamcrest.core.Is
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.BDDMockito.given
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc
import ru.abbysoft.rehearsapp.model.Place
import ru.abbysoft.rehearsapp.server.controller.PlaceController

import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath

private const val ID = 0L

@RunWith(SpringRunner::class)
@WebMvcTest(PlaceController::class)
class PlaceControllerRestTest {

    @MockBean
    private lateinit var controller: PlaceController

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Test
    fun testUnicodeNameForPlaceShouldBeReturnedCorrectly() {
        val place = getPlaceWithRussianName()

        given(controller.getPlace(ID)).willReturn(place)

        mockMvc.perform(get("/place/0/").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk)
                .andExpect(jsonPath("$.name", Is.`is`(place.name)))
    }

    private fun getPlaceWithRussianName(): Place {
        val place = Place()
        place.name = "Имя на русском"
        return place
    }
}