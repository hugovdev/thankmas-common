package me.hugo.thankmas.player

import com.github.benmanes.caffeine.cache.Caffeine
import java.util.*
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.ConcurrentMap
import java.util.concurrent.TimeUnit

/**
 * Offers a way to save custom player data classes and
 * provides functions to fetch it, change it and remove it.
 */
public class PlayerDataManager<P : PlayerData<P>>(private val dataSupplier: (playerUUID: UUID) -> P) {

    /**
     * Cache that contains the player data from every player
     * that connected in the last minute.
     */
    private val loginCache = Caffeine.newBuilder()
        .expireAfterAccess(1, TimeUnit.MINUTES)
        .build<UUID, P>()

    /** Contains the player data for every player who is online. */
    private val sessionPlayerData: ConcurrentMap<UUID, P> = ConcurrentHashMap()

    /** Gets the player data for [uuid]. */
    public fun getPlayerData(uuid: UUID): P {
        val data = getPlayerDataOrNull(uuid)
        requireNotNull(data) { "Tried to get player data of $uuid but it's null." }

        return data
    }

    /** Gets the player data for [uuid], can be null. */
    public fun getPlayerDataOrNull(uuid: UUID): P? {
        return sessionPlayerData[uuid]
    }

    /** Create player data for [uuid] if non-existent. */
    public fun createPlayerData(uuid: UUID): P {
        return dataSupplier(uuid).also { loginCache.put(uuid, it) }
    }

    /** Registers the player data in persistent server storage. */
    public fun registerPlayerData(uuid: UUID): P {
        val playerData = requireNotNull(loginCache.getIfPresent(uuid))
        { "Tried to register $uuid's player data but found nothing in login cache!" }

        sessionPlayerData[uuid] = playerData

        return playerData
    }

    /** @returns whether data for [uuid] is currently saved. */
    public fun hasPlayerDaya(uuid: UUID): Boolean = sessionPlayerData.containsKey(uuid)

    /** Removes [uuid]'s player data. */
    public fun removePlayerData(uuid: UUID): P? {
        return sessionPlayerData.remove(uuid)
    }

    /** @returns all the registered player data. */
    public fun getAllPlayerData(): Collection<P> {
        return sessionPlayerData.values.toSet()
    }
}