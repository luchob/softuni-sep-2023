package org.softuni.mobilele.service.impl;

import java.util.UUID;
import org.softuni.mobilele.model.dto.CreateOfferDTO;
import org.softuni.mobilele.repository.OfferRepository;
import org.softuni.mobilele.service.OfferService;
import org.springframework.stereotype.Service;

@Service
public class OfferServiceImpl implements OfferService {

  private final OfferRepository offerRepository;

  public OfferServiceImpl(OfferRepository offerRepository) {
    this.offerRepository = offerRepository;
  }

  @Override
  public UUID createOffer(CreateOfferDTO createOfferDTO) {
    //todo - create offer
    throw new UnsupportedOperationException("Comming soon!");
  }
}
