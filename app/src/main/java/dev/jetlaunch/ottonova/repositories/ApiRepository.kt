package dev.jetlaunch.ottonova.repositories

import androidx.lifecycle.MutableLiveData
import dev.jetlaunch.ottonova.models.HealthPrompt
import dev.jetlaunch.ottonova.models.Profile
import dev.jetlaunch.ottonova.models.TimelineModel
import dev.jetlaunch.ottonova.network.OttonovaApi
import java.lang.Exception
import javax.inject.Inject

class ApiRepository @Inject constructor(private val api: OttonovaApi) {

    val errorLiveData = MutableLiveData<String?>()

    suspend fun loadProfile(): Profile? {
        return try {
            val response = api.loadProfilesAsync().await()
            response.first()
        } catch (e: Exception) {
            errorLiveData.postValue(e.message ?: "")
            null
        }
    }

    suspend fun loadTimeLineEvents(profileId: String): ArrayList<TimelineModel>? {
        return try {
            api.loadTimelineEventsAsync(profileId).await()
        }catch (e: Exception){
            errorLiveData.postValue(e.message ?: "")
            null
        }
    }

    suspend fun loadLoadHelpPrompts(profileId: String): ArrayList<HealthPrompt>? {
     return   try {
             api.loadHealthPromptsAsync(profileId).await()
        }catch (e: Exception){
            errorLiveData.postValue(e.message ?: "")
            null
        }
    }

}