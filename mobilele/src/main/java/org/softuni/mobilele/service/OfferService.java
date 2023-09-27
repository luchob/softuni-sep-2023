package org.softuni.mobilele.service;


import java.util.UUID;
import org.softuni.mobilele.model.dto.CreateOfferDTO;

public interface OfferService {

  UUID createOffer(CreateOfferDTO createOfferDTO);

}
