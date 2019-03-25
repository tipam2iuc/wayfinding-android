package e.plass.acceuilwayfinding;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import e.plass.acceuilwayfinding.model.Formation;
import e.plass.acceuilwayfinding.model.Util;


/**
 * A simple {@link Fragment} subclass.
 */
public class DescriptionFormationFragment extends Fragment {
    private TextView description,title;

    public DescriptionFormationFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Formation f = Util.getCurrentFormation();
        View view = inflater.inflate(R.layout.fragment_description_formation, container, false);
        description = view.findViewById(R.id.TextView_description_item_formation);
        title = view.findViewById(R.id.TextView_description_title_item_formation);


        description.setText(f.getDescripetion().trim());
        title.setText(f.getName());
        return view;
    }

}
