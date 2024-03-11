package org.practicatrim2.personajes

class Warlock (override val nombre:String, override val genero:String, override val raza: Razas): Personaje(nombre, genero, raza) {

    override fun toString(): String {
        return "$nombre is a $genero Warock, instructed by the ${raza.description} race."
    }
}
