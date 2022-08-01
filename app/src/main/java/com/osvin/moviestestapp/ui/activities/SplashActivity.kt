package com.osvin.moviestestapp.ui.activities

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowInsets
import android.view.WindowManager
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.osvin.moviestestapp.R
import com.osvin.moviestestapp.ui.viewModel.SplashViewModel

@SuppressLint("CustomSplashScreen")
@Suppress("DEPRECATION")
class SplashActivity : AppCompatActivity() {
    private lateinit var splashViewModel: SplashViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        initViewModel()

        observeViewModel()

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.R ){
            window.insetsController?.hide(WindowInsets.Type.statusBars())
        }else{
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        }
    }

    private fun observeViewModel() {
        splashViewModel.initSplashScreen()
        splashViewModel.splashModel.observe(this, Observer {
            val intent = Intent(this, MoviesActivity::class.java)
            startActivity(intent)
            finish()
        })
    }

    private fun initViewModel() {
        splashViewModel = ViewModelProvider(this)[SplashViewModel::class.java]
    }
}