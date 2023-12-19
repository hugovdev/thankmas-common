package me.hugo.thankmas.state

/**
 * Allows you to listen to value changes.
 */
public class StatefulValue<T>(initialValue: T) {

    /** All the runnables that get run when this value changes. */
    private val subscribers: MutableList<(old: T, new: T, value: StatefulValue<T>) -> Unit> = mutableListOf()

    /** Value to change and listen to. */
    public var value: T = initialValue
        set(newValue) {
            if (newValue == value) return

            subscribers.forEach { it(field, newValue, this) }

            field = newValue
        }

    public fun subscribe(listener: (old: T, new: T, value: StatefulValue<T>) -> Unit) {
        subscribers.add(listener)
    }

    public fun unsubscribe(listener: (old: T, new: T, value: StatefulValue<T>) -> Unit) {
        subscribers.remove(listener)
    }
}