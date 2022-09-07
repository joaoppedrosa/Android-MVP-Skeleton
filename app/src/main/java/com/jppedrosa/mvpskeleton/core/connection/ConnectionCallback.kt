package com.jppedrosa.mvpskeleton.core.connection

/**
 * @author João Pedro Pedrosa (<a href="mailto:joaopopedrosa@gmail.com">joaopopedrosa@gmail.com</a>) on 07/09/2022.
 */
interface ConnectionCallback {
    fun onConnected()
    fun onNotConnected()
}