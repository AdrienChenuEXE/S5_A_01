import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.application_s5_a_01.R

sealed class BottomNavigationScreens(
    val route: String,
    val icon: Int, // ID de ressource de l'icône
    val label: String
) {
    object Home : BottomNavigationScreens("home", R.drawable.home, "Home")
    object Search : BottomNavigationScreens("search", R.drawable.settings, "Search")
    object Profile : BottomNavigationScreens("profile", R.drawable.profile, "Profile")

    companion object {
        val allScreens: List<BottomNavigationScreens> by lazy {
            listOf(Home, Search, Profile)
        }
    }
}

@Composable
fun BottomNavigationBar(navController: NavController) {
    Row {
        BottomNavigationScreens.allScreens.forEach { screen ->
            BottomNavigationItem(
                icon = screen.icon,
                label = screen.label,
                onClick = {
                    navController.navigate(screen.route) {
                    }
                }
            )
        }
    }
}



@Composable
fun BottomNavigationItem(
    icon: Int, // ID de ressource de l'icône
    label: String,
    onClick: () -> Unit
) {
    Row {
        Icon(
            painter = painterResource(id = icon),
            contentDescription = null
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = label)
    }
}
