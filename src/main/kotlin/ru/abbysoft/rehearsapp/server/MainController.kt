package ru.abbysoft.rehearsapp.server

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
class MainController {

    private val random = Random(System.currentTimeMillis())

    @RequestMapping("/")
    fun index() : String {
        val randId = random.nextLong()
        return """
            This is Rehearsapp REST api index page!
             <br>
             <a href='${APP_PAGE}'>about Rehearsapp</a>
             <br>
             <a href='${PLACE_GET_METHOD_PATH}/?id=${randId}'>get Place</a>
        """
    }

    @GetMapping(PLACE_GET_METHOD_PATH)
    fun getPlace(@RequestParam("id") id : Long) : String {
        // get place from db and return
        return "place with id [${id}] is Saint-Petersburg Baltiyskaya house 88"
    }
}