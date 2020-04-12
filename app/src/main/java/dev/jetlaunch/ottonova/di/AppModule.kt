package dev.jetlaunch.ottonova.di

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import dagger.Module
import dagger.Provides
import dev.jetlaunch.ottonova.Constant
import dev.jetlaunch.ottonova.network.OttonovaApi
import dev.jetlaunch.ottonova.repositories.ApiRepository
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    @Singleton
    fun provideOttanovaAPI() : OttonovaApi{
        val builder = Retrofit.Builder()
            .addCallAdapterFactory(CoroutineCallAdapterFactory.invoke())
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(Constant.BASE_URL)

        return builder.build().create(OttonovaApi::class.java)
    }

    @Provides
    fun provideApiRepository( api: OttonovaApi) = ApiRepository(api)
}