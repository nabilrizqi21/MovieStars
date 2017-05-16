package id.sch.smktelkom_mlg.privateassignment.xirpl621.projectindividu;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Joice Jessica on 5/14/2017.
 */

public class RecommendAdapter extends RecyclerView.Adapter<RecommendAdapter.ViewHolder> {

    List<RecommendListItem> offlineListItems;
    Context context;


    public RecommendAdapter(ArrayList<RecommendListItem> offlineListItems, Context context) {
        this.offlineListItems = offlineListItems;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recommend_list_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        RecommendListItem place = offlineListItems.get(position);
        holder.textViewHead.setText(place.head);
        holder.imageViewOtof.setImageURI(Uri.parse(place.imageUrl));

    }

    @Override
    public int getItemCount() {

        return offlineListItems.size();

    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView textViewHead;
        public ImageView imageViewOtof;
        public LinearLayout linearLayout;

        public ViewHolder(View itemView) {
            super(itemView);

            textViewHead = (TextView) itemView.findViewById(R.id.textViewHead);
            imageViewOtof = (ImageView) itemView.findViewById(R.id.imageViewOtof);
            linearLayout = (LinearLayout) itemView.findViewById(R.id.linearLayout);
        }
    }
}

