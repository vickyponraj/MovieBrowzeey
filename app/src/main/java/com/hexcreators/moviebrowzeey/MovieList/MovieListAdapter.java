package com.hexcreators.moviebrowzeey.MovieList;

import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.hexcreators.moviebrowzeey.Data.Model.Results;
import com.hexcreators.moviebrowzeey.R;

import java.util.ArrayList;
import java.util.List;

public class MovieListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Results> movies = new ArrayList<>();
    private Callbacks callbacks;
    private int mode = 0;

    public MovieListAdapter(Callbacks callbacks) {
        this.callbacks = callbacks;
    }

    @Override
    public int getItemViewType(int position) {
        return mode;
    }

    @NonNull
    @Override
    public  RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        int item_layout = viewType == 0 ? R.layout.item_list_movie : R.layout.item_suggest_movie;
        if(viewType==0){
            View rootView = LayoutInflater.from(parent.getContext()).inflate(item_layout, parent, false);
            return new ListViewHolder(rootView);
        }else {
            View rootView = LayoutInflater.from(parent.getContext()).inflate(item_layout, parent, false);
            return new CardViewHolder(rootView);
        }

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if(mode==0){
            ((ListViewHolder) holder).bindView(movies.get(position));
        }else {
            ((CardViewHolder) holder).bindView(movies.get(position));
        }
    }

    /*@Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bindView(movies.get(position));
    }*/

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public void setMovies(List<Results> movies) {
        this.movies = movies;
        notifyDataSetChanged();
    }

    public int getMode() {
        return mode;
    }

    public void setMode(int mode) {
        this.mode = mode;
    }

    private class CardViewHolder extends RecyclerView.ViewHolder {

        private TextView tv_movie_name;
        private ImageView iv_movie_poster;
        private CardView cv_root;
        private RelativeLayout rl_trans;

        public CardViewHolder(View itemView) {
            super(itemView);
            tv_movie_name   = itemView.findViewById(R.id.tv_movie_name);
            iv_movie_poster = itemView.findViewById(R.id.iv_movie_poster);
            cv_root         = itemView.findViewById(R.id.cv_root);
            rl_trans        = itemView.findViewById(R.id.rl_trans);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    callbacks.onItemClick(movies.get(getAdapterPosition()));
                }
            });
        }

        public void bindView(Results results) {
            cv_root.setVisibility(View.VISIBLE);
            rl_trans.setVisibility(View.GONE);
            Glide.with(itemView.getContext())
                    .load("https://image.tmdb.org/t/p/w500/" + results.getPoster_path())
                    .apply(new RequestOptions().centerCrop())
                    .into(iv_movie_poster);
            tv_movie_name.setText(results.getTitle());
        }
    }

    private class ListViewHolder extends RecyclerView.ViewHolder {

        private TextView tv_movie_name;
        private ImageView iv_movie_poster;

        public ListViewHolder(View itemView) {
            super(itemView);
            tv_movie_name = itemView.findViewById(R.id.tv_movie_name);
            iv_movie_poster = itemView.findViewById(R.id.iv_movie_poster);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    callbacks.onItemClick(movies.get(getAdapterPosition()));
                }
            });
        }

        public void bindView(Results results) {
            Glide.with(itemView.getContext())
                    .load("https://image.tmdb.org/t/p/w500/" + results.getPoster_path())
                    .apply(new RequestOptions().centerCrop())
                    .into(iv_movie_poster);
            tv_movie_name.setText(results.getTitle());
        }
    }


   public interface Callbacks {
        void onItemClick(Results results);
    }
}
