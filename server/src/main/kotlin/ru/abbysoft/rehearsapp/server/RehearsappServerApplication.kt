package ru.abbysoft.rehearsapp.server

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.runApplication

@EntityScan("ru.abbysoft.rehearsapp.model")
@SpringBootApplication
class RehearsappServerApplication

fun main(args: Array<String>) {
	runApplication<RehearsappServerApplication>(*args)
}
