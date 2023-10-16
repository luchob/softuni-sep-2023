package org.softuni.mobilele.repository;

import java.lang.annotation.Retention;
import org.softuni.mobilele.model.entity.ExchangeRateEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExchangeRateRepository extends JpaRepository<ExchangeRateEntity, String> {
}
