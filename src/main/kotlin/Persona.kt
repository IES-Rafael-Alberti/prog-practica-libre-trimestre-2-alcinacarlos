package org.practicatrim2

abstract class Persona(
    private val nombre:String,
    private val edad:Int,
    private val dni:String
) {
    fun obtenerInformacion(){
        println("$nombre con DNI $dni y $edad años")
    }
}