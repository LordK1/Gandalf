package com.k1.gandalf;

import android.content.Context;
import android.location.LocationManager;

/**
 * Created by k1 on 3/11/16.
 */
public interface AppContextComponent {


    CustomApplication application(); //provision method

    Context applicationContext();//provision method

    LocationManager locationManager();//provision method
}
