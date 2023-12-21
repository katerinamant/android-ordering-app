package com.example.softcafeengineer.domain;

import org.junit.Assert;
import org.junit.Test;

public class UserTest
{
    @Test
    public void set_values() {
        User user = new User();

        user.setUsername("User");
        Assert.assertEquals("User", user.getUsername());

        user.setPassword("1234");
        Assert.assertEquals("1234", user.getPassword());

        Cafeteria cafe = new Cafeteria();
        user.setCafe(cafe);
        Assert.assertEquals(cafe, user.getCafe());
    }

    @Test
    public void constructor_with_args() {
        User user = new User("User", "1234");

        Assert.assertEquals("User", user.getUsername());
        Assert.assertEquals("1234", user.getPassword());
    }
}
