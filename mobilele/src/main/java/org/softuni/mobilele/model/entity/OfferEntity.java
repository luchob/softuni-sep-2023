package org.softuni.mobilele.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;
import java.sql.Types;
import java.util.UUID;
import org.hibernate.annotations.JdbcTypeCode;
import org.softuni.mobilele.model.enums.EngineEnum;
import org.softuni.mobilele.model.enums.TransmissionEnum;

@Entity
@Table(name = "offers")
public class OfferEntity extends BaseEntity{

  @NotNull
  @JdbcTypeCode(Types.VARCHAR)
  private UUID uuid;
  @NotEmpty
  private String description;
  @NotNull
  @ManyToOne
  private ModelEntity model;

  @NotNull
  @Enumerated(EnumType.STRING)
  private EngineEnum engine;
  @NotNull
  @Enumerated(EnumType.STRING)
  private TransmissionEnum transmission;

  @NotEmpty
  private String imageUrl;

  @Positive
  private int mileage;

  @NotNull
  private BigDecimal price;

  @Min(1930)
  private int year;

  public String getDescription() {
    return description;
  }

  public OfferEntity setDescription(String description) {
    this.description = description;
    return this;
  }

  public ModelEntity getModel() {
    return model;
  }

  public OfferEntity setModel(ModelEntity model) {
    this.model = model;
    return this;
  }

  public EngineEnum getEngine() {
    return engine;
  }

  public OfferEntity setEngine(EngineEnum engine) {
    this.engine = engine;
    return this;
  }

  public TransmissionEnum getTransmission() {
    return transmission;
  }

  public OfferEntity setTransmission(TransmissionEnum transmission) {
    this.transmission = transmission;
    return this;
  }

  public String getImageUrl() {
    return imageUrl;
  }

  public OfferEntity setImageUrl(String imageUrl) {
    this.imageUrl = imageUrl;
    return this;
  }

  public int getMileage() {
    return mileage;
  }

  public OfferEntity setMileage(int mileage) {
    this.mileage = mileage;
    return this;
  }

  public BigDecimal getPrice() {
    return price;
  }

  public OfferEntity setPrice(BigDecimal price) {
    this.price = price;
    return this;
  }

  public int getYear() {
    return year;
  }

  public OfferEntity setYear(int year) {
    this.year = year;
    return this;
  }

  public UUID getUuid() {
    return uuid;
  }

  public OfferEntity setUuid(UUID uuid) {
    this.uuid = uuid;
    return this;
  }
}
