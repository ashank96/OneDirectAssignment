package com.example.aloofwillow.mycontactsapp.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.aloofwillow.mycontactsapp.presenter.ContactsPresenter;
import com.example.aloofwillow.mycontactsapp.view.ContactsListViewHolder;

public class ContactsListAdapter extends RecyclerView.Adapter<ContactsListViewHolder> {

    ContactsPresenter presenter;

    public ContactsListAdapter(ContactsPresenter presenter) {
        this.presenter=presenter;
    }

    @Override
    public ContactsListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(viewType,parent,false);
        return new ContactsListViewHolder(view,presenter);
    }

    @Override
    public void onBindViewHolder(ContactsListViewHolder holder, int position) {
        presenter.bindContactsRowViewAtPosition(holder,position);
    }


    @Override
    public int getItemCount() {
        return presenter.getContactsRowCount();
    }

    @Override
    public int getItemViewType(int position) {
        return presenter.getContactsViewLayout();
    }

}
