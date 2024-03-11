package org.practicatrim2

class Transportista(
    override val salario: Int,
    override val cargo: Cargo,
    override var dadoDeBaja: Boolean,
    nombre: String,
    edad: Int,
    dni: String
): Persona(nombre, edad, dni), Trabajador {
    override fun trabajar() {
        TODO("Not yet implemented")
    }

    override fun cobrar() {
        TODO("Not yet implemented")
    }
}