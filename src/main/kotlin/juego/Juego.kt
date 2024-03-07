package org.practicatrim2.juego

import com.github.ajalt.mordant.rendering.TextColors
import com.github.ajalt.mordant.terminal.Terminal
import com.github.ajalt.mordant.widgets.Text
import org.practicatrim2.capitalizar
import org.practicatrim2.personajes.Clases
import org.practicatrim2.personajes.Personaje

open class Juego() {

    private val colorTitanes = TextColors.brightRed // Color para mordant para la clase Titan
    private val colorWarlocks = TextColors.brightYellow // Color para mordant para la clase Warlock
    private val colorHunters = TextColors.rgb("#00eeff") // Color para mordant para la clase Hunter
    val terminal = Terminal() // Variable empleada para todo lo relacionado con mordant
    fun crearPersonaje(clase: Clases){
        val nombre = pedirNombrePersonaje()
        when (clase){
            Clases.TITAN ->{}
            Clases.WARLOCK ->{}
            Clases.HUNTER -> {}
        }
    }
    fun pedirNombrePersonaje():String{
        println()
        terminal.println((TextColors.brightWhite) ("                                                                                      Please enter a name: "))
        var nombre = readln() //Cadena para el nombre del personaje
        while (nombre == "" || nombre.isEmpty() || nombre.isBlank()){
            terminal.println((TextColors.brightYellow) ("                                                                               Please enter a valid name: "))
            nombre = readln().capitalizar()
        }
        return nombre
    }

    fun pedirGeneroPersonaje():String{
        println()
        while(true) {
            terminal.println((TextColors.brightWhite)("                                                                             Do you wish them to be male or female: "))
            val genero = readln().capitalizar()
            when(genero){
                "Male" -> return "Male"
                "Female" -> return "Female"
                else -> terminal.println(TextColors.brightYellow("                                                                Please, answer the requested prompt correctly"))
            }
        }
    }


    fun mostrarMenuPersonaje(){
        terminal.println(TextColors.brightWhite("                                                                       CHOOSE A CLASS FOR YOUR CHARACTER"))
        println()
        terminal.println("                                  ${colorTitanes ("TITAN")}                                             ${colorWarlocks ("WARLOCK")}                                             ${colorHunters ("HUNTER")}")
    }
    fun mostrarInformacionClases(){
        println()
        println("                  Titans are specialized in close combat,              Warlocks are specialized in magic,                  Hunters are specialized in tactics,\n" +
                "                  defense and heavy equipment. Those who              they long to understand the traveler                 they wield the light to claim it's \n" +
                "                   act with confidence and decision and             and it's powers. A warlock's mind is an              treasures. Being assassins, experts at\n" +
                "                   serve as brute force instruments for            arsenal of lethal secrets between divinity             wielding knives and precision weapons.\n" +
                "                           the Traveler's will.                                   and madness.                              They tend to write their own laws.")
    }

    fun selectorClasePersonaje():Clases{
        while (true) {
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
    fun cargarDatos(){
        TODO()
    }
}