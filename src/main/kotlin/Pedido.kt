package org.practicatrim2

class Pedido(
    val id:Int = generarId(),
    val nombreEmpresa:String,
    var listaMinerales:MutableList<Mineral>,
    var calidadMinima:Int
) {
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
        return listaMinerales.sumOf { it.valor*it.calidad.valor }
    }
    override fun toString(): String {
        return """
            Pedido:
            ID: $id
            Empresa: $nombreEmpresa
            Minerales: $listaMinerales
            Calidad mínima: $calidadMinima
            Precio total: ${obtenerTotalValor()}$
        """
    }

}