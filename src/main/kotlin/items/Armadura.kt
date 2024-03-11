package org.practicatrim2.items

import com.github.ajalt.mordant.rendering.TextColors
import com.github.ajalt.mordant.rendering.TextStyle

class Armadura(override var nombre:String, override var parte:String, override var rareza:String, override var rarity:TextStyle) : Item(){

    override fun toString(): String {
        return "${rarity(nombre)} -- ${rarity(rareza)} $parte"
    }
}