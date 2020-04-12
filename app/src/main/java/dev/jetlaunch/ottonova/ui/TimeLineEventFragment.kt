package dev.jetlaunch.ottonova.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import dev.jetlaunch.ottonova.R
import dev.jetlaunch.ottonova.viewmodels.MainViewModel
import kotlinx.android.synthetic.main.time_line_event_layout.*

class TimeLineEventFragment: Fragment() {
    companion object{
        fun createNewInstance(): TimeLineEventFragment {
            return TimeLineEventFragment()
        }
    }
    private val viewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return LayoutInflater.from(requireContext()).inflate(R.layout.time_line_event_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.timeLineEventLiveData.observe(viewLifecycleOwner, Observer { timeLineModel ->
            tv_category.text = timeLineModel.category
            tv_title.text = timeLineModel.title
            tv_description.text = timeLineModel.description
        })
    }
}