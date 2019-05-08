package com.shi.creator.entity;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NamedQueries(value = {
		@NamedQuery(name = Holder.SELECT_ALL, query = "select u from Holder u"),
		@NamedQuery(name = Holder.FIND_BY_EMAIL, query = "select u from Holder u where u.email = :email")})

@Entity
@Table(name = "holders", schema = "public", 
		uniqueConstraints = @UniqueConstraint(columnNames = "email"))
@Getter@Setter@AllArgsConstructor@NoArgsConstructor
public class Holder {
	public static final String SELECT_ALL = "select all holders";
	public static final String FIND_BY_EMAIL = "find holder by email";
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "name", nullable = false)
	private String name;
	
	@Column(name = "surname", nullable = false)
	private String surname;
	
	@Column(name = "email", nullable = false)
	private String email;

	@Setter(AccessLevel.NONE)
	@Getter(AccessLevel.NONE)
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "holder")
	private Set<Contract> contracts;
	
	public void addContract(Contract contract) {
		if(contracts == null)
			contracts = new HashSet<Contract>();
		contracts.add(contract);
		contract.setHolder(this);
	}
	
	public Set<Contract> getContracts() {
		if(contracts != null) 
			return Collections.unmodifiableSet(contracts);
		else 
			return Collections.unmodifiableSet(new HashSet<Contract>());
	}
	
	@Override
	public String toString() {
		return "Holder [id=" + id + ", name=" + name + ", surname=" + surname + ", email=" + email + "]";
	}	
}
