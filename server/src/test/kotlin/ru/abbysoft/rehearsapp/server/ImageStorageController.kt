package ru.abbysoft.rehearsapp.server

import org.junit.Test
import ru.abbysoft.rehearsapp.server.ImageStorageController
import java.util.*

class ImageStorageControllerTest {

    @Test
    fun testImageStoredCorrectlyAndReturned() {
        val controller = ImageStorageController()

        val input = getData()
        val id = controller.saveImage(input)
        val returned = controller.getImage(id)

        assert(Arrays.compare(input, returned) == 0)
    }

    private fun getData(): ByteArray {
        return byteArrayOf(5, 2, 1, 4, 5)
    }
}