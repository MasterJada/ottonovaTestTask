package dev.jetlaunch.ottonova.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.jetlaunch.ottonova.di.Injector
import dev.jetlaunch.ottonova.models.DateModel
import dev.jetlaunch.ottonova.models.HealthPrompt
import dev.jetlaunch.ottonova.models.ITimeLineRecyclerItem
import dev.jetlaunch.ottonova.models.TimelineModel
import dev.jetlaunch.ottonova.repositories.ApiRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel : ViewModel() {
    init {
        Injector.component?.inject(this)
    }
    @Inject
    lateinit var apiRepository: ApiRepository

    private var profileId = ""

    val errorData by lazy {   apiRepository.errorLiveData }

    val timeLineLiveData = MutableLiveData<ArrayList<ITimeLineRecyclerItem>>()
    val promptLiveData = MutableLiveData<HealthPrompt>()
    val timeLineEventLiveData = MutableLiveData<TimelineModel>()



    fun loadProfile() = viewModelScope.launch {
        apiRepository.loadProfile()?.let { profile ->
            profileId = profile.profile_id
            loadHealthPrompt()
            loadTimeLine()

        }
    }

    private fun loadTimeLine() = viewModelScope.launch {
        apiRepository.loadTimeLineEvents(profileId)?.let { resultItems ->
            val result = resultItems.groupBy { it.timestamp.substringBefore("T") }
            val items = ArrayList<ITimeLineRecyclerItem>()
            result.keys.forEach { key ->
                items.add(DateModel(key))
                result[key]?.let {
                    items.addAll(it)
                }
            }
            timeLineLiveData.postValue(items)
        }
    }

    private fun loadHealthPrompt() = viewModelScope.launch {
        apiRepository.loadLoadHelpPrompts(profileId)?.let { prompts ->
            promptLiveData.postValue(prompts.first())
        }
    }


    fun retry() = viewModelScope.launch {
        apiRepository.errorLiveData.postValue(null)
        loadProfile()
    }
}