package org.practicatrim2
import org.practicatrim2.Terminal.terminal
import com.github.ajalt.mordant.rendering.TextColors.*
import com.github.ajalt.mordant.table.table
import com.github.ajalt.mordant.terminal.YesNoPrompt
import kotlin.random.Random

object GestionarJuego {
    const val MINERAL_MINIMO_POR_PEDIDO = 3
    const val MINERAL_MAXIMO_POR_PEDIDO = 5
    const val MINERALES_MINIMOS_POR_MINERO = 2
    const val MINERALES_MAXIMOS_POR_MINERO = 10
    const val DIAS_ENTRE_PEDIDOS = 3

    fun generarMineralAleatorios():Mineral{
        val nombre = MineralesPosibles.entries.random().nombre
        val valorMineral = MineralesPosibles.entries.random().valor
        val calidadMineral = Calidad.entries.toTypedArray().random()
        return Mineral(nombre, valorMineral, calidadMineral)
    }
    fun contratarTransportista(mina: Mina):Transportista?{
        terminal.println(brightRed("No hay transportistas disponibles"))
        val respuesta = YesNoPrompt("Quieres contratar a otro transportista?", terminal).ask()
        if (respuesta!!){
            val transportista = generarTrabajadorAleatorio("transportista") as Transportista
            mina.contratarTrabajador(transportista)
            terminal.println(brightMagenta("Has contratado a un transportista!!"))
            transportista.mostrarTrabajador()
            return transportista
        }else{
            terminal.println(brightBlue("No has contratado a ninguna transportista"))
            terminal.println(brightCyan("El pedido se añadirá a la cola hasta que exista un tranportista disponible"))
            return null
        }
    }
    fun generarTrabajadorAleatorio(tipo:String): Trabajador {
        val nombreAleatorio = generarNombreAleatorio()
        val edadAleatoria = generarEdadAleatoria()
        val dniAleatorio = generarDniAleatorio()
        val cargoAleatorio = generarCargoAleatorio()
        return when (tipo){
            "transportista" -> Transportista(
                nombreAleatorio,
                edadAleatoria,
                dniAleatorio,
                Cargo.CONDUCTOR.salario,
                Cargo.CONDUCTOR,
                false
            )
            "minero" -> Minero(
                nombreAleatorio,
                edadAleatoria,
                dniAleatorio,
                cargoAleatorio.salario,
                cargoAleatorio,
                false
            )

            else -> {throw IllegalArgumentException("Trabajador no especificado")}
        }

    }
    private fun generarNombreAleatorio(): String {
        val nombres = listOf(
            "Juan", "María", "Pedro", "Ana", "José", "Isabel", "Francisco", "David", "Laura", "Carlos", "Diego", "Lucas"
        )
        return nombres.random()
    }
    private fun generarEdadAleatoria(): Int {
        return (18..65).random()
    }

    private fun generarDniAleatorio(): String {
        val letras = listOf("A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "Ñ", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z")
        val numero = (10000000..99999999).random()
        val letra = letras.random()
        return "$numero$letra"
    }

    private fun generarCargoAleatorio(): Cargo {
        return Cargo.entries.toTypedArray().random()
    }
    private fun iniciarDia(mina:Mina){
        mina.avanzarDia()
        terminal.println(brightBlue("Los mineros han picado muy fuertemente hoy, tienes en total ${mina.obtenerInventario().size} minerales"))
        Menu.buclePrincipal(mina)
    }
    fun contratarTrabajador(mina: Mina){
        var trabajador:Trabajador? = null
        while (trabajador == null){
            terminal.println(brightCyan("Qué tipo de trabajador quieres contratar? (transportista o minero)?"))
            try {
                val tipoTrabajador = readln()
                trabajador = generarTrabajadorAleatorio(tipoTrabajador)
                terminal.println(brightMagenta("Has contradado a un trabajador!!"))
                trabajador.mostrarTrabajador()
            }catch (e:IllegalArgumentException){
                println(e)
            }
        }
        mina.contratarTrabajador(trabajador)
    }
    private fun generarNombreEmpresaAleatoria(): String {
        return listOf("Nvidia", "Alibaba", "Samsung", "Taiwan Semiconductor", "Sony", "Home Depot", "Procter & Gamble", "Visa", "Mastercard", "Nike", "JPMorgan Chase", "Exxon Mobil", "Walmart", "Chevron", "Bank of China", "Industrial and Commercial Bank of China", "Wells Fargo", "Coca-Cola", "Nestlé", "Intel", "Broadcom", "Pfizer", "Merck & Co.", "AT&T", "Verizon Communications", "Cisco Systems", "PayPal", "Abbott Laboratories").random()
    }
    private fun generarCalidadAleatoria():Calidad{
        return Calidad.entries.random()
    }
    fun generarPedidoAleatorio():Pedido{
        val mineralesAleatorios = mutableListOf<Mineral>()
        repeat(Random.nextInt(MINERAL_MINIMO_POR_PEDIDO, MINERAL_MAXIMO_POR_PEDIDO)){
            mineralesAleatorios.add(generarMineralAleatorios())
        }
        return Pedido(
            generarNombreEmpresaAleatoria(),
            mineralesAleatorios,
            generarCalidadAleatoria()
        )
    }
    fun logicaGestionarPedidoCadaDia(mina: Mina){
        val pedidosAEliminar = mutableListOf<Pedido>()
        for (pedido in mina.colaPedidos){
            if(mina.gestionarPedido(pedido)) pedidosAEliminar.add(pedido)
        }
        if (pedidosAEliminar.isEmpty()) terminal.println(brightRed("No hay pedidos por gestionar, intenta conseguir alguno o completa los minerales que faltan en aquellos pedidos que ya tengas"))
        for (pedido in pedidosAEliminar){
            terminal.println(brightYellow("Pedido ${pedido.nombreEmpresa} ha sido entregado!!"))
            mina.colaPedidos.remove(pedido)
        }
    }
    fun primerDia(mina: Mina){
        mina.añadirPedido(generarPedidoAleatorio())
    }
    fun añadirPedidoRandom(mina: Mina){
        terminal.println(brightYellow("NUEVO PEDIDO!!!!"))
        val pedidoNuevo = generarPedidoAleatorio()
        pedidoNuevo.mostrarPedido()
        mina.añadirPedido(pedidoNuevo)
    }
    private fun añadirPedidosCadaXDias(mina: Mina){
        if (mina.dia % DIAS_ENTRE_PEDIDOS == 0) {
            añadirPedidoRandom(mina)
            Thread.sleep(3000)
        }
    }
    fun iniciarMina(mina:Mina){
        do {
            iniciarDia(mina)
            añadirPedidosCadaXDias(mina)
            //logicaGestionarPedidoCadaDia(mina)
            Thread.sleep(1000)

        }while (mina.consultarActividad())
    }
}