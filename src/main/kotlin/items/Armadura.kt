package org.practicatrim2.items

import com.github.ajalt.mordant.rendering.TextColors
import com.github.ajalt.mordant.rendering.TextStyle
import com.github.ajalt.mordant.terminal.Terminal
import org.practicatrim2.personajes.Personaje
import java.io.File

class Armadura(override var nombre:String, override var parte:String, override var rareza:String, override var rarity:TextStyle) : Equipable<MutableList<Item>>, Sustituible<Item, MutableList<Item>>, Guardable<Item>, Informable, Item(){
    private val terminal = Terminal() //Variable empleada para
    override fun equipable(t : MutableList<Item>): Boolean {
        if (t.size == 5){
            terminal.warning("You already have 5 armor items equipped")
        }
        else if(t.size < 5) {
            t.find { it.parte == parte }.let {
                terminal.warning("You already have one of this item type equipped")
                return false
            }
        }
        return true
    }

    override fun equipar(item: Item, personaje: Personaje) {
        personaje.armaduraEquipada.add(item as Armadura)
        terminal.println(TextColors.brightGreen("Armor equipped successfully"))
    }

    override fun preguntarParaEquipar(item: Item): Boolean{
        terminal.println(TextColors.brightWhite("Do you wish to equip: ${item.rarity(item.nombre)}? (y / n)"))
        val respuesta = readln().lowercase()
        while (true) {
            when (respuesta) {
                "y", "yes" -> return true
                "n", "no" -> return false
                else -> terminal.warning("Please, answer the requested prompt correctly")
            }
        }
    }

    override fun sustituir(t: Item, e: MutableList<Item>) {
        while(true) {
            terminal.println(TextColors.brightWhite("Exchange armor? (y / n)"))
            val decision = readln().lowercase() //String para obtener la respuesta del usuario
            var posicionArmadura = 0 //Entero para iterar en la lista de Armaduras

            when(decision) {
                "y", "yes" -> {
                    e.forEach {
                        if (e[posicionArmadura].parte == t.parte) {
                            val armaduraPrevia = e[posicionArmadura] //Armadura equipada actualmente en el personaje
                            e.remove(e[posicionArmadura])
                            e.add(t)
                            terminal.println(TextColors.brightGreen("Armor equipped successfully"))
                            preguntarParaGuardar(armaduraPrevia as Armadura)
                        }
                        posicionArmadura++
                    }
                }
                "n", "no" -> {
                    preguntarParaGuardar(t as Armadura)
                }
                else -> terminal.warning("Please, answer the requested prompt correctly")
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
                else -> terminal.warning("Please, answer the requested prompt correctly")
            }
        }
    }

    override fun guardar(a: Item) {
        val workingDirectory = System.getProperty("user.dir")
        val armaduraFormateada = "\nA ; ${a.nombre} ; ${a.parte} ; ${a.rareza}"
        File("$workingDirectory/Datos_Guardado/Vault.txt").appendText(armaduraFormateada, Charsets.UTF_8)
        terminal.println(TextColors.brightGreen("${a.rarity(a.nombre)} stored successfully"))
    }

    override fun mostrarInformacion() {
        terminal.println("${rarity(nombre)} -- ${rarity(rareza)} $parte")
    }
}