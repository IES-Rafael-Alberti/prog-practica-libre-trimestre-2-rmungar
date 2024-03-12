package org.practicatrim2.juego

interface Comprobable<T> {
    fun comprobarAccion(accion: T):Int

    fun comprobarDatosPrevios():Boolean

    fun comprobarDatosArmaduras():Boolean
    fun comprobarDatosPersonaje():Boolean

    fun comprobarDatosArmas():Boolean

    fun comprobarSeleccionModoJuego():String
}