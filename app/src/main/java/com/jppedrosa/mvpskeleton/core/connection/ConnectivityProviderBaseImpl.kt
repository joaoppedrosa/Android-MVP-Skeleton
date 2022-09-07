package com.jppedrosa.mvpskeleton.core.connection

import android.os.Handler
import android.os.Looper

/**
 * @author João Pedro Pedrosa (<a href="mailto:joaopopedrosa@gmail.com">joaopopedrosa@gmail.com</a>) on 07/09/2022.
 */
abstract class ConnectivityProviderBaseImpl : ConnectivityProvider {
    private val handler = Handler(Looper.getMainLooper())
    private val listeners = mutableSetOf<ConnectivityProvider.ConnectivityStateListener>()
    private var subscribed = false

    override fun addListener(listener: ConnectivityProvider.ConnectivityStateListener) {
        this.listeners.add(listener)
        listener.onStateChange(getNetworkState())
        verifySubscription()
    }

    override fun removeListener(listener: ConnectivityProvider.ConnectivityStateListener) {
        this.listeners.remove(listener)
        verifySubscription()
    }

    private fun verifySubscription() {
        if (!this.subscribed && this.listeners.isNotEmpty()) {
            subscribe()
            this.subscribed = true
        } else if (this.subscribed && this.listeners.isEmpty()) {
            unsubscribe()
            this.subscribed = false
        }
    }

    protected fun dispatchChange(state: ConnectivityProvider.NetworkState) {
        this.handler.post {
            for (listener in this.listeners) {
                listener.onStateChange(state)
            }
        }
    }

    protected abstract fun subscribe()
    protected abstract fun unsubscribe()
}