package org.practicatrim2.items

import org.practicatrim2.personajes.Personaje

interface Equipable<T> {
    fun equipable(itemsEquipados: MutableList<T>):Boolean

    fun equipar(item: T, personaje: Personaje)

    fun preguntarParaEquipar(item: T, personaje: Personaje):Boolean
}

interface Sustituible<T, E> {
    fun sustituir(item: T, personaje: E)
}

interface Guardable<A>{
    fun guardar(item: A)
    fun preguntarParaGuardar(item: A)
}

interface Comprobable<T> {
    fun comprobarAccion(accion: T):Int

    fun comprobarDatosPrevios():Boolean

    fun comprobarDatosArmaduras():Boolean
    fun comprobarDatosPersonaje():Boolean

    fun comprobarDatosArmas():Boolean

    fun comprobarSeleccionModoJuego():String
}

interface Mostrable{
    fun mostrarRazaPersonaje()

    fun mostrarClasePersonaje()

    fun mostrarInformacionClases()

    fun mostrarInformacionRazas()

    fun mostrarMenuModosJuego()

    fun mostrarEasterEgg(huevo:List<String>)

}

interface Informable{
    fun mostrarInformacion(item: Item)
}

interface Jugable{
    fun jugarGambito(personaje: Personaje, featured:Boolean)

    fun jugarOcaso(personaje: Personaje, featured:Boolean)

    fun jugarTrials(personaje: Personaje, featured:Boolean)

    fun jugarRyD(personaje: Personaje, featured:Boolean)
}