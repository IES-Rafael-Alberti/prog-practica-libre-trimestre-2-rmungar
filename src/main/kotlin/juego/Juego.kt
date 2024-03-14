package org.practicatrim2.juego

import org.practicatrim2.items.ActionFigure
import org.practicatrim2.items.Armadura
import org.practicatrim2.items.Item
import org.practicatrim2.modosDeJuego.GameModes
import org.practicatrim2.personajes.*
import java.io.File
import kotlin.random.Random

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
            // Solicita al gestor de consola que muestre un marcador para la entrada de texto.
            GestorConsola.marcadorEntradaTexto()

            // Obtiene la entrada de la sección de juego del usuario a través del gestor de entrada.
            val seleccion = GestorEntrada.pedirEntradaDeSeccionDeJuego()

            // Realiza una acción dependiendo de la selección del usuario.
            when (seleccion) {
                // Si la selección es "1" o "c", devuelve 1 para la sección de juego "c" (crear).
                "1", "c" -> return 1
                // Si la selección es "2" o "s", devuelve 2 para la sección de juego "s" (seleccionar).
                "2", "s" -> return 2
                // Si la selección es vacía, "e", o "exit", devuelve 3 para salir del juego.
                "", "e", "exit" -> return 3
                // Si la selección no coincide con ninguna de las anteriores, muestra un mensaje de entrada incorrecta.
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
            // Muestra el texto de selección de clase en la consola.
            GestorConsola.mostrarTextoSeleccionClase()

            // Obtiene la clase de personaje ingresada por el usuario a través del gestor de entrada.
            val claseAcrear = GestorEntrada.pedirClaseDePersonaje()

            // Realiza una acción dependiendo de la clase seleccionada por el usuario.
            when (claseAcrear) {
                // Si la clase seleccionada es "TITAN", devuelve la clase TITAN.
                "TITAN" -> return Clases.TITAN
                // Si la clase seleccionada es "HUNTER", devuelve la clase HUNTER.
                "HUNTER" -> return Clases.HUNTER
                // Si la clase seleccionada es "WARLOCK", devuelve la clase WARLOCK.
                "WARLOCK" -> return Clases.WARLOCK
                // Si la clase seleccionada no coincide con ninguna de las anteriores,
                // muestra un mensaje de entrada incorrecta.
                else -> GestorConsola.mostrarEntradaIncorrecta()
            }
        }
    }

    /**
     * Función para generar un nuevo personaje.
     * @return Un objeto de tipo Personaje generado según la clase seleccionada por el usuario.
     */
    fun generarPersonaje(): Personaje {
        // Muestra en la consola la información sobre la clase de personaje disponible.
        GestorConsola.mostrarClasePersonaje()
        // Muestra en la consola información detallada sobre las clases de personaje disponibles.
        GestorConsola.mostrarInformacionClases()
        // Selecciona la clase del personaje mediante la función selectorClasePersonaje().
        val clase = selectorClasePersonaje()
        // Crea un nuevo personaje utilizando la clase seleccionada.
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
        // Pide al usuario que ingrese el nombre del personaje.
        val nombre = pedirNombrePersonaje()
        // Pide al usuario que ingrese el género del personaje.
        val genero = pedirGeneroPersonaje()
        // LLlama la función selectorRazaPersonaje() para seleccionar la raza del personaje.
        val raza = selectorRazaPersonaje()
        // Crea un nuevo personaje según la clase ingresada.
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
        // "Limpia" la consola
        GestorConsola.separador()
        // Muestra en la consola la solicitud de entrada de nombre de personaje.
        GestorConsola.mostrarEntradaDeNombre()
        // Solicita al usuario que ingrese el nombre del personaje y lo almacena.
        var nombre = GestorEntrada.pedirNombreDePersonaje()
        // Mientras el nombre ingresado esté vacío, sea nulo o contenga solo espacios en blanco, vuelve a solicitar la entrada del usuario.
        while (nombre == "" || nombre.isEmpty() || nombre.isBlank()) {
            // Muestra un mensaje de entrada incorrecta en la consola.
            GestorConsola.mostrarEntradaIncorrecta()
            // Muestra nuevamente la solicitud de entrada de nombre de personaje.
            GestorConsola.mostrarEntradaDeNombre()
            // Vuelve a solicitar al usuario que ingrese el nombre del personaje y actualiza la variable "nombre".
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
        println() // Imprime una línea en blanco para mejorar la legibilidad.
        while (true) {
            // Muestra en la consola la solicitud de entrada de género del personaje.
            GestorConsola.mostrarEntradaDeGenero()
            // Solicita al usuario que ingrese el género del personaje y lo almacena en la variable "genero".
            val genero = GestorEntrada.pedirGeneroDePersonaje()
            // Realiza una acción dependiendo del género ingresado por el usuario.
            when (genero) {
                // Si el género es "Male" o "M", devuelve "Male".
                "Male", "M" -> return "Male"
                // Si el género es "Female" o "F", devuelve "Female".
                "Female", "F" -> return "Female"
                // Muestra un mensaje de entrada incorrecta, en caso de que sea necesario.
                else -> GestorConsola.mostrarEntradaIncorrecta()
            }
        }
    }

    /**
     * Función para seleccionar la raza del personaje.
     * @return Una instancia de la enumeración Razas que representa la raza seleccionada por el usuario.
     */
    private fun selectorRazaPersonaje(): Razas {
        // Muestra en la consola la información sobre las razas de personaje disponibles.
        GestorConsola.mostrarRazaPersonaje()
        GestorConsola.mostrarInformacionRazas()

        // Ciclo para mantener la solicitud de selección de raza hasta que se haga una selección válida.
        while (true) {
            println() // Imprime una línea en blanco para mejorar la legibilidad.
            // Muestra en la consola el texto de selección de raza.
            GestorConsola.mostrarTextoSeleccionRaza()
            // Solicita al usuario que seleccione una raza y la almacena.
            val razaAcrear = GestorEntrada.pedirRazaDePersoanje()
            // Realiza una acción dependiendo de la raza seleccionada por el usuario.
            when (razaAcrear) {
                // Si la raza seleccionada es "HUMAN" o "H", devuelve la raza HUMAN.
                "HUMAN", "H" -> return Razas.HUMAN
                // Si la raza seleccionada es "AWOKEN" o "A", devuelve la raza AWOKEN.
                "AWOKEN", "A" -> return Razas.AWOKEN
                // Si la raza seleccionada es "EXO" o "E", devuelve la raza EXO.
                "EXO", "E" -> return Razas.EXO
                // Muestra un mensaje de entrada incorrecta en caso de que sea necesario
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
        // Utiliza la función useLines para leer el contenido del archivo de texto, convertirlo en una lista y seleccionar una aleatoriamente.
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
        // Define el modo de juego.
        val gameMode: GameModes = GameModes.GAMBIT
        // Muestra una introduccion para este modo de juego.
        GestorConsola.animacionGambito()
        println() // Imprime una línea en blanco para mejorar la legibilidad.
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
        // Define el modo de juego.
        val gameMode: GameModes = GameModes.NIGHTFALL
        // Muestra una introduccion para este modo de juego.
        GestorConsola.animacionOcaso()
        println() // Imprime una línea en blanco para mejorar la legibilidad.
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
        // Define el modo de juego.
        val gameMode: GameModes = GameModes.RAIDS_DUNGEONS
        // Muestra una introduccion para este modo de juego.
        GestorConsola.animacionRyD()
        println() // Imprime una línea en blanco para mejorar la legibilidad.
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
        // Define el modo de juego.
        val gameMode: GameModes = GameModes.TRIALS
        // Muestra una introduccion para este modo de juego.
        GestorConsola.animacionTrials()
        println() // Imprime una línea en blanco para mejorar la legibilidad.
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
            // Obtiene la ruta del archivo de texto que contiene los Easter Eggs.
            val archivoEasterEggs = File("$workingDirectory/Loot_Pool/EasterEggs.txt")
            // Utiliza la función useLines para leer el contenido del archivo de texto, convertirlo en una lista de líneas y obtener una aleatoria.
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
            // Muestra en la consola la solicitud de entrada del ID del arma a extraer del Vault.
            GestorConsola.mostrarEntradaDeId()
            // Solicita al usuario que ingrese el ID del arma a extraer del Vault.
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
                // Si se ingresa un ID inválido, muestra un mensaje de entrada incorrecta.
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
            // Borra el archivo del Vault.
            ficheroVault.delete()
            // Crea un nuevo archivo del Vault.
            ficheroVault.createNewFile()
            // Remueve el item de la lista del Vault.
            vault.remove(item)
            // Procesa y guarda nuevamente todos los items restantes en el Vault.
            vault.forEach {
                val itemProcesado = Item.procesarItem(it)
                itemProcesado.guardar(itemProcesado)
            }
        }
    }
}
