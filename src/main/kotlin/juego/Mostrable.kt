package org.practicatrim2.juego

import org.practicatrim2.items.ActionFigure
import org.practicatrim2.items.Item
import org.practicatrim2.personajes.Personaje


/**
 * Interfaz que establece las bases de las funciones que se emplean para mostrar datos por pantalla
 */
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

    fun mostrarExcesoArmas()

    fun mostrarExcesoArmaduras()

    fun mostrarArmaEquipada()

    fun mostrarArmaduraEquipada()

    fun pedirSlot()

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

    fun preguntarParaEquiparArma(item: Item)

    fun preguntarParaEquiparArmadura(item: Item)

    fun preguntarParaCambiarArma()

    fun preguntarParaCambiarArmadura()

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

    fun mostrarObjetoGuardado(item: Item)

    fun preguntarParaGuardar(item: Item)

    fun mostrarObjetoEliminado()
}