package org.practicatrim2.items

import org.practicatrim2.personajes.Personaje

interface Equipable<T> {
    fun equipable(t: T):Boolean
}

interface Sustituible<T,E> {
    fun sustituir(t:T, e:E)
}

interface Guardable<A>{
    fun guardar(a:A)
}

interface Comprobable<T> {
    fun comprobarAccion(t:T):Boolean
    fun comprobarDatosPrevios()
    fun comprobarDatosArmaduras():Boolean
    fun comprobarDatosArmas():Boolean
    fun comprobarSeleccionModoJuego(personaje: Personaje)
}

interface Mostrable{
    fun mostrarRazaPersonaje()
    fun mostrarClasePersonaje()
    fun mostrarInformacionClases()
    fun mostrarInformacionRazas()
    fun mostrarMenuModosJuego()

}

interface Informable{
    fun mostrarInformacion()
}

interface Jugable{
    fun jugarGambito()
    fun jugarOcaso()
    fun jugarTrials()
    fun jugarRyD()

}