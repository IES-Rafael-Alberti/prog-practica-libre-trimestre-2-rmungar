package org.practicatrim2
import com.google.gson.Gson
import java.io.File

fun main() {
    val workingDirectory = System.getProperty("user.dir")
    val gson = Gson()
    File("$workingDirectory/Datos_Guardado/Armor_Set.json").writeText("A", Charsets.UTF_8)
    File("$workingDirectory/Datos_Guardado/Weapons_Set.json").writeText("Palindrome ; Retold Tale ; Apex Predator", Charsets.UTF_8)
    val armaAleatoria = File("$workingDirectory/Loot_Pool/Trials.txt").useLines { it.toList() }.random()
    println(armaAleatoria)

    //CONVERTIR OBJETOS A TEXTO
    /**
    val armaPrueba = Arma("Palindromo", "Aggressive Frame", "Hand Cannon", Elementos.VOID)
    val guardarArma = gson.toJson(armaPrueba)
    File("$workingDirectory/Datos_Guardado/Vault.json").writeText(guardarArma)
    */

    //LEER DATOS Y CONVERTIRLOS A OBJETOS
    /**
    val json = File("$workingDirectory/Datos_Guardado/Vault.json").readText()
    val sacarArmaDelVault = gson.fromJson(json, Arma::class.java)
    println(sacarArmaDelVault.toString())
    */
}