package com.fy.qrcodescanner

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import androidx.activity.result.ActivityResultLauncher
import androidx.core.content.ContextCompat

object QrScannerLauncher {

    fun launchWithPermissionCheck(
        context: Context,
        permissionLauncher: ActivityResultLauncher<String>,
        scannerLauncher: ActivityResultLauncher<Intent>
    ) {
        if (ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.CAMERA
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            val intent = Intent(context, QrScannerActivity::class.java)
            scannerLauncher.launch(intent)
        } else {
            permissionLauncher.launch(Manifest.permission.CAMERA)
        }
    }

    fun restartScanner(
        context: Context,
        permissionLauncher: ActivityResultLauncher<String>,
        scannerLauncher: ActivityResultLauncher<Intent>
    ) {
        if (ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.CAMERA
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            val intent = Intent(context, QrScannerActivity::class.java).apply {
                // Completely clear the activity stack and start fresh
                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or
                        Intent.FLAG_ACTIVITY_CLEAR_TASK or
                        Intent.FLAG_ACTIVITY_CLEAR_TOP)
                // Add a timestamp parameter to force a fresh instance
                putExtra("restart_timestamp", System.currentTimeMillis())
            }
            scannerLauncher.launch(intent)
        } else {
            permissionLauncher.launch(Manifest.permission.CAMERA)
        }
    }

    fun stopScanner(context: Context) {
        // Send a broadcast intent to tell QrScannerActivity to close itself
        val intent = Intent("com.fy.qrcodescanner.ACTION_CLOSE_SCANNER")
        context.sendBroadcast(intent)
    }

}
