package org.practicatrim2

/**
 * Clase abstracta que define las características básicas de una persona.
 *
 * @param nombre El nombre de la persona.
 * @param edad La edad de la persona.
 * @param dni El DNI de la persona.
 */
abstract class Persona(
    val nombre:String,
    val edad:Int,
    val dni:String
) {
    fun obtenerInformacion(){
        println("$nombre con DNI $dni y $edad años")
    }
}