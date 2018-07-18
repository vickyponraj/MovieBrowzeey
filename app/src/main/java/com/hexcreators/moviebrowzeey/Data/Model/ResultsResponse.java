package com.hexcreators.moviebrowzeey.Data.Model;

import java.util.List;

import io.realm.RealmList;
import io.realm.RealmObject;

public class ResultsResponse extends RealmObject {

    private RealmList<Results> results;

    private Integer page;

    private Integer total_pages;

    private Integer total_results;


    public RealmList<Results> getResults() {
        return results;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getTotal_pages() {
        return total_pages;
    }

    public void setTotal_pages(Integer total_pages) {
        this.total_pages = total_pages;
    }

    public Integer getTotal_results() {
        return total_results;
    }

    public void setTotal_results(Integer total_results) {
        this.total_results = total_results;
    }

    public void setResults(RealmList<Results> results) {
        this.results = results;
    }
}
