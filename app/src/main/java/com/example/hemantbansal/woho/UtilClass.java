package com.example.hemantbansal.woho;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.app.FragmentManager;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Brekisssh on 30/1/16.
 */


/*This class will contain methods to be used through out the project*/


public class UtilClass {
	
	
	//Use to get json response from the url ... @param(completeurl) = the url from which you want to take data;

public static JSONObject getJSONFromUrl(String phnno){
    InputStream is = null;
    JSONObject jsonObject=null;
    String jsonstring="";
    String phone=phnno;
    String message="Dear user, your OTP is  3476";
    String ur= "http://api.textlocal.in/send/?username=hem.ban.061@gmail.com&hash=23d89d627abc938aee0eef548380ee9143fe1325&sender=TXTLCL&message="+message+"&numbers="+phone;
    String completeurl = ur;
    try {
        URL url = new URL(completeurl);
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        urlConnection.setRequestMethod("POST");
        urlConnection.setReadTimeout(15000);
        urlConnection.setDoOutput(true);
        urlConnection.setDoInput(true);

        is = new BufferedInputStream(urlConnection.getInputStream());
        Scanner s = new Scanner(is).useDelimiter("\\A");
        if(s.hasNext()){
            jsonstring= s.next();
        }
        urlConnection.disconnect();
    } catch (MalformedURLException e) {
        Log.d("error", "error in getjsonfromurl MalformedUrlexception");
    } catch (IOException e) {
        Log.d("error", "error in getjsonfromurl Ioexception");
    }
    catch (Exception e){
        e.printStackTrace();
    }

    Log.d("Answer=",jsonstring);

    try {
        jsonObject = new JSONObject(jsonstring);
    } catch (JSONException e) {
        Log.d("error", "Json exception in get JSONFRomURL ");
        return null;

    }
    return jsonObject;
}


  

    public static boolean isNetworkAvailable(Context context)
    {
        ConnectivityManager connectivityManager= (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo=connectivityManager.getActiveNetworkInfo();
        return (networkInfo!=null && networkInfo.isConnected());
    }
	
	//in this method pass a string it will return a boolean whether it is a valid email or not

    public static boolean isValidEmail(String email) {
        return !TextUtils.isEmpty(email) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    public static void toastS(Context context , String message){
        Toast.makeText(context,message,Toast.LENGTH_SHORT).show();
    }

    public static void toastL(Context context , String message){


        Toast.makeText(context,message,Toast.LENGTH_LONG).show();
    }

	
	//This method will be used to hit a url with POST method

    public static JSONObject postJSONObject(String completeUrl,JSONObject jsonObject)
    {
        DataOutputStream dataOutputStream;
        InputStream is;
        String jsonstring1 ="";
        JSONObject jsonObject1= null;

        JSONObject j = new JSONObject();



        try{
            String jsonstring = jsonObject.toString();
            URL url = new URL(completeUrl);
            HttpURLConnection httpURLConnection=(HttpURLConnection)url.openConnection();
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setConnectTimeout(15000);
            httpURLConnection.setReadTimeout(15000);
            httpURLConnection.setRequestProperty("Content-Type", "application/json;charset=utf-8");
            httpURLConnection.setRequestProperty("X-Requested-With", "XMLHttpRequest");

            dataOutputStream = new DataOutputStream(httpURLConnection.getOutputStream());
            dataOutputStream.write(jsonstring.getBytes());
            dataOutputStream.flush();
            dataOutputStream.close();

            int httpResult = httpURLConnection.getResponseCode();
            if(httpResult==HttpURLConnection.HTTP_OK) {
                is = new BufferedInputStream(httpURLConnection.getInputStream());
                Scanner s = new Scanner(is).useDelimiter("\\A");
                if (s.hasNext()) {
                    jsonstring1 = s.next();
                }
            }

        }catch(MalformedURLException e){
            Log.d("error","malformedUrl in Post");
        }catch (IOException e){
            Log.d("error","IOException in Post");
        }catch(Exception e){
            Log.d("error","Exception in Post");
        }


        Log.e("eeroor",jsonstring1);

        try {
            jsonObject1 = new JSONObject(jsonstring1);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return jsonObject1;
    }
    
    //Use this method to convert bitmap into BASE64 encoded image.
       public static String getStringImage(Bitmap bmp){
        if (bmp!=null){
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
            byte[] imageBytes = baos.toByteArray();
            return Base64.encodeToString(imageBytes, Base64.DEFAULT);}

        return null;
    }

    //method to decode image from base64
    public static Bitmap decodeImage(String encodedString){
        String encodedImage= encodedString.replace("data:image/jpeg;base64,","");
        byte[] decodedString = Base64.decode(encodedImage, Base64.DEFAULT);
        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);

        return decodedByte;
    }
}
