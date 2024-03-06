package org.practicatrim2.personajes
import org.practicatrim2.items.Arma
import org.practicatrim2.items.Armadura

abstract class Personaje(val nombre: String,val genero:String, val raza:Razas, val clase: Clases) {
    open val armaduraEquipada:List<Armadura> = emptyList()//Lista con el conjunto de 5 partes de armadura equipadas actualmente
    open val armaEquipada: List<Arma> = emptyList()//Lista con el conjunto de 3 armas equipadas actualmente

    open fun armaduraEquipadaToString():String{
        return armaduraEquipada.toString()
    }
    open fun armaEquipadaToString():String{
        return armaEquipada.toString()
    }
}