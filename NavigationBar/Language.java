package com.thiman.android.reservationmanager.NavigationBar;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.thiman.android.reservationmanager.R;

import java.util.ArrayList;
import java.util.List;

public class Language extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private Spinner spinner;
    private List<String> list;
    private ArrayAdapter<String> adapter;
    private TextView textView;
    private EditText editText;
    private String editText_text,selected_item;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_language);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        editText= findViewById(R.id.editText1);
        spinner = findViewById(R.id.spinner1);
        textView = findViewById(R.id.textView1);
        list=new ArrayList<String>();
        list.add("Surat"); list.add("Nagaur");list.add("Delhi");
        add_spinner();
    }
    public void add_spinner()
    {adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//Spinner click listener
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
    }

    public void add_item(View v)
    {
        editText_text= editText.getText().toString();
        if(editText_text.length()<1)
        {
            Toast.makeText(this,"Please enter item ",Toast.LENGTH_SHORT).show();
        }
        else
        {
            list.add(editText_text);
            textView.setText("Added : "+editText_text);
            add_spinner();
            editText.setText(null);
        }

    }

    public void remove_item(View v)
    {

        list.remove(selected_item);
        textView.setText("Removed : " + selected_item);
        add_spinner();
    }

    @Override
    public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
                               long arg3) {
        // TODO Auto-generated method stub
        spinner.setSelection(arg2);
        selected_item = (String) spinner.getSelectedItem();

    }

    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub

    }
}




