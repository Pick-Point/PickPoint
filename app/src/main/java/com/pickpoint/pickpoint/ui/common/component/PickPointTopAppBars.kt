package com.pickpoint.pickpoint.ui.common.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pickpoint.pickpoint.ui.theme.*


@Composable
fun TopAppBar(
    title: String,
    modifier: Modifier = Modifier,
    onNavigationClick: () -> Unit,
    onActionClick: (() -> Unit)? = null
) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .height(56.dp),
        color = MaterialTheme.colorScheme.background,
        shadowElevation = 4.dp
    ) {
        Row(
            modifier = Modifier
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            IconButton(
                onClick = onNavigationClick,
                modifier = Modifier.size(24.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Menu,
                    contentDescription = null,
                    tint = LightPrototypeOnPrimaryColor
                )
            }
            Text(
                text = title,
                fontSize = 28.sp,
                color = LightPrototypeSecondaryColor
            )
            onActionClick?.let {
                IconButton(
                    onClick = it,
                    modifier = Modifier.size(24.dp)
                ) {
                    Icon(
                        imageVector = Icons.Filled.Settings,
                        contentDescription = null,
                        tint = LightPrototypeOnPrimaryColor
                    )
                }
            }
        }
    }
}

@Composable
fun SecondaryTopAppBar(
    title: String,
    onNavigationClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .height(56.dp),
        color = MaterialTheme.colorScheme.background,
        shadowElevation = 4.dp
    ) {
        Row(
            modifier = Modifier
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            IconButton(
                onClick = onNavigationClick,
                modifier = Modifier.size(24.dp)
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                    contentDescription = null,
                    tint = LightPrototypeOnPrimaryColor
                )
            }
            Text(
                text = title,
                fontSize = 28.sp,
                color = LightPrototypeSecondaryColor,
                modifier = Modifier.padding(start = 18.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FirstPreview() {
    TopAppBar(title = "Pick Point", onNavigationClick = {}, onActionClick = {})
}

@Preview(showBackground = true)
@Composable
fun SecondPreview() {
    SecondaryTopAppBar(title = "Settings", onNavigationClick = {})
}