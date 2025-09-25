package com.app.imagerandom.presentation.view.imagedetail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ImageDetailScreen(
    imageUrl: String,
    onShareClick: () -> Unit,
    onSaveClick: () -> Unit,
    onLikeClick: () -> Unit,
    onBackClick: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Transparent,
                )
            )
        }
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(it) // Consume padding from Scaffold
                .background(Color.Black.copy(alpha = 0.8f)) // Darker background
        ) {
            AsyncImage(
                model = imageUrl,
                contentDescription = "Full screen image",
                contentScale = ContentScale.Fit, // Fit image within bounds, maintaining aspect ratio
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp) // Add some padding around the image
            )

            // Action buttons column
            Column(
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .padding(16.dp)
                    .background(
                        MaterialTheme.colorScheme.surface.copy(alpha = 0.5f), // Semi-transparent background for buttons
                        shape = MaterialTheme.shapes.medium
                    )
                    .padding(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                IconButton(onClick = onShareClick) {
                    Icon(
                        Icons.Filled.Share,
                        contentDescription = "Share Image",
                        tint = Color.Black.copy(alpha = 0.8f)
                    )
                }
                IconButton(onClick = onSaveClick) {
                    Icon(
                        Icons.Filled.Edit,
                        contentDescription = "Save Image",
                        tint = Color.Black.copy(alpha = 0.8f)
                    )
                }
                IconButton(onClick = onLikeClick) {
                    Icon(
                        Icons.Filled.Favorite,
                        contentDescription = "Like Image",
                        tint = Color.Black.copy(alpha = 0.8f)
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF444444) // Darker preview background
@Composable
fun ImageDetailScreenPreview() {
    ImageDetailScreen(
        imageUrl = "https://images.unsplash.com/photo-1506744038136-46273834b3fb?q=75&fm=jpg", // Sample image
        onShareClick = {},
        onSaveClick = {},
        onLikeClick = {},
        onBackClick = {}
    )
}
