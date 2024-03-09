package org.practicatrim2.personajes

data class Hunter (override val nombre:String, override val genero:String, override val raza: Razas):Personaje(nombre, genero, raza){

    override fun toString(): String {
        return "$nombre is a $genero Hunter trained by the ${raza.description} race. \n${armaEquipadaToString()}"
    }
}