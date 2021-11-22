package com.example.delcampofresco.ui.fruits;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class FruitsViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public FruitsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is fruits fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}