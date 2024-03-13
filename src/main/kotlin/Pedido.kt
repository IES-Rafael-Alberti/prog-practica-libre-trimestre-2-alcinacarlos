package org.practicatrim2

class Pedido(
    val id:Int = generarId(),
    val nombreEmpresa:String,
    var listaMinerales:MutableList<String>,
    var calidadMinima:Calidad,
    var entregado: Boolean = false
) {
    companion object{
        private var contador_id:Int = 0
        fun generarId(): Int {
            return ++contador_id
        }
    }
    private fun agregarMineral(mineral: String) {
        listaMinerales.add(mineral)
    }

    private fun eliminarMineral(mineral: String) {
        listaMinerales.remove(mineral)
    }
    fun obtenerTotalValor(): Double {
        var total = 0.0 //Valor total
        val nombresMinerales = listaMinerales.map { nombre ->
            MineralesPosibles.entries.find { it.nombre == nombre }!!
        }.toMutableList()
        for (mineral in nombresMinerales){
            total += mineral.valor
        }
        return total
    }
    override fun toString(): String {
        return """
            Pedido:
            ID: $id
            Empresa: $nombreEmpresa
            Minerales: $listaMinerales
            Calidad mínima: ${calidadMinima.nombre}
            Precio total: ${obtenerTotalValor()}$
            Entregado: ${entregado}
        """
    }

}