package net.apptronix.umang.first.myapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

/**
 * Created by umang on 22-06-2018.
 */

public class Tab1 extends Fragment {
    TextView t1;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.tab1,null);
        t1=(TextView)v.findViewById(R.id.textView);
        Toast.makeText(getActivity(), "Hii", Toast.LENGTH_SHORT).show();
        return v;
    }
}
