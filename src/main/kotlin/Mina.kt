package org.practicatrim2
/**
 * Representa una mina.
 *
 * @param nombre El nombre de la mina.
 * @param dinero El dinero disponible en la mina.
 * @param listaTrabajadores La lista de trabajadores de la mina.
 */
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

    /**
     * Avanza un día en la mina.
     */
    fun avanzarDia(){
        dia++
        minar()
    }

    /**
     * Obtiene la lista de pedidos en la cola.
     */
    fun obtenerPedidos() = colaPedidos

    /**
     * Finaliza la mina.
     */
    fun finalizar(){
        activa = false
    }

    /**
     * Añade una acción al historial de acciones.
     *
     * @param acciones La acción a añadir.
     */
    private fun añadirAHistorialAcciones(acciones: String){
        if (historialAcciones[dia] == null) historialAcciones[dia] = mutableListOf(acciones)
        else historialAcciones[dia]!!.add(acciones)
    }

    /**
     * Obtiene el historial de acciones como una cadena.
     */
    fun obtenerHistorialAcciones(): String {
        return historialAcciones.map { (dia, acciones) ->
            "Dia $dia -> ${acciones.joinToString(", ")}"
        }.joinToString("\n")
    }

    /**
     * Obtiene la lista de pedidos realizados.
     */
    fun obtenerpedidosRealizados(): List<Pedido> = pedidosRealizados

    /**
     * Añade un mineral al inventario.
     *
     * @param mineral El mineral a añadir.
     */
    fun añadirInventario(mineral: Mineral){
        inventario.add(mineral)
    }

    /**
     * Obtiene la lista de minerales del inventario.
     */
    fun obtenerInventario():List<Mineral> = inventario

    /**
     * Obtiene el dinero disponible.
     */
    fun obtenerDinero() = dinero

    /**
     * Obtiene la lista de trabajadores.
     */
    fun obtenerTrabajadores() = listaTrabajadores

    /**
     * Contrata a un nuevo trabajador.
     *
     * @param trabajador El trabajador a contratar.
     */
    fun contratarTrabajador(trabajador: Trabajador){
        listaTrabajadores.add(trabajador)
    }

    /**
     * Consulta si la mina está activa.
     */
    fun consultarActividad():Boolean{
        return activa && dinero > 0
    }

    /**
     * Simula la actividad de los mineros en la mina.
     */
    private fun minar(){
        val mineros = listaTrabajadores.filterIsInstance<Minero>()
        mineros.forEach { it.trabajar(this) }
        mineros.forEach { it.cobrar(this) }
        val acciones = "Los ${mineros.size} mineros han minado correctamente"
        añadirAHistorialAcciones(acciones)

    }

    /**
     * Busca transportistas disponibles para entregar un pedido.
     */
    private fun buscarTransportistasDisponibles(): Transportista? {
        val transportistaDisponible = listaTrabajadores.filterIsInstance<Transportista>().filter{ it.estaDisponible(this.dia) }
        return if (transportistaDisponible.isEmpty()){
            GestionarJuego.contratarTransportista(this)
        }else{
            transportistaDisponible.first()
        }
    }
    /**
     * Intenta entregar un pedido.
     *
     * @param pedido El pedido a entregar.
     *
     * @return `true` si el pedido se ha entregado correctamente, `false` en caso contrario.
     */
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

    /**
     * Añade un pedido a la cola.
     *
     * @param pedido El pedido a añadir.
     */
    fun añadirPedido(pedido: Pedido){
        colaPedidos.add(pedido)
    }

    /**
     * Comprueba si la mina tiene los minerales necesarios para un pedido.
     *
     * @param pedido El pedido a comprobar.
     *
     * @return Un par de `Boolean` y `List<Mineral>` que indica si la mina tiene los minerales necesarios y la lista de minerales del pedido.
     */
    private fun checkearMineralesPedido(pedido: Pedido): Pair<Boolean, List<Mineral>> {
        val mineralesPedido = pedido.listaMinerales
        val tieneMinerales = mineralesPedido.all { mineral ->
            inventario.any { it.nombre == mineral.nombre && it.calidad.multiplicador >= pedido.calidadMinima.multiplicador }
        }
        return Pair(tieneMinerales, mineralesPedido)
    }

    /**
     * Gestiona un pedido (lo entrega si es posible).
     *
     * @param pedido El pedido a gestionar.
     *
     * @return `true` si el pedido se ha gestionado correctamente, `false` en caso contrario.
     */
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
                return false
            }

        } else {
            return false
        }
    }

    /**
     * Cobra dinero a la mina.
     *
     * @param dinero El dinero a cobrar.
     */
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