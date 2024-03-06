package org.practicatrim2.items

import com.github.ajalt.mordant.rendering.TextColors
import com.github.ajalt.mordant.terminal.Terminal
import com.github.ajalt.colormath.Color


class Arma(val nombre:String, val arquetipo:String, val tipoArma:String, val elemento: Elementos, val rareza :String,private var rarity:TextColors, val colorElemento:TextColors): Equipable<Arma>, Item(){

    override fun equipable(): Boolean {
        TODO("Not yet implemented")
    }

    override fun mostrarInformacion() {
        val t = Terminal()
        t.println("${rarity(nombre)} -- ${colorElemento(elemento.desc)} -- ${rareza} ${tipoArma} \n -------------- ${arquetipo}")
    }
}