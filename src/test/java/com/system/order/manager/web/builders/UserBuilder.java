package com.system.order.manager.web.builders;

import com.system.order.manager.model.User;

public class UserBuilder {

    private User user;

    private UserBuilder() {

    }

    public static UserBuilder user() {
        UserBuilder userBuilder = new UserBuilder();
        defaultUserData(userBuilder);
        return userBuilder;
    }

    public static void defaultUserData(UserBuilder builder) {
        builder.user = new User();
        User element = builder.user;

        element.setName("Jonas");
        element.setEmail("jonas@outlook.com");
    }

    public UserBuilder withName(String name) {
        user.setName(name);
        return this;
    }

    public UserBuilder withEmail(String email) {
        user.setEmail(email);
        return this;
    }

    public User now() {
        return user;
    }
}
