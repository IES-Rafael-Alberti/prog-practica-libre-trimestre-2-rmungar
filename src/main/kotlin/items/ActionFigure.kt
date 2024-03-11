package org.practicatrim2.items

import com.github.ajalt.mordant.rendering.TextColors
import com.github.ajalt.mordant.rendering.TextStyle

data class ActionFigure(val nombre:String, val ability: String, val tipoObjeto:String, val damageType:String, val rareza:String, val rarity:TextStyle = TextColors.rgb("#da86e3")){
    override fun toString(): String {
        return "YOU FOUND ${rarity(nombre)} ${rarity(rareza)} ${rarity(tipoObjeto)}!! WHOSE SPECIAL ABILITY IS: ${rarity(ability)} AND DEALS ${rarity(damageType)} DAMAGE!!"
    }
}
