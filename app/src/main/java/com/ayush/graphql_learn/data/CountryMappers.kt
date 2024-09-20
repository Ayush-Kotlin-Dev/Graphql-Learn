package com.ayush.graphql_learn.data

import com.ayush.CountriesQuery
import com.ayush.CountryQuery
import com.ayush.graphql_learn.domain.DetailedCountry
import com.ayush.graphql_learn.domain.SimpleCountry

fun CountryQuery.Country.toDetailedCountry() :  DetailedCountry{
    return DetailedCountry(
        code = code,
        name = name,
        emoji = emoji,
        capital = capital ?: "NULL",
        currency = currency ?: "NULL",
        languages = languages.mapNotNull { it?.name },
        continent = continent.name
    )
}

fun CountriesQuery.Country.toSimpleCountry() :  SimpleCountry{
    return SimpleCountry(
        code = code,
        name = name,
        emoji = emoji,
        capital = capital ?: "NULL"
    )
}
