package com.example.liew.idelivery.Common;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.text.format.DateFormat;

import com.example.liew.idelivery.Model.Request;
import com.example.liew.idelivery.Model.User;
import com.example.liew.idelivery.Remote.FCMRetrofitClient;
import com.example.liew.idelivery.Remote.IGeoCoordinates;
import com.example.liew.idelivery.Remote.RetrofitClient;
import com.example.liew.idelivery.Service.APIService;

import java.util.Calendar;
import java.util.Locale;

/**
 * Created by kundan on 12/15/2017.
 */

public class Common {

    public static final String SHIPPERS_TABLE = "Shippers";
    public static final String ORDER_NEED_SHIP_TABLE = "OrdersNeedShip";

    public static User currentUser;
    public static Request currentRequest;

    public static String topicName = "News";

    public static final String UPDATE = "Update";
    public static final String DELETE = "Delete";

    public static final String USER_KEY = "User";
    public static final String PWD_KEY = "Password";

    public static final int PICK_IMAGE_REQUEST = 71;

    public static final String baseUrl = "https://maps.googleapis.com";

    private static final String BASE_URL = "https://fcm.googleapis.com/";

    public static String PHONE_TEXT = "userPhone";

    public static APIService getFCMService()    {
        return FCMRetrofitClient.getClient(BASE_URL).create(APIService.class);
    }

    public static String convertCodeToStatus(String code)  {
        if (code.equals("0"))   {
            return "Placed.";
        }
        else if (code.equals("1"))   {
            return "On The Way.";
        }
        else    {
            return "Shipping.";
        }
    }

    public static IGeoCoordinates getGeoCodeService()   {
        return RetrofitClient.getClient(baseUrl).create(IGeoCoordinates.class);
    }

    public static Bitmap scaleBitmap(Bitmap bitmap, int newWidth, int newHeight)    {

        Bitmap scaleBitmap = Bitmap.createBitmap(newWidth,newHeight, Bitmap.Config.ARGB_8888);

        float scaleX = newWidth / (float)bitmap.getWidth();
        float scaleY = newHeight / (float)bitmap.getWidth();

        float pivotX = 0, pivotY = 0;

        Matrix scaleMatrix = new Matrix();
        scaleMatrix.setScale(scaleX,scaleY,pivotX,pivotY);

        Canvas canvas = new Canvas(scaleBitmap);
        canvas.setMatrix(scaleMatrix);
        canvas.drawBitmap(bitmap,0,0,new Paint(Paint.FILTER_BITMAP_FLAG));

        return scaleBitmap;
    }

    public static String getDate(long time) {

        Calendar calendar = Calendar.getInstance(Locale.ENGLISH);
        calendar.setTimeInMillis(time);

        StringBuilder date = new StringBuilder(DateFormat.format("dd-MM-yyyy HH:mm",calendar).toString());

        return date.toString();
    }
}
