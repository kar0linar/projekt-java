package com.jsf.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.ManyToOne;
import com.jsf.entities.Role;
import java.util.Date;


/**
 * The persistent class for the species database table.
 * 
 */
@Entity
@Table(name="species")
@NamedQuery(name="Specy.findAll", query="SELECT s FROM Specy s")
public class Specy implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="species_id")
	private Integer speciesId;

	@Column(name="species_name")
	private String speciesName;

	//bi-directional many-to-one association to Animal
	@OneToMany(mappedBy="specy")
	private List<Animal> animals;

	public Specy() {
	}

	public Integer getSpeciesId() {
		return this.speciesId;
	}

	public void setSpeciesId(Integer speciesId) {
		this.speciesId = speciesId;
	}

	public String getSpeciesName() {
		return this.speciesName;
	}

	public void setSpeciesName(String speciesName) {
		this.speciesName = speciesName;
	}

	public List<Animal> getAnimals() {
		return this.animals;
	}

	public void setAnimals(List<Animal> animals) {
		this.animals = animals;
	}

	public Animal addAnimal(Animal animal) {
		getAnimals().add(animal);
		animal.setSpecy(this);

		return animal;
	}

	public Animal removeAnimal(Animal animal) {
		getAnimals().remove(animal);
		animal.setSpecy(null);

		return animal;
	}

}