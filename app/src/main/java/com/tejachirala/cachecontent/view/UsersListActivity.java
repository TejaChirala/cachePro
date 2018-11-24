package com.tejachirala.cachecontent.view;

import android.app.ActivityOptions;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.tejachirala.cachecontent.R;
import com.tejachirala.cachecontent.adapter.AccountAdapter;
import com.tejachirala.cachecontent.model.Account;
import com.tejachirala.cachecontent.model.Resource;
import com.tejachirala.cachecontent.model.Status;
import com.tejachirala.cachecontent.viewmodel.UsersViewModel;

import java.io.IOException;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UsersListActivity extends BaseActivity implements AccountAdapter.AccountClickListener, BaseActivity.DialogListener {

    public static final String KEY_ACCOUNT_ID = "accountId";

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout swipeRefreshLayout;

    private AccountAdapter adapter;
    private UsersViewModel usersViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        adapter = new AccountAdapter(this, this);
        recyclerView.setAdapter(adapter);

        RecyclerView.LayoutManager staggeredGridLayoutManager =
                new StaggeredGridLayoutManager(getResources().getInteger(R.integer.column_count), StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(staggeredGridLayoutManager);

        setSwipeRefreshLayout();

        usersViewModel = ViewModelProviders.of(this).get(UsersViewModel.class);
        usersViewModel.getAccountsLiveData()
                .observe(this, new Observer<Resource>() {
                    @Override
                    public void onChanged(@Nullable Resource resource) {
                        processResponse(resource);
                    }
                });
        usersViewModel.getAccounts();
    }

    private void setSwipeRefreshLayout() {

        int arrowColor = ContextCompat.getColor(this, R.color.colorPrimary);
        swipeRefreshLayout.setColorSchemeColors(arrowColor, arrowColor, arrowColor, arrowColor);
        swipeRefreshLayout.setEnabled(true);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {

            @Override
            public void onRefresh() {
                usersViewModel.refreshAccounts(false);
            }
        });

    }

    private void processResponse(Resource resource) {

        switch (resource.status) {
            case Status.LOADING:
                showProgress(getStringFromResource(R.string.loading), false);
                break;

            case Status.SUCCESS:
                swipeRefreshLayout.setRefreshing(false);
                dismissProgress();
                setAccountsData((List<Account>) resource.data);
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

    private void setAccountsData(List<Account> data) {
        adapter.setAccountDataSet(data);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onAccountClicked(String accountId) {

        Intent intent = new Intent(this, UserDetailsActivity.class);
        intent.putExtra(KEY_ACCOUNT_ID, accountId);
        ActivityOptions options = ActivityOptions.makeCustomAnimation(this, R.anim.slide_from_right, R.anim.stay);
        startActivity(intent, options.toBundle());

    }

    @Override
    public void onDialogPositiveButtonClicked() {
        usersViewModel.refreshAccounts(false);
    }
}
