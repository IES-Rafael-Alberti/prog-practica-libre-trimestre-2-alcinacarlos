package org.practicatrim2

class Mina(
    val nombre:String,
    var dinero:Double,
    private var listaTrabajadores:MutableList<Trabajador>
) {
    var dia: Int = 0
    var colaPedidos:MutableList<Pedido> = mutableListOf()
    private val inventario:MutableList<Mineral> = mutableListOf()
    private var historialAcciones: MutableMap<Int, MutableList<String>> = mutableMapOf()
    private var activa:Boolean = true //Indica si la mina está terminada o no
    private val pedidosRealizados:MutableList<Pedido> = mutableListOf()
    private var pedidosPendientesEntrega:MutableSet<Pedido> = mutableSetOf()

    fun avanzarDia(){
        dia++
        minar()
    }
    fun obtenerPedidos() = colaPedidos
    fun finalizar(){
        activa = false
    }
    private fun añadirAHistorialAcciones(acciones: String){
        if (historialAcciones[dia] == null) historialAcciones[dia] = mutableListOf(acciones)
        else historialAcciones[dia]!!.add(acciones)
    }
    fun obtenerHistorialAcciones(): String {
        return historialAcciones.map { (dia, acciones) ->
            "Dia $dia -> ${acciones.joinToString(", ")}"
        }.joinToString("\n")
    }
    fun obtenerpedidosPendientesEntrega() = pedidosPendientesEntrega
    fun obtenerpedidosRealizados() = pedidosRealizados

    fun añadirInventario(mineral: Mineral){
        inventario.add(mineral)
    }
    fun obtenerInventario():List<Mineral> = inventario
    fun obtenerDinero() = dinero
    fun obtenerTrabajadores() = listaTrabajadores
    fun contratarTrabajador(trabajador: Trabajador){
        listaTrabajadores.add(trabajador)
    }
    fun consultarActividad():Boolean{
        return activa && dinero > 0
    }
    private fun minar(){
        val mineros = listaTrabajadores.filterIsInstance<Minero>()
        mineros.forEach { it.trabajar(this) }
        mineros.forEach { it.cobrar(this) }
        val acciones = "Los ${mineros.size} mineros han minado correctamente"
        añadirAHistorialAcciones(acciones)

    }
    private fun buscarTransportistasDisponibles(): Transportista? {
        val transportistaDisponible = listaTrabajadores.filterIsInstance<Transportista>().filter{ it.estaDisponible(this.dia) }
        return if (transportistaDisponible.isEmpty()){
            GestionarJuego.contratarTransportista(this)
        }else{
            transportistaDisponible.first()
        }
    }
    private fun entregarPedido(pedido: Pedido):Boolean{
        val transportista = buscarTransportistasDisponibles()
        if (transportista != null) {
            transportista.trabajar(this)
            pedido.entregado = true
            pedidosRealizados.add(pedido)
            val acciones = "Transportista ${transportista.nombre} con DNI ${transportista.dni} entrega pedido a: ${pedido.nombreEmpresa}"
            añadirAHistorialAcciones(acciones)
            return true
        }else{
            return false
        }
    }
    fun añadirPedido(pedido: Pedido){
        colaPedidos.add(pedido)
    }
    private fun checkearMineralesPedido(pedido: Pedido): Pair<Boolean, List<Mineral>> {
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

                return true
            } else{
                val acciones = "No se ha podido entregar el pedido: ${pedido.nombreEmpresa}"
                añadirAHistorialAcciones(acciones)
                pedidosPendientesEntrega.add(pedido)
                return false
            }

        } else {
            return false
        }
    }

    private fun cobrarDinero(dinero: Double){
        this.dinero += dinero
    }


    override fun toString(): String {
        return """
        Mina: $nombre
        Dinero: ${dinero}$
        Activa: $activa
        Día: $dia
        Trabajadores: ${listaTrabajadores.size}
        Pedidos realizados: ${pedidosRealizados.size}
        Historial de acciones:
        ${historialAcciones.map { (dia, acciones) ->
            "$dia: ${acciones.joinToString(", ")}"
        }.joinToString("\n")}
    """
    }

}