package com.example.aloofwillow.mycontactsapp.controllers;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bluelinelabs.conductor.Controller;
import com.example.aloofwillow.mycontactsapp.MainActivity;
import com.example.aloofwillow.mycontactsapp.R;
import com.example.aloofwillow.mycontactsapp.presenter.ContactAddPresenter;
import com.example.aloofwillow.mycontactsapp.presenter.ContactsPresenter;

public class ContactAddController extends Controller {
    ContactAddPresenter contactAddPresenter;
    @NonNull
    @Override
    protected View onCreateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container) {
        View view=inflater.inflate(R.layout.add_contact,container,false);
        contactAddPresenter=new ContactAddPresenter(view,this);
        return view;
    }

    @Override
    protected void onDetach(@NonNull View view) {
        super.onDetach(view);
        MainActivity.setUpMenu();

    }
}
