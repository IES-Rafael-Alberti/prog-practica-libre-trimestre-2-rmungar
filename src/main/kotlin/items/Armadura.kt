package org.practicatrim2.items

import com.github.ajalt.mordant.rendering.TextColors
import com.github.ajalt.mordant.rendering.TextStyle
import com.github.ajalt.mordant.terminal.Terminal
import java.io.File

class Armadura(val nombre:String, val parte:String, val rareza:String, private val rarity:TextStyle) : Equipable<List<Armadura>>,Sustituible<Armadura, MutableList<Armadura>>,Guardable<Armadura>, Item(){
    private val t = Terminal() //Variable empleada para
    override fun equipable(armaduraEquipada : List<Armadura>): Boolean {
        if (armaduraEquipada.size == 5){
            println("You already have 5 armor items equipped, would you like to replace the currently equipped one?:")
            return false
        }
        else{
            armaduraEquipada.find { it.parte == parte }.let { println("You already have one of this item type equipped, exchange?") }
            return true
        }
    }

    override fun mostrarInformacion() {
        val t = Terminal()
        t.println("${rarity(nombre)} -- ${rarity(rareza)} $parte")
    }

    override fun sustituir(armadura: Armadura, armaduraEquipada: MutableList<Armadura>) {
        while(true) {
            println("Exchange armor? (y / n)")
            val decision = readln().lowercase() //String para obtener la respuesta del usuario
            var posicionArmadura = 0 //Entero para iterar en la lista de Armaduras

            when(decision) {
                "y", "yes" -> {
                    armaduraEquipada.forEach {
                        if (armaduraEquipada[posicionArmadura].parte == armadura.parte) {
                            val armaduraPrevia = armaduraEquipada[posicionArmadura] //Armadura equipada actualmente en el personaje
                            armaduraEquipada.remove(armaduraEquipada[posicionArmadura])
                            armaduraEquipada.add(armadura)
                            t.println(TextColors.brightGreen("Armor equipped successfully"))
                            preguntarParaGuardar(armaduraPrevia)
                        }
                        posicionArmadura++
                    }
                }
                "n", "no" -> {
                    preguntarParaGuardar(armadura)
                }
                else -> t.println(TextColors.brightYellow("Please, answer the requested prompt correctly"))
            }
        }
    }

    fun preguntarParaGuardar(armadura: Armadura){
        while (true) {
            println("Would you like to save the armor you had previousy equipped? (y / n)")
            val decision = readln().lowercase()
            when (decision) {
                "y", "yes" ->{
                    guardar(armadura)
                    break
                }
                "n", "no" -> {
                    t.println(TextColors.brightRed("Armor sent to the DCV, rest in peace"))
                    break
                }
                else -> t.println(TextColors.brightYellow("Please, answer the requested prompt correctly"))
            }
        }
    }

    override fun guardar(a: Armadura) {
        val workingDirectory = System.getProperty("user.dir")
        val armaduraFormateada = "W ; ${a.nombre} ; ${a.parte} ; ${a.rareza}"
        File("$workingDirectory/Datos_Guardado/Vault.txt").appendText(armaduraFormateada, Charsets.UTF_8)
        t.println(TextColors.brightGreen("Armor stored successfully"))
    }
}