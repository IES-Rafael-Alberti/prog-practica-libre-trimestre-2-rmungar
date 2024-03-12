package org.practicatrim2.juego

import org.practicatrim2.capitalizar

object GestorEntrada: Introducible {

//      MENU INICIAL    ------------------------------------------------------------------------------------------------
    override fun pedirEntradaInicial():String {
        val accion = readln()
        return accion
    }

//      SELECCION DE MODO DE JUEGO  ------------------------------------------------------------------------------------

    override fun pedirEntradaDeModoDeJuego():String {
        val entrada: String = readln().lowercase()
        return entrada
    }

//      SELECCION DE SECCION DE JUEGO   --------------------------------------------------------------------------------
    override fun pedirEntradaDeSeccionDeJuego(): String {
        val seleccion = readln().lowercase()
        return seleccion
    }

//      RELACCIONADO CON EL PERSONAJE   --------------------------------------------------------------------------------

    override fun pedirClaseDePersonaje(): String {
        val claseAcrear = readln().uppercase()
        return claseAcrear
    }

    override fun pedirNombreDePersonaje(): String {
        val nombre = readln() //Cadena para el nombre del personaje
        return nombre
    }

    override fun pedirGeneroDePersonaje(): String {
        val genero = readln().capitalizar()
        return genero
    }

    override fun pedirRazaDePersoanje(): String {
        val razaAcrear = readln().uppercase() //Cadena ingresada por el usuario a comprobar
        return razaAcrear
    }
}