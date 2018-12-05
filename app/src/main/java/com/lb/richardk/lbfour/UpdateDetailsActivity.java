package com.lb.richardk.lbfour;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class UpdateDetailsActivity extends AppCompatActivity {

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("User");

    public EditText fName;
    public EditText lName;
    public EditText dob;
    public EditText email;
    public EditText mob;

    public String uid;

    public String fn;
    public String ln;
    public String db;
    public String em;
    public String mb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_details);

        fName = (EditText) findViewById(R.id.FirstName);
        lName = (EditText) findViewById(R.id.LastName);
        dob = (EditText) findViewById(R.id.DateOfBirth);
        email = (EditText) findViewById(R.id.Email);
        mob = (EditText) findViewById(R.id.Mobile);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        uid = user.getUid();

        Query query = myRef.child(uid);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (DataSnapshot user : dataSnapshot.getChildren()) {

                        String key = user.getKey();
                        String value = user.getValue().toString();
                        Log.d("success", "key = " + key);
                        Log.d("success", "value = " + value);


                        if(key == "firstName")
                        {
                            fName.setText(value, TextView.BufferType.EDITABLE);
                        }
                        else if(key == "lastName")
                        {
                            lName.setText(value);
                        }
                        else if(key == "number")
                        {
                            mob.setText(value);
                        }
                        else if(key == "dateOfBirth")
                        {
                            dob.setText(value);
                        }
                        else if(key == "email")
                        {
                            email.setText(value);
                        }
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        Button updateBtn = (Button)findViewById(R.id.Updatebutton);
        updateBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                fn = fName.getText().toString();
                ln = lName.getText().toString();
                db = dob.getText().toString();
                em = email.getText().toString();
                mb = mob.getText().toString();

                NewUser user = new NewUser(fn, ln, db, em, mb);

                myRef.child(uid).setValue(user);

                Intent startIntent = new Intent (getApplicationContext(), HomeActivity.class);
                startActivity(startIntent);
            }
        });
    }
}
