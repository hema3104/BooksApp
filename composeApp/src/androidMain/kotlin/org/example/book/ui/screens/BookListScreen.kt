package org.example.book.ui.screens

import android.net.Uri
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import coil.compose.AsyncImage
import org.example.book.presentation.BookViewModel
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import org.koin.java.KoinJavaComponent.getKoin
import org.koin.mp.KoinPlatform

@Composable
fun BookListScreen(nav: NavController) {

    // Inject ViewModel via Koin Java bridge
    val vm = remember {
        KoinPlatform.getKoin().get<BookViewModel>()
    }

    val state by vm.state.collectAsState()

    // Load books once and triggers API fetch
    LaunchedEffect(Unit) {
        vm.loadBooks()
    }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {

        //  Search Bar
        TextField(
            value = state.search,
            onValueChange = {
                vm.updateSearch(it)
            },
            placeholder = { Text("Search books") },
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )

        // Book List
        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {

            items(state.books) { book -> //Displaying list

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                          nav.navigate("detail")
                        }
                        .padding(8.dp)
                ) {

                    // Image
                    AsyncImage(
                        model = book.image,
                        contentDescription = null,
                        modifier = Modifier.size(60.dp)
                    )

                    // Text
                    Column(
                        modifier = Modifier
                            .weight(1f)
                            .padding(start = 8.dp)
                    ) {

                        Text(
                            text = book.title,
                            style = MaterialTheme.typography.titleMedium
                        )

                        Text(text = book.author)
                    }

                    // Wishlist Toggle -> UI only triggers action
                    IconButton(
                        onClick = { vm.toggle(book) }
                    ) {

                        Icon(
                            imageVector = Icons.Default.Favorite,
                            contentDescription = null,
                            tint =
                                if (book.isWishlisted)
                                    Color.Red
                                else
                                    Color.Gray
                        )
                    }

                }
            }
        }
    }
}
