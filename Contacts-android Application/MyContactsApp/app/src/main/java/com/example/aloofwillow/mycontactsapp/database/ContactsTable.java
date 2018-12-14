 package com.example.aloofwillow.mycontactsapp.database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.aloofwillow.mycontactsapp.model.ContactsModel;

import java.util.ArrayList;
import java.util.List;

 public class ContactsTable {

     public void insertContact(SQLiteDatabase writableDatabase, ContactsModel contactsModel){
         ContentValues contentValues = new ContentValues();
         contentValues.put("id",contactsModel.getId());
         contentValues.put("firstName", contactsModel.getFirstName());
         contentValues.put("phone", contactsModel.getPhone());
         contentValues.put("lastName", contactsModel.getLastName());
         contentValues.put("email", contactsModel.getEmail());
         writableDatabase.insert("contacts", null, contentValues);
     }
    public List<ContactsModel> getContacts(SQLiteDatabase db){
        ArrayList<ContactsModel> contacts = new ArrayList<>();
        Cursor res =  db.rawQuery( "select * from contacts order by firstName,lastName asc ", null );
        res.moveToFirst();
        while(res.isAfterLast() == false){
            contacts.add(new ContactsModel(res.getInt(res.getColumnIndex("id")),
                    res.getString(res.getColumnIndex("firstName")),
                    res.getString(res.getColumnIndex("lastName")),
                    res.getString(res.getColumnIndex("phone")),
                    res.getString(res.getColumnIndex("email"))));
            res.moveToNext();
        }
        return contacts;
    }

     public void createTable(SQLiteDatabase sqLiteDatabase) {
         sqLiteDatabase.execSQL(
                 "create table contacts " +
                         "(id integer unique, firstName text,lastName text,phone text,email text)");
     }

     public void dropTable(SQLiteDatabase sqLiteDatabase) {
         sqLiteDatabase.execSQL("DROP TABLE IF EXISTS contacts");
     }

     public void updateContact(SQLiteDatabase writableDatabase, ContactsModel contactsModel) {
         ContentValues contentValues = new ContentValues();
         contentValues.put("firstName", contactsModel.getFirstName());
         contentValues.put("phone", contactsModel.getPhone());
         contentValues.put("lastName", contactsModel.getLastName());
         contentValues.put("email", contactsModel.getEmail());
         writableDatabase.update("contacts",contentValues,"id=?",new String[] { Integer.toString(contactsModel.getId())});

     }

     public void deleteContact(SQLiteDatabase sqLiteDatabase,Integer id) {
          sqLiteDatabase.delete("contacts",
                 "id = ? ",
                 new String[] { Integer.toString(id) });
     }

     public void delete(SQLiteDatabase writableDatabase) {
         writableDatabase.execSQL("Delete from contacts");
     }

     public int getRowsCount(SQLiteDatabase readableDatabase) {
         String countQuery = "SELECT  * FROM contacts";
         Cursor cursor = readableDatabase.rawQuery(countQuery, null);
         int count = cursor.getCount();
         cursor.close();
         return count;
     }

     public int getLastId(SQLiteDatabase readableDatabase) {
         String countQuery = "SELECT  * FROM contacts order by firstName,lastName asc";
         Cursor cursor = readableDatabase.rawQuery(countQuery, null);
         int lastId=0;

         if(cursor.getCount()==0)
             return -1;
         cursor.moveToLast();
         lastId = cursor.getInt(cursor.getColumnIndex("id"));

         cursor.close();
         return lastId;
     }
 }
