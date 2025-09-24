package com.app.imagerandom.presentation.view

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.dotlottie.dlplayer.Mode
import com.lottiefiles.dotlottie.core.compose.ui.DotLottieAnimation
import com.lottiefiles.dotlottie.core.util.DotLottieSource
import dagger.hilt.android.AndroidEntryPoint
import com.app.imagerandom.domain.model.AppTheme
import com.app.imagerandom.presentation.ui.MyApplicationTheme
import com.app.imagerandom.presentation.ui.ProvideBrushes
import com.app.imagerandom.presentation.viewmodel.theme.AppThemeViewModel


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.light(
                Color.TRANSPARENT, Color.TRANSPARENT
            ),
            navigationBarStyle = SystemBarStyle.light(
                Color.TRANSPARENT, Color.TRANSPARENT
            )
        )
        setContent {
            val viewModel: AppThemeViewModel = hiltViewModel()
            val theme by viewModel.theme.collectAsState()
            MyApplicationTheme(theme) {
                navController = rememberNavController()
                ProvideBrushes {
                    RootNavigator(
                        navController,
                    )
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}

@Composable
fun LoadingIndicator(
    isLoading: Int
) {
    Log.d("LoadingIndicator", "loading $isLoading")
    if (isLoading > 0) {
        Dialog(onDismissRequest = { }) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                DotLottieAnimation(
                    source = DotLottieSource.Asset("animation_busy_cursor.lottie"),
                    autoplay = true,
                    loop = true,
                    speed = 0.3f,
                    useFrameInterpolation = false,
                    playMode = Mode.FORWARD,
                    modifier = Modifier.size(width = 120.dp, height = 27.dp)
                )
                DotLottieAnimation(
                    source = DotLottieSource.Asset("animation_loading_text.lottie"),
                    autoplay = true,
                    loop = true,
                    speed = 1f,
                    useFrameInterpolation = false,
                    playMode = Mode.FORWARD,
                    modifier = Modifier.size(width = 120.dp, height = 27.dp)
                )
            }
        }
    }
}

@Preview
@Composable
fun LoadingIndicatorPreview() {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        LoadingIndicator(isLoading = 1)
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyApplicationTheme(AppTheme.SYSTEM) {
        RootNavigator(
            navController = rememberNavController(),
        )
    }
}