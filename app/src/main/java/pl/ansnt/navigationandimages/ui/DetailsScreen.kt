package pl.ansnt.navigationandimages.ui

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun DetailsScreen(modifier: Modifier = Modifier, imageId: Int, onOpenURL: (String) -> Unit) {
    Text("Details of $imageId")
}
