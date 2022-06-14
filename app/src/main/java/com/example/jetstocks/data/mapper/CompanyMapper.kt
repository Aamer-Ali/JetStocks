package com.example.jetstocks.data.mapper

import com.example.jetstocks.data.local.CompanyListingEntity
import com.example.jetstocks.domain.model.CompanyListing


fun CompanyListingEntity.toCompanyListing(): CompanyListing {
    return CompanyListing(
        name = name,
        symbol = symbol,
        exchange = exchange
    )
}

fun CompanyListing.toCompanyListingEntity(): CompanyListingEntity {
    return CompanyListingEntity(
        name = name,
        symbol = symbol,
        exchange = exchange
    )
}
