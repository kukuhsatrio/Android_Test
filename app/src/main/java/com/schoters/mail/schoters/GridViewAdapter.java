package com.schoters.mail.schoters;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.content.res.AppCompatResources;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import java.util.ArrayList;
import java.util.List;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;
import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;
import static java.security.AccessController.getContext;

/**
 * Created by Belal on 12/22/2015.
 */
public class GridViewAdapter extends BaseAdapter {

    //Imageloader to load images
    private ImageLoader imageLoader;

    //Context
    private Context context;

    //Array List that would contain the urls and the titles for the images
    //private ArrayList<String> images;
    private ArrayList<String> names;

    public GridViewAdapter(Context context, ArrayList<String> names){
        //Getting all the values
        this.context = context;
        //this.images = images;
        this.names = names;
    }

    /*@Override
    public int getCount() {
        return images.size();
    }*/

    @Override
    public int getCount() {
        return names.size();
    }

    @Override
    public Object getItem(int position) {
        return names.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //Creating a linear layout
        LinearLayout linearLayout = new LinearLayout(context);
        linearLayout.setOrientation(LinearLayout.VERTICAL);

        //NetworkImageView
        NetworkImageView networkImageView = new NetworkImageView(context);

        //Initializing ImageLoader
        //imageLoader = CustomVolleyRequest.getInstance(context).getImageLoader();
        //imageLoader.get(images.get(position), ImageLoader.getImageListener(networkImageView, R.mipmap.ic_launcher, android.R.drawable.ic_dialog_alert));

        //Setting the image url to load
       // networkImageView.setImageUrl(images.get(position),imageLoader);

        //Creating a textview to show the title

        CheckBox checkBox= new CheckBox(context);
        checkBox.setText(names.get(position));
        checkBox.setBackground(ContextCompat.getDrawable(context, R.drawable.bg_selector));
        checkBox.setPadding(dpToPx(10), dpToPx(5), dpToPx(10), dpToPx(5));
        checkBox.setTextSize(dpToPx(7));
        checkBox.setTextColor(AppCompatResources.getColorStateList(context, R.color.selector_txt));
        checkBox.setButtonDrawable(ContextCompat.getDrawable(context, android.R.color.transparent));
        checkBox.setId(R.id.checkbox0);

        //TextView textView = new TextView(context);
        //textView.setText(names.get(position));

        //Scaling the imageview
        //networkImageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        linearLayout.setLayoutParams(new GridView.LayoutParams(MATCH_PARENT,MATCH_PARENT));

        //Adding views to the layout
        linearLayout.addView(checkBox);
        //linearLayout.addView(networkImageView);

        //Returnint the layout
        return linearLayout;
    }

    public int dpToPx(int dp) {
        float density = this.context.getResources().getDisplayMetrics().density;
        return Math.round((float) dp * density);
    }
}