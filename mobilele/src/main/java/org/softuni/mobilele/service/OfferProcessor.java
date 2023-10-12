package org.softuni.mobilele.service;

import org.softuni.mobilele.model.dto.CreateOfferDTO;

import java.util.UUID;

public interface OfferProcessor {

    UUID createOffer(CreateOfferDTO createOfferDTO);
}
