package org.practicatrim2.items

import com.github.ajalt.mordant.rendering.TextColors
import juego.GameModes
import java.io.File
import com.github.ajalt.mordant.terminal.Terminal
import com.github.ajalt.mordant.rendering.TextColors.*
import com.github.ajalt.mordant.rendering.TextStyle

open class Item {
    fun obtenerItem(modoDeJuego: GameModes): Item {
        val directorioActual = System.getProperty("user.dir")
        val itemObtenido: String = File("$directorioActual/Loot_Pool/${modoDeJuego.desc}.txt").useLines { it.toList() }.random()
        val itemProcesado = procesarItem(itemObtenido)
        return itemProcesado
    }

    fun procesarItem(item: String) : Item {
        val itemAprocesar = item.split(" ; ")
        val itemProcesado = clasificarItem(itemAprocesar)
        return itemProcesado
    }

    private fun clasificarItem(itemAprocesar: List<String>) : Item {
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

    private fun obtenerElemento(itemAprocesar: List<String>): Pair<Elementos,TextStyle> {
        when (itemAprocesar[4]) {
            "Void" -> return Pair(Elementos.VOID, TextColors.rgb("#9500ff"))
            "Solar" -> return Pair(Elementos.SOLAR, brightRed)
            "Arc" -> return Pair(Elementos.ARC, brightCyan)
            "Strand" -> return Pair(Elementos.STRAND, green)
            "Stasis" -> return Pair(Elementos.STASIS, blue)
        }
        return Pair(Elementos.KINETIC, brightWhite)
    }
    fun obtenerRareza(itemAprocesar: List<String>):TextStyle{
        when(itemAprocesar[itemAprocesar.size - 1]){
            "Exotic" -> return TextColors.rgb("#bf8506")
        }
        return TextColors.rgb("#9500ff")
    }
    open fun mostrarInformacion(){
        val t = Terminal()
        t.println(brightWhite("---NO DATA FOUND---"))
    }
}