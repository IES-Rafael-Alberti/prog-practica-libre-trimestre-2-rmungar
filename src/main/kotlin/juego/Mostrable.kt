package org.practicatrim2.juego

import com.github.ajalt.mordant.rendering.TextColors
import org.practicatrim2.items.ActionFigure
import org.practicatrim2.items.Item
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

    fun mostrarInfoPersonaje(personaje: Personaje)

    fun mostrarArmaduraPersonaje(personaje: Personaje)

    fun mostrarArmasPersonaje(personaje: Personaje)

    fun mostrarYaHay3Armas()

    fun mostrarYaHay5Armaduras()

//      INTERFACES  ----------------------------------------------------------------------------------------------------

    fun mostrarInterfazJuego()

    fun mostrarInterfazPersonaje(personaje: Personaje)

//      RELACCIONADO CON LA  ENTRADA DEL USUARIO  ----------------------------------------------------------------------

    fun mostrarEntradaIncorrecta()

    fun marcadorEntradaTexto()

    fun mostrarEntradaDeNombre()

    fun mostrarEntradaDeGenero()

    fun mostrarEntradaDeAccionInicial()

    fun mostrarOpcionAbrirVault()

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

    fun mostrarArmaGuardada(item:Item)
}