package org.practicatrim2

import com.github.ajalt.mordant.rendering.TextColors
import com.github.ajalt.mordant.table.table
import org.practicatrim2.Terminal.terminal
import kotlin.random.Random

class Minero(
    nombre: String,
    edad: Int,
    dni: String,
    override val salario: Int,
    override val cargo: Cargo,
    override var dadoDeBaja: Boolean
): Persona(nombre, edad, dni), Trabajador {
    override fun trabajar(mina: Mina) {
        val numeroMineralesAObtener = Random.nextInt(GestionarJuego.MINERALES_MINIMOS_POR_MINERO,GestionarJuego.MINERALES_MAXIMOS_POR_MINERO)
        repeat(numeroMineralesAObtener){
            val mineral = GestionarJuego.generarMineralAleatorios()
            mina.inventario.add(mineral)
        }
    }

    override fun cobrar(mina: Mina) {
        mina.dinero -= salario
    }
    override fun mostrarTrabajador() {
        terminal.println(table {
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