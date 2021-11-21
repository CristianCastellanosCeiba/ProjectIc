package com.example.projectic

 import android.os.Build
 import android.os.Bundle
 import android.widget.Toast
 import androidx.annotation.RequiresApi
 import androidx.appcompat.app.AppCompatActivity
 import androidx.lifecycle.ViewModelProvider
 import androidx.lifecycle.get
 import com.example.domain.entity.Auto
 import com.example.projectic.databinding.ActivityMainBinding
 import com.example.projectic.di.DaggerDiComponents
 import com.example.projectic.di.DiModule
 import com.example.projectic.viewModel.AutoViewModel
 import com.example.projectic.viewModel.AutoViewModelFactory
 import java.text.SimpleDateFormat
 import java.util.*
 import javax.inject.Inject

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var autoViewModel: AutoViewModel
    @Inject
    lateinit var autoViewModelFactory: AutoViewModelFactory

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initInject()
        binding.button2.setOnClickListener {
            getRegistryAuto()
        }
        binding.button.setOnClickListener {
            getDeleteAuto()
        }
        binding.button3.setOnClickListener {
            getPrice()
        }
        observer()
    }

    private fun initInject() {
        DaggerDiComponents.builder().diModule(DiModule(this)).build().inject(this)
        autoViewModel = ViewModelProvider(this, autoViewModelFactory).get()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun getRegistryAuto() {
        autoViewModel.getRegistryAuto(data(), "A")
    }

    private fun getDeleteAuto() {
        autoViewModel.getDeleteAuto("Awe123").observe(this, {

        })
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun getPrice() {
        autoViewModel.getPrice(data(), getCurrentDateTime())
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun data(): Auto {
        return Auto(
           "Awe123",
            getCurrentDateTime(),
            null,
            "Auto"

        )
    }

    private fun observer() {
        autoViewModel.categories.observe(this, {

        })

        autoViewModel.errors.observe(this, {
            Toast.makeText(this,"${it.message}",Toast.LENGTH_LONG).show()
        })
    }

    fun Date.toString(format: String, locale: Locale = Locale.getDefault()): String {
        val formatter = SimpleDateFormat(format, locale)
        return formatter.format(this)
    }

    fun getCurrentDateTime(): Date { return Calendar.getInstance().time }


}