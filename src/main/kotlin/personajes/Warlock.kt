package org.practicatrim2.personajes

import org.practicatrim2.capitalizar

/**
 * Clase que representa un hechicero en el juego.
 *
 * @property nombre El nombre del hechicero.
 * @property genero El género del hechicero.
 * @property raza La raza del hechicero.
 */
class Warlock (override val nombre:String, override val genero:String, override val raza: Razas): Personaje(nombre, genero, raza) {

    override fun toString(): String {
        return "${nombre.capitalizar()} is a $genero Warock, instructed as an ${raza.description}."
    }
}
