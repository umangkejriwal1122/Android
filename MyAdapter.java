package umang.kejriwal.clouddatabase;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class MyAdapter extends ArrayAdapter<String> {
    Activity myactivity;
    ArrayList<String> name;
    ArrayList<String> age;

    public MyAdapter(Activity context, ArrayList<String> myname, ArrayList<String> myage) {
        super(context, R.layout.items,myname);
        this.myactivity = context;
        this.name = myname;
        this.age = myage;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = myactivity.getLayoutInflater();
        View root = inflater.inflate(R.layout.items,null,true);
        TextView t1 = root.findViewById(R.id.textView);
        TextView t2 = root.findViewById(R.id.textView2);
        t1.setText(name.get(position));
        t2.setText(age.get(position));
        return root;
    }
}
