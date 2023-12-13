package me.hugo.thankmas

import dev.kezz.miniphrase.MiniPhrase
import dev.kezz.miniphrase.i18n.PropertiesFileTranslationRegistry
import java.io.File
import java.util.*

public class ThankmasPlugin(translationFolder: File) {

    public companion object {
        public lateinit var instance: ThankmasPlugin
    }

    public val translations: MiniPhrase = MiniPhrase.configureAndBuild {
        translationRegistry(PropertiesFileTranslationRegistry(translationFolder))
        defaultLocale(Locale.ENGLISH)
    }

    init {
        instance = this
    }
}