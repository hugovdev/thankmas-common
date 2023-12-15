package me.hugo.thankmas.lang

import org.koin.core.component.KoinComponent

/**
 * Class that provides translation context to any
 * other object in a plugin or server and allows for
 * dependency injection.
 */
public interface TranslatedComponent : Translated, KoinComponent