package com.osvin.moviestestapp.ui.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.osvin.moviestestapp.models.SplashModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashViewModel(app: Application): AndroidViewModel(app)  {

    private val _splashModel: MutableLiveData<SplashModel> = MutableLiveData()
    val splashModel: LiveData<SplashModel> = _splashModel

    fun initSplashScreen(){
        viewModelScope.launch{
            delay(2000)
            updateLiveData()
        }
    }

    private fun updateLiveData() {
        val splashModel = SplashModel()
        _splashModel.value = splashModel
    }

}