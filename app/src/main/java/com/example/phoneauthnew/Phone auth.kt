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
import com.google.firebase.FirebaseException
import com.google.firebase.auth.*
import com.google.firebase.auth.PhoneAuthProvider
import java.util.concurrent.TimeUnit

@Composable
fun PhoneAuthScreen(navController: NavController) {
    var phoneNumber by remember { mutableStateOf("") }
    val context = LocalContext.current
    val activity = context as? Activity ?: return

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            value = phoneNumber,
            onValueChange = { phoneNumber = it },
            label = { Text("Enter Phone Number") },
            leadingIcon = { Text("+91") }
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            if (phoneNumber.length == 10) {
                startPhoneAuth("+91$phoneNumber", activity, navController)
            } else {
                Toast.makeText(context, "Enter a valid 10-digit phone number", Toast.LENGTH_SHORT).show()
            }
        }) {
            Text("Send OTP")
        }
    }
}


fun startPhoneAuth(phoneNumber: String, activity: Activity, navController: NavController) {
    val auth = FirebaseAuth.getInstance()

    val callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
        override fun onVerificationCompleted(credential: PhoneAuthCredential) {
            signInWithCredential(credential, activity, navController)
        }

        override fun onVerificationFailed(e: FirebaseException) {
            Toast.makeText(activity, "Verification failed: ${e.message}", Toast.LENGTH_SHORT).show()
        }

        override fun onCodeSent(id: String, token: PhoneAuthProvider.ForceResendingToken) {
            navController.navigate("otp/$id")
        }
    }

    val options = PhoneAuthOptions.newBuilder(auth)
        .setPhoneNumber(phoneNumber)
        .setTimeout(60L, TimeUnit.SECONDS)
        .setActivity(activity)
        .setCallbacks(callbacks)
        .build()

    PhoneAuthProvider.verifyPhoneNumber(options)
}
