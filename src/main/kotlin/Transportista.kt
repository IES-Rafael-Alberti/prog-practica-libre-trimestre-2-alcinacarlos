package org.practicatrim2

import kotlin.math.min

class Transportista(
    nombre: String,
    edad: Int,
    dni: String,
    override val salario: Int,
    override val cargo: Cargo,
    override var dadoDeBaja: Boolean
): Persona(nombre, edad, dni), Trabajador {
    private var diaLiberacion:Int = 0
    companion object{
        const val DIAS_COOLDOWN = 2
    }
    fun estaDisponible(dia: Int):Boolean{
        return dia >= diaLiberacion
    }
    override fun trabajar(mina: Mina) {
        cobrar(mina)
        diaLiberacion = mina.dia + DIAS_COOLDOWN
    }
    override fun cobrar(mina: Mina) {
        mina.dinero -= salario
    }
}