package com.example.theboredapp.view

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.theboredapp.R
import com.example.theboredapp.databinding.ActivityMainBinding
import com.example.theboredapp.viewmodel.MainActivityViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    lateinit var mainActivityViewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mainActivityViewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)

        //playing the video
        binding.videoView.setVideoURI(Uri.parse("android.resource://" + packageName + "/" + R.raw.blob))
        binding.videoView.start()
        binding.videoView.setOnPreparedListener { it.isLooping = true }

        binding.btnClick.setOnClickListener {
            progressBar.showProgressBar()
            mainActivityViewModel.getUser()!!.observe(this, Observer { serviceSetterGetter ->
                progressBar.hideProgressBar()
                val msg = serviceSetterGetter.activity
                txtResponse.text = msg
            })
        }
    }
}
