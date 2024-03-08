package org.practicatrim2.juego

import com.github.ajalt.mordant.rendering.TextColors
import com.github.ajalt.mordant.terminal.Terminal
import org.practicatrim2.animaciones.AnimationManager
import org.practicatrim2.items.Armadura
import org.practicatrim2.items.Comprobable
import java.io.File

object GestionJuego :Juego(), Comprobable<String> {
    private val terminal = Terminal() //Variable apra el uso de Mordant

    private val color_Blanco = TextColors.brightWhite // Variable del color blanco
    private val color_Verde = TextColors.brightGreen // Variable del color verde
    private val color_Rojo = TextColors.brightRed // Variable del color rojo
    private val color_Amarillo = TextColors.brightYellow // Variable del color amarillo

    private val workingDirectory = System.getProperty("user.dir") // Directorio actual
    private val FicheroArmaduras = File("$workingDirectory/Datos_Guardado/Armor_Set.txt") // Fichero donde se guardan las armaduras de una partida previa
    private val FicheroArmas = File("$workingDirectory/Datos_Guardado/Weapons_Set.txt") // Fichero donde se guardan las armas de una partida previa
    private val FicheroVault = File("$workingDirectory/Datos_Guardado/Vault.txt") // Fichero donde se guardan las armas y armaduras NO EQUIPADAS de una partida previa


    private fun separador(){
        repeat(15){
            println()
        }
    }

    fun comenzarJuego():Juego{
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

    override fun comprobarAccion(accion:String):Boolean{
        when(accion){
            "1" -> {
                val datosExistentes = comprobarDatosPrevios()
                if (!datosExistentes) {
                    terminal.warning("No game data")
                    generarNuevoJuego()
                }

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
                terminal.warning("                                                                      Please, answer the requested prompt correctly")
                return false
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
        crearPersonaje(clase)

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
                        terminal.println((color_Verde)("                                                                                  DATA OVERWRITTEN"))
                        generarNuevoJuego()
                    }

                    "n", "no" -> {
                        separador()
                        terminal.println((color_Verde)("                                                                                   LOADING GAME...."))
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

    fun cargarDatos() {
        val armaduras = FicheroArmaduras.useLines { it.toList() }
        armaduras.forEach {
            val armadura = Armadura(it[0].toString(),it[1].toString(),it[2].toString(),TextColors.rgb("${it[3]}"))
        }
    }
    fun generarNuevoJuego(){
        FicheroArmaduras.writeText("")
        FicheroArmas.writeText("")
        FicheroVault.writeText("")
    }
    fun acabarJuego(){
        TODO()
    }
}