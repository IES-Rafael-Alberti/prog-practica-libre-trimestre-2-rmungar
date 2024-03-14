package org.practicatrim2.juego


/**
 * Interfaz que sienta las bases para todas aquellas funciones que van a comprobar algo. En un caso en particular se usa el
 * tipo generico [T].
 */
interface Comprobable<T> {
    fun comprobarAccion(accion: T):Int

    fun comprobarDatosPrevios():Boolean

    fun comprobarDatosArmaduras():Boolean
    fun comprobarDatosPersonaje():Boolean

    fun comprobarDatosArmas():Boolean

    fun comprobarSeleccionModoJuego():String
}