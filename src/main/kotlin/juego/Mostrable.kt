package org.practicatrim2.juego

import org.practicatrim2.items.ActionFigure
import org.practicatrim2.personajes.Personaje

interface Mostrable {


//      LIMPIAR CONSOLA ------------------------------------------------------------------------------------------------

    fun separador()

//      DATOS DE JUEGO  ------------------------------------------------------------------------------------------------

    fun mostrarExistenciaDatosPrevios()

    fun mostrarNoExistenciaDatosPrevios()

    fun cargandoDatos()

    fun cargandoJuego()

    fun creandoDatos()

    fun sobreescribiendoDatos()

    fun datosSobreescritos()


//      MENUS   --------------------------------------------------------------------------------------------------------

    fun bienvenida()

    fun mostrarMenuInicio()

    fun mostrarMenuModosJuego()


//      RELACIONADO CON EL PERSONAJE    --------------------------------------------------------------------------------

    fun mostrarRazaPersonaje()

    fun mostrarClasePersonaje()

    fun mostrarInformacionClases()

    fun mostrarTextoSeleccionClase()

    fun mostrarInformacionRazas()

    fun mostrarTextoSeleccionRaza()

//      INTERFACES  ----------------------------------------------------------------------------------------------------

    fun mostrarInterfazJuego()

    fun mostrarInterfazPersonaje(personaje: Personaje)

//      RELACCIONADO CON LA  ENTRADA DEL USUARIO  ----------------------------------------------------------------------

    fun mostrarEntradaIncorrecta()

    fun marcadorEntradaTexto()

    fun mostrarEntradaDeNombre()

    fun mostrarEntradaDeGenero()

    fun mostrarEntradaDeAccionInicial()

//      EASTER EGGS ----------------------------------------------------------------------------------------------------

    fun mostrarEasterEgg(huevo: ActionFigure)

//      RELACIONADO CON LOS MODOS DE JUEGO  ----------------------------------------------------------------------------

    fun animacionGambito()

    fun animacionOcaso()

    fun animacionTrials()

    fun animacionRyD()

//      VAULT   --------------------------------------------------------------------------------------------------------
    fun mostrarEntradaDeId()

    fun mostrarVault()
}