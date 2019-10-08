package ru.abbysoft.rehearsapp.server

import org.springframework.web.bind.annotation.*
import java.io.File
import java.io.FileInputStream
import java.io.FileNotFoundException
import java.io.FileOutputStream

@RestController
@RequestMapping("/image")
class ImageStorageController {

    /**
     * Load image on server
     *
     * @return image identifier
     */
    @PostMapping("/save/")
    fun saveImage(data: ByteArray) : String {
        val name = generateNewFile()
        saveFile(File(name), data)

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
        return readFile(id)
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