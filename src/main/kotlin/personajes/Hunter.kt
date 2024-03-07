package org.practicatrim2.personajes

import org.practicatrim2.items.Arma
import org.practicatrim2.items.Armadura

class Hunter (nombre:String, genero:String, razas: Razas):Personaje(nombre, genero, razas, clase = Clases.HUNTER){
    override val armaEquipada :List<Arma> = emptyList()//Lista con el conjunto de 3 armas equipadas actualmente
    override val armaduraEquipada: List<Armadura> = emptyList()//Lista con el conjunto de 5 partes de armadura equipadas actualmente

    override fun toString(): String {
        return "$nombre is a $genero Titan, born and risen in the ${raza.description} race. \n${armaEquipadaToString()}"
    }
    override fun armaduraEquipadaToString():String{
        return "CURRENT ARMOR SET \n${armaduraEquipada[0]} helmet \n${armaduraEquipada[1]} grips\n ${armaduraEquipada[2]} vest\n ${armaduraEquipada[3]} strides \n${armaduraEquipada[4]} cloak"
    }
    override fun armaEquipadaToString():String{
        return "CURRENT WEAPONS SET \n${armaEquipada[0].mostrarInformacion()} \n${armaEquipada[1].mostrarInformacion()} \n ${armaEquipada[2].mostrarInformacion()} "
    }
}