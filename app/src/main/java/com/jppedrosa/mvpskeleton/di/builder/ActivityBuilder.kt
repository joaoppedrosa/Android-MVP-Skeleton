package com.jppedrosa.mvpskeleton.di.builder

import com.jppedrosa.mvpskeleton.ui.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * @author João Pedro Pedrosa (<a href="mailto:joaopopedrosa@gmail.com">joaopopedrosa@gmail.com</a>) on 09/09/2022.
 */
@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector
    internal abstract fun bindMainActivity(): MainActivity
}