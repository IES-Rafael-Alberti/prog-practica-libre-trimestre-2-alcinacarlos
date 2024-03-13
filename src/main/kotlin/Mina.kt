package org.practicatrim2

import com.github.ajalt.mordant.rendering.TextColors.*
import com.github.ajalt.mordant.rendering.TextStyles.*
import com.github.ajalt.mordant.terminal.Terminal
import com.github.ajalt.mordant.terminal.YesNoPrompt

class Mina(
    val nombre:String,
    var dinero:Double,
    var listaTrabajadores:MutableList<Trabajador>
) {
    val inventario:MutableList<Mineral> = mutableListOf()
    var historialAcciones: MutableMap<Int, MutableList<String>> = mutableMapOf()
    var activa:Boolean = true //Indica si la mina está terminada o no
    var dia: Int = 0
    var colaPedidos:MutableList<Pedido> = mutableListOf()
    val pedidosRealizados:MutableList<Pedido> = mutableListOf()
    var pedidosPendientesEntrega:MutableList<Pedido> = mutableListOf()

    fun avanzarDia(){
        dia++
    }
    fun trabajar(){
        listaTrabajadores.forEach { it.trabajar(this) }
    }
    fun buscarTransportistasDisponibles(): List<Transportista?> {

        return listaTrabajadores.filterIsInstance<Transportista>().filter { it.estaDisponible(this) }
    }
    fun entregarPedido(pedido: Pedido){
        val transportista = buscarTransportistasDisponibles().first()
        transportista.trabajar(this)
        pedido.entregado = true
        pedidosRealizados.add(pedido)
    }
    fun gestionarPedido(pedido: Pedido) {
        val mineralesPedido = pedido.listaMinerales.map { nombre ->
            MineralesPosibles.entries.find { it.nombre == nombre }
        }.filterNotNull()
        val tieneMinerales = mineralesPedido.all { mineral ->
            inventario.any { it.nombre == mineral.nombre && it.calidad.multiplicador >= pedido.calidadMinima.multiplicador }
        }
        if (tieneMinerales) {
            val precioACobrar = pedido.obtenerTotalValor()
            cobrarDinero(precioACobrar)

            val acciones = mutableListOf("Pedido procesado: ${pedido.nombreEmpresa}")
            historialAcciones[dia] = acciones
        } else {
            println("No se puede cumplir el pedido: ${pedido.nombreEmpresa}")
        }
    }

    fun cobrarDinero(dinero: Double){
        this.dinero += dinero
    }


    override fun toString(): String {
        return """
        Mina: ${nombre}
        Dinero: ${dinero}$
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