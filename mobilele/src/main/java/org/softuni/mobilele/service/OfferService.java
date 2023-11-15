package org.softuni.mobilele.service;


import java.util.Optional;
import java.util.UUID;
import org.softuni.mobilele.model.dto.CreateOfferDTO;
import org.softuni.mobilele.model.dto.OfferDetailDTO;
import org.softuni.mobilele.model.dto.OfferSummaryDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;

public interface OfferService {

  UUID createOffer(CreateOfferDTO createOfferDTO, UserDetails seller);

  Page<OfferSummaryDTO> getAllOffers(Pageable pageable);

  Optional<OfferDetailDTO> getOfferDetail(UUID offerUUID, UserDetails viewer);

  void deleteOffer(UUID offerUUID);
}
