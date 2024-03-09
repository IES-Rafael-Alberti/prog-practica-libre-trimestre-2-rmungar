package org.practicatrim2.items

import com.github.ajalt.mordant.rendering.TextColors
import com.github.ajalt.mordant.rendering.TextColors.*
import com.github.ajalt.mordant.rendering.TextStyle
import org.practicatrim2.personajes.Personaje


interface GestorItem{       //DIP --> SOLID
    fun procesarItem(item: String): Item
    fun clasificarItem(itemAprocesar: List<String>): Item
    fun obtenerElemento(itemAprocesar: List<String>): Pair<Elementos,TextStyle>
    fun obtenerRareza(itemAprocesar: List<String>): TextStyle
}

open class Item:GestorItem, Equipable<MutableList<Item>>, Sustituible<Item, MutableList<Item>>, Guardable<Item>{
    open var nombre: String = ""
    open var parte: String = ""
    open var rareza: String = ""
    open var rarity: TextStyle = TextColors.rgb("#9500ff")
    open var arquetipo:String = ""
    open var tipoArma: String = ""
    open var elemento: Elementos = Elementos.KINETIC
    open var colorElemento:TextStyle = brightWhite


    override fun procesarItem(item: String): Item {
        val itemAprocesar = item.split(" ; ")
        val itemProcesado = clasificarItem(itemAprocesar)
        return itemProcesado
    }

    override fun clasificarItem(itemAprocesar: List<String>) : Item {
        if (itemAprocesar[0] == "W") {
            val elemento = obtenerElemento(itemAprocesar).first // Elemento del arma
            val colorElemento = obtenerElemento(itemAprocesar).second
            val rarity = obtenerRareza(itemAprocesar) // Patrón de color para la terminal de acuerdo a la rareza del arma
            val weapon = Arma(itemAprocesar[1], itemAprocesar[2], itemAprocesar[3], elemento, itemAprocesar[5], rarity, colorElemento)
            return weapon
        }
        else{
            val rarity = obtenerRareza(itemAprocesar) // Patrón de color para la terminal de acuerdo a la rareza del arma
            val armor = Armadura(itemAprocesar[1], itemAprocesar[2], itemAprocesar[3], rarity)
            return armor
        }
    }

    override fun obtenerElemento(itemAprocesar: List<String>): Pair<Elementos,TextStyle> {
        when (itemAprocesar[4]) {
            "Void" -> return Pair(Elementos.VOID, TextColors.rgb("#9500ff"))
            "Solar" -> return Pair(Elementos.SOLAR, brightRed)
            "Arc" -> return Pair(Elementos.ARC, brightCyan)
            "Strand" -> return Pair(Elementos.STRAND, green)
            "Stasis" -> return Pair(Elementos.STASIS, blue)
        }
        return Pair(Elementos.KINETIC, brightWhite)
    }
    override fun obtenerRareza(itemAprocesar: List<String>):TextStyle{
        when(itemAprocesar[itemAprocesar.size - 1]){
            "Exotic" -> return TextColors.rgb("#bf8506")
        }
        return TextColors.rgb("#9500ff")
    }

    override fun equipable(t: MutableList<Item>): Boolean {
        return false
    }

    override fun guardar(a: Item) {

    }

    override fun preguntarParaEquipar(item: Item): Boolean {
        return false
    }

    override fun equipar(item: Item, personaje: Personaje) {

    }

    override fun sustituir(t: Item, e: MutableList<Item>) {

    }
}
