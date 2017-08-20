package com.example.android.quakereport;

import android.app.Activity;
import android.graphics.drawable.GradientDrawable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by preri on 6/8/2017.
 */

class EarthquakeAdapter extends ArrayAdapter<Earthquake> {


    public EarthquakeAdapter(Activity context, ArrayList<Earthquake> earthquakes) {
        super(context, 0, earthquakes);

    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent){

        View listItemView  = convertView;
        if(listItemView==null){
            listItemView= LayoutInflater.from(getContext()).inflate(R.layout.earthquake_list_item,parent,false);
        }

        final Earthquake eqInfo = getItem(position);


        TextView mag = (TextView) listItemView.findViewById(R.id.eq_mag);
        mag.setText( decimalCheck(eqInfo.getEqMag()));

        TextView off = (TextView) listItemView.findViewById(R.id.eq_offset);
        off.setText(offset(eqInfo.getEqLoc()));


        TextView loc = (TextView) listItemView.findViewById(R.id.eq_loc);
        loc.setText(location(eqInfo.getEqLoc()));

        Date object = new Date(eqInfo.getEqDate());

        TextView date = (TextView) listItemView.findViewById(R.id.eq_date);
        String simpleDate = formatDate(object);
        date.setText(simpleDate);

        TextView time = (TextView) listItemView.findViewById(R.id.eq_time);
        String simpleTime = formatTime(object);
        time.setText(simpleTime);

        // Set the proper background color on the magnitude circle.
        // Fetch the background from the TextView, which is a GradientDrawable.
        GradientDrawable magnitudeCircle = (GradientDrawable) mag.getBackground();

        // Get the appropriate background color based on the current earthquake magnitude
        int magnitudeColor = getMagnitudeColor(eqInfo.getEqMag());

        // Set the color on the magnitude circle
        magnitudeCircle.setColor(magnitudeColor);


        return listItemView;
    }

    public int getMagnitudeColor(double magnitude){
        int magColor;
        int magnitudeFloor=(int)Math.floor(magnitude);
        switch(magnitudeFloor){
            case 0 :
            case 1 : magColor = R.color.magnitude1;
                     break;
            case 2: magColor=R.color.magnitude2;
                    break;
            case 3: magColor=R.color.magnitude3;
                break;
            case 4: magColor=R.color.magnitude4;
                break;
            case 5: magColor=R.color.magnitude5;
                break;
            case 6: magColor=R.color.magnitude6;
                break;
            case 7: magColor=R.color.magnitude7;
                break;
            case 8: magColor=R.color.magnitude8;
                break;
            case 9: magColor=R.color.magnitude9;
                break;
            default: magColor=R.color.magnitude10plus;
                    break;

        }
        return ContextCompat.getColor(getContext(),magColor);

    }


    private String decimalCheck(Double eqMag) {
        DecimalFormat df = new DecimalFormat("0.0");
        return df.format(eqMag);
    }

    private String offset(String eqOff) {
        if(eqOff.contains(" of "))
        {
            int xOff = eqOff.indexOf(" of ");
            return eqOff.substring(0,xOff+3);
        }
        else
            return "Near the";

    }

    private String location (String eqLoc){
        int len = eqLoc.length();
        if(eqLoc.contains(" of "))
        {
            int xLoc = eqLoc.indexOf(" of ");
            return eqLoc.substring(xLoc+4,len);
        }
        else
            return eqLoc.substring(0);


    }

    private String formatTime(Date object) {

        SimpleDateFormat sdf = new SimpleDateFormat("h:mm a");
        return sdf.format(object);
    }

    private String formatDate(Date object) {
        SimpleDateFormat sdf = new SimpleDateFormat("LLL dd, yyyy");
        return sdf.format(object);
    }


}
