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
                comprobarDatosPrevios()
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

    override fun comprobarSeleccionModoJuego(personaje: Personaje) {
        mostrarMenuModosJuego()
        while (true) {
            val entrada: String = readln().lowercase()
            when(entrada){
                "1","gambit","gambit prime" -> jugarGambito(personaje)
                "2","nightfall","grandmaster nightfall" -> jugarOcaso(personaje)
                "3", "trials","trials of osiris" -> jugarTrials(personaje)
                "4", "raids", "dungeons", "raids_dungeons","raids and dungeons" -> jugarRyD(personaje)
                "s", "save" -> guardarDatos(personaje)
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
        val datosPersonaje = FicheroPersonaje.useLines { it.toString() }
        val datosArmaduras = FicheroArmaduras.useLines { it.toList() }
        val datosArmas = FicheroArmas.useLines { it.toList() }
        val personaje = separarPersonajes(datosPersonaje)

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

    }
    fun separarPersonajes(datosPersonaje: String):Personaje{
        when (datosPersonaje[0].toString()){
            "W" -> {
                val warlock = Warlock.generarPersonaje(datosPersonaje)
                return warlock
            }
            "T" -> {
                val titan = Titan.generarPersonaje(datosPersonaje)
                return titan
            }
            else -> {
                val hunter = Hunter.generarPersonaje(datosPersonaje)
                return hunter
            }
        }
    }

    fun generarNuevoJuego(){
        FicheroArmaduras.writeText("")
        FicheroArmas.writeText("")
        FicheroVault.writeText("")
        FicheroPersonaje.writeText("")
    }

    fun guardarDatos(personaje: Personaje){
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
    fun acabarJuego(){

    }
}