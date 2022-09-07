package com.jppedrosa.mvpskeleton.core

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.fragment.app.Fragment
import dagger.android.support.AndroidSupportInjection

/**
 * @author João Pedro Pedrosa (<a href="mailto:joaopopedrosa@gmail.com">joaopopedrosa@gmail.com</a>) on 07/09/2022.
 */
abstract class BaseFragment<Presenter : BasePresenter<*>> : Fragment() {

    /**
     * The Presenter.
     */
    var presenter: Presenter? = null

    /**
     * Create presenter presenter.
     *
     * @return the presenter
     */
    @NonNull
    protected abstract fun createPresenter(): Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState ?: Bundle())
        this.presenter = createPresenter()
    }

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        sendViewScreen()
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onDestroy() {
        super.onDestroy()
        if (this.presenter != null) {
            this.presenter!!.destroyView()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        if (this.presenter != null) {
            this.presenter!!.destroyView()
        }
    }

    /**
     * Register for metrics that view was seen
     */
    protected abstract fun sendViewScreen()
}