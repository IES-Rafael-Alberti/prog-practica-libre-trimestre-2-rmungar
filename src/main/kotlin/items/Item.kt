package org.practicatrim2.items

import com.github.ajalt.mordant.rendering.TextColors
import com.github.ajalt.mordant.rendering.TextColors.*
import com.github.ajalt.mordant.rendering.TextStyle
import com.github.ajalt.mordant.terminal.Terminal
import org.practicatrim2.juego.GestionJuego
import org.practicatrim2.juego.GestorConsola
import org.practicatrim2.personajes.Personaje
import java.io.File


/**
 * Interfaz para gestionar la creación y clasificación de ítems.
 */
interface GestorItem {
    /**
     * Procesa un string de entrada y lo convierte en un objeto Item.
     * @param item El string que representa el ítem a procesar.
     * @return El ítem procesado como un objeto Item.
     */
    fun procesarItem(item: String): Item

    /**
     * Clasifica una lista de strings que representa un ítem y lo convierte en un objeto Item.
     * @param itemAprocesar La lista de strings que representa el ítem a clasificar.
     * @return El ítem clasificado como un objeto Item.
     */
    fun clasificarItem(itemAprocesar: List<String>): Item

    /**
     * Obtiene el elemento y el estilo de texto asociado a partir de los datos de un ítem.
     * @param itemAprocesar La lista de strings que representa el ítem del cual se obtendrá el elemento.
     * @return Un par que contiene el elemento del ítem (Elementos) y el estilo de texto (TextStyle).
     */
    fun obtenerElemento(itemAprocesar: List<String>): Pair<Elementos, TextStyle>

    /**
     * Obtiene el estilo de texto asociado a la rareza de un ítem.
     * @param itemAprocesar La lista de strings que representa el ítem del cual se obtendrá la rareza.
     * @return El estilo de texto (TextStyle) asociado a la rareza del ítem.
     */
    fun obtenerRareza(itemAprocesar: List<String>): TextStyle
}

/**
 * Clase base para representar un ítem en el juego.
 *
 * @property nombre El nombre del ítem.
 * @property parte La parte del cuerpo asociada al ítem (en caso de ser una armadura).
 * @property rareza La rareza del ítem.
 * @property rarity El estilo de texto que representa la rareza del ítem.
 * @property arquetipo El arquetipo del ítem (en caso de ser un arma).
 * @property tipoArma El tipo de arma (en caso de ser un arma).
 * @property elemento El elemento asociado al ítem (en caso de ser un arma elemental).
 * @property colorElemento El estilo de texto que representa el color del elemento del arma.
 */
open class Item: Equipable<Item>, Sustituible<Item, Personaje>, Guardable<Item> {
    open var nombre: String = ""
    open var parte: String = ""
    open var rareza: String = ""
    open var rarity: TextStyle = TextColors.rgb("#9500ff")
    open var arquetipo:String = ""
    open var tipoArma: String = ""
    open var elemento: Elementos = Elementos.KINETIC
    open var colorElemento:TextStyle = brightWhite
    private val terminal = Terminal()

    /**
     * Implementación de la interfaz GestorItem para procesar y clasificar ítems.
     */
   companion object:GestorItem{
       /**
        * Procesa un string que representa un ítem y lo convierte en un objeto Item.
        * @param item El string que representa el ítem a procesar.
        * @return El ítem procesado como un objeto Item.
        */
       override fun procesarItem(item: String): Item {
           // Divide el string del ítem en una lista de strings
           val itemAprocesar = item.split(" ; ")
           val itemProcesado = clasificarItem(itemAprocesar)
           // Retorna el ítem procesado.
           return itemProcesado
       }

       /**
        * Clasifica una lista de strings que representa un ítem y lo convierte en un objeto Item.
        * @param itemAprocesar La lista de strings que representa el ítem a clasificar.
        * @return El ítem clasificado como un objeto Item.
        */
       override fun clasificarItem(itemAprocesar: List<String>): Item {
           // Verifica si el ítem es un arma ("W") o una armadura (A).
           if (itemAprocesar[0] == "W") {
               val elemento = obtenerElemento(itemAprocesar).first // Elemento del arma
               val colorElemento = obtenerElemento(itemAprocesar).second // Obtiene la rareza del arma.
               val rarity = obtenerRareza(itemAprocesar) // Patrón de color para la terminal de acuerdo a la rareza del arma
               // Crea un objeto Arma con los datos proporcionados.
               val weapon = Arma(itemAprocesar[1], itemAprocesar[2], itemAprocesar[3], elemento, itemAprocesar[5], rarity, colorElemento)
               // Retorna el arma creada.
               return weapon
           } else {
               val rarity = obtenerRareza(itemAprocesar)
               // Crea un objeto Armadura con los datos proporcionados.
               val armor = Armadura(itemAprocesar[1], itemAprocesar[2], itemAprocesar[3], rarity)
               // Retorna la armadura creada.
               return armor
           }
       }

       /**
        * Obtiene el elemento y el estilo de texto asociado a partir de los datos de un ítem.
        * @param itemAprocesar La lista de strings que representa el ítem del cual se obtendrá el elemento.
        * @return Un par que contiene el elemento del ítem (Elementos) y el estilo de texto (TextStyle) asociado.
        */
       override fun obtenerElemento(itemAprocesar: List<String>): Pair<Elementos, TextStyle> {
           // Determina el elemento del ítem basado en el cuarto elemento de la lista.
           when (itemAprocesar[4]) {
               "Void" -> return Pair(Elementos.VOID, TextColors.rgb("#9500ff")) // Si el elemento es Void
               "Solar" -> return Pair(Elementos.SOLAR, brightRed) // Si el elemento es Solar
               "Arc" -> return Pair(Elementos.ARC, brightCyan) // Si el elemento es Arc
               "Strand" -> return Pair(Elementos.STRAND, green) // Si el elemento es Strand
               "Stasis" -> return Pair(Elementos.STASIS, blue) // Si el elemento es Stasis
           }
           // Si no se encuentra un elemento válido, se devuelve el elemento KINETIC con un estilo de texto blanco brillante.
           return Pair(Elementos.KINETIC, brightWhite)
       }

       /**
        * Obtiene el estilo de texto asociado a la rareza de un ítem.
        * @param itemAprocesar La lista de strings que representa el ítem del cual se obtendrá la rareza.
        * @return El estilo de texto (TextStyle) asociado a la rareza del ítem.
        */
       override fun obtenerRareza(itemAprocesar: List<String>): TextStyle {
           // Comprueba la rareza del ítem basada en el último elemento de la lista.
           when (itemAprocesar[itemAprocesar.size - 1]) {
               "Exotic" -> return TextColors.rgb("#bf8506") // Si la rareza es Exótica
           }
           return TextColors.rgb("#9500ff")
       }
   }


    /**
     * Verifica si un ítem es equipable en función de los ítems ya equipados.
     * @param itemsEquipados Lista de ítems ya equipados.
     * @return true si el ítem es equipable, false de lo contrario.
     */
    override fun equipable(itemsEquipados: MutableList<Item>): Boolean {
        // Verifica si la lista de ítems equipados no está vacía.
        if (itemsEquipados.isNotEmpty()) {
            // Comprueba el tipo del primer ítem equipado.
            when (itemsEquipados[0]) {
                is Armadura -> {
                    if (itemsEquipados.size >= 5) {
                        // Muestra un mensaje de advertencia si ya hay 5 armaduras equipadas.
                        GestorConsola.mostrarExcesoArmaduras()
                        return false
                    }
                    return true
                }
                else -> {
                    if (itemsEquipados.size >= 3) {
                        // Muestra un mensaje de advertencia si ya hay 3 armas equipadas.
                        GestorConsola.mostrarExcesoArmas()
                        return false
                    }
                    return true
                }
            }
        } else {
            // Si la lista de ítems equipados está vacía, devuelve true.
            return true
        }
    }

    /**
     * Guarda un ítem en el archivo del Vault.
     * @param item El ítem a guardar.
     */
    override fun guardar(item: Item) {
        val ficheroVault = File("${GestionJuego.workingDirectory}/Datos_Guardado/Vault.txt")        // Ruta al archivo del Vault.
        // Verifica el tipo del ítem y realiza la acción correspondiente.
        when (item) {
            is Armadura -> {
                // Formatea la armadura.
                val armaduraFormateada = if (ficheroVault.useLines { it.toList() }.isEmpty()) {
                    "A ; ${item.nombre} ; ${item.parte} ; ${item.rareza}"
                } else {
                    "\nA ; ${item.nombre} ; ${item.parte} ; ${item.rareza}"
                }
                // Agrega la armadura al archivo del Vault.
                ficheroVault.appendText(armaduraFormateada)
                GestorConsola.mostrarObjetoGuardado(item)
            }
            else -> {
                // Formatea el arma.
                val armaFormateada = if (ficheroVault.useLines { it.toList() }.isEmpty()) {
                    "W ; ${item.nombre} ; ${item.arquetipo} ; ${item.tipoArma} ; ${item.elemento} ; ${item.rareza}"
                } else {
                    "\nW ; ${item.nombre} ; ${item.arquetipo} ; ${item.tipoArma} ; ${item.elemento} ; ${item.rareza}"
                }
                // Agrega el arma al archivo del Vault.
                ficheroVault.appendText(armaFormateada)
                GestorConsola.mostrarObjetoGuardado(item)
            }
        }
    }

    /**
     * Pregunta al usuario si desea guardar un ítem en el Vault.
     * @param item El ítem que se está considerando guardar.
     */
    override fun preguntarParaGuardar(item: Item) {
        while (true) {

            GestorConsola.preguntarParaGuardar(item)
            val decision = readln().lowercase()
            // Realiza acciones basadas en la decisión del usuario.
            when (decision) {
                "y", "yes" -> {
                    // Si el usuario decide guardar el ítem, llama a la función guardar y sale del bucle.
                    guardar(item)
                    break
                }
                "n", "no" -> {
                    GestorConsola.mostrarObjetoEliminado()
                    break
                }
                else -> {
                    GestorConsola.mostrarEntradaIncorrecta()
                }
            }
        }
    }

    /**
     * Pregunta al usuario si desea equipar un ítem en el personaje.
     * @param item El ítem que se está considerando equipar.
     * @param personaje El personaje al que se le está ofreciendo equipar el ítem.
     * @return Devuelve true si el usuario decide equipar el ítem, de lo contrario, devuelve false.
     */
    override fun preguntarParaEquipar(item: Item, personaje: Personaje): Boolean {
        while (true) {
            // Selecciona el tipo de mensaje a mostrar según el tipo de ítem.
            when (item) {
                is Armadura -> {
                    GestorConsola.preguntarParaEquiparArmadura(item)
                }
                else -> {
                    GestorConsola.preguntarParaEquiparArma(item)
                }
            }

            val respuesta = readln().lowercase()

            // Realiza acciones basadas en la respuesta del usuario.
            when (respuesta) {
                "y", "yes" -> {
                    // Si el usuario decide equipar el ítem, llama a la función equipar y devuelve true.
                    equipar(item, personaje)
                    return true
                }
                "n", "no" -> {
                    GestorConsola.mostrarObjetoEliminado()
                    return false
                }
                else -> {
                    GestorConsola.mostrarEntradaIncorrecta()
                }
            }
        }
    }

    /**
     * Equipa un ítem en el personaje.
     * @param item El ítem que se va a equipar.
     * @param personaje El personaje en el que se va a equipar el ítem.
     */
    override fun equipar(item: Item, personaje: Personaje) {
        // Verifica el tipo de ítem y realiza las acciones correspondientes.
        when (item) {
            is Armadura -> {
                // Si el ítem es una armadura, lo añade a la lista de armaduras equipadas del personaje.
                personaje.armaduraEquipada.add(item)
                GestorConsola.mostrarArmaduraEquipada()
            }
            is Arma -> {
                // Si el ítem es un arma, lo añade a la lista de armas equipadas del personaje.
                personaje.armaEquipada.add(item)
                GestorConsola.mostrarArmaEquipada()
            }
        }
    }

    /**
     * Sustituye un ítem equipado por otro en el personaje.
     * @param item El ítem que se va a equipar en lugar del ítem actual.
     * @param personaje El personaje en el que se va a sustituir el ítem.
     */
    override fun sustituir(item: Item, personaje: Personaje) {
        // Verifica el tipo de ítem y realiza las acciones correspondientes.
        when(item){
            is Armadura -> {
                // Si el ítem es una armadura, pregunta al usuario si desea intercambiarla.
                while(true) {
                    GestorConsola.preguntarParaCambiarArmadura()
                    val decision = readln().lowercase() // Obtiene la respuesta del usuario
                    var posicionArmadura = 0 // Inicializa un contador para iterar en la lista de armaduras equipadas
                    when(decision) {
                        "y", "yes" -> {
                            // Si el usuario desea intercambiar la armadura, busca la armadura actual en la lista de armaduras equipadas.
                            repeat(personaje.armaduraEquipada.size) {
                                if (personaje.armaduraEquipada[posicionArmadura].parte == item.parte) {
                                    // Si encuentra la armadura actual, la reemplaza por la nueva armadura.
                                    val armaduraPrevia = personaje.armaduraEquipada[posicionArmadura] // Guarda la armadura que se va a reemplazar
                                    personaje.armaduraEquipada.remove(personaje.armaduraEquipada[posicionArmadura])
                                    personaje.armaduraEquipada.add(item)
                                    GestorConsola.mostrarArmaduraEquipada()
                                    preguntarParaGuardar(armaduraPrevia)
                                }
                                posicionArmadura++
                            }
                            break
                        }
                        "n", "no" -> {
                            // Si el usuario no desea intercambiar la armadura, pregunta si desea guardar el ítem nuevo.
                            preguntarParaGuardar(item)
                            break
                        }
                        else -> GestorConsola.mostrarEntradaIncorrecta()
                    }
                }
            }
            else -> {
                // Si el ítem es un arma, pregunta al usuario si desea intercambiarla.
                var seleccionValida1 = false // Indica si el usuario ha ingresado una entrada válida para el primer bucle while
                var seleccionValida2 = false // Indica si el usuario ha ingresado una entrada válida para el segundo bucle while
                while (!seleccionValida1) {
                    GestorConsola.preguntarParaCambiarArma()
                    val decision = readln().lowercase() // Obtiene la respuesta del usuario
                    var slotArma = 0 // Inicializa un contador para iterar en la lista de armas equipadas
                    when (decision) {
                        "y", "yes" -> {
                            // Si el usuario desea intercambiar el arma, muestra las armas equipadas y pregunta al usuario qué ranura desea reemplazar.
                            personaje.armaEquipada.forEach {
                                println(it)
                            }
                            while (!seleccionValida2){
                                GestorConsola.pedirSlot()
                                val slot = readln() // Obtiene la ranura seleccionada por el usuario
                                when(slot){
                                    "1" -> {
                                        // Si el usuario selecciona la ranura 1, reemplaza el arma en esa ranura por el nuevo ítem.
                                        val armaAguardar = personaje.armaEquipada[0] // Guarda el arma que se va a reemplazar
                                        personaje.armaEquipada[0] = item as Arma // Reemplaza el arma
                                        preguntarParaGuardar(armaAguardar) // Pregunta al usuario si desea guardar el arma anterior
                                        seleccionValida2 = true // Indica que se ha seleccionado una ranura válida y sale del segundo bucle while
                                        seleccionValida1 = true // Indica que se ha completado la operación y sale del primer bucle while
                                    }
                                    "2" -> {
                                        // Si el usuario selecciona la ranura 2, reemplaza el arma en esa ranura por el nuevo ítem.
                                        val armaAguardar = personaje.armaEquipada[1]
                                        personaje.armaEquipada[1] = item as Arma
                                        preguntarParaGuardar(armaAguardar)
                                        seleccionValida2 = true
                                        seleccionValida1 = true
                                    }
                                    "3" -> {
                                        // Si el usuario selecciona la ranura 3, reemplaza el arma en esa ranura por el nuevo ítem.
                                        val armaAguardar = personaje.armaEquipada[2]
                                        personaje.armaEquipada[2] = item as Arma
                                        preguntarParaGuardar(armaAguardar)
                                        seleccionValida2 = true
                                        seleccionValida1 = true
                                    }
                                    else -> {
                                        GestorConsola.mostrarEntradaIncorrecta()
                                    }
                                }
                            }
                        }
                        "n", "no" -> {
                            // Si el usuario no desea intercambiar el arma, pregunta si desea guardar el ítem nuevo.
                            preguntarParaGuardar(item)
                            seleccionValida2 = true // sale del segundo bucle while
                            seleccionValida1 = true // sale del primer bucle while
                        }
                        else -> GestorConsola.mostrarEntradaIncorrecta()
                    }
                }
            }
        }
    }
}
