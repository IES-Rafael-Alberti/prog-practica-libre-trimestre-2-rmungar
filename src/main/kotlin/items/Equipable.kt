package org.practicatrim2.items

interface Equipable<T> {
    fun equipable(t: T):Boolean
}

interface Sustituible<T,E> {
    fun sustituir(t:T, e:E)
}

interface Guardable<A>{
    fun guardar(a:A)
}