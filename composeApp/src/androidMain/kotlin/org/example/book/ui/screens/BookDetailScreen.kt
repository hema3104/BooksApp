package org.example.book.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

//shows big book image, title, author, description
@Composable
fun BookDetailScreen(nav: NavController) {

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Detail Screen")

        Spacer(Modifier.height(16.dp))

        Button(
            onClick = { nav.popBackStack() }
        ) {
            Text("Go Back")
        }
    }
}
