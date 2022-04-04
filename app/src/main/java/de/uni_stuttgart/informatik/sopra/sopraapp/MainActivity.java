package de.uni_stuttgart.informatik.sopra.sopraapp;

import android.Manifest;
import android.app.SearchManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Color;
import android.location.Location;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.PolygonOptions;

import java.util.ArrayList;
import java.util.Locale;

/**
 * Class handle the whole app, in this class is a navigation drawer, so you can navigate to all sub-menus
 *
 * also the map will be regulated here. If a sub-menu wants to do operations on the map, it will call this activity
 *
 * @author Patrick Koss
 * @author Sarah Gutierrez Muñoz
 * @author Michael Hersam
 */
public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {

    public static ImageButton location;
    private DrawerLayout mDrawer;
    private Toolbar toolbar;
    private NavigationView nvDrawer;
    public static GoogleMap mMap;
    ActionBarDrawerToggle drawerToggle;
    public static int userId;
    public static Feld[] felder;
    public static Schadensfall[] schadensfall;
    double lat;
    double lng;
    private TextView navHeaderUserNameText;
    private TextView navHeaderUserIdText;
    public static ArrayList<Polygon> polygons = new ArrayList<>();
    //public static MapView mapView;
    //public static IMapController mapController;

    /**
     * Creates the fragment instance
     * method is called when fragment is created
     *
     * @param savedInstanceState previous instance
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //set layout for content view
        setContentView(R.layout.activity_main);
        Bundle extras = getIntent().getExtras();
        userId = extras.getInt("userID");

        //read from the preferences and get the correct language for display
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(this);
        Configuration config = getBaseContext().getResources().getConfiguration();

        String lang = settings.getString("LANG", "");
        if (! "".equals(lang) && ! config.locale.getLanguage().equals(lang)) {
            Locale locale = new Locale(lang);
            Locale.setDefault(locale);
            config.locale = locale;
            getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
        }

        //set toolbar title
        setTitle(getString(R.string.title_activity_login));

        //user is getting ask if he want to allow the location permission
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 123);

        //checks for first run and gives user a heads up for map usage
        checkFirstRun();



        //get screen with and height
        DisplayMetrics displayMetrics = new DisplayMetrics();
        WindowManager wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        wm.getDefaultDisplay().getMetrics(displayMetrics);
        int screenWidth = displayMetrics.widthPixels;
        int screenHeight = displayMetrics.heightPixels;

        //location, gps button
        location = findViewById(R.id.imageButton);

        //location.setX(screenWidth - screenWidth / 7);
        //location.setY(screenHeight - screenHeight / 5);

        location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GPSTracker g = new GPSTracker(getApplicationContext());
                Location l = g.getLocation();
                if (l != null) {
                    lat = l.getLatitude();
                    lng = l.getLongitude();
                }
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(lat, lng), 16));
            }
        });



        // Set a Toolbar to replace the ActionBar
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Find our drawer view
        mDrawer = findViewById(R.id.drawer_layout);
        drawerToggle = setupDrawerToggle();


        // Tie DrawerLayout events to the ActionBarToggle
        mDrawer.addDrawerListener(drawerToggle);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used
        nvDrawer = findViewById(R.id.nvView);
        setupDrawerContent(nvDrawer);

        /*set menu item title dynamically according to current locale*/
        //get our menu
        Menu menu = nvDrawer.getMenu();

        //get menu items
        MenuItem nav_felder = menu.findItem(R.id.nav_felder_fragment);
        MenuItem nav_schaden = menu.findItem(R.id.nav_schadensmeldungen_fragment);
        MenuItem nav_vertrag = menu.findItem(R.id.nav_vertraege_fragment);
        MenuItem nav_account = menu.findItem(R.id.nav_account_fragment);
        MenuItem nav_hilfe = menu.findItem(R.id.nav_hilfe_fragment);
        MenuItem nav_info = menu.findItem(R.id.nav_appInfo_fragment);
        MenuItem nav_logout = menu.findItem(R.id.nav_logout_fragment);

        //set menu item title
        nav_felder.setTitle(getString(R.string.item_feld));
        nav_schaden.setTitle(getString(R.string.item_dmg));
        nav_vertrag.setTitle(getString(R.string.item_contract));
        nav_account.setTitle(getString(R.string.item_acc));
        nav_hilfe.setTitle(getString(R.string.item_help));
        nav_info.setTitle(getString(R.string.item_app));
        nav_logout.setTitle(getString(R.string.item_logout));


        //Create the map
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        //set user data in navDrawer
        View navHeader = nvDrawer.getHeaderView(0);
        navHeaderUserIdText = (TextView) navHeader.findViewById(R.id.nav_headerText);
        navHeaderUserNameText = (TextView) navHeader.findViewById(R.id.nav_headermail);
        navHeaderUserIdText.setText("ID: " + String.valueOf(userId));
        navHeaderUserNameText.setText(extras.getString("userFullName"));
        if(extras.getString("role").equals("Reviewer")||extras.getString("role").equals("Gutachter")) {
            userId=-1;
        }

    }


    /*
     * method handle which item is select on the navigation drawer
     *
     * @param item
     * @return
     */
    /*@Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }*/


    /**
     * method sets up the content of the navigation drawer dynamically
     *
     * @param navigationView
     */
    private void setupDrawerContent(NavigationView navigationView) {
        Log.e("Drawer", "setup drawer content");
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                        selectDrawerItem(menuItem);
                        return true;
                    }
                });
    }

    /**
     * method handle what a selected menu point will do
     *
     * @param menuItem that was selected
     */
    public void selectDrawerItem(MenuItem menuItem) {
        Fragment fragment = null;
        Class fragmentClass;
        switch (menuItem.getItemId()) {
            case R.id.nav_start:
                fragmentClass = Maps.class;
                drawPolygons(getApplicationContext());
                //set gps button visible
                if (MainActivity.location.getVisibility() == View.INVISIBLE) {
                    location.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.nav_felder_fragment:
                fragmentClass = FeldFragment.class;
                break;
            case R.id.nav_schadensmeldungen_fragment:
                fragmentClass = SchadensfallFragment.class;
                break;
            case R.id.nav_vertraege_fragment:
                fragmentClass = VertragFragment.class;
                break;
            case R.id.nav_account_fragment:
                fragmentClass = AccountFragment.class;
                break;
            case R.id.nav_hilfe_fragment:
                fragmentClass = HilfeFragment.class;
                break;
            case R.id.nav_appInfo_fragment:
                fragmentClass = AppInfoFragment.class;
                break;
            case R.id.nav_logout_fragment:
                fragmentClass = LogOutFragment.class;
                mDrawer.closeDrawers();
                showDialog();
                return;
            default:
                fragmentClass = FeldFragment.class;
        }

        try {
            fragment = (Fragment) fragmentClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

        //open the new Fragment
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();

        ft.replace(R.id.flContent, fragment).addToBackStack("tag").commit();

        // Set action bar title
        setTitle(menuItem.getTitle());

        // Highlight the selected item has been done by NavigationView
        menuItem.setChecked(true);

        // Close the navigation drawer
        mDrawer.closeDrawers();
    }

    /**
     * method sets up the drawer toggle
     *
     * @return
     */
    private ActionBarDrawerToggle setupDrawerToggle() {
        // NOTE: Make sure you pass in a valid toolbar reference.  ActionBarDrawToggle() does not require it
        // and will not render the hamburger icon without it.
        return new ActionBarDrawerToggle(this, mDrawer, toolbar, R.string.drawer_open, R.string.drawer_close);
    }

    /**
     * method handle what the application does when the navigation drawer is open
     *
     * @param savedInstanceState
     */
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        drawerToggle.syncState();
    }

    /*
     * method sets new configuration
     * after changes did occur (orientation etc)
     * @param newConfig
     */
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        setTitle(getString(R.string.title_activity_login));

        //LocaleManager.setLocale(this);
    }

    /**
     * method opens a google map
     *
     * @param googleMap
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        drawPolygons(getApplicationContext());
    }

    /**
     * method draws the fields from the coordinates out of the database and set Polygon onClickListener
     *
     * @param context
     */
    public static void drawPolygons(final Context context) {
        mMap.clear();
        final DatenbankDaten db = new DatenbankDaten(context);
        db.open();
        felder = db.getFeld();
        schadensfall = db.getSchadensfall();
        ArrayList<LatLng> triangle = new ArrayList<>();
        //ArrayList<Polygon> polygons = new ArrayList<>();
        polygons.removeAll(polygons);
        if (userId >= 0) {
            for (int j = 0; j < schadensfall.length; j++) {
                if (userId == schadensfall[j].getFeld().getVertrag().getPerson().getId()) {
                    for (int i = 0; i < schadensfall[j].getSchadensposition().length; i++) {
                        triangle.add(schadensfall[j].getSchadensposition()[i]);
                    }
                    Polygon polygon = mMap.addPolygon(new PolygonOptions()
                            .addAll(triangle)
                            .fillColor(Color.parseColor("#66ff0000"))
                            .strokeColor(Color.parseColor("#66ff0000"))
                            .strokeWidth(5));
                    polygon.setClickable(true);
                    polygons.add(polygon);
                    triangle.removeAll(triangle);
                }
            }
            for (int j = 0; j < felder.length; j++) {
                if (userId == felder[j].getVertrag().getPerson().getId()) {
                    for (int i = 0; i < felder[j].getKoordinaten().length; i++) {
                        triangle.add(felder[j].getKoordinaten()[i]);
                    }
                    Polygon polygon = mMap.addPolygon(new PolygonOptions()
                            .addAll(triangle)
                            .fillColor(Color.parseColor("#660000ff"))
                            .strokeColor(Color.parseColor("#660000ff"))
                            .strokeWidth(5));
                    polygon.setClickable(true);
                    polygons.add(polygon);
                    triangle.removeAll(triangle);
                }
            }

            for (int j = 0; j < felder.length; j++) {
                if (userId == felder[j].getVertrag().getPerson().getId()) {
                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(felder[j].getKoordinaten()[0].latitude, felder[j].getKoordinaten()[0].longitude), 16));
                    break;
                }
            }
        } else {
            for (int j = 0; j < schadensfall.length; j++) {
                for (int i = 0; i < schadensfall[j].getSchadensposition().length; i++) {
                    triangle.add(schadensfall[j].getSchadensposition()[i]);
                }
                Polygon polygon = mMap.addPolygon(new PolygonOptions()
                        .addAll(triangle)
                        .fillColor(Color.parseColor("#66ff0000"))
                        .strokeColor(Color.parseColor("#66ff0000"))
                        .strokeWidth(5));
                polygon.setClickable(true);
                polygons.add(polygon);
                triangle.removeAll(triangle);
            }
            for (int j = 0; j < felder.length; j++) {
                for (int i = 0; i < felder[j].getKoordinaten().length; i++) {
                    triangle.add(felder[j].getKoordinaten()[i]);
                }
                Polygon polygon = mMap.addPolygon(new PolygonOptions()
                        .addAll(triangle)
                        .fillColor(Color.parseColor("#660000ff"))
                        .strokeColor(Color.parseColor("#660000ff"))
                        .strokeWidth(5));
                polygon.setClickable(true);
                polygons.add(polygon);
                triangle.removeAll(triangle);
            }

            for (int j = 0; j < felder.length; j++) {
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(felder[j].getKoordinaten()[0].latitude, felder[j].getKoordinaten()[0].longitude), 16));
                break;
            }

            //setPosition if there are no fields
            Feld[] felder = db.getFeld();
            ArrayList<Feld> userFelder = new ArrayList<Feld>();
            //search for the fields the user has registered
            for (int i = 0; i < felder.length; i++) {
                if(userId>=0){
                    if (userId == felder[i].getVertrag().getPerson().getId())
                        userFelder.add(felder[i]);
                }
                else{
                    userFelder.add(felder[i]);
                }
            }
            if(userFelder.size()==0){
                location.performClick();
            }
        }
        db.close();
        mMap.setOnPolygonClickListener(new GoogleMap.OnPolygonClickListener() {
            @Override
            public void onPolygonClick(Polygon polygon) {
                for (int j = 0; j < felder.length; j++) {
                    if (userId == felder[j].getVertrag().getPerson().getId()) {
                        if (felder[j].getKoordinaten()[0].latitude == polygon.getPoints().get(0).latitude)
                            Toast.makeText(context, context.getResources().getString(R.string.list_data_header) + felder[j].getId() + ", " + Double.toString(felder[j].getFlaeche()) + " ha", Toast.LENGTH_SHORT).show();
                    } else {
                        if (felder[j].getKoordinaten()[0].latitude == polygon.getPoints().get(0).latitude)
                            Toast.makeText(context, context.getResources().getString(R.string.list_data_header) + felder[j].getId() + ", " + Double.toString(felder[j].getFlaeche()) + " ha", Toast.LENGTH_SHORT).show();
                    }
                }
                for (int j = 0; j < schadensfall.length; j++) {
                    if (userId == schadensfall[j].getFeld().getVertrag().getPerson().getId()) {
                        if (schadensfall[j].getSchadensposition()[0].latitude == polygon.getPoints().get(0).latitude)
                            Toast.makeText(context, context.getResources().getString(R.string.s_list_data_header) + ", " + Double.toString(schadensfall[j].getFlaeche()) + " ha", Toast.LENGTH_SHORT).show();
                    } else {
                        if (schadensfall[j].getSchadensposition()[0].latitude == polygon.getPoints().get(0).latitude)
                            Toast.makeText(context, context.getResources().getString(R.string.s_list_data_header) + ", " + Double.toString(schadensfall[j].getFlaeche()) + " ha", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    /**
     * Method to handle user interaction in logout dialog
     */
    public void logoutUser() {
        final Intent loginActivityIntent = new Intent(this, LoginActivity.class);
        startActivity(loginActivityIntent);
        finish();
    }

    /**
     * Method to handle user interaction in logout dialog
     */
    public void negativeClickLogoutDialog() {
        //nothing to do here for the moment
        getFragmentManager().popBackStack();
    }

    /**
     * shows the logout dialog
     */
    void showDialog() {
        DialogFragment newFragment = LogOutFragment.newInstance();
        newFragment.show(getSupportFragmentManager(), "Dialog");

    }


    /**
     * inflates menu and enables the search icon + functionality to start
     *
     * @param menu current menu
     * @return boolean true
     */
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.options_menu, menu);

        // Associate searchable configuration with the SearchView
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);

        //set search icon as search start indicator
        SearchView searchView = (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));


        return true;
    }


    /* checks if the device has an active internet connection
     *
     * @return boolean whether there is an internet connection (true) or not (false)
     */
    public boolean isOnline() {
        ConnectivityManager cm =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnected();
    }


    /**
     * checks if the app runs for the very first time
     *
     * show dialogue that warns user of the missing map functionality
     */
    public void checkFirstRun() {
        boolean isFirstRun = getSharedPreferences("PREFERENCE", MODE_PRIVATE).getBoolean("isFirstRun", true);
        //first run check
        if (isFirstRun){
            //user warning to initiate an internet connection
            if(isOnline() != true) {
                final AlertDialog.Builder alertInternet = new AlertDialog.Builder(MainActivity.this);
                alertInternet.setMessage(getString(R.string.no_internet));
                alertInternet.setCancelable(false); //must read so no cancel
                alertInternet.setNeutralButton(getString(R.string.button_okay), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int btn2) {
                        dialogInterface.dismiss(); //simple dismiss
                    }
                });
                alertInternet.show();
            }

            //set boolean false, so dialogue doesn't show again
            getSharedPreferences("PREFERENCE", MODE_PRIVATE)
                    .edit()
                    .putBoolean("isFirstRun", false)
                    .apply();
        }
    }



}
