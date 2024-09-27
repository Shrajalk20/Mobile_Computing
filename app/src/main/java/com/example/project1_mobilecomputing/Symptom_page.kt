package com.example.project1_mobilecomputing

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.project1_mobilecomputing.classes.MyDatabaseHelper

import com.example.project1_mobilecomputing.ui.theme.Project1_mobileComputingTheme

class Symptom_page : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Project1_mobileComputingTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->

                    var fever by remember { mutableStateOf<String>("0") }
                    symptoms(
                        modifier = Modifier.padding(innerPadding),
                        context = this,
                        fever = remember { mutableStateOf<String>("0") },
                        headache = remember { mutableStateOf<String>("0") },
                        nausea = remember { mutableStateOf<String>("0") },
                        diarrhea = remember { mutableStateOf<String>("0") },
                        sore_throat = remember { mutableStateOf<String>("0") },
                        muscle_pain = remember { mutableStateOf<String>("0") },
                        loss_smell_taste = remember { mutableStateOf<String>("0") },
                        cough = remember { mutableStateOf<String>("0") },
                        shortness_breath = remember { mutableStateOf<String>("0") },
                        fatigue = remember { mutableStateOf<String>("0") },
                        heartRate = intent.getIntExtra("heartRate", 0),
                        resRate = intent.getIntExtra("resRate", 0)
                    )
                }
            }
        }
    }
}

@Composable
fun symptoms(
    modifier: Modifier, fever: MutableState<String>,
    context: Context,
    headache: MutableState<String>,
    nausea: MutableState<String>,
    diarrhea: MutableState<String>,
    sore_throat: MutableState<String>,
    muscle_pain: MutableState<String>,
    loss_smell_taste: MutableState<String>,
    cough: MutableState<String>,
    shortness_breath: MutableState<String>,
    fatigue: MutableState<String>,
    heartRate: Int,
    resRate: Int
) {

    Column(

        modifier = Modifier
            .fillMaxHeight()
            .padding(top = 100.dp)
    ) {

        TextField(
            value = fever.value,
            onValueChange = { fever.value = it },
            label = { Text("Fever") }
        )
        TextField(
            value = headache.value,
            onValueChange = { headache.value = it },
            label = { Text("Headache") }
        )
        TextField(
            value = nausea.value,
            onValueChange = { nausea.value = it },
            label = { Text("Naushea") }
        )
        TextField(
            value = diarrhea.value,
            onValueChange = { diarrhea.value = it },
            label = { Text("Diarrhea") }
        )
        TextField(
            value = sore_throat.value,
            onValueChange = { sore_throat.value = it },
            label = { Text("Sore Throat") }
        )
        TextField(
            value = muscle_pain.value,
            onValueChange = { muscle_pain.value = it },
            label = { Text("Muscle pain") }
        )
        TextField(
            value = loss_smell_taste.value,
            onValueChange = { loss_smell_taste.value= it },
            label = { Text("Loss of smell or taste") }
        )
        TextField(
            value = cough.value,
            onValueChange = { cough.value = it },
            label = { Text("Cough") }
        )
        TextField(
            value = shortness_breath.value,
            onValueChange = { shortness_breath.value = it },
            label = { Text("Shortness of breath") }
        )
        TextField(
            value = fatigue.value,
            onValueChange = { fatigue.value = it },
            label = { Text("Fatigue") }
        )
        Button(onClick = {
            val myDB = MyDatabaseHelper(context)
            myDB.adddata(fever.value, headache.value, nausea.value, diarrhea.value, sore_throat.value, muscle_pain.value, loss_smell_taste.value, cough.value, shortness_breath.value, fatigue.value, heartRate, resRate)
        })
        {
            Text("SUBMIT")
        }

    }
}