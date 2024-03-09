package org.practicatrim2

import com.github.ajalt.mordant.terminal.Terminal
import org.practicatrim2.animaciones.AnimationManager
import org.practicatrim2.juego.GestionJuego
import org.practicatrim2.juego.Juego


fun String.capitalizar():String{
    val texto = this.toList()
    var palabraCapitalizada = ""
    var posicion = 0
    for (caracter in texto){
        if (caracter == texto[0]) palabraCapitalizada += caracter.toString().uppercase()
        else if (texto[posicion - 1] == ' ') palabraCapitalizada += caracter.toString().uppercase()
        else{
            palabraCapitalizada += caracter.lowercase()
        }
        posicion++
    }
    return palabraCapitalizada
}

fun main() {
    val workingDirectory = System.getProperty("user.dir")
    val terminal = Terminal()
    val juego = Juego()
    val gestor = GestionJuego
    val manager = AnimationManager

    //terminal.println(TextColors.brightWhite("                                                                       CHOOSE A CLASS FOR YOUR CHARACTER"))
    juego.mostrarMenuModosJuego()

}