package com.bignerdranch.android.myreceipts;

//import

/**
 * Class: ReceiptListActivity
 *
 * Created by Samuel Allan (SBA006)
 *
 * ICT311 (Mobile App Development)
 * Task 2
 * Semester 2, 2018
 * University of the Sunshine Coast
 */

import android.support.v4.app.Fragment;

public class ReceiptListActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new ReceiptListFragment();
    }
}
