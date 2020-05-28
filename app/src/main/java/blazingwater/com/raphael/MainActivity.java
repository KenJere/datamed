package blazingwater.com.raphael;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.util.ArrayList;

public class MainActivity extends Activity{

    Button mAdd, mView;
    TextView textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAdd = findViewById(R.id.add);
        mView = findViewById(R.id.view);
        textView = findViewById(R.id.textView);
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected())
        {
             textView.setVisibility(View.INVISIBLE);
        }
        else
        {
            mAdd.setEnabled(false);
            mView.setEnabled(false);
        }

    }
    public void addContact(View view) {

        startActivity(new Intent(this, AddInfo.class ));
    }
    public void viewContact(View view) {

        startActivity(new Intent(this, get_info.class ));
    }
}


