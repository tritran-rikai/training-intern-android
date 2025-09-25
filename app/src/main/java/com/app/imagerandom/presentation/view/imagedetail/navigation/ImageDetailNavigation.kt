package com.app.imagerandom.presentation.view.imagedetail.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.app.imagerandom.presentation.view.imagedetail.ImageDetailScreen
import java.net.URLDecoder
import java.net.URLEncoder
import java.nio.charset.StandardCharsets


const val IMAGE_URL_ARG = "imageUrl"
const val IMAGE_DETAIL_ROUTE_BASE = "image_detail_route"
const val IMAGE_DETAIL_ROUTE = "$IMAGE_DETAIL_ROUTE_BASE/{$IMAGE_URL_ARG}"

// Navigation extension for NavController
fun NavController.navigateToImageDetail(imageUrl: String) {
    val encodedUrl = URLEncoder.encode(imageUrl, StandardCharsets.UTF_8.toString())
    navigate("$IMAGE_DETAIL_ROUTE_BASE/$encodedUrl")
}

fun NavGraphBuilder.imageDetailScreen(navController: NavController) {
    composable(
        route = IMAGE_DETAIL_ROUTE,
        arguments = listOf(navArgument(IMAGE_URL_ARG) { type = NavType.StringType })
    ) {
        val encodedImageUrl = it.arguments?.getString(IMAGE_URL_ARG)
        val imageUrl = encodedImageUrl?.let { url ->
            URLDecoder.decode(
                url,
                StandardCharsets.UTF_8.toString()
            )
        }
        ImageDetailScreen(
            imageUrl = imageUrl ?: "",
            onShareClick = { /* TODO */ },
            onSaveClick = { /* TODO */ },
            onLikeClick = { /* TODO */ },
            onBackClick = { navController.popBackStack() }
        )
    }
}