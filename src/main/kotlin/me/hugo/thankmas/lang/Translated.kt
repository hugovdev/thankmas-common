package me.hugo.thankmas.lang

import dev.kezz.miniphrase.MiniPhrase
import dev.kezz.miniphrase.MiniPhraseContext
import me.hugo.thankmas.DefaultTranslations

/**
 * Class that provides translation context to any
 * other object in a plugin or server.
 */
public interface Translated : MiniPhraseContext {
    override val miniPhrase: MiniPhrase
        get() = DefaultTranslations.instance.translations
}