package com.bignerdranch.android.myreceipts;

import java.util.Date;
import java.util.UUID;

public class Receipt {
    private UUID mId;
    private String mTitle;
    private String mShop; //edit
    private String mComment; //edit2
    private Date mDate;

    public UUID getId() {
        return mId;
    }

    public Receipt() {
        this(UUID.randomUUID());
    }

    public Receipt(UUID id) {
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

    public String getComment() { return mComment; } //edit2

    public void setComment(String mComment) { this.mComment = mComment; } //edit2

    public Date getDate() { return mDate; }

    public void setDate(Date mDate) {
        this.mDate = mDate;
    }

    public String getPhotoFilename() {
        return "IMG_" + getId().toString() + ".jpg";
    }
}