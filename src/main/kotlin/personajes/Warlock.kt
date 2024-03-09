package org.practicatrim2.personajes

import org.practicatrim2.items.Arma
import org.practicatrim2.items.Armadura

class Warlock(nombre:String, genero:String, raza: Razas): Personaje(nombre, genero, raza, clase = Clases.WARLOCK) {
    companion object{
        fun generarPersonaje(datosPersonaje:String): Warlock {
            val datosPersonajeProcesados = datosPersonaje.split(" ; ")
            val raza = obtenerRaza(datosPersonajeProcesados)
            val warlock = Warlock(datosPersonajeProcesados[1], datosPersonajeProcesados[2], raza)
            return warlock
        }
        fun obtenerRaza(datosPersonajeProcesados: List<String>):Razas{
            when(datosPersonajeProcesados[3]){
                "Human" -> return Razas.HUMAN
                "Awoken" -> return Razas.AWOKEN
                else -> return Razas.EXO
            }
        }
    }
    override val armaEquipada :MutableList<Arma> = mutableListOf()//Lista con el conjunto de 3 armas equipadas actualmente
    override val armaduraEquipada: MutableList<Armadura> = mutableListOf()//Lista con el conjunto de 5 partes de armadura equipadas actualmente

    override fun toString(): String {
        return "$nombre is a $genero Titan, born and risen in the ${raza.description} race. \n${armaEquipadaToString()}"
    }
    override fun armaduraEquipadaToString():String{
        return "CURRENT ARMOR SET \n${armaduraEquipada[0].nombre} hood \n${armaduraEquipada[1].nombre} gloves\n ${armaduraEquipada[2].nombre} robes\n ${armaduraEquipada[3].nombre} boots \n${armaduraEquipada[4].nombre} bond"
    }
    override fun armaEquipadaToString():String{
        return "CURRENT WEAPONS SET \n${armaEquipada[0].mostrarInformacion()} \n${armaEquipada[1].mostrarInformacion()} \n ${armaEquipada[2].mostrarInformacion()} "
    }


}
