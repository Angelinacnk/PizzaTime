package com.example.pizzatimer

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var editTextMinutes: EditText
    private lateinit var textViewCountdown: TextView
    private lateinit var buttonStartTimer: Button
    private var countDownTimer: CountDownTimer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialisieren der UI-Komponenten
        editTextMinutes = findViewById(R.id.edit_text_minutes)
        textViewCountdown = findViewById(R.id.text_view_countdown)
        buttonStartTimer = findViewById(R.id.button_start_timer)

        // Button Click Listener setzen
        buttonStartTimer.setOnClickListener {
            val input = editTextMinutes.text.toString()
            if (input.isNotEmpty()) {
                val minutes = input.toIntOrNull() ?: 0
                if (minutes > 0) {
                    startTimer((minutes * 60 * 1000).toLong())
                } else {
                    Toast.makeText(this, "Bitte geben Sie eine gültige Zahl ein", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Bitte geben Sie eine Zeit ein", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun startTimer(milliseconds: Long) {
        countDownTimer = object : CountDownTimer(milliseconds, 1000) {
            @SuppressLint("DefaultLocale", "SetTextI18n")
            override fun onTick(millisUntilFinished: Long) {
                val minutes = (millisUntilFinished / 1000).toInt() / 60
                val seconds = (millisUntilFinished / 1000).toInt() % 60
                val timeLeftFormatted = String.format("%02d:%02d", minutes, seconds)
                textViewCountdown.text = "Verbleibende Zeit: $timeLeftFormatted"
            }

            override fun onFinish() {
                textViewCountdown.text = "Zeit abgelaufen!"
                Toast.makeText(this@MainActivity, "Zeit ist um!", Toast.LENGTH_SHORT).show()
            }
        }.start()
    }
}