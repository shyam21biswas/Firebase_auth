package com.example.phoneauthnew

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

import com.example.phoneauthnew.ui.theme.PhoneAuthNewTheme

import android.app.Activity
import android.content.Context
import android.util.Log

import android.widget.Toast

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color

import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseException

import com.google.firebase.auth.*
import com.google.firebase.auth.PhoneAuthProvider
import java.util.concurrent.TimeUnit



class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            PhoneAuthNewTheme {

                val navController: NavHostController = rememberNavController()

                NavHost(navController = navController, startDestination = "phone_auth") {
                    composable("phone_auth") { PhoneAuthScreen(navController) }
                    composable("otp/{verificationId}") { backStackEntry ->
                        val verificationId = backStackEntry.arguments?.getString("verificationId")
                        OtpScreen(navController, verificationId)
                    }
                    composable("success") { SuccessScreen() }
                }



            }
        }

    }
}


