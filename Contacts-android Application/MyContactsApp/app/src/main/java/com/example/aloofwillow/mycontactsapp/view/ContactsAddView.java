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
import com.example.aloofwillow.mycontactsapp.presenter.ContactAddPresenter;

public class ContactsAddView implements View.OnClickListener, View.OnTouchListener {
    View view;
    ContactAddPresenter contactAddPresenter;
    EditText firstName,lastName,phone,email;
    Button saveButton;
    LinearLayout linearLayout;
    public ContactsAddView(View view, ContactAddPresenter contactAddPresenter) {
        this.view=view;
        this.contactAddPresenter=contactAddPresenter;
        init();
    }
    private void init(){
        firstName=view.findViewById(R.id.contactFirstName);
        lastName=view.findViewById(R.id.contactLastName);
        phone=view.findViewById(R.id.contactPhoneNumber);
        email=view.findViewById(R.id.contactEmailId);
        saveButton=view.findViewById(R.id.contactAddButton);
        saveButton.setOnClickListener(this);
        linearLayout=view.findViewById(R.id.addContactLayout);
        linearLayout.setOnTouchListener(this);
    }

    protected void hideKeyboard(View view)
    {
        InputMethodManager in = (InputMethodManager) view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        in.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
    }


    @Override
    public void onClick(View view) {
        if(view.getId()==saveButton.getId()) {
            String err;
            String fname=firstName.getText().toString();
            String lname=lastName.getText().toString();
            String phn=phone.getText().toString();
            String em=email.getText().toString();
            if ((err = contactAddPresenter.validate(fname,lname,phn,em)) != null)
                Toast.makeText(view.getContext(), err, Toast.LENGTH_SHORT).show();
            else
                contactAddPresenter.saveContactDetails(fname,lname,phn,em);
            contactAddPresenter.exitView();
        }
    }

    public void showToast(String message) {
        Toast.makeText(view.getContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        hideKeyboard(view);
        return false;
    }
}
