package com.jsf.entities;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EmbeddedId;
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
/**
 * The primary key class for the role database table.
 * 
 */
@Embeddable
public class RolePK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="idmovie", insertable=false, updatable=false)
	private int idmovie;

	@Column(name="idperson", insertable=false, updatable=false)
	private int idperson;

	public RolePK() {
	}
	public int getIdMovie() {
		return this.idmovie;
	}
	public void setIdMovie(int idmovie) {
		this.idmovie = idmovie;
	}
	public int getIdPerson() {
		return this.idperson;
	}
	public void setIdPerson(int idperson) {
		this.idperson = idperson;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof RolePK)) {
			return false;
		}
		RolePK castOther = (RolePK)other;
		return 
			(this.idmovie == castOther.idmovie)
			&& (this.idperson == castOther.idperson);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.idmovie;
		hash = hash * prime + this.idperson;
		
		return hash;
	}
}