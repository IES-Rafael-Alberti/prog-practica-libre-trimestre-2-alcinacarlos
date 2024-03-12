package org.practicatrim2

class Mina(
    val nombre:String,
    var listaTrabajadores:MutableList<Trabajador>,
    var historialAcciones: MutableMap<Int, MutableList<String>> = mutableMapOf(),
    var activa:Boolean = true, //Indica si la mina está terminada o no
    var dia: Int = 0,
    val pedidosRealizados:MutableList<Pedido> = mutableListOf()
) {
    fun avanzarDia(){
        dia++
    }
    fun trabajar(){
        listaTrabajadores.forEach { it.trabajar() }
    }

    override fun toString(): String {
        return """
        Mina: ${nombre}
        Activa: ${activa}
        Día: ${dia}
        Trabajadores: ${listaTrabajadores.size}
        Pedidos realizados: ${pedidosRealizados.size}
        Historial de acciones:
        ${historialAcciones.map { (dia, acciones) ->
            "$dia: ${acciones.joinToString(", ")}"
        }.joinToString("\n")}
    """
    }

}