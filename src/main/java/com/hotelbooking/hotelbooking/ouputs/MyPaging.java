package com.hotelbooking.hotelbooking.ouputs;

import java.io.Serializable;

public class MyPaging implements Serializable {
    private Integer totalPage;
    private Integer previousPage;
    private Integer currentPage; // the page which the user would like to see
    private Integer nextPage;
    private Integer pageSize; // the total of item is showed per the page

    public MyPaging() {
    }

    public MyPaging(Integer totalPage, Integer previousPage, Integer currentPage, Integer nextPage, Integer pageSize) {
        this.totalPage = totalPage;
        this.previousPage = previousPage;
        this.currentPage = currentPage;
        this.nextPage = nextPage;
        this.pageSize = pageSize;
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    public Integer getPreviousPage() {
        return previousPage;
    }

    public void setPreviousPage(Integer previousPage) {
        this.previousPage = previousPage;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getNextPage() {
        return nextPage;
    }

    public void setNextPage(Integer nextPage) {
        this.nextPage = nextPage;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
