package org.practicatrim2.personajes

import org.practicatrim2.capitalizar

class Hunter (override val nombre:String, override val genero:String, override val raza: Razas):Personaje(nombre, genero, raza){

    override fun toString(): String {
        return "${nombre.capitalizar()} is a $genero Hunter trained as an ${raza.description}."
    }
}