package com.ayush.graphql_learn.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.ayush.graphql_learn.domain.DetailedCountry
import com.ayush.graphql_learn.domain.SimpleCountry

@Composable
fun CountriesScreen(
    onCountrySelected: (String) -> Unit,
    state : CountriesViewModel.CountriesState,
    onCountryDetailDismissed: () -> Unit
) {

    if (state.detailCountry != null) {
        CountryDialog(
            country = state.detailCountry,
            onDismiss = onCountryDetailDismissed
        )
    }

    Box(modifier = Modifier.fillMaxSize()) {
        if (state.isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        } else {
            LazyColumn {
                items(state.countries) { country ->
                    CountryItem(
                        country = country,
                        onCountrySelected = onCountrySelected
                    )
                }
            }
        }
        state.detailCountry?.let { country ->

        }
    }
}

@Composable
fun CountryItem(
    country: SimpleCountry,
    onCountrySelected: (String) -> Unit
) {
    Row(
        modifier = Modifier.clickable {
            onCountrySelected(country.code)
        }
    ) {
        Text(text = country.emoji,
            fontSize = 24.sp
        )
        Spacer(modifier = Modifier.width(8.dp))
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(text = country.name)
            Spacer(modifier = Modifier.width(14.dp))
            Text(text = country.capital)
        }

    }
}

@Composable
fun CountryDialog(
    country: DetailedCountry,
    onDismiss: () -> Unit
) {
    Dialog(
        onDismissRequest = onDismiss
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
                .background(color = androidx.compose.ui.graphics.Color.White)
                .clip(shape = androidx.compose.foundation.shape.RoundedCornerShape(8.dp))
        ){
            Text(text = country.emoji,
                fontSize = 24.sp
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = country.name)
            Spacer(modifier = Modifier.width(14.dp))
            Text(text = country.capital)
            Spacer(modifier = Modifier.width(14.dp))
            Text(text = country.currency)
            Spacer(modifier = Modifier.width(14.dp))
            Text(text = country.continent)
            Spacer(modifier = Modifier.width(14.dp))
            Text(text = country.languages.joinToString())
        }
    }
}
