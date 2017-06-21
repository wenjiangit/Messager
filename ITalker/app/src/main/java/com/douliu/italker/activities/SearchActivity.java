package com.douliu.italker.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.SearchView;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.douliu.italker.R;
import com.example.commom.app.ToolbarActivity;

/**
 * 搜索的Activity
 */
public class SearchActivity extends ToolbarActivity {

    private static final String EXTRA_TYPE = "EXTRA_TYPE";
    public static final int TYPE_USER = 1;
    public static final int TYPE_GROUP = 2;
    private int mType;

    public static void show(Context context,int type) {
        Intent intent = new Intent(context, SearchActivity.class);
        intent.putExtra(EXTRA_TYPE, type);
        context.startActivity(intent);
    }

    @Override
    protected boolean initArgs(Bundle bundle) {
        mType = bundle.getInt(EXTRA_TYPE);
        return mType == TYPE_USER || mType == TYPE_GROUP;
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_search;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.search, menu);
        MenuItem item = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(item);
        initSearchView(searchView);
        return true;
    }

    private void initSearchView(SearchView searchView) {
        if (searchView == null) {
            return;
        }
//        searchView.setIconifiedByDefault(false);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                search(query);
                return true;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                if (TextUtils.isEmpty(newText)) {
                    search("");
                }
                return true;
            }
        });
    }

    private void search(String query) {

    }

}
