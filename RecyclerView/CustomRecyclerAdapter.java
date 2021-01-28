package net.apptronix.umang.first.recyclerview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.List;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.toolbox.ImageLoader;


public class CustomRecyclerAdapter extends RecyclerView.Adapter<CustomRecyclerAdapter.ViewHolder> {

    Context context;

    List<Utils> getVideoAdapter;

    ImageLoader imageLoader;

    private ItemClickListener clickListener;

    public CustomRecyclerAdapter(List<Utils> getVideoAdapter, Context context){

        super();

        this.getVideoAdapter = getVideoAdapter;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = null;
        CustomRecyclerAdapter.ViewHolder vh=null;
        v = LayoutInflater.from(parent.getContext()).inflate(R.layout.items, parent, false);
        vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public int getItemViewType(int position) {
        if(getVideoAdapter.get(position)==null)
            return 0;
        return 1;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Utils getVideoAdapter1 =  getVideoAdapter.get(position);

        holder.NameTextView.setText(getVideoAdapter1.getName());

        holder.LinkTextView.setText(String.valueOf(getVideoAdapter1.getLink()));

    }

    @Override
    public int getItemCount() {

        return getVideoAdapter.size();
    }

    public void setClickListener(ItemClickListener itemClickListener) {
        this.clickListener = itemClickListener;
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public TextView NameTextView,LinkTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            NameTextView = itemView.findViewById(R.id.username) ;
            LinkTextView = itemView.findViewById(R.id.password) ;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (clickListener != null) clickListener.onClick(view, getAdapterPosition());
        }
    }
}
