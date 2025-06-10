# 🍕Firebase Phone Authentication in Kotlin Jetpack Compose

This repository demonstrates **Firebase Phone Authentication** in **Kotlin Jetpack Compose** using Firebase Authentication.

---

## 🚀 Features
- **Phone Number Authentication** using Firebase
- **OTP Verification**
- **Navigation Between Screens** (Phone Input → OTP → Success)
- **Error Handling & Debugging**
- **Jetpack Compose UI**

---

## 🛠️ Setup Instructions

### 1️⃣ **Clone the Repository**
```sh
git clone https://github.com/shyam21biswas/firebase-phone-authentication.git
cd firebase-phone-authentication
```

### 2️⃣ **Set Up Firebase**
1. Go to [Firebase Console](https://console.firebase.google.com/)
2. **Create a New Project** (or select an existing one)
3. **Enable Phone Authentication**:
   - Go to **Authentication → Sign-in method**
   - Enable **Phone Authentication**
   - Add a **Test Phone Number** (e.g., `+91 9876543210`, OTP: `123456`)
4. **Download `google-services.json`**:
   - Go to **Project Settings → General → Your apps**
   - Download `google-services.json` and place it in the **`app/`** folder

### 3️⃣ **Add SHA-1 & SHA-256 Keys in Firebase**
1. Run this command in the terminal:
   ```sh
   ./gradlew signingReport
   ```
2. Copy the **SHA-1** and **SHA-256** keys
3. Go to **Firebase Console → Project Settings → General → Add Fingerprint**
4. Paste the keys and **Save Changes**

### 4️⃣ **Enable Firebase Billing (Required)**
1. Go to [Google Cloud Console](https://console.cloud.google.com/)
2. Select your Firebase project
3. Click **Billing** in the left menu
4. If billing is not enabled, **link a billing account** (Free quota still applies!)

---

## 📦 Dependencies

Add these dependencies in **`app/build.gradle.kts`**:

```kotlin
dependencies {
    implementation(platform("com.google.firebase:firebase-bom:32.7.2"))
    implementation("com.google.firebase:firebase-auth-ktx")
    implementation("com.google.android.gms:play-services-auth:20.7.0")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")
}
```

Sync Gradle after adding dependencies.

---

## 🎯 Running the App
1. **Open in Android Studio**
2. **Connect a Physical Device** (Phone Authentication might not work on an emulator)
3. **Run the App**

---

## ❌ Common Issues & Fixes

| Issue | Solution |
|-------|----------|
| `An internal error has occurred. [ BILLING_NOT_ENABLED ]` | Enable **Billing** in Google Cloud Console |
| `Quota exceeded for phone verification` | Use **Firebase Test Phone Number** |
| `Invalid phone number format` | Ensure the number is in **E.164 format** (e.g., `+91XXXXXXXXXX`) |
| `App not working in Release Mode` | **Add SHA-1 & SHA-256** in Firebase Console |
| `OTP not received on some numbers` | Try a different number or **avoid VPNs** |

---

## 📜 License
This project is **MIT Licensed**.

---

## 🤝 Contributing
1. Fork the repository
2. Create a new branch (`git checkout -b feature-name`)
3. Commit your changes (`git commit -m 'Added new feature'`)
4. Push to GitHub (`git push origin feature-name`)
5. Open a Pull Request 🚀

---

## 💡 Credits
Created by **Shyam Biswas** 🎉

---

## 🌟 Support
If you like this project, give it a ⭐ on GitHub!

