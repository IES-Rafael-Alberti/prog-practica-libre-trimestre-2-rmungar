package org.practicatrim2.animaciones

import com.github.ajalt.mordant.animation.Animation
import com.github.ajalt.mordant.animation.textAnimation
import com.github.ajalt.mordant.rendering.TextColors
import com.github.ajalt.mordant.terminal.Terminal

/**
 * Objeto encargado de gestionar las animaciones en el juego.
 */
object AnimationManager {
    private val totalFrames = 120
    private val terminal = Terminal()

    /**
     * Crea y devuelve una animación de carga.
     *
     * @return Una animación que muestra una carga en progreso.
     */
    fun animacionCargando(): Animation<Int> {
        val a = terminal.textAnimation<Int> { frame ->
            (1..196).joinToString("") {
                val hue = (frame + it) * 3 % 360
                TextColors.hsv(hue, 1, 1)("·")
            }
        }

        repeat(totalFrames) {
            a.update(it)
            Thread.sleep(25)
        }

        return a
    }
}