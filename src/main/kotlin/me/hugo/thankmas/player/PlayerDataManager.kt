package me.hugo.thankmas.player

import java.util.*
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.ConcurrentMap

/**
 * Offers a way to save custom player data classes and
 * provides functions to fetch it, change it and remove it.
 */
public class PlayerDataManager<T : PlayerData>(private val dataSupplier: (playerUUID: UUID) -> T) {

    private val playerData: ConcurrentMap<UUID, T> = ConcurrentHashMap()

    /** Gets the player data for [uuid]. */
    public fun getPlayerData(uuid: UUID): T {
        val data = getPlayerDataOrNull(uuid)
        requireNotNull(data) { "Tried to get player data of $uuid but it's null." }

        return data
    }

    /** Gets the player data for [uuid], can be null. */
    public fun getPlayerDataOrNull(uuid: UUID): T? {
        return playerData[uuid]
    }

    /** Create player data for [uuid] if non-existent. */
    public fun createPlayerData(uuid: UUID): T {
        return playerData.computeIfAbsent(uuid) { dataSupplier(uuid) }
    }

    /** @returns whether data for [uuid] is currently saved. */
    public fun hasPlayerDaya(uuid: UUID): Boolean = playerData.containsKey(uuid)

    /** Removes [uuid]'s player data. */
    public fun removePlayerData(uuid: UUID): T? {
        return playerData.remove(uuid)
    }

    /** @returns all the registered player data. */
    public fun getAllPlayerData(): Collection<T> {
        return playerData.values
    }
}