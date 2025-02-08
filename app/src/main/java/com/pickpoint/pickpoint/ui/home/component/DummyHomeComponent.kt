package com.pickpoint.pickpoint.ui.home.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pickpoint.pickpoint.ui.theme.LightPrototypePrimaryColor
import com.pickpoint.pickpoint.ui.theme.PickPointTheme

@Composable
fun TopMenu(
    onReportClick: () -> Unit,
    onSettingsClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier.size(112.dp),
        shape = MaterialTheme.shapes.large,
        shadowElevation = 4.dp
    ) {
        Column(
            modifier = modifier
                .background(LightPrototypePrimaryColor)
                .padding(vertical = 8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            NavigationItem(
                icon = Icons.Filled.Settings,
                text = "Settings",
                onClick = onSettingsClick
            )
            NavigationItem(
                icon = Icons.Default.Info,
                text = "Report",
                onClick = onReportClick
            )
        }
    }
}

@Composable
private fun NavigationItem(
    icon: ImageVector,
    text: String,
    onClick: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .clickable(onClick = onClick)
            .padding(horizontal = 12.dp, vertical = 6.dp)
            .size(88.dp, 40.dp)
            .fillMaxWidth()
    ) {
        Icon(
            imageVector = icon,
            contentDescription = text,
            modifier = Modifier.size(18.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = text)
    }
}

@Preview(
    name = "Navigation Drawer",
    showBackground = true,
)
@Composable
fun NavigationDrawerPreview() {
    PickPointTheme {
        Surface {
            TopMenu(
                onReportClick = { },
                onSettingsClick = { }
            )
        }
    }
}

@Preview(
    name = "Navigation Item",
    showBackground = true,
)
@Composable
fun NavigationItemPreview() {
    PickPointTheme {
        Surface {
            NavigationItem(
                icon = Icons.Default.Info,
                text = "Sample Item",
                onClick = { }
            )
        }
    }
}