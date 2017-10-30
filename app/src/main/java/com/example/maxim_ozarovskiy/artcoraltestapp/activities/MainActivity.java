package com.example.maxim_ozarovskiy.artcoraltestapp.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.dinoExample.maxim_ozarovskiy.artcoraltestapp.R;
import com.example.maxim_ozarovskiy.artcoraltestapp.adapter.DinoListAdapter;
import com.example.maxim_ozarovskiy.artcoraltestapp.interfaces.MainViewContract;
import com.example.maxim_ozarovskiy.artcoraltestapp.model.dinoView.Dino;
import com.example.maxim_ozarovskiy.artcoraltestapp.model.dinoView.DinoViewExample;
import com.example.maxim_ozarovskiy.artcoraltestapp.network.RESTСlient;
import com.example.maxim_ozarovskiy.artcoraltestapp.presenter.MainPresenter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.maxim_ozarovskiy.artcoraltestapp.activities.LoginActivity.APP_PREFERENCES;
import static com.example.maxim_ozarovskiy.artcoraltestapp.activities.LoginActivity.APP_PREFERENCES_SESSION_ID;
import static com.example.maxim_ozarovskiy.artcoraltestapp.activities.LoginActivity.APP_PREFERENCES_SESSION_NAME;
import static com.example.maxim_ozarovskiy.artcoraltestapp.activities.LoginActivity.APP_PREFERENCES_TOKEN;


public class MainActivity extends AppCompatActivity implements MainViewContract.view {

    private Button add;
    private String token;
    private String cookie;
    private String cookie2;
    private String sessIdText;
    private String sessNameText;

    private List<Dino> dinoViewList;
    private RecyclerView recyclerView;
    private DinoListAdapter myAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private SwipeRefreshLayout swipeRefreshLayout;
    private RelativeLayout relativeLayout;
    private SharedPreferences mySettings;
    private MainPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycler_view);
        presenter = new MainPresenter(this);
        initUI();
        getData();
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), CreateDinoActivity.class);
                startActivity(intent);
            }
        });


        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshItems();
            }
        });

    }

    private void getData() {
        if (mySettings.contains(APP_PREFERENCES_TOKEN)
                && mySettings.contains(APP_PREFERENCES_SESSION_ID)
                && mySettings.contains(APP_PREFERENCES_SESSION_NAME)) {
            token = mySettings.getString(APP_PREFERENCES_TOKEN, "");
            sessIdText = mySettings.getString(APP_PREFERENCES_SESSION_ID, "");
            sessNameText = mySettings.getString(APP_PREFERENCES_SESSION_NAME, "");

            cookie = sessIdText + "=" + sessNameText;
            cookie2 = sessNameText + "=" + sessIdText;
            presenter.getDino(token,cookie);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.logout_toolbar_btn:
                presenter.logoutUserButton(token,cookie2);
                return (true);
        }
        return(super.onOptionsItemSelected(item));
    }

    private void clearPreffs(){
        SharedPreferences.Editor editor = mySettings.edit();
        editor.remove("token");
        editor.remove("session_id");
        editor.remove("session_name");
        editor.remove("user_name");
        editor.apply();

    }

    private void goToLoginScreen() {
        Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
        startActivity(intent);
        finish();
    }

    private void refreshItems() {
        // Load items
        // ...
        getData();

        // Load complete
        onItemsLoadComplete();
    }

    private void onItemsLoadComplete() {
        // Update the adapter and notify data set changed
        // ...


        // Stop refresh animation
        swipeRefreshLayout.setRefreshing(false);
    }

    private void initUI() {
        recyclerView = (RecyclerView) findViewById(R.id.recycler);
        relativeLayout = (RelativeLayout) findViewById(R.id.relative_layout);
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh);
        add = (Button) findViewById(R.id.add_dino_btn_recycler_layout);
        mySettings = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
    }

    private void setData() {

        myAdapter = new DinoListAdapter(this, dinoViewList);
        myAdapter.notifyDataSetChanged();
        mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(myAdapter);

    }

    @Override
    public void callbackDino(List<Dino> dino) {
        dinoViewList = dino;
        setData();
    }

    @Override
    public void callbackDinoError(String alert) {
        Toast.makeText(this,alert,Toast.LENGTH_LONG).show();
    }

    @Override
    public void callbackLogout(String successfull) {
        Toast.makeText(this,successfull,Toast.LENGTH_LONG).show();
        clearPreffs();
        goToLoginScreen();
        finish();
    }

    @Override
    public void callbackLogoutError(String alert) {
        Toast.makeText(this,alert,Toast.LENGTH_LONG).show();
    }

}
