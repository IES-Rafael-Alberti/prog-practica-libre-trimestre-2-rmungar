package org.practicatrim2

import java.io.File

fun main() {
    val workingDirectory = System.getProperty("user.dir")
    File("$workingDirectory/Datos_Guardado/Armor_Set.txt").writeText("A", Charsets.UTF_8)
    File("$workingDirectory/Datos_Guardado/Weapons_Set.txt").writeText("Palindrome ; Retold Tale ; Apex Predator", Charsets.UTF_8)
    File("$workingDirectory/Datos_Guardado/Vault.txt").writeText("ASDFASDFASDF ; ADSFASDFASF ; ADFASDFA", Charsets.UTF_8)
    val armaAleaoria = File("$workingDirectory/Loot_Pool/Trials.txt").useLines { it.toList() }.random()
    println(armaAleaoria)
}