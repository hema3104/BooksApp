package org.example.book.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import org.example.book.domain.model.Book
import org.example.book.presentation.BookViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WishlistItem(
    book: Book,
    vm: BookViewModel
) {

    val dismissState = rememberSwipeToDismissBoxState()

    SwipeToDismissBox(

        state = dismissState,

        // Enable Left → Right swipe
        enableDismissFromStartToEnd = true,
        enableDismissFromEndToStart = false,

        backgroundContent = {

            // Background Actions
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0xFFFFE0E0))
                    .padding(horizontal = 20.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                // Unfavorite
                Text(
                    text = "Unfav ",
                    color = Color.Red,
                    modifier = Modifier.clickable {

                        vm.toggle(book)
                    }
                )

                // 🗑 Delete
                Text(
                    text = "Delete 🗑",
                    color = Color.Black,
                    modifier = Modifier.clickable {

                        vm.toggle(book)
                    }
                )
            }
        }
    ) {

        // Card UI
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
                    modifier = Modifier.padding(start = 8.dp)
                ) {

                    Text(
                        text = book.title,
                        style = MaterialTheme.typography.titleMedium
                    )

                    Text(book.author)
                }
            }
        }
    }
}
