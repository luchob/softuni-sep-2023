package org.softuni.mobilele.model.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.NamedAttributeNode;
import jakarta.persistence.NamedEntityGraph;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;

@Entity
@Table(name = "brands")
@NamedEntityGraph(
    name = "brandWithModels",
    attributeNodes = @NamedAttributeNode("models")
)
public class BrandEntity extends BaseEntity {
  @Column(unique = true, nullable = false)
  private String name;

  @OneToMany(
      fetch = FetchType.LAZY,
      cascade = CascadeType.ALL,
      mappedBy = "brand"
  )
  private List<ModelEntity> models;

  public BrandEntity setName(String name) {
    this.name = name;
    return this;
  }

  public String getName() {
    return name;
  }

  public List<ModelEntity> getModels() {
    return models;
  }

  public BrandEntity setModels(List<ModelEntity> models) {
    this.models = models;
    return this;
  }
}
