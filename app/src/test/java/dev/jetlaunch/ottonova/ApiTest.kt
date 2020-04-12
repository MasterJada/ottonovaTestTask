package dev.jetlaunch.ottonova

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import dev.jetlaunch.ottonova.network.OttonovaApi
import dev.jetlaunch.ottonova.repositories.ApiRepository
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiTest {
    @Rule
    @JvmField
    var rule = InstantTaskExecutorRule()

    private var profileId = "freemium_profile"


    private val mockClient = OkHttpClient
        .Builder()
        .addInterceptor(MockInterceptor())
        .build()

    private val api = Retrofit.Builder()
        .addCallAdapterFactory(CoroutineCallAdapterFactory.invoke())
        .addConverterFactory(GsonConverterFactory.create())
        .client(mockClient)
        .baseUrl(Constant.BASE_URL)
        .build()
        .create(OttonovaApi::class.java)



    private val repository = ApiRepository(api)


    @Test
    fun loadProfile() {
        runBlocking {
            val data = repository.loadProfile()
            Assert.assertNotNull(data?.profile_id)
            Assert.assertEquals(profileId, data?.profile_id)
        }
    }

    @Test
    fun loadHelpPrompts() {
        runBlocking {
            val data = repository.loadLoadHelpPrompts(profileId)
            Assert.assertNotNull(data)
            Assert.assertNotEquals(0, data?.size)
        }
    }

    @Test
    fun loadTimeLineEvents() {
        runBlocking {
            val data = repository.loadTimeLineEvents(profileId)
            Assert.assertNotNull(data)
            Assert.assertNotEquals(0, data?.size)
        }
    }
}