package org.practicatrim2.items

import com.github.ajalt.mordant.rendering.TextColors
import com.github.ajalt.mordant.terminal.Terminal

class Armadura(val nombre:String, val parte:String, val rareza:String, private val rarity:TextColors) : Equipable<Armadura>, Item(){

    override fun equipable(): Boolean {
        TODO("Not yet implemented")
    }

    override fun mostrarInformacion() {
        val t = Terminal()
        t.println("${rarity(nombre)} -- ${rarity(rareza)} $parte")
    }
}