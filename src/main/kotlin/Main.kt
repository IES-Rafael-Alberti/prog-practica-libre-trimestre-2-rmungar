package org.practicatrim2
import com.google.gson.Gson
import java.io.File
import com.github.ajalt.mordant.terminal.Terminal
import org.practicatrim2.items.Arma
import org.practicatrim2.items.Item

fun main() {
    val workingDirectory = System.getProperty("user.dir")

    File("$workingDirectory/Datos_Guardado/Armor_Set.txt").writeText("A", Charsets.UTF_8)
    File("$workingDirectory/Datos_Guardado/Weapons_Set.txt").writeText("Palindrome ; Retold Tale ; Apex Predator", Charsets.UTF_8)
    val armaAleatoria = File("$workingDirectory/Loot_Pool/Raids_Dungeons.txt").useLines { it.toList() }.random()
    val itemPrueba = Item()
    val arma = itemPrueba.procesarItem(armaAleatoria)
    arma.mostrarInformacion()

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