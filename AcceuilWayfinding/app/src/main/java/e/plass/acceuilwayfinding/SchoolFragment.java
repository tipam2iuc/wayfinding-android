package e.plass.acceuilwayfinding;


import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

import e.plass.acceuilwayfinding.model.Ecole;
import e.plass.acceuilwayfinding.model.SchoolAdapter;
import e.plass.acceuilwayfinding.model.Util;
import edmt.dev.advancednestedscrollview.AdvancedNestedScrollView;
import edmt.dev.advancednestedscrollview.MaxHeightRecyclerView;


/**
 * A simple {@link android.support.v4.app.Fragment} subclass.
 */
public class SchoolFragment extends android.support.v4.app.Fragment implements OnMapReadyCallback {
    private              LocationManager             lm;
    private static final int                         PERMS_CALL_ID = 10;
    private              FusedLocationProviderClient fusedLocationProviderClient;
    private              LatLng                      lastLocation;

    private SupportMapFragment mapFragment;
    private GoogleMap          googleMap;
    private View               fragment;

    private ArrayList<Ecole> ecoles = new ArrayList<>();
    private Context          context;
    private MaxHeightRecyclerView recyclerView;
    private LinearLayoutManager ln;

    private View view;

    private boolean isShowingCardHeaderShadow;


    public SchoolFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ecoles = new ArrayList<>();
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_school, container, false);

        recyclerView = view.findViewById(R.id.card_recycler_view);
        ln = new LinearLayoutManager(getContext());
        fragment = view.findViewById(R.id.map_school);
        mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map_school);
        if (mapFragment == null) {
            FragmentManager     fragmentManager     = getFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            mapFragment = SupportMapFragment.newInstance();
            fragmentTransaction.replace(R.id.map_school, mapFragment).commit();
        }
        mapFragment.getMapAsync(this);


        Util u = new Util();
        ecoles.addAll(Util.getEcoles());
        initRecycleView();
        final View cardheaderShadow = view.findViewById(R.id.card_header_shadow);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                boolean isRecyclerViewScrolledTop = ln.findFirstVisibleItemPosition() == 0
                        && ln.findViewByPosition(0).getTop() == 0;
                if(!isRecyclerViewScrolledTop && !isShowingCardHeaderShadow){
                    isShowingCardHeaderShadow = true;
                     showOrHideView(cardheaderShadow,isShowingCardHeaderShadow);
                }else{
                    isShowingCardHeaderShadow = false;
                    showOrHideView(cardheaderShadow,isShowingCardHeaderShadow);
                }
            }
        });
        AdvancedNestedScrollView advancedNestedScrollView = view.findViewById(R.id.nested_scroll_view);
        advancedNestedScrollView.setOverScrollMode(View.OVER_SCROLL_ALWAYS);
        advancedNestedScrollView.setOnScrollChangeListener((NestedScrollView.OnScrollChangeListener) (nestedScrollView, scrollX, scrollY, olscrollX, olscrollY) -> {
            recyclerView.scrollToPosition(0);
            cardheaderShadow.setAlpha(0f);
            isShowingCardHeaderShadow = false;
        });
        return view;
    }

    private void showOrHideView(View view, boolean isShow) {
        view.animate().alpha(isShow?1f:0f).setDuration(100).setInterpolator(new DecelerateInterpolator());

    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.nav_items, null);
    }

    public void initRecycleView() {
        SchoolAdapter custumAdapter = new SchoolAdapter(getContext(), ecoles);
        recyclerView.setAdapter(custumAdapter);
        recyclerView.setLayoutManager(ln);
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), ln.getOrientation()));
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            return;
        }





        try {
            // Customise the styling of the base map using a JSON object defined
            // in a raw resource file.
            boolean success = googleMap.setMapStyle(
                    MapStyleOptions.loadRawResourceStyle(
                            getContext(), R.raw.mapstyle));

            if (!success) {
                Log.e("SchoolFragment", "Style parsing failed.");
            }
        } catch (Resources.NotFoundException e) {
            Log.e("SchoolFragment", "Can't find style. Error: ", e);
        }

        MapsInitializer.initialize(getContext());
        this.googleMap = googleMap;
        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

        LatLng latLng = new LatLng(4.0837159, 9.7804508 );


        for (Ecole e :
                Util.getEcoles()) {
            googleMap.addMarker(new MarkerOptions()
                    .position(e.getPosition())
                    .title(e.getName())
                    .snippet("Position : "+e.getPosition().latitude + "," + e.getPosition().longitude)
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE))
                    .flat(true));
        }
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Util.getEcoles().get(2).getPosition(), 6f));
        this.googleMap.setOnMapClickListener(this::onMapClick);
    }

    private void onMapClick(LatLng latLng1) {
        Intent i = new Intent(getContext(), SchoolMapsActivity.class);
//        ActivityOptions options = ActivityOptions
//                .makeSceneTransitionAnimation(getActivity(), fragment, "robot");
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
//            startActivity(i, options.toBundle());
//        } else {
            startActivity(i);
        //}
    }
}
