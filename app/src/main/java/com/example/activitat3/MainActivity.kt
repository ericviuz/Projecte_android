package com.example.activitat3

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.card.MaterialCardView
import com.google.android.material.slider.Slider

class MainActivity : AppCompatActivity() {

    private var home = true
    private var peso = 70
    private var edad = 20

    private lateinit var cardHome: MaterialCardView
    private lateinit var cardDona: MaterialCardView
    private lateinit var tvAltura: TextView
    private lateinit var tvPeso: TextView
    private lateinit var tvEdad: TextView
    private lateinit var sliderAltura: Slider

    private val colorSeleccionado = Color.rgb(81, 45, 168)
    private val colorNormal = Color.rgb(28, 28, 50)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        cardHome = findViewById(R.id.seleccio_home)
        cardDona = findViewById(R.id.seleccio_dona)
        tvAltura = findViewById(R.id.tv_altura)
        tvPeso = findViewById(R.id.tv_peso)
        tvEdad = findViewById(R.id.tv_edad)
        sliderAltura = findViewById(R.id.slider_altura)

        seleccionarGenero(true)

        cardHome.setOnClickListener {
            seleccionarGenero(true)
        }

        cardDona.setOnClickListener {
            seleccionarGenero(false)
        }

        sliderAltura.addOnChangeListener { _, value, _ ->
            tvAltura.text = value.toInt().toString()
        }

        findViewById<Button>(R.id.btn_peso_menos).setOnClickListener {
            if (peso > 1) {
                peso--
                tvPeso.text = peso.toString()
            }
        }

        findViewById<Button>(R.id.btn_peso_mas).setOnClickListener {
            peso++
            tvPeso.text = peso.toString()
        }

        findViewById<Button>(R.id.btn_edad_menos).setOnClickListener {
            if (edad > 1) {
                edad--
                tvEdad.text = edad.toString()
            }
        }

        findViewById<Button>(R.id.btn_edad_mas).setOnClickListener {
            edad++
            tvEdad.text = edad.toString()
        }

        findViewById<Button>(R.id.btn_calcular).setOnClickListener {
            val alturaMetros = sliderAltura.value / 100f
            val imc = peso / (alturaMetros * alturaMetros)

            val intent = Intent(this, ResultActivity::class.java)
            intent.putExtra("IMC", imc)
            intent.putExtra("EDAD", edad)
            intent.putExtra("HOME", home)

            startActivity(intent)
        }
    }

    private fun seleccionarGenero(esHome: Boolean) {
        home = esHome

        cardHome.setCardBackgroundColor(
            if (home) colorSeleccionado else colorNormal
        )

        cardDona.setCardBackgroundColor(
            if (!home) colorSeleccionado else colorNormal
        )

        cardHome.isSelected = home
        cardDona.isSelected = !home
    }
}
