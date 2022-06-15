package com.example.jetstocks.display.company_listing

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Divider
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph

//@Composable
//fun CompanyListingScreen(
//    viewModel: CompanyListingViewModel = hiltViewModel()
//) {
//    val swipeToRefreshStates = rememberSwipeRefreshState(isRefreshing = viewModel.states.isRefresh)
//    val state = viewModel.states
//
//    Column(
//        modifier = Modifier.padding(all = 16.dp)
//    ) {
//
//        OutlinedTextField(
//            modifier = Modifier.fillMaxWidth(),
//            value = state.searchQuery,
//            onValueChange = {},
//            maxLines = 1,
//            singleLine = true,
//            placeholder = {
//                Text(
//                    text = "Search"
//                )
//            })
//        SwipeRefresh(state = swipeToRefreshStates, onRefresh = {
//            viewModel.onEvent(event = CompanyListingEvent.OnRefresh)
//        }) {
//            LazyColumn() {
//                items(state.companies.size) { i ->
//                    val company = state.companies[i]
//                    CompanyItem(company = company, modifier = Modifier.clickable {
//                        Log.d("List Item", "CompanyListingScreen: $company")
//                    })
//                }
//            }
//
//        }
//    }
//}




@Composable

fun CompanyListingsScreen(
    viewModel: CompanyListingViewModel
) {
    val swipeRefreshState = rememberSwipeRefreshState(
        isRefreshing = viewModel.states.isRefresh
    )
    val state = viewModel.states
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        OutlinedTextField(
            value = state.searchQuery,
            onValueChange = {
                viewModel.onEvent(
                    CompanyListingEvent.OnSearchQueryChange(it)
                )
            },
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            placeholder = {
                Text(text = "Search...")
            },
            maxLines = 1,
            singleLine = true
        )
        SwipeRefresh(
            state = swipeRefreshState,
            onRefresh = {
                viewModel.onEvent(CompanyListingEvent.OnRefresh)
            }
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxSize()
            ) {
                items(state.companies.size) { i ->
                    val company = state.companies[i]
                    CompanyItem(
                        company = company,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                // TODO: Navigate to detail screen
                            }
                            .padding(16.dp)
                    )
                    if(i < state.companies.size) {
                        Divider(modifier = Modifier.padding(
                            horizontal = 16.dp
                        ))
                    }
                }
            }
        }
    }
}