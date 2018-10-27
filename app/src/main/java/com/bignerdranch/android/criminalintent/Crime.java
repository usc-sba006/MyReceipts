package com.bignerdranch.android.criminalintent;

import java.util.Date;
import java.util.UUID;

public class Crime {
    private UUID mId;
    private String mTitle;
    private String mShop; //edit
    private Date mDate;

    public UUID getId() {
        return mId;
    }

    public Crime() {
        this(UUID.randomUUID());
    }

    public Crime(UUID id) {
        mId = id;
        mDate = new Date();
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public String getShop() { return mShop; } //edit

    public void setShop(String mShop) { this.mShop = mShop; } //edit

    public Date getDate() { return mDate; }

    public void setDate(Date mDate) {
        this.mDate = mDate;
    }

    public String getPhotoFilename() {
        return "IMG_" + getId().toString() + ".jpg";
    }
}
