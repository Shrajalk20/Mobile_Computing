package com.example.project1_mobilecomputing.classes

import android.content.Context
import android.util.Log
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.io.BufferedReader
import java.io.InputStreamReader
import kotlin.math.abs
import kotlin.math.pow

class Respiratorycaller {

    private val accelValuesX: MutableList<Float> = mutableListOf()
    private val accelValuesY: MutableList<Float> = mutableListOf()
    private val accelValuesZ: MutableList<Float> = mutableListOf()
    //zprivate val viewModel = view()
    private var _result = MutableStateFlow<Int?>(null)
    var result: StateFlow<Int?> = _result.asStateFlow()

    fun mainn(context: Context) : Int {

        val resrate : Int
        val csvFiles = listOf("CSVBreatheY_1.txt", "CSVBreatheX.txt", "CSVBreatheZ_1.txt")
        for (file in csvFiles) {
            readCsvAndPopulateList(context, file)
        }
        println("it's working");

        resrate = respiratoryRateCalculator(accelValuesX, accelValuesY, accelValuesZ)
        println("it's working : " + result);
        _result.value = resrate

        return resrate

    }

    fun updateData(result: Int) {
        _result.value = result
    }

    private fun readCsvAndPopulateList(context: Context, fileName: String) {
        try {
            // Open the CSV file from assets
            val assetManager = context.assets
            val inputStream = assetManager.open(fileName)
            val bufferedReader = BufferedReader(InputStreamReader(inputStream))

            // Read the single line of CSV
            val line = bufferedReader.readLine()
            bufferedReader.close()

            // Split the line into values and convert them to Float
            if (line != null) {
                val values = line.split(",") // Split by comma
                for (value in values) {
                    try {

                        if (fileName === "CSVBreatheY_1.txt") {
                            val floatValue = value.trim().toFloat()
                            accelValuesY.add(floatValue)
                            //Log.d("Integer","Result Y: $floatValue")
                        }
                        if (fileName === "CSVBreatheX.txt") {
                            val floatValue1 = value.trim().toFloat()
                            accelValuesX.add(floatValue1)

                        }
                        if (fileName === "CSVBreatheZ_1.txt") {
                            val floatValue2 = value.trim().toFloat()
                            accelValuesZ.add(floatValue2)
                        }


                    } catch (e: NumberFormatException) {
                        e.printStackTrace() // Handle invalid float values
                    }
                }
            }
        } catch (e: Exception) {
            e.printStackTrace() // Handle file reading errors
        }
    }

    private fun respiratoryRateCalculator(
        accelValuesX: MutableList<Float>,
        accelValuesY: MutableList<Float>,
        accelValuesZ: MutableList<Float>,
    ): Int {
        var previousValue: Float
        var currentValue: Float
        previousValue = 10f
        var k = 0
        for (i in 11..<accelValuesY.size) {
            currentValue = kotlin.math.sqrt(
                accelValuesZ[i].toDouble().pow(2.0) + accelValuesX[i].toDouble()
                    .pow(2.0) + accelValuesY[i].toDouble().pow(2.0)
            ).toFloat()
            if (abs(x = previousValue - currentValue) > 0.15) {
                k++
            }
            previousValue = currentValue
        }
        val ret = (k.toDouble() / 45.00)
        val result = (ret * 30).toInt()
        Log.d("Integer","Result : $result")
        return result
    }
}