package com.tejachirala.cachecontent.viewmodel;

import android.app.Application;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

/**
 * Created by tejachirala on 24/11/18
 */
public class ViewModelFactory extends ViewModelProvider.AndroidViewModelFactory {

    private Application application;
    private String accountId;

    public ViewModelFactory(@NonNull Application application, String accountId) {
        super(application);
        this.application = application;
        this.accountId = accountId;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new UsersDetailsViewModel(application, accountId);
    }
}
