package org.practicatrim2.items

import juego.GameModes
import java.io.File

abstract class Item() {
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
            val elemento = obtenerElemento(itemAprocesar)
            val weapon   = Arma(itemAprocesar[1], itemAprocesar[2], itemAprocesar[3], elemento)
            return weapon
        }
        else{
            val armor = Armadura(itemAprocesar[1], itemAprocesar[2], itemAprocesar[3])
            return armor
        }
    }

    private fun obtenerElemento(itemAprocesar: List<String>): Elementos {
        when (itemAprocesar[4]) {
            "Void" -> return Elementos.VOID
            "Solar" -> return Elementos.SOLAR
            "Arc" -> return Elementos.ARC
            "Strand" -> return Elementos.STRAND
            "Stasis" -> return Elementos.STASIS
        }
        return Elementos.KINETIC
    }
}