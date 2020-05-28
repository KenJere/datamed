 package blazingwater.com.raphael;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

 public class get_info extends Activity {
    String JSON_STRING, json_string;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_info);
    }
   public void getJSON(View view)
   {
      new Backgroundtask().execute();
   }
   class Backgroundtask extends AsyncTask <Void, Void, String>
   {
       String json_url;
       @Override
       protected void onPreExecute() {
           json_url = "http://datamed.co.ke/json_get_data.php";
       }

       @Override
       protected String doInBackground(Void... voids) {
           try {
               URL url = new URL(json_url);
               HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
               InputStream inputStream =httpURLConnection.getInputStream();
               BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
               StringBuilder stringBuilder = new StringBuilder();
               while ((JSON_STRING = bufferedReader.readLine())!= null)
               {
                  stringBuilder.append(JSON_STRING+"\n");
               }
               bufferedReader.close();
               inputStream.close();
               httpURLConnection.disconnect();
               return stringBuilder.toString().trim();
           }
           catch (MalformedURLException e) {
               e.printStackTrace();
           }
           catch (IOException e) {
               e.printStackTrace();
           }

           return null;
       }

       @Override
       protected void onProgressUpdate(Void... values) {

       }

       @Override
       protected void onPostExecute(String result) {
           TextView textView = findViewById(R.id.textView);
           textView.setText(result);
           json_string = result;
       }
   }
     public void parseJSON(View view)
     {
      if(json_string==null)
      {
          Toast.makeText(getApplicationContext(),"First get JSON",Toast.LENGTH_LONG).show();
      }
      else
      {
          Intent intent= new Intent(this, DisplayListView.class);
          intent.putExtra("json_data",json_string);
          startActivity(intent);
      }
     }
}
