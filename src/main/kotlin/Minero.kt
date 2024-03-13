package org.practicatrim2

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
        val numeroMineralesAObtener = Random.nextInt(1,5)
        repeat(numeroMineralesAObtener){
            val mineral = GestionarJuego.generarMineralAleatorios()
            mina.inventario.add(mineral)
        }
    }

    override fun cobrar(mina: Mina) {
        mina.dinero -= salario
    }
}