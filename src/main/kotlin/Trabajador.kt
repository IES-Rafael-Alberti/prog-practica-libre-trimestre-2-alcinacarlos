package org.practicatrim2

interface Trabajador {
    val salario:Int
    val cargo:Cargo
    var dadoDeBaja:Boolean
    fun trabajar()
    fun cobrar()
    fun darBaja(){
        dadoDeBaja = true
    }
    fun darAlta(){
        dadoDeBaja = false
    }
}