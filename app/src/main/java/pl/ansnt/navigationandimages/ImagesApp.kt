@file:OptIn(ExperimentalMaterial3Api::class)

package pl.ansnt.navigationandimages

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import pl.ansnt.navigationandimages.ui.DetailsScreen
import pl.ansnt.navigationandimages.ui.GridScreen


enum class ImagesScreen(@StringRes val title: Int) {
    Grid(title = R.string.app_name),
    Details(title = R.string.details),
}

@Composable
fun ImagesAppBar(
    currentScreen: ImagesScreen,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = { Text(stringResource(currentScreen.title)) },
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ),
        modifier = modifier,
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = null
                    )
                }
            }
        }
    )
}

@Composable
fun ImagesApp(navController: NavHostController = rememberNavController()) {
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = ImagesScreen.valueOf(
        backStackEntry?.destination?.route?.split("/")?.first() ?: ImagesScreen.Grid.name
    )

    Scaffold(
        topBar = {
            ImagesAppBar(
                currentScreen = currentScreen,
                canNavigateBack = navController.previousBackStackEntry != null,
                navigateUp = { navController.navigateUp() }
            )
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = ImagesScreen.Grid.name,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            composable(route = ImagesScreen.Grid.name) {
                GridScreen(
                    onImageClick = { imageId -> navController.navigate("${ImagesScreen.Details.name}/$imageId") },
                )
            }
            composable(
                route = "${ImagesScreen.Details.name}/{imageId}",
                arguments = listOf(navArgument("imageId") { type = NavType.IntType })
            ) {
                val context = LocalContext.current
                val imageId = backStackEntry?.arguments?.getInt("imageId")

                if (imageId != null) {
                    DetailsScreen(imageId = imageId, onOpenURL = { openWebPage(context, it) })
                }
            }
        }
    }
}

fun openWebPage(context: Context, url: String) {
    val webpage: Uri = Uri.parse(url)
    val intent = Intent(Intent.ACTION_VIEW, webpage)
    Log.i("ImagesApp", url)
    context.startActivity(intent)
}