package org.practicatrim2.juego

import com.github.ajalt.mordant.rendering.TextColors
import com.github.ajalt.mordant.terminal.Terminal
import org.practicatrim2.animaciones.AnimationManager
import org.practicatrim2.items.Arma
import org.practicatrim2.items.Armadura
import org.practicatrim2.items.Item
import org.practicatrim2.personajes.Hunter
import org.practicatrim2.personajes.Personaje
import org.practicatrim2.personajes.Titan
import org.practicatrim2.personajes.Warlock
import java.io.File
import kotlin.random.Random

object GestionJuego :Juego(), Comprobable<String> {


    private val FicheroArmaduras = File("$workingDirectory/Datos_Guardado/Armor_Set.txt") // Fichero donde se guardan las armaduras de una partida previa
    private val FicheroArmas = File("$workingDirectory/Datos_Guardado/Weapons_Set.txt") // Fichero donde se guardan las armas de una partida previa
    private val FicheroPersonaje = File("$workingDirectory/Datos_Guardado/Character.txt") // Fichero donde se guardan los datos del personaje de una partida previa



    fun comenzarJuego(){
        GestorConsola.bienvenida()
        GestorConsola.mostrarMenuInicio()
        val datosExistentes = ejecutarAccionInicial()
        if (datosExistentes) {
            val personaje = cargarDatos()
            while (true){
                jugar(personaje)
            }
        }
        else {
            val personaje = generarPersonaje()
            while (true){
                jugar(personaje)
            }
        }

    }

    private fun ejecutarAccionInicial(): Boolean{
        println()
        GestorConsola.mostrarEntradaDeAccionInicial()
        var accion = GestorEntrada.pedirEntradaInicial()
        while(comprobarAccion(accion) !in 1..3){
            GestorConsola.mostrarEntradaDeAccionInicial()
            accion = GestorEntrada.pedirEntradaInicial()
        }
        when(accion){
            "1" -> {
                comprobarDatosPrevios()
                return false
            }
            "2" -> {
                if(comprobarDatosPrevios()){
                    cargarDatos()
                    return true
                }
                else { generarNuevoJuego()
                    return false
                }
            }
            else -> {
                acabarJuego()
                return false
            }
        }
    }

    override fun comprobarAccion(accion:String):Int {
        when(accion){
            "1" -> {
                return 1
            }
            "2" -> {
                return 2
            }
            "3" -> {
                return 3
            }
            else -> {
                GestorConsola.mostrarEntradaIncorrecta()
                return 4
            }
        }
    }

    override fun comprobarSeleccionModoJuego(): String {
        while (true) {
            GestorConsola.marcadorEntradaTexto()
            var entrada: String = GestorEntrada.pedirEntradaDeModoDeJuego()
            when(entrada){
                "1","gambit","gambit prime" -> {
                    entrada = "Gambit"
                    return entrada
                }
                "2","nightfall","grandmaster nightfall" -> {
                    entrada = "Nightfall"
                    return entrada
                }
                "3", "trials","trials of osiris" -> {
                    entrada = "Trials"
                    return entrada
                }
                "4", "raids", "dungeons", "raids_dungeons","raids and dungeons" -> {
                    entrada = "Raids_Dungeons"
                    return entrada
                }
                "s", "save" -> {
                    entrada = "Save"
                    return entrada
                }
                "e", "exit" -> {
                    entrada = "Exit"
                    return entrada
                }
                else -> GestorConsola.mostrarEntradaIncorrecta()
            }
        }
    }


    fun jugar(personaje: Personaje){
        while (true) {
            GestorConsola.mostrarInterfazJuego()
            val modoPrincipal = selectorSeccionJuego()
            if (modoPrincipal == 1) {
                while (true) {
                    GestorConsola.mostrarInterfazPersonaje(personaje)
                }
            }
            else if (modoPrincipal == 2) {
                jugarModo2(personaje)
            }
            else {
                break
            }
        }
    }


    fun jugarModo2(personaje: Personaje){
        while (true) {
            var featured = false
            val focusedMode = Random.nextInt(1, 4)
            GestorConsola.mostrarMenuModosJuego()
            val modo = comprobarSeleccionModoJuego()
            when (modo) {
                "Gambit" -> {
                    if (focusedMode == 1) featured = true
                    GestionJuego.jugarGambito(personaje, featured)
                }

                "Nightfall" -> {
                    if (focusedMode == 2) featured = true
                    GestionJuego.jugarOcaso(personaje, featured)
                }

                "Raids_Dungeons" -> {
                    if (focusedMode == 3) featured = true
                    GestionJuego.jugarRyD(personaje, featured)
                }

                "Trials" -> {
                    if (focusedMode == 4) featured = true
                    GestionJuego.jugarTrials(personaje, featured)
                }

                "Save" -> {
                    guardarDatos(personaje)
                }

                "Exit" -> break
            }
        }
    }


    override fun comprobarDatosPrevios():Boolean{
        val datosArmaduras = comprobarDatosArmaduras()
        val datosArmas = comprobarDatosArmas()
        val datosPersonaje = comprobarDatosPersonaje()
        if (!datosArmaduras && !datosArmas && !datosPersonaje) {
            while (true) {

                val decision = readln().lowercase()
                when (decision) {
                    "y", "yes" -> {
                        GestorConsola.separador()
                        GestorConsola.sobreescribiendoDatos()
                        GestorConsola.datosSobreescritos()
                        generarNuevoJuego()
                        return true
                    }

                    "n", "no" -> {
                        GestorConsola.separador()
                        GestorConsola.cargandoDatos()
                        cargarDatos()
                        return true
                    }
                    else -> {
                       GestorConsola.mostrarEntradaIncorrecta()
                    }
                }
            }
        }
        else {
            GestorConsola.mostrarNoExistenciaDatosPrevios()
            val decision = readln().lowercase()// Entrada del usuario que indica lo que desea hacer
            when (decision) {
                "y", "yes" -> {
                    generarNuevoJuego()
                    GestorConsola.creandoDatos()
                    GestorConsola.cargandoDatos()
                    return false
                }

                "n", "no" -> {
                   acabarJuego()
                }
            }
        }
        return false
    }

    override fun comprobarDatosArmaduras():Boolean{
        val datosArmadura = FicheroArmaduras.useLines { it.toList() }
        return datosArmadura.isEmpty()
    }
    override fun comprobarDatosArmas(): Boolean{
        val datosArmas = FicheroArmas.useLines { it.toList() }
        return datosArmas.isEmpty()
    }

    override fun comprobarDatosPersonaje(): Boolean {
        val datosPersonaje = FicheroPersonaje.useLines { it.toList() }
        return datosPersonaje.isEmpty()
    }

    private fun cargarDatos():Personaje {
        val datosPersonaje = FicheroPersonaje.useLines { it.toList() }[0]
        val datosArmaduras = FicheroArmaduras.useLines { it.toList() }
        val datosArmas = FicheroArmas.useLines { it.toList() }
        val personaje = Personaje.generarPersonaje(datosPersonaje)


        datosArmaduras.forEach {
            val armaduraItem = Item.procesarItem(it)
            val armadura = Armadura(armaduraItem.nombre, armaduraItem.parte, armaduraItem.rareza, armaduraItem.rarity)
            personaje.armaduraEquipada.add(armadura)
        }
        datosArmas.forEach {
            val armaItem = Item.procesarItem(it)
            val arma = Arma(armaItem.nombre, armaItem.arquetipo, armaItem.tipoArma, armaItem.elemento, armaItem.rareza, armaItem.rarity, armaItem.colorElemento)
            personaje.armaEquipada.add(arma)
        }
        return personaje
    }

    private fun generarNuevoJuego(){
        FicheroArmaduras.delete()
        FicheroArmas.delete()
        FicheroPersonaje.delete()
        FicheroArmaduras.createNewFile()
        FicheroPersonaje.createNewFile()
        FicheroArmas.createNewFile()
    }

    private fun guardarDatos(personaje: Personaje){
        personaje.armaEquipada.forEach {
            if (it == personaje.armaEquipada[0]) FicheroArmas.writeText("W ; ${it.nombre} ; ${it.arquetipo} ; ${it.tipoArma} ; ${it.elemento} ; ${it.rareza}")
            FicheroArmas.appendText("\nW ; ${it.nombre} ; ${it.arquetipo} ; ${it.tipoArma} ; ${it.elemento} ; ${it.rareza}")
        }
        personaje.armaduraEquipada.forEach {
            if (it == personaje.armaduraEquipada[0]) FicheroArmas.writeText("A ; ${it.nombre} ; ${it.arquetipo} ; ${it.tipoArma} ; ${it.elemento} ; ${it.rareza}")
            FicheroArmaduras.appendText("\nA ; ${it.nombre} ; ${it.parte} ; ${it.rareza}")
        }

        when(personaje){
            is Titan -> FicheroPersonaje.writeText("T ; ${personaje.nombre} ; ${personaje.genero} ; ${personaje.raza}")
            is Warlock -> FicheroPersonaje.writeText("W ; ${personaje.nombre} ; ${personaje.genero} ; ${personaje.raza}")
            is Hunter -> FicheroPersonaje.writeText("H ; ${personaje.nombre} ; ${personaje.genero} ; ${personaje.raza}")
        }

    }
    private fun acabarJuego():Boolean{
        return false
    }
}