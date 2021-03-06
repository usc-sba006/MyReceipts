package database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import database.ReceiptDbSchema.ReceiptTable;

/**
 * Class: ReceiptBaseHelper
 *
 * Created by Samuel Allan (SBA006)
 *
 * ICT311 (Mobile App Development)
 * Task 2
 * Semester 2, 2018
 * University of the Sunshine Coast
 */

public class ReceiptBaseHelper extends SQLiteOpenHelper {
    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "receiptBase.db";

    public ReceiptBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + ReceiptTable.NAME + "(" +
                " _id integer primary key autoincrement, " +
                ReceiptTable.Cols.UUID + ", " +
                ReceiptTable.Cols.TITLE + ", " +
                ReceiptTable.Cols.SHOP + ", " + //edit
                ReceiptTable.Cols.COMMENT + ", " + //edit2
                ReceiptTable.Cols.DATE + ", " +
                ReceiptTable.Cols.LATITUDE + ", " +
                ReceiptTable.Cols.LONGITUDE +
                ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}
