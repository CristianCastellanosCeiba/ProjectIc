package com.example.projectic.ui

 import android.os.Bundle
 import android.widget.Toast
 import androidx.activity.viewModels
 import androidx.appcompat.app.AppCompatActivity
 import com.example.domain.entity.Auto
 import com.example.domain.entity.Motorcycle
 import com.example.projectic.databinding.ActivityMainBinding
 import com.example.projectic.ui.adapters.autos.AutosAdapter
 import com.example.projectic.ui.adapters.motorcycles.MotorcyclesAdapter
 import com.example.projectic.viewModel.AutoViewModel
 import com.example.projectic.viewModel.MotorcycleViewModel
 import dagger.hilt.android.AndroidEntryPoint
 import java.text.SimpleDateFormat
 import java.util.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapterAutos: AutosAdapter
    private lateinit var adapterMotorcycle: MotorcyclesAdapter
    private val motorcycleViewModel: MotorcycleViewModel by viewModels()
    private val autoViewModel: AutoViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button2.setOnClickListener {
            getRegistryAuto()
        }
        binding.button.setOnClickListener {
            getDeleteAuto()
        }
        binding.button3.setOnClickListener {
            getPrice()
        }
        binding.buttonViewAutos.setOnClickListener {
            getDataDbAutos()
        }
        binding.buttonViewMotorcycle.setOnClickListener {
            getDataBdMotorcycles()
        }
        observer()
    }

    private fun getRegistryAuto() {
        if (binding.editText.text.toString().isNotEmpty()) {
            when {
                binding.radioBtn1.isChecked -> {
                    autoViewModel.getRegistryAuto(Auto(binding.editText.text.toString(), getCurrentDateTime(), "Auto"))
                    binding.editText.setText("")
                }
                binding.radioBtn2.isChecked -> {
                    if (binding.editText2.text.isNotEmpty()) {
                        motorcycleViewModel.getRegistryMotorcycle(Motorcycle(
                            Integer.parseInt(binding.editText2.text.toString()),
                            binding.editText.text.toString(), "Motorcycle", getCurrentDateTime()
                        ))
                        binding.editText.setText("")
                        binding.editText2.setText("")
                    } else {
                        Toast.makeText(this, "El campo Ingrese cilindraje no puede estar vacío para las motos.", Toast.LENGTH_LONG).show()
                    }
                }
                else -> {
                    Toast.makeText(this, "¡Por favor seleccione el tipo de vehículo, gracias!", Toast.LENGTH_LONG).show()
                }
            }
            observer()
        } else {
            Toast.makeText(this, "El campo Placa del vehículo no puede estar vacío", Toast.LENGTH_LONG).show()
        }
    }

    private fun getDeleteAuto() {
        if (binding.editText.text.isNotEmpty()) {
            when {
                binding.radioBtn1.isChecked -> {
                    autoViewModel.getDeleteAuto(binding.editText.text.toString())
                    binding.editText.setText("")
                }
                binding.radioBtn2.isChecked -> {
                    motorcycleViewModel.getDeleteMotorcycle(binding.editText.text.toString())
                    binding.editText.setText("")
                }
                else -> {
                    Toast.makeText(this, "¡Por favor seleccione el tipo de vehículo, gracias!", Toast.LENGTH_LONG).show()
                }
            }
            observer()
        } else {
            Toast.makeText(this, "El campo Placa del vehículo no puede estar vacío", Toast.LENGTH_LONG).show()
        }
    }

    private fun getPrice() {
        if (binding.editText.text.isNotEmpty()) {
            when {
                binding.radioBtn1.isChecked -> {
                    autoViewModel.getPrice(binding.editText.text.toString()).observe(this, {
                        Toast.makeText(this,"Valor a pagar por el auto: $it", Toast.LENGTH_LONG).show()
                    })
                }
                binding.radioBtn2.isChecked -> {
                    motorcycleViewModel.getPrice(binding.editText.text.toString()).observe(this, {
                        Toast.makeText(this, "Valor a pagar por la moto: $it", Toast.LENGTH_LONG).show()
                    })
                }
                else -> {
                    Toast.makeText(this, "¡Por favor seleccione el tipo de vehículo, gracias!", Toast.LENGTH_LONG).show()
                }
            }
        } else {
            Toast.makeText(this, "El campo Placa del vehículo no puede estar vacío", Toast.LENGTH_LONG).show()
        }
    }

    private fun observer() {

        autoViewModel.getAllAutos().observe(this, {
            binding.textView2.text = "Autos actualmente $it"
        })

        motorcycleViewModel.getAllMotorcycle().observe(this, {
            binding.textView3.text = "Motos actualmente $it"
        })

        autoViewModel.errors.observe(this, {
            Toast.makeText(this,"${it.message}",Toast.LENGTH_SHORT).show()
        })

        motorcycleViewModel.errorsMotorcycle.observe(this, {
            Toast.makeText(this, "${it.message}", Toast.LENGTH_SHORT).show()
        })
    }

    private fun getDataDbAutos() {
        autoViewModel.getListAutos().observe(this, {
            if (it.result.isEmpty()) {
                Toast.makeText(this, "No hay registros.", Toast.LENGTH_LONG).show()
            } else {
                adapterAutos = AutosAdapter(it.result)
                println("Este es el resultado ${it.result.toMutableList()}")
                binding.rcVehicles.adapter = adapterAutos
            }
        })
    }

    private fun getDataBdMotorcycles() {
        motorcycleViewModel.getListMotorcycles().observe(this, {
            if (it.result.isEmpty()) {
                Toast.makeText(this, "No hay registros.", Toast.LENGTH_LONG).show()
            } else {
                adapterMotorcycle = MotorcyclesAdapter(it.result)
                binding.rcVehicles.adapter = adapterMotorcycle
            }
        })
    }

    fun Date.toString(format: String, locale: Locale = Locale.getDefault()): String {
        val formatter = SimpleDateFormat(format, locale)
        return formatter.format(this)
    }

    fun getCurrentDateTime(): Date { return Calendar.getInstance().time }


}