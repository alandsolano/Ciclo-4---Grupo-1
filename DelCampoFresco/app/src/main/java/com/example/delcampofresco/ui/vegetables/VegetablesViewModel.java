package com.example.delcampofresco.ui.vegetables;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class VegetablesViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public VegetablesViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is vegetables fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}