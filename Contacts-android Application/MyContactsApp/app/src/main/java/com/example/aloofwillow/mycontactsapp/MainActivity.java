package com.example.aloofwillow.mycontactsapp;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bluelinelabs.conductor.Conductor;
import com.bluelinelabs.conductor.Router;
import com.bluelinelabs.conductor.RouterTransaction;
import com.bluelinelabs.conductor.changehandler.HorizontalChangeHandler;
import com.example.aloofwillow.mycontactsapp.controllers.ContactAddController;
import com.example.aloofwillow.mycontactsapp.controllers.ContactsListController;

public class MainActivity extends AppCompatActivity{
    TextView message;
    static Menu menu;
    private Router router;
    ViewGroup container;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        container = (ViewGroup) findViewById(R.id.controller_container);
        router= Conductor.attachRouter(this,container,savedInstanceState);
        if (!router.hasRootController()) {
            router.setRoot(RouterTransaction
                    .with(new ContactsListController()));
        }

    }

    @Override
    public void onBackPressed() {
        if (!router.handleBack()) {
            super.onBackPressed();
            setUpMenu();

        }

    }

    public static void setUpMenu(){
        MenuItem menuItem=menu.findItem(R.id.add);
        menuItem.setEnabled(true);
        menuItem.getIcon().setAlpha(255);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        setUpMenu();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        this.menu=menu;
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.add:
                router.pushController(RouterTransaction.with(new ContactAddController())
                        .pushChangeHandler(new HorizontalChangeHandler())
                        .popChangeHandler(new HorizontalChangeHandler()));

                item.setEnabled(false);
                menu.findItem(R.id.add).getIcon().setAlpha(0);


                break;
        }
        return true;
    }
}

