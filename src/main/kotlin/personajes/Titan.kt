package org.practicatrim2.personajes

import org.practicatrim2.capitalizar

class Titan (override val nombre: String, override val genero:String, override val raza:Razas ):Personaje(nombre,genero,raza) {

    override fun toString(): String {
        return "${nombre.capitalizar()} is a $genero Titan, born and risen as an ${raza.description}."
    }
}