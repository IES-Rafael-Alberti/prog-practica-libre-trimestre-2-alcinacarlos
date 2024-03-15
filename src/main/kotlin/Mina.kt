package org.practicatrim2

class Mina(
    val nombre:String,
    var dinero:Double,
    private var listaTrabajadores:MutableList<Trabajador>
) {
    var dia: Int = 0
    var colaPedidos:MutableList<Pedido> = mutableListOf()
    val inventario:MutableList<Mineral> = mutableListOf()
    private var historialAcciones: MutableMap<Int, MutableList<String>> = mutableMapOf()
    private var activa:Boolean = true //Indica si la mina está terminada o no
    private val pedidosRealizados:MutableList<Pedido> = mutableListOf()
    private var pedidosPendientesEntrega:MutableSet<Pedido> = mutableSetOf()

    fun avanzarDia(){
        dia++
        minar()
    }
    fun obtenerDinero() = dinero
    fun obtenerTrabajadores() = listaTrabajadores
    fun contratarTrabajador(trabajador: Trabajador){
        listaTrabajadores.add(trabajador)
    }
    fun consultarActividad():Boolean{
        return activa && dinero < 0
    }
    private fun minar(){
        listaTrabajadores.filterIsInstance<Minero>().forEach { it.trabajar(this) }
        listaTrabajadores.filterIsInstance<Minero>().forEach { it.cobrar(this) }

    }
    private fun buscarTransportistasDisponibles(): Transportista? {
        var transportistaDisponible = listaTrabajadores.filterIsInstance<Transportista>().filter{ it.estaDisponible(this.dia) }
        if (transportistaDisponible.isEmpty()){
            return GestionarJuego.contratarTransportista(this)
        }else{
            return transportistaDisponible.first()
        }
    }
    private fun entregarPedido(pedido: Pedido):Boolean{
        val transportista = buscarTransportistasDisponibles()
        if (transportista != null) {
            transportista.trabajar(this)
            pedido.entregado = true
            pedidosRealizados.add(pedido)
            return true
        }else{
            return false
        }
    }
    fun añadirPedido(pedido: Pedido){
        colaPedidos.add(pedido)
    }
    fun checkearMineralesPedido(pedido: Pedido): Pair<Boolean, List<Mineral>> {
        val mineralesPedido = pedido.listaMinerales
        val tieneMinerales = mineralesPedido.all { mineral ->
            inventario.any { it.nombre == mineral.nombre && it.calidad.multiplicador >= pedido.calidadMinima.multiplicador }
        }
        return Pair(tieneMinerales, mineralesPedido)
    }
    fun gestionarPedido(pedido: Pedido):Boolean {
        val mineralesComprobados = checkearMineralesPedido(pedido)
        if (mineralesComprobados.first) {
            if (entregarPedido(pedido)){
                val precioACobrar = pedido.obtenerTotalValor()
                cobrarDinero(precioACobrar)

                //eliminar minerales

                for (mineral in mineralesComprobados.second) {
                    inventario.removeAll { it.nombre == mineral.nombre && it.calidad.multiplicador >= pedido.calidadMinima.multiplicador }
                }

                //eliminar pedido de la cola
                //colaPedidos.remove(pedido)

                //registra la accion del pedido
                val acciones = mutableListOf("Pedido entregado a: ${pedido.nombreEmpresa}")
                historialAcciones[dia] = acciones
                return true
            } else{
                println("No se ha podido entregar el pedido: ${pedido.nombreEmpresa}")
                pedidosPendientesEntrega.add(pedido)
                return false
            }

        } else {
            println("No se puede cumplir el pedido: ${pedido.nombreEmpresa}")
            return false
        }
    }

    private fun cobrarDinero(dinero: Double){
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