package com.vivek.android2.gullu;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.ArrayList;

import Adapter.SelectListAdapter;
import AsynkTask.GetList;
import Model.ModelName;

public class Autocomplete extends AppCompatActivity {
    AutoCompleteTextView autoCompleteTextView;
    EditText name, secret_number;
    Button submit, touch_me;
    ProgressBar progress_bar;
    RelativeLayout block_user_interaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_autocomplete);
        find_views();
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
        final SelectListAdapter selectListAdapter = new SelectListAdapter(this, R.layout.list_item, modelNames);
        autoCompleteTextView.setAdapter(selectListAdapter);
        autoCompleteTextView.addTextChangedListener(new TextWatcher() {

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

        on_click_listener();

    }

    public void find_views() {
        autoCompleteTextView = (AutoCompleteTextView) findViewById(R.id.autocopmlete);
        name = (EditText) findViewById(R.id.name);
        secret_number = (EditText) findViewById(R.id.secret_number);
        submit = (Button) findViewById(R.id.submit);
        progress_bar = (ProgressBar) findViewById(R.id.progress_bar);
        block_user_interaction = (RelativeLayout) findViewById(R.id.block_user_interaction);
        touch_me = (Button) findViewById(R.id.touch_me);
    }

    public void on_click_listener() {
        View.OnClickListener clickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.submit:
                        GetList getList = new GetList(Autocomplete.this, progress_bar, block_user_interaction);
                        getList.execute();
                        break;
                    case R.id.touch_me:
                        Intent intent = new Intent(getApplicationContext(), Select.class);
                        startActivity(intent);
                        break;
                }
            }
        };
        submit.setOnClickListener(clickListener);
        touch_me.setOnClickListener(clickListener);
    }

    public void congratulation_message() {
        Toast toast = new Toast(Autocomplete.this);
        toast.setGravity(Gravity.CENTER, 0, 0);
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.congratulation_message_layout, (ViewGroup) findViewById(R.id.root));
        toast.setView(view);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_autocomplete, menu);
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
