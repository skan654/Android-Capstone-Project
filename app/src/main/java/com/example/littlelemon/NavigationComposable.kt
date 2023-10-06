package com.example.littlelemon

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

private fun determineStartDestination(context: Context): String {
    val sharedPreferences= context.getSharedPreferences("Little Lemon", Context.MODE_PRIVATE )
    if (sharedPreferences.getBoolean("userRegistered", true)){
        return Onboarding.route
    }
    else {
        return Home.route
    }

}

@Composable
fun Navigating(context: Context, navController: NavHostController){
    NavHost(
        navController = navController,
        startDestination = determineStartDestination(context)
    ){
    }

}