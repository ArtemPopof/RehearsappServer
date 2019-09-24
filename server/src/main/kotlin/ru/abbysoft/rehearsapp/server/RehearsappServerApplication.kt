package ru.abbysoft.rehearsapp.server

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class RehearsappServerApplication

fun main(args: Array<String>) {
	runApplication<RehearsappServerApplication>(*args)
}
