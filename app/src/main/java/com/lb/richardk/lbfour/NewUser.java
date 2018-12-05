package com.lb.richardk.lbfour;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class NewUser {

    public String firstName;
    public String lastName;
    public String number;
    public String dateOfBirth;
    public String email;

    public NewUser() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public NewUser(String fName, String lName, String num, String dob, String email) {
        this.firstName = fName;
        this.lastName = lName;
        this.number = num;
        this.dateOfBirth = dob;
        this.email = email;
    }

}