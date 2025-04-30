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
}
