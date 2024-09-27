package com.example.project1_mobilecomputing.classes;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;


public class MyDatabaseHelper extends SQLiteOpenHelper {

    private Context context;
    private static final String DATABASE_NAME = "Symptom.db";
    private static final int DATABASE_VERSION = 7;
    private static final String TABLE_NAME = "Symptom_details";

    private static final String COLUMN_ID = "ID";
    private static final String Fever = "fever";
    private static final String Headache = "headache";
    private static final String NAUSEA = "nausea";
    private static final String DIARRHEA = "diarrhea";
    private static final String SORE_THROAT = "sore_throat";
    private static final String MUSCLE_PAIN = "muscle_pain";
    private static final String LOSS_SMELL_TASTE = "loss_smell_taste";
    private static final String COUGH = "cough";
    private static final String SHORTNESS_BREATH = "shortness_breath";
    private static final String FATIGUE = "fatigue";
    private static final String HEART_RATE = "Hrate";
    private static final String RES_RATE = "ResRate";


    public MyDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String query = "CREATE TABLE " + TABLE_NAME + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                Fever + " TEXT, " +
                Headache + " TEXT, " +
                NAUSEA + " TEXT, " +
                DIARRHEA + " TEXT, " +
                SORE_THROAT + " TEXT, " +
                MUSCLE_PAIN + " TEXT, " +
                LOSS_SMELL_TASTE + " TEXT, " +
                COUGH + " TEXT, " +
                SHORTNESS_BREATH + " TEXT, " +
                FATIGUE + " TEXT, " +
                HEART_RATE + " INTEGER, " +
                RES_RATE + " INTEGER);";

        db.execSQL(query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

        db.execSQL(" DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);

    }

    public void adddata(String fever, String headache, String nausea, String diarrhea, String sore_throat, String muscle_pain, String loss_smell_taste, String cough, String shortness_breath, String fatigue, Integer heartRate, Integer resRate) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(Fever, fever);
        cv.put(Headache, headache);
        cv.put(NAUSEA, nausea);
        cv.put(DIARRHEA, diarrhea);
        cv.put(SORE_THROAT, sore_throat);
        cv.put(MUSCLE_PAIN, muscle_pain);
        cv.put(LOSS_SMELL_TASTE, loss_smell_taste);
        cv.put(COUGH, cough);
        cv.put(SHORTNESS_BREATH, shortness_breath);
        cv.put(FATIGUE, fatigue);
        cv.put(HEART_RATE, heartRate);
        cv.put(RES_RATE, resRate);

        long result = db.insert(TABLE_NAME, null, cv);
        Log.d("TAG", "adddata");
        Log.d("TAG", "fever: $fever");
        Log.d("TAG", "headache: $headache");
        Log.d("TAG", "nausea: $nausea");
        Log.d("TAG", "diarrhea: $diarrhea");
        Log.d("TAG", "sore_throat: $sore_throat");
        Log.d("TAG", "muscle_pain: $muscle_pain");
        Log.d("TAG", "loss_smell_taste: $loss_smell_taste");
        Log.d("TAG", "cough: $cough");
        Log.d("TAG", "shortness_breath: $shortness_breath");
        Log.d("TAG", "fatigue: $fatigue");
        Log.d("TAG", "heartRate: $heartRate");
        Log.d("TAG", "resRate: $resRate");
        if (result == -1) {
            Toast.makeText(context, "FAILED TO ADD DATA", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(context, "DATA ADDED SUCCESSFULLY : ", Toast.LENGTH_LONG).show();
        }
    }

}

