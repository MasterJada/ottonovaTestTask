package dev.jetlaunch.ottonova.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import dev.jetlaunch.ottonova.R
import dev.jetlaunch.ottonova.adapters.EventsAdapter
import dev.jetlaunch.ottonova.viewmodels.MainViewModel
import kotlinx.android.synthetic.main.time_line_fragment.*

class TimeLineFragment : Fragment() {
    companion object {
        fun createNewInstance(): TimeLineFragment {
            return TimeLineFragment()
        }
    }

    private val viewModel: MainViewModel by activityViewModels()
    private val adapter: EventsAdapter by lazy { EventsAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return LayoutInflater.from(requireContext())
            .inflate(R.layout.time_line_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.timeLineLiveData.observe(viewLifecycleOwner, Observer {
            adapter.items = it
        })
        viewModel.promptLiveData.observe(viewLifecycleOwner, Observer {
            tv_prompt.text = it.message
        })
        viewModel.loadProfile()
        rv_events.layoutManager = LinearLayoutManager(requireContext())
        rv_events.adapter = adapter
        adapter.setOnclickListener { timelineModel ->
            viewModel.timeLineEventLiveData.postValue(timelineModel)
            goToTimeLineEventFragment()
        }
    }

    private fun goToTimeLineEventFragment() {
        parentFragmentManager.beginTransaction()
            .replace(R.id.container, TimeLineEventFragment.createNewInstance())
            .addToBackStack("event")
            .commit()
    }
}