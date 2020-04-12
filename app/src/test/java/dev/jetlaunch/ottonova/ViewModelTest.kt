package dev.jetlaunch.ottonova

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import dev.jetlaunch.ottonova.models.*
import dev.jetlaunch.ottonova.network.OttonovaApi
import dev.jetlaunch.ottonova.repositories.ApiRepository
import dev.jetlaunch.ottonova.viewmodels.MainViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.setMain
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test


@ExperimentalCoroutinesApi
class ViewModelTest {
    @Rule
    @JvmField
    var rule = InstantTaskExecutorRule()

    init {
        Dispatchers.setMain(TestCoroutineDispatcher())
    }

    private val api = mock<OttonovaApi> {
        on { loadProfilesAsync() } doReturn GlobalScope.async { arrayListOf(Profile("profile_id")) }
        on { loadHealthPromptsAsync("profile_id") } doReturn GlobalScope.async {
            arrayListOf(
                HealthPrompt("", "messae", "", true, null, null)
            )
        }
        on { loadTimelineEventsAsync("profile_id") } doReturn GlobalScope.async {
            arrayListOf(
                TimelineModel("0", "12.04.2001", "", "", "", ""),
                TimelineModel("1", "12.04.2001", "", "", "", ""),
                TimelineModel("2", "13.04.2001", "", "", "", ""),
                TimelineModel("3", "13.04.2001", "", "", "", ""),
                TimelineModel("4", "14.04.2001", "", "", "", ""),
                TimelineModel("5", "15.04.2001", "", "", "", "")
            )
        }
    }
    private val repository = ApiRepository(api)
    private val viewModel = MainViewModel().apply {
        this.apiRepository = repository
    }

    private val expectedTimeLine = arrayListOf(
        DateModel("12.04.2001") as ITimeLineRecyclerItem,
        TimelineModel("0", "12.04.2001", "", "", "", "") as ITimeLineRecyclerItem,
        TimelineModel("1", "12.04.2001", "", "", "", "") as ITimeLineRecyclerItem,
        DateModel("13.04.2001") as ITimeLineRecyclerItem,
        TimelineModel("2", "13.04.2001", "", "", "", "") as ITimeLineRecyclerItem,
        TimelineModel("3", "13.04.2001", "", "", "", "") as ITimeLineRecyclerItem,
        DateModel("14.04.2001") as ITimeLineRecyclerItem,
        TimelineModel("4", "14.04.2001", "", "", "", "") as ITimeLineRecyclerItem,
        DateModel("15.04.2001") as ITimeLineRecyclerItem,
        TimelineModel("5", "15.04.2001", "", "", "", "") as ITimeLineRecyclerItem
    )
    private val expectedHealthPrompt = HealthPrompt("", "messae", "", true, null, null)


    @Before
    fun setup(){

        viewModel.loadProfile()
        viewModel.promptLiveData.observeForever {  }
        viewModel.timeLineLiveData.observeForever {  }
    }

    @Test
    fun `test health prompts`(){
        Assert.assertNotNull(viewModel.promptLiveData.value)
        Assert.assertEquals(expectedHealthPrompt, viewModel.promptLiveData.value)
    }
    @Test
    fun `test timeline`(){
        Assert.assertNotNull(viewModel.timeLineLiveData.value)
       Assert.assertArrayEquals(expectedTimeLine.toArray() , viewModel.timeLineLiveData.value!!.toArray())
    }


}