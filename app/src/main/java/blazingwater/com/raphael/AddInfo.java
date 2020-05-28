package blazingwater.com.raphael;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.LauncherActivity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;


public class AddInfo extends Activity{
    Button mSend;
    EditText mfarmer_ID, mAmount;
    Spinner mSpinner;
    String farmer_ID, shift_status, amount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_info);

        mSend = findViewById(R.id.send);
        mfarmer_ID =  findViewById(R.id.farmer_ID);
        mAmount = findViewById(R.id.amount);
        mSpinner = findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.spinner, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinner.setAdapter(adapter);

    }
    public void sendInfo(View view)
    {
      farmer_ID = mfarmer_ID.getText().toString();
      shift_status = mSpinner.getSelectedItem().toString();
      amount = mAmount.getText().toString();
      Log.d("UserINPUT", "The farmer ID==  " + farmer_ID);
      Log.d("UserINPUT", "The shift_status==  " + shift_status);
      Log.d("UserINPUT", "The amount ==  " + amount);
      BackgroundTask backgroundTask = new BackgroundTask();
      backgroundTask.execute(farmer_ID, shift_status, amount);
      finish();

    }

    class BackgroundTask extends AsyncTask<String,Void,String>
      {
          String add_info_url;
          @Override
          protected void onPreExecute() {

              add_info_url= "http://datamed.co.ke/add_info.php";
          }

          @Override
          protected String doInBackground(String... args) {
              String farmer_ID, shift_status, amount;
              farmer_ID = args [0];
              shift_status = args [1];
              amount = args [2];
              try {
                  URL url = new URL(add_info_url) ;
                  HttpURLConnection httpURLConnection=(HttpURLConnection) url.openConnection();
                  httpURLConnection.setRequestMethod("POST");
                  httpURLConnection.setDoOutput(true);
                  OutputStream outputStream = httpURLConnection.getOutputStream();
                  BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));

                  String data_string = URLEncoder.encode("farmer_ID", "UTF-8")+"="+URLEncoder.encode(farmer_ID, "UTF-8")+"&"+
                          URLEncoder.encode("shift_status", "UTF-8")+"="+URLEncoder.encode(shift_status, "UTF-8")+"&"+
                          URLEncoder.encode("amount", "UTF-8")+"="+URLEncoder.encode(amount, "UTF-8");
                  bufferedWriter.write(data_string);
                  bufferedWriter.flush();
                  bufferedWriter.close();
                  outputStream.close();

                  InputStream inputStream = httpURLConnection.getInputStream();
                  inputStream.close();
                  httpURLConnection.disconnect();
                  return "Data inserted successfully..";

              } catch (MalformedURLException e){
                  e.printStackTrace();
              } catch (IOException e) {
                  e.printStackTrace();
              }


              return null;
          }

          @Override
          protected void onProgressUpdate(Void... values) {
              super.onProgressUpdate(values);
          }

          @Override
          protected void onPostExecute(String result) {
              Toast.makeText(getApplicationContext(),result,Toast.LENGTH_LONG).show();
          }
      }

}
