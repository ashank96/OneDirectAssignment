package com.example.aloofwillow.mycontactsapp.controllers;

import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bluelinelabs.conductor.Controller;
import com.example.aloofwillow.mycontactsapp.R;
import com.example.aloofwillow.mycontactsapp.presenter.ContactsPresenter;


public class ContactsListController extends Controller {
    ContactsPresenter dumycontactsPresenter,contactsPresenter;
    @NonNull
    @Override
    protected View onCreateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container) {

        View view=view=inflater.inflate(R.layout.main_recyclerview, container, false);
        dumycontactsPresenter=new ContactsPresenter(view);
        Log.i("count lo ",""+dumycontactsPresenter.getContactsRowCount());
        if(dumycontactsPresenter.getContactsRowCount()==0) {
            view = inflater.inflate(R.layout.message, container, false);
            return view;
        }
        else
            view=inflater.inflate(R.layout.main_recyclerview, container, false);
        contactsPresenter=new ContactsPresenter(view,this);
        return view;
    }


}
