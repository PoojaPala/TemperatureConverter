package com.example.temperatureconverter

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.content.Intent
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner

class MainActivity : AppCompatActivity() {
    // List to store conversion history
    val conversionHistory = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initializing UI elements
        val temperatureInput: EditText = findViewById(R.id.edit_temperature)
        val unitSpinner: Spinner = findViewById(R.id.spinner_unit)
        val convertButton: Button = findViewById(R.id.button_convert)
        val viewHistoryButton: Button = findViewById(R.id.button_view_history)

        // Setting up the Spinner with conversion options
        val units = arrayOf("Celsius to Fahrenheit", "Fahrenheit to Celsius")
        val spinnerAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, units)
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        unitSpinner.adapter = spinnerAdapter

        unitSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: android.view.View?, position: Int, id: Long) {
                // Handle item selection (if needed)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Handle no item selected case (if needed)
            }
        }

        // Setting up the Convert button click listener
        convertButton.setOnClickListener {
            val temperatureText = temperatureInput.text.toString()
            val temperature = temperatureText.toDoubleOrNull()
            val selectedUnit = unitSpinner.selectedItem.toString()
            val result = when (selectedUnit) {
                "Celsius to Fahrenheit" -> temperature?.let { celsiusToFahrenheit(it) }
                "Fahrenheit to Celsius" -> temperature?.let { fahrenheitToCelsius(it) }
                else -> null
            }

            // Starting the ResultActivity to display the conversion result
            val resultIntent = Intent(this, ResultActivity::class.java).apply {
                putExtra("result", result.toString())
                putExtra("selected_option", selectedUnit)
            }
            conversionHistory.add("Converted $temperature $selectedUnit: $result")
            startActivity(resultIntent)
        }

        // Setting up the View History button click listener
        viewHistoryButton.setOnClickListener {
            // Starting the HistoryActivity to display the conversion history
            val historyIntent = Intent(this, HistoryActivity::class.java).apply {
                putStringArrayListExtra("history", ArrayList(conversionHistory))
            }
            startActivity(historyIntent)
        }
    }

    // Function to convert Celsius to Fahrenheit
    fun celsiusToFahrenheit(celsius: Double): Double = (celsius * 9 / 5) + 32

    // Function to convert Fahrenheit to Celsius
    fun fahrenheitToCelsius(fahrenheit: Double): Double = (fahrenheit - 32) * 5 / 9
}
