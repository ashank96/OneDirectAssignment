package com.example.aloofwillow.mycontactsapp.model;

import android.os.Parcel;
import android.os.Parcelable;

public class ContactsModel implements Parcelable {
    public static int counter=0;
    private  int id;
    private String firstName;
    private String lastName;
    private String phone;
    private String email;

    public ContactsModel(String firstName, String lastName, String phone, String email) {
        setId(counter++);
        setFirstName(firstName);
        setLastName(lastName);
        setPhone(phone);
        setEmail(email);
    }
    public ContactsModel(int id,String firstName, String lastName, String phone, String email) {
        setId(id);
        setFirstName(firstName);
        setLastName(lastName);
        setPhone(phone);
        setEmail(email);
    }



    public int getId() {
        return id;
    }

    public void setId(int count) {
        this.id = count;
    }



    public ContactsModel(Parcel in){
        setFirstName(in.readString());
        setLastName(in.readString());
        setPhone(in.readString());
        setEmail(in.readString());
    }

    public static final Creator<ContactsModel> CREATOR = new Creator<ContactsModel>() {
        @Override
        public ContactsModel createFromParcel(Parcel in) {
            return new ContactsModel(in);
        }

        @Override
        public ContactsModel[] newArray(int size) {
            return new ContactsModel[size];
        }
    };

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(firstName);
        parcel.writeString(lastName);
        parcel.writeString(phone);
        parcel.writeString(email);


    }
}
