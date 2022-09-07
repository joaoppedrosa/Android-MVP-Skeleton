package com.jppedrosa.mvpskeleton

import android.os.Bundle
import android.util.Log
import com.jppedrosa.mvpskeleton.core.BaseActivity
import com.jppedrosa.mvpskeleton.core.connection.ConnectionCallback

class MainActivity : BaseActivity<MainPresenter>(), MainView, ConnectionCallback {

    private val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setConnectionCallback(this)
    }

    override fun createPresenter(): MainPresenter {
        return MainPresenter(this, this)
    }

    override fun sendViewScreen() {
        Log.d(TAG, this.localClassName)
    }

    override fun onConnected() {
        Log.d(TAG, "Internet connected!")
    }

    override fun onNotConnected() {
        Log.d(TAG, "Lost Connection: Internet disconnected!")
    }
}