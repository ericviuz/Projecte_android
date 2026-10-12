package com.example.activitat3

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.util.Locale

class ResultActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val imc = intent.getFloatExtra("IMC", 0f)

        val tvImc = findViewById<TextView>(R.id.tv_imc)
        val tvCategoria = findViewById<TextView>(R.id.tv_categoria)
        val tvDescripcion = findViewById<TextView>(R.id.tv_descripcion)

        tvImc.text = String.format(Locale.getDefault(), "%.2f", imc)

        when {
            imc < 18.5f -> {
                tvCategoria.text = "BAJO PESO"
                tvDescripcion.text = "IMC inferior a 18,5."
            }

            imc < 25f -> {
                tvCategoria.text = "NORMAL"
                tvDescripcion.text = "IMC entre 18,5 y 24,9."
            }

            imc < 30f -> {
                tvCategoria.text = "SOBREPESO"
                tvDescripcion.text = "IMC entre 25 y 29,9."
            }

            else -> {
                tvCategoria.text = "OBESIDAD"
                tvDescripcion.text = "IMC igual o superior a 30."
            }
        }

        findViewById<Button>(R.id.btn_recalcular).setOnClickListener {
            finish()
        }
    }
}
