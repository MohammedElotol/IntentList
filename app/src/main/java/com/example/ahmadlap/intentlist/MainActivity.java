package com.example.ahmadlap.intentlist;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends ActionBarActivity implements PersonListAdapter.CustomButtonListener {
    public static int REQUEST_CODE = 111;
    ListView listView;
    PersonListAdapter adapter;
    List<String> names;
    List<String> phones;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        names = new ArrayList<String>();
        phones = new ArrayList<String>();

        listView = (ListView) this.findViewById(R.id.listView);
        adapter = new PersonListAdapter(getApplicationContext(), R.layout.list_item, names,phones);
        adapter.setCustomButtonListener(MainActivity.this);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, DetailsActivity.class);
                intent.putExtra("name",listView.getItemAtPosition(position).toString());
                MainActivity.this.startActivityForResult(intent, REQUEST_CODE);
            }
        });

        ImageButton iBAddPerson= (ImageButton) this.findViewById(R.id.add);
        iBAddPerson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddDetails.class);
                MainActivity.this.startActivityForResult(intent,REQUEST_CODE);

            }
        });
//        ImageButton bDeletePerson = (ImageButton) this.findViewById(R.id.deletePerson);
//        bDeletePerson.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                names.remove(listView.getItemAtPosition())
//            }
//        });  ImageButton bDeletePerson = (ImageButton) this.findViewById(R.id.deletePerson);


    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == MainActivity.REQUEST_CODE && resultCode == AddDetails.RESULT_CODE) {
            names.add(data.getStringExtra("name"));
            phones.add(data.getStringExtra("phone"));
//            Toast.makeText(getApplicationContext(),data.getStringExtra("name"),Toast.LENGTH_SHORT).show();
//            Toast.makeText(getApplicationContext(),data.getStringExtra("phone"),Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onButtonClickListener(int position) {
        names.remove(position);
        phones.remove(position);
        adapter.notifyDataSetChanged();
    }
}
