package org.practicatrim2.juego

import org.practicatrim2.personajes.Personaje


/**
 * Interfaz que define los métodos necesarios para jugar diferentes modos de juego en el juego.
 * Cada método corresponde a un modo de juego específico, como Gambito, Ocaso, Trials y RyD (Raids y Dungeons).
 */
interface Jugable {
    fun jugarGambito(personaje: Personaje, featured:Boolean)

    fun jugarOcaso(personaje: Personaje, featured:Boolean)

    fun jugarTrials(personaje: Personaje, featured:Boolean)

    fun jugarRyD(personaje: Personaje, featured:Boolean)
}