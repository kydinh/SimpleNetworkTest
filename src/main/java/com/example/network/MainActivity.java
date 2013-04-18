package com.example.network;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.util.EntityUtils;
import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import com.example.network.api.Http;

public class MainActivity extends Activity {
    
    TextView tvText;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvText = (TextView)findViewById(R.id.text);
        NetworkRequest[]networkRequests = {new NetworkRequest()};
        new C4iRequestData().execute(networkRequests);
    }

    private class C4iRequestData extends AsyncTask<NetworkRequest, Void, String> {
        
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            tvText.setText("abc");
        }

        @Override
        protected String doInBackground(NetworkRequest... networkRequests) {
            
            NetworkRequest request = networkRequests[0];
            Log.d("Debug", request.getUrlString());
            Http http = new Http();
            Log.d("Debug", request.getUrlString());
            try {
                HttpResponse result =  http.get(request.getUrlString(), request.getHeaders());
                return EntityUtils.toString(result.getEntity());
            } catch (Exception e) {
                Log.d("Debug", request.getUrlString());
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            try {
                //Log.d("Debug4", ""+result.getStatusLine().getStatusCode());
                tvText.setText(result);
                //tvText.setText("abcef");
            } catch (ParseException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IllegalStateException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } 
        }
    }
}
