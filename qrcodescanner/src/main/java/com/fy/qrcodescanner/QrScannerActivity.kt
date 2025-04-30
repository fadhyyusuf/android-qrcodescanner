package com.fy.qrcodescanner

import android.app.Activity
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.camera.view.PreviewView

class QrScannerActivity : AppCompatActivity(), QrScannerListener {

    private lateinit var previewView: PreviewView
    private lateinit var qrScanner: QrScanner

    private val closeReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            if (intent?.action == "com.fy.qrcodescanner.ACTION_CLOSE_SCANNER") {
                finish()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_qr_scanner)
        registerReceiver(closeReceiver, IntentFilter("com.fy.qrcodescanner.ACTION_CLOSE_SCANNER"))

        // Initialize the PreviewView FIRST
        previewView = findViewById(R.id.previewView)

        // Then safely use it
        qrScanner = QrScanner(this)
        qrScanner.start(previewView, this)
    }

    override fun onQrScanned(value: String) {
        val resultIntent = Intent().apply {
            putExtra("qr_result", value)
        }
        setResult(Activity.RESULT_OK, resultIntent)
        finish()

    }

    override fun onDestroy() {
        super.onDestroy()
        try {
            unregisterReceiver(closeReceiver)
        } catch (e: Exception) {
            // Receiver might not be registered
        }
        qrScanner.stop()
    }
}
