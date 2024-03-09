package org.practicatrim2.items

import org.practicatrim2.personajes.Personaje

interface Equipable<T> {
    fun equipable(itemsEquipados: MutableList<T>):Boolean

    fun equipar(item: T, personaje: Personaje)

    fun preguntarParaEquipar(item: T):Boolean
}

interface Sustituible<T, E> {
    fun sustituir(item: T, personaje: E)
}

interface Guardable<A>{
    fun guardar(item: A)
    fun preguntarParaGuardar(item: A)
}

interface Comprobable<T> {
    fun comprobarAccion(accion: T):Boolean

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
    fun mostrarInformacion(item: Item)
}

interface Jugable{
    fun jugarGambito(personaje: Personaje)

    fun jugarOcaso(personaje: Personaje)

    fun jugarTrials(personaje: Personaje)

    fun jugarRyD(personaje: Personaje)
}