package de.nordakademie.multiplechoice.service;

import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * Created by Ferenc on 18.10.2016.
 */
@Service
public class UUIDService {
    public String getUUID() {
        return UUID.randomUUID().toString();
    }
}
