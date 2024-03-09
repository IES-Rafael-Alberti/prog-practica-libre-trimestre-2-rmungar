package org.practicatrim2.juego

import com.github.ajalt.mordant.rendering.TextColors
import com.github.ajalt.mordant.terminal.Terminal
import org.practicatrim2.animaciones.AnimationManager
import org.practicatrim2.items.Arma
import org.practicatrim2.items.Armadura
import org.practicatrim2.items.Comprobable
import org.practicatrim2.items.Item
import org.practicatrim2.personajes.Personaje
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
        repeat(15){
            println()
        }
    }

    fun comenzarJuego():Juego{
        TODO()
    }
    private fun menuInicio(){
        println()
        terminal.println(color_Amarillo("                                                                                    1 - New Game"))
        terminal.println(colorVerde("                                                                                    2 - Continue Game"))
        terminal.println(color_Rojo("                                                                                    3 - Exit Game"))
    }

    private fun ejecutarAccionInicial(){
        println()
        val persona = cargarDatos()
        terminal.print(color_Blanco("                                                                        What are you going to do today? :"))
        var accion = readln()
        while(comprobarAccion(accion,persona) in 1..3){
            terminal.print(color_Blanco("                                                                         What are you going to do today? :"))
            accion = readln()
        }
    }

    override fun comprobarAccion(accion:String,personaje: Personaje):Int {
        when(accion){
            "1" -> {
                comprobarDatosPrevios()
                return 1
            }
            "2" -> {
                cargarDatos()
                return 2
            }
            "3" -> {
                acabarJuego(personaje)
                return 3
            }
            else -> {
                terminal.warning("                                                                      Please, answer the requested prompt correctly")
                return 4
            }
        }
    }

    override fun comprobarSeleccionModoJuego(personaje: Personaje) {
        mostrarMenuModosJuego()
        while (true) {
            val focusedMode = Random.nextInt(1,4)
            val entrada: String = readln().lowercase()
            when(entrada){
                "1","gambit","gambit prime" -> {
                    var featured = false
                    if (focusedMode == 1) featured = true
                    jugarGambito(personaje, featured)
                }
                "2","nightfall","grandmaster nightfall" -> {
                    var featured = false
                    if (focusedMode == 2) featured = true
                    jugarOcaso(personaje, featured)
                }
                "3", "trials","trials of osiris" -> {
                    var featured = false
                    if (focusedMode == 3) featured = true
                    jugarTrials(personaje, featured)
                }
                "4", "raids", "dungeons", "raids_dungeons","raids and dungeons" -> {
                    var featured = false
                    if (focusedMode == 4) featured = true
                    jugarRyD(personaje, featured)
                }
                "s", "save" -> guardarDatos(personaje)
                "e", "exit" -> acabarJuego(personaje)
                else -> terminal.warning("                                                                      Please, answer the requested prompt correctly")
            }
        }
    }


    fun jugar(){
        terminal.println((color_Blanco)("                                                                          WELCOME TO DESTINY - LITE"))
        menuInicio()
        ejecutarAccionInicial()
        mostrarClasePersonaje()
        mostrarInformacionClases()
        val clase = selectorClasePersonaje()
        val personaje = crearPersonaje(clase)


    }


    override fun comprobarDatosPrevios(){
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
                    }

                    "n", "no" -> {
                        separador()
                        terminal.println((colorVerde)("                                                                                   LOADING GAME...."))
                        val animacion = AnimationManager.animacionCargando()
                        cargarDatos()
                    }
                    else -> {
                        terminal.warning("                                                                      Please, answer the requested prompt correctly")
                    }
                }
            }
        }

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
        val datosPersonaje = FicheroPersonaje.useLines { it.toString() }
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
            FicheroArmas.appendText("\nW ; ${it.nombre} ; ${it.arquetipo} ; ${it.tipoArma} ; ${it.elemento} ; ${it.rareza} ; ${it.rarity} ; ${it.colorElemento}")
        }
        personaje.armaduraEquipada.forEach {
            FicheroArmaduras.appendText("\nA ; ${it.nombre} ; ${it.parte} ; ${it.rareza} ; ${it.rarity}")
        }
    }
    private fun acabarJuego(personaje: Personaje):Boolean{
        guardarDatos(personaje)
        return false
    }
}