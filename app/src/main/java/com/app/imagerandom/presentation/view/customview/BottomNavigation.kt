package com.app.imagerandom.presentation.view.customview

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.app.imagerandom.domain.model.BottomTab
import com.app.imagerandom.domain.model.Items
import com.app.imagerandom.presentation.ui.BackgroundMode
import com.app.imagerandom.presentation.ui.BottomNavigationMode
import com.app.imagerandom.presentation.ui.Coating
import com.app.imagerandom.presentation.ui.CoatingIcon
import com.app.imagerandom.presentation.ui.icon
import com.app.imagerandom.presentation.ui.iconActive
import com.app.imagerandom.presentation.ui.offsets
import com.app.imagerandom.presentation.ui.selectedTop
import com.app.imagerandom.presentation.ui.shadow1
import com.app.imagerandom.presentation.ui.shadow2
import com.app.imagerandom.presentation.ui.shadow3
import com.app.imagerandom.presentation.ui.shadow4

data class DpOffset(val x: Dp, val y: Dp)

@Composable
fun BottomNavigation(
    modifier: Modifier = Modifier,
    currentTab: BottomTab,
    onTabSelected: (BottomTab) -> Unit,
) {


    val offsets = MaterialTheme.colorScheme.offsets
    val shadowConfigs = listOf(
        Triple(20.dp, MaterialTheme.colorScheme.shadow1, offsets[0]),
        Triple(24.dp, MaterialTheme.colorScheme.shadow2, offsets[1]),
        Triple(4.dp, MaterialTheme.colorScheme.shadow3, offsets[2]),
        Triple(20.dp, MaterialTheme.colorScheme.shadow4, offsets[3])
    )
    ConstraintLayout(
        modifier = modifier.fillMaxWidth()
    ) {
        val (bottomTab) = createRefs()

        // Bottom Navigation
        Box(
            modifier = Modifier
                .height(72.dp)
                .width(218.dp)
                .padding(bottom = 20.dp)
                .shadow(10.dp, RoundedCornerShape(20.dp), true)
                .clip(RoundedCornerShape(50.dp))
                .constrainAs(bottomTab) {
                    top.linkTo(parent.top, margin = 20.dp)
                    start.linkTo(parent.start)
                    bottom.linkTo(parent.bottom)
                    end.linkTo(parent.end)
                }
        ) {
            shadowConfigs.forEach { (elevation, shadowColor, offset) ->
                Box(
                    modifier = Modifier
                        .matchParentSize()
                        .shadow(
                            elevation = elevation,
                            shape = RoundedCornerShape(28.dp),
                            ambientColor = shadowColor,
                            spotColor = shadowColor,
                            clip = false
                        )
                        .offset(x = offset.x, y = offset.y)
                )
            }
            Box(
                modifier = Modifier
                    .matchParentSize()
                    .background(color = MaterialTheme.colorScheme.BottomNavigationMode)
            )
            Box(
                modifier = Modifier
                    .matchParentSize()
                    .background(color = MaterialTheme.colorScheme.Coating)
            )
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 24.dp)
                    .align(Alignment.Center),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Items().items.forEach { tab ->
                    BottomNavigationItem(
                        painter = painterResource(tab.icon),
                        isSelected = currentTab.route == tab.route,
                        onClick = { onTabSelected(tab) },
                        isDisable = false,
                        showBadge = tab.label == "Setting"
                    )
                }
            }
        }
    }
}

@Composable
fun BottomNavigationItem(
    painter: Painter,
    isSelected: Boolean,
    onClick: () -> Unit,
    showBadge: Boolean = false,
    isDisable: Boolean = true,
) {
    val iconColor = when {
        isSelected -> MaterialTheme.colorScheme.iconActive
        else -> MaterialTheme.colorScheme.icon
    }
    val interactionSource = remember { MutableInteractionSource() }
    ConstraintLayout(
        modifier = Modifier
            .width(48.dp)
            .height(70.dp)
            .clickable(
                enabled = !isDisable,
                indication = null,
                interactionSource = interactionSource,
                onClick = onClick
            )
    ) {
        val (content, selection, notify) = createRefs()
        if (isSelected) {
            Box(
                modifier = Modifier
                    .width(38.dp)
                    .height(4.dp)
                    .background(
                        color = MaterialTheme.colorScheme.selectedTop,
                        RoundedCornerShape(bottomEnd = 10.dp, bottomStart = 10.dp)
                    )
                    .constrainAs(selection) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    },
            ) {
                Box(
                    modifier = Modifier
                        .matchParentSize()
                        .background(
                            color = MaterialTheme.colorScheme.CoatingIcon,
                            RoundedCornerShape(bottomEnd = 10.dp, bottomStart = 10.dp)
                        )

                )
            }
        }
        Icon(
            painter = painter,
            contentDescription = "Bottom Navigation Icon",
            modifier = Modifier
                .size(18.dp)
                .constrainAs(content) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
            tint = iconColor
        )
        if (showBadge) {
            Box(
                modifier = Modifier
                    .size(6.dp)
                    .background(
                        brush = Brush.radialGradient(
                            colors = listOf(
                                Color(0xFFF35421),
                                Color(0xFFCB1F15)
                            )
                        ), CircleShape
                    )
                    .offset(x = 8.dp, y = (-4).dp)
                    .constrainAs(notify) {
                        top.linkTo(parent.top, margin = 11.dp)
                        end.linkTo(parent.end, margin = 11.dp)
                    },
            )
        }
    }
}

@Preview
@Composable
fun BottomNavigationPreview() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.BackgroundMode),
    ) {
        BottomNavigation(
            currentTab = BottomTab.Home,
            onTabSelected = {}
        )
    }
}