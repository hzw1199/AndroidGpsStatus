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

```xml
<declare-styleable name="GpsStatusTextView">
    <attr name="colorClosed" format="color" />
    <attr name="colorFixed" format="color" />
    <attr name="colorUnFixed" format="color" />
</declare-styleable>
```

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

```xml
<declare-styleable name="GpsStatusImageView">
    <attr name="drawable0" format="reference" />
    <attr name="drawable1" format="reference" />
    <attr name="drawable2" format="reference" />
    <attr name="drawable3" format="reference" />
    <attr name="thr_1_2" format="integer" />
    <attr name="thr_2_3" format="integer" />
</declare-styleable>
```

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
LocationManager locationManager;
```

* 初始化  
在确认拥有ACCESS_FINE_LOCATION权限后进行初始化

```java
proxy = GpsStatusProxy.getInstance(getApplicationContext());
locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);

proxy.register();
locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 1, listener);
```

* 每次定位回调后调用GpsStatusProxy.notifyLocation(Location)

```java
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
locationManager.removeUpdates(listener);
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