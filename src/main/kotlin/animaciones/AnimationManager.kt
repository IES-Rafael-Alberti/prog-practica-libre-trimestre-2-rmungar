package org.practicatrim2.animaciones

import com.github.ajalt.mordant.animation.Animation
import com.github.ajalt.mordant.animation.textAnimation
import com.github.ajalt.mordant.rendering.TextColors
import com.github.ajalt.mordant.terminal.Terminal
import kotlin.concurrent.thread

object AnimationManager {
    private val totalFrames = 120
    private val terminal = Terminal()

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



    fun nave() {
        val dots = "·"
        val spaceShip = "🚀"
        val terminalWidth = 196

        thread {
            var dotsCount = 0

            // Animation for dots
            repeat(1) {
                // Move cursor back to the spaceship position

                for (i in 0 until dotsCount) {
                    print(dots)

                }
                Thread.sleep(50)
                dotsCount = (dotsCount + 1) % terminalWidth
                print(spaceShip)
            }
        }
    }
}