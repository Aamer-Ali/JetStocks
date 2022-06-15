package com.example.jetstocks.display.company_listing

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetstocks.domain.model.CompanyListing

@Composable
fun CompanyItem(
    modifier: Modifier = Modifier,
    company: CompanyListing
) {

    Column(modifier = modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 5.dp, vertical = 2.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                modifier = Modifier.weight(1f),
                text = company.name,
                fontSize = 16.sp,
                color = MaterialTheme.colors.onBackground,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1
            )
            Text(
                text = company.exchange,
                color = MaterialTheme.colors.onBackground
            )
        }
        Text(
            text = " (${company.symbol}) ",
            style = MaterialTheme.typography.subtitle1,
            color = MaterialTheme.colors.onBackground
        )

    }
}
