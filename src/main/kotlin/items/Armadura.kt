package org.practicatrim2.items

import com.github.ajalt.mordant.rendering.TextStyle

/**
 * Clase que representa una armadura en el juego.
 *
 * @property nombre El nombre de la armadura.
 * @property parte La parte del cuerpo a la que pertenece la armadura.
 * @property rareza La rareza de la armadura.
 * @property rarity El estilo de texto asociado a la rareza de la armadura.
 */
class Armadura(override var nombre:String, override var parte:String, override var rareza:String, override var rarity:TextStyle) : Item(){

    override fun toString(): String {
        return "${rarity(nombre)} -- ${rarity(rareza)} $parte"
    }
}