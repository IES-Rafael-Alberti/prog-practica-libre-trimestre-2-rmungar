package org.practicatrim2.modosDeJuego

import com.github.ajalt.mordant.rendering.TextColors
import com.github.ajalt.mordant.rendering.TextStyle
import com.github.ajalt.mordant.rendering.TextStyles

enum class GameModes(open val desc:String, val lootPool:String, val color:TextStyle) {
    GAMBIT("Gambit", "Gambit.txt", TextColors.rgb("#4c964b")),NIGHTFALL("Nightfall", "Nightfall.txt", TextColors.rgb("#186bc9")),RAIDS_DUNGEONS("Raids_Dungeons","Raids_Dungeons.txt", TextColors.red),TRIALS("Trials", "Trials.txt", TextColors.rgb("#bf9821"))
}