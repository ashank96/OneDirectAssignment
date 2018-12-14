package com.example.aloofwillow.mycontactsapp.presenter;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.View;

import com.bluelinelabs.conductor.Controller;
import com.bluelinelabs.conductor.RouterTransaction;
import com.bluelinelabs.conductor.changehandler.FadeChangeHandler;
import com.example.aloofwillow.mycontactsapp.adapters.ContactsListAdapter;
import com.example.aloofwillow.mycontactsapp.controllers.ContactsHostController;
import com.example.aloofwillow.mycontactsapp.controllers.ContactsListController;
import com.example.aloofwillow.mycontactsapp.SwipeCallback;
import com.example.aloofwillow.mycontactsapp.database.MyDbHelper;
import com.example.aloofwillow.mycontactsapp.view.ContactsViewInterface;
import com.example.aloofwillow.mycontactsapp.R;
import com.example.aloofwillow.mycontactsapp.model.ContactsModel;

import java.util.ArrayList;
import java.util.List;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;


public class ContactsPresenter{
    static List<ContactsModel> models;
    ContactsListController contactsListController;
    View contactsListView;
    static ContactsListAdapter contactsAdapter;
    RecyclerView recyclerView;
    SwipeCallback swipeCallback;
    static MyDbHelper dbHelper;

    public ContactsPresenter() {

    }

    public ContactsPresenter(View view, Controller controller) {
        setContactsListView(view);
        this.contactsListController=(ContactsListController) controller;
        dbHelper=new MyDbHelper(view.getContext());
        generateModels();
        contactsAdapter=new ContactsListAdapter(this);
        setRecyclerViewContactsAndAdapter();
        enableSwipeAndDelete();

    }

    public ContactsPresenter(View view) {
        dbHelper=new MyDbHelper(view.getContext());
        Log.i("hh",dbHelper.getLastContactId()+"");
        generateModels();
    }

    public void changeControllerForContactDetails(ContactsModel contact){
        contactsListController.getRouter().pushController(RouterTransaction.with(new ContactsHostController(contact))
                .pushChangeHandler(new FadeChangeHandler())
                .popChangeHandler(new FadeChangeHandler()));
    }


    public void setContactsListView(View contactsListView) {
        this.contactsListView = contactsListView;
    }

    public void bindContactsRowViewAtPosition(ContactsViewInterface contactsView, int position){
                contactsView.showRow(models.get(position));
    }

    public int getContactsRowCount(){
        return models.size();
    }

    private void generateModels(){
        List<ContactsModel> arrayList = new ArrayList<>();
        dbHelper.getAllContacts().
                subscribe(new Observer<List<ContactsModel>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(List<ContactsModel> contactsModels) {
                /*for(int i=0;i<contactsModels.size();i++) {
                    Log.i("contact"+i,""+contactsModels.get(i).getFirstName());
                    arrayList.add(contactsModels.get(i));
                }*/
                models=contactsModels;
            }
            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });

    }

    public int getContactsViewLayout(){
        return R.layout.contacts_row_view;
    }

    public List<ContactsModel> getContacts(){
        return models;
    }

    public void removeContact(ContactsModel contactsModel, int position) {
        models.remove(position);
        dbHelper.deleteContact(io.reactivex.Observable.just(contactsModel));
    }

    public void restoreContact(ContactsModel item, int position) {
        models.add(position, item);
        dbHelper.insertContact(io.reactivex.Observable.just(item));

    }

    private void setRecyclerViewContactsAndAdapter(){
        recyclerView=contactsListView.findViewById(R.id.simple_recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(contactsListView.getContext()));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(contactsAdapter);
    }

    private void enableSwipeAndDelete() {
        this.swipeCallback = new SwipeCallback(contactsListController.getApplicationContext()) {
            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {


                final int position = viewHolder.getAdapterPosition();
                final ContactsModel contact = getContacts().get(position);

                removeContact(contact,position);
                contactsAdapter.notifyItemRemoved(position);



                Snackbar snackbar = Snackbar
                        .make(contactsListView.findViewById(R.id.coordinatorLayout), "Item was removed from the list.", Snackbar.LENGTH_LONG);
                snackbar.setAction("UNDO", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        restoreContact(contact,position);
                        contactsAdapter.notifyItemInserted(position);
                        recyclerView.scrollToPosition(position);
                    }
                });

                snackbar.setActionTextColor(Color.YELLOW);
                snackbar.show();

            }
        };

        ItemTouchHelper itemTouchhelper = new ItemTouchHelper(swipeCallback);
        itemTouchhelper.attachToRecyclerView(recyclerView);
    }

}
