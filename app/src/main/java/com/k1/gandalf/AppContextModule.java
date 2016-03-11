package com.k1.gandalf;

import android.content.Context;
import android.location.LocationManager;

import dagger.Module;
import dagger.Provides;

/**
 * Sample of using {@link Module}
 * Created by k1 on 3/11/16.
 */
@Module
public class AppContextModule {

    private final CustomApplication application;

    public AppContextModule(CustomApplication application) {
        this.application = application;
    }

    @Provides
    public CustomApplication application(){
        return this.application;
    }

    @Provides
    public Context applicationContext(){
        return this.application;
    }

    @Provides
    public LocationManager locationService(Context context){
        return (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
    }


}
