package org.practicatrim2

import kotlin.math.min

class Transportista(
    override val salario: Int,
    override val cargo: Cargo,
    override var dadoDeBaja: Boolean,
    nombre: String,
    edad: Int,
    dni: String,
): Persona(nombre, edad, dni), Trabajador {
    private var diaLiberacion:Int = 0
    companion object{
        const val DIAS_COOLDOWN = 2
    }
    fun estaDisponible(mina: Mina):Boolean{
        return mina.dia >= diaLiberacion
    }
    override fun trabajar(mina: Mina) {
        cobrar(mina)
        diaLiberacion = mina.dia + DIAS_COOLDOWN
    }
    override fun cobrar(mina: Mina) {
        TODO("Not yet implemented")
    }
}