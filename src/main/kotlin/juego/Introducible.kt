package org.practicatrim2.juego


/**
 * Interfaz que sienta las bases para aquellas entradas que se le solicitan al usuario
 */
interface Introducible {
//      MENU INICIAL    ------------------------------------------------------------------------------------------------
    fun pedirEntradaInicial(): String

//      DATOS   --------------------------------------------------------------------------------------------------------
    fun pedirDecisionDatos():String

//      SELECCION DE MODO DE JUEGO  ------------------------------------------------------------------------------------

    fun pedirEntradaDeModoDeJuego(): String

//      SELECCION DE SECCION DE JUEGO   --------------------------------------------------------------------------------

    fun pedirEntradaDeSeccionDeJuego(): String

//      RELACIONADO CON EL PERSONAJE    --------------------------------------------------------------------------------

    fun pedirNombreDePersonaje(): String

    fun pedirGeneroDePersonaje(): String

    fun pedirClaseDePersonaje(): String

    fun pedirRazaDePersoanje(): String

//      VAULT   --------------------------------------------------------------------------------------------------------

    fun pedirOpcionAccederVault():String

    fun pedirOpcionExtraerVault():String

    fun pedirEnterParaSalir():String
}