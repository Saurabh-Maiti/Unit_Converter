package com.example.unit_converter

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.unit_converter.ui.theme.Unit_ConverterTheme
import kotlin.math.roundToInt

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Unit_ConverterTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    unit_converter()
                }
            }
        }
    }
}

@Composable
fun unit_converter() {
    var inputValue by remember { mutableStateOf("") }
    var outputValue by remember { mutableStateOf("") }
    var inputUnit by remember { mutableStateOf("Meter") }
    var outputUnit by remember { mutableStateOf("Meter") }
    var iExpanded by remember { mutableStateOf(false) }
    var oExpanded by remember { mutableStateOf(false) }
    var conversionFactor = remember { mutableDoubleStateOf(1.00) }
    var oconversionFactor = remember { mutableDoubleStateOf(1.00) }

    fun convertUnit() {
        var inputValueDouble = inputValue.toDoubleOrNull() ?: 0.0
        var result =
            (inputValueDouble * conversionFactor.value * 100.0 / oconversionFactor.value).roundToInt() / 100.0
        outputValue = result.toString()

    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    )
    {
        Text(
            text = "Unit Converter App",
            style = MaterialTheme.typography.headlineLarge,
            color = Color.DarkGray
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(value = inputValue, onValueChange = {
            inputValue = it
            convertUnit()
        }, label = { Text(text = "Enter Value") })
        Spacer(modifier = Modifier.height(16.dp))
        Row {

            Box {
                //Input Button
                Button(
                    onClick = { iExpanded = true },
                    colors = ButtonDefaults.buttonColors(Color.Black)
                ) {
                    Text(text = inputUnit)
                    Icon(Icons.Default.ArrowDropDown, contentDescription = "")
                }
                DropdownMenu(expanded = iExpanded, onDismissRequest = { iExpanded = false }) {
                    DropdownMenuItem(text = { Text(text = "Centimeter") },
                        onClick = {
                            iExpanded = false
                            inputUnit = "Centimeter"
                            conversionFactor.value = 0.01
                            convertUnit()

                        })

                    DropdownMenuItem(text = { Text(text = "Meter") },
                        onClick = {
                            iExpanded = false
                            inputUnit = "Meter"
                            conversionFactor.value = 1.0
                            convertUnit()
                        })

                    DropdownMenuItem(text = { Text(text = "Feet") },
                        onClick = {
                            iExpanded = false
                            inputUnit = "Feet"
                            conversionFactor.value = 0.3408
                            convertUnit()
                        })

                    DropdownMenuItem(text = { Text(text = "Millimeter") },
                        onClick = {
                            iExpanded = false
                            inputUnit = "Millimeter"
                            conversionFactor.value = 0.001
                            convertUnit()
                        })
                }
            }
            Spacer(modifier = Modifier.width(40.dp))
            Box {
                //Output Button
                Button(
                    onClick = { oExpanded = true },
                    colors = ButtonDefaults.buttonColors(Color.Black)
                ) {
                    Text(text = outputUnit)
                    Icon(Icons.Default.ArrowDropDown, contentDescription = "")
                }
                DropdownMenu(expanded = oExpanded, onDismissRequest = { oExpanded = false }) {
                    DropdownMenuItem(text = { Text(text = "Centimeter") },
                        onClick = {
                            oExpanded = false
                            outputUnit = "Centimeter"
                            oconversionFactor.value = 0.01
                            convertUnit()
                        })
                    DropdownMenuItem(text = { Text(text = "Meter") },
                        onClick = {
                            oExpanded = false
                            outputUnit = "Meter"
                            oconversionFactor.value = 1.0
                            convertUnit()
                        })
                    DropdownMenuItem(text = { Text(text = "Feet") }, onClick = {
                        oExpanded = false
                        outputUnit = "Feet"
                        oconversionFactor.value = 0.3048
                        convertUnit()
                    })
                    DropdownMenuItem(text = { Text(text = "Millimeter") }, onClick = {
                        oExpanded = false
                        outputUnit = "Millimeter"
                        oconversionFactor.value = 0.001
                        convertUnit()
                    })
                }
            }

        }
        Spacer(modifier = Modifier.height(230.dp))
        Text(
            text = "Result:$outputValue $outputUnit",
            style = MaterialTheme.typography.headlineMedium,
            color = Color.DarkGray
        )
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = "Developed By:Saurabh Maiti",
            color = Color.Blue,
            style = MaterialTheme.typography.titleSmall
        )

    }
}

@Preview(showBackground = true)
@Composable
fun unit_converterPreview() {
    unit_converter()
}