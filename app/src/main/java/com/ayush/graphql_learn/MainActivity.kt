package com.ayush.graphql_learn

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.ayush.graphql_learn.presentation.CountriesScreen
import com.ayush.graphql_learn.presentation.CountriesViewModel
import com.ayush.graphql_learn.ui.theme.GraphqlLearnTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GraphqlLearnTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val viewModel = hiltViewModel<CountriesViewModel>()
                    val state = viewModel.state.collectAsState()
                    CountriesScreen(
                        onCountrySelected = viewModel::onCountrySelected,
                        state = state.value,
                        onCountryDetailDismissed = viewModel::onCountryDetailDismissed
                    )
                }
            }
        }
    }
}

