package umang.kejriwal.clouddatabase;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class MyAdapter extends ArrayAdapter<String> {

    ArrayList<String> name,mobile;
    Activity activity;

    public MyAdapter(Activity act, ArrayList<String> na, ArrayList<String> mob) {
        super(act, R.layout.items,na);
        this.activity = act;
        this.name = na;
        this.mobile = mob;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = activity.getLayoutInflater();
        View root = inflater.inflate(R.layout.items,null,true);
        TextView t1 = root.findViewById(R.id.textView2);
        TextView t2 = root.findViewById(R.id.textView3);
        t1.setText(name.get(position));
        t2.setText(mobile.get(position));
        return root;
    }
}
