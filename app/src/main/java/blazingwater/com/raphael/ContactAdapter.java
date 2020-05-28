package blazingwater.com.raphael;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class ContactAdapter extends ArrayAdapter {

    List list = new ArrayList();

    public ContactAdapter(Context context, int resource) {
        super(context, resource);
    }

    public void add(Contacts object) {
        super.add(object);
        list.add(object);

    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Nullable
    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View row;
        row = convertView;
        ContactHolder contactHolder;
        if (row == null)
        {
            LayoutInflater layoutInflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = layoutInflater.inflate(R.layout.row_layout,parent, false);
            contactHolder = new ContactHolder();
            contactHolder.tx_name = row.findViewById(R.id.tx_name);
            contactHolder.tx_phone = row.findViewById(R.id.tx_phone);
            contactHolder.tx_amount = row.findViewById(R.id.tx_amount);
            row.setTag(contactHolder);
        }
        else
            {
              contactHolder = (ContactHolder) row.getTag();
            }
        Contacts contacts = (Contacts)this.getItem(position);
        contactHolder.tx_name.setText(contacts.getName());
        contactHolder.tx_phone.setText(contacts.getPhone());
        contactHolder.tx_amount.setText(contacts.getAmount());
        return row ;
    }
    static class ContactHolder
    {
        TextView tx_name , tx_phone , tx_amount;
    }
}
