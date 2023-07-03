package com.devdahcoder.user.model;

import java.util.List;
import java.util.Objects;

public class UserResponseModel<T> {

    private List<T> data;
    private int totalData;

    public UserResponseModel(List<T> data, int totalData) {

        this.data = data;
        this.totalData = totalData;

    }

    public List<T> getData() {

        return data;

    }

    public void setData(List<T> data) {

        this.data = data;

    }

    public int getTotalData() {

        return totalData;

    }

    public void setTotalData(int totalData) {

        this.totalData = totalData;

    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;

        if (!(o instanceof UserResponseModel<?> that)) return false;

        return getTotalData() == that.getTotalData() && Objects.equals(getData(), that.getData());

    }

    @Override
    public int hashCode() {

        return Objects.hash(getData(), getTotalData());

    }

    @Override
    public String toString() {

        return "UserResponseModel{" +
                "data=" + data +
                ", totalData=" + totalData +
                '}';

    }

}
