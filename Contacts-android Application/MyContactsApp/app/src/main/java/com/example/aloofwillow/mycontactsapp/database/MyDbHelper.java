package com.example.aloofwillow.mycontactsapp.database;

import android.content.Context;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.aloofwillow.mycontactsapp.MainActivity;
import com.example.aloofwillow.mycontactsapp.model.ContactsModel;

import java.util.List;

public class MyDbHelper extends SQLiteOpenHelper {
    ContactsTable contactsTable;

    public MyDbHelper(Context context) {
        super(context, "MyDB" , null, 1);
        contactsTable=new ContactsTable();
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        contactsTable.createTable(sqLiteDatabase);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        contactsTable.dropTable(sqLiteDatabase);
        onCreate(sqLiteDatabase);
    }

    public Observable<List<ContactsModel>> getAllContacts(){
        return Observable.just(contactsTable.getContacts(getReadableDatabase()));
    }

    public void insertContact(Observable<ContactsModel> contactsModelObservable){
        contactsModelObservable.
                subscribe(new Observer<ContactsModel>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(ContactsModel contactsModel) {
                contactsTable.insertContact(getWritableDatabase(),contactsModel);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }

    public void updateContact(Observable<ContactsModel> contactsModelObservable){
        contactsModelObservable.
                subscribe(new Observer<ContactsModel>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(ContactsModel contactsModel) {
                contactsTable.updateContact(getWritableDatabase(),contactsModel);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }

    public void deleteContact(Observable<ContactsModel> contactsModelObservable){
        contactsModelObservable.subscribe(new Observer<ContactsModel>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(ContactsModel contactsModel) {
                    contactsTable.deleteContact(getWritableDatabase(),contactsModel.getId());
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });


    }

    public void deleteAll() {
        contactsTable.delete(getWritableDatabase());
    }

    public int getContactsCount(){
        return contactsTable.getRowsCount(getReadableDatabase());
    }

    public int getLastContactId(){
        return contactsTable.getLastId(getReadableDatabase());
    }

}
