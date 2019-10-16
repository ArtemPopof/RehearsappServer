package ru.abbysoft.rehearsapp.server

import org.junit.Assert.assertNotNull
import org.junit.Test
import ru.abbysoft.rehearsapp.server.controller.ImageStorageController
import java.util.*

class ImageStorageControllerTest {

    @Test
    fun testImageStoredCorrectlyAndReturned() {
        val controller = ImageStorageController()

        val input = getData()
        val response = controller.saveImage(input)
        assertNotNull(response.imageId)
        val returned = controller.getImage(response.imageId as String)

        assert(Arrays.compare(input, returned) == 0)
    }

    private fun getData(): ByteArray {
        return byteArrayOf(5, 2, 1, 4, 5)
    }
}