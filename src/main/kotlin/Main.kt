package org.practicatrim2

import com.github.ajalt.mordant.animation.textAnimation
import com.github.ajalt.mordant.rendering.TextColors
import java.io.File
import com.github.ajalt.mordant.terminal.Terminal
import org.practicatrim2.items.Item
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
    val armaAleatoria = File("$workingDirectory/Loot_Pool/Raids_Dungeons.txt").useLines { it.toList() }.random()
    val itemPrueba = Item()
    val arma = itemPrueba.procesarItem(armaAleatoria)
    val t = Terminal()
    val juego = Juego()
    val gestor = GestionJuego()
    //juego.mostrarMenuPersonaje()
    //juego.mostrarInformacionClases()
    //juego.selectorClasePersonaje()
    //gestor.jugar()
    //juego.mostrarRazaPersonaje()
    //juego.mostrarInformacionRazas()
    //juego.selectorRazaPersonaje()
    //juego.mostrarMenuModosJuego()

    val a = t.textAnimation<Int> { frame ->
        (1..196).joinToString("") {
            val hue = (frame + it) * 3 % 360
            TextColors.hsv(hue, 1, 1)("·")
        }
    }

    repeat(240) {
        a.update(it)
        Thread.sleep(25)
    }


    //CONVERTIR OBJETOS A TEXTO
    /**
        val armaPrueba = Arma("Palindromo", "Aggressive Frame", "Hand Cannon", Elementos.VOID)
        val guardarArma = gson.toJson(armaPrueba)
        File("$workingDirectory/Datos_Guardado/Vault.txt").appendText(guardarArma)
    */

    //LEER DATOS Y CONVERTIRLOS A OBJETOS
    /**
        val json = File("$workingDirectory/Datos_Guardado/Vault.txt").readText()
        val sacarArmaDelVault = gson.fromJson(json, Arma::class.java)
        println(sacarArmaDelVault.toString())
    */
}