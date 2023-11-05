package org.softuni.mobilele.service;


import java.util.Optional;
import java.util.UUID;
import org.softuni.mobilele.model.dto.CreateOfferDTO;
import org.softuni.mobilele.model.dto.OfferDetailDTO;
import org.softuni.mobilele.model.dto.OfferSummaryDTO;
import org.softuni.mobilele.model.dto.SearchOfferDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OfferService {

  UUID createOffer(CreateOfferDTO createOfferDTO);

  Page<OfferSummaryDTO> getAllOffers(SearchOfferDTO searchOfferDTO, Pageable pageable);

  Optional<OfferDetailDTO> getOfferDetail(UUID offerUUID);

  void deleteOffer(UUID offerUUID);
}
