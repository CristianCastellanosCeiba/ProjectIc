package com.example.infrastructure.exception

class NotDeleteCar: Exception {
    constructor(message: String): super(message)
}