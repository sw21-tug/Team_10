package com.example.teachomatic3000.ui.Einstellungen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class EinstellungenViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is datum Fragment"
    }
    val text: LiveData<String> = _text
}