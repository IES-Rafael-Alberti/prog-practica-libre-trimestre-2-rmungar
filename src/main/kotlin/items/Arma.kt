package org.practicatrim2.items

import com.github.ajalt.mordant.rendering.TextStyle

/**
 * Clase que representa un arma en el juego.
 *
 * @property nombre El nombre del arma.
 * @property arquetipo El arquetipo del arma.
 * @property tipoArma El tipo de arma.
 * @property elemento El elemento asociado al arma.
 * @property rareza La rareza del arma.
 * @property rarity El estilo de texto asociado a la rareza del arma.
 * @property colorElemento El estilo de texto asociado al elemento del arma.
 */
data class Arma( override var nombre:String, override var arquetipo:String, override var tipoArma:String, override var elemento: Elementos, override var rareza :String, override var rarity:TextStyle, override var colorElemento:TextStyle): Item(){
    override fun toString(): String {
        return "${rarity(nombre)} -- ${colorElemento(elemento.desc)} -- ${rarity(rareza)} $tipoArma \n -------------- $arquetipo"
    }
}