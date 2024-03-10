package org.practicatrim2.juego

import com.github.ajalt.mordant.rendering.TextColors
import com.github.ajalt.mordant.terminal.Terminal
import org.practicatrim2.animaciones.AnimationManager
import org.practicatrim2.items.Arma
import org.practicatrim2.items.Armadura
import org.practicatrim2.items.Comprobable
import org.practicatrim2.items.Item
import org.practicatrim2.personajes.Hunter
import org.practicatrim2.personajes.Personaje
import org.practicatrim2.personajes.Titan
import org.practicatrim2.personajes.Warlock
import java.io.File
import kotlin.random.Random

object GestionJuego :Juego(), Comprobable<String> {
    private val terminal = Terminal() //Variable apra el uso de Mordant

    private val color_Blanco = TextColors.brightWhite // Variable del color blanco
    private val color_Rojo = TextColors.brightRed // Variable del color rojo
    private val color_Amarillo = TextColors.brightYellow // Variable del color amarillo

    private val FicheroArmaduras = File("$workingDirectory/Datos_Guardado/Armor_Set.txt") // Fichero donde se guardan las armaduras de una partida previa
    private val FicheroArmas = File("$workingDirectory/Datos_Guardado/Weapons_Set.txt") // Fichero donde se guardan las armas de una partida previa
    private val FicheroVault = File("$workingDirectory/Datos_Guardado/Vault.txt") // Fichero donde se guardan las armas y armaduras NO EQUIPADAS de una partida previa
    private val FicheroPersonaje = File("$workingDirectory/Datos_Guardado/Character.txt") // Fichero donde se guardan los datos del personaje de una partida previa


    private fun separador(){
        repeat(35){
            println()
        }
    }

    fun comenzarJuego(){
        terminal.println((color_Blanco)("                                                                          WELCOME TO DESTINY - LITE"))
        menuInicio()
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
    private fun menuInicio(){
        println()
        terminal.println(color_Amarillo("                                                                                    1 - New Game"))
        terminal.println(colorVerde("                                                                                    2 - Continue Game"))
        terminal.println(color_Rojo("                                                                                    3 - Exit Game"))
    }

    private fun ejecutarAccionInicial(): Boolean{
        println()
        terminal.print(color_Blanco("                                                                        What are you going to do today? :"))
        var accion = readln()
        while(comprobarAccion(accion) !in 1..3){
            terminal.print(color_Blanco("                                                                         What are you going to do today? :"))
            accion = readln()
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
                terminal.warning("                                                                      Please, answer the requested prompt correctly")
                return 4
            }
        }
    }

    override fun comprobarSeleccionModoJuego(): String {
        while (true) {

            var entrada: String = readln().lowercase()
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
                else -> terminal.warning("                                                                      Please, answer the requested prompt correctly")
            }
        }
    }


    fun jugar(personaje: Personaje){
        while (true){
            var featured = false
            val focusedMode = Random.nextInt(1,4)
            mostrarMenuModosJuego()
            val modo = comprobarSeleccionModoJuego()
            when (modo){
                "Gambit" -> {
                    if (focusedMode == 1) featured = true
                    jugarGambito(personaje, featured)
                }
                "Nightfall" -> {
                    if (focusedMode == 2) featured = true
                    jugarOcaso(personaje, featured)
                }
                "Raids_Dungeons" -> {
                    if (focusedMode == 3) featured = true
                    jugarRyD(personaje, featured)
                }
                "Trials" -> {
                    if (focusedMode == 4) featured = true
                    jugarTrials(personaje, featured)
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
        if (!datosArmaduras && !datosArmas) {
            while (true) {
                terminal.print(color_Rojo("                                                         It seems you have saved data, would you like to overwrite it? (y / n): ")) // Entrada del usuario que indica lo que desea hacer
                val decision = readln().lowercase()
                when (decision) {
                    "y", "yes" -> {
                        separador()
                        terminal.println((color_Rojo)("                                                                                  OVERWRITING DATA"))
                        val animacion = AnimationManager.animacionCargando()
                        println()
                        terminal.println((colorVerde)("                                                                                  DATA OVERWRITTEN"))
                        generarNuevoJuego()
                        return true
                    }

                    "n", "no" -> {
                        separador()
                        terminal.println((colorVerde)("                                                                                   LOADING GAME...."))
                        val animacion = AnimationManager.animacionCargando()
                        cargarDatos()
                        return true
                    }
                    else -> {
                        terminal.warning("                                                                      Please, answer the requested prompt correctly")
                    }
                }
            }
        }
        else {
            terminal.print(color_Rojo("                                                         It seems there isn't saved data, would you like to start a new game? (y / n): "))
            val decision = readln().lowercase()// Entrada del usuario que indica lo que desea hacer
            when (decision) {
                "y", "yes" -> {
                    generarNuevoJuego()
                    terminal.println((colorVerde)("                                                                                   CREATING SAVE FILES...."))
                    val animacion = AnimationManager.animacionCargando()
                    return false
                }

                "n", "no" -> {
                    return false
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

    private fun cargarDatos():Personaje {
        val datosPersonaje = FicheroPersonaje.useLines { it.toList() }[0]
        val datosArmaduras = FicheroArmaduras.useLines { it.toList() }
        val datosArmas = FicheroArmas.useLines { it.toList() }
        val personaje = Personaje.generarPersonaje(datosPersonaje)


        datosArmaduras.forEach {
            val armaduraItem = Item().procesarItem(it)
            val armadura = Armadura(armaduraItem.nombre, armaduraItem.parte, armaduraItem.rareza, armaduraItem.rarity)
            personaje.armaduraEquipada.add(armadura)
        }
        datosArmas.forEach {
            val armaItem = Item().procesarItem(it)
            val arma = Arma(armaItem.nombre, armaItem.arquetipo, armaItem.tipoArma, armaItem.elemento, armaItem.rareza, armaItem.rarity, armaItem.colorElemento)
            personaje.armaEquipada.add(arma)
        }
        return personaje
    }

    private fun generarNuevoJuego(){
        FicheroArmaduras.writeText("")
        FicheroArmas.writeText("")
        FicheroVault.writeText("")
        FicheroPersonaje.writeText("")
    }

    private fun guardarDatos(personaje: Personaje){
        FicheroArmas.writeText("")
        FicheroArmaduras.writeText("")
        FicheroPersonaje.writeText("")
        personaje.armaEquipada.forEach {
            FicheroArmas.appendText("\nW ; ${it.nombre} ; ${it.arquetipo} ; ${it.tipoArma} ; ${it.elemento} ; ${it.rareza}")
        }
        personaje.armaduraEquipada.forEach {
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