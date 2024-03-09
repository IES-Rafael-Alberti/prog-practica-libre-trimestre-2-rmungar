package org.practicatrim2.personajes

import org.practicatrim2.items.Arma
import org.practicatrim2.items.Armadura

class Hunter (nombre:String, genero:String, razas: Razas):Personaje(nombre, genero, razas, clase = Clases.HUNTER){
    override val armaEquipada :MutableList<Arma> = mutableListOf()//Lista con el conjunto de 3 armas equipadas actualmente
    override val armaduraEquipada: MutableList<Armadura> = mutableListOf()//Lista con el conjunto de 5 partes de armadura equipadas actualmente

    companion object{
        fun generarPersonaje(datosPersonaje:String): Hunter {
            val datosPersonajeProcesados = datosPersonaje.split(" ; ")
            val raza = obtenerRaza(datosPersonajeProcesados)
            val hunter = Hunter(datosPersonajeProcesados[1], datosPersonajeProcesados[2], raza)
            return hunter
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
        return "CURRENT ARMOR SET \n${armaduraEquipada[0]} helmet \n${armaduraEquipada[1]} grips\n ${armaduraEquipada[2]} vest\n ${armaduraEquipada[3]} strides \n${armaduraEquipada[4]} cloak"
    }
    override fun armaEquipadaToString():String{
        return "CURRENT WEAPONS SET \n${armaEquipada[0].mostrarInformacion()} \n${armaEquipada[1].mostrarInformacion()} \n ${armaEquipada[2].mostrarInformacion()} "
    }


}