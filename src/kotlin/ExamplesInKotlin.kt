package kotlin

import models.Fruit
import kotlin.reflect.KClass
import kotlin.reflect.full.primaryConstructor

// declaration-site variance
class Producer<out T>(private val member: T) {
    fun get(): T {
        return member
    }
}

class Consumer<in T>(private var member: T) {
    fun set(member: T) {
        this.member = member
    }
}

// use-site variance

fun <T> copyStuff(from: Producer<out T>, to: Consumer<in T>) {
    to.set(from.get())
}

fun getSize(someCollection: Collection<*>): Int {
    return someCollection.size
}

// reified types
inline fun <reified T : Fruit> loadFromDb(id: String): T =
    loadFromDb(T::class, id)

fun <T : Fruit> loadFromDb(cls: KClass<T>, id: String): T {
    val entity = cls.primaryConstructor!!.call()
    val tableName = cls.simpleName
    // DB magic goes here - load from table `tableName`,
    // and use the data to populate `entity`
    // (possibly via `memberProperties`)
    return entity
}