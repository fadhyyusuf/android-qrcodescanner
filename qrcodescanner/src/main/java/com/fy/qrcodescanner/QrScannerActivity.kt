package com.fy.qrcodescanner

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.camera.view.PreviewView

class QrScannerActivity : AppCompatActivity(), QrScannerListener {

    private lateinit var previewView: PreviewView
    private lateinit var qrScanner: QrScanner

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_qr_scanner)
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
        qrScanner.stop()
    }
}
