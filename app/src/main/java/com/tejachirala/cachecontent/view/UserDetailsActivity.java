package com.tejachirala.cachecontent.view;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.tejachirala.cachecontent.R;
import com.tejachirala.cachecontent.model.Account;
import com.tejachirala.cachecontent.model.Resource;
import com.tejachirala.cachecontent.model.Status;
import com.tejachirala.cachecontent.viewmodel.UsersDetailsViewModel;
import com.tejachirala.cachecontent.viewmodel.ViewModelFactory;
import com.tejachirala.cachepro.CachePro;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UserDetailsActivity extends BaseActivity {

    @BindView(R.id.profile_pic_iv)
    ImageView profilePic;

    @BindView(R.id.background_iv)
    ImageView background;

    @BindView(R.id.user_name_tv)
    TextView userName;

    @BindView(R.id.likes_count)
    TextView likesCount;

    @BindView(R.id.category_count)
    TextView categoriesCount;

    @BindView(R.id.collection_count)
    TextView collectionsCount;

    private UsersDetailsViewModel usersDetailsViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_details);

        ButterKnife.bind(this);

        String accountId = "";
        if (getIntent() != null && getIntent().hasExtra(UsersListActivity.KEY_ACCOUNT_ID)) {
            accountId = getIntent().getStringExtra(UsersListActivity.KEY_ACCOUNT_ID);
        }

        usersDetailsViewModel = ViewModelProviders.of(this,
                new ViewModelFactory(getApplication(), accountId))
                .get(UsersDetailsViewModel.class);

        usersDetailsViewModel.getAccountsLiveData()
                .observe(this, new Observer<Resource>() {
                    @Override
                    public void onChanged(@Nullable Resource resource) {
                        processResponse(resource);
                    }
                });
        usersDetailsViewModel.getAccount();
    }

    private void processResponse(Resource resource) {

        switch (resource.status) {
            case Status.LOADING:
                showProgress(getStringFromResource(R.string.loading), false);
                break;

            case Status.SUCCESS:
                dismissProgress();
                setAccountData((Account) resource.data);
                break;

            case Status.ERROR:
                dismissProgress();
                Exception e = (Exception) resource.data;
                if (e instanceof IOException) {
                    showNetworkErrorDialog(null);
                } else {
                    showInternalErrorDialog(null);
                }
                break;
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


    private void setAccountData(Account data) {

        CachePro.with(this)
                .load(data.getUser().getProfileImage().getLarge())
                .getAsImageBitmap()
                .withPlaceHolder(R.drawable.baseline_account_circle_black_48)
                .into(profilePic);

        CachePro.with(this)
                .load(data.getUrls().getFull())
                .getAsImageBitmap()
                .into(background);

        userName.setText(data.getUser().getName());
        likesCount.setText(String.valueOf(data.getLikes()));
        categoriesCount.setText(String.valueOf(data.getCategories().size()));
        collectionsCount.setText(String.valueOf(data.getCurrentUserCollections().size()));

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.stay, R.anim.slide_to_right);
    }
}
