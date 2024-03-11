package org.practicatrim2.juego

import com.github.ajalt.mordant.rendering.TextColors
import com.github.ajalt.mordant.terminal.Terminal
import org.practicatrim2.capitalizar
import org.practicatrim2.items.*
import org.practicatrim2.modosDeJuego.GameModes
import org.practicatrim2.personajes.*
import java.io.File
import kotlin.random.Random

open class Juego :Mostrable,Jugable {
    private val terminal = Terminal() // Variable empleada para lo relacionado con mordant

    private val colorTitanes = TextColors.brightRed // Color para mordant para la clase Titan
    private val colorWarlocks = TextColors.brightYellow // Color para mordant para la clase Warlock
    private val colorHunters = TextColors.rgb("#00eeff") // Color para mordant para la clase Hunter

    private val colorBlanco = TextColors.brightWhite // Variable para el color blanco
    val colorVerde = TextColors.brightGreen // Variable para el color verde
    val colorMythic = TextColors.rgb("#da86e3")

    val workingDirectory = System.getProperty("user.dir")!! // Directorio actual
    private val FicheroVault = File("$workingDirectory/Datos_Guardado/Vault.txt") // Fichero donde se guardan las armas y armaduras NO EQUIPADAS de una partida previa

    private val veces = 1
    private val numeroEasterEgg = 33
    
    fun separador(){
        repeat(35){
            println()
        }
    }
    override fun mostrarClasePersonaje(){
        separador()
        terminal.println(colorBlanco("                                                                       CHOOSE A CLASS FOR YOUR CHARACTER"))
        println()
        terminal.println("                                  ${colorTitanes ("TITAN")}                                             ${colorWarlocks ("WARLOCK")}                                             ${colorHunters ("HUNTER")}")
    }

    override fun mostrarInformacionClases(){
        println()
        println("                  Titans are specialized in close combat,              Warlocks are specialized in magic,                  Hunters are specialized in tactics,\n" +
                "                  defense and heavy equipment. Those who              they long to understand the traveler                 they wield the light to claim it's \n" +
                "                   act with confidence and decision and             and it's powers. A warlock's mind is an              treasures. Being assassins, experts at\n" +
                "                   serve as brute force instruments for            arsenal of lethal secrets between divinity             wielding knives and precision weapons.\n" +
                "                           the Traveler's will.                                   and madness.                              They tend to write their own laws.")
    }

    override fun mostrarInterfazJuego() {
        terminal.println("                                                     ${colorBlanco("CHARACTER INTERFACE")}                                              ${colorBlanco("STAR CHART")}")
        repeat(5){
            println()
        }
        terminal.println("                                                                                       ${colorBlanco("e - Exit")}")
    }

    fun selectorSeccionJuego():Int{
        println()
        while (true) {
            terminal.println(colorBlanco("                                                                                         > "))
            val seleccion = readln().lowercase()
            when (seleccion) {
                "1", "c" -> return 1
                "2", "s" -> return 2
                "e" -> return 3
                else -> terminal.warning("                                                                Please, answer the requested prompt correctly")
            }
        }
    }

    fun selectorClasePersonaje():Clases{
        while (true) {
            println()
            terminal.print(colorBlanco("                                                            Enter the class you wish to create a character of: "))
            val claseAcrear = readln().uppercase()
            when(claseAcrear){
                "TITAN" -> return Clases.TITAN

                "HUNTER" -> return Clases.HUNTER

                "WARLOCK" -> return Clases.WARLOCK

                else -> terminal.warning("                                                                Please, answer the requested prompt correctly")
            }
        }
    }

    override fun mostrarMenuModosJuego(){
        terminal.print(colorBlanco("                                                                             LOADING STAR CHART"))
        repeat(3){
            Thread.sleep(100)
            terminal.print(colorBlanco("."))
        }
        separador()
        Thread.sleep(500)
        terminal.println(colorBlanco("                                                                                 --STAR CHART--"))
        println()
        terminal.println("       ${GameModes.GAMBIT.color ("⟁ GAMBIT PRIME ⟁")}                               ${GameModes.NIGHTFALL.color ("★ GRANDMASTER NIGHTFALL ★")}                               ${GameModes.TRIALS.color ("⚔ TRIALS OF OSIRIS ⚔")}                               ${GameModes.RAIDS_DUNGEONS.color("✦ RAIDS & DUNGEONS ✦")}")
        terminal.println("                                                         ${colorBlanco("s: Save")}                                                           ${colorBlanco("e: Exit")}")
    }

    fun generarPersonaje():Personaje{
        mostrarClasePersonaje()
        mostrarInformacionClases()
        val clase = selectorClasePersonaje()
        val personaje = crearPersonaje(clase)
        return personaje
    }
    fun crearPersonaje(clase: Clases):Personaje{
        val nombre = pedirNombrePersonaje()
        val genero = pedirGeneroPersonaje()
        val raza = selectorRazaPersonaje()
        return when (clase){
            Clases.TITAN ->{
                Titan(nombre, genero, raza)
            }

            Clases.WARLOCK ->{
                Warlock(nombre, genero, raza)
            }

            Clases.HUNTER -> {
                Hunter(nombre, genero, raza)
            }
        }
    }

    private fun pedirNombrePersonaje():String{
        separador()
        terminal.print(colorBlanco("                                                                              Please enter a name: "))
        var nombre = readln() //Cadena para el nombre del personaje
        while (nombre == "" || nombre.isEmpty() || nombre.isBlank()){
            terminal.warning("                                                                           Please enter a valid name")
            terminal.print(colorBlanco("                                                                              Please enter a name: "))
            nombre = readln().capitalizar()
        }
        return nombre
    }

    private fun pedirGeneroPersonaje():String{
        println()
        while(true) {
            terminal.print(colorBlanco("                                                                    Do you wish them to be male or female?: "))
            val genero = readln().capitalizar()
            when(genero){
                "Male" -> return "Male"
                "Female" -> return "Female"
                else -> terminal.warning("                                                                Please, answer the requested prompt correctly")
            }
        }
    }

    override fun mostrarRazaPersonaje(){
        separador()
        terminal.println(colorBlanco("                                                                       CHOOSE A RACE FOR YOUR CHARACTER"))
        println()
        terminal.println("                                  ${colorTitanes ("HUMAN")}                                              ${colorWarlocks ("AWOKEN")}                                               ${colorHunters ("EXO")}")
    }

    override fun mostrarInformacionRazas(){
        println()
        println("                  The survivors of a race whose kindgom               A humanoid race that was born when a                 Humanoid race that appeared during\n" +
                "                extended past the Solar System, now fight              colony ship disappeared in a space -                Earth's Golden Age. They are robots \n" +
                "                     for survival after the Traveller                     time anomaly during the first                     with conscience and feelings built\n" +
                "                  unintentionally led them to their first                          collapse.                                           by humanity.\n" +
                "                                 collapse.")
    }

    private fun selectorRazaPersonaje():Razas {
        mostrarRazaPersonaje()
        mostrarInformacionRazas()
        while (true) {
            println()
            terminal.print(colorBlanco("                                                                     Enter the race you wish them to be: "))
            val razaAcrear = readln().uppercase() //Cadena ingresada por el usuario a comprobar
            when (razaAcrear) {
                "HUMAN" -> return Razas.HUMAN

                "AWOKEN" -> return Razas.AWOKEN

                "EXO" -> return Razas.EXO

                else -> terminal.warning("                                                                Please, answer the requested prompt correctly")
            }
        }
    }

    private fun obtenerItem(modoDeJuego: GameModes): String {
        val directorioActual = System.getProperty("user.dir")
        val itemObtenido: String = File("$directorioActual/Loot_Pool/${modoDeJuego.desc}.txt").useLines { it.toList() }.random()
        return itemObtenido
    }


    override fun jugarGambito(personaje: Personaje, featured:Boolean) {
        val gameMode:GameModes = GameModes.GAMBIT
        terminal.print(GameModes.GAMBIT.color("                                                                             TRAVELLING TO THE DRIFTER'S REALM"))
        repeat(3){
            Thread.sleep(100)
            terminal.print(GameModes.GAMBIT.color("."))
        }
        println()
        var rotations = veces
        if (featured) rotations++
        repeat(rotations){
            val probabilidadEasterEgg = Random.nextInt(1,50)
            encontrarEasterEgg(probabilidadEasterEgg)
            val itemObtenido = obtenerItem(gameMode)
            administrarItem(itemObtenido, personaje)
        }
    }

    override fun jugarOcaso(personaje: Personaje, featured:Boolean) {
        val gameMode:GameModes = GameModes.NIGHTFALL
        terminal.print(GameModes.NIGHTFALL.color("                                                                             TRAVELLING TO THE SCARLET KEEP"))
        repeat(3){
            Thread.sleep(100)
            terminal.print(GameModes.NIGHTFALL.color("."))
        }
        println()
        var rotations = veces
        if (featured) rotations++
        repeat(rotations){
            val probabilidadEasterEgg = Random.nextInt(1,50)
            encontrarEasterEgg(probabilidadEasterEgg)
            val itemObtenido = obtenerItem(gameMode)
            administrarItem(itemObtenido, personaje)
        }
    }

    override fun jugarRyD(personaje: Personaje, featured:Boolean) {
        val gameMode:GameModes = GameModes.RAIDS_DUNGEONS
        terminal.print(GameModes.RAIDS_DUNGEONS.color("                                                                             TRAVELLING TO THE ROOT OF NIGHTMARES"))
        repeat(3){
            Thread.sleep(100)
            terminal.print(GameModes.RAIDS_DUNGEONS.color("."))
        }
        println()
        var rotations = veces
        if (featured) rotations++
        repeat(rotations){
            val probabilidadEasterEgg = Random.nextInt(1,50)
            encontrarEasterEgg(probabilidadEasterEgg)
            val itemObtenido = obtenerItem(gameMode)
            administrarItem(itemObtenido, personaje)
        }
    }

    override fun jugarTrials(personaje: Personaje, featured:Boolean) {
        val gameMode:GameModes = GameModes.TRIALS
        terminal.print(GameModes.TRIALS.color("                                                                             TRAVELLING TO THE LIGHTHOUSE"))
        repeat(3){
            Thread.sleep(100)
            terminal.print(GameModes.TRIALS.color("."))
        }
        println()
        var rotations = veces
        if (featured) rotations++
        repeat(rotations){
            val probabilidadEasterEgg = Random.nextInt(1,50)
            encontrarEasterEgg(probabilidadEasterEgg)
            val itemObtenido = obtenerItem(gameMode)
            administrarItem(itemObtenido, personaje)
        }
    }

    override fun mostrarEasterEgg(huevo: ActionFigure) {
        terminal.println(huevo.toString())
    }

    private fun encontrarEasterEgg(probabilidadEasterEgg: Int){
        if (probabilidadEasterEgg == numeroEasterEgg){
            val huevoEncontrado = File("$workingDirectory/Loot_Pool/EasterEggs.txt").useLines { it.toList() }.random().split(" ; ")
            val huevo = ActionFigure(huevoEncontrado[1], huevoEncontrado[2], huevoEncontrado[3], huevoEncontrado[4], huevoEncontrado[5])
            terminal.warning("HEY, YOU FOUND AN EASTER EGG!!!")
            mostrarEasterEgg(huevo)
        }
    }

    fun administrarItem(itemObtenido:String, personaje: Personaje){
        when(val itemObtenidoProcesado = Item.procesarItem(itemObtenido)){
            is Armadura -> {
                if(itemObtenidoProcesado.preguntarParaEquipar(itemObtenidoProcesado, personaje)) {
                    if(itemObtenidoProcesado.equipable(personaje.armaduraEquipada as MutableList<Item>)) itemObtenidoProcesado.equipar(itemObtenidoProcesado, personaje)
                    else itemObtenidoProcesado.sustituir(itemObtenidoProcesado, (personaje))
                }
            }
            else -> {
                if(itemObtenidoProcesado.preguntarParaEquipar(itemObtenidoProcesado, personaje)) {
                    if(itemObtenidoProcesado.equipable(personaje.armaEquipada as MutableList<Item>)) itemObtenidoProcesado.equipar(itemObtenidoProcesado, personaje)
                    else itemObtenidoProcesado.sustituir(itemObtenidoProcesado, personaje)
                }
            }
        }
    }

    fun extraerArmaDeVault(personaje: Personaje){
        mostrarVault()
        while (true){
            terminal.println(colorBlanco("Enter the ID of the chosen item (<ENTER> to cancel): "))
            val input = readln()
            when(input){
                in "1".."${FicheroVault.useLines { it.toList() }.size}" -> {
                    val item = FicheroVault.useLines { it.toList() }[input.toInt() - 1]
                    administrarItem(item, personaje)
                    break
                }
                "" -> break
                else -> terminal.warning("                                                                Please, answer the requested prompt correctly")
            }
        }
    }
    fun mostrarVault(){
        val itemsPorProcesar = FicheroVault.useLines { it.toList() }
        var id = 1
        itemsPorProcesar.forEach {
            val item = Item.procesarItem(it)
            println("$id - $item")
            println()
            id++
        }
    }


}
