package com.hexcreators.moviebrowzeey.Data.Model;

import java.util.List;

public class Movie {

    private Integer budget;

    private Double vote_average;

    private String backdrop_path;

    private List<Genres> genres;

    private String status;

    private Integer runtime;

    private List<Spoken_languages> spoken_languages;

    private Boolean adult;

    private String homepage;

    private Integer id;

    private List<Production_countries> production_countries;

    private String title;

    private String original_language;

    private String overview;

    private List<Production_companies> production_companies;

    private Object belongs_to_collection;

    private String imdb_id;

    private String release_date;

    private String original_title;

    private Integer vote_count;

    private String poster_path;

    private Boolean video;

    private String tagline;

    private Integer revenue;

    private Double popularity;


    public Integer getBudget() {
        return budget;
    }

    public void setBudget(Integer budget) {
        this.budget = budget;
    }

    public Double getVote_average() {
        return vote_average;
    }

    public void setVote_average(Double vote_average) {
        this.vote_average = vote_average;
    }

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public void setBackdrop_path(String backdrop_path) {
        this.backdrop_path = backdrop_path;
    }

    public List<Genres> getGenres() {
        return genres;
    }

    public void setGenres(List<Genres> genres) {
        this.genres = genres;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getRuntime() {
        return runtime;
    }

    public void setRuntime(Integer runtime) {
        this.runtime = runtime;
    }

    public List<Spoken_languages> getSpoken_languages() {
        return spoken_languages;
    }

    public void setSpoken_languages(List<Spoken_languages> spoken_languages) {
        this.spoken_languages = spoken_languages;
    }

    public Boolean getAdult() {
        return adult;
    }

    public void setAdult(Boolean adult) {
        this.adult = adult;
    }

    public String getHomepage() {
        return homepage;
    }

    public void setHomepage(String homepage) {
        this.homepage = homepage;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Production_countries> getProduction_countries() {
        return production_countries;
    }

    public void setProduction_countries(List<Production_countries> production_countries) {
        this.production_countries = production_countries;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOriginal_language() {
        return original_language;
    }

    public void setOriginal_language(String original_language) {
        this.original_language = original_language;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public List<Production_companies> getProduction_companies() {
        return production_companies;
    }

    public void setProduction_companies(List<Production_companies> production_companies) {
        this.production_companies = production_companies;
    }

    public Object getBelongs_to_collection() {
        return belongs_to_collection;
    }

    public void setBelongs_to_collection(Object belongs_to_collection) {
        this.belongs_to_collection = belongs_to_collection;
    }

    public String getImdb_id() {
        return imdb_id;
    }

    public void setImdb_id(String imdb_id) {
        this.imdb_id = imdb_id;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public String getOriginal_title() {
        return original_title;
    }

    public void setOriginal_title(String original_title) {
        this.original_title = original_title;
    }

    public Integer getVote_count() {
        return vote_count;
    }

    public void setVote_count(Integer vote_count) {
        this.vote_count = vote_count;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public Boolean getVideo() {
        return video;
    }

    public void setVideo(Boolean video) {
        this.video = video;
    }

    public String getTagline() {
        return tagline;
    }

    public void setTagline(String tagline) {
        this.tagline = tagline;
    }

    public Integer getRevenue() {
        return revenue;
    }

    public void setRevenue(Integer revenue) {
        this.revenue = revenue;
    }

    public Double getPopularity() {
        return popularity;
    }

    public void setPopularity(Double popularity) {
        this.popularity = popularity;
    }
}
