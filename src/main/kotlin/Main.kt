package org.practicatrim2

import com.github.ajalt.mordant.rendering.TextColors
import com.github.ajalt.mordant.terminal.Terminal
import org.practicatrim2.animaciones.AnimationManager
import org.practicatrim2.items.Armadura
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

    //val arm = Armadura("Kentarck", "Helmet", "Legendary",TextColors.brightWhite)
//
//
    //try {
    //    val gestor = GestionJuego
//
    //    gestor.comenzarJuego()
    //}
    //catch (e:Exception){
    //    println(e.message)
    //}
    val juego = Juego()
    juego.mostrarInterfazJuego()
    juego.selectorSeccionJuego()
}