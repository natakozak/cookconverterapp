package com.example.cookconverter

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var inputEditText: EditText
    private lateinit var inputUnitSpinner: Spinner
    private lateinit var outputUnitSpinner: Spinner
    private lateinit var convertButton: Button
    private lateinit var resultTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize views
        inputEditText = findViewById(R.id.input_edit_text)
        inputUnitSpinner = findViewById(R.id.input_unit_spinner)
        outputUnitSpinner = findViewById(R.id.output_unit_spinner)
        convertButton = findViewById(R.id.convert_button)
        resultTextView = findViewById(R.id.result_text_view)

        // Set click listener for convert button
        convertButton.setOnClickListener {
            convertValue()
        }
    }

    private fun convertValue() {
        // Get input value from EditText
        val inputValue = inputEditText.text.toString().toDoubleOrNull()

        // Check if input value is valid
        if (inputValue == null) {
            resultTextView.text = getString(R.string.invalid_input)
            return
        }

        // Get selected units from spinners
        val inputUnit = inputUnitSpinner.selectedItem.toString()
        val outputUnit = outputUnitSpinner.selectedItem.toString()

        // Perform conversion based on selected units
        val convertedValue = performConversion(inputValue, inputUnit, outputUnit)

        // Display the converted value
        resultTextView.text = convertedValue.toString()
    }

    private fun performConversion(value: Double, inputUnit: String, outputUnit: String): Double {
        val conversionFactor: Double // Значення конверсійного коефіцієнта для кожної одиниці виміру

        when {
            inputUnit == "Milliliters" && outputUnit == "Fluid Ounces" -> {
                conversionFactor = 0.033814
            }
            inputUnit == "Milliliters" && outputUnit == "Grams" -> {
                conversionFactor = 1.0
            }
            inputUnit == "Milliliters" && outputUnit == "Cups" -> {
                conversionFactor = 0.00422675
            }
            else -> {
                // Інші конверсії
                when {
                    inputUnit == "Grams" && outputUnit == "Ounces" -> {
                        conversionFactor = 0.03527396
                    }
                    inputUnit == "Cups" && outputUnit == "Milliliters" -> {
                        conversionFactor = 236.58824
                    }
                    else -> {
                        conversionFactor = 0.0
                    }
                }
            }

        }

        return value * conversionFactor
    }

}

