package com.example.jetstocks.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.jetstocks.display.company_info.CompanyInfoScreen
import com.example.jetstocks.display.company_listing.CompanyListingViewModel
import com.example.jetstocks.display.company_listing.CompanyListingsScreen

@Composable
fun JetStockNavigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = JetStockScreens.CompanyListingScreen.name
    ) {

        composable(route = JetStockScreens.CompanyListingScreen.name) {
            val companyListingViewModel = hiltViewModel<CompanyListingViewModel>()
            CompanyListingsScreen(
                viewModel = companyListingViewModel,
                navController = navController
            )
        }

        composable(route = JetStockScreens.CompanyInfoScreen.name) {
            CompanyInfoScreen()
        }
    }
}