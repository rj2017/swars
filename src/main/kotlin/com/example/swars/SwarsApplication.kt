package com.example.swars

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SwarsApplication

fun main(args: Array<String>) {
	runApplication<SwarsApplication>(*args)
}
