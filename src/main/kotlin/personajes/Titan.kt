package org.practicatrim2.personajes

import org.practicatrim2.capitalizar

/**
 * Clase que representa un titan en el juego.
 *
 * @property nombre El nombre del titan.
 * @property genero El género del titan.
 * @property raza La raza del titan.
 */
class Titan (override val nombre: String, override val genero:String, override val raza:Razas ):Personaje(nombre,genero,raza) {

    override fun toString(): String {
        return "${nombre.capitalizar()} is a $genero Titan, born and risen as an ${raza.description}."
    }
}