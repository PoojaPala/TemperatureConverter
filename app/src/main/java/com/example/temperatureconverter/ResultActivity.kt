package com.example.temperatureconverter

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ResultActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        // Retrieve the conversion result and selected option from the Intent
        val conversionResult = intent.getStringExtra("result")
        val conversionOption = intent.getStringExtra("selected_option")

        // TextView to display the conversion result
        val resultTextView: TextView = findViewById(R.id.text_result)
        resultTextView.text = "Converted Temperature: $conversionResult"

        // Button to go back to the previous screen
        val backButton: Button = findViewById(R.id.button_back)
        backButton.setOnClickListener {
            finish() // Close the current activity and go back to the previous screen
        }
    }
}
