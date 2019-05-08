package com.shi.creator.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NamedQueries(value = {
		@NamedQuery(name = Contract.SELECT_ALL, query = "select u from Contract u"),
		@NamedQuery(name = Contract.FIND_BY_EMAIL, query = "select u from Contract u where u.number = :number")})

@Entity
@Table(name = "contracts", schema = "public", uniqueConstraints = @UniqueConstraint(columnNames = "number"))
@Getter@Setter@AllArgsConstructor@NoArgsConstructor
public class Contract {

	public static final String SELECT_ALL = "select all contracts";
	public static final String FIND_BY_EMAIL = "find contract by number";
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "number", nullable = false)
	private String number;
	
	@Getter(AccessLevel.NONE)
	@Column(name = "holder_id",nullable= false, insertable = false, updatable = false)
	private long holderId;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "holder_id", referencedColumnName = "id")
	private Holder holder;
	
	@Column(name = "signedDate", nullable = false)
	private LocalDate signedDate;
	
	@Column(name = "sum", nullable = false)
	private BigDecimal sum;
	
	@Column(name = "since", nullable = false)
    private LocalDate since;
    
	@Column(name = "till", nullable = false)
    private LocalDate till;
	
	@Override
	public String toString() {
		return "Contract [id=" + id + ", number=" + number + ", holder_id=" + holder.getId() 
				+ ", signedDate=" + signedDate + ", sum=" + sum + ", since=" 
				+ since + ", till=" + till + "]";
	}	
}
