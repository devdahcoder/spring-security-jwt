package com.devdahcoder.user.generic;

import java.util.List;

public class UserGenericListResponse<T> {

    private int page = 0;
    private int offSet = 0;
    private String order = "ASC";
    private List<T> data;
    private int limit = 0;
    private int totalData = 0;
    private int totalPage = 0;
    private String message = "All data";
    private int currentTotalData = 0;
    private boolean hasNext = false;
    private boolean hasPrevious = false;

    public UserGenericListResponse(List<T> data) {

        this.data = data;

    }

    public UserGenericListResponse(
            int page,
            int offSet,
            String order,
            List<T> data,
            int limit,
            int totalData,
            int totalPage,
            boolean hasNext,
            boolean hasPrevious,
            int currentTotalData
    ) {

        this.page = page;
        this.order = order;
        this.data = data;
        this.offSet = offSet;
        this.hasNext = hasNext;
        this.limit = limit;
        this.totalData = totalData;
        this.totalPage = totalPage;
        this.hasPrevious = hasPrevious;
        this.currentTotalData = currentTotalData;

    }

    public int getPage() {

        return page;

    }

    public void setPage(int page) {

        this.page = page;

    }

    public int getOffSet() {

        return offSet;

    }

    public void setOffSet(int offSet) {

        this.offSet = offSet;

    }

    public String getOrder() {

        return order;

    }

    public void setOrder(String order) {

        this.order = order;

    }

    public List<T> getData() {

        return data;

    }

    public void setData(List<T> data) {

        this.data = data;

    }

    public int getLimit() {

        return limit;

    }

    public void setLimit(int limit) {

        this.limit = limit;

    }

    public int getTotalData() {

        return totalData;

    }

    public void setTotalData(int totalData) {

        this.totalData = totalData;

    }

    public int getTotalPage() {

        return totalPage;

    }

    public void setTotalPage(int totalPage) {

        this.totalPage = totalPage;

    }

    public String getMessage() {

        return message;

    }

    public void setMessage(String message) {

        this.message = message;

    }

    public int getCurrentTotalData() {

        return currentTotalData;

    }

    public void setCurrentTotalData(int currentTotalData) {

        this.currentTotalData = currentTotalData;

    }

    public boolean getHasNext() {

        return hasNext;

    }

    public void setHasNext(boolean hasNext) {

        this.hasNext = hasNext;

    }

    public boolean getHasPrevious() {

        return hasPrevious;

    }

    public void setHasPrevious(boolean hasPrevious) {

        this.hasPrevious = hasPrevious;

    }

}
