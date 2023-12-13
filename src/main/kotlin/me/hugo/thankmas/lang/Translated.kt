package me.hugo.thankmas.lang

import dev.kezz.miniphrase.MiniPhrase
import dev.kezz.miniphrase.MiniPhraseContext
import me.hugo.thankmas.ThankmasPlugin

public interface Translated : MiniPhraseContext {
    override val miniPhrase: MiniPhrase
        get() = ThankmasPlugin.instance.translations
}