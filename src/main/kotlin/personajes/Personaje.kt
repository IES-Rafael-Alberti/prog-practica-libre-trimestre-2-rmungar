package org.practicatrim2.personajes
import org.practicatrim2.items.Arma
import org.practicatrim2.items.Armadura

/**
 * Clase abstracta que define un personaje en el juego.
 * Los personajes pueden ser de diferentes tipos (Warlock, Titan, Hunter) y tienen propiedades exclusivas.
 * Esta clase sirve para la creación y manipulación de personajes en el juego.
 *
 * @property nombre El nombre del personaje.
 * @property genero El género del personaje (por ejemplo, masculino o femenino).
 * @property raza La raza del personaje (por ejemplo, Humano, Awoken o Exo).
 * @property armaduraEquipada Lista de las partes de armadura actualmente equipadas por el personaje.
 * @property armaEquipada Lista de las armas actualmente equipadas por el personaje.
 */
abstract class Personaje(open val nombre: String, open val genero:String, open val raza:Razas) {
    open val armaduraEquipada:MutableList<Armadura> = mutableListOf()//Lista con el conjunto de 5 partes de armadura equipadas actualmente
    open val armaEquipada: MutableList<Arma> = mutableListOf()//Lista con el conjunto de 3 armas equipadas actualmente

    companion object{
        /**
         * Genera un personaje a partir de los datos proporcionados.
         * @param datosPersonaje Los datos del personaje en formato de cadena.
         * @return El objeto Personaje generado.
         */
        fun generarPersonaje(datosPersonaje: String): Personaje {
            // Divide los datos del personaje en una lista de strings
            val datosPersonajeProcesados = datosPersonaje.split(" ; ")
            val raza = obtenerRaza(datosPersonajeProcesados)
            // Crea un objeto Personaje según el tipo especificado en los datos.
            return when (datosPersonajeProcesados[0]){
                "W" -> Warlock(datosPersonajeProcesados[1], datosPersonajeProcesados[2], raza)
                "T" -> Titan(datosPersonajeProcesados[1], datosPersonajeProcesados[2], raza)
                else -> Hunter(datosPersonajeProcesados[1], datosPersonajeProcesados[2], raza)
            }
        }

        /**
         * Obtiene la raza del personaje a partir de los datos procesados.
         * @param datosPersonajeProcesados Los datos del personaje procesados en una lista de strings.
         * @return La raza del personaje como un objeto de la enumeración Razas.
         */
        private fun obtenerRaza(datosPersonajeProcesados: List<String>): Razas {
            // Determina la raza del personaje basada en el cuarto elemento de la lista.
            return when(datosPersonajeProcesados[3]){
                "Human" -> Razas.HUMAN
                "Awoken" -> Razas.AWOKEN
                else -> Razas.EXO
            }
        }
    }
}