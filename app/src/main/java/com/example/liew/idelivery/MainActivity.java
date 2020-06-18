package com.example.liew.idelivery;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.liew.idelivery.Common.Common;
import com.example.liew.idelivery.Model.User;
/*import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;*/


public class MainActivity extends AppCompatActivity {

    Button btnSignIn, btnSignUp;
    TextView txtSlogan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //btnMainSignIn = (Button)findViewById(R.id.btnMainSignIn);
        btnSignUp = (Button)findViewById(R.id.btnSignUp);
        btnSignIn = (Button)findViewById(R.id.btnSignIn);
        txtSlogan = (TextView)findViewById(R.id.txtSlogan);

        //Paper.init(this);

        Typeface face = Typeface.createFromAsset(getAssets(),"fonts/NABILA.TTF");
        txtSlogan.setTypeface(face);

        /*btnMainSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signInIntent = new Intent(MainActivity.this,SignIn.class);
                startActivity(signInIntent);
            }
        });*/

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        //check remember
        /*String user = Paper.book().read(Common.USER_KEY);
        String pwd = Paper.book().read(Common.PWD_KEY);

        if (user != null && pwd != null)    {
            if (!user.isEmpty() && !pwd.isEmpty())
                login(user,pwd);
        }*/
    }

    /*private void login(final String phone, final String pwd) {
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference table_user = database.getReference("User");

        //add progressBar
        final ProgressDialog mDialog = new ProgressDialog(MainActivity.this);
        mDialog.setTitle("STAFF LOG-IN");
        mDialog.setMessage("Please wait! while we check your credential!!");
        mDialog.setCanceledOnTouchOutside(false);
        mDialog.show();

        table_user.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                table_user.addValueEventListener(new ValueEventListener() {

                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        //check if user doesn't exist in database
                        if (dataSnapshot.child(phone).exists()){
                            //get user information
                            mDialog.dismiss();
                            User user = dataSnapshot.child(phone).getValue(User.class);
                            user.setPhone(phone);//set phone

                            if (user.getPassword().equals(pwd)) {
                                mDialog.dismiss();
                                Toast.makeText(MainActivity.this, "Welcome! Sign In Successful!!", Toast.LENGTH_SHORT).show();

                                Intent homeIntent = new Intent(MainActivity.this, Home.class);
                                Common.currentUser = user;
                                startActivity(homeIntent);
                                finish();
                            }
                            else {
                                mDialog.dismiss();
                                Toast.makeText(MainActivity.this, "Wrong Password!!!", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else {
                            mDialog.dismiss();
                            Toast.makeText(MainActivity.this, "Not Registered Yet! Kindly Register", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }*/
}
