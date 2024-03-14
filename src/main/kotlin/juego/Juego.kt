package org.practicatrim2.juego

import org.practicatrim2.items.ActionFigure
import org.practicatrim2.items.Armadura
import org.practicatrim2.items.Item
import org.practicatrim2.modosDeJuego.GameModes
import org.practicatrim2.personajes.*
import java.io.File
import kotlin.random.Random

/**
 * Clase que implementa Jugable y se centra en lo relacionado con el juego, pero solo lo que se encuentra "dentro" a
 * difrencia de Gesion Juego, que se encarga de toda la lógica detrás del juego.
 */

open class Juego : Jugable {

    val workingDirectory = System.getProperty("user.dir")!! // Directorio actual
    private val ficheroVault = File("$workingDirectory/Datos_Guardado/Vault.txt") // Fichero donde se guardan las armas y armaduras NO EQUIPADAS de una partida previa
    private val veces = 1 // Número de repeticiones del los bucles al jugar
    private val numeroEasterEgg = 33 // Valor predefinido para los easter eggs

    /**
     * Función para seleccionar una sección del juego.
     * @return Un entero que representa la sección seleccionada:
     *  - 1 para la sección de juego "c" (crear).
     *  - 2 para la sección de juego "s" (seleccionar).
     *  - 3 para salir del juego.
     */
    fun selectorSeccionJuego(): Int {
        println()
        while (true) {
            GestorConsola.marcadorEntradaTexto()
            val seleccion = GestorEntrada.pedirEntradaDeSeccionDeJuego()

            // Realiza una acción dependiendo de la selección del usuario.
            when (seleccion) {
                "1", "c" -> return 1
                "2", "s" -> return 2
                "", "e", "exit" -> return 3
                else -> GestorConsola.mostrarEntradaIncorrecta()
            }
        }
    }

    /**
     * Función para seleccionar la clase del personaje.
     * @return Una instancia de la enumeración Clases que representa la clase seleccionada por el usuario.
     */
    private fun selectorClasePersonaje(): Clases {
        while (true) {
            println()
            GestorConsola.mostrarTextoSeleccionClase()
            val claseAcrear = GestorEntrada.pedirClaseDePersonaje()

            when (claseAcrear) {
                // Si la clase seleccionada es "TITAN", devuelve la clase TITAN.
                "TITAN" -> return Clases.TITAN
                // Si la clase seleccionada es "HUNTER", devuelve la clase HUNTER.
                "HUNTER" -> return Clases.HUNTER
                // Si la clase seleccionada es "WARLOCK", devuelve la clase WARLOCK.
                "WARLOCK" -> return Clases.WARLOCK

                else -> GestorConsola.mostrarEntradaIncorrecta()
            }
        }
    }

    /**
     * Función para generar un nuevo personaje.
     * @return Un objeto de tipo Personaje generado según la clase seleccionada por el usuario.
     */
    fun generarPersonaje(): Personaje {
        GestorConsola.mostrarClasePersonaje()
        GestorConsola.mostrarInformacionClases()
        val clase = selectorClasePersonaje()
        val personaje = crearPersonaje(clase)
        // Retorna el personaje creado.
        return personaje
    }

    /**
     * Función para crear un nuevo personaje en función de la clase proporcionada.
     * @param clase La clase del personaje a crear.
     * @return Un objeto de tipo Personaje creado con el nombre, género y raza proporcionados.
     */
    private fun crearPersonaje(clase: Clases): Personaje {
        val nombre = pedirNombrePersonaje()
        val genero = pedirGeneroPersonaje()
        val raza = selectorRazaPersonaje()
        return when (clase) {
            // Si la clase es TITAN, crea un objeto de tipo Titan con el nombre, género y raza ingresados.
            Clases.TITAN -> {
                Titan(nombre, genero, raza)
            }
            // Si la clase es WARLOCK, crea un objeto de tipo Warlock con el nombre, género y raza ingresados.
            Clases.WARLOCK -> {
                Warlock(nombre, genero, raza)
            }
            // Si la clase es HUNTER, crea un objeto de tipo Hunter con el nombre, género y raza ingresados.
            Clases.HUNTER -> {
                Hunter(nombre, genero, raza)
            }
        }
    }

    /**
     * Función para solicitar y validar el nombre del personaje.
     * @return El nombre del personaje ingresado por el usuario.
     */
    private fun pedirNombrePersonaje(): String {
        GestorConsola.separador()
        GestorConsola.mostrarEntradaDeNombre()
        var nombre = GestorEntrada.pedirNombreDePersonaje()
        while (nombre == "" || nombre.isEmpty() || nombre.isBlank()) {
            GestorConsola.mostrarEntradaIncorrecta()
            GestorConsola.mostrarEntradaDeNombre()
            nombre = GestorEntrada.pedirNombreDePersonaje()
        }
        // Retorna el nombre del personaje ingresado comprobado.
        return nombre
    }

    /**
     * Función para solicitar y validar el género del personaje.
     * @return El género del personaje ingresado por el usuario (Male o Female).
     */
    private fun pedirGeneroPersonaje(): String {
        println()
        while (true) {
            GestorConsola.mostrarEntradaDeGenero()
            val genero = GestorEntrada.pedirGeneroDePersonaje()
            when (genero) {
                // Si el género es "Male" o "M", devuelve "Male".
                "Male", "M" -> return "Male"
                // Si el género es "Female" o "F", devuelve "Female".
                "Female", "F" -> return "Female"

                else -> GestorConsola.mostrarEntradaIncorrecta()
            }
        }
    }

    /**
     * Función para seleccionar la raza del personaje.
     * @return Una instancia de la enumeración Razas que representa la raza seleccionada por el usuario.
     */
    private fun selectorRazaPersonaje(): Razas {
        GestorConsola.mostrarRazaPersonaje()
        GestorConsola.mostrarInformacionRazas()

        // Ciclo para mantener la solicitud de selección de raza hasta que se haga una selección válida.
        while (true) {
            println()
            GestorConsola.mostrarTextoSeleccionRaza()
            val razaAcrear = GestorEntrada.pedirRazaDePersoanje()
            when (razaAcrear) {
                // Si la raza seleccionada es "HUMAN" o "H", devuelve la raza HUMAN.
                "HUMAN", "H" -> return Razas.HUMAN
                // Si la raza seleccionada es "AWOKEN" o "A", devuelve la raza AWOKEN.
                "AWOKEN", "A" -> return Razas.AWOKEN
                // Si la raza seleccionada es "EXO" o "E", devuelve la raza EXO.
                "EXO", "E" -> return Razas.EXO

                else -> GestorConsola.mostrarEntradaIncorrecta()
            }
        }
    }

    /**
     * Función para obtener un item aleatorio de un archivo de texto basado en el modo de juego proporcionado.
     * @param modoDeJuego El modo de juego del cual se desea obtener el item.
     * @return Un String que representa el item obtenido aleatoriamente del archivo de texto correspondiente al modo de juego.
     */
    private fun obtenerItem(modoDeJuego: GameModes): String {
        // Obtiene el directorio actual del sistema.
        val directorioActual = System.getProperty("user.dir")
        // Genera la ruta del archivo de texto que contiene los items del modo de juego especificado.
        val archivoItems = File("$directorioActual/Loot_Pool/${modoDeJuego.desc}.txt")
        val itemObtenido: String = archivoItems.useLines { it.toList() }.random()
        // Retorna el item obtenido.
        return itemObtenido
    }

    /**
     * Función para jugar al modo Gambito en el juego.
     * @param personaje El personaje que jugará el modo Gambito.
     * @param featured Indica si el modo Gambito está en modo destacado.
     */
    override fun jugarGambito(personaje: Personaje, featured: Boolean) {
        val gameMode: GameModes = GameModes.GAMBIT
        GestorConsola.animacionGambito()
        println()
        // Determina el número de repeticiones del juego.
        var rotations = veces
        if (featured) rotations++
        // Repite el proceso de juego el número de veces determinado.
        repeat(rotations) {
            // Genera una probabilidad aleatoria para encontrar un Easter Egg durante el juego.
            val probabilidadEasterEgg = Random.nextInt(1, 50)
            // Verifica si se encuentra un Easter Egg y realiza la acción correspondiente.
            encontrarEasterEgg(probabilidadEasterEgg)
            // Obtiene un item aleatorio para el modo Gambito.
            val itemObtenido = obtenerItem(gameMode)
            // Administra el item obtenido al personaje.
            administrarItem(itemObtenido, personaje)
        }
    }

    /**
     * Función para jugar al modo Ocaso en el juego.
     * @param personaje El personaje que jugará el modo Ocaso.
     * @param featured Indica si el modo Ocaso está en modo destacado.
     */
    override fun jugarOcaso(personaje: Personaje, featured: Boolean) {
        val gameMode: GameModes = GameModes.NIGHTFALL
        GestorConsola.animacionOcaso()
        println()
        // Determina el número de repeticiones del juego.
        var rotations = veces
        if (featured) rotations++
        // Repite el proceso de juego el número de veces determinado.
        repeat(rotations) {
            // Genera una probabilidad aleatoria para encontrar un Easter Egg durante el juego.
            val probabilidadEasterEgg = Random.nextInt(1, 50)
            // Verifica si se encuentra un Easter Egg y realiza la acción correspondiente.
            encontrarEasterEgg(probabilidadEasterEgg)
            // Obtiene un item aleatorio para el modo Ocaso.
            val itemObtenido = obtenerItem(gameMode)
            // Administra el item obtenido al personaje.
            administrarItem(itemObtenido, personaje)
        }
    }

    /**
     * Función para jugar al modo Incursiones y Mazmorras en el juego.
     * @param personaje El personaje que jugará el modo Incursiones y Mazmorras.
     * @param featured Indica si el modo Incursiones y Mazmorras está en modo destacado.
     */
    override fun jugarRyD(personaje: Personaje, featured: Boolean) {
        val gameMode: GameModes = GameModes.RAIDS_DUNGEONS
        GestorConsola.animacionRyD()
        println()
        // Determina el número de repeticiones del juego.
        var rotations = veces
        if (featured) rotations++
        // Repite el proceso de juego el número de veces determinado.
        repeat(rotations) {
            // Genera una probabilidad aleatoria para encontrar un Easter Egg durante el juego.
            val probabilidadEasterEgg = Random.nextInt(1, 50)
            // Verifica si se encuentra un Easter Egg y realiza la acción correspondiente.
            encontrarEasterEgg(probabilidadEasterEgg)
            // Obtiene un item aleatorio para el modo Incursiones y Mazmorras.
            val itemObtenido = obtenerItem(gameMode)
            // Administra el item obtenido al personaje.
            administrarItem(itemObtenido, personaje)
        }
    }

    /**
     * Función para jugar al modo Trials en el juego.
     * @param personaje El personaje que jugará el modo Trials.
     * @param featured Indica si el modo Trials está en modo destacado.
     */
    override fun jugarTrials(personaje: Personaje, featured: Boolean) {
        val gameMode: GameModes = GameModes.TRIALS
        GestorConsola.animacionTrials()
        println()
        // Determina el número de repeticiones del juego.
        var rotations = veces
        if (featured) rotations++
        // Repite el proceso de juego el número de veces determinado.
        repeat(rotations) {
            // Genera una probabilidad aleatoria para encontrar un Easter Egg durante el juego.
            val probabilidadEasterEgg = Random.nextInt(1, 50)
            // Verifica si se encuentra un Easter Egg y realiza la acción correspondiente.
            encontrarEasterEgg(probabilidadEasterEgg)
            // Obtiene un item aleatorio para el modo Trials.
            val itemObtenido = obtenerItem(gameMode)
            // Administra el item obtenido al personaje.
            administrarItem(itemObtenido, personaje)
        }
    }

    /**
     * Función para encontrar un Easter Egg en el juego, si se cumple la probabilidad especificada.
     * @param probabilidadEasterEgg La probabilidad de encontrar un Easter Egg.
     */
    private fun encontrarEasterEgg(probabilidadEasterEgg: Int) {
        // Verifica si la probabilidad de encontrar un Easter Egg coincide con el número de Easter Egg predefinido.
        if (probabilidadEasterEgg == numeroEasterEgg) {
            val archivoEasterEggs = File("$workingDirectory/Loot_Pool/EasterEggs.txt")
            val huevoEncontrado = archivoEasterEggs.useLines { it.toList() }.random().split(" ; ")
            // Crea un objeto ActionFigure con la información del Easter Egg encontrado.
            val huevo = ActionFigure(huevoEncontrado[1], huevoEncontrado[2], huevoEncontrado[3], huevoEncontrado[4], huevoEncontrado[5])
            // Muestra en la consola el Easter Egg encontrado.
            GestorConsola.mostrarEasterEgg(huevo)
        }
    }

    /**
     * Función para administrar un item obtenido por el personaje.
     * @param itemObtenido El item obtenido por el personaje en forma de String.
     * @param personaje El personaje al que se le asignará o equipará el item.
     */
    private fun administrarItem(itemObtenido: String, personaje: Personaje) {
        // Procesa el item obtenido para convertirlo en un objeto de la clase correspondiente.
        when (val itemObtenidoProcesado = Item.procesarItem(itemObtenido)) {
            is Armadura -> {
                // Verifica si el usuario quiere equipar la armadura.
                if (itemObtenidoProcesado.preguntarParaEquipar(itemObtenidoProcesado, personaje)) {
                    // Verifica si la armadura se puede equipar y, en caso afirmativo, la equipa.
                    if (itemObtenidoProcesado.equipable(personaje.armaduraEquipada as MutableList<Item>)) {
                        itemObtenidoProcesado.equipar(itemObtenidoProcesado, personaje)
                    } else {
                        // Si no se puede equipar, sustituye el item actual por el nuevo.
                        itemObtenidoProcesado.sustituir(itemObtenidoProcesado, personaje)
                    }
                }
            }
            else -> {
                // Verifica si el usuario quiere equipar el arma.
                if (itemObtenidoProcesado.preguntarParaEquipar(itemObtenidoProcesado, personaje)) {
                    // Verifica si el arma se puede equipar y, en caso afirmativo, la equipa.
                    if (itemObtenidoProcesado.equipable(personaje.armaEquipada as MutableList<Item>)) {
                        itemObtenidoProcesado.equipar(itemObtenidoProcesado, personaje)
                    } else {
                        // Si no se puede equipar, sustituye el item actual por el nuevo.
                        itemObtenidoProcesado.sustituir(itemObtenidoProcesado, personaje)
                    }
                }
            }
        }
    }

    /**
     * Función para extraer un arma del Vault del personaje.
     * @param personaje El personaje del cual se extraerá el arma del Vault.
     */
    fun extraerArmaDeVault(personaje: Personaje) {
        while (true) {
            GestorConsola.mostrarEntradaDeId()
            when (val input = GestorEntrada.pedirOpcionExtraerVault()) {
                // Si el ID ingresado está dentro del rango válido, continua con la extracción del arma.
                in "1".."${ficheroVault.useLines { it.toList() }.size}" -> {
                    // Obtiene el arma del Vault según el ID ingresado.
                    val item = ficheroVault.useLines { it.toList() }[input.toInt() - 1]
                    // Actualiza el Vault, removiendo el arma extraída.
                    acctualizarVault(item)
                    // Administra el arma extraída al personaje.
                    administrarItem(item, personaje)
                    // Sale del bucle while después de extraer y administrar el arma.
                    break
                }
                // Si se ingresa una cadena vacía, sale del bucle while.
                "" -> break
                else -> GestorConsola.mostrarEntradaIncorrecta()
            }
        }
    }

    /**
     * Función para actualizar el contenido del Vault después de extraer un item.
     * @param item El item que se extrajo del Vault y debe ser eliminado de la lista.
     */
    private fun acctualizarVault(item: String) {
        // Lee el contenido actual del Vault y lo convierte en una lista mutable.
        val vault: MutableList<String> = ficheroVault.useLines { it.toMutableList() }
        // Busca un item del Vault.
        vault.find { it == item }?.let {
            ficheroVault.delete()
            ficheroVault.createNewFile()
            vault.remove(item)
            // Procesa y guarda nuevamente todos los items restantes en el Vault.
            vault.forEach {
                val itemProcesado = Item.procesarItem(it)
                itemProcesado.guardar(itemProcesado)
            }
        }
    }
}
