package dev.jetlaunch.ottonova.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.google.android.material.snackbar.Snackbar
import dev.jetlaunch.ottonova.R
import dev.jetlaunch.ottonova.viewmodels.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if(savedInstanceState == null)
            goToTimelineFragment()


        viewModel.errorData.observe(this, Observer {error ->
            if (error != null)
            Snackbar.make(container, error, Snackbar.LENGTH_INDEFINITE).setAction("retry"){
                viewModel.retry()
            }.show()
        })

    }

    private fun goToTimelineFragment(){
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, TimeLineFragment.createNewInstance())
            .commit()
    }



}
