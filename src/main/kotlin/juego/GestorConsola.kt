package org.practicatrim2.juego

import com.github.ajalt.mordant.rendering.TextAlign
import com.github.ajalt.mordant.rendering.TextColors
import com.github.ajalt.mordant.table.Borders
import com.github.ajalt.mordant.table.table
import com.github.ajalt.mordant.terminal.Terminal
import org.practicatrim2.animaciones.AnimationManager
import org.practicatrim2.items.ActionFigure
import org.practicatrim2.items.Item
import org.practicatrim2.modosDeJuego.GameModes
import org.practicatrim2.personajes.Personaje
import java.io.File


/**
 * Este objeto se encarga unicamente de lo relacionado con mostrar mensajes / menus por consola mediante el uso de mordant
 */
object GestorConsola : Mostrable{
    private val terminal = Terminal()
    private val colorTitanes = TextColors.brightRed // Color para mordant para la clase Titan
    private val colorWarlocks = TextColors.brightYellow // Color para mordant para la clase Warlock
    private val colorHunters = TextColors.rgb("#00eeff") // Color para mordant para la clase Hunter
    private val colorBlanco = TextColors.brightWhite // Variable para el color blanco
    private val colorVerde = TextColors.brightGreen // Variable para el color verde
    private val colorRojo = TextColors.brightRed // Variable del color rojo
    private val colorAmarillo = TextColors.brightYellow // Variable del color amarillo


//      LIMPIAR CONSOLA ------------------------------------------------------------------------------------------------

    override fun separador(){
        repeat(35){
            println()
        }
    }

//      DATOS DE JUEGO  ------------------------------------------------------------------------------------------------

    override fun sobreescribiendoDatos() {
        terminal.println((colorRojo)("                                                                                  OVERWRITING DATA"))
        val animacion = AnimationManager.animacionCargando()
        println()
    }

    override fun datosSobreescritos() {
        terminal.println((colorVerde)("                                                                                  DATA OVERWRITTEN"))
    }

    override fun cargandoDatos() {
        terminal.println((colorVerde)("                                                                                   LOADING DATA...."))
        val animacion = AnimationManager.animacionCargando()
        println()
    }

    override fun cargandoJuego() {
        terminal.println((colorVerde)("                                                                                   LOADING GAME...."))
        val animacion = AnimationManager.animacionCargando()
        println()
    }

    override fun creandoDatos() {
        terminal.println((colorVerde)("                                                                                   CREATING SAVE FILES...."))
        val animacion = AnimationManager.animacionCargando()
        println()
    }

    override fun mostrarExistenciaDatosPrevios() {
        terminal.print(colorRojo("                                                         It seems you have saved data, would you like to overwrite it? (y / n): ")) // Entrada del usuario que indica lo que desea hacer
    }

    override fun mostrarNoExistenciaDatosPrevios() {
        terminal.print(colorRojo("                                                         It seems there isn't saved data, would you like to start a new game? (y / n): "))
    }

//      MENUS   --------------------------------------------------------------------------------------------------------

    override fun bienvenida() {
    terminal.println(colorBlanco("                                                                          WELCOME TO DESTINY - LITE"))
}

    override fun mostrarMenuInicio() {
        println()
        terminal.println(colorAmarillo("                                                                                    1 - New Game"))
        terminal.println(colorVerde("                                                                                    2 - Continue Game"))
        terminal.println(colorRojo("                                                                                    3 - Exit Game"))
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
        terminal.println("                                                         ${colorBlanco("s: Save")}                                                         ${colorBlanco("Enter: Exit")}")
    }


//      RELACIONADO CON EL PERSONAJE    --------------------------------------------------------------------------------

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

    override fun mostrarTextoSeleccionClase() {
        terminal.print(colorBlanco("                                                            Enter the class you wish to create a character of: "))
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

    override fun mostrarTextoSeleccionRaza() {
        terminal.print(colorBlanco("                                                                     Enter the race you wish them to be: "))
    }

    override fun mostrarInfoPersonaje(personaje: Personaje) {
        terminal.println("                                                                     " + colorBlanco(personaje.toString()))
    }

    override fun mostrarArmaduraPersonaje(personaje: Personaje){
        terminal.println(colorBlanco("          CURRENTLY EQUIPPED ARMOR"))
        println()
        personaje.armaduraEquipada.forEach {
            terminal.println(colorBlanco("        ${it.rarity(it.nombre)}   ---   ${it.parte}"))
        }
    }

    override fun mostrarArmasPersonaje(personaje: Personaje){
        terminal.println(colorBlanco("          CURRENTLY EQUIPPED WEAPONS"))
        println()
        personaje.armaEquipada.forEach {
            terminal.println(it)
        }
    }

    override fun mostrarExcesoArmas() {
        terminal.warning("You already have 3 weapons equipped")
    }

    override fun mostrarExcesoArmaduras() {
        terminal.warning("You already have 5 armor pieces equipped")
    }

    override fun mostrarArmaEquipada() {
        terminal.println(colorVerde("Weapon equipped successfully"))
    }

    override fun mostrarArmaduraEquipada() {
        terminal.println(colorVerde("Armor equipped successfully"))
    }

    override fun pedirSlot() {
        terminal.println(colorBlanco("Select a weapon slot :"))
    }

//      INTERFACES  ----------------------------------------------------------------------------------------------------

    override fun mostrarInterfazJuego() {
        terminal.println("                                                     ${colorBlanco("CHARACTER INTERFACE")}                                              ${colorBlanco("STAR CHART")}")
        repeat(5){
            println()
        }
        terminal.println("                                                                                      ${colorBlanco("e - Exit")}")
    }

    override fun mostrarInterfazPersonaje(personaje: Personaje) {
        terminal.print(colorBlanco("Loading Character details"))
        repeat(3){
            print(".")
            Thread.sleep(100)
        }
        println()
        terminal.println(colorBlanco("Character Details"))
        mostrarInfoPersonaje(personaje)
        println()
        println()
        mostrarArmaduraPersonaje(personaje)
        println()
        mostrarArmasPersonaje(personaje)
    }

//      MARCADORES DE ENTRADA DEL USUARIO   ----------------------------------------------------------------------------

    override fun mostrarEntradaIncorrecta() {
    terminal.warning("                                                                      Please, answer the requested prompt correctly")
}

    override fun marcadorEntradaTexto() {
        terminal.print(colorBlanco("                                                                                         > "))
    }

    override fun mostrarEntradaDeNombre() {
        terminal.print(colorBlanco("                                                                              Please enter a name: "))
    }

    override fun mostrarEntradaDeGenero() {
        terminal.print(colorBlanco("                                                                    Do you wish them to be male or female?: "))
    }

    override fun mostrarEntradaDeAccionInicial() {
        terminal.print(colorBlanco("                                                                        What are you going to do today? :"))
    }

    override fun mostrarEntradaDeId() {
        terminal.print(colorBlanco("                                                              Enter the ID of the chosen item (<ENTER> to cancel): "))
    }

    override fun mostrarOpcionAbrirVault() {
        terminal.println(colorBlanco("                                                      Do you want to access the vault? (y / n)              <ENTER> to exit"))
        println()
        marcadorEntradaTexto()
    }

    override fun preguntarParaEquiparArma(item: Item) {
        terminal.println(colorBlanco("Do you wish to equip: ${item.rarity(item.nombre)}? (y / n)"))
    }

    override fun preguntarParaEquiparArmadura(item: Item) {
        terminal.println(colorBlanco("Do you wish to equip: ${item.rarity(item.nombre)} ${item.parte}? (y / n)"))
    }

    override fun preguntarParaCambiarArma() {
        terminal.println(colorBlanco("Exchange weapon? (y / n)"))
    }

    override fun preguntarParaCambiarArmadura() {
        terminal.println(colorBlanco("Exchange armor? (y / n)"))
    }

//      EASTER EGGS ----------------------------------------------------------------------------------------------------

    override fun mostrarEasterEgg(huevo: ActionFigure) {
        terminal.warning("HEY, YOU FOUND AN EASTER EGG!!!")
        terminal.println(huevo.toString())
    }

//      RELACIONADO CON LOS MODOS DE JUEGO  ----------------------------------------------------------------------------

    override fun animacionGambito() {
        terminal.print(GameModes.GAMBIT.color("                                                                             TRAVELLING TO THE DRIFTER'S REALM"))
        repeat(3){
            Thread.sleep(100)
            terminal.print(GameModes.GAMBIT.color("."))
        }
    }

    override fun animacionOcaso() {
        terminal.print(GameModes.NIGHTFALL.color("                                                                             TRAVELLING TO THE SCARLET KEEP"))
        repeat(3){
            Thread.sleep(100)
            terminal.print(GameModes.NIGHTFALL.color("."))
        }
    }

    override fun animacionTrials() {
        terminal.print(GameModes.TRIALS.color("                                                                             TRAVELLING TO THE LIGHTHOUSE"))
        repeat(3){
            Thread.sleep(100)
            terminal.print(GameModes.TRIALS.color("."))
        }
    }

    override fun animacionRyD() {
        terminal.print(GameModes.RAIDS_DUNGEONS.color("                                                                             TRAVELLING TO THE ROOT OF NIGHTMARES"))
        repeat(3){
            Thread.sleep(100)
            terminal.print(GameModes.RAIDS_DUNGEONS.color("."))
        }
    }


//      VAULT   --------------------------------------------------------------------------------------------------------

    override fun mostrarVault() {
        val workingDirectory = System.getProperty("user.dir")!! // Directorio actual
        val ficheroVault = File("$workingDirectory/Datos_Guardado/Vault.txt") // Fichero donde se guardan las armas y armaduras NO EQUIPADAS de una partida previa
        val itemsPorProcesar = ficheroVault.useLines { it.toList() }
        var id = 1
        val table = table {
            cellBorders = Borders.ALL
            align = TextAlign.CENTER
            header {
                style(italic = true)
                style = colorBlanco
                row ("ID", "Name", "Frame", "Weapon Type", "Element")
            }
            body {
                cellBorders = Borders.NONE
                align = TextAlign.LEFT
                column(0){
                    cellBorders = Borders.ALL
                    style(color = colorBlanco)
                }
                itemsPorProcesar.forEach {
                    val item = Item.procesarItem(it)
                    row (id, item.rarity(item.nombre) +"           ", item.arquetipo + "       ", item.tipoArma + "       ", item.colorElemento(item.elemento.desc) + "     ")
                    id++
                }
            }
        }
        terminal.println(table)
    }

    override fun mostrarObjetoGuardado(item: Item) {
        terminal.println(colorVerde("${item.rarity(item.nombre)} stored successfully"))
    }

    override fun preguntarParaGuardar(item: Item) {
        terminal.println(colorBlanco("Would you like to store ${item.rarity(item.nombre)}? (y / n)"))
    }

    override fun mostrarObjetoEliminado() {
        terminal.println(colorRojo("Item sent to the DCV, rest in peace"))
    }
}


