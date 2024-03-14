package org.practicatrim2.items

/**
 * Clase enumerada que contiene los distintos tipos de elementos del juego
 * @property desc -> Nombre del elemento
 */
enum class Elementos(open val desc:String) {
    SOLAR("Solar"),ARC("Arc"),VOID("Void"),STRAND("Strand"),STASIS("Stasis"),KINETIC("Kinetic")
}