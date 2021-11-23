package com.example.projectic

 import android.os.Bundle
 import android.widget.Toast
 import androidx.appcompat.app.AppCompatActivity
 import com.example.domain.entity.Auto
 import com.example.domain.entity.Motorcycle
 import com.example.projectic.databinding.ActivityMainBinding
 import com.example.projectic.viewModel.AutoViewModel
 import com.example.projectic.viewModel.MotorcycleViewModel
 import dagger.hilt.android.AndroidEntryPoint
 import java.text.SimpleDateFormat
 import java.util.*
 import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    @Inject
    lateinit var autoViewModel: AutoViewModel
    @Inject
    lateinit var motorcycleViewModel: MotorcycleViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //createDependencies()

        binding.button2.setOnClickListener {
            getRegistryAuto()
        }
        binding.button.setOnClickListener {
            getDeleteAuto()
        }
        binding.button3.setOnClickListener {
            //getPrice()
        }
        observer()
    }

    /*private fun createDependencies() {
        val motorcycleRepository: MotorcycleRepository = MotorcycleRepositoryImpl(applicationContext)
        motorcycleViewModel = MotorcycleViewModel(motorcycleRepository)

        val autoRepository: AutoRepository = AutoRepositoryImpl(applicationContext)
        autoViewModel = AutoViewModel(autoRepository)
    }*/

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

    /*private fun getPrice() {
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
    }*/

    private fun observer() {

        /*autoViewModel.getAllAutos().observe(this, {
            binding.textView2.text = "Autos actualmente $it"
        })

        motorcycleViewModel.getAllMotorcycle().observe(this, {
            binding.textView3.text = "Motos actualmente $it"
        })*/

        autoViewModel.errors.observe(this, {
            Toast.makeText(this,"${it.message}",Toast.LENGTH_LONG).show()
        })

        motorcycleViewModel.errorsMotorcycle.observe(this, {
            Toast.makeText(this, "${it.message}", Toast.LENGTH_LONG).show()
        })
    }

    fun Date.toString(format: String, locale: Locale = Locale.getDefault()): String {
        val formatter = SimpleDateFormat(format, locale)
        return formatter.format(this)
    }

    fun getCurrentDateTime(): Date { return Calendar.getInstance().time }


}