package com.devdahcoder.user.util;

import com.devdahcoder.user.model.UserResponseModel;
import org.jetbrains.annotations.NotNull;
import org.springframework.cglib.core.Predicate;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserUtil {

    public int getUserTotalCount(int limit, @NotNull UserResponseModel users) {

        return (int) Math.ceil((double) users.getTotalData() / limit);

    }

    public void getUserPages(int user) {}

    public int calculateOffSet(int limit, int page) {

        int pageNumber = page <= 0 ? 1 : page;

        return (pageNumber - 1) * limit;

    }

    public boolean paginationCheck(Integer page, @NotNull Predicate predicate) {

        return predicate.evaluate(page);

    }



}
