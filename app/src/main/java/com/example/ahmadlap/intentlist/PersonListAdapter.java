package com.example.ahmadlap.intentlist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Mohammed PC on 2/24/2015.
 */
public class PersonListAdapter extends ArrayAdapter<String> {
    Context context;
    List<String> names;
    List<String> phones;
    public PersonListAdapter(Context context, int resource, List<String> names, List<String> phones) {
        super(context, resource, names);
        this.context = context;
        this.names = names;
        this.phones = phones;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){/*To reduce creating new inflaters (previously uncreated) */

            LayoutInflater inflater= (LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.list_item, parent, false);

            TextView tvName = (TextView) convertView.findViewById(R.id.tvName);
            TextView tvPhone= (TextView) convertView.findViewById(R.id.tvPhone);
            ViewHolder viewHolder= new ViewHolder();
            viewHolder.holdedName=tvName;
            viewHolder.holdedPhone=tvPhone;
            convertView.setTag(viewHolder);// To attach an Object to view
        }
        ViewHolder viewHolder= (ViewHolder) convertView.getTag();
        viewHolder.holdedName.setText(names.get(position));
        viewHolder.holdedPhone.setText(phones.get(position));

        return convertView;
    }

    static class ViewHolder {
        TextView holdedName;
        TextView holdedPhone;
    }

}

