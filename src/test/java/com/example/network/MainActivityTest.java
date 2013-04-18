package com.example.network;

import org.junit.Before;
import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.equalTo;
import org.junit.Test;
import org.junit.runner.RunWith;
import android.widget.TextView;
import com.xtremelabs.robolectric.Robolectric;
import com.xtremelabs.robolectric.RobolectricTestRunner;

@RunWith(RobolectricTestRunner.class)
public class MainActivityTest {
    
    MainActivity activity;
    TextView tvText;
    @Before
    public void setup() throws Exception{
        Robolectric.setDefaultHttpResponse(200, "Test ok");
        activity = new MainActivity();
        activity.onCreate(null);
        tvText = (TextView)activity.findViewById(R.id.text);
        
    }
    
    @Test
    public void testRespone() throws Exception{
        
        assertThat(tvText.getText().toString(), equalTo("Test ok"));
    }
}
