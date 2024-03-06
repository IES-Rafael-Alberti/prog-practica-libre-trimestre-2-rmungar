package org.practicatrim2.personajes

import org.practicatrim2.items.Arma
import org.practicatrim2.items.Armadura

class Warlock(nombre:String, genero:String, raza: Razas): Personaje(nombre, genero, raza, clase = Clases.WARLOCK) {
    override val armaEquipada :List<Arma> = emptyList()//Lista con el conjunto de 3 armas equipadas actualmente
    override val armaduraEquipada: List<Armadura> = emptyList()//Lista con el conjunto de 5 partes de armadura equipadas actualmente

    override fun toString(): String {
        return "$nombre is a $genero Titan, born and risen in the ${raza.description} race. \n${armaEquipadaToString()}"
    }
    override fun armaduraEquipadaToString():String{
        return "CURRENT ARMOR SET \n${armaduraEquipada[0]} hood \n${armaduraEquipada[1]} gloves\n ${armaduraEquipada[2]} robes\n ${armaduraEquipada[3]} boots \n${armaduraEquipada[4]} bond"
    }
    override fun armaEquipadaToString():String{
        return "CURRENT WEAPONS SET \n${armaEquipada[0]} \n${armaEquipada[1]} \n ${armaEquipada[2]} "
    }
}
