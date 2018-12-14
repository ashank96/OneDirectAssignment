package com.example.aloofwillow.mycontactsapp.controllers;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bluelinelabs.conductor.Controller;
import com.example.aloofwillow.mycontactsapp.R;
import com.example.aloofwillow.mycontactsapp.model.ContactsModel;
import com.example.aloofwillow.mycontactsapp.presenter.ContactsDetailPresenter;

@SuppressLint("ValidController")
public class ContactsEditChildController extends Controller {

    ContactsDetailPresenter presenter;
    ContactsModel contactsModel;
    ContactsHostController parentController;
    public ContactsEditChildController(@Nullable Bundle args) {
        super(args);
    }
    public ContactsEditChildController(ContactsModel contact, ContactsHostController contactsHostController){
        this.contactsModel=contact;
        presenter=new ContactsDetailPresenter(contactsHostController);
    }



    @NonNull
    @Override
    protected View onCreateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container) {
        View view=inflater.inflate(R.layout.contacts_details, container, false);
        presenter.setView(view,"edit");
        presenter.setController(this);
        presenter.setContactDetails(contactsModel);
        return view;
    }
}
