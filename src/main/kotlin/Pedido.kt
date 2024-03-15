package org.practicatrim2
import org.practicatrim2.Terminal.terminal
import com.github.ajalt.mordant.table.table
import com.github.ajalt.mordant.rendering.TextColors.*

class Pedido(
    val nombreEmpresa:String,
    var listaMinerales:MutableList<Mineral>,
    var calidadMinima:Calidad,
    var entregado: Boolean = false
) {
    private val id:Int = generarId()
    companion object{
        private var contador_id:Int = 0
        fun generarId(): Int {
            return ++contador_id
        }
    }
    private fun agregarMineral(mineral: Mineral) {
        listaMinerales.add(mineral)
    }

    private fun eliminarMineral(mineral: Mineral) {
        listaMinerales.remove(mineral)
    }
    fun obtenerTotalValor(): Double {
        var total = 0.0 //Valor total
        val nombresMinerales = listaMinerales.map { mineral ->
            MineralesPosibles.entries.find { it.nombre == mineral.nombre }!!
        }
        for (mineral in nombresMinerales){
            total += mineral.valor
        }
        return total
    }
    fun listaMineralesAString():String{
        return listaMinerales.joinToString(", ") { it.nombre }
    }
    fun mostrarPedido() {
        terminal.println(brightYellow("NUEVO PEDIDO!!!!"))
        terminal.println(table {
            borderStyle = green
            style = brightWhite
            header {
                row("Pedido", "Valores")
                style = brightYellow
            }
            body {
                row("ID", id)
                row("Nombre de la Empresa:", nombreEmpresa)
                row("Minerales", listaMineralesAString())
                row("Calidad Minima", calidadMinima.nombre)
                row("Entregado", entregado)
            }
        })
    }

}