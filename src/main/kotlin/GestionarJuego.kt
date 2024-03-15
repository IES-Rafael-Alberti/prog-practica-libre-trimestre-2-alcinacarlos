package org.practicatrim2
import org.practicatrim2.Terminal.terminal
import com.github.ajalt.mordant.rendering.TextColors.*
import com.github.ajalt.mordant.terminal.*
import com.github.ajalt.mordant.terminal.YesNoPrompt
import kotlin.random.Random

object GestionarJuego {
    const val MINERAL_MINIMO_POR_PEDIDO = 3
    const val MINERAL_MAXIMO_POR_PEDIDO = 5
    const val MINERALES_MINIMOS_POR_MINERO = 2
    const val MINERALES_MAXIMOS_POR_MINERO = 10
    const val DIAS_ENTRE_PEDIDOS = 3

    fun presentarJuego(){
        println("Bienvenido a Mining Simulator Ultimate Deluxe Premium Edition (nombre muy original)")
        println("En este juego, comienzas siendo el dueño de una MINA, tu objetivo es gestionar la mina de la manera más eficiente sin llegar a bancarota")
        println("Cada 3 dias recibes un pedido que tienes que cumplir en un plazo")
        println("Cada 5 dias los trabajadores cobran su sueldo")
        println("Cada 10 dias tienes que pagar el alquiler de maquinaria")
        println("Si te quedas en bancarota pierdes")
        println("Con esta explicacion es suficiente, lo demás lo verás sobre la marcha, mucha suerte y cuidado con los gases!")
    }

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
            terminal.println(brightMagenta("Has contratado a: $transportista"))
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
            "Juan", "María", "Pedro", "Ana", "José", "Isabel", "Francisco", "David", "Laura", "Carlos"
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
        terminal.println((brightMagenta("Dia ${mina.dia}, Dinero ${mina.obtenerDinero()}")))
        var opcion:Int? = null
        while (opcion !in 0..9){
            Menu.mostrar()
            opcion = readln().toIntOrNull()
        }
        when(opcion){
            0 -> terminal.println(brightMagenta("Avanzando día..."))
            1 -> {
                terminal.println(brightMagenta("Total de trabajadores: ${mina.obtenerTrabajadores().size}"))
                mina.obtenerTrabajadores().forEach { it.mostrarTrabajador() }
            }
        }
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
    private fun logicaGestionarPedidoCadaDia(mina: Mina){
        val pedidosAEliminar = mutableListOf<Pedido>()
        for (pedido in mina.colaPedidos){
            if(mina.gestionarPedido(pedido)) pedidosAEliminar.add(pedido)
        }
        for (pedido in pedidosAEliminar){
            mina.colaPedidos.remove(pedido)
        }
    }
    fun primerDia(mina: Mina){
        val primerPedido = generarPedidoAleatorio()
        primerPedido.mostrarPedido()
        mina.añadirPedido(primerPedido)
        Thread.sleep(2000)
    }
    private fun añadirPedidosCadaXDias(mina: Mina){
        if (mina.dia % DIAS_ENTRE_PEDIDOS == 0){
            val pedidoNuevo = generarPedidoAleatorio()
            pedidoNuevo.mostrarPedido()
            mina.añadirPedido(pedidoNuevo)
            Thread.sleep(2000)
        }
    }
    fun iniciarMina(mina:Mina){
        do {
            iniciarDia(mina)
            añadirPedidosCadaXDias(mina)
            logicaGestionarPedidoCadaDia(mina)
            Thread.sleep(1000)

        }while (mina.consultarActividad())
    }
}