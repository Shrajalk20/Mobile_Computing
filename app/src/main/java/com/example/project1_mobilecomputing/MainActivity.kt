package com.example.project1_mobilecomputing

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.project1_mobilecomputing.classes.Respiratorycaller
import com.example.project1_mobilecomputing.classes.heartratecaller
import com.example.project1_mobilecomputing.ui.theme.Project1_mobileComputingTheme

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.P)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Project1_mobileComputingTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val symptomIntent = Intent(this@MainActivity, Symptom_page::class.java)
                    mainScreen(modifier = Modifier.padding(innerPadding), context = this, symptomIntent)
                }
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.P)
@Composable
fun mainScreen(modifier: Modifier, context: Context, symptomIntent: Intent){
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally ,
        verticalArrangement = Arrangement.Center

    ) {
        var result_heart by remember { mutableStateOf(value = 0) }
        var respiratory_rate by remember { mutableStateOf(value = 0) }
        val heartrate = heartratecaller()
        val Resrate = Respiratorycaller()
        Button(onClick = {

           result_heart = heartrate.runcalculator(context,"HeartRate.mp4")
        })
        {
            Text(
                text = "Calculate Heart Rate"
            )
        }
        if (result_heart != 0) {
            Text(
                text = "Heart Rate : $result_heart"
            )
        }
        Button(onClick = { respiratory_rate = Resrate.mainn(context)}) {
            Text(
                text = "Calculate Respiratory Rate"
            )
        }
    if (respiratory_rate != 0) {
        Text(
            text = "Respiratory Rate : $respiratory_rate"
        )
    }
        Button(onClick = {
            symptomIntent.putExtra("heartRate", result_heart)
            symptomIntent.putExtra("resRate", respiratory_rate)
            context.startActivity(symptomIntent)

        }) {
                Text(
                    text = "Symptoms"
                )
        }
    }
}