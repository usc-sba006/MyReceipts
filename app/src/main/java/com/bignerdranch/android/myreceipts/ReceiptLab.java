package com.bignerdranch.android.myreceipts;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import database.ReceiptBaseHelper;
import database.ReceiptCursorWrapper;
import database.ReceiptDbSchema;

public class ReceiptLab {
    private static ReceiptLab sReceiptLab;

    private Context mContext;
    private SQLiteDatabase mDatabase;

    public static ReceiptLab get(Context context) {
        if (sReceiptLab == null) {
            sReceiptLab = new ReceiptLab(context);
        }
        return sReceiptLab;
    }

    private ReceiptLab(Context context) {
        mContext = context.getApplicationContext();
        mDatabase = new ReceiptBaseHelper(mContext)
                .getWritableDatabase();
    }

    public void addReceipt(Receipt c) {
        ContentValues values = getContentValues(c);
        mDatabase.insert(ReceiptDbSchema.ReceiptTable.NAME, null, values);
    }

    public List<Receipt> getReceipts() {
        List<Receipt> receipts = new ArrayList<>();
        ReceiptCursorWrapper cursor = queryReceipts(null, null);
        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                receipts.add(cursor.getReceipt());
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }
        return receipts;
    }

    public Receipt getReceipt(UUID id) {
        ReceiptCursorWrapper cursor = queryReceipts(
                ReceiptDbSchema.ReceiptTable.Cols.UUID + " = ?",
                new String[] { id.toString() }
        );
        try {
            if (cursor.getCount() == 0) {
                return null;
            }
            cursor.moveToFirst();
            return cursor.getReceipt();
        } finally {
            cursor.close();
        }
    }

    public File getPhotoFile(Receipt receipt) {
        File filesDir = mContext.getFilesDir();
        return new File(filesDir, receipt.getPhotoFilename());
    }

    public void updateReceipt(Receipt receipt) {
        String uuidString = receipt.getId().toString();
        ContentValues values = getContentValues(receipt);
        mDatabase.update(ReceiptDbSchema.ReceiptTable.NAME, values,
                ReceiptDbSchema.ReceiptTable.Cols.UUID + " = ?",
                new String[] { uuidString });
    }

    private ReceiptCursorWrapper queryReceipts(String whereClause, String[] whereArgs) {
        Cursor cursor = mDatabase.query(
                ReceiptDbSchema.ReceiptTable.NAME,
                null, // columns - null selects all columns
                whereClause,
                whereArgs,
                null, // groupBy
                null, // having
                null // orderBy
        );
        return new ReceiptCursorWrapper(cursor);
    }

    private static ContentValues getContentValues(Receipt receipt) {
        ContentValues values = new ContentValues();
        values.put(ReceiptDbSchema.ReceiptTable.Cols.UUID, receipt.getId().toString());
        values.put(ReceiptDbSchema.ReceiptTable.Cols.TITLE, receipt.getTitle());
        values.put(ReceiptDbSchema.ReceiptTable.Cols.SHOP, receipt.getShop()); //edit
        values.put(ReceiptDbSchema.ReceiptTable.Cols.COMMENT, receipt.getComment()); //edit2
        values.put(ReceiptDbSchema.ReceiptTable.Cols.DATE, receipt.getDate().getTime());
        values.put(ReceiptDbSchema.ReceiptTable.Cols.LATITUDE, receipt.getLatitude());
        values.put(ReceiptDbSchema.ReceiptTable.Cols.LONGITUDE, receipt.getLongitude());

        return values;
    }
}
