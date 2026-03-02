package org.example.book.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import org.example.book.presentation.BookViewModel
import org.koin.mp.KoinPlatform

//shows wishlisted books and swipe
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WishListScreen(nav: NavController) {

    val vm = remember {
        KoinPlatform.getKoin().get<BookViewModel>()
    }

    val state by vm.state.collectAsState()

    if (state.wishlist.isEmpty()) {

        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text("No books in Wishlist")
        }

    } else {

        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {

            items(state.wishlist, key = { it.id }) { book -> //shows display

                val dismissState =
                    rememberSwipeToDismissBoxState()

                SwipeToDismissBox(

                    state = dismissState,

                    // Only Left to Right swipe
                    enableDismissFromStartToEnd = true,
                    enableDismissFromEndToStart = false,

                    backgroundContent = {

                        // left swipe
                        Row(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(Color(0xFFFFE0E0))
                                .padding(start = 20.dp),
                            verticalAlignment =
                                Alignment.CenterVertically
                        ) {

                            // UNFAV
                            Text(
                                text = "Unfav",
                                color = Color.Red,
                                modifier = Modifier
                                    .padding(end = 16.dp)
                                    .clickable {
                                        vm.toggle(book)
                                    }
                            )

                            // 🗑 DELETE
                            Text(
                                text = "Delete",
                                color = Color.Black,
                                modifier = Modifier
                                    .clickable {
                                        vm.toggle(book) //unfav and delete call this
                                    }
                            )
                        }
                    }
                ) {

                    // CARD
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                    ) {

                        Row(
                            modifier = Modifier.padding(12.dp)
                        ) {

                            AsyncImage(
                                model = book.image,
                                contentDescription = null,
                                modifier = Modifier.size(60.dp)
                            )

                            Column(
                                modifier = Modifier
                                    .padding(start = 8.dp)
                            ) {

                                Text(
                                    text = book.title,
                                    style = MaterialTheme
                                        .typography
                                        .titleMedium
                                )

                                Text(book.author)
                            }
                        }
                    }
                }
            }
        }
    }
}
