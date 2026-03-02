package org.example.book.ui.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.*
import org.example.book.ui.screens.*

@Composable
fun AppNavHost() {

    val nav = rememberNavController()

    Scaffold(

        // Bottom Navigation Bar
        bottomBar = {

            NavigationBar {

                NavigationBarItem(
                    selected = true,
                    onClick = {
                        nav.navigate("list")
                    },
                    icon = {
                        Text("Library")
                    }
                )

                NavigationBarItem(
                    selected = false,
                    onClick = {
                        nav.navigate("wishlist")
                    },
                    icon = {
                        Text("Wishlist")
                    }
                )
            }
        }

    ) { padding ->

        NavHost(
            navController = nav,
            startDestination = "list",
            modifier = Modifier.padding(padding)
        ) {

            // 📚 LIST
            composable("list") {
                BookListScreen(nav)
            }

            //DETAIL
            composable("detail") {
                BookDetailScreen(nav)
            }

            // WISHLIST
            composable("wishlist") {
                WishListScreen(nav)
            }
        }
    }
}
