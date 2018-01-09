package com.vivek.android2.gullu;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

import Adapter.SelectListAdapter;
import Model.ModelName;

public class Select extends AppCompatActivity {
    ListView listView;
    EditText edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);
        edit = (EditText) findViewById(R.id.edit);
        ArrayList<ModelName> modelNames = new ArrayList<>();
        for (int i = 0; i <= 20; i++) {
            ModelName modelName = new ModelName();
            if (i > 10) {
                modelName.setName("Smith");
            } else
                modelName.setName("Vivek Kumar Sharma");
            modelName.setAddress("5-D-261, KBHB, Jodhpur");
            modelNames.add(modelName);
        }
        listView = (ListView) findViewById(R.id.list);
        final SelectListAdapter selectListAdapter = new SelectListAdapter(this, R.layout.list_item, modelNames);
        listView.setAdapter(selectListAdapter);
        listView.setDivider(new ColorDrawable(Color.parseColor("#ffffff")));
        listView.setDividerHeight(2);
        edit.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                System.out.println("Text [" + s + "]");

                selectListAdapter.getFilter().filter(s.toString());
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {

            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_select_canteen, menu);
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
}
