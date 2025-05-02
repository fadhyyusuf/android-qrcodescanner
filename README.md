# QR Code Scanner Library for Android

## Overview

**QrCodeScanner** is a lightweight and customizable Android library designed specifically for scanning QR codes containing **strings or text**. Built using the **CameraX API** and **Google ML Kit**, this library provides a seamless and efficient way to add QR code scanning functionality to your Android applications.

## Features

- ✅ **String/Text QR Code Scanning Only** – optimized for textual data.
- 🚀 Easy integration into Android projects.
- ✨ Lightweight and modern – written entirely in Kotlin.
- 📸 Powered by CameraX for enhanced camera performance.
- 🔍 QR code detection with Google ML Kit for accurate and reliable results.

---

## Installation

### 1. Add the Dependency

In your project’s `build.gradle` file:

```gradle
dependencies {
    implementation 'com.github.fadhyyusuf:android-qrcodescanner:1.1.4'
}
```

### 2. Add Required Repositories

In your `settings.gradle` or root `build.gradle`:

```gradle
repositories {
    google()
    mavenCentral()
}
```

### 3. Add Camera Permission

In your `AndroidManifest.xml`:

```xml
<uses-permission android:name="android.permission.CAMERA" />
```

---

## Usage

### 🧩 Fragment Integration

```kotlin
class ExampleFragment : Fragment() {

    private val permissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            val intent = Intent(requireContext(), QrScannerActivity::class.java)
            qrScannerLauncher.launch(intent)
        } else {
            Toast.makeText(requireContext(), "Camera permission denied", Toast.LENGTH_SHORT).show()
        }
    }

    private val qrScannerLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == RESULT_OK) {
            val scanned = result.data?.getStringExtra("qr_result") ?: ""
            Toast.makeText(requireContext(), "Scanned: $scanned", Toast.LENGTH_LONG).show()
            // YOUR CODE HERE
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        QrScannerLauncher.launchWithPermissionCheck(
            context = requireContext(),
            permissionLauncher = permissionLauncher,
            scannerLauncher = qrScannerLauncher
        )
    }
}
```

---

### 🧱 Activity Integration

```kotlin
class MainActivity : AppCompatActivity() {

    private val permissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            val intent = Intent(this, QrScannerActivity::class.java)
            qrScannerLauncher.launch(intent)
        } else {
            Toast.makeText(this, "Camera permission denied", Toast.LENGTH_SHORT).show()
        }
    }

    private val qrScannerLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == RESULT_OK) {
            val scanned = result.data?.getStringExtra("qr_result") ?: ""
            Toast.makeText(this, "Scanned: $scanned", Toast.LENGTH_LONG).show()
            // YOUR CODE HERE
        }
    }

    fun startScanner() {
        QrScannerLauncher.launchWithPermissionCheck(
            context = this,
            permissionLauncher = permissionLauncher,
            scannerLauncher = qrScannerLauncher
        )
    }
}
```

---

## Output

When a QR code is successfully scanned, the result is returned via intent with the key:

```kotlin
val scanned = result.data?.getStringExtra("qr_result") ?: ""
```

---

## Acknowledgments

- [Google CameraX](https://developer.android.com/training/camerax)
- [Google ML Kit](https://developers.google.com/ml-kit/vision/barcode-scanning)

---
