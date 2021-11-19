package com.example.domain.entity

import java.util.*

open class Vehicle(
    open var registration: String,
    open var hourEntry: Date,
    open var hourExit: Date,
    open var type: String
)