package org.practicatrim2.items

import org.practicatrim2.personajes.Personaje

interface Equipable<T> {
    fun equipable(itemsEquipados: MutableList<T>):Boolean

    fun equipar(item: T, personaje: Personaje)

    fun preguntarParaEquipar(item: T, personaje: Personaje):Boolean
}

interface Sustituible<T, E> {
    fun sustituir(item: T, personaje: E)
}

interface Guardable<A>{
    fun guardar(item: A)
    fun preguntarParaGuardar(item: A)
}
