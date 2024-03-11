package org.practicatrim2.personajes

class Titan (override val nombre: String, override val genero:String, override val raza:Razas ):Personaje(nombre,genero,raza) {

    override fun toString(): String {
        return "$nombre is a $genero Titan, born and risen in the ${raza.description} race."
    }
}