package com.example.android.quakereport;

/**
 * Created by preri on 6/8/2017.
 */

public class Earthquake {

    private Double eqMag;
    private String eqLoc;
    private static String eqUrl;
    private long eqDate;

    Earthquake(Double mag, String loc, long date,String url){
        eqMag = mag;
        eqLoc = loc;
        eqDate=date;
        eqUrl = url;
    }

    public Double getEqMag(){
        return eqMag;
    }

    public String getEqLoc(){
        return eqLoc;
    }

    public long getEqDate(){
        return eqDate;
    }

    public static String getEqUrl(){return eqUrl;}
}
