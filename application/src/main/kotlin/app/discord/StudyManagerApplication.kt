package app.discord

import app.discord.common.EnableDiscordBot
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
@EnableDiscordBot
class StudyManagerApplication

fun main(args: Array<String>){ runApplication<StudyManagerApplication>(*args) }