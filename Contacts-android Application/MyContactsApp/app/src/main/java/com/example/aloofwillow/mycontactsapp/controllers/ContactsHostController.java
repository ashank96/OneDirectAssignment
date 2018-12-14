package com.example.aloofwillow.mycontactsapp.controllers;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.bluelinelabs.conductor.Controller;
import com.bluelinelabs.conductor.RouterTransaction;
import com.example.aloofwillow.mycontactsapp.R;
import com.example.aloofwillow.mycontactsapp.model.ContactsModel;


@SuppressLint("ValidController")
public class ContactsHostController extends Controller implements View.OnClickListener {

    ContactsModel contact;
    ViewGroup childContainer;
    View masterView;
    ContactsDetailChildController contactsDetailChildController;
    ContactsEditChildController contactsEditChildController;

    public ContactsHostController(){

    }
    public ContactsHostController(ContactsModel contact) {
        this.contact=contact;

    }

    @NonNull
    @Override
    protected View onCreateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container) {
            masterView=inflater.inflate(R.layout.contacts_edit_or_detail,container,false);
            Button editButton=masterView.findViewById(R.id.editButton);
            Button detailButton=masterView.findViewById(R.id.detailButton);
            editButton.setOnClickListener(this);
            detailButton.setOnClickListener(this);
            contactsDetailChildController=new ContactsDetailChildController(contact,this);
            contactsEditChildController=new ContactsEditChildController(contact,this);
            childContainer=masterView.findViewById(R.id.child_controller_container);
            getChildRouter(childContainer).setRoot(RouterTransaction.with(contactsDetailChildController));
        return masterView;
    }


    @Override
    public void onClick(View view) {

        switch(view.getId()){
            case R.id.editButton:
                getChildRouter(childContainer).setRoot(RouterTransaction.with(new ContactsEditChildController(contact,this)));


                break;

            case R.id.detailButton:
                getChildRouter(childContainer).setRoot(RouterTransaction.with(new ContactsDetailChildController(contact,this)));


                break;

                default:Log.i("click","defaut");


        }
    }
}
