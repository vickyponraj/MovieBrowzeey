package com.hexcreators.moviebrowzeey.MovieList;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.hexcreators.moviebrowzeey.Data.Model.Results;
import com.hexcreators.moviebrowzeey.R;

import java.util.ArrayList;
import java.util.List;

public class MovieListAdapter extends RecyclerView.Adapter<MovieListAdapter.ViewHolder> {

    private List<Results> movies = new ArrayList<>();
    private Callbacks callbacks;
    private int mode = 0;

    public MovieListAdapter(Callbacks callbacks) {
        this.callbacks = callbacks;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        int item_layout = mode == 0 ? R.layout.item_list_movie : R.layout.item_suggest_movie;
        View rootView = LayoutInflater.from(parent.getContext()).inflate(item_layout, parent, false);
        return new ViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bindView(movies.get(position));
    }

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

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tv_movie_name;
        private ImageView iv_movie_poster;

        public ViewHolder(View itemView) {
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


    interface Callbacks {

        void onItemClick(Results results);
    }
}
