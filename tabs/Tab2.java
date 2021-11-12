package umang.kejriwal.tabs;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class Tab2 extends Fragment {
    TextView t1;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.tab2,null);
        t1 = v.findViewById(R.id.section_label);
        Toast.makeText(getActivity(),"Tab 2 is clicked",Toast.LENGTH_SHORT).show();
        return v;
    }
}
