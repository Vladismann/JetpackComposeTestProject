package com.example.JetpackComposeTestProject

import androidx.activity.ComponentActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview

class MainActivity : ComponentActivity() {
    @Composable
    fun ContactColumn(contact: Contact) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = Color.LightGray)
                    .weight(0.25F)
                ,
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    style = MaterialTheme.typography.headlineSmall,
                    text = "${contact.name} ${contact.surname.orEmpty()}")
                Text(
                    style = MaterialTheme.typography.headlineMedium,
                    text = contact.familyName
                )
            }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.weight(0.75F),
            ) {
                Text("Мобильный телефон: ${contact.phone}")
                Text("Адрес: ${contact.address}")
            }
        }
    }

    @Preview(name = "portrait", showSystemUi = true)
    @Composable
    fun ContactColumnPreview() {
        ContactColumn(
            contact = Contact(
                name = "Евгений",
                surname = "Андреевич",
                familyName = "Лукашин",
                phone = "+7 495 495 95 95",
                address = "г. Москва, 3-я улица Строителей, д. 25, кв. 12"
            )
        )
    }
}