package ru.abbysoft.rehearsapp.server.controller

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import org.springframework.web.servlet.mvc.support.RedirectAttributes
import ru.abbysoft.rehearsapp.model.ImageControllerResponse
import java.io.File
import java.io.FileInputStream
import java.io.FileNotFoundException
import java.io.FileOutputStream

@RestController
@RequestMapping("/image")
class ImageStorageController {

    val logger: Logger = LoggerFactory.getLogger(ImageStorageController::class.java)

    /**
     * Load image on server
     *
     * @return image identifier
     */
    @PostMapping("/save/")
    fun saveImage(@RequestBody data: ByteArray) : ImageControllerResponse {
        val name = generateNewFile()
        val file = File(name)
        saveFile(file, data)

        if (file.exists()) {
            logger.info("Saved image $name with size ${data.size}")
        } else {
            logger.error("Image $name is failed to load")
        }

        return ImageControllerResponse().apply { imageId = name }
    }

    @PostMapping("/save")
    fun saveImage(@RequestParam("file") file: MultipartFile,
                  redirectAttributes: RedirectAttributes): ImageControllerResponse {
        logger.info("Saved image with size: ${file.size}")

        if (file.isEmpty) {
            redirectAttributes.addFlashAttribute("message", "Please select file to upload")
            logger.error("File is empty, could not upload")
            return ImageControllerResponse()
        }

        val bytes = file.bytes
        val response = saveImage(bytes)

        redirectAttributes.addFlashAttribute("message",
                "You successfully uploaded image ''");

        logger.info("Successfully saved image")

        return response
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
            logger.info("saving $id image with size ${data.size}")
        } else {
            logger.error("Failed to find image with id $id")
        }

        return data
    }

    @GetMapping("/{id}.jpeg", produces = [MediaType.IMAGE_JPEG_VALUE])
    fun getImageAsResponse(@PathVariable("id") id: String): ByteArray? {
        val data = readFile(id)
        if (data != null) {
            logger.info("saving /images/$id.jpeg image with size ${data.size}")
        } else {
            logger.error("Failed to find image with id $id")
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