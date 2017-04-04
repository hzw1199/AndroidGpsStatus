# GPS Status
[![](https://jitpack.io/v/hzw1199/AndroidGpsStatus.svg)](https://jitpack.io/#hzw1199/AndroidGpsStatus)

![](/media/anglerM4B30Xwuzongheng04042017015425.gif)

# Download
在project的build.gradle中加入以下语句:  

```
allprojects {
    repositories {
        ...
        maven { url "https://jitpack.io" }
    }
}
```

在module的build.gradle中加入以下语句:  

```
dependencies {
    compile 'com.github.hzw1199:AndroidGpsStatus:1.0'
}
```
# Features

* ```GpsStatusTextView``` 和 ```GpsStatusImageView``` 独立布局
* 通过 ```GpsStatusTextView``` 颜色的改变反映GPS关闭、定位成功、正在定位三种状态
* 通过 ```GpsStatusImageView``` 图标的改变反映GPS关闭以及GPS信号强度弱、中、强状态
* 在手动开启和关闭GPS时能准确作出判断
* 在经过隧道导致GPS定位丢失时能准确作出判断

# Usage

## Layout

### GpsStatusTextView

* 只需在布局文件中定义 ``GpsStatusTextView```  

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
* 支持在xml中设置GPS关闭、定位成功、正在定位三种状态下的字体颜色  

| Attribute      | format        | describe  | default |
| :---------: | :-------------: |:-------------:|:-------------:|
| colorClosed|color|GPS关闭时字体颜色|#F44336|
| colorFixed |color|GPS定位成功时字体颜色|#4CAF50|
| colorUnFixed |color|GPS正在定位时字体颜色|#f4b400|

### GpsStatusImageView

* 只需在布局文件中定义 ```GpsStatusImageView```  

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
* 支持在xml中设置GPS关闭以及GPS信号强度弱、中、强状态下的图片资源  
* 支持在xml中设置GPS信号强度弱-中、中-强状态的卫星数量阈值，默认为4、7  

| Attribute      | format        | describe  | default |
| :---------: | :-------------: |:-------------:|:-------------:|
| drawable0 | reference |GPS关闭时图标资源| ic_gps_0_24dp|
| drawable1 | reference |GPS信号强度弱时图标资源| ic_gps_1_24dp|
| drawable2 | reference |GPS信号强度中等时图标资源| ic_gps_2_24dp|
| drawable3 | reference |GPS信号强度强时图标资源| ic_gps_3_24dp|
| thr_1_2 | integer |GPS信号强度从弱到中的卫星数量阈值|4|
| thr_2_3 | integer |GPS信号强度从中到强的卫星数量阈值|7|

## Set Up
两个控件只需在布局文件中定义，但是在代码中仍然需要进行如下操作：

* 权限  
在AndroidManifest.xml中声明权限  

```xml
<uses-permission android:name="android.permission.ACCESS_FINE_LOCATION" />
```

* 定义  

```java
GpsStatusProxy proxy;
```

* 初始化  
在确认拥有```ACCESS_FINE_LOCATION```权限后进行初始化

```java
proxy = GpsStatusProxy.getInstance(getApplicationContext());
proxy.register();
```

* 每次定位回调后调用```GpsStatusProxy.notifyLocation(android.location.Location)```，例如：  

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

* 销毁  
  
```java
proxy.unRegister();
```



* 若对你有帮助请加星  

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