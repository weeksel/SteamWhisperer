package info.androidhive.slidingmenu.fragments;
import android.app.Fragment;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.websitepipeline.edwardweeks.steamwhisperer.R;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by kbrown on 5/29/2015.
 */
public class APICallTest extends Fragment implements View.OnClickListener {

    public APICallTest(){}
    private View mRootView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mRootView = inflater.inflate(R.layout.fragment_api_call_test, container, false);

        mRootView.findViewById(R.id.my_button1).setOnClickListener(this);
        mRootView.findViewById(R.id.my_button2).setOnClickListener(this);

        return mRootView;

    }

    private class LongRunningGetIO extends AsyncTask<View, Void, String> {
        protected String getASCIIContentFromEntity(HttpEntity entity) throws IllegalStateException, IOException {
            InputStream in = entity.getContent();

            StringBuffer out = new StringBuffer();
            int n = 1;
            while (n > 0) {
                byte[] b = new byte[4096];
                n = in.read(b);

                if (n > 0) out.append(new String(b, 0, n));
            }
            return out.toString();
        }



        @Override
        protected String doInBackground(View... params) {
            String requestString = "";
            View clickedView = params[0];

            if (clickedView.getId() == R.id.my_button1){
                requestString = "http://api.steampowered.com/ISteamNews/GetNewsForApp/v0002/?appid=440&count=3&maxlength=300&format=json";
            }
            else {
                requestString = "http://api.steampowered.com/ISteamUser/GetFriendList/v0001/?key=2E0A2C9F0350C1BC0E59A151827A78DF&steamid=76561197993865907&relationship=all";
            }

            HttpClient httpClient = new DefaultHttpClient();
            HttpContext localContext = new BasicHttpContext();
            HttpGet httpGet = new HttpGet(requestString);
            String text = null;
            try {
                HttpResponse response = httpClient.execute(httpGet, localContext);
                HttpEntity entity = response.getEntity();
                text = getASCIIContentFromEntity(entity);

            } catch (Exception e) {
                return e.getLocalizedMessage();
            }

            return text;
        }


        protected void onPostExecute(String results) {
            if (results != null) {
                EditText et = (EditText) mRootView.findViewById(R.id.my_edit);

                et.setText(results);
            }

            Button b = (Button) mRootView.findViewById(R.id.my_button1);
            Button c = (Button) mRootView.findViewById(R.id.my_button2);

            b.setClickable(true);
        }

    }

    @Override
    public void onClick(View arg0) {
        Button b = (Button) mRootView.findViewById(R.id.my_button1);
        Button c = (Button) mRootView.findViewById(R.id.my_button2);


        b.setClickable(false);
        c.setClickable(false);
        new LongRunningGetIO().execute(arg0);
    }


}

