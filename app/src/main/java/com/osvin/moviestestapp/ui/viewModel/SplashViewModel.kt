package com.osvin.moviestestapp.ui.viewModel

import androidx.lifecycle.*
import com.osvin.moviestestapp.domain.models.SplashModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel
@Inject constructor(): ViewModel()  {

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