package com.example.weatherapp.presentation.screens

import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.util.Log
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect

import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.content.ContextCompat
import androidx.navigation.NavHostController
import com.example.weatherapp.operations.data_management.data_entities.NetWorkEntitySubClasses.Location
import com.example.weatherapp.presentation.components.HomeScreenFront
import com.google.android.gms.location.LocationServices



@ExperimentalMaterialApi
@Composable
fun HomeScreen(nav: NavHostController, viewModel: WeatherViewModel) {
    val context = LocalContext.current

    val permissionLauncher: ManagedActivityResultLauncher<String, Boolean> =
        rememberLauncherForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) {
            if (it) {
                Log.d("Permissions Test", "permissionLauncher: Permissions granted")
                getCurrentLocation(viewModel, context)
            } else {
                Log.d("Permissions Test", "permissionLauncher: permissions result denied")
                viewModel.announceSnackBarMessage("Permission is denied please grant the permission first in the settings ")
            }
        }

    DisposableEffect(Unit) {
        checkAndRequestPermission(permissionLauncher, context,viewModel)
        onDispose { /* Clean up if needed */ }
    }

    HomeScreenFront(nav, viewModel)
}

fun checkAndRequestPermission(
    permissionLauncher: ManagedActivityResultLauncher<String, Boolean>,
    context: Context,
    viewModel: WeatherViewModel
) {
    val permissionCheckResult: Int = ContextCompat.checkSelfPermission(
        context,
        android.Manifest.permission.ACCESS_FINE_LOCATION
    )
    if (permissionCheckResult == PackageManager.PERMISSION_GRANTED) {
        Log.d("Permissions Test", "permissionCheckResult:$permissionCheckResult ")
        // If permission is granted, you can perform related operations here
        getCurrentLocation(viewModel, context)
    } else {
        Log.d("Permissions Test", "permissionCheckResult:$permissionCheckResult ")
        permissionLauncher.launch(android.Manifest.permission.ACCESS_FINE_LOCATION)
    }
}

@SuppressLint("MissingPermission")
fun getCurrentLocation(viewModel: WeatherViewModel, context: Context) {
    val fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)
   fusedLocationClient.lastLocation
       .addOnSuccessListener {location->
           location?.let {
               val latLng = "${it.latitude},${it.longitude}"
               Log.d("Permissions Test", "getCurrentLocation: current Location : $latLng")
               viewModel.saveCurrentLocation(latLng)
           }?: run {
               //
               Log.d("Permissions Test", "getCurrentLocation: current Location : null ")
           }
       }
       .addOnFailureListener {e:Exception->
           //
           Log.d("Permissions Test", "getCurrentLocation:addOnFailureListener ${e.message} ")
       }
}



























@ExperimentalMaterialApi
@Preview(showBackground = true)
@Composable
fun HomePreview() {
//    HomeScreen(nav = rememberNavController())
}