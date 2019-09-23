package ru.abbysoft.rehearsapp.server

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class MainController {

    @RequestMapping("/")
    fun index() : String {
        return "This is Rehearsapp REST api index page! \n\n\n <a href='${APP_PAGE}'>about Rehearsapp</a>"
    }
}