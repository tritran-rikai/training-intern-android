package com.app.imagerandom.presentation.view.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.lazy.staggeredgrid.rememberLazyStaggeredGridState
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.app.imagerandom.domain.model.ImageRandom
import com.app.imagerandom.domain.model.Links
import com.app.imagerandom.domain.model.Urls
import com.app.imagerandom.presentation.view.imagedetail.navigation.navigateToImageDetail
import com.app.imagerandom.presentation.viewmodel.theme.HomeViewModel

const val HOME_ROUTE = "home_route"
fun NavController.navigateToHome() = navigate(HOME_ROUTE) {
    popUpTo(graph.startDestinationId) { inclusive = true }
    launchSingleTop = true
}

fun NavGraphBuilder.homeScreen(navController: NavController) { // Added NavController parameter
    composable(
        route = HOME_ROUTE,
    ) {
        val viewModel = hiltViewModel<HomeViewModel>()
        val list by viewModel.listImageState.collectAsState()
        val isLoading by viewModel.isLoading.collectAsState()
        val allImagesLoaded by viewModel.allImagesLoaded.collectAsState()
        HomeScreen(
            navController = navController, // Pass NavController
            list = list,
            isLoading = isLoading,
            allImagesLoaded = allImagesLoaded,
            onLoadMoreClick = viewModel::loadMoreImages
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavController, // Added NavController parameter
    list: List<ImageRandom>,
    isLoading: Boolean,
    allImagesLoaded: Boolean,
    onLoadMoreClick: () -> Unit
) {
    val gridState = rememberLazyStaggeredGridState()

    val buffer = 5
    val shouldLoadMore by remember {
        derivedStateOf {
            val lastVisibleItem = gridState.layoutInfo.visibleItemsInfo.lastOrNull()
            if (lastVisibleItem == null || list.isEmpty()) false
            else lastVisibleItem.index >= list.size - 1 - buffer
        }
    }

    LaunchedEffect(shouldLoadMore) {
        if (shouldLoadMore && !isLoading && !allImagesLoaded) {
            onLoadMoreClick()
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("ImageRandom - Staggered") })
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (list.isEmpty() && !isLoading) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text("Welcome! No images yet.")
                    Spacer(modifier = Modifier.height(8.dp))
                    Text("Click below to load some.")
                    Spacer(modifier = Modifier.height(16.dp))
                    Button(onClick = onLoadMoreClick, enabled = !isLoading) {
                        Text("Load Images")
                    }
                }
            } else {
                LazyVerticalStaggeredGrid(
                    state = gridState,
                    columns = StaggeredGridCells.Fixed(2),
                    contentPadding = PaddingValues(vertical = 8.dp),
                    verticalItemSpacing = 8.dp,
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.weight(1f)
                ) {
                    items(list, key = { it.id }) { image ->
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable { // Make card clickable
                                    navController.navigateToImageDetail(image.urls.full)
                                }
                        ) {
                            AsyncImage(
                                model = image.urls.full,
                                contentDescription = image.description ?: "Random Image",
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .wrapContentHeight()
                            )
                        }
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))
                if (isLoading) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp), contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }
                if (allImagesLoaded && list.isNotEmpty()) {
                    Text("You've reached the end!", modifier = Modifier.padding(vertical = 8.dp))
                }
                if (!isLoading && !allImagesLoaded && list.isNotEmpty()) {
                    Button(onClick = onLoadMoreClick) {
                        Text("Load More Images")
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true, name = "Home Screen with Images")
@Composable
fun HomeScreenPreview() {
    val sampleImages = listOf(
        ImageRandom(
            id = "1", createdAt = "", updatedAt = "", width = 1920, height = 1080, color = "", blurHash = "", likes = 0, likedByUser = false, description = "Landscape",
            user = com.app.imagerandom.domain.model.User(
                id = "user1", username = "u1", name = "User 1", portfolioUrl = null, bio = null, location = null, totalLikes = 0, totalPhotos = 0, totalCollections = 0, instagramUsername = null, twitterUsername = null,
                profileImage = com.app.imagerandom.domain.model.ProfileImage("", "", ""), links = com.app.imagerandom.domain.model.UserLinks("", "", "", "", "")
            ),
            currentUserCollections = emptyList(),
            urls = Urls(raw = "", full = "https://images.unsplash.com/photo-1506744038136-46273834b3fb?q=75&fm=jpg", regular = "", small = "", thumb = ""),
            links = Links("", "", "", "")
        ),
        ImageRandom(
            id = "2", createdAt = "", updatedAt = "", width = 1080, height = 1920, color = "", blurHash = "", likes = 0, likedByUser = false, description = "Portrait",
            user = com.app.imagerandom.domain.model.User(
                id = "user2", username = "u2", name = "User 2", portfolioUrl = null, bio = null, location = null, totalLikes = 0, totalPhotos = 0, totalCollections = 0, instagramUsername = null, twitterUsername = null,
                profileImage = com.app.imagerandom.domain.model.ProfileImage("", "", ""), links = com.app.imagerandom.domain.model.UserLinks("", "", "", "", "")
            ),
            currentUserCollections = emptyList(),
            urls = Urls(raw = "", full = "https://images.unsplash.com/photo-1530501860029-353975191040?q=75&fm=jpg", regular = "", small = "", thumb = ""),
            links = Links("", "", "", "")
        )
    )
    HomeScreen(
        navController = rememberNavController(),
        list = sampleImages,
        isLoading = false,
        allImagesLoaded = false,
        onLoadMoreClick = {})
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true, name = "Home Screen Empty")
@Composable
fun HomeScreenEmptyPreview() {
    HomeScreen(
        navController = rememberNavController(),
        list = emptyList(),
        isLoading = false,
        allImagesLoaded = false,
        onLoadMoreClick = {})
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true, name = "Home Screen Loading More")
@Composable
fun HomeScreenLoadingPreview() {
    val sampleImages = listOf(
        ImageRandom(
            id = "1", createdAt = "", updatedAt = "", width = 1920, height = 1080, color = "", blurHash = "", likes = 0, likedByUser = false, description = "Landscape",
            user = com.app.imagerandom.domain.model.User(
                id = "user1", username = "u1", name = "User 1", portfolioUrl = null, bio = null, location = null, totalLikes = 0, totalPhotos = 0, totalCollections = 0, instagramUsername = null, twitterUsername = null,
                profileImage = com.app.imagerandom.domain.model.ProfileImage("", "", ""), links = com.app.imagerandom.domain.model.UserLinks("", "", "", "", "")
            ),
            currentUserCollections = emptyList(),
            urls = Urls(raw = "", full = "https://images.unsplash.com/photo-1506744038136-46273834b3fb?q=75&fm=jpg", regular = "", small = "", thumb = ""),
            links = Links("", "", "", "")
        )
    )
    HomeScreen(
        navController = rememberNavController(),
        list = sampleImages,
        isLoading = true,
        allImagesLoaded = false,
        onLoadMoreClick = {})
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true, name = "Home Screen All Loaded")
@Composable
fun HomeScreenAllLoadedPreview() {
    val sampleImages = listOf(
        ImageRandom(
            id = "1", createdAt = "", updatedAt = "", width = 1920, height = 1080, color = "", blurHash = "", likes = 0, likedByUser = false, description = "Landscape",
            user = com.app.imagerandom.domain.model.User(
                id = "user1", username = "u1", name = "User 1", portfolioUrl = null, bio = null, location = null, totalLikes = 0, totalPhotos = 0, totalCollections = 0, instagramUsername = null, twitterUsername = null,
                profileImage = com.app.imagerandom.domain.model.ProfileImage("", "", ""), links = com.app.imagerandom.domain.model.UserLinks("", "", "", "", "")
            ),
            currentUserCollections = emptyList(),
            urls = Urls(raw = "", full = "https://images.unsplash.com/photo-1506744038136-46273834b3fb?q=75&fm=jpg", regular = "", small = "", thumb = ""),
            links = Links("", "", "", "")
        )
    )
    HomeScreen(
        navController = rememberNavController(),
        list = sampleImages,
        isLoading = false,
        allImagesLoaded = true,
        onLoadMoreClick = {})
}
