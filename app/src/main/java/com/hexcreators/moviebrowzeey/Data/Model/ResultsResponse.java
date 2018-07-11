package com.hexcreators.moviebrowzeey.Data.Model;

import java.util.List;

public class ResultsResponse {

    private List<Results> results;

    private Integer page;

    private Integer total_pages;

    private Integer total_results;


    public List<Results> getResults() {
        return results;
    }

    public void setResults(List<Results> results) {
        this.results = results;
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
}
