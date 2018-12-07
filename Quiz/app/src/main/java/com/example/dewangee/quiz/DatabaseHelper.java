package com.example.dewangee.quiz;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "quizdb.db";
    public static final String TABLE_NAME = "questions";
    public static final String C1 = "id";
    public static final String C2 = "stmt";
    public static final String C3 = "expected_ans";
    public static final String C4 = "given_ans";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
        SQLiteDatabase db = this.getWritableDatabase();
        //addQuestions();
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME +" ("+
                C1 + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                C2 + " TEXT," +
                C3 + " TEXT," +
                C4 + " TEXT )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }

    public void insertdata(String stmt, String expected_ans){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(C2, stmt);
        cv.put(C3, expected_ans );
        long res = db.insert(TABLE_NAME, null, cv);
    }

    public void update(int id, String given_ans){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(C4, given_ans);
        db.update(TABLE_NAME, cv, "ID=?",new String[]{Integer.toString(id)});

    }

    public int numberofquestions(){
        SQLiteDatabase db = this.getWritableDatabase();
        int val =0;
        Cursor cursor = db.query(TABLE_NAME, new String[]{"stmt"}, null, null, null, null, null);
        if (cursor!=null)
        {
            val = cursor.getCount();
        }
        return val;

    }

    public String getUserAnswer(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        String val ="";
        Cursor cursor = db.query(TABLE_NAME, new String[]{"given_ans"}, "ID=?", new String[]{Integer.toString(id)}, null, null, null);
        if (cursor!=null)
        {
            cursor.moveToFirst();
            val = cursor.getString(0);
        }
        return val;

    }

    public String getQuestion(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        String val ="";
        Cursor cursor = db.query(TABLE_NAME, new String[]{"stmt"}, "ID=?", new String[]{Integer.toString(id)}, null, null, null);
        if (cursor!=null)
        {
            cursor.moveToFirst();
            val = cursor.getString(0);
        }
        return val;

    }

    public void addQuestions(){
        insertdata("The Language that the computer can understand is called Machine Language.","True");
        insertdata("Magnetic Tape used random access method.","False");
        insertdata("Worms and trojan horses are easily detected and eliminated by antivirus software.","True");
        insertdata("Freeware is software that is available for use at no monetary cost.","True");
        insertdata("The hexadecimal number system contains digits from 1 - 15.","False");

        insertdata("Dot-matrix, Deskjet, Inkjet and Laser are all types of Printers.","True");
        insertdata("Whaling / Whaling attack is a kind of phishing attacks that target senior executives and other high profile to access valuable information.","True");
        insertdata("Octal number system contains digits from 0 - 7.","True");
        insertdata("TMS Word is a hardware.","False");
        insertdata("GNU / Linux is a open source operating system.","True");

        insertdata("IPv6 Internet Protocol address is represented as eight groups of four Octal digits.","False");
        insertdata("You type the body of a reply the same way you would type the body of a new message.","True");
        insertdata("You must include a subject in any mail message you compose.","False");
        insertdata("If you want to respond to the sender of a message, click the Respond button.","False");
        insertdata("CPU controls only input data of computer.","False");

        insertdata("CPU stands for Central Performance Unit.","False");
        insertdata("When you reply to a message, you need to enter the text in the Subject: field.","False");
        insertdata("There is only one way to print a message.","False");
        insertdata("You cannot preview a message before you print it.","False");
        insertdata("You can only print one copy of a selected message.","False");

        insertdata("When you print a message, it is automatically deleted from your Inbox.","False");
        insertdata("You cannot edit Contact forms.","False");
        insertdata("You need to delete a contact and create a new one to change contact information.","False");
        insertdata("You should always open and attachment before saving it.","False");
        insertdata("You must complete all fields in the Contact form before you can save the contact.","False");

        insertdata("You can store Web-based e-mail messages in online folders.","True");
        insertdata("You can delete e-mails from a Web-based e-mail account.","True");
        insertdata("Twitter is an online social networking and blogging service.","False");
        insertdata("When you include multiple addresses in a message, you should separate each address with a period (.)","False");
        insertdata("You cannot format text in an e-mail message.","False");
    }



}
