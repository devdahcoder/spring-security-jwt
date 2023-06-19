package com.devdahcoder.user.contract;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public enum UserRole {

    ADMIN,
    USER;

    @Contract(pure = true)
    public static @NotNull String getRole(@NotNull UserRole role) {

        return role.name();

    }

    @Contract(pure = true)
    public static boolean isAdmin(@NotNull UserRole role) {

        return role.equals(ADMIN);

    }

    @Contract(pure = true)
    public static boolean isUser(@NotNull UserRole role) {

        return role.equals(USER);

    }

}
