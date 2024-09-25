package me.hugo.thankmas.player

import java.util.*
import kotlin.time.Duration

/** Base player data class with a simple cooldown system. */
public open class PlayerData<P: PlayerData<P>>(
    protected val playerUUID: UUID,
    protected val playerDataManager: PlayerDataManager<P>
) {

    /** Map that contains the timestamp of when each cooldown ends. */
    private val activeCooldowns: MutableMap<String, Long> = mutableMapOf()

    /** @returns whether a player's cooldown of [id] is active. */
    public fun isOnCooldown(id: String): Boolean {
        val finishTime: Long = activeCooldowns[id] ?: return false

        return finishTime > System.currentTimeMillis()
    }

    /** @returns the remaining waiting time for the cooldown with id [id]. */
    public fun getRemainingCooldown(id: String): Int {
        val finishTime: Long = activeCooldowns[id] ?: return 0

        return (finishTime - System.currentTimeMillis()).toInt() / 1000
    }

    /** Sets the waiting time for the cooldown with id [id] to [time]. */
    public fun setCooldown(id: String, time: Duration) {
        activeCooldowns[id] = System.currentTimeMillis() + time.inWholeMilliseconds
    }

}