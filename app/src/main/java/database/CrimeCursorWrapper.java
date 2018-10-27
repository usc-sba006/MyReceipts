package database;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.bignerdranch.android.myreceipts.Crime;

import java.util.Date;
import java.util.UUID;

public class CrimeCursorWrapper extends CursorWrapper {
    public CrimeCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public Crime getCrime() {
        String uuidString = getString(getColumnIndex(CrimeDbSchema.CrimeTable.Cols.UUID));
        String title = getString(getColumnIndex(CrimeDbSchema.CrimeTable.Cols.TITLE));
        String shop = getString(getColumnIndex(CrimeDbSchema.CrimeTable.Cols.SHOP)); //edit
        long date = getLong(getColumnIndex(CrimeDbSchema.CrimeTable.Cols.DATE));

        Crime crime = new Crime(UUID.fromString(uuidString));
        crime.setTitle(title);
        crime.setShop(shop);
        crime.setDate(new Date(date));

        return crime;
    }
}
