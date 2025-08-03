package com.example.jetpackComposeTestProject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class ContactDetailsActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ContactColumn(
                contact = Contact(
                    name = "Василий",
                    surname = "Иванович",
                    familyName = "Кузякин",
                    phone = "+7 985 932 09 45",
                    address = "Ивановская область дер. Крутово, д. 6",
                    email = "kuziakhin@mail.ru",
                    imageRes = R.drawable.example
                )
            )
        }
    }

    @Composable
    fun ContactColumn(contact: Contact) {
        val context = LocalContext.current
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(WindowInsets.systemBars.asPaddingValues())
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ContactImage(contact)
            ContactName(contact)
            InfoRow(context.getString(R.string.phone), contact.phone)
            InfoRow(context.getString(R.string.address), contact.address)
            if (!contact.email.isNullOrBlank()) {
                InfoRow(context.getString(R.string.email), contact.email)
            }
        }
    }

    @Composable
    fun ContactImage(contact: Contact) {
        Box(
            modifier = Modifier
                .fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            if (contact.imageRes == null) {
                Image(
                    painter = painterResource(id = R.drawable.circle),
                    contentDescription = null,
                )
                Text(
                    text = "${contact.name[0]}${contact.familyName[0]}",
                    fontWeight = FontWeight.Bold
                )
            } else {
                // четких требований по изображению не было, закруглил по размеру circle
                Image(
                    modifier = Modifier
                        .size(48.dp)
                        .clip(CircleShape),
                    painter = painterResource(id = contact.imageRes),
                    contentDescription = null,
                )
            }
        }
    }

    @Composable
    fun ContactName(contact: Contact) {
        Column(
            modifier = Modifier.padding(top = 16.dp, bottom = 32.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                text = "${contact.name} ${contact.surname.orEmpty()}"
            )

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    fontSize = 20.sp,
                    text = contact.familyName
                )
                if (contact.isFavorite) {
                    Image(
                        modifier = Modifier
                            .padding(start = 8.dp),
                        painter = painterResource(id = android.R.drawable.star_big_on),
                        contentDescription = null
                    )
                }
            }
        }
    }

    @Composable
    fun InfoRow(column: String, info: String?) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier.weight(1f),
                contentAlignment = Alignment.CenterEnd
            ) {
                Text("$column:  ", fontStyle = FontStyle.Italic)
            }
            if (info != null) {
                Text(
                    text = info,
                    modifier = Modifier.weight(1f, fill = false),
                    softWrap = true,
                    overflow = TextOverflow.Clip
                )
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
                address = "г. Москва, 3-я улица Строителей, д. 25, кв. 12",
                email = "elukashin@yandex.ru",
                isFavorite = true
            )
        )
    }

    @Preview(name = "portrait", showSystemUi = true)
    @Composable
    fun ContactColumnPreviewSecond() {
        ContactColumn(
            contact = Contact(
                name = "Василий",
                surname = "",
                familyName = "Кузякин",
                phone = "...",
                address = "Ивановская область дер. Крутово, д. 6",
                email = "",
                imageRes = R.drawable.example
            )
        )
    }
}