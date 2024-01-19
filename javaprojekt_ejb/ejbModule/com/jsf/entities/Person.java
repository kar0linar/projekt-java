package com.jsf.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.OneToMany;
import com.jsf.entities.Role;

/**
 * The persistent class for the person database table.
 * 
 */
@Entity
@Table(name = "person")
@NamedQuery(name = "Person.findAll", query = "SELECT p FROM Person p")
public class Person implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true, nullable = false)
	private Integer idperson;

	@Column(length = 4)
	private Integer birthyear;

	@Column(length = 45)
	private String name;

	@Column(length = 45)
	private String surname;
	
	@Column(length = 45)
	private String portrait;

	public void log(String text) {
		System.out.println(text + ": [" + idperson + "], " + name + ", " + surname + ", " + birthyear + ", " + portrait);
	}
	
	//bi-directional many-to-one association to Role
	@OneToMany(mappedBy="person")
	private List<Role> roles;
	
	public Person() {
	}

	public Integer getIdperson() {
		return this.idperson;
	}

	public void setIdperson(Integer idperson) {
		this.idperson = idperson;
	}

	public Integer getBirthyear() {
		return this.birthyear;
	}

	public void setBirthyear(Integer birthyear) {
		this.birthyear = birthyear;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return this.surname;
	}
	
	public void setSurname(String surname) {
		this.surname = surname;
	}
	
	public String getPortrait() {
		return this.portrait;
	}

	public void setPortrait(String portrait) {
		this.portrait = portrait;
	}

	public List<Role> getRoles() {
		return this.roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public Role addRole(Role role) {
		getRoles().add(role);
		role.setPerson(this);

		return role;
	}

	public Role removeRole(Role role) {
		getRoles().remove(role);
		role.setPerson(null);

		return role;
	}

}