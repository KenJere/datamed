package blazingwater.com.raphael;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class DisplayListView extends AppCompatActivity {
String json_string;
JSONObject jsonObject;
JSONArray jsonArray;
ContactAdapter contactAdapter;
ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display_listview_layout);
        listView = findViewById(R.id.listview);
        contactAdapter = new ContactAdapter(this,R.layout.row_layout);
        listView.setAdapter(contactAdapter);
        json_string = getIntent().getExtras().getString("json_data");
        try {
            jsonObject = new JSONObject(json_string);
            jsonArray = jsonObject.getJSONArray("server_response");
            int count = 0 ;
            String name, phone, amount;

            while(count<jsonArray.length())
            {
              JSONObject JO = jsonArray.getJSONObject(count);
                name = JO.getString("name");
                phone = JO.getString("phone");
                amount = JO.getString("amount");
                Contacts contacts = new Contacts(name, phone, amount);
                contactAdapter.add(contacts);
                count++;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
