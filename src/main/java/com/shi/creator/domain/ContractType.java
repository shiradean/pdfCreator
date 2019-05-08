package com.shi.creator.domain;

import java.math.BigDecimal;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;

import lombok.Getter;
import lombok.Setter;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ContractType", propOrder = {
	    "number",
	    "signedDate",
	    "since",
	    "till",
	    "holder",
	    "sum"
})
@Getter@Setter
public class ContractType {

	@XmlAttribute(name = "id")
	protected Long id;
	
    @XmlElement(required = true)
    private String number;
	
    @XmlSchemaType(name = "date")
	@XmlElement(required = true)
	private XMLGregorianCalendar signedDate;
	
	@XmlSchemaType(name = "date")
	@XmlElement(required = true)
    private XMLGregorianCalendar since;
    
	@XmlSchemaType(name = "date")
	@XmlElement(required = true)
    private XMLGregorianCalendar till;
	
	@XmlElement(required = true)
	private HolderType holder;
	
	@XmlElement(required = true)
	private BigDecimal sum;
}
