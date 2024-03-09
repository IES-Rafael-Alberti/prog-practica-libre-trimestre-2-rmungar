package org.practicatrim2.personajes
import org.practicatrim2.items.Arma
import org.practicatrim2.items.Armadura

abstract class Personaje(open val nombre: String, open val genero:String, open val raza:Razas) {
    open val armaduraEquipada:MutableList<Armadura> = mutableListOf()//Lista con el conjunto de 5 partes de armadura equipadas actualmente
    open val armaEquipada: MutableList<Arma> = mutableListOf()//Lista con el conjunto de 3 armas equipadas actualmente

    companion object{
        fun generarPersonaje(datosPersonaje:String): Personaje {
            val datosPersonajeProcesados = datosPersonaje.split(" ; ")
            val raza = obtenerRaza(datosPersonajeProcesados)
            when (datosPersonajeProcesados[0]){
                "W" -> {
                    val warlock = Warlock(datosPersonajeProcesados[1], datosPersonajeProcesados[2], raza)
                    return warlock
                }
                "T" -> {
                    val titan = Titan(datosPersonajeProcesados[1], datosPersonajeProcesados[2], raza)
                    return titan
                }
                else -> {
                    val hunter = Hunter(datosPersonajeProcesados[1], datosPersonajeProcesados[2], raza)
                    return hunter
                }
            }
        }
        private fun obtenerRaza(datosPersonajeProcesados: List<String>):Razas{
            return when(datosPersonajeProcesados[3]){
                "Human" -> Razas.HUMAN
                "Awoken" -> Razas.AWOKEN
                else -> Razas.EXO
            }
        }
    }

    fun armaduraEquipadaToString():String{
        return "CURRENT ARMOR SET \n${armaduraEquipada[0].nombre} helmet \n${armaduraEquipada[1].nombre} gloves\n ${armaduraEquipada[2].nombre} robes\n ${armaduraEquipada[3].nombre} boots \n${armaduraEquipada[4].nombre} class item"
    }
    fun armaEquipadaToString():String{
        return "CURRENT WEAPONS SET \n${armaEquipada[0].mostrarInformacion(armaEquipada[0])} \n${armaEquipada[1].mostrarInformacion(armaEquipada[1])} \n ${armaEquipada[2].mostrarInformacion(armaEquipada[2])} "
    }



}