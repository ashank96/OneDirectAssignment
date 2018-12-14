package com.example.aloofwillow.mycontactsapp.view;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aloofwillow.mycontactsapp.R;
import com.example.aloofwillow.mycontactsapp.model.ContactsModel;
import com.example.aloofwillow.mycontactsapp.presenter.ContactsPresenter;
import com.example.aloofwillow.mycontactsapp.view.ContactsViewInterface;

public class ContactsListViewHolder extends RecyclerView.ViewHolder implements ContactsViewInterface, View.OnClickListener{
    private TextView firstNameView;
    private TextView lastNameView;
    private ContactsModel contact;
    private ContactsPresenter contactsPresenter;


    public ContactsListViewHolder(View itemView, ContactsPresenter presenter) {
        super(itemView);
        itemView.setOnClickListener(this);
        this.contactsPresenter=presenter;
        firstNameView=itemView.findViewById(R.id.firstName);
        lastNameView=itemView.findViewById(R.id.lastName);
    }
    @Override
    public void showRow(ContactsModel contact) {
            this.contact=contact;
            firstNameView.setText(contact.getFirstName());
            lastNameView.setText(contact.getLastName());
    }

    @Override
    public void onClick(View view) {
        contactsPresenter.changeControllerForContactDetails(contact);
    }
}
