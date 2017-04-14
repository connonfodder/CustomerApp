/*
package com.aadhk.customer.ui.activity;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.aadhk.customer.R;
import com.aadhk.customer.util.Constant;
import com.aadhk.customer.util.FetchAddressIntentService;
import com.aadhk.customer.util.PermissionUtils;
import com.aadhk.library.rx.RxSchedulers;
import com.aadhk.library.utils.LogUtil;
import com.aadhk.library.utils.ToastUtil;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;
import rx.functions.Func1;

public class MapsActivity extends AppCompatActivity implements GoogleMap.OnMyLocationButtonClickListener, OnMapReadyCallback, ActivityCompat.OnRequestPermissionsResultCallback, GoogleMap.OnMapClickListener {

    private GoogleMap mMap;
    private Marker mMarker;
    private LatLng currLatLng;
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1;
    private static final int LOCATION_PERMISSION_REQUEST_LAST_LOCATION = 2;
    private final static int REQUEST_PLACE_PICKER = 3;
    private static final LatLng COMPANY = new LatLng(22.5328372933, 114.0194437472);
    private TextView tv;
    LocationManager mLocationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
//        int dpi = getResources().getDisplayMetrics().densityDpi;
//        LogUtil.e("dpi"+dpi);
        tv = (TextView) findViewById(R.id.tv);
        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getCurrentLocation();
//                getLocation("大厦");
//                startIntentService();
                try {
                    PlacePicker.IntentBuilder intentBuilder = new PlacePicker.IntentBuilder();
                    Intent intent = intentBuilder.build(MapsActivity.this);
                    startActivityForResult(intent, REQUEST_PLACE_PICKER);
                } catch (GooglePlayServicesRepairableException e) {
                    e.printStackTrace();
                } catch (GooglePlayServicesNotAvailableException e) {
                    e.printStackTrace();
                }

            }
        });
        mLocationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_PLACE_PICKER && resultCode == Activity.RESULT_OK) {
            // The user has selected a place. Extract the name and address.
            final Place place = PlacePicker.getPlace(data, this);
            final CharSequence name = place.getName();
            final CharSequence address = place.getAddress();
            String attributions = PlacePicker.getAttributions(data);
            if (attributions == null) attributions = "";
            tv.setText("name=" + name + ", address=" + address + ", html=" + Html.fromHtml(attributions));
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    public void onMapClick(LatLng latLng) {
        this.currLatLng = latLng;
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 16));
        mMarker.setPosition(latLng);
        getCurrentLocations();
//        startIntentService();
    }

    @Override
    public boolean onMyLocationButtonClick() {
        this.currLatLng = mMap.getCameraPosition().target;
        mMarker.setPosition(currLatLng);
        getCurrentLocations();
        return false;
    }

    private void getCurrentLocations() {
        final Geocoder geocoder = new Geocoder(this, Locale.getDefault());
        Observable observable = Observable.just(1)
                .map(new Func1<Integer, List<Address>>() {
                    @Override
                    public List<Address> call(Integer integer) {
                        List<Address> addresses = null;
                        try {
                            addresses = geocoder.getFromLocation(currLatLng.latitude, currLatLng.longitude, 4);
                        } catch (Exception e) {
                            //TODO:
                            addresses = new ArrayList<Address>();
                        }
                        return addresses;
                    }
                }).compose(RxSchedulers.io_main());
        observable.subscribe(new Action1<List<Address>>() {
            @Override
            public void call(List<Address> o) {
                String address = "";
                for (Address item : o) {
                    ToastUtil.showShort("名称:" + item.getAddressLine(0) + " 街道:" + item.getAddressLine(1) + "   ");
                    ArrayList<String> addressFragments = new ArrayList<>();
                    for (int i = 0; i < item.getMaxAddressLineIndex(); i++) {
                        addressFragments.add(item.getAddressLine(i));
                    }
                    address += TextUtils.join(System.getProperty("line.separator"), addressFragments) + "\n"; // item.getAddressLine(0) + " :" + item.getAddressLine(1) + " :" + item.getAddressLine(2) + " :" + item.getAddressLine(3) + " \n ";
                }
                tv.setText(address);
            }
        });
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(0, 0);
        mMarker = mMap.addMarker(new MarkerOptions().position(sydney).title("marker").draggable(true));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        mMap.setOnMyLocationButtonClickListener(this);
        mMap.setOnMapClickListener(this);
        enableMyLocation();
        UiSettings settings = mMap.getUiSettings();
        settings.setMapToolbarEnabled(true);
        settings.setCompassEnabled(true);
        settings.setZoomControlsEnabled(true);
        settings.setRotateGesturesEnabled(true);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case LOCATION_PERMISSION_REQUEST_CODE:
                if (PermissionUtils.isPermissionGranted(permissions, grantResults, Manifest.permission.ACCESS_FINE_LOCATION)) {
                    // Enable the my location layer if the permission has been granted.
                    enableMyLocation();
                } else {
                    // Display the missing permission error dialog when the fragments resume. TODO:
                }
                break;
            case LOCATION_PERMISSION_REQUEST_LAST_LOCATION:
                if (PermissionUtils.isPermissionGranted(permissions, grantResults, Manifest.permission.ACCESS_FINE_LOCATION)) {
                    // Enable the my location layer if the permission has been granted.
//                    startIntentService();
                } else {
                    // Display the missing permission error dialog when the fragments resume.
                }
                break;
            default:
                return;
        }
    }

    private void enableMyLocation() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // Permission to access the location is missing.
            PermissionUtils.requestPermission(this, LOCATION_PERMISSION_REQUEST_CODE, Manifest.permission.ACCESS_FINE_LOCATION, true);
        } else if (mMap != null) {
            // Access to the location has been granted to the app.
            mMap.setMyLocationEnabled(true);
        }
    }

    private boolean checkReady() {
        if (mMap == null) {
            ToastUtil.showLong(R.string.map_not_ready);
            return false;
        }
        return true;
    }

    protected Location mLastLocation;

    private AddressResultReceiver mResultReceiver;

    protected void startIntentService(Location location) {
        mResultReceiver = new AddressResultReceiver(new Handler());
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            PermissionUtils.requestPermission(this, LOCATION_PERMISSION_REQUEST_LAST_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION, true);
        }
        LogUtil.d("location=" + location);
        Intent intent = new Intent(this, FetchAddressIntentService.class);
        intent.putExtra(Constant.RECEIVER, mResultReceiver);
        intent.putExtra(Constant.LOCATION_DATA_EXTRA, location);
        startService(intent);
    }

    public static final int LOCATION_UPDATE_MIN_DISTANCE = 10;
    public static final int LOCATION_UPDATE_MIN_TIME = 5000;

    private LocationListener mLocationListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {

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

    private void getCurrentLocation() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            PermissionUtils.requestPermission(this, LOCATION_PERMISSION_REQUEST_LAST_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION, true);
        }
        boolean isGPSEnabled = mLocationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        boolean isNetworkEnabled = mLocationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);

        Location location = null;
        if (!(isGPSEnabled || isNetworkEnabled))
            ToastUtil.showLong("GSP is disable");
        else {
            if (isNetworkEnabled) {
                mLocationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, LOCATION_UPDATE_MIN_TIME, LOCATION_UPDATE_MIN_DISTANCE, mLocationListener);
                location = mLocationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
            }
            if (isGPSEnabled) {
                mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, LOCATION_UPDATE_MIN_TIME, LOCATION_UPDATE_MIN_DISTANCE, mLocationListener);
                location = mLocationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            }
        }
        if (location != null) {
            ToastUtil.showLong("I've got location" + location.getLatitude() + ", " + location.getLongitude());
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(location.getLatitude(), location.getLongitude()), 16));
            startIntentService(location);
        }
    }

    class AddressResultReceiver extends ResultReceiver {

        public AddressResultReceiver(Handler handler) {
            super(handler);
        }

        @Override
        protected void onReceiveResult(int resultCode, Bundle resultData) {
            // Display the address string
            // or an error message sent from the intent service.
            String mAddressOutput = resultData.getString(Constant.RESULT_DATA_KEY);
            if (resultCode == Constant.SUCCESS_RESULT) {
                ToastUtil.showLong("----------aha~~~----------" + mAddressOutput);
            }
        }
    }

    public void moveToCompany(View view) {
        if (!checkReady()) {
            return;
        }
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(COMPANY, 16));
    }

    //根据地址名称来查询
    private Location getLocation(final String address) {
        final Geocoder geocoder = new Geocoder(this, Locale.getDefault());
        Observable observable = Observable.create(new Observable.OnSubscribe<Address>() {
            @Override
            public void call(Subscriber<? super Address> subscriber) {
                try {
                    Location curr = new Location(LocationManager.NETWORK_PROVIDER);
                    //if any latitude is less than -90 or greater than 90
                    //IllegalArgumentException	if any longitude is less than -180 or greater than 180
                    //(LeftLat LeftLong) 左下角   (RightLat RightLong) 右上角
//                    List<Address> addresses = geocoder.getFromLocationName(address, 3,  COMPANY.latitude - 0.003, COMPANY.longitude - 0.006,  COMPANY.latitude + 0.003, COMPANY.longitude + 0.006);
                    List<Address> addresses = geocoder.getFromLocation(COMPANY.latitude, COMPANY.longitude, 1);
//                    List<Address> addresses = geocoder.getFromLocationName(address, 3);
                    for (Address address : addresses) {
                        ArrayList<String> addressFragments = new ArrayList<>();
                        for (int i = 0; i < address.getMaxAddressLineIndex(); i++) {
                            addressFragments.add(address.getAddressLine(i));
                        }
                        Log.d("jack", "-----------" + TextUtils.join(System.getProperty("line.separator"), addressFragments));
                    }
                    Location location = new Location(address);
                    location.setLatitude(addresses.get(0).getLatitude());
                    location.setLongitude(addresses.get(0).getLongitude());
                } catch (Exception e) {

                }
            }
        }).compose(RxSchedulers.io_main());
        observable.subscribe(new Action1<Address>() {
            @Override
            public void call(Address o) {
                ToastUtil.showShort(o.getAddressLine(0) + o.getAddressLine(1));
            }
        });
        return null;
    }
}
*/
