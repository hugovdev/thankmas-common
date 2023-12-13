package me.hugo.translationstest

import de.cubbossa.translations.AppTranslations
import de.cubbossa.translations.Translations
import de.cubbossa.translations.TranslationsFramework
import de.cubbossa.translations.persistent.PropertiesMessageStorage
import de.cubbossa.translations.persistent.PropertiesStyleStorage
import net.kyori.adventure.text.minimessage.tag.resolver.Placeholder
import org.bukkit.Bukkit
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.plugin.java.JavaPlugin
import java.io.File

class TranslationsTest : JavaPlugin(), Listener {

    private lateinit var translations: Translations
    private lateinit var instance: TranslationsTest

    override fun onEnable() {
        instance = this

        TranslationsFramework.enable(File(dataFolder, "/.."))

        translations = TranslationsFramework.application("TranslationsTest")
        translations.messageStorage = PropertiesMessageStorage(File(dataFolder, "/lang/"))
        translations.styleStorage = PropertiesStyleStorage(File(dataFolder, "/lang/styles.properties"))

        translations.loadStyles()
        translations.loadLocales()

        TranslationsFramework.global().loadLocales()

        Bukkit.getPluginManager().registerEvents(instance, instance)
    }

    @EventHandler
    private fun onJoin(event: PlayerJoinEvent) {
        val player = event.player
        
        player.sendMessage(
            translations.message("message.test").formatted(Placeholder.unparsed("player_name", player.name))
        )
        player.sendMessage(translations.message("message.unknown"))

        player.sendMessage(
            TranslationsFramework.global().message("message.test")
                .formatted(Placeholder.unparsed("player_name", player.name))
        )
    }

    override fun onDisable() {
        translations.close()
        TranslationsFramework.disable()
    }

}