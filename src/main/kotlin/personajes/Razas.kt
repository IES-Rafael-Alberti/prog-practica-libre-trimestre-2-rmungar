package org.practicatrim2.personajes


/**
 * Clase enumerada que contiene algo de información sobre las tres razas disponibles para los personajes
 * @property description -> Nombre de la raza
 */
enum class Razas(open val description:String) {
    HUMAN("Human"), AWOKEN("Awoken"), EXO("Exo")
}