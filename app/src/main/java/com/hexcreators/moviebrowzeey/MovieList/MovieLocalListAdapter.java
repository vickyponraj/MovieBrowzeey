package com.hexcreators.moviebrowzeey.MovieList;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;
import com.hexcreators.moviebrowzeey.Data.Model.Movie;
import com.hexcreators.moviebrowzeey.Data.Model.Results;
import com.hexcreators.moviebrowzeey.R;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

public class MovieLocalListAdapter extends RecyclerView.Adapter<MovieLocalListAdapter.ViewHolder> {

    private List<Movie> movieList = new ArrayList<>();
    private Callback callback;

    public MovieLocalListAdapter(Callback callback) {
        this.callback = callback;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(parent.getContext()).inflate(R.layout._item_local_movie, parent, false);
        return new ViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bindData(movieList.get(position));
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    public void setData(List<Movie> data) {
        this.movieList = data;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView iv_movie_poster;
        private TextView tv_movie_name;
        private TextView tv_movie_disc;

        public ViewHolder(View itemView) {
            super(itemView);
            iv_movie_poster = itemView.findViewById(R.id.iv_movie_poster);
            tv_movie_name = itemView.findViewById(R.id.tv_movie_name);
            tv_movie_disc = itemView.findViewById(R.id.tv_movie_disc);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (callback != null){
                        callback.onClickMovie(movieList.get(getAdapterPosition()).getId());
                    }
                }
            });
        }

        public void bindData(Movie movie) {
            Glide.with(itemView.getContext())
                    .load("https://image.tmdb.org/t/p/w500/" + movie.getPoster_path())
                    .apply(new RequestOptions().centerCrop())
                    .transition(DrawableTransitionOptions.withCrossFade(1000))
                    .into(iv_movie_poster);
            tv_movie_name.setText(movie.getTitle());
            tv_movie_disc.setText(movie.getOverview());
        }
    }


    public interface Callback {

        void onClickMovie(Integer movie_id);
    }
}
