package org.practicatrim2.modosDeJuego

import com.github.ajalt.mordant.rendering.TextColors
import com.github.ajalt.mordant.rendering.TextStyle

/**
 * Clase enumerada que contiene los 4 tipos distintos de modos de juego y algo de información de ellos
 * @property desc -> Nombre del modo de juego en cuestion
 * @property color -> Color de la libreria de mordant para emplear textos de colo personalizado
 */
enum class GameModes(open val desc:String, val color:TextStyle) {
    GAMBIT("Gambit", TextColors.rgb("#4c964b")),NIGHTFALL("Nightfall", TextColors.rgb("#186bc9")),RAIDS_DUNGEONS("Raids_Dungeons", TextColors.red),TRIALS("Trials", TextColors.rgb("#bf9821"))
}