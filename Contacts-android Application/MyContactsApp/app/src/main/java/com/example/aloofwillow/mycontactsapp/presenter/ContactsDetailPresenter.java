package com.example.aloofwillow.mycontactsapp.presenter;

import android.util.Log;
import android.view.View;

import com.bluelinelabs.conductor.Controller;
import com.bluelinelabs.conductor.RouterTransaction;
import com.example.aloofwillow.mycontactsapp.controllers.ContactsDetailChildController;
import com.example.aloofwillow.mycontactsapp.controllers.ContactsHostController;
import com.example.aloofwillow.mycontactsapp.database.MyDbHelper;
import com.example.aloofwillow.mycontactsapp.model.ContactsModel;
import com.example.aloofwillow.mycontactsapp.view.ContactsDetailView;

import java.util.Comparator;

import io.reactivex.Observable;

public class ContactsDetailPresenter extends ContactAddPresenter {
    ContactsDetailView contactsDetailView;
    Controller controller;
    ContactsModel contactsModel;
    View view;
    MyDbHelper dbHelper;
    ContactsHostController parentController;


    public ContactsDetailPresenter(ContactsHostController contactsHostController){ super();  parentController=contactsHostController; }

    public void setContactDetails(ContactsModel contactsModel) {
        setContactsModel(contactsModel);
        contactsDetailView.showContactDetails(contactsModel);
    }

    public void setView(View view, String tag) {
        this.view = view;
        dbHelper=new MyDbHelper(view.getContext());
        contactsDetailView=new ContactsDetailView(view,tag,this);
    }

    @Override
    public String validate(String firstName, String secondName, String phone, String email) {
        return super.validate(firstName, secondName, phone, email);

    }

    public final void updateContactDetails(String firstName, String secondName, String phone, String email) {
        int index=getIndexofContact(contactsModel.getPhone());
        if(duplicateFound(phone,index)) {
            return;
        }
        contactsModel=new ContactsModel(contactsModel.getId(),firstName,secondName,phone,email);
        dbHelper.updateContact(Observable.just(contactsModel));
        exitView();
    }

    private int getIndexofContact(String phone) {
        for(int i =0;i<ContactsPresenter.models.size();i++){
            if(ContactsPresenter.models.get(i).getPhone().equals(phone)) {
                return i;
            }
        }
        return 0;
    }

    private boolean duplicateFound(String phone,int index) {
        for(int i =0;i<ContactsPresenter.models.size();i++){
            if(ContactsPresenter.models.get(i).getPhone().equals(phone) && index!=i ) {
                contactsDetailView.showToast("Duplicate phone numbers not allowed");
                return true;
            }
        }
        return false;
    }
    public void exitView(){
        parentController.getRouter().popController(parentController);
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

    public Controller getController() {
        return controller;
    }

    public void setContactsModel(ContactsModel contactsModel) {
        this.contactsModel = contactsModel;
    }

    public interface ContactsDetailViewInterface {
        public void showContactDetails(ContactsModel contactsModel);
        public void showToast(String tag);
    }

}
