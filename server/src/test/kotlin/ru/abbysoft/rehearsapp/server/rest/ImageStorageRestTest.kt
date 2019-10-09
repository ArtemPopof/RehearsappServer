package ru.abbysoft.rehearsapp.server.rest

import junit.framework.Assert.assertEquals
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
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import ru.abbysoft.rehearsapp.model.ImageControllerResponse
import ru.abbysoft.rehearsapp.server.ImageStorageController

@WebMvcTest(ImageStorageController::class)
@RunWith(SpringRunner::class)
class ImageStorageRestTest {

    @MockBean
    private lateinit var controller: ImageStorageController

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Test
    fun testImageMustBeSavedProperly() {
        val data = getData()
        val name = "img19508057124"

        given(controller.saveImage(data)).willReturn(ImageControllerResponse().apply { imageId = name })

        mockMvc.perform(post("/image/save/")
                .contentType(MediaType.APPLICATION_JSON).content(data))
                .andExpect(status().isOk)
                .andExpect(jsonPath("$.imageId", Is.`is`(name)))
    }

    private fun getData(): ByteArray {
        return byteArrayOf(5, 2, 5, 1, 3, 5, 6, 7 ,8 , 5, 22)
    }

}