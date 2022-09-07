package com.jppedrosa.mvpskeleton.core.connection

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.net.ConnectivityManager.CONNECTIVITY_ACTION
import android.net.ConnectivityManager.EXTRA_NETWORK_INFO
import android.net.NetworkInfo

/**
 * @author João Pedro Pedrosa (<a href="mailto:joaopopedrosa@gmail.com">joaopopedrosa@gmail.com</a>) on 07/09/2022.
 */
@Suppress("DEPRECATION")
class ConnectivityProviderLegacyImpl(
    private val context: Context,
    private val cm: ConnectivityManager
) : ConnectivityProviderBaseImpl() {

    private val receiver = ConnectivityReceiver()

    override fun subscribe() {
        this.context.registerReceiver(this.receiver, IntentFilter(CONNECTIVITY_ACTION))
    }

    override fun unsubscribe() {
        this.context.unregisterReceiver(this.receiver)
    }

    override fun getNetworkState(): ConnectivityProvider.NetworkState {
        val activeNetworkInfo = this.cm.activeNetworkInfo
        return if (activeNetworkInfo != null) {
            ConnectivityProvider.NetworkState.ConnectedState.ConnectedLegacy(activeNetworkInfo)
        } else {
            ConnectivityProvider.NetworkState.NotConnectedState
        }
    }

    private inner class ConnectivityReceiver : BroadcastReceiver() {
        override fun onReceive(c: Context, intent: Intent) {
            val networkInfo = cm.activeNetworkInfo
            val fallbackNetworkInfo: NetworkInfo? = intent.getParcelableExtra(EXTRA_NETWORK_INFO)
            val state: ConnectivityProvider.NetworkState =
                if (networkInfo?.isConnectedOrConnecting == true) {
                    ConnectivityProvider.NetworkState.ConnectedState.ConnectedLegacy(networkInfo)
                } else if (networkInfo != null && fallbackNetworkInfo != null &&
                    networkInfo.isConnectedOrConnecting != fallbackNetworkInfo.isConnectedOrConnecting
                ) {
                    ConnectivityProvider.NetworkState.ConnectedState.ConnectedLegacy(
                        fallbackNetworkInfo
                    )
                } else {
                    val state = networkInfo ?: fallbackNetworkInfo
                    if (state != null) ConnectivityProvider.NetworkState.ConnectedState.ConnectedLegacy(
                        state
                    ) else ConnectivityProvider.NetworkState.NotConnectedState
                }
            dispatchChange(state)
        }
    }
}