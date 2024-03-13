package org.practicatrim2

enum class MineralesPosibles(val nombre: String, val valor: Double) {
    // Minerales comunes
    CALCITA("Calcita", 2.0),
    FELDESPATO("Feldespato", 3.0),
    MICA("Mica", 4.0),

    // Minerales metálicos
    ORO("Oro", 1000.0),
    PLATA("Plata", 500.0),
    COBRE("Cobre", 100.0),
    HIERRO("Hierro", 50.0),

    // Minerales preciosos
    DIAMANTE("Diamante", 10000.0),
    RUBÍ("Rubí", 5000.0),
    ZAFIRO("Zafiro", 4000.0),
    ESMERALDA("Esmeralda", 3000.0),

    // Minerales raros
    OPALO("Ópalo", 2000.0),
    JADE("Jade", 1500.0),
    TURQUESA("Turquesa", 1000.0),
    LAPISLÁZULI("Lapislázuli", 500.0),

    // Otros
    HEMATITA("Hematite", 10.0),
    PIRITA("Pirita", 5.0),
    GALENA("Galena", 2.5),
    CARBÓN("Carbón", 1.5),
}
