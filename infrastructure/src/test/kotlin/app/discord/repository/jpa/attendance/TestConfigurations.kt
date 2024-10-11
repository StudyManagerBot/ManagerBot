package app.discord.repository.jpa.attendance

import io.kotest.core.config.AbstractProjectConfig
import io.kotest.extensions.spring.SpringExtension

class TestConfigurations: AbstractProjectConfig() {
    override fun extensions() = listOf(SpringExtension)
}