# GPS Status
[![](https://jitpack.io/v/hzw1199/AndroidGpsStatus.svg)](https://jitpack.io/#hzw1199/AndroidGpsStatus)

[中文看这里](/READMEcn.md)  

![](/media/anglerM4B30Xwuzongheng04042017015425.gif)

# Download
Add it in your build.gradle at the end of repositories:  

```
allprojects {
    repositories {
        ...
        maven { url "https://jitpack.io" }
    }
}
```

Add the dependency in the form:  

```
dependencies {
    compile 'com.github.hzw1199:AndroidGpsStatus:1.2'
}
```
# Features

* The layout of ```GpsStatusTextView``` and ```GpsStatusImageView``` are independent  
* The color of ```GpsStatusTextView``` shows the status of GPS sensor: closed, fixed, unfixed  
* The icon of ```GpsStatusImageView``` shows the status of GPS signal strength: closed, weak, medium, strong (through the number of satellites)  
* Accurately make judgments while manual open and close the GPS  
* Accurately make judgments while in the tunnel leading to the loss of GPS positioning  

# Usage

## Layout

### GpsStatusTextView

* Configure the ```GpsStatusTextView```  

```xml
<com.adam.gpsstatus.GpsStatusTextView
    android:id="@+id/gpsText"
    android:layout_width="wrap_content"
    android:layout_height="48dp"
    android:text="GPS"
    android:textSize="24sp"
    android:textStyle="bold"
    android:gravity="center_vertical"
    app:colorClosed="@color/gps_icon_red"
    app:colorFixed="@color/gps_icon_green"
    app:colorUnFixed="@color/gps_icon_yellow"/>
```
* Configure xml attributes to set the text color of GPS status: closed, fixed, unfixed  

| Attribute      | format        | describe  | default |
| :---------: | :-------------: |:-------------:|:-------------:|
| colorClosed|color|text color while GPS is closed|#F44336|
| colorFixed |color|text color while GPS is fixed|#4CAF50|
| colorUnFixed |color|text color while GPS is searching satellites|#F4B400|

### GpsStatusImageView

* Configure the ```GpsStatusImageView```  

```xml
<com.adam.gpsstatus.GpsStatusImageView
    android:id="@+id/gpsImage"
    android:layout_width="48dp"
    android:layout_height="48dp"
    app:drawable0="@drawable/ic_gps_0_24dp"
    app:drawable1="@drawable/ic_gps_1_24dp"
    app:drawable2="@drawable/ic_gps_2_24dp"
    app:drawable3="@drawable/ic_gps_3_24dp"
    app:thr_1_2="4"
    app:thr_2_3="7" />
```
* Configure xml attributes to set the drawable of GPS signal strength: closed, weak, medium, strong  
* Configure xml attributes to set GPS signal strength weak - medium, medium - strong through the thresholds of satellite number, default thresholds are 4 and 7  

| Attribute      | format        | describe  | default |
| :---------: | :-------------: |:-------------:|:-------------:|
| drawable0 | reference |drawable while GPS closed| ic_gps_0_24dp|
| drawable1 | reference |drawable while GPS signal is weak| ic_gps_1_24dp|
| drawable2 | reference |drawable while GPS signal is medium| ic_gps_2_24dp|
| drawable3 | reference |drawable while GPS signal is strong| ic_gps_3_24dp|
| thr_1_2 | integer |threshold of satellite number from weak to medium|4|
| thr_2_3 | integer |threshold of satellite number from medium to strong|7|

## Set Up
You only need to define ```GpsStatusTextView``` or ```GpsStatusImageView``` in layout, but still need to do the following in the code:

* Permission  
Add permission in AndroidManifest.xml  

```xml
<uses-permission android:name="android.permission.ACCESS_FINE_LOCATION" />
```

* Define  

```java
GpsStatusProxy proxy;
```

* Initialization  
Make sure to have ```ACCESS_FINE_LOCATION``` permission before initialization

```java
proxy = GpsStatusProxy.getInstance(getApplicationContext());
proxy.register();
```

* Call ```GpsStatusProxy.notifyLocation(android.location.Location)``` in location callback, for example:  

```java
LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 1, listener);
LocationListener listener = new LocationListener() {
    @Override
    public void onLocationChanged(Location location) {
        proxy.notifyLocation(location);
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
};
```

* Destroy  
  
```java
proxy.unRegister();
```



* If this project helps you, please star me.  

## License

```
The MIT License (MIT)

Copyright (c) 2017 AndroidGpsStatus

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in
all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
THE SOFTWARE.
```
