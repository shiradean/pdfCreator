package com.shi.creator.domain;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import lombok.Getter;
import lombok.Setter;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "HolderType", propOrder = {
    "name",
    "surname",
    "email"
})
@Getter@Setter
public class HolderType {
	
    @XmlElement(required = true)
    protected String name;
    
    @XmlElement(required = true)
    protected String surname;
    
    @XmlElement(required = true)
    protected String email;
    
    @XmlAttribute(name = "id")
    protected Long id;
}
