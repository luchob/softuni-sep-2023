package org.softuni.mobilele.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "brands")
public class BrandEntity extends BaseEntity {
  @Column(unique = true, nullable = false)
  private String brand;

  public String getBrand() {
    return brand;
  }

  public BrandEntity setBrand(String brand) {
    this.brand = brand;
    return this;
  }
}
