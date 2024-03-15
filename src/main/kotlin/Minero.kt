package org.practicatrim2

import com.github.ajalt.mordant.rendering.TextColors
import com.github.ajalt.mordant.table.table
import org.practicatrim2.Terminal.terminal
import kotlin.random.Random
/**
 * Representa un minero
 *
 * @param nombre El nombre del minero.
 * @param edad La edad del minero.
 * @param dni El DNI del minero.
 * @param salario El salario diario del minero.
 * @param cargo El cargo del minero (un objeto de la clase `Cargo`).
 * @param dadoDeBaja Indica si el minero está dado de baja o no.
 */
class Minero(
    nombre: String,
    edad: Int,
    dni: String,
    override val salario: Int,
    override val cargo: Cargo,
    override var dadoDeBaja: Boolean
): Persona(nombre, edad, dni), Trabajador {

    /**
     * Comprueba si el minero está disponible para trabajar.
     *
     * @return `true` si el minero está disponible, `false` en caso contrario.
     */
    fun estaDisponible():Boolean{
        if(dadoDeBaja) {
            incorporarseRandom()
            return false
        }else return true
    }

    /**
     * Simula la actividad del minero en la mina.
     *
     * @param mina La mina en la que trabaja el minero.
     */
    override fun trabajar(mina: Mina) {
        if (!estaDisponible()) return
        lastimarseRandom()

        val numeroMineralesAObtener = Random.nextInt(GestionarJuego.MINERALES_MINIMOS_POR_MINERO,GestionarJuego.MINERALES_MAXIMOS_POR_MINERO)
        repeat(numeroMineralesAObtener){
            val mineral = GestionarJuego.generarMineralAleatorios()
            mina.añadirInventario(mineral)
        }
    }

    /**
     * Cobra el salario al minero.
     *
     * @param mina La mina que paga al minero.
     */
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