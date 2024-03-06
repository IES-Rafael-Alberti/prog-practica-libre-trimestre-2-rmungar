package org.practicatrim2.items

class Arma(nombre:String, val arquetipo:String, val tipoArma:String, val elemento: Elementos): Equipable, Item(){
    override fun equipable(): Boolean {
        TODO("Not yet implemented")
    }
}