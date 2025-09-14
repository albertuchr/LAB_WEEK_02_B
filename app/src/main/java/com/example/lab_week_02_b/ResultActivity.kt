package com.example.lab_week_02_b

import android.graphics.Color
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout

class ResultActivity : AppCompatActivity() {
    companion object {
        const val COLOR_KEY = "COLOR_KEY"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val colorCode = intent.getStringExtra(COLOR_KEY) ?: intent.getStringExtra(MainActivity.COLOR_KEY)
        val backgroundScreen = findViewById<ConstraintLayout>(R.id.background_screen)
        val resultMessage = findViewById<TextView>(R.id.color_code_result_message)

        if (colorCode.isNullOrBlank()) {
            resultMessage.text = getString(R.string.color_code_input_empty)
            return
        }

        // Tampilkan teks dan ubah background — bungkus parse di try/catch supaya gak crash
        resultMessage.text = getString(R.string.color_code_result_message, colorCode.uppercase())
        try {
            // Module expects user to input 6 hex chars (like "ff0000"), so prepend '#'
            backgroundScreen.setBackgroundColor(Color.parseColor("#$colorCode"))
        } catch (ex: IllegalArgumentException) {
            // Kalau parsing gagal, beri info dan jangan crash
            resultMessage.text = getString(R.string.color_code_input_invalid)
            backgroundScreen.setBackgroundColor(Color.WHITE)
        }
    }
}
