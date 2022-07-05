package com.kevin.kotlin.delegate

import kotlin.properties.Delegates
import kotlin.properties.PropertyDelegateProvider
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

/**
 * Created by: kevin
 * Date: 2022-07-05
 */

class Delegate {
//    operator fun getValue(example: Example, property: KProperty<*>): String {
//    }
//
//    operator fun setValue(example: Example, property: KProperty<*>, s: String) {
//    }

    operator fun getValue(thisRef: Any?, property: KProperty<*>): String {
        return "$thisRef, thank you for delegating '${property.name}' to me!"
    }

    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: String) {
        println("$value has been assigned to '${property.name}' in $thisRef.")
    }
}

class MyDelegateProvider : PropertyDelegateProvider<Any, Delegate>{
    override fun provideDelegate(thisRef: Any, property: KProperty<*>): Delegate {
        return Delegate()
    }
}

class Example {
    var p: String by MyDelegateProvider()
    val k: String by lazy(LazyThreadSafetyMode.NONE) {
        println("computed!")
        "Hello"
    }
}

class User {
    var name: String by Delegates.observable("<no name>") {
            prop, old, new ->
        println("$old -> $new")
    }
}

var topLevelInt: Int = 0

class ClassWithDelegate(val anotherClassInt: Int)
class MyClass(var memberInt: Int, val anotherClassInstance: ClassWithDelegate) {
    var delegatedToMember: Int by this::memberInt
    var delegatedToTopLevel: Int by ::topLevelInt

    val delegatedToAnotherClass: Int by anotherClassInstance::anotherClassInt

    var newName: Int = 0
    @Deprecated("Use 'newName' instead", ReplaceWith("newName"))
    var oldName: Int by this::newName
}
var MyClass.extDelegated: Int by ::topLevelInt

class UserV2(val map: Map<String, Any?>) {
    val name: String by map//map["name"]
    val age: Int     by map//map["age"]
}

fun main() {
    val userv2 = UserV2(mapOf(
        "name" to "Kevin",
        "age" to 25
    ))


    val myClass = MyClass(10, ClassWithDelegate(30))
    // 通知：'oldName: Int' is deprecated.
    // Use 'newName' instead
    myClass.oldName = 42
    println(myClass.newName) // 42

    val user = User()
    user.name = "first"
    user.name = "second"

    val example = Example()
    example.p = "Okio"
    println(example.p)
}