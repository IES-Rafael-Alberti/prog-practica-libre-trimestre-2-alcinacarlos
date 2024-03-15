package org.practicatrim2
import kotlin.random.Random
/**
 * Interfaz que define las características y funcionalidades comunes a todos los trabajadores
 */
interface Trabajador {
    val salario:Int
    val cargo:Cargo
    var dadoDeBaja:Boolean
    /**
     * Simula la actividad del trabajador en la mina.
     *
     * @param mina La mina en la que trabaja el trabajador.
     */
    fun trabajar(mina: Mina)
    /**
     * Cobra el salario al trabajador.
     *
     * @param mina La mina de la que cobra el trabajador.
     */
    fun cobrar(mina: Mina)
    fun darBaja(){
        dadoDeBaja = true
    }
    fun darAlta(){
        dadoDeBaja = false
    }
    /**
     * Incorpora al trabajador de forma aleatoria (con una probabilidad del 50%).
     */
    fun incorporarseRandom(){
        if (Random.nextFloat() > 0.5f) darAlta()
    }

    /**
     * Lesiona al trabajador de forma aleatoria (con una probabilidad del 50%).
     */
    fun lastimarseRandom(){
        if (Random.nextFloat() > 0.5f) darBaja()
    }
    fun mostrarTrabajador()
}