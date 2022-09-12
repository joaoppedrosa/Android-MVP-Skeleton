package com.jppedrosa.mvpskeleton.ui

import android.os.Bundle
import androidx.core.content.ContextCompat
import com.jppedrosa.mvpskeleton.core.BaseActivity
import com.jppedrosa.mvpskeleton.core.connection.ConnectionCallback
import com.jppedrosa.mvpskeleton.databinding.ActivityMainBinding

class MainActivity : BaseActivity<MainPresenter>(), MainView, ConnectionCallback {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.binding = ActivityMainBinding.inflate(this.layoutInflater)
        val view = this.binding.root
        setContentView(view)
    }

    override fun createPresenter(): MainPresenter {
        return MainPresenter(this, this)
    }

    override fun onStart() {
        super.onStart()
        this.presenter?.onStart()
    }

    override fun onResume() {
        super.onResume()
        this.presenter?.onResume()
    }

    override fun onDestroy() {
        super.onDestroy()
        this.presenter?.onDestroy()
    }

    override fun startInternetConnectionListener() {
        setConnectionCallback(this)
    }

    override fun stopInternetConnectionListener() {
        setConnectionCallback(null)
    }

    override fun onConnected() {
        this.binding.internetState.text = "Internet connected!"
        this.binding.internetState.setTextColor(
            ContextCompat.getColor(
                this,
                android.R.color.holo_green_light
            )
        )
    }

    override fun onNotConnected() {
        this.binding.internetState.text = "Internet disconnected!"
        this.binding.internetState.setTextColor(
            ContextCompat.getColor(
                this,
                android.R.color.holo_red_light
            )
        )
    }
}