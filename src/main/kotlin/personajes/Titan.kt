package org.practicatrim2.personajes

import org.practicatrim2.items.Arma
import org.practicatrim2.items.Armadura

class Titan(nombre: String, genero:String, raza:Razas):Personaje(nombre,genero,raza, clase = Clases.TITAN) {
    override val armaEquipada :MutableList<Arma> = mutableListOf() //Lista con el conjunto de 3 armas equipadas actualmente
    override val armaduraEquipada: MutableList<Armadura> = mutableListOf() //Lista con el conjunto de 5 partes de armadura equipadas actualmente

    companion object{
        fun generarPersonaje(datosPersonaje:String): Titan {
            val datosPersonajeProcesados = datosPersonaje.split(" ; ")
            val raza = obtenerRaza(datosPersonajeProcesados)
            val titan = Titan(datosPersonajeProcesados[1], datosPersonajeProcesados[2], raza)
            return titan
        }
        fun obtenerRaza(datosPersonajeProcesados: List<String>):Razas{
            when(datosPersonajeProcesados[3]){
                "Human" -> return Razas.HUMAN
                "Awoken" -> return Razas.AWOKEN
                else -> return Razas.EXO
            }
        }
    }
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