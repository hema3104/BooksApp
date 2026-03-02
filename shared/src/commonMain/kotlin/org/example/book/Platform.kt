package org.example.book

//each platform must provide its own implementation
interface Platform {
    val name: String
}

expect fun getPlatform(): Platform //defines expected platform class