package org.practicatrim2.juego

import com.github.ajalt.mordant.rendering.TextColors
import com.github.ajalt.mordant.terminal.Terminal
import org.practicatrim2.capitalizar
import org.practicatrim2.items.Jugable
import org.practicatrim2.items.Mostrable
import org.practicatrim2.modosDeJuego.GameModes
import org.practicatrim2.personajes.*
import java.io.File

open class Juego():Mostrable,Jugable {
    private val terminal = Terminal() // Variable empleada para lo relacionado con mordant

    private val colorTitanes = TextColors.brightRed // Color para mordant para la clase Titan
    private val colorWarlocks = TextColors.brightYellow // Color para mordant para la clase Warlock
    private val colorHunters = TextColors.rgb("#00eeff") // Color para mordant para la clase Hunter
    private val color_Blanco = TextColors.brightWhite

    val workingDirectory = System.getProperty("user.dir") // Directorio actual
    private val LootGambit = File("${workingDirectory}/Loot_Pool/Gambit.txt") // Fichero donde se guardan las posibles recompensas del modo Gambit
    private val LootNightfall = File("${workingDirectory}/Loot_Pool/Nightfall.txt") // Fichero donde se guardan las posibles recompensas del modo Nightfall
    private val LootTrials = File("${workingDirectory}/Loot_Pool/Trials.txt") // Fichero donde se guardan las posibles recompensas del modo Trials
    private val LootRyD = File("${workingDirectory}/Loot_Pool/Raids_Dungeons.txt") // Fichero donde se guardan las posibles recompensas del modo Raids and Dungeons
    private fun separador(){
        repeat(35){
            println()
        }
    }
    override fun mostrarClasePersonaje(){
        separador()
        terminal.println(color_Blanco("                                                                       CHOOSE A CLASS FOR YOUR CHARACTER"))
        println()
        terminal.println("                                  ${colorTitanes ("TITAN")}                                             ${colorWarlocks ("WARLOCK")}                                             ${colorHunters ("HUNTER")}")
    }

    override fun mostrarInformacionClases(){
        println()
        println("                  Titans are specialized in close combat,              Warlocks are specialized in magic,                  Hunters are specialized in tactics,\n" +
                "                  defense and heavy equipment. Those who              they long to understand the traveler                 they wield the light to claim it's \n" +
                "                   act with confidence and decision and             and it's powers. A warlock's mind is an              treasures. Being assassins, experts at\n" +
                "                   serve as brute force instruments for            arsenal of lethal secrets between divinity             wielding knives and precision weapons.\n" +
                "                           the Traveler's will.                                   and madness.                              They tend to write their own laws.")
    }

    fun selectorClasePersonaje():Clases{
        while (true) {
            println()
            terminal.print(color_Blanco("                                                            Enter the class you wish to create a character of: "))
            val claseAcrear = readln().uppercase()
            when(claseAcrear){
                "TITAN" -> return Clases.TITAN

                "HUNTER" -> return Clases.HUNTER

                "WARLOCK" -> return Clases.WARLOCK

                else -> terminal.warning("                                                                Please, answer the requested prompt correctly")
            }
        }
    }

    override fun mostrarMenuModosJuego(){
        terminal.print(color_Blanco("                                                                             LOADING STAR CHART"))
        repeat(3){
            Thread.sleep(1000)
            terminal.print(color_Blanco("."))
        }
        separador()
        Thread.sleep(500)
        terminal.println(color_Blanco("                                                                                 --STAR CHART--"))
        println()
        terminal.println("       ${GameModes.GAMBIT.color ("⟁ GAMBIT PRIME ⟁")}                               ${GameModes.NIGHTFALL.color ("★ GRANDMASTER NIGHTFALL ★")}                               ${GameModes.TRIALS.color ("⚔ TRIALS OF OSIRIS ⚔")}                               ${GameModes.RAIDS_DUNGEONS.color("✦ RAIDS & DUNGEONS ✦")}")
        terminal.println("                                                         ${color_Blanco("s: Save")}                                                           ${color_Blanco("e: Exit")}")
        terminal.print(color_Blanco("                                                                                       > "))
    }


    fun crearPersonaje(clase: Clases):Personaje{
        val nombre = pedirNombrePersonaje()
        val genero = pedirGeneroPersonaje()
        val raza = selectorRazaPersonaje()
        when (clase){
            Clases.TITAN ->{
                return Titan(nombre, genero, raza)
            }
            Clases.WARLOCK ->{
                return Warlock(nombre, genero, raza)
            }
            Clases.HUNTER -> {
                return Hunter(nombre, genero, raza)
            }
        }
    }

    private fun pedirNombrePersonaje():String{
        separador()
        terminal.print(color_Blanco("                                                                              Please enter a name: "))
        var nombre = readln() //Cadena para el nombre del personaje
        while (nombre == "" || nombre.isEmpty() || nombre.isBlank()){
            terminal.warning("                                                                           Please enter a valid name: ")
            nombre = readln().capitalizar()
        }
        return nombre
    }

    private fun pedirGeneroPersonaje():String{
        println()
        while(true) {
            terminal.print(color_Blanco("                                                                    Do you wish them to be male or female?: "))
            val genero = readln().capitalizar()
            when(genero){
                "Male" -> return "Male"
                "Female" -> return "Female"
                else -> terminal.warning("                                                                Please, answer the requested prompt correctly")
            }
        }
    }

    override fun mostrarRazaPersonaje(){
        separador()
        terminal.println(color_Blanco("                                                                       CHOOSE A RACE FOR YOUR CHARACTER"))
        println()
        terminal.println("                                  ${colorTitanes ("HUMAN")}                                              ${colorWarlocks ("AWOKEN")}                                               ${colorHunters ("EXO")}")
    }

    override fun mostrarInformacionRazas(){
        println()
        println("                  The survivors of a race whose kindgom,               A humanoid race that was born when a                 Humanoid race that appeared during\n" +
                "                 extended past the Solar System now fight              colony ship disappeared in a space -                Earth's Golden Age. They are robots \n" +
                "                     for survival after the Traveller                     time anomaly during the first                     with conscience and feelings built\n" +
                "                  unintentionally led them to their first                          collapse.                                           by humanity.\n" +
                "                                 collapse.")
    }

    private fun selectorRazaPersonaje():Razas {
        mostrarRazaPersonaje()
        mostrarInformacionRazas()
        while (true) {
            println()
            terminal.print(color_Blanco("                                                                     Enter the race you wish them to be: "))
            val razaAcrear = readln().uppercase() //Cadena ingresada por el usuario a comprobar
            when (razaAcrear) {
                "HUMAN" -> return Razas.HUMAN

                "AWOKEN" -> return Razas.AWOKEN

                "EXO" -> return Razas.EXO

                else -> terminal.warning("                                                                Please, answer the requested prompt correctly")
            }
        }
    }

    override fun jugarGambito() {

    }

    override fun jugarOcaso() {
        TODO("Not yet implemented")
    }

    override fun jugarRyD() {
        TODO("Not yet implemented")
    }

    override fun jugarTrials() {
        TODO("Not yet implemented")
    }

}