package org.practicatrim2
import kotlin.random.Random

interface Trabajador {
    val salario:Int
    val cargo:Cargo
    var dadoDeBaja:Boolean
    fun trabajar(mina: Mina)
    fun cobrar(mina: Mina)
    fun darBaja(){
        dadoDeBaja = true
    }
    fun darAlta(){
        dadoDeBaja = false
    }
    fun incorporarseRandom(){
        if (Random.nextFloat() > 0.5f) darAlta()
    }

    fun lastimarseRandom(){
        if (Random.nextFloat() > 0.5f) darBaja()
    }
    fun mostrarTrabajador()
}