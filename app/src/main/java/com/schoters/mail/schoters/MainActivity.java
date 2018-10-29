package com.schoters.mail.schoters;

import android.app.ActionBar;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    //Web api url
    public static final String DATA_URL = "http://private-90552-schoterspersonal.apiary-mock.com/categories";

    //Tag values to read from json
    //public static final String TAG_IMAGE_URL = "image";
    public static final String TAG_NAME = "name";

    //GridView Object
    private GridView gridView;
    private Button btnDone;

    //ArrayList for Storing image urls and titles
    //private ArrayList<String> images;
    private ArrayList<String> names;
    private String dataSelected;
    public ArrayList<CheckBox> array = new ArrayList<>();

    private ArrayList<String> AllCheckbox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gridView = (GridView) findViewById(R.id.gridView);
        btnDone = (Button)findViewById(R.id.btnDone);

        //images = new ArrayList<>();
        names = new ArrayList<>();

        //Calling the getData method
        getData();

        btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Log.d("Name: ","Clicked");

                Intent iCategory = new Intent(getApplicationContext(),Category.class);
                startActivity(iCategory);
            }
        });
    }

    private void getData(){
        //Showing a progress dialog while our app fetches the data from url
        final ProgressDialog loading = ProgressDialog.show(this, "Please wait...","Fetching data...",false,false);

        //Creating a json array request to get the json from our api
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(DATA_URL,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        //Dismissing the progressdialog on response
                        loading.dismiss();


                        Log.d("Data", String.valueOf(response));

                        //Displaying our grid
                        showGrid(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        );

        //Creating a request queue
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        //Adding our request to the queue
        requestQueue.add(jsonArrayRequest);
    }


    private void showGrid(JSONArray jsonArray){
        //Looping through all the elements of json array
        for(int i = 0; i<jsonArray.length(); i++){
            //Creating a json object of the current index
            JSONObject obj = null;
            try {
                //getting json object from current index
                obj = jsonArray.getJSONObject(i);

                //getting image url and title from json object
                //images.add(obj.getString(TAG_IMAGE_URL));
                names.add(obj.getString(TAG_NAME));


            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        //Creating GridViewAdapter Object
        GridViewAdapter gridViewAdapter = new GridViewAdapter(this,names);


        //Adding adapter to gridview
        gridView.setAdapter(gridViewAdapter);
    }
}
