package org.practicatrim2.juego

import org.practicatrim2.personajes.Personaje

interface Jugable {
    fun jugarGambito(personaje: Personaje, featured:Boolean)

    fun jugarOcaso(personaje: Personaje, featured:Boolean)

    fun jugarTrials(personaje: Personaje, featured:Boolean)

    fun jugarRyD(personaje: Personaje, featured:Boolean)
}