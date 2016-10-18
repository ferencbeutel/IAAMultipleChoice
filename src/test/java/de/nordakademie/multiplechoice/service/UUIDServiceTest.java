package de.nordakademie.multiplechoice.service;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Ferenc on 18.10.2016.
 */
public class UUIDServiceTest {

    UUIDService uuidService = new UUIDService();

    @Test
    public void testGetUUID() {
        String UUID = uuidService.getUUID();
        Assert.assertNotEquals("We need a unique ID every time!", uuidService.getUUID(), UUID);
    }
}