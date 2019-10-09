package ru.abbysoft.rehearsapp.server

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import java.io.File
import java.io.FileInputStream
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.util.logging.Level
import java.util.logging.Logger

@RestController
@RequestMapping("/image")
class ImageStorageController {

    @Autowired
    lateinit var logger: Logger

    /**
     * Load image on server
     *
     * @return image identifier
     */
    @PostMapping("/save/")
    fun saveImage(@RequestBody data: ByteArray) : String {
        val name = generateNewFile()
        val file = File(name)
        saveFile(file, data)

        if (file.exists()) {
            logger.log(Level.INFO, "Saved image $name with size ${data.size}")
        } else {
            logger.log(Level.SEVERE, "Image $name is failed to load")
        }

        return name
    }

    private fun generateNewFile(): String {
        return File.createTempFile("img", "", null).name
    }

    private fun saveFile(file: File, data: ByteArray) {
        val fos = FileOutputStream(file)
        fos.write(data)

        fos.close()
    }

    @GetMapping("/{id}/")
    fun getImage(@PathVariable("id") id: String): ByteArray? {
        val data = readFile(id)
        if (data != null) {
            logger.log(Level.INFO, "get $id image with size ${data.size}")
        } else {
            logger.log(Level.SEVERE, "Failed to find image with id $id")
        }

        return data
    }

    private fun readFile(id: String): ByteArray? {
        try {
            FileInputStream(File(id)).use {
                return it.readAllBytes()
            }
        } catch (ex: FileNotFoundException) {
            return null
        }
    }

}