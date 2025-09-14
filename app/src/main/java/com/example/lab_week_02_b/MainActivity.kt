package com.example.lab_week_02_b

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    companion object {
        const val COLOR_KEY = "COLOR_KEY"
        const val ERROR_KEY = "ERROR_KEY"
    }
    
    private val startForResult = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { activityResult ->
        val data = activityResult.data
        val error = data?.getBooleanExtra(ERROR_KEY, false)
        if (error == true) {
            Toast.makeText(this, getString(R.string.color_code_input_invalid), Toast.LENGTH_LONG).show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val submitButton = findViewById<Button>(R.id.submit_button)

        submitButton.setOnClickListener {
            val colorCode = findViewById<TextInputEditText>(R.id.color_code_input_field)
                .text.toString().trim()

            if (colorCode.isEmpty()) {
                Toast.makeText(this, getString(R.string.color_code_input_empty), Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }

            if (colorCode.length != 6) {
                Toast.makeText(this, getString(R.string.color_code_input_wrong_length), Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }

            val resultIntent = Intent(this, ResultActivity::class.java)
            resultIntent.putExtra(COLOR_KEY, colorCode)
            startForResult.launch(resultIntent)
        }
    }
}
