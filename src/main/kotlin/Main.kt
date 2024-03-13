package org.practicatrim2


import org.practicatrim2.juego.GestionJuego


fun String.capitalizar():String{
    val texto = this.toList()
    var palabraCapitalizada = ""
    var posicion = 0
    for (caracter in texto){
        if (caracter == texto[0]) palabraCapitalizada += caracter.toString().uppercase()
        else if (texto[posicion - 1] == ' ') palabraCapitalizada += caracter.toString().uppercase()
        else{
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
