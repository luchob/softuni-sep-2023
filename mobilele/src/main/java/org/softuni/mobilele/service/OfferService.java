package org.softuni.mobilele.service;


import org.softuni.mobilele.model.dto.CreateOfferDTO;
import org.softuni.mobilele.model.entity.ModelEntity;
import org.softuni.mobilele.model.entity.OfferEntity;

public interface OfferService {

  OfferEntity createOffer(CreateOfferDTO createOfferDTO, ModelEntity modelEntity);
}
