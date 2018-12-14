package com.example.aloofwillow.mycontactsapp.view;

import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.aloofwillow.mycontactsapp.R;
import com.example.aloofwillow.mycontactsapp.controllers.ContactsDetailChildController;
import com.example.aloofwillow.mycontactsapp.model.ContactsModel;
import com.example.aloofwillow.mycontactsapp.presenter.ContactsDetailPresenter;

public class ContactsDetailView implements ContactsDetailPresenter.ContactsDetailViewInterface,View.OnClickListener, View.OnTouchListener {
    View view;
    EditText firstName,lastName,phone,email;
    Button saveButton;
    ContactsDetailPresenter contactsDetailPresenter;
    LinearLayout linearLayout;
    public ContactsDetailView(View view, String tag, ContactsDetailPresenter contactsDetailPresenter) {
        this.view=view;
        this.contactsDetailPresenter=contactsDetailPresenter;

        init(tag);
    }

    private void init(String tag) {
        firstName=view.findViewById(R.id.contactDetailFirstName);
        lastName=view.findViewById(R.id.contactDetailLastName);
        phone=view.findViewById(R.id.contactDetailPhone);
        email=view.findViewById(R.id.contactDetailEmail);
        saveButton=view.findViewById(R.id.contactUpdateButton);
        linearLayout=view.findViewById(R.id.detailContactLayout);
        linearLayout.setOnTouchListener(this);
        saveButton.setOnClickListener(this);
        if(tag.equals("detail"))
            enableOrDisable(false);
        else
            enableOrDisable(true);
    }

    private void enableOrDisable(boolean flag) {

            firstName.setEnabled(flag);
            lastName.setEnabled(flag);
            phone.setEnabled(flag);
            email.setEnabled(flag);
            saveButton.setEnabled(flag);
            if(flag==true)
                saveButton.setVisibility(View.VISIBLE);
            else
                saveButton.setVisibility(View.GONE);


    }

    @Override
    public void showContactDetails(ContactsModel contactsModel) {
        firstName.setText(contactsModel.getFirstName());
        lastName.setText(contactsModel.getLastName());
        phone.setText(contactsModel.getPhone());
        email.setText(contactsModel.getEmail());
    }

    protected void hideKeyboard(View view)
    {
        InputMethodManager in = (InputMethodManager) view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        in.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
    }


    @Override
    public void showToast(String tag) {
        Toast.makeText(view.getContext(), tag, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View view) {
        if(view.getId()==saveButton.getId()) {
            String err;
            String fname=firstName.getText().toString();
            String lname=lastName.getText().toString();
            String phn=phone.getText().toString();
            String em=email.getText().toString();
            if ((err = contactsDetailPresenter.validate(fname,lname,phn,em)) != null)
                Toast.makeText(view.getContext(), err, Toast.LENGTH_SHORT).show();
            else
                contactsDetailPresenter.updateContactDetails(fname,lname,phn,em);
            contactsDetailPresenter.exitView();
        }
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        hideKeyboard(view);
        return false;
    }
}
