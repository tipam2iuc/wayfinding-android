package e.plass.acceuilwayfinding;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import e.plass.acceuilwayfinding.model.Util;


/**
 * A simple {@link android.support.v4.app.Fragment} subclass.
 */
public class SearchFragment extends android.support.v4.app.Fragment {


    public SearchFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Util u = new Util();
        View view = inflater.inflate(R.layout.fragment_search, container, false);

        // Inflate the layout for this fragment
        return view;
    }
}
