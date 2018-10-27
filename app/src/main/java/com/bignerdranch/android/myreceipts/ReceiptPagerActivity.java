package com.bignerdranch.android.myreceipts;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;

import java.util.List;
import java.util.UUID;

public class ReceiptPagerActivity extends AppCompatActivity {
    private static final String EXTRA_RECEIPT_ID =
            "com.bignerdranch.android.myreceipts.receipt_id";
    private static final int REQUEST_ERROR = 0;

    private ViewPager mViewPager;
    private List<Receipt> mReceipts;

    public static Intent newIntent(Context packageContext, UUID receiptId) {
        Intent intent = new Intent(packageContext, ReceiptPagerActivity.class);
        intent.putExtra(EXTRA_RECEIPT_ID, receiptId);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receipt_pager);

        UUID receiptId = (UUID) getIntent()
                .getSerializableExtra(EXTRA_RECEIPT_ID);

        mViewPager = (ViewPager) findViewById(R.id.receipt_view_pager);
        mReceipts = ReceiptLab.get(this).getReceipts();
        FragmentManager fragmentManager = getSupportFragmentManager();
        mViewPager.setAdapter(new FragmentStatePagerAdapter(fragmentManager) {
            @Override
            public Fragment getItem(int position) {
                Receipt receipt = mReceipts.get(position);
                return ReceiptFragment.newInstance(receipt.getId());
            }
            @Override
            public int getCount() {
                return mReceipts.size();
            }
        });

        for (int i = 0; i < mReceipts.size(); i++) {
            if (mReceipts.get(i).getId().equals(receiptId)) {
                mViewPager.setCurrentItem(i);
                break;
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        GoogleApiAvailability apiAvailability = GoogleApiAvailability.getInstance();
        int errorCode = apiAvailability.isGooglePlayServicesAvailable(this);

        if (errorCode != ConnectionResult.SUCCESS) {
            Dialog errorDialog = apiAvailability.getErrorDialog(this, errorCode, REQUEST_ERROR,
                    new DialogInterface.OnCancelListener() {
                        @Override
                        public void onCancel(DialogInterface dialog) {
                            finish();
                        }
                    });
        }
    }
}
