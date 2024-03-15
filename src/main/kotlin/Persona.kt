package org.practicatrim2

abstract class Persona(
    val nombre:String,
    val edad:Int,
    val dni:String
) {
    fun obtenerInformacion(){
        println("$nombre con DNI $dni y $edad años")
    }
}