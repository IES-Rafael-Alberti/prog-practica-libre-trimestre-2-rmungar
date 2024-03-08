package org.practicatrim2.items

interface Equipable<T> {
    fun equipable(t: T):Boolean
}

interface Sustituible<T,E> {
    fun sustituir(t:T, e:E)
}

interface Guardable<A>{
    fun guardar(a:A)
}

interface Comprobable<T> {
    fun comprobarAccion(t:T):Boolean
    fun comprobarDatosPrevios():Boolean
    fun comprobarDatosArmaduras():Boolean
    fun comprobarDatosArmas():Boolean
}

interface Mostrable{
    fun mostrarRazaPersonaje()
    fun mostrarClasePersonaje()
    fun mostrarInformacionClases()
    fun mostrarInformacionRazas()
    fun mostrarMenuModosJuego()

}

interface Informable{
    fun mostrarInformacion()
}