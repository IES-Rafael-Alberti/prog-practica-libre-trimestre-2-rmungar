package tests

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.practicatrim2.juego.GestionJuego
import kotlin.random.Random

class GestionJuegoTest {

    /**
     * Pequeño test que comprueba que la funcion comprobarAccion devuelve el numero en formato de string (En caso de que este se encuentre en el rango)
     */
    @Test
    fun comprobarAccion() {
        val accion = Random.nextInt(1,4)
        assertEquals(accion, GestionJuego.comprobarAccion(accion.toString()))
    }
}