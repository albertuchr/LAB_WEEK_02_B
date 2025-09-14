package com.example.lab_week_02_b

import android.app.Activity
import android.graphics.Color
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import android.content.Intent

class ResultActivity : AppCompatActivity() {
    companion object {
        const val COLOR_KEY = "COLOR_KEY"
        const val ERROR_KEY = "ERROR_KEY"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val colorCode = intent.getStringExtra(COLOR_KEY)
        val backgroundScreen = findViewById<ConstraintLayout>(R.id.background_screen)
        val resultMessage = findViewById<TextView>(R.id.color_code_result_message)

        if (colorCode != null) {
            try {
                backgroundScreen.setBackgroundColor(Color.parseColor("#$colorCode"))
                resultMessage.text = getString(R.string.color_code_result_message, colorCode.uppercase())
            } catch (ex: IllegalArgumentException) {
                val errorIntent = Intent()
                errorIntent.putExtra(ERROR_KEY, true)
                setResult(Activity.RESULT_OK, errorIntent)
                finish()
            }
        }
    }
}
