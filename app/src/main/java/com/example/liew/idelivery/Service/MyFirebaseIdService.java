package com.example.liew.idelivery.Service;

import com.example.liew.idelivery.Common.Common;
import com.example.liew.idelivery.Model.Token;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessagingService;

;


public class MyFirebaseIdService extends FirebaseMessagingService {

    @Override
    public void onNewToken(String token) {
        super.onNewToken(token);
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        if (Common.currentUser != null) {
            updateToServer(refreshedToken);
        }
    }

    private void updateToServer(String refreshedToken) {
            FirebaseDatabase db = FirebaseDatabase.getInstance();
            DatabaseReference tokens = db.getReference("Tokens");
            Token token = new Token(refreshedToken, true);  //true bcz : token sent from server side
            tokens.child(Common.currentUser.getPhone()).setValue(token);
    }

}