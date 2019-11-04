package com.miamy.niforances.gyeyang;

import android.util.Log;
import android.view.View;

import net.daum.mf.map.api.CalloutBalloonAdapter;
import net.daum.mf.map.api.MapPOIItem;

class CustomCalloutBalloonAdapter implements CalloutBalloonAdapter {

    private final View mCalloutBalloon = null;

    public CustomCalloutBalloonAdapter() {
    }

    @Override
    public View getCalloutBalloon(MapPOIItem poiItem) {
        return null;
    }

    @Override
    public View getPressedCalloutBalloon(MapPOIItem poiItem) {
        Log.i("asdf","asdf");
        return null;
    }
}
