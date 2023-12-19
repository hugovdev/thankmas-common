package me.hugo.thankmas

import dev.kezz.miniphrase.MiniPhrase
import dev.kezz.miniphrase.i18n.PropertiesFileTranslationRegistry
import me.hugo.thankmas.smallcaps.Alphabet
import net.kyori.adventure.text.minimessage.MiniMessage
import net.kyori.adventure.text.minimessage.tag.resolver.TagResolver
import java.io.File
import java.util.*

/**
 * Class that initializes translations for this server/plugin.
 */
public class DefaultTranslations(translationFolder: File) {

    public companion object {
        public lateinit var instance: DefaultTranslations
    }

    // Create a MiniPhrase translations instance for this ThankmasPlugin!
    public val translations: MiniPhrase = MiniPhrase.configureAndBuild {
        translationRegistry(PropertiesFileTranslationRegistry(translationFolder, fetchFromResources = true))
        defaultLocale(Locale.US)
        miniMessage(MiniMessage.builder()
            .editTags { resolver -> resolver.resolver(TagResolver.resolver("small_caps", Alphabet::convert)) }
            .build())
    }

    init {
        instance = this
    }
}