package com.example.forumAlura.forumAlura

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.web.config.EnableSpringDataWebSupport

@SpringBootApplication
@EnableSpringDataWebSupport
class ForumAluraApplication

fun main(args: Array<String>) {
	runApplication<ForumAluraApplication>(*args)
}
