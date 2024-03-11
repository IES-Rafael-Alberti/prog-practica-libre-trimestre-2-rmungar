package org.practicatrim2.items

import com.github.ajalt.mordant.rendering.TextStyle


data class Arma( override var nombre:String, override var arquetipo:String, override var tipoArma:String, override var elemento: Elementos, override var rareza :String, override var rarity:TextStyle, override var colorElemento:TextStyle): Item(){
    override fun toString(): String {
        return "${rarity(nombre)} -- ${colorElemento(elemento.desc)} -- ${rarity(rareza)} $tipoArma \n -------------- $arquetipo"
    }
}