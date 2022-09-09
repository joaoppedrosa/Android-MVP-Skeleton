package com.jppedrosa.mvpskeleton.di.component

import android.app.Application
import com.jppedrosa.mvpskeleton.MVPSkeletonApplication
import com.jppedrosa.mvpskeleton.di.builder.ActivityBuilder
import com.jppedrosa.mvpskeleton.di.builder.FragmentBuilder
import com.jppedrosa.mvpskeleton.di.module.ApplicationModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

/**
 * @author João Pedro Pedrosa (<a href="mailto:joaopopedrosa@gmail.com">joaopopedrosa@gmail.com</a>) on 09/09/2022.
 */
@Singleton
@Component(
    modules = [
        (AndroidInjectionModule::class),
        (ApplicationModule::class),
        (ActivityBuilder::class),
        (FragmentBuilder::class)
    ]
)
interface ApplicationComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): ApplicationComponent
    }

    fun inject(app: MVPSkeletonApplication)
}