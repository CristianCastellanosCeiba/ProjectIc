package com.example.domain.entity

import java.util.*

abstract class Vehicle(
    open var registration: String,
    open var hourEntry: Date,
    open var type: String
)