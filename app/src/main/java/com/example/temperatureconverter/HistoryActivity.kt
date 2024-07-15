package com.example.temperatureconverter

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class HistoryActivity : AppCompatActivity() {

    // List to store the history of temperature conversions
    private val conversionHistory = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)

        // TextView to display the conversion history
        val historyTextView: TextView = findViewById(R.id.text_history)

        // Button to clear the conversion history
        val clearHistoryButton: Button = findViewById(R.id.button_clear_history)

        // Retrieve the history from the Intent and add it to the conversionHistory list
        val receivedHistory = intent.getStringArrayListExtra("history")
        if (receivedHistory != null) {
            conversionHistory.addAll(receivedHistory)
        }

        // Display the conversion history in the TextView
        historyTextView.text = conversionHistory.joinToString("\n")

        // Set an OnClickListener on the clearHistoryButton to clear the history
        clearHistoryButton.setOnClickListener {
            conversionHistory.clear()
            historyTextView.text = "History cleared."
        }
    }
}
