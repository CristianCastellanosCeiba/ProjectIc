package com.example.domain.entity

import java.util.*

data class Motorcycle(var cc: Boolean, override var registration: String,
                      override var type: String, override var hourEntry: Date, override var hourExit: Date
): Vehicle(registration, hourEntry, hourExit, type)
