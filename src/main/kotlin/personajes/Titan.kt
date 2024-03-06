package org.practicatrim2.personajes

import org.practicatrim2.items.Arma
import org.practicatrim2.items.Armadura

class Titan(nombre: String, genero:String, raza:Razas):Personaje(nombre,genero,raza, clase = Clases.TITAN) {
    override val armaEquipada :List<Arma> = emptyList() //Lista con el conjunto de 3 armas equipadas actualmente
    override val armaduraEquipada: List<Armadura> = emptyList() //Lista con el conjunto de 5 partes de armadura equipadas actualmente

    override fun toString(): String {
        return "$nombre is a $genero Titan, born and risen in the ${raza.description} race. \n${armaEquipadaToString()}"
    }
    override fun armaduraEquipadaToString():String{
        return "CURRENT ARMOR SET \n${armaduraEquipada[0]} helmet \n${armaduraEquipada[1]} gauntlets\n ${armaduraEquipada[2]} chest plate\n ${armaduraEquipada[3]} greaves \n${armaduraEquipada[4]} mark"
    }
    override fun armaEquipadaToString():String{
        return "CURRENT WEAPONS SET \n${armaEquipada[0]} \n${armaEquipada[1]} \n ${armaEquipada[2]} "
    }
}