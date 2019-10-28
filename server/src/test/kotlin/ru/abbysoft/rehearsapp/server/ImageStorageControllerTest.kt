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

    @Test
    fun testImageSavingIsNotVeryLong() {
        val maxSaveTime = 2 * 1000

        val data = getBigData()
        val controller = ImageStorageController()

        val start = System.currentTimeMillis()
        val imageID = controller.saveImage(data)
        val diff = System.currentTimeMillis() - start

        assert(diff < maxSaveTime)
        assertNotNull(imageID)
    }

    private fun getData(): ByteArray {
        return byteArrayOf(5, 2, 1, 4, 5)
    }

    private fun getBigData(): ByteArray {
        val array = ByteArray(1 * 1024 * 1024)

        for (i in 0 until 1 * 1024 * 1024) {
            array[i] = (i % 127).toByte()
        }

        return array
    }
}