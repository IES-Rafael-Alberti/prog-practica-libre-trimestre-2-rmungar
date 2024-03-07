package org.practicatrim2.items

import com.github.ajalt.mordant.rendering.TextColors
import com.github.ajalt.mordant.rendering.TextStyle
import com.github.ajalt.mordant.terminal.Terminal


class Arma(val nombre:String, val arquetipo:String, val tipoArma:String, val elemento: Elementos, val rareza :String,private var rarity:TextStyle, val colorElemento:TextStyle): Equipable<List<Arma>>, Item(){

    override fun equipable(armaEquipada: List<Arma>): Boolean {
        val t = Terminal()
        var cont = 1
        if(armaEquipada.size == 3){
            println("You already have 3 weapons equipped, choose one to replace:")
            return false
        }
        else{
            t.println("${rarity(nombre)} has been equipped")
            return true
        }
    }

    override fun mostrarInformacion() {
        val t = Terminal()
        t.println("${rarity(nombre)} -- ${colorElemento(elemento.desc)} -- ${rareza} ${tipoArma} \n -------------- ${arquetipo}")
    }
}