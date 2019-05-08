package com.shi.creator.converter;

import java.time.LocalDate;

import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.modelmapper.Converter;
import org.modelmapper.spi.MappingContext;

public class DateConverter implements Converter<LocalDate, XMLGregorianCalendar>{

    public XMLGregorianCalendar convert(MappingContext<LocalDate, XMLGregorianCalendar> context) {
        try {
      	  System.out.println(context.getSource().toString());
          return DatatypeFactory.newInstance().newXMLGregorianCalendar(context.getSource().toString());
        } catch (Exception e) {
          return null;
        }
      }
}
