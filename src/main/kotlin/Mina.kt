package org.practicatrim2

class Mina(
    val nombre:String,
    var listaTrabajadores:MutableList<Trabajador>,
    var historialAcciones: MutableMap<String, MutableList<String>> = mutableMapOf(),
    val estadoMina:Boolean = false, //Indica si la mina está terminada o no
    val dia: Int = 0,
    val pedidosRealizados:MutableList<Pedido> = mutableListOf()
) {
}