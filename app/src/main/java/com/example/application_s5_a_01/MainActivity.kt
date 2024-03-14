package com.example.application_s5_a_01
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.example.application_s5_a_01.ui.SAEApp
import com.example.application_s5_a_01.ui.screens.OnboardScreen
import com.example.application_s5_a_01.ui.theme.SAETheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var isDarkMode: Boolean by remember { mutableStateOf(false) }
            var isOnboardingDone: Boolean by remember { mutableStateOf(true) }

            SAETheme(isDarkMode) {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    if (isOnboardingDone) {
                        SAEApp(
                            isDarkMode = isDarkMode,
                            switchDarkMode = { isDarkMode = !isDarkMode }
                        )
                    } else {
                        OnboardScreen(
                            doneOnboarding = {
                                isOnboardingDone = true
                            }
                        )
                    }
                }
            }
        }
    }
}