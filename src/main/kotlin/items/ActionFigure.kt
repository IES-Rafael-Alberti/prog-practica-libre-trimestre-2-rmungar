package org.practicatrim2.items

import com.github.ajalt.mordant.rendering.TextColors
import com.github.ajalt.mordant.rendering.TextStyle

/**
 * Clase que representa una figura de acción en el juego.
 *
 * @property nombre El nombre de la figura de acción.
 * @property ability La habilidad especial de la figura de acción.
 * @property tipoObjeto El tipo de objeto de la figura de acción.
 * @property damageType El tipo de daño que inflige la figura de acción.
 * @property rareza La rareza de la figura de acción.
 * @property rarity El estilo de texto asociado a la rareza de la figura de acción.
 */
data class ActionFigure(val nombre:String, val ability: String, val tipoObjeto:String, val damageType:String, val rareza:String, val rarity:TextStyle = TextColors.rgb("#da86e3")){
    override fun toString(): String {
        return "YOU FOUND ${rarity(nombre)} ${rarity(rareza)} ${rarity(tipoObjeto)}!! WHOSE SPECIAL ABILITY IS: ${rarity(ability)} AND DEALS ${rarity(damageType)} DAMAGE!!"
    }
}
