package com.exam.upraxis.di.module

import android.app.Application
import com.exam.upraxis.data.local.source.UserListLocalSource
import com.exam.upraxis.data.remote.service.UserApiService
import com.exam.upraxis.data.remote.source.GetUsersRemoteSource
import com.exam.upraxis.data.repository.SimpleRepository
import com.exam.upraxis.data.usecase.AppInitializationUseCaseImpl
import com.exam.upraxis.data.usecase.UserUseCaseImpl
import com.exam.upraxis.domain.base.Repository
import com.exam.upraxis.domain.model.User
import com.exam.upraxis.domain.rx.SchedulerProvider
import com.exam.upraxis.domain.usecase.AppInitializationUseCase
import com.exam.upraxis.domain.usecase.UserUseCase
import dagger.Module
import dagger.Provides
import io.realm.Realm
import io.realm.RealmConfiguration
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class DataModule {
    @Provides
    internal fun provideApi(retrofit: Retrofit): UserApiService = retrofit.create(
        UserApiService::class.java)

    @Singleton
    @Provides
    fun realmConfiguration(application: Application): RealmConfiguration {
        Realm.init(application)
        return RealmConfiguration.Builder()
            .deleteRealmIfMigrationNeeded()
            .build()
    }

    @Provides
    fun userListRepository(
        localSource: UserListLocalSource,
        remoteSource: GetUsersRemoteSource,
        schedulerProvider: SchedulerProvider
    ): Repository<List<User>> = SimpleRepository(
        localSource = localSource,
        remoteSource = remoteSource,
        schedulerProvider = schedulerProvider
    )

    @Provides
    fun appInitializationUseCase(appInitializationUseCaseImpl: AppInitializationUseCaseImpl): AppInitializationUseCase = appInitializationUseCaseImpl

    @Provides
    fun userUseCase(userUseCaseImpl: UserUseCaseImpl): UserUseCase = userUseCaseImpl
}