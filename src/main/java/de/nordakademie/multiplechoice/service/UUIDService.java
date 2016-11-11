package de.nordakademie.multiplechoice.service;

import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * This class is used to generate unique ids
 *
 * @author Ferenc Beutel, Max Hort, Melanie Beckmann, Hendrik Peters
 */
@Service
public class UUIDService {
  public String getUUID() {
    return UUID.randomUUID().toString();
  }
}
