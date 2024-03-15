package org.practicatrim2
import org.practicatrim2.Terminal.terminal
import com.github.ajalt.mordant.rendering.TextColors.*
import com.github.ajalt.mordant.table.table
import com.github.ajalt.mordant.terminal.YesNoPrompt
import kotlin.random.Random

/**
 * Esta clase se encarga de la lógica principal del juego.
 */
object GestionarJuego {

    /**
     * Constante que define el número mínimo de minerales por pedido.
     */
    const val MINERAL_MINIMO_POR_PEDIDO = 3

    /**
     * Constante que define el número máximo de minerales por pedido.
     */
    const val MINERAL_MAXIMO_POR_PEDIDO = 5

    /**
     * Constante que define el número mínimo de minerales que un minero puede extraer por día.
     */
    const val MINERALES_MINIMOS_POR_MINERO = 2

    /**
     * Constante que define el número máximo de minerales que un minero puede extraer por día.
     */
    const val MINERALES_MAXIMOS_POR_MINERO = 10

    /**
     * Constante que define el número de días que deben pasar entre cada pedido nuevo.
     */
    const val DIAS_ENTRE_PEDIDOS = 3

    /**
     * Función que genera un mineral aleatorio.
     *
     * @return Un objeto `Mineral` aleatorio.
     */
    fun generarMineralAleatorios(): Mineral {
        val nombre = MineralesPosibles.entries.random().nombre
        val valorMineral = MineralesPosibles.entries.random().valor
        val calidadMineral = Calidad.entries.toTypedArray().random()
        return Mineral(nombre, valorMineral, calidadMineral)
    }

    /**
     * Función que intenta contratar un transportista para la mina.
     *
     * @param mina La mina a la que se quiere contratar un transportista.
     *
     * @return Un objeto `Transportista` si se ha podido contratar uno, o `null` si no.
     */
    fun contratarTransportista(mina: Mina): Transportista? {
        terminal.println(brightRed("No hay transportistas disponibles"))
        val respuesta = YesNoPrompt("Quieres contratar a otro transportista?", terminal).ask()
        if (respuesta!!) {
            val transportista = generarTrabajadorAleatorio("transportista") as Transportista
            mina.contratarTrabajador(transportista)
            terminal.println(brightMagenta("Has contratado a un transportista!!"))
            transportista.mostrarTrabajador()
            return transportista
        } else {
            terminal.println(brightBlue("No has contratado a ninguna transportista"))
            terminal.println(brightCyan("El pedido se añadirá a la cola hasta que exista un tranportista disponible"))
            return null
        }
    }

    /**
     * Función que genera un trabajador aleatorio.
     *
     * @param tipo El tipo de trabajador que se quiere generar ("transportista" o "minero").
     *
     * @return Un objeto `Trabajador` aleatorio del tipo especificado.
     */
    fun generarTrabajadorAleatorio(tipo: String): Trabajador {
        val nombreAleatorio = generarNombreAleatorio()
        val edadAleatoria = generarEdadAleatoria()
        val dniAleatorio = generarDniAleatorio()
        val cargoAleatorio = generarCargoAleatorio()
        return when (tipo) {
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
            else -> { throw IllegalArgumentException("Trabajador no especificado") }
        }
    }

    /**
     * Función que genera un nombre aleatorio.
     *
     * @return Un nombre aleatorio.
     */
    private fun generarNombreAleatorio(): String {
        val nombres = listOf(
            "Juan", "María", "Pedro", "Ana", "José", "Isabel", "Francisco", "David", "Laura", "Carlos", "Diego", "Lucas"
        )
        return nombres.random()
    }
    /**
     * Función que genera una edad aleatoria.
     *
     * @return Una edad aleatoria entre 18 y 65 años.
     */
    private fun generarEdadAleatoria(): Int {
        return (18..65).random()
    }

    /**
     * Función que genera un DNI aleatorio.
     *
     * @return Un DNI aleatorio.
     */
    private fun generarDniAleatorio(): String {
        val letras = listOf(
            "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "Ñ", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"
        )
        val numero = (10000000..99999999).random()
        val letra = letras.random()
        return "$numero$letra"
    }

    /**
     * Función que genera un cargo aleatorio.
     *
     * @return Un objeto `Cargo` aleatorio.
     */
    private fun generarCargoAleatorio(): Cargo {
        return Cargo.entries.toTypedArray().random()
    }

    /**
     * Función que avanza un día en la mina.
     *
     * @param mina La mina a la que se le quiere avanzar un día.
     */
    private fun iniciarDia(mina: Mina) {
        mina.avanzarDia()
        terminal.println(brightBlue("Los mineros han picado muy fuertemente hoy, tienes en total ${mina.obtenerInventario().size} minerales"))
        Menu.buclePrincipal(mina)
    }

    /**
     * Función que permite al jugador contratar un trabajador.
     *
     * @param mina La mina a la que se quiere contratar un trabajador.
     */
    fun contratarTrabajador(mina: Mina) {
        var trabajador: Trabajador? = null
        while (trabajador == null) {
            terminal.println(brightCyan("Qué tipo de trabajador quieres contratar? (transportista o minero)?"))
            try {
                val tipoTrabajador = readln()
                trabajador = generarTrabajadorAleatorio(tipoTrabajador)
                terminal.println(brightMagenta("Has contradado a un trabajador!!"))
                trabajador.mostrarTrabajador()
            } catch (e: IllegalArgumentException) {
                println(e)
            }
        }
        mina.contratarTrabajador(trabajador)
    }

    /**
     * Función que genera un nombre de empresa aleatorio.
     *
     * @return Un nombre de empresa aleatorio.
     */
    private fun generarNombreEmpresaAleatoria(): String {
        return listOf(
            "Nvidia", "Alibaba", "Samsung", "Taiwan Semiconductor", "Sony", "Home Depot", "Procter & Gamble", "Visa", "Mastercard", "Nike", "JPMorgan Chase", "Exxon Mobil", "Walmart", "Chevron", "Bank of China", "Industrial and Commercial Bank of China", "Wells Fargo", "Coca-Cola", "Nestlé", "Intel", "Broadcom", "Pfizer", "Merck & Co.", "AT&T", "Verizon Communications", "Cisco Systems", "PayPal", "Abbott Laboratories"
        ).random()
    }

    /**
     * Función que genera una calidad aleatoria.
     *
     * @return Un objeto `Calidad` aleatorio.
     */
    private fun generarCalidadAleatoria(): Calidad {
        return Calidad.entries.random()
    }

    /**
     * Función que genera un pedido aleatorio.
     *
     * @return Un objeto `Pedido` aleatorio.
     */
    fun generarPedidoAleatorio(): Pedido {
        val mineralesAleatorios = mutableListOf<Mineral>()
        repeat(Random.nextInt(MINERAL_MINIMO_POR_PEDIDO, MINERAL_MAXIMO_POR_PEDIDO)) {
            mineralesAleatorios.add(generarMineralAleatorios())
        }
        return Pedido(
            generarNombreEmpresaAleatoria(),
            mineralesAleatorios,
            generarCalidadAleatoria()
        )
    }
    /**
     * Función que se encarga de la lógica de gestionar los pedidos cada día.
     *
     * @param mina La mina a la que se le quieren gestionar los pedidos.
     */
    fun logicaGestionarPedidoCadaDia(mina: Mina){
        val pedidosAEliminar = mutableListOf<Pedido>()
        for (pedido in mina.obtenerPedidos()){
            if(mina.gestionarPedido(pedido)) pedidosAEliminar.add(pedido)
        }
        if (pedidosAEliminar.isEmpty()) terminal.println(brightRed("No hay pedidos por gestionar, intenta conseguir alguno o completa los minerales que faltan en aquellos pedidos que ya tengas"))
        for (pedido in pedidosAEliminar){
            terminal.println(brightYellow("Pedido ${pedido.nombreEmpresa} ha sido entregado!!"))
            terminal.println(brightYellow("Te han dado ${pedido.obtenerTotalValor()}$"))
            mina.colaPedidos.remove(pedido)
        }
    }
    /**
     * Función que se encarga de la lógica del primer día de la mina.
     *
     * @param mina La mina a la que se le quiere iniciar el primer día.
     */
    fun primerDia(mina: Mina) {
        mina.añadirPedido(generarPedidoAleatorio())
    }

    /**
     * Función que permite al jugador añadir un pedido aleatorio a la mina.
     *
     * @param mina La mina a la que se le quiere añadir un pedido aleatorio.
     */
    fun añadirPedidoRandom(mina: Mina) {
        terminal.println(brightYellow("NUEVO PEDIDO!!!!"))
        val pedidoNuevo = generarPedidoAleatorio()
        pedidoNuevo.mostrarPedido()
        mina.añadirPedido(pedidoNuevo)
    }

    /**
     * Función que se encarga de añadir pedidos aleatorios a la mina cada cierto número de días.
     *
     * @param mina La mina a la que se le quieren añadir pedidos aleatorios.
     */
    private fun añadirPedidosCadaXDias(mina: Mina) {
        if (mina.dia % DIAS_ENTRE_PEDIDOS == 0) {
            añadirPedidoRandom(mina)
            Thread.sleep(3000)
        }
    }

    /**
     * Función que inicia la lógica del juego.
     *
     * @param mina La mina a la que se le quiere iniciar el juego.
     */
    fun iniciarMina(mina: Mina) {
        do {
            iniciarDia(mina)
            añadirPedidosCadaXDias(mina)
            //logicaGestionarPedidoCadaDia(mina)
            Thread.sleep(1000)
        } while (mina.consultarActividad())
    }
}