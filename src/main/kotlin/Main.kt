package org.practicatrim2


import org.practicatrim2.juego.GestionJuego


/**
 * Función para capitalizar la primera letra de cada palabra en una cadena.
 * @return La cadena con la primera letra de cada palabra capitalizada.
 */
fun String.capitalizar(): String {
    // Convierte la cadena en una lista de caracteres.
    val texto = this.toList()
    var palabraCapitalizada = ""
    var posicion = 0
    // Itera sobre cada caracter en la lista.
    for (caracter in texto) {
        // Si es el primer caracter de la cadena, lo capitaliza.
        if (caracter == texto[0]) {
            palabraCapitalizada += caracter.toString().uppercase()
        } else if (texto[posicion - 1] == ' ') {
            // Si el caracter anterior es un espacio, capitaliza el caracter actual.
            palabraCapitalizada += caracter.toString().uppercase()
        } else {
            // Si no, convierte el caracter actual en minúscula.
            palabraCapitalizada += caracter.lowercase()
        }
        posicion++
    }
    return palabraCapitalizada
}

fun main() {

   try {
    val gestor = GestionJuego
       gestor.comenzarJuego()
   }
   catch (e:Exception){
       println(e.message)
   }
}
