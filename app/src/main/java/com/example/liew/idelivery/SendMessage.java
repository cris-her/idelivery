package com.example.liew.idelivery;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.liew.idelivery.Common.Common;
import com.example.liew.idelivery.Model.DataMessage;
import com.example.liew.idelivery.Model.MyResponse;
import com.example.liew.idelivery.Service.APIService;
import com.rengwuxian.materialedittext.MaterialEditText;

import java.util.HashMap;
import java.util.Map;

import info.hoang8f.widget.FButton;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SendMessage extends AppCompatActivity {

    MaterialEditText edtTitle,edtMessage;
    FButton btnSend;

    APIService mService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_message);

        mService = Common.getFCMService();

        edtMessage = (MaterialEditText)findViewById(R.id.edtMessage);
        edtTitle = (MaterialEditText)findViewById(R.id.edtTitle);

        btnSend = (FButton) findViewById(R.id.btnSend);

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (TextUtils.isEmpty(edtTitle.getText().toString()))   {
                    Toast.makeText(SendMessage.this, "Enter Title of Message!!!", Toast.LENGTH_SHORT).show();
                }
                else if (TextUtils.isEmpty(edtMessage.getText().toString()))   {
                    Toast.makeText(SendMessage.this, "Enter Message to Send!!!", Toast.LENGTH_SHORT).show();
                }
                else {

//                    Notification notification = new Notification(edtTitle.getText().toString(), edtMessage.getText().toString());
//
//                    Sender toTopic = new Sender();
//                    toTopic.to = new StringBuilder("/topics/").append(Common.topicName).toString();
//                    toTopic.notification = notification;

                    Map<String, String> dataSend = new HashMap<>();
                    dataSend.put("title",edtTitle.getText().toString());
                    dataSend.put("message",edtMessage.getText().toString());
                    DataMessage dataMessage = new DataMessage(Common.topicName,dataSend);

                    mService.sendNotification(dataMessage).enqueue(new Callback<MyResponse>() {
                        @Override
                        public void onResponse(Call<MyResponse> call, Response<MyResponse> response) {
                            if (response.isSuccessful()) {
                                Toast.makeText(SendMessage.this, "Message Sent!!!", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<MyResponse> call, Throwable t) {
                            Toast.makeText(SendMessage.this, "" + t.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
    }
}
