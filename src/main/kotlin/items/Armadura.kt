package org.practicatrim2.items

import com.github.ajalt.mordant.rendering.TextStyle

data class Armadura(override var nombre:String, override var parte:String, override var rareza:String, override var rarity:TextStyle) : Informable, Item()