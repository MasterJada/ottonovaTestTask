package dev.jetlaunch.ottonova.network

import dev.jetlaunch.ottonova.models.HealthPrompt
import dev.jetlaunch.ottonova.models.Profile
import dev.jetlaunch.ottonova.models.TimelineModel
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Path


interface OttonovaApi {
    @GET("api/user/customer/profiles")
    fun loadProfilesAsync():Deferred<ArrayList<Profile>>

    @GET("api/user/customer/profiles/{profile_id}/timeline-events")
    fun loadTimelineEventsAsync(@Path("profile_id") profileId: String) : Deferred<ArrayList<TimelineModel>>

    @GET("api/user/customer/profiles/{profile_id}/health-prompts")
    fun loadHealthPromptsAsync(@Path("profile_id") profileId: String) : Deferred<ArrayList<HealthPrompt>>
}