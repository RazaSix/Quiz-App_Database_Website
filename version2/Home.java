package magic3q.version2;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Home extends AppCompatActivity {

    private double longitude;
    private double latitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        Button next = (Button) findViewById(R.id.button);

        next.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                // Checking that GPS is enabled
                LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
                if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {

                    // Allow user to enable if GPS not enabled
                    alertGPScheck();
                } else {

                    try {
                        LocationListener locationListener = new FindLocation();
                        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 10, locationListener);


                        GlobalClass getLatLong = (GlobalClass) getApplicationContext();
                        double lati = getLatLong.getLatitude();
                        double longi = getLatLong.getLongitude();


                        //Displays Current Lat/Long to screen using toast- For Test Purposes
                        //String coordDisplay = "1-Latitude is "+lati+" Longitude is "+longi;
                        //Toast.makeText(getApplicationContext(),coordDisplay,Toast.LENGTH_LONG).show();



                        if(52.249874 < latitude && latitude  < 53.251119 && -0.893409 < longitude && longitude < -0.887372){
                            Toast.makeText(getApplicationContext(),"Within Uni Limits",Toast.LENGTH_LONG).show();

                            // To QLoad for manual Questionnaire ID input
                            //startActivity(new Intent(Home.this, QLoad.class));

                            // To QRScanner
                            startActivity(new Intent(Home.this, QRead.class));
                        }
                        else{
                            Toast.makeText(getApplicationContext(),"Not Within Uni Limits",Toast.LENGTH_LONG).show();
                        }

                    }catch(SecurityException se){
                        se.printStackTrace();
                        Toast.makeText(getApplicationContext(),"Please Enable GPS Permission",Toast.LENGTH_LONG).show();
                    }


                }


            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    private class FindLocation implements LocationListener {

        public void onLocationChanged(Location location) {
            latitude = location.getLatitude();
            longitude = location.getLongitude();

            GlobalClass setLatLong = (GlobalClass) getApplicationContext();
            setLatLong.setLatitude(latitude);
            setLatLong.setLongitude(longitude);

            /*
            double lat = location.getLatitude();
            double longi = location.getLongitude();

            String testDisplay = "2Latitude is "+lat+" Longitude is "+longi;
            Toast.makeText(getApplicationContext(),testDisplay,Toast.LENGTH_LONG).show();
             */


        }

        @Override
        public void onStatusChanged(String s, int i, Bundle bundle) {

        }

        @Override
        public void onProviderEnabled(String s) {

        }

        @Override
        public void onProviderDisabled(String s) {

        }


    }




    private void alertGPScheck() {
        final AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        dialogBuilder.setMessage("Do you want to enable GPS?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(@SuppressWarnings("unused") DialogInterface dialogInterface, int i) {
                        startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(@SuppressWarnings("unused") DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
        final AlertDialog alertDialog = dialogBuilder.create();
        alertDialog.show();
    }
}
