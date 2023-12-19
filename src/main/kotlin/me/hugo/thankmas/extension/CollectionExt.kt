package me.hugo.thankmas.extension

public fun <T> Collection<T>.chooseWeighted(weightSupplier: (T) -> Double): T {
    if (isEmpty()) throw NoSuchElementException("Tried to choose a randomly weighted element on empty collection.")

    val totalWeighting = sumOf { weightSupplier(it) }

    var probabilityIterator = 0.0
    val randomNumber: Double = Math.random() * totalWeighting

    forEach {
        probabilityIterator += weightSupplier(it)
        if (probabilityIterator >= randomNumber) return it
    }

    throw NoSuchElementException("Could not choose a randomly weighted item on this collection.")
}