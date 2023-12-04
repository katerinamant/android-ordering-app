package com.example.softcafeengineer.domain;

import org.junit.Assert;
import org.junit.Test;

public class StatusTest
{
    @Test
    public void enum_status() {
        Assert.assertEquals("IN_PROGRESS", Status.IN_PROGRESS.name());
        Assert.assertEquals("CANCELED", Status.CANCELED.name());
        Assert.assertEquals("WAITING", Status.WAITING.name());
        Assert.assertEquals("COMPLETED", Status.COMPLETED.name());
    }
}
