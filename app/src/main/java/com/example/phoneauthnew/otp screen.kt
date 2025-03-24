package com.example.phoneauthnew



import android.app.Activity
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider

@Composable
fun OtpScreen(navController: NavController, verificationId: String?) {
    var otp by remember { mutableStateOf("") }
    val context = LocalContext.current
    val activity = context as? Activity ?: return

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            value = otp,
            onValueChange = { otp = it },
            label = { Text("Enter OTP") }
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            verificationId?.let {
                verifyOtp(it, otp, activity, navController)
            }
        }) {
            Text("Verify OTP")
        }
    }
}

fun verifyOtp(verificationId: String, otp: String, activity: Activity, navController: NavController) {
    val credential = PhoneAuthProvider.getCredential(verificationId, otp)
    signInWithCredential(credential, activity, navController)
}

fun signInWithCredential(credential: PhoneAuthCredential, activity: Activity, navController: NavController) {
    FirebaseAuth.getInstance().signInWithCredential(credential)
        .addOnCompleteListener { task ->
            if (task.isSuccessful) {
                Toast.makeText(activity, "Login Successful", Toast.LENGTH_SHORT).show()
                navController.navigate("success")
            } else {
                Toast.makeText(activity, "Login Failed: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
            }
        }
}
