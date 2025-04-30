package com.fy.qrcodescanner

interface QrScannerListener {
    fun onQrScanned(qrCode: String)
}