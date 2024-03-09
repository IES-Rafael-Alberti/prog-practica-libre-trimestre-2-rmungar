package org.practicatrim2.items

import com.github.ajalt.mordant.rendering.TextColors
import com.github.ajalt.mordant.rendering.TextStyle
import com.github.ajalt.mordant.terminal.Terminal
import java.io.File


class Arma( override var nombre:String, override var arquetipo:String, override var tipoArma:String, override var elemento: Elementos, override var rareza :String, override var rarity:TextStyle, override var colorElemento:TextStyle): Equipable<MutableList<Item>>, Sustituible<Item, MutableList<Item>>, Guardable<Item>, Informable, Item(){
    private val terminal = Terminal()

    override fun equipable(t: MutableList<Item>): Boolean {
        var cont = 1
        if(t.size == 3){
            println("You already have 3 weapons equipped, choose one to replace:")
            return false
        }
        else{
            terminal.println("${rarity(nombre)} has been equipped")
            return true
        }
    }
    override fun sustituir(t: Item, e: MutableList<Item>) {
        var seleccionValida1 = false // Booleano para abrir y cerrar el primer bucle while, indica si el usuario ha ingresado una entrada válida
        var seleccionValida2 = false // Booleano para abrir y cerrar el segundo bucle while, indica si el usuario ha ingresado una entrada válida
        if (e.size == 3) {
            while (!seleccionValida1) {
                println("Exchange weapon? (y / n)")
                val decision = readln().lowercase() // String para obtener la respuesta del usuario
                var cont = 1 // Entero para mostrar por pantalla
                when (decision) {
                    "y", "yes" -> {
                        e.forEach {
                            println("$cont - ")
                            (it as Arma).mostrarInformacion()
                            cont++
                        }
                        while (!seleccionValida2){
                            terminal.println((TextColors.brightWhite)("Select a weapon slot :"))
                            val slot = readln()
                            when(slot){
                                "1" -> {
                                    val armaAguardar = e[0]
                                    e.remove(armaAguardar)
                                    e[0] = t
                                    preguntarParaGuardar(armaAguardar as Arma)
                                    seleccionValida2 = true
                                    seleccionValida1 = true
                                }
                                "2" -> {
                                    val armaAguardar = e[1]
                                    e.remove(armaAguardar)
                                    e[1] = t
                                    preguntarParaGuardar(armaAguardar as Arma)
                                    seleccionValida2 = true
                                    seleccionValida1 = true
                                }
                                "3" -> {
                                    val armaAguardar = e[2]
                                    e.remove(armaAguardar)
                                    e[2] = t
                                    preguntarParaGuardar(armaAguardar as Arma)
                                    seleccionValida2 = true
                                    seleccionValida1 = true
                                }
                                else -> {
                                    terminal.println(TextColors.brightYellow("Please, answer the requested prompt correctly"))
                                }
                            }
                        }
                    }
                    "n", "no" -> {
                        preguntarParaGuardar(t as Arma)
                        seleccionValida2 = true
                        seleccionValida1 = true
                    }
                    else -> terminal.println(TextColors.brightYellow("Please, answer the requested prompt correctly"))
                }
            }
        }
        else{
            e[2] = t
        }
    }
    fun preguntarParaGuardar(arma: Arma){
        while (true) {
            println("Would you like to store ${arma.rarity(arma.nombre)}? (y / n)")
            val decision = readln().lowercase()
            when (decision) {
                "y", "yes" ->{
                    guardar(arma)
                    break
                }
                "n", "no" -> {
                    terminal.println(TextColors.brightRed("${arma.rarity(arma.nombre)} sent to the DCV, rest in peace"))
                    break
                }
                else -> terminal.println(TextColors.brightYellow("Please, answer the requested prompt correctly"))
            }
        }
    }
    override fun guardar(a: Item) {
        val workingDirectory = System.getProperty("user.dir")
        val armaFormateada = "\nW ; ${a.nombre} ; ${a.arquetipo} ; ${a.tipoArma} ; ${a.elemento} ;${a.rareza}"
        File("$workingDirectory/Datos_Guardado/Vault.txt").appendText(armaFormateada)
        terminal.println(TextColors.brightGreen("${a.rarity(a.nombre)} stored successfully"))
    }

    override fun mostrarInformacion() {
        terminal.println("${rarity(nombre)} -- ${colorElemento(elemento.desc)} -- ${rarity(rareza)} $tipoArma \n -------------- $arquetipo")
    }
}