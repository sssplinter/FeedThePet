package com.kristina.feedthebeast.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class BeastViewModel : ViewModel() {

    companion object{
        private const val SCORE_DIVIDER = 15
    }

    private val _score = MutableLiveData<Int>(0)
    val score: LiveData<Int>
        get() = _score

    private val _animate = MutableLiveData<Boolean>(false)
    val animate : LiveData<Boolean>
        get() = _animate

    fun feed(){
        _score.value = _score.value?.plus(1)
        if(checkScore()){
            _animate.value = true
        }
    }

    private fun checkScore(): Boolean{
        return _score.value?.rem(SCORE_DIVIDER) == 0
    }

    fun doneAnimation(){
        _animate.value = false
    }

}