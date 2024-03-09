package org.practicatrim2.items

import com.github.ajalt.mordant.rendering.TextColors
import com.github.ajalt.mordant.rendering.TextColors.*
import com.github.ajalt.mordant.rendering.TextStyle
import com.github.ajalt.mordant.terminal.Terminal
import org.practicatrim2.personajes.Personaje
import java.io.File


interface GestorItem{       //DIP --> SOLID
    fun procesarItem(item: String): Item
    fun clasificarItem(itemAprocesar: List<String>): Item
    fun obtenerElemento(itemAprocesar: List<String>): Pair<Elementos,TextStyle>
    fun obtenerRareza(itemAprocesar: List<String>): TextStyle
}

open class Item:GestorItem, Equipable<Item>, Sustituible<Item, Personaje>, Guardable<Item>, Informable {
    open var nombre: String = ""
    open var parte: String = ""
    open var rareza: String = ""
    open var rarity: TextStyle = TextColors.rgb("#9500ff")
    open var arquetipo:String = ""
    open var tipoArma: String = ""
    open var elemento: Elementos = Elementos.KINETIC
    open var colorElemento:TextStyle = brightWhite
    private val terminal = Terminal()


    override fun procesarItem(item: String): Item {
        val itemAprocesar = item.split(" ; ")
        val itemProcesado = clasificarItem(itemAprocesar)
        return itemProcesado
    }

    override fun clasificarItem(itemAprocesar: List<String>) : Item {
        if (itemAprocesar[0] == "W") {
            val elemento = obtenerElemento(itemAprocesar).first // Elemento del arma
            val colorElemento = obtenerElemento(itemAprocesar).second
            val rarity = obtenerRareza(itemAprocesar) // Patrón de color para la terminal de acuerdo a la rareza del arma
            val weapon = Arma(itemAprocesar[1], itemAprocesar[2], itemAprocesar[3], elemento, itemAprocesar[5], rarity, colorElemento)
            return weapon
        }
        else{
            val rarity = obtenerRareza(itemAprocesar) // Patrón de color para la terminal de acuerdo a la rareza del arma
            val armor = Armadura(itemAprocesar[1], itemAprocesar[2], itemAprocesar[3], rarity)
            return armor
        }
    }

    override fun obtenerElemento(itemAprocesar: List<String>): Pair<Elementos,TextStyle> {
        when (itemAprocesar[4]) {
            "Void" -> return Pair(Elementos.VOID, TextColors.rgb("#9500ff"))
            "Solar" -> return Pair(Elementos.SOLAR, brightRed)
            "Arc" -> return Pair(Elementos.ARC, brightCyan)
            "Strand" -> return Pair(Elementos.STRAND, green)
            "Stasis" -> return Pair(Elementos.STASIS, blue)
        }
        return Pair(Elementos.KINETIC, brightWhite)
    }
    override fun obtenerRareza(itemAprocesar: List<String>):TextStyle{
        when(itemAprocesar[itemAprocesar.size - 1]){
            "Exotic" -> return TextColors.rgb("#bf8506")
        }
        return TextColors.rgb("#9500ff")
    }

    override fun equipable(itemsEquipados: MutableList<Item>): Boolean {
        when(itemsEquipados[0]){
            is Armadura -> {
                if (itemsEquipados.size == 5){
                    terminal.warning("You already have 5 armor items equipped")
                }
                else if(itemsEquipados.size < 5) {
                    itemsEquipados.find { it.parte == parte }.let {
                        terminal.warning("You already have one of this item type equipped")
                        return false
                    }
                }
                return true
            }
            else -> {
                if(itemsEquipados.size == 3){
                    terminal.warning("You already have 3 weapons equipped")
                    return false
                }
                else{
                    return true
                }
            }
        }
    }

    override fun guardar(item: Item) {
        when(item){
            is Armadura -> {
                val workingDirectory = System.getProperty("user.dir")
                val armaduraFormateada = "\nA ; ${item.nombre} ; ${item.parte} ; ${item.rareza}"
                File("$workingDirectory/Datos_Guardado/Vault.txt").appendText(armaduraFormateada, Charsets.UTF_8)
                terminal.println(brightGreen("${item.rarity(item.nombre)} stored successfully"))
            }
            else -> {
                val workingDirectory = System.getProperty("user.dir")
                val armaFormateada = "\nW ; ${item.nombre} ; ${item.arquetipo} ; ${item.tipoArma} ; ${item.elemento} ;${item.rareza}"
                File("$workingDirectory/Datos_Guardado/Vault.txt").appendText(armaFormateada)
                terminal.println(brightGreen("${item.rarity(item.nombre)} stored successfully"))
            }
        }
    }

    override fun preguntarParaGuardar(item: Item) {
        while (true) {
            terminal.println("Would you like to store ${item.rarity(item.nombre)}? (y / n)")
            val decision = readln().lowercase()
            when (decision) {
                "y", "yes" ->{
                    guardar(item)
                    break
                }
                "n", "no" -> {
                    terminal.println(brightRed("Armor sent to the DCV, rest in peace"))
                    break
                }
                else -> terminal.warning("Please, answer the requested prompt correctly")
            }
        }
    }

    override fun preguntarParaEquipar(item: Item): Boolean {
        terminal.println(brightWhite("Do you wish to equip: ${item.rarity(item.nombre)}? (y / n)"))
        val respuesta = readln().lowercase()
        while (true) {
            when (respuesta) {
                "y", "yes" -> return true
                "n", "no" -> return false
                else -> terminal.warning("Please, answer the requested prompt correctly")
            }
        }
    }

    override fun equipar(item: Item, personaje: Personaje) {
        when(item){
            is  Armadura -> {
                personaje.armaduraEquipada.add(item)
                terminal.println(brightGreen("Armor equipped successfully"))
            }
            else -> {
                personaje.armaEquipada.add(item as Arma)
                terminal.println(brightGreen("Armor equipped successfully"))
            }
        }
    }

    override fun sustituir(item: Item, personaje: Personaje) {
        when(item){
            is Armadura -> {
                while(true) {
                    terminal.println(brightWhite("Exchange armor? (y / n)"))
                    val decision = readln().lowercase() //String para obtener la respuesta del usuario
                    var posicionArmadura = 0 //Entero para iterar en la lista de Armaduras

                    when(decision) {
                        "y", "yes" -> {
                            repeat(personaje.armaduraEquipada.size) {
                                if (personaje.armaduraEquipada[posicionArmadura].parte == item.parte) {
                                    val armaduraPrevia = personaje.armaduraEquipada[posicionArmadura] //Armadura equipada actualmente en el personaje
                                    personaje.armaduraEquipada.remove(personaje.armaduraEquipada[posicionArmadura])
                                    personaje.armaduraEquipada.add(item)
                                    terminal.println(brightGreen("Armor equipped successfully"))
                                    preguntarParaGuardar(armaduraPrevia)
                                }
                                posicionArmadura++
                            }
                        }
                        "n", "no" -> {
                            preguntarParaGuardar(item)
                        }
                        else -> terminal.warning("Please, answer the requested prompt correctly")
                    }
                }
            }
            else -> {
                var seleccionValida1 = false // Booleano para abrir y cerrar el primer bucle while, indica si el usuario ha ingresado una entrada válida
                var seleccionValida2 = false // Booleano para abrir y cerrar el segundo bucle while, indica si el usuario ha ingresado una entrada válida
                if (personaje.armaEquipada.size == 3) {
                    while (!seleccionValida1) {
                        println("Exchange weapon? (y / n)")
                        val decision = readln().lowercase() // String para obtener la respuesta del usuario
                        var cont = 1 // Entero para mostrar por pantalla
                        when (decision) {
                            "y", "yes" -> {
                                personaje.armaEquipada.forEach {
                                    println("$cont - ")
                                    mostrarInformacion(it)
                                    cont++
                                }
                                while (!seleccionValida2){
                                    terminal.println(brightWhite("Select a weapon slot :"))
                                    val slot = readln()
                                    when(slot){
                                        "1" -> {
                                            val armaAguardar = personaje.armaEquipada[0]
                                            personaje.armaEquipada.remove(armaAguardar)
                                            personaje.armaEquipada[0] = item as Arma
                                            preguntarParaGuardar(armaAguardar)
                                            seleccionValida2 = true
                                            seleccionValida1 = true
                                        }
                                        "2" -> {
                                            val armaAguardar = personaje.armaEquipada[1]
                                            personaje.armaEquipada.remove(armaAguardar)
                                            personaje.armaEquipada[1] = item as Arma
                                            preguntarParaGuardar(armaAguardar)
                                            seleccionValida2 = true
                                            seleccionValida1 = true
                                        }
                                        "3" -> {
                                            val armaAguardar = personaje.armaEquipada[2]
                                            personaje.armaEquipada.remove(armaAguardar)
                                            personaje.armaEquipada[2] = item as Arma
                                            preguntarParaGuardar(armaAguardar)
                                            seleccionValida2 = true
                                            seleccionValida1 = true
                                        }
                                        else -> {
                                            terminal.warning("Please, answer the requested prompt correctly")
                                        }
                                    }
                                }
                            }
                            "n", "no" -> {
                                preguntarParaGuardar(item)
                                seleccionValida2 = true
                                seleccionValida1 = true
                            }
                            else -> terminal.warning("Please, answer the requested prompt correctly")
                        }
                    }
                }
                else{
                    personaje.armaEquipada.add(item as Arma)
                }
            }
        }
    }

    override fun mostrarInformacion(item: Item) {
        when (item){
            is Armadura -> terminal.println("${rarity(nombre)} -- ${rarity(rareza)} $parte")
            else -> terminal.println("${rarity(nombre)} -- ${colorElemento(elemento.desc)} -- ${rarity(rareza)} $tipoArma \n -------------- $arquetipo")
        }
    }
}
