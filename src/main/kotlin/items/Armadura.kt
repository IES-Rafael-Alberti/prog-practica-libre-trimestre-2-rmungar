package org.practicatrim2.items

import com.github.ajalt.mordant.rendering.TextColors
import com.github.ajalt.mordant.rendering.TextStyle
import com.github.ajalt.mordant.terminal.Terminal
import java.io.File

class Armadura(override var nombre:String, override var parte:String, override var rareza:String, override var rarity:TextStyle) : Equipable<List<Armadura>>,Sustituible<Armadura, MutableList<Armadura>>, Guardable<Armadura>, Informable, Item(){
    private val terminal = Terminal() //Variable empleada para
    override fun equipable(armaduraEquipada : List<Armadura>): Boolean {
        if (armaduraEquipada.size == 5){
            terminal.println("You already have 5 armor items equipped")
            return false
        }
        else{
            armaduraEquipada.find { it.parte == parte }.let { println("You already have one of this item type equipped") }
            return true
        }
    }

    override fun sustituir(armadura: Armadura, armaduraEquipada: MutableList<Armadura>) {
        while(true) {
            terminal.println("Exchange armor? (y / n)")
            val decision = readln().lowercase() //String para obtener la respuesta del usuario
            var posicionArmadura = 0 //Entero para iterar en la lista de Armaduras

            when(decision) {
                "y", "yes" -> {
                    armaduraEquipada.forEach {
                        if (armaduraEquipada[posicionArmadura].parte == armadura.parte) {
                            val armaduraPrevia = armaduraEquipada[posicionArmadura] //Armadura equipada actualmente en el personaje
                            armaduraEquipada.remove(armaduraEquipada[posicionArmadura])
                            armaduraEquipada.add(armadura)
                            terminal.println(TextColors.brightGreen("Armor equipped successfully"))
                            preguntarParaGuardar(armaduraPrevia)
                        }
                        posicionArmadura++
                    }
                }
                "n", "no" -> {
                    preguntarParaGuardar(armadura)
                }
                else -> terminal.println(TextColors.brightYellow("Please, answer the requested prompt correctly"))
            }
        }
    }

    fun preguntarParaGuardar(armadura: Armadura){
        while (true) {
            terminal.println("Would you like to store ${armadura.rarity(armadura.nombre)}? (y / n)")
            val decision = readln().lowercase()
            when (decision) {
                "y", "yes" ->{
                    guardar(armadura)
                    break
                }
                "n", "no" -> {
                    terminal.println(TextColors.brightRed("Armor sent to the DCV, rest in peace"))
                    break
                }
                else -> terminal.println(TextColors.brightYellow("Please, answer the requested prompt correctly"))
            }
        }
    }

    override fun guardar(a: Armadura) {
        val workingDirectory = System.getProperty("user.dir")
        val armaduraFormateada = "\nA ; ${a.nombre} ; ${a.parte} ; ${a.rareza}"
        File("$workingDirectory/Datos_Guardado/Vault.txt").appendText(armaduraFormateada, Charsets.UTF_8)
        terminal.println(TextColors.brightGreen("${a.rarity(a.nombre)} stored successfully"))
    }

    override fun mostrarInformacion() {
        val t = Terminal()
        t.println("${rarity(nombre)} -- ${rarity(rareza)} $parte")
    }
}