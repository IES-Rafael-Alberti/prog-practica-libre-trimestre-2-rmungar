package org.practicatrim2.juego


import org.practicatrim2.items.Arma
import org.practicatrim2.items.Armadura
import org.practicatrim2.items.Item
import org.practicatrim2.personajes.Hunter
import org.practicatrim2.personajes.Personaje
import org.practicatrim2.personajes.Titan
import org.practicatrim2.personajes.Warlock
import java.io.File
import kotlin.random.Random

object GestionJuego :Juego(), Comprobable<String> {


    private val FicheroArmaduras = File("$workingDirectory/Datos_Guardado/Armor_Set.txt") // Fichero donde se guardan las armaduras de una partida previa
    private val FicheroArmas = File("$workingDirectory/Datos_Guardado/Weapons_Set.txt") // Fichero donde se guardan las armas de una partida previa
    private val FicheroPersonaje = File("$workingDirectory/Datos_Guardado/Character.txt") // Fichero donde se guardan los datos del personaje de una partida previa



    /**
     * Función para iniciar el juego.
     * Esta función muestra la bienvenida, el menú de inicio, y luego procede con la ejecución de la acción inicial.
     */
    fun comenzarJuego() {
        GestorConsola.bienvenida()
        GestorConsola.mostrarMenuInicio()
        val datosExistentes = ejecutarAccionInicial() // verifica si existen datos guardados.
        // Si existen datos guardados, carga el personaje y comienza el juego.
        if (datosExistentes) {
            val personaje = cargarDatos()
            while (true) {
                jugar(personaje)
                break
            }
        }
        // Si no existen datos guardados, genera un nuevo personaje y comienza el juego.
        else {
            val personaje = generarPersonaje()
            while (true) {
                jugar(personaje)
                break
            }
        }
    }

    /**
     * Función para ejecutar la acción inicial del juego.
     * Esta función solicita al usuario que elija entre cargar datos previos, generar un nuevo juego o salir del juego.
     * @return Un valor booleano que indica si se cargaron datos previos (true) o se generó un nuevo juego (false).
     */
    private fun ejecutarAccionInicial(): Boolean {
        println()
        // Muestra en la consola la solicitud de entrada para la acción inicial.
        GestorConsola.mostrarEntradaDeAccionInicial()
        var accion = GestorEntrada.pedirEntradaInicial()
        // Verifica si la acción inicial ingresada es válida.
        while (comprobarAccion(accion) !in 1..3) {
            GestorConsola.mostrarEntradaDeAccionInicial()
            accion = GestorEntrada.pedirEntradaInicial()
        }
        // Realiza una acción dependiendo de la acción inicial
        when (accion) {
            "1" -> {
                // Si el usuario elige cargar datos previos y existen datos previos, carga los datos y retorna true.
                if (comprobarDatosPrevios()) {
                    cargarDatos()
                    return true
                }
                // Si no existen datos previos, genera un nuevo juego y retorna false.
                else {
                    generarNuevoJuego()
                    return false
                }
            }
            "2" -> {
                // Si el usuario elige generar un nuevo juego y existen datos previos, carga los datos y retorna true.
                if (comprobarDatosPrevios()) {
                    cargarDatos()
                    return true
                }
                // Si no existen datos previos, genera un nuevo juego y retorna false.
                else {
                    generarNuevoJuego()
                    return false
                }
            }
            else -> {
                // Si el usuario elige salir del juego, finaliza el juego y retorna false.
                return false
            }
        }
    }

    /**
     * Función para comprobar la acción inicial ingresada por el usuario.
     * @param accion La acción inicial ingresada por el usuario.
     * @return Un entero que indica el resultado de la comprobación:
     * - 1 si la acción es cargar datos previos,
     * - 2 si la acción es generar un nuevo juego,
     * - 3 si la acción es salir del juego,
     * - 4 si la acción no es válida.
     */
    override fun comprobarAccion(accion: String): Int {
        // Comprueba la acción inicial ingresada y retorna un valor dependiendo del caso.
        when (accion) {
            "1" -> return 1 // Cargar datos previos
            "2" -> return 2 // Generar un nuevo juego
            "3" -> return 3 // Salir del juego
            else -> {
                // Si la acción no es válida, muestra un mensaje de entrada incorrecta y retorna 4.
                GestorConsola.mostrarEntradaIncorrecta()
                return 4
            }
        }
    }

    /**
     * Función para comprobar la selección de modo de juego ingresada por el usuario.
     * @return El modo de juego seleccionado por el usuario como una cadena de texto.
     */
    override fun comprobarSeleccionModoJuego(): String {
        // Repite el proceso hasta que se seleccione un modo de juego válido.
        while (true) {
            GestorConsola.marcadorEntradaTexto()
            val entrada: String = GestorEntrada.pedirEntradaDeModoDeJuego()
            // Comprueba la selección del usuario y retorna el modo de juego correspondiente.
            when (entrada) {
                // Modo Gambit
                "1", "gambit", "gambit prime" -> return "Gambit"
                // Modo Nightfall
                "2", "nightfall", "grandmaster nightfall" -> return "Nightfall"
                // Modo Trials
                "3", "trials", "trials of osiris" -> return "Trials"
                // Modo Raids y Dungeons
                "4", "raids", "dungeons", "raids_dungeons", "raids and dungeons" -> return "Raids_Dungeons"
                // Guardar partida
                "s", "save" -> return "Save"
                // Salir del juego
                "e", "exit" -> return "Exit"

                else -> GestorConsola.mostrarEntradaIncorrecta()
            }
        }
    }

    /**
     * Función principal para jugar con un personaje.
     * @param personaje El personaje con el que se jugará.
     */
    private fun jugar(personaje: Personaje) {
        // Repite el proceso hasta que se seleccione un modo de juego válido.
        while (true) {
            GestorConsola.mostrarInterfazJuego()
            val modoPrincipal = selectorSeccionJuego()
            when (modoPrincipal) {
                // Modo 1
                1 -> {
                    // Juega el Modo 1 con el personaje y sale del bucle.
                    jugarModo1(personaje)
                    break
                }
                // Modo 2
                2 -> {
                    // Juega el Modo 2 con el personaje y sale del bucle.
                    jugarModo2(personaje)
                    break
                }
                else -> {
                    // Sale del bucle y termina la función.
                    break
                }
            }
        }
    }

    /**
     * Función para jugar en el Modo 1.
     * @param personaje El personaje con el que se jugará el Modo 1.
     */
    private fun jugarModo1(personaje: Personaje) {
        // Repite el proceso hasta que se tome una decisión válida.
        while (true) {
            GestorConsola.mostrarInterfazPersonaje(personaje)
            GestorConsola.mostrarOpcionAbrirVault()
            val decision = GestorEntrada.pedirOpcionAccederVault()
            // Realiza una acción dependiendo de la decisión tomada por el usuario.
            when (decision) {
                // Si el usuario decide lo decide, muestra el Vault y permite extraer un arma.
                "y", "yes" -> {
                    GestorConsola.mostrarVault()
                    extraerArmaDeVault(personaje)
                }
                // Si no permite salir del Modo 1.
                "n", "no" -> {
                    GestorConsola.marcadorEntradaTexto()
                    val entrada = GestorEntrada.pedirEnterParaSalir()
                    if (entrada == "") break // Si se presiona Enter, sale del bucle.
                }
                // Si no se ingresa nada (se deja en blanco), sale del Modo 1.
                "" -> break
            }
        }
    }

    /**
     * Función para jugar en el Modo 2.
     * @param personaje El personaje con el que se jugará el Modo 2.
     */
    private fun jugarModo2(personaje: Personaje) {
        // Repite el proceso hasta que se salga del Modo 2.
        while (true) {
            var featured = false
            val focusedMode = Random.nextInt(1, 5)
            GestorConsola.mostrarMenuModosJuego()
            val modo = comprobarSeleccionModoJuego()
            // Realiza una acción dependiendo del modo de juego seleccionado.
            when (modo) {
                // Gambit
                "Gambit" -> {
                    // Si el modo focused coincide con Gambit, se marca como "featured".
                    if (focusedMode == 1) featured = true
                    GestionJuego.jugarGambito(personaje, featured)
                }
                // Nightfall
                "Nightfall" -> {
                    // Si el modo focused coincide con Nightfall, se marca como "featured".
                    if (focusedMode == 2) featured = true
                    GestionJuego.jugarOcaso(personaje, featured)
                }
                // Raids y Dungeons
                "Raids_Dungeons" -> {
                    // Si el modo focused coincide con Raids y Dungeons, se marca como "featured".
                    if (focusedMode == 3) featured = true
                    GestionJuego.jugarRyD(personaje, featured)
                }
                // Trials
                "Trials" -> {
                    // Si el modo focused coincide con Trials, se marca como "featured".
                    if (focusedMode == 4) featured = true
                    GestionJuego.jugarTrials(personaje, featured)
                }
                "Save" -> {
                    // Guarda los datos del personaje.
                    guardarDatos(personaje)
                }
                // Si no se ingresa nada (se deja en blanco), sale del Modo 2.
                "" -> break
                else -> GestorConsola.mostrarEntradaIncorrecta()
            }
        }
    }

    /**
     * Función para comprobar si existen datos previos del juego.
     * @return Un valor booleano que indica si existen datos previos (true) o no (false).
     */
    override fun comprobarDatosPrevios(): Boolean {
        // Comprueba si existen datos de armaduras, armas y personaje.
        val datosArmaduras = comprobarDatosArmaduras()
        val datosArmas = comprobarDatosArmas()
        val datosPersonaje = comprobarDatosPersonaje()
        if (!datosArmaduras && !datosArmas && !datosPersonaje) {
            while (true) {
                // Muestra un mensaje indicando que no existen datos previos y solicita una decisión al usuario.
                GestorConsola.mostrarExistenciaDatosPrevios()
                val decision = GestorEntrada.pedirDecisionDatos()
                // Realiza una acción dependiendo de la decisión tomada por el usuario.
                when (decision) {
                    // Si el usuario decide empezar un nuevo juego, genera un nuevo juego y retorna true.
                    "y", "yes" -> {
                        GestorConsola.separador()
                        GestorConsola.sobreescribiendoDatos()
                        GestorConsola.datosSobreescritos()
                        generarNuevoJuego()
                        return true
                    }
                    // Si el usuario decide cargar datos previos, carga los datos y retorna true.
                    "n", "no" -> {
                        GestorConsola.separador()
                        GestorConsola.cargandoDatos()
                        cargarDatos()
                        return true
                    }
                    else -> {
                        GestorConsola.mostrarEntradaIncorrecta()
                    }
                }
            }
        } else {
            // Si existen datos previos, se solicita al usuario que tome otra decisión.
            GestorConsola.mostrarNoExistenciaDatosPrevios()
            val decision = GestorEntrada.pedirDecisionDatos()
            // Realiza una acción dependiendo de la decisión tomada por el usuario.
            when (decision) {
                // Si el usuario decide empezar un nuevo juego, genera un nuevo juego y muestra mensajes correspondientes.
                "y", "yes" -> {
                    generarNuevoJuego()
                    GestorConsola.creandoDatos()
                    GestorConsola.cargandoDatos()
                    return false
                }
                // Si el usuario decide salir del juego, finaliza el juego.
                "n", "no" -> {
                    acabarJuego()
                }
            }
        }
        return false
    }

    /**
     * Función para comprobar si existen datos de armaduras previos.
     * @return Un valor booleano que indica si existen datos de armaduras previos (true) o no (false).
     */
    override fun comprobarDatosArmaduras(): Boolean {
        // Lee los datos de armaduras del fichero y los almacena en una lista.
        val datosArmadura = FicheroArmaduras.useLines { it.toList() }
        return datosArmadura.isEmpty()
    }

    /**
     * Función para comprobar si existen datos de armas previos.
     * @return Un valor booleano que indica si existen datos de armas previos (true) o no (false).
     */
    override fun comprobarDatosArmas(): Boolean {
        // Lee los datos de armas del fichero y los almacena en una lista.
        val datosArmas = FicheroArmas.useLines { it.toList() }
        return datosArmas.isEmpty()
    }

    /**
     * Función para comprobar si existen datos de personaje previos.
     * @return Un valor booleano que indica si existen datos de personaje previos (true) o no (false).
     */
    override fun comprobarDatosPersonaje(): Boolean {
        // Lee los datos de personaje del fichero y los almacena en una lista.
        val datosPersonaje = FicheroPersonaje.useLines { it.toList() }
        // Retorna true si la lista de datos de personaje está vacía, lo que indica que no existen datos previos de personaje.
        return datosPersonaje.isEmpty()
    }

    /**
     * Función para cargar los datos de personaje, armaduras y armas desde los ficheros.
     * @return El personaje creado a partir de los datos cargados.
     */
    private fun cargarDatos(): Personaje {
        val datosPersonaje = FicheroPersonaje.useLines { it.toList() }[0]
        val datosArmaduras = FicheroArmaduras.useLines { it.toList() }
        val datosArmas = FicheroArmas.useLines { it.toList() }
        // Genera un personaje a partir de los datos del primer personaje.
        val personaje = Personaje.generarPersonaje(datosPersonaje)

        // Itera sobre los datos de armaduras y los agrega al inventario de armaduras del personaje.
        datosArmaduras.forEach {
            val armaduraItem = Item.procesarItem(it)
            val armadura = Armadura(armaduraItem.nombre, armaduraItem.parte, armaduraItem.rareza, armaduraItem.rarity)
            personaje.armaduraEquipada.add(armadura)
        }

        // Itera sobre los datos de armas y las agrega al inventario de armas del personaje.
        datosArmas.forEach {
            val armaItem = Item.procesarItem(it)
            val arma = Arma(armaItem.nombre, armaItem.arquetipo, armaItem.tipoArma, armaItem.elemento, armaItem.rareza, armaItem.rarity, armaItem.colorElemento)
            personaje.armaEquipada.add(arma)
        }
        // Retorna el personaje cargado con los datos.
        return personaje
    }

    /**
     * Función para generar un nuevo juego, eliminando los ficheros de armaduras, armas y personaje existentes
     * y creando nuevos ficheros vacíos para empezar un juego desde cero.
     */
    private fun generarNuevoJuego() {
        // Elimina los ficheros existentes
        FicheroArmaduras.delete()
        FicheroArmas.delete()
        FicheroPersonaje.delete()

        // Crea nuevos ficheros
        FicheroArmaduras.createNewFile()
        FicheroPersonaje.createNewFile()
        FicheroArmas.createNewFile()
    }

    /**
     * Función para guardar los datos del personaje en los ficheros correspondientes.
     * @param personaje El personaje cuyos datos se van a guardar.
     */
    private fun guardarDatos(personaje: Personaje) {
        // Guarda los datos de las armas equipadas del personaje en el fichero de armas.
        personaje.armaEquipada.forEach {
            if (it == personaje.armaEquipada[0])
                FicheroArmas.writeText("W ; ${it.nombre} ; ${it.arquetipo} ; ${it.tipoArma} ; ${it.elemento} ; ${it.rareza}")
            FicheroArmas.appendText("\nW ; ${it.nombre} ; ${it.arquetipo} ; ${it.tipoArma} ; ${it.elemento} ; ${it.rareza}")
        }

        // Guarda los datos de las armaduras equipadas del personaje en el fichero de armaduras.
        personaje.armaduraEquipada.forEach {
            if (it == personaje.armaduraEquipada[0])
                FicheroArmas.writeText("A ; ${it.nombre} ; ${it.arquetipo} ; ${it.tipoArma} ; ${it.elemento} ; ${it.rareza}")
            FicheroArmaduras.appendText("\nA ; ${it.nombre} ; ${it.parte} ; ${it.rareza}")
        }

        // Guarda los datos básicos del personaje en el fichero de personaje.
        when(personaje) {
            is Titan -> FicheroPersonaje.writeText("T ; ${personaje.nombre} ; ${personaje.genero} ; ${personaje.raza}")
            is Warlock -> FicheroPersonaje.writeText("W ; ${personaje.nombre} ; ${personaje.genero} ; ${personaje.raza}")
            is Hunter -> FicheroPersonaje.writeText("H ; ${personaje.nombre} ; ${personaje.genero} ; ${personaje.raza}")
        }
    }

    /**
     * Función para finalizar el juego.
     * @return Siempre devuelve false, indicando que el juego ha terminado.
     */
    private fun acabarJuego(): Boolean {
        return false
    }
}