package com.example.ahmadlap.intentlist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Mohammed PC on 2/24/2015.
 */
public class PersonListAdapter extends ArrayAdapter<String> {
    Context context;
    List<String> names;
    List<String> phones;
    CustomButtonListener customListener;
    public PersonListAdapter(Context context, int resource, List<String> names, List<String> phones) {
        super(context, resource, names);
        this.context = context;
        this.names = names;
        this.phones = phones;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if(convertView == null){/*To reduce creating new inflaters (previously uncreated) */

            LayoutInflater inflater= (LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.list_item, parent, false);

            TextView tvName = (TextView) convertView.findViewById(R.id.tvName);
            TextView tvPhone= (TextView) convertView.findViewById(R.id.tvPhone);
            ImageButton iBDelete = (ImageButton) convertView.findViewById(R.id.deletePerson);
            ViewHolder viewHolder= new ViewHolder();
            viewHolder.holdedName=tvName;
            viewHolder.holdedPhone=tvPhone;
            viewHolder.iBDelete = iBDelete;
            convertView.setTag(viewHolder);// To attach an Object to view
        }
        ViewHolder viewHolder= (ViewHolder) convertView.getTag();
        viewHolder.holdedName.setText(names.get(position));
        viewHolder.holdedPhone.setText(phones.get(position));
        viewHolder.iBDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (customListener != null) {
                    customListener.onButtonClickListener(position);
                }
            }
        });

        return convertView;
    }

    public void setCustomButtonListener(CustomButtonListener listener) {
        this.customListener = listener;
    }

    public interface CustomButtonListener {
        public void onButtonClickListener(int position);
    }

    static class ViewHolder {
        TextView holdedName;
        TextView holdedPhone;
        ImageButton iBDelete;
    }

}

