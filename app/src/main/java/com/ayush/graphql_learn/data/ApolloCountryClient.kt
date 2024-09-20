package com.ayush.graphql_learn.data

import com.apollographql.apollo.ApolloClient
import com.ayush.CountriesQuery
import com.ayush.CountryQuery
import com.ayush.graphql_learn.domain.CountryClient
import com.ayush.graphql_learn.domain.DetailedCountry
import com.ayush.graphql_learn.domain.SimpleCountry

class ApolloCountryClient(
    private val apolloClient: ApolloClient
) : CountryClient {
    override suspend fun getCountries(): List<SimpleCountry> {
        return apolloClient
            .query(CountriesQuery())
            .execute()
            .data
            ?.countries
            ?.map {
                it.toSimpleCountry()
            } ?: emptyList()
    }

    override suspend fun getCountry(code: String): DetailedCountry? {
        return apolloClient
            .query(CountryQuery(code))
            .execute()
            .data
            ?.country
            ?.toDetailedCountry()
    }
}
