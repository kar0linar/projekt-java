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
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.OneToMany;




@Entity
@Table(name = "animal")
@NamedQuery(name = "Animal.findAll", query = "SELECT a FROM Animal a")
public class Animal implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="animal_id")
	private int animalId;

	@Column(name="animal_name")
	private String animalName;

	@Temporal(TemporalType.DATE)
	@Column(name="join_date")
	private Date joinDate;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="caretaker_id")
	private User user;

	//bi-directional many-to-one association to Category
	@ManyToOne
	@JoinColumn(name="category_id")
	private Category category;

	//bi-directional many-to-one association to Specy
	@ManyToOne
	@JoinColumn(name="species_id")
	private Specy specy;
	
	public void log(String text) {
		System.out.println(text + ": [" + animalId+ "], " + animalName + ", " + joinDate + ", " + user + ", " + category);
	}
	
		
		
	public Animal() {
	}

	public Integer getAnimalId() {
		return this.animalId;
	}

	public void setAnimalId(Integer animalId) {
		this.animalId = animalId;
	}

	public String getAnimalName() {
		return this.animalName;
	}

	public void setAnimalName(String animalName) {
		this.animalName = animalName;
	}

	public Date getJoinDate() {
		return this.joinDate;
	}

	public void setJoinDate(Date joinDate) {
		this.joinDate = joinDate;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Category getCategory() {
		return this.category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Specy getSpecy() {
		return this.specy;
	}

	public void setSpecy(Specy specy) {
		this.specy = specy;
	}

}