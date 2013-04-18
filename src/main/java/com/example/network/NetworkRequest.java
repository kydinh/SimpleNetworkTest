package com.example.network;

import com.example.network.api.ApiRequest;

public class NetworkRequest extends ApiRequest{

    @Override
    public String getUrlString() {
        return "http://stackoverflow.com/questions/6343166/android-os-networkonmainthreadexception";
    }
    
}
