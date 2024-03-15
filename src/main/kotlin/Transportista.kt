package org.practicatrim2

import com.github.ajalt.mordant.rendering.TextColors
import com.github.ajalt.mordant.table.table
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

    override fun mostrarTrabajador() {
        Terminal.terminal.println(table {
            borderStyle = TextColors.green
            style = TextColors.brightWhite
            body {
                row("Nombre", nombre)
                row("Edad", edad)
                row("DNI", dni)
                row("Salario al día", "$salario$")
                row("Cargo", cargo.nombre)
                row ("Dado de baja", dadoDeBaja)
            }
        })
    }
}