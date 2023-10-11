package org.softuni.mobilele.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "brands")
public class BrandEntity extends BaseEntity {
  @Column(unique = true, nullable = false)
  private String name;

  public BrandEntity setName(String name) {
    this.name = name;
    return this;
  }

  public String getName() {
    return name;
  }

}
