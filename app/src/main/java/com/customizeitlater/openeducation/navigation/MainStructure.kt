package com.customizeitlater.openeducation.navigation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Brightness4
import androidx.compose.material.icons.filled.Brightness7
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ThumbDown
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainStructure(
    navController: NavHostController,
    onThemeToggle: () -> Unit = {},
    isDarkTheme: Boolean = true,
    onAddPostClick: () -> Unit,
    onNavigateToProfile: () -> Unit,
    onNavigateToSearch: () -> Unit,
    onNavigateToReels: () -> Unit,
    onNavigateToHome: () -> Unit
) {
    var selectedIndex by remember { mutableIntStateOf(0) }

    Scaffold(topBar = {
        TopAppBar(title = {
            Text(
                text = "Quotes", style = MaterialTheme.typography.titleLarge
            )
        }, actions = {
            IconButton(onClick = onThemeToggle) {
                Icon(
                    imageVector = if (isDarkTheme) Icons.Default.Brightness7 else Icons.Default.Brightness4,
                    contentDescription = "Toggle Theme"
                )
            }
        })
    }, bottomBar = {
        NavigationBar {
            NavigationItem(
                icon = Icons.Default.Home,
                label = "Home",
                selected = selectedIndex == 0,
                onClick = {
                    selectedIndex = 0
                    onNavigateToHome()
                })
            NavigationItem(
                icon = Icons.Default.Search,
                label = "Search",
                selected = selectedIndex == 1,
                onClick = {
                    selectedIndex = 1
                    onNavigateToSearch()
                })
            NavigationBarItem(icon = {
                Icon(
                    imageVector = Icons.Default.Add, contentDescription = "Create"
                )
            }, label = { Text("Create") }, selected = false, onClick = { onAddPostClick() })
            NavigationItem(
                icon = Icons.Default.PlayArrow,
                label = "Reels",
                selected = selectedIndex == 3,
                onClick = {
                    selectedIndex = 3
                    onNavigateToReels()
                })
            NavigationItem(
                icon = Icons.Default.Person,
                label = "Profile",
                selected = selectedIndex == 4,
                onClick = {
                    selectedIndex = 4
                    onNavigateToProfile()
                })
        }
    }) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            repeat(5) {
                ElevatedCard(
                    modifier = Modifier.fillMaxWidth(), shape = MaterialTheme.shapes.large
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(
                            text = "“A meaningful quote or question here...”",
                            style = MaterialTheme.typography.bodyLarge
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                            Icon(Icons.Default.ThumbUp, contentDescription = "Upvote")
                            Icon(Icons.Default.ThumbDown, contentDescription = "Downvote")
                        }
                    }
                }
            }
        }
    }
}


@Composable
fun RowScope.NavigationItem( icon: ImageVector, label: String, selected: Boolean, onClick: () -> Unit) {

    NavigationBarItem(
        icon = { Icon(icon, contentDescription = label) },
        label = { Text(label) },
        selected = selected,
        onClick = onClick
    )
}

