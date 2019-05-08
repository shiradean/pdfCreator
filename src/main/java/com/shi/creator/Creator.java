package com.shi.creator;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.StringReader;
import java.io.StringWriter;
import java.net.URI;
import java.net.URL;
import java.util.Calendar;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.namespace.QName;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Templates;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.sax.SAXResult;
import javax.xml.transform.stream.StreamSource;

import org.apache.fop.apps.FOUserAgent;
import org.apache.fop.apps.Fop;
import org.apache.fop.apps.FopFactory;
import org.apache.xmlgraphics.util.MimeConstants;

import com.shi.creator.domain.ContractType;

public class Creator {
	
	private String toXMLString(ContractType contract) throws JAXBException {
		JAXBElement<ContractType> jx = new JAXBElement<ContractType>(new QName("contract"), ContractType.class, contract);
		
		JAXBContext context = JAXBContext.newInstance(ContractType.class);
        Marshaller marshaller = context.createMarshaller();
        
        
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        StringWriter sw = new StringWriter();
        marshaller.marshal(jx, sw);
        return sw.toString();
	}
	
	public byte[] createPdf(ContractType contract) {
		try {
			ByteArrayOutputStream pdfBaos = new ByteArrayOutputStream();		
			
			Source xsltSource = new StreamSource(this.getClass().getClassLoader().getResourceAsStream("template.xsl"));
			TransformerFactory transFact = TransformerFactory.newInstance();
			Templates cachedXSLT = transFact.newTemplates(xsltSource);
			Transformer transformer = cachedXSLT.newTransformer();			

			
			FopFactory fopFactory = FopFactory.newInstance(new URI("/"));
			
			FOUserAgent userAgent = fopFactory.newFOUserAgent();
			userAgent.setAuthor("shiradean");
			userAgent.setCreationDate(Calendar.getInstance().getTime());
			userAgent.setTitle("Contract �" + contract.getNumber());
			userAgent.setSubject("contract pdf");

			Fop fop = fopFactory.newFop(MimeConstants.MIME_PDF, userAgent, pdfBaos);
			

			Result res = new SAXResult(fop.getDefaultHandler());
			transformer.transform(new StreamSource(new StringReader(toXMLString(contract))), res);

			return pdfBaos.toByteArray();
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
