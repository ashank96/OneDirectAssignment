package com.example.aloofwillow.mycontactsapp.presenter;

import android.annotation.TargetApi;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.text.Editable;
import android.util.Log;
import android.view.View;

import com.bluelinelabs.conductor.Controller;
import com.bluelinelabs.conductor.Router;
import com.bluelinelabs.conductor.RouterTransaction;
import com.example.aloofwillow.mycontactsapp.MainActivity;
import com.example.aloofwillow.mycontactsapp.controllers.ContactAddController;
import com.example.aloofwillow.mycontactsapp.controllers.ContactsListController;
import com.example.aloofwillow.mycontactsapp.database.ContactsTable;
import com.example.aloofwillow.mycontactsapp.database.MyDbHelper;
import com.example.aloofwillow.mycontactsapp.model.ContactsModel;
import com.example.aloofwillow.mycontactsapp.view.ContactsAddView;

import java.util.Comparator;
import java.util.Observable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ContactAddPresenter {
    ContactsAddView addView;
    ContactAddController contactAddController;
    MyDbHelper dbHelper;
    public static final String FIRST_OR_LAST_NAME_ERROR="First Name or Last Name have less than 3 characters\n";
    public static final String FIRST_AND_LAST_NAME_EQUAL_ERROR="First Name and Last Name should be different\n";
    public static final String INVALID_PHONE_ERROR="Invalid Phone Number\n";
    public static final String INVALID_EMAIL_ERROR="Invalid Email Address\n";


    public ContactAddPresenter(){ }

    public ContactAddPresenter(View view, ContactAddController contactAddController) {
        addView=new ContactsAddView(view,this);
        dbHelper=new MyDbHelper(view.getContext());
        ContactsModel.counter=dbHelper.getLastContactId()+1;

        Log.i("count",ContactsModel.counter+"");
        this.contactAddController=contactAddController;
    }



    public String validate(String firstName, String secondName, String phone, String email) {
        String err=null;
        final Pattern VALID_EMAIL_ADDRESS =
                Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
        Matcher matcher = VALID_EMAIL_ADDRESS.matcher(email);

        if(firstName.length()<=3||secondName.length()<=3) {
            err = FIRST_OR_LAST_NAME_ERROR;
            return err;
        }
        if(firstName.equalsIgnoreCase(secondName)) {
            err = FIRST_AND_LAST_NAME_EQUAL_ERROR;
            return err;
        }
        if(!(phone.startsWith("6")||phone.startsWith("7")||phone.startsWith("8")||phone.startsWith("9"))
                || phone.length()<10) {
            err = INVALID_PHONE_ERROR;
            return err;
        }
        if(!matcher.find()) {
            err = INVALID_EMAIL_ERROR;
            return err;
        }

        return err;
    }


    public final void saveContactDetails(String firstName, String secondName, String phone, String email) {

        if(duplicateFound(phone))
            return;

        dbHelper.insertContact(io.reactivex.Observable.just(new ContactsModel(firstName,secondName,phone,email)));
    }

    public boolean duplicateFound(String phone) {
        for(ContactsModel contact:ContactsPresenter.models){
            if(contact.getPhone().equals(phone)) {
                addView.showToast("Duplicate phone numbers not allowed");
                return true;
            }
        }
        return false;
    }

    public void exitView(){
       Router router=contactAddController.getRouter();
       router.popController(contactAddController);

    }


}