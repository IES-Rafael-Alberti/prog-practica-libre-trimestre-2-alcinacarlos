package org.practicatrim2

enum class Calidad(val multiplicador: Double, val nombre:String) {
    BAJA(0.3, "Baja"),
    MEDIA(0.5, "Media"),
    ALTA(0.7, "Alta"),
    EXCELENTE(1.0, "Excelente")
}