package com.tejachirala.cachecontent.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.tejachirala.cachecontent.R;
import com.tejachirala.cachecontent.model.Account;
import com.tejachirala.cachepro.CachePro;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by tejachirala on 24/11/18
 */
public class AccountAdapter extends RecyclerView.Adapter<AccountAdapter.ViewHolder> {

    private Context context;
    private AccountClickListener accountClickListener;
    private List<Account> accounts;

    public AccountAdapter(Context context, AccountClickListener accountClickListener) {
        this.context = context;
        this.accountClickListener = accountClickListener;
    }

    public void setAccountDataSet(List<Account> accountDataset) {
        accounts = accountDataset;
    }

    public interface AccountClickListener {

        public void onAccountClicked(String accountId);

    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.user_layout, viewGroup, false);

        ButterKnife.bind(this, view);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        final Account account = accounts.get(viewHolder.getAdapterPosition());

        CachePro.with(context)
                .load(account.getUser().getProfileImage().getLarge())
                .getAsImageBitmap()
                .withPlaceHolder(R.drawable.baseline_account_circle_black_48)
                .into(viewHolder.profilePic);

        viewHolder.userName.setText(account.getUser().getName());

        viewHolder.constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (accountClickListener != null) {
                    accountClickListener.onAccountClicked(account.getId());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return (accounts != null) ? accounts.size() : 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.main_layout)
        ConstraintLayout constraintLayout;

        @BindView(R.id.profile_pic_iv)
        ImageView profilePic;

        @BindView(R.id.user_name_tv)
        TextView userName;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
