package id.sch.smktelkom_mlg.privateassignment.xirpl621.projectindividu;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by Joice Jessica on 5/14/2017.
 */

public class News_adapter extends RecyclerView.Adapter<News_adapter.ViewHolder> {

    private List<News_list> mainListItems;
    private Context context;

    public News_adapter(List<News_list> mainListItems, Context context) {
        this.mainListItems = mainListItems;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.newslist, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final News_list mainListItem = mainListItems.get(position);

        holder.textViewHead.setText(mainListItem.getHead());
        holder.textViewDesc.setText(mainListItem.getDesc());

        Glide
                .with(context)
                .load("http://image.tmdb.org/t/p/w500" + mainListItem.getImageUrl())
                .into(holder.imageViewOtof);

        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, mainListItem.getHead() + " is opened", Toast.LENGTH_LONG).show();
                Intent singleBlogIntent = new Intent(context, DetailActivity.class);
                singleBlogIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                singleBlogIntent.putExtra("blog_id", position);
                context.startActivity(singleBlogIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mainListItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView textViewHead;
        public TextView textViewDesc;
        public ImageView imageViewOtof;
        public LinearLayout linearLayout;
        public TextView textViewReview;

        public ViewHolder(View itemView) {
            super(itemView);

            textViewHead = (TextView) itemView.findViewById(R.id.textViewHead);
            textViewDesc = (TextView) itemView.findViewById(R.id.textViewDesc);
            imageViewOtof = (ImageView) itemView.findViewById(R.id.imageViewOtof);
            linearLayout = (LinearLayout) itemView.findViewById(R.id.linearLayout);
            textViewReview = (TextView) itemView.findViewById(R.id.textViewReview);

            //textViewHead.setText();
        }
    }
}

