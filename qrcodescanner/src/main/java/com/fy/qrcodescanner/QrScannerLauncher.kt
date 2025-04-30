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

    fun stopScanner(context: Context) {
        val intent = Intent(context, QrScannerActivity::class.java).apply {
            action = "com.fy.qrcodescanner.ACTION_STOP_SCANNER"
            flags = Intent.FLAG_ACTIVITY_NEW_TASK
        }
        context.startActivity(intent)
    }

}
