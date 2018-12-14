package com.example.aloofwillow.mycontactsapp.presenter;

import android.view.View;

import com.example.aloofwillow.mycontactsapp.controllers.ContactAddController;
import com.example.aloofwillow.mycontactsapp.view.ContactsAddView;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class ContactAddPresenterTest {

    @Test
    public void validate() {

        ContactAddPresenter contactAddPresenter=new ContactAddPresenter();
        String err=contactAddPresenter.validate("wer","se","7204589985","ash@gmail.com");
        Assert.assertTrue(err==ContactAddPresenter.FIRST_OR_LAST_NAME_ERROR);

        err=contactAddPresenter.validate("werti","sesdd","5204589985","ash@gmail.com");
        Assert.assertTrue(err==ContactAddPresenter.INVALID_PHONE_ERROR);

        err=contactAddPresenter.validate("werti","sesdd","7204589985","ash");
        Assert.assertTrue(err==ContactAddPresenter.INVALID_EMAIL_ERROR);

        err=contactAddPresenter.validate("werti","werti","7204589985","ash");
        Assert.assertTrue(err==ContactAddPresenter.FIRST_AND_LAST_NAME_EQUAL_ERROR);
    }

}