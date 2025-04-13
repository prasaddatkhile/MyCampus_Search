package com.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class DataUrls {

    public StringBuffer getResponseData()throws IOException{

        String url=" https://api.unsplash.com/photos/random?query=colleges&client_id=w205hi2l5V97X7hEOXADOteBxnX8Uh0RW3Md8Q-bG6k";

        HttpURLConnection httpClient=(HttpURLConnection) new URL(url).openConnection();
        httpClient.setRequestMethod("GET");
        StringBuffer response=new StringBuffer();
         int responseCode=httpClient.getResponseCode();

         if(responseCode==HttpURLConnection.HTTP_OK){

            BufferedReader in=new BufferedReader(new InputStreamReader(httpClient.getInputStream()));
           String inputLine;
           while ((inputLine=in.readLine())!=null) {
            response.append(inputLine);
            
           }
           in.close();
           return response;
        }else{
            throw new RuntimeException("Get request failed with response code"+ responseCode);

        }

            }

    

        


    }
    

