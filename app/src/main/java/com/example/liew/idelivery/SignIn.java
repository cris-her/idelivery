package com.example.liew.idelivery;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.example.liew.idelivery.Common.Common;
import com.example.liew.idelivery.Model.User;
import com.rengwuxian.materialedittext.MaterialEditText;
import com.rey.material.widget.CheckBox;

import info.hoang8f.widget.FButton;
import io.paperdb.Paper;

public class SignIn extends AppCompatActivity {

    EditText edtPhone,edtPassword;
    Button btnSignIn;

    TextView txtForgotPwd;

    CheckBox ckbRemember;

    FirebaseDatabase db;
    DatabaseReference users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        edtPassword = (MaterialEditText)findViewById(R.id.edtPassword);
        edtPhone = (MaterialEditText)findViewById(R.id.edtPhone);
        btnSignIn = (FButton)findViewById(R.id.btnSignIn);
        txtForgotPwd = (TextView)findViewById(R.id.txtForgotPwd);

        ckbRemember = (CheckBox)findViewById(R.id.ckbRemember);

        //init paper
        Paper.init(this);

        db = FirebaseDatabase.getInstance();
        users = db.getReference("User");

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signInUser(edtPhone.getText().toString(),edtPassword.getText().toString());
            }
        });

        txtForgotPwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showForgotPwdDialog();
            }
        });
    }

    private void showForgotPwdDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Forgot Password");
        builder.setMessage("Enter Your Secure Code");

        LayoutInflater inflater = this.getLayoutInflater();
        View forgot_view = inflater.inflate(R.layout.forgot_password_layout,null);

        builder.setView(forgot_view);
        builder.setIcon(R.drawable.ic_security_black_24dp);

        final MaterialEditText edtPhone = (MaterialEditText)forgot_view.findViewById(R.id.edtPhone);
        final MaterialEditText edtSecureCode = (MaterialEditText)forgot_view.findViewById(R.id.txtSecureCode);

        builder.setPositiveButton("SHOW", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                users.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        User user = dataSnapshot.child(edtPhone.getText().toString()).getValue(User.class);

                        if (user.getSecureCode().equals(edtSecureCode.getText().toString())) {
                            Toast.makeText(SignIn.this, "Your Password : " + user.getPassword(), Toast.LENGTH_LONG).show();
                        }
                        else {
                            Toast.makeText(SignIn.this, "Wrong Secure-Code!!", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        });

        builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        builder.show();
    }

    private void signInUser(final String phone, String password) {

        //save user and password
        if (ckbRemember.isChecked())    {
            Paper.book().write(Common.USER_KEY,edtPhone.getText().toString());
            Paper.book().write(Common.PWD_KEY,edtPassword.getText().toString());
        }

        final ProgressDialog mDialog = new ProgressDialog(this);
        mDialog.setTitle("STAFF LOG-IN");
        mDialog.setMessage("Please wait! while we check your credential!!");
        mDialog.setCanceledOnTouchOutside(false);
        mDialog.show();

        final String localPhone = phone;
        final String localPassword = password;
        users.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.child(localPhone).exists())    {

                    mDialog.dismiss();
                    User user = dataSnapshot.child(localPhone).getValue(User.class);
                    user.setPhone(localPhone);

                    if (Boolean.parseBoolean(user.getIsStaff()))    {

                        if (user.getPassword().equals(localPassword))   {
                            mDialog.dismiss();
                            Intent loginIntent = new Intent(SignIn.this,Home.class);
                            Common.currentUser = user;
                            startActivity(loginIntent);
                            finish();
                        }
                        else    {
                            mDialog.dismiss();
                            Toast.makeText(SignIn.this, "Wrong Password!", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else    {
                        mDialog.dismiss();
                        Toast.makeText(SignIn.this, "Please Login with Staff Account!", Toast.LENGTH_SHORT).show();
                    }
                }
                else    {
                    mDialog.dismiss();
                    Toast.makeText(SignIn.this, "User Does Not Exist in Database", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
