package com.example.emicalculator;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

class NameAdapter extends ArrayAdapter <Names> {
    public NameAdapter(Activity context, ArrayList<Names> userNames) {
        super(context, 0,userNames);


    }
}
