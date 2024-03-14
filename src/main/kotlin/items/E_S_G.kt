package org.practicatrim2.items

import org.practicatrim2.personajes.Personaje

/**
 * Interfaz que define comportamientos relacionados con la capacidad de ser equipado por un personaje.
 *
 * @param T El tipo de objeto que puede ser equipado.
 */
interface Equipable<T> {
    fun equipable(itemsEquipados: MutableList<T>):Boolean

    fun equipar(item: T, personaje: Personaje)

    fun preguntarParaEquipar(item: T, personaje: Personaje):Boolean
}

/**
 * Interfaz que define el comportamiento de sustitución de un objeto en un personaje.
 *
 * @param T El tipo de objeto que puede ser sustituido.
 * @param E El tipo de personaje en el que se puede sustituir el objeto.
 */
interface Sustituible<T, E> {
    fun sustituir(item: T, personaje: E)
}

/**
 * Interfaz que define el comportamiento para guardar y preguntar para guardar un objeto.
 *
 * @param A El tipo de objeto que se puede guardar.
 */
interface Guardable<A>{
    fun guardar(item: A)
    fun preguntarParaGuardar(item: A)
}
