package database;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.bignerdranch.android.myreceipts.Receipt;

import java.util.Date;
import java.util.UUID;

/**
 * Class: ReceiptCursorWrapper
 *
 * Created by Samuel Allan (SBA006)
 *
 * ICT311 (Mobile App Development)
 * Task 2
 * Semester 2, 2018
 * University of the Sunshine Coast
 */

public class ReceiptCursorWrapper extends CursorWrapper {
    public ReceiptCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public Receipt getReceipt() {
        String uuidString = getString(getColumnIndex(ReceiptDbSchema.ReceiptTable.Cols.UUID));
        String title = getString(getColumnIndex(ReceiptDbSchema.ReceiptTable.Cols.TITLE));
        String shop = getString(getColumnIndex(ReceiptDbSchema.ReceiptTable.Cols.SHOP)); //edit
        String comment = getString(getColumnIndex(ReceiptDbSchema.ReceiptTable.Cols.COMMENT)); //edit2
        long date = getLong(getColumnIndex(ReceiptDbSchema.ReceiptTable.Cols.DATE));
        double latitude = getLong(getColumnIndex(ReceiptDbSchema.ReceiptTable.Cols.LATITUDE));
        double longitude = getLong(getColumnIndex(ReceiptDbSchema.ReceiptTable.Cols.LONGITUDE));

        Receipt receipt = new Receipt(UUID.fromString(uuidString));
        receipt.setTitle(title);
        receipt.setShop(shop); //edit
        receipt.setComment(comment); //edit2
        receipt.setDate(new Date(date));
        receipt.setLatitude(latitude);
        receipt.setLongitude(longitude);

        return receipt;
    }
}
