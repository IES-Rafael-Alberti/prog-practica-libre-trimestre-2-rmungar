package org.practicatrim2.juego

import com.github.ajalt.mordant.rendering.TextColors
import com.github.ajalt.mordant.terminal.Terminal
import java.io.File

class GestionJuego() :Juego() {
    private val terminal = Terminal() //Variable apra el uso de Mordant

    private val color_Blanco = TextColors.brightWhite // Variable del color blanco
    private val color_Verde = TextColors.brightGreen // Variable del color verde
    private val color_Rojo = TextColors.brightRed // Variable del color rojo
    private val color_Amarillo = TextColors.brightYellow // Variable del color amarillo

    private val workingDirectory = System.getProperty("user.dir") // Directorio actual
    private val FicheroArmaduras = File("$workingDirectory/Datos_Guardado/Armor_Set.txt") // Fichero donde se guardan las armaduras de una partida previa
    private val FicheroArmas = File("$workingDirectory/Datos_Guardado/Weapons_Set.txt") // Fichero donde se guardan las armas de una partida previa
    private val FicheroVault = File("$workingDirectory/Datos_Guardado/Vault.txt") // Fichero donde se guardan las armas y armaduras NO EQUIPADAS de una partida previa

    fun comenzarJuego(){
        TODO()
    }
    fun menuInicio(){
        println()
        terminal.println((color_Amarillo)("                                                                                    1 - New Game"))
        terminal.println((color_Verde)("                                                                                    2 - Continue Game"))
        terminal.println((color_Rojo)("                                                                                    3 - Exit Game"))
    }

    fun ejecutarAccionInicial(){
        println()
        terminal.print((color_Blanco)("                                                                        What are you going to do today? :"))
        var accion = readln()
        while(!comprobarAccion(accion)){
            terminal.print((color_Blanco)("                                                                         What are you going to do today? :"))
            accion = readln()
        }
    }

    fun comprobarAccion(accion:String):Boolean{
        when(accion){
            "1" -> {
                generarNuevoJuego()
                return true
            }
            "2" -> {
                cargarDatos()
                return true
            }
            "3" -> {
                acabarJuego()
                return true
            }
            else -> {
                terminal.println(TextColors.brightYellow("                                                                      Please, answer the requested prompt correctly"))
                return false
            }

        }
    }

    fun jugar(){
        terminal.println((color_Blanco)("                                                                          WELCOME TO DESTINY - LITE"))
        menuInicio()
        ejecutarAccionInicial()
        mostrarMenuPersonaje()
        mostrarInformacionClases()
        val clase = selectorClasePersonaje()
        crearPersonaje(clase)

    }
    fun generarNuevoJuego(){
        FicheroArmaduras.writeText("")
        FicheroArmas.writeText("")
        FicheroVault.writeText("")
    }

    fun comprobarDatosPrevios(){

    }
    fun cargarDatos(){
        TODO()
    }
    fun acabarJuego(){
        TODO()
    }
}