package org.practicatrim2.items

import com.github.ajalt.mordant.rendering.TextColors
import com.github.ajalt.mordant.rendering.TextStyle
import com.github.ajalt.mordant.terminal.Terminal
import java.io.File


class Arma(val nombre:String, val arquetipo:String, val tipoArma:String, val elemento: Elementos, val rareza :String,private var rarity:TextStyle, val colorElemento:TextStyle): Equipable<List<Arma>>, Sustituible<Arma, MutableList<Arma>>, Guardable<Arma>, Informable, Item(){
    private val terminal = Terminal()

    override fun equipable(armaEquipada: List<Arma>): Boolean {
        val t = Terminal()
        var cont = 1
        if(armaEquipada.size == 3){
            println("You already have 3 weapons equipped, choose one to replace:")
            return false
        }
        else{
            t.println("${rarity(nombre)} has been equipped")
            return true
        }
    }
    override fun sustituir(arma: Arma, armasEquipadas: MutableList<Arma>) {
        var seleccionValida1 = false // Booleano para abrir y cerrar el primer bucle while, indica si el usuario ha ingresado una entrada válida
        var seleccionValida2 = false // Booleano para abrir y cerrar el segundo bucle while, indica si el usuario ha ingresado una entrada válida
        if (armasEquipadas.size == 3) {
            while (!seleccionValida1) {
                println("Exchange weapon? (y / n)")
                val decision = readln().lowercase() // String para obtener la respuesta del usuario
                var cont = 1 // Entero para mostrar por pantalla
                when (decision) {
                    "y", "yes" -> {
                        armasEquipadas.forEach {
                            println("$cont - ")
                            it.mostrarInformacion()
                            cont++
                        }
                        while (!seleccionValida2){
                            terminal.println((TextColors.brightWhite)("Select a weapon slot :"))
                            val slot = readln()
                            when(slot){
                                "1" -> {
                                    val armaAguardar = armasEquipadas[0]
                                    armasEquipadas.remove(armaAguardar)
                                    armasEquipadas[0] = arma
                                    preguntarParaGuardar(armaAguardar)
                                    seleccionValida2 = true
                                    seleccionValida1 = true
                                }
                                "2" -> {
                                    val armaAguardar = armasEquipadas[1]
                                    armasEquipadas.remove(armaAguardar)
                                    armasEquipadas[1] = arma
                                    preguntarParaGuardar(armaAguardar)
                                    seleccionValida2 = true
                                    seleccionValida1 = true
                                }
                                "3" -> {
                                    val armaAguardar = armasEquipadas[2]
                                    armasEquipadas.remove(armaAguardar)
                                    armasEquipadas[2] = arma
                                    preguntarParaGuardar(armaAguardar)
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
                        preguntarParaGuardar(arma)
                        seleccionValida2 = true
                        seleccionValida1 = true
                    }
                    else -> terminal.println(TextColors.brightYellow("Please, answer the requested prompt correctly"))
                }
            }
        }
        else{
            armasEquipadas[2] = arma
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
    override fun guardar(a: Arma) {
        val workingDirectory = System.getProperty("user.dir")
        val armaFormateada = "W ; ${a.nombre} ; ${a.arquetipo} ; ${a.tipoArma} ; ${a.elemento} ;${a.rareza}"
        File("$workingDirectory/Datos_Guardado/Vault.txt").appendText(armaFormateada)
        terminal.println(TextColors.brightGreen("${a.rarity(a.nombre)} stored successfully"))
    }

    override fun mostrarInformacion() {
        val t = Terminal()
        t.println("${rarity(nombre)} -- ${colorElemento(elemento.desc)} -- ${rarity(rareza)} $tipoArma \n -------------- $arquetipo")
    }
}