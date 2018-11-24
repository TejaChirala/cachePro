package com.tejachirala.cachecontent.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.tejachirala.cachecontent.model.Account;
import com.tejachirala.cachecontent.model.Resource;
import com.tejachirala.cachepro.CachePro;
import com.tejachirala.cachepro.CompletionListener;

import org.json.JSONArray;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by tejachirala on 24/11/18
 */
public class UsersViewModel extends AndroidViewModel {

    private MutableLiveData<Resource> response = null;
    private final String url = "http://pastebin.com/raw/wgkJgazE";

    public UsersViewModel(@NonNull Application application) {
        super(application);
        response = new MutableLiveData<>();

    }

    public MutableLiveData<Resource> getAccountsLiveData() {
        return response;
    }

    public void refreshAccounts() {
        refreshAccounts(true);
    }

    public void refreshAccounts(boolean showProgress) {
        CachePro.with(getApplication())
                .invalidate(url);
        getAccounts(showProgress);
    }

    public void getAccounts() {
        getAccounts(true);
    }

    public void getAccounts(boolean showProgress) {

        if (showProgress)
            response.setValue(Resource.loading(null));

        CachePro.with(getApplication())
                .load(url)
                .getAsJSONArray()
                .setCompletionListener(new CompletionListener<JSONArray>() {
                    @Override
                    public void onCompleted(boolean isSuccess, Exception e, JSONArray result, String loadedFrom) {
                        if (isSuccess) {
                            Type collectionType = new TypeToken<List<Account>>() {
                            }.getType();
                            List<Account> accountList = new Gson().fromJson(result.toString(), collectionType);
                            response.setValue(Resource.success(accountList));
                        } else {
                            response.setValue(Resource.error("", e));
                        }
                    }
                });

    }

}
