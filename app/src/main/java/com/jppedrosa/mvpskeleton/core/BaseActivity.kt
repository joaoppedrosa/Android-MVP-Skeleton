package com.jppedrosa.mvpskeleton.core

import android.os.Bundle
import android.view.WindowManager
import androidx.annotation.NonNull
import androidx.fragment.app.FragmentActivity
import com.jppedrosa.mvpskeleton.core.connection.ConnectionCallback
import com.jppedrosa.mvpskeleton.core.connection.ConnectivityProvider
import dagger.android.AndroidInjection

abstract class BaseActivity<Presenter : BasePresenter<*>> : FragmentActivity(),
    ConnectivityProvider.ConnectivityStateListener {

    /**
     * Connectivity Internet State Provider
     */
    private val provider: ConnectivityProvider by lazy { ConnectivityProvider.createProvider(this) }

    /**
     * Internet connection callback
     */
    private var connectionCallback: ConnectionCallback? = null

    /**
     * The Presenter
     */
    var presenter: Presenter? = null

    /**
     * Create presenter
     *
     * @return
     */
    @NonNull
    protected abstract fun createPresenter(): Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState ?: Bundle())
        this.presenter = createPresenter()
    }

    override fun onResume() {
        this.window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN)
        super.onResume()
    }

    override fun onStart() {
        this.window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN)
        super.onStart()
        this.provider.addListener(this)
    }

    override fun onStop() {
        this.provider.removeListener(this)
        super.onStop()
    }

    override fun onDestroy() {
        if (this.presenter != null) {
            this.presenter!!.destroyView()
        }

        super.onDestroy()
    }

    /**
     * On internet state change
     *
     * @param state of the internet
     */
    override fun onStateChange(state: ConnectivityProvider.NetworkState) {
        val hasInternet = state.hasInternet()
        if (this.connectionCallback == null) {
            return
        }

        if (hasInternet) {
            this.connectionCallback!!.onConnected()
        } else {
            this.connectionCallback!!.onNotConnected()
        }
    }

    /**
     * Set connection callback
     *
     * @param connectionCallback
     */
    public fun setConnectionCallback(connectionCallback: ConnectionCallback?) {
        this.connectionCallback = connectionCallback
    }

    /**
     * Has internet
     *
     * @return true if has internet connection, otherwise return false
     */
    private fun ConnectivityProvider.NetworkState.hasInternet(): Boolean {
        return (this as? ConnectivityProvider.NetworkState.ConnectedState)?.hasInternet == true
    }
}