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


    private val veces = 1
    private val numeroEasterEgg = 33


    fun selectorSeccionJuego():Int{
        println()
        while (true) {
            GestorConsola.marcadorEntradaTexto()
            val seleccion = GestorEntrada.pedirEntradaDeSeccionDeJuego()
            when (seleccion) {
                "1", "c" -> return 1
                "2", "s" -> return 2
                "e" -> return 3
                else -> GestorConsola.mostrarEntradaIncorrecta()
            }
        }
    }

    private fun selectorClasePersonaje():Clases{
        while (true) {
            println()
            GestorConsola.mostrarTextoSeleccionClase()
            val claseAcrear = GestorEntrada.pedirClaseDePersonaje()
            when(claseAcrear){
                "TITAN" -> return Clases.TITAN

                "HUNTER" -> return Clases.HUNTER

                "WARLOCK" -> return Clases.WARLOCK

                else -> GestorConsola.mostrarEntradaIncorrecta()
            }
        }
    }

    fun generarPersonaje():Personaje{
        GestorConsola.mostrarClasePersonaje()
        GestorConsola.mostrarInformacionClases()
        val clase = selectorClasePersonaje()
        val personaje = crearPersonaje(clase)
        return personaje
    }

    private fun crearPersonaje(clase: Clases):Personaje{
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
        GestorConsola.separador()
        GestorConsola.mostrarEntradaDeNombre()
        var nombre = GestorEntrada.pedirNombreDePersonaje() //Cadena para el nombre del personaje
        while (nombre == "" || nombre.isEmpty() || nombre.isBlank()){
            GestorConsola.mostrarEntradaIncorrecta()
            GestorConsola.mostrarEntradaDeNombre()
            nombre = GestorEntrada.pedirNombreDePersonaje()
        }
        return nombre
    }

    private fun pedirGeneroPersonaje():String{
        println()
        while(true) {
        GestorConsola.mostrarEntradaDeGenero()
            val genero = pedirGeneroPersonaje()
            when(genero){
                "Male","M" -> return "Male"
                "Female","F" -> return "Female"
                else -> GestorConsola.mostrarEntradaIncorrecta()
            }
        }
    }

    private fun selectorRazaPersonaje():Razas {
        GestorConsola.mostrarRazaPersonaje()
        GestorConsola.mostrarInformacionRazas()
        while (true) {
            println()
            GestorConsola.mostrarTextoSeleccionRaza()
            val razaAcrear = GestorEntrada.pedirRazaDePersoanje()
            when (razaAcrear) {
                "HUMAN","H" -> return Razas.HUMAN

                "AWOKEN","A" -> return Razas.AWOKEN

                "EXO","E" -> return Razas.EXO

                else -> GestorConsola.mostrarEntradaIncorrecta()
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
        GestorConsola.animacionGambito()
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
        GestorConsola.animacionOcaso()
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
        GestorConsola.animacionRyD()
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
        GestorConsola.animacionTrials()
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

    private fun encontrarEasterEgg(probabilidadEasterEgg: Int){
        if (probabilidadEasterEgg == numeroEasterEgg){
            val huevoEncontrado = File("$workingDirectory/Loot_Pool/EasterEggs.txt").useLines { it.toList() }.random().split(" ; ")
            val huevo = ActionFigure(huevoEncontrado[1], huevoEncontrado[2], huevoEncontrado[3], huevoEncontrado[4], huevoEncontrado[5])
            GestorConsola.mostrarEasterEgg(huevo)
        }
    }

    private fun administrarItem(itemObtenido:String, personaje: Personaje){
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
        GestorConsola.mostrarVault()
        while (true){
            GestorConsola.mostrarEntradaDeId()
            when(val input = readln()){
                in "1".."${ficheroVault.useLines { it.toList() }.size}" -> {
                    val item = ficheroVault.useLines { it.toList() }[input.toInt() - 1]
                    administrarItem(item, personaje)
                    break
                }
                "" -> break
                else -> GestorConsola.mostrarEntradaIncorrecta()
            }
        }
    }

}
