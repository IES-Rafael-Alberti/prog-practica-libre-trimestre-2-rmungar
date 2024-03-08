package org.practicatrim2.juego

import com.github.ajalt.mordant.rendering.TextColors
import com.github.ajalt.mordant.terminal.Terminal
import org.practicatrim2.capitalizar
import org.practicatrim2.items.Mostrable
import org.practicatrim2.modosDeJuego.GameModes
import org.practicatrim2.personajes.*

open class Juego():Mostrable {

    private val colorTitanes = TextColors.brightRed // Color para mordant para la clase Titan
    private val colorWarlocks = TextColors.brightYellow // Color para mordant para la clase Warlock
    private val colorHunters = TextColors.rgb("#00eeff") // Color para mordant para la clase Hunter
    private val terminal = Terminal() // Variable empleada para lo relacionado con mordant
    private fun separador(){
        repeat(35){
            println()
        }
    }
    override fun mostrarClasePersonaje(){
        separador()
        terminal.println(TextColors.brightWhite("                                                                       CHOOSE A CLASS FOR YOUR CHARACTER"))
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
            terminal.print((TextColors.brightWhite)("                                                            Enter the class you wish to create a character of: "))
            val claseAcrear = readln().uppercase()
            when(claseAcrear){
                "TITAN" -> return Clases.TITAN

                "HUNTER" -> return Clases.HUNTER

                "WARLOCK" -> return Clases.WARLOCK

                else -> terminal.println(TextColors.brightYellow("                                                                Please, answer the requested prompt correctly"))
            }
        }
    }

    override fun mostrarMenuModosJuego(){
        separador()
        terminal.println(TextColors.brightWhite("                                                                       CHOOSE A CLASS FOR YOUR CHARACTER"))
        println()
        terminal.println("                   ${GameModes.GAMBIT.color ("GAMBIT PRIME")}                               ${GameModes.NIGHTFALL.color ("GRANDMASTER NIGHTFALL")}                               ${GameModes.TRIALS.color ("TRIALS OF OSIRIS")}                               ${GameModes.RAIDS_DUNGEONS.color("RAIDS & DUNGEONS")}")
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

    fun pedirNombrePersonaje():String{
        separador()
        terminal.print((TextColors.brightWhite) ("                                                                              Please enter a name: "))
        var nombre = readln() //Cadena para el nombre del personaje
        while (nombre == "" || nombre.isEmpty() || nombre.isBlank()){
            terminal.print((TextColors.brightYellow) ("                                                                           Please enter a valid name: "))
            nombre = readln().capitalizar()
        }
        return nombre
    }

    fun pedirGeneroPersonaje():String{
        println()
        while(true) {
            terminal.print((TextColors.brightWhite)("                                                                    Do you wish them to be male or female?: "))
            val genero = readln().capitalizar()
            when(genero){
                "Male" -> return "Male"
                "Female" -> return "Female"
                else -> terminal.println(TextColors.brightYellow("                                                                Please, answer the requested prompt correctly"))
            }
        }
    }

    override fun mostrarRazaPersonaje(){
        separador()
        terminal.println(TextColors.brightWhite("                                                                       CHOOSE A RACE FOR YOUR CHARACTER"))
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

    fun selectorRazaPersonaje():Razas {
        mostrarRazaPersonaje()
        mostrarInformacionRazas()
        while (true) {
            println()
            terminal.print((TextColors.brightWhite)("                                                                     Enter the race you wish them to be: "))
            val razaAcrear = readln().uppercase() //Cadena ingresada por el usuario a comprobar
            when (razaAcrear) {
                "HUMAN" -> return Razas.HUMAN

                "AWOKEN" -> return Razas.AWOKEN

                "EXO" -> return Razas.EXO

                else -> terminal.println(TextColors.brightYellow("                                                                Please, answer the requested prompt correctly"))
            }
        }
    }



}