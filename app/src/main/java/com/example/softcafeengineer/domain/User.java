package com.example.softcafeengineer.domain;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class User implements Parcelable
{
    private String username;
    private String password;
    private Cafeteria cafe;

    // Default constructor
    public User() { }

    public User(String user, String pass) {
        this.username = user;
        this.password = pass;
    }

    public void setUsername(String user) { this.username = user; }
    public String getUsername() { return this.username; }

    public void setPassword(String pass) { this.password = pass; }
    public String getPassword(){ return this.password; }

    public void setCafe(Cafeteria cafe) { this.cafe = cafe; }
    public Cafeteria getCafe() { return this.cafe; }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(this.username);
        dest.writeString(this.password);
    }

    public static final Parcelable.Creator<User> CREATOR = new Parcelable.Creator<User>() {
        public User createFromParcel(Parcel in) {
            return new User(in);
        }
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    private User(Parcel in) {
        this.username = in.readString();
        this.password = in.readString();
    }
}
