package com.douliu.italker.frags.search;


import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.douliu.italker.R;
import com.douliu.italker.activities.SearchActivity;
import com.example.commom.app.PresenterFragment;
import com.example.commom.widget.EmptyView;
import com.example.commom.widget.PortraitView;
import com.example.commom.widget.recycler.RecyclerAdapter;
import com.example.factory.model.card.UserCard;
import com.example.factory.presenter.search.SearchContract;
import com.example.factory.presenter.search.UserSearchPresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 用户搜索的fragment
 */
public class UserSearchFragment extends PresenterFragment<SearchContract.Presenter>
        implements SearchContract.UserView, SearchActivity.SearchFragment {

    @BindView(R.id.recycler)
    RecyclerView mRecycler;
    @BindView(R.id.empty)
    EmptyView mEmpty;

    private RecyclerAdapter<UserCard> mAdapter;

    public UserSearchFragment() {
        // Required empty public constructor
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.fragment_user_search;
    }

    @Override
    protected void initWidget(View rootView) {
        super.initWidget(rootView);

        mRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecycler.setAdapter(mAdapter = new RecyclerAdapter<UserCard>() {
            @Override
            protected int getItemViewType(int position, UserCard userCard) {
                return R.layout.cell_search_list;
            }

            @Override
            protected ViewHolder<UserCard> onCreateViewHolder(View root, int viewType) {
                return new UserSearchFragment.ViewHolder(root);
            }
        });

        mEmpty.bind(mRecycler);
        setPlaceHolderView(mEmpty);
    }

    @Override
    public void onSearchDone(List<UserCard> userCards) {
        mAdapter.replace(userCards);

        mPlaceHolderView.triggerOkOrEmpty(mAdapter.getItemCount() > 0);
    }

    @Override
    protected SearchContract.Presenter createPresenter() {
        return new UserSearchPresenter(this);
    }

    @Override
    public void search(String content) {
        mPresenter.search(content);
    }


    class ViewHolder extends RecyclerAdapter.ViewHolder<UserCard>{

        @BindView(R.id.im_portrait)
        PortraitView mPortraitView;
        @BindView(R.id.tv_name)
        TextView mTvName;
        @BindView(R.id.im_follow)
        ImageView mImFollow;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @Override
        public void onBind(UserCard userCard) {
            Glide.with(UserSearchFragment.this)
                    .load(userCard.getPortrait())
                    .apply(RequestOptions.centerCropTransform())
                    .into(mPortraitView);

            mTvName.setText(userCard.getName());
            mImFollow.setEnabled(userCard.isFollow());

        }
    }


}
