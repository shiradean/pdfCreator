package com.shi.creator;

import java.io.ByteArrayOutputStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.net.URI;
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
	private static final String PDF_AUTHOR = "shiradean";
	private static final String COMPANY_ADDRESS = "Vanickova 7, Praha, Czech Republic, 16900";
	private static final String COMPANY_NAME = "SUZ CVUT";
	private static final String COMPANY_REPRESENTATIVE = "Ida Vavrouskova";
	
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

			transformer.setParameter("compName", COMPANY_NAME);
			transformer.setParameter("compAddress", COMPANY_ADDRESS);
			transformer.setParameter("compRepresentative", COMPANY_REPRESENTATIVE);
			
			FopFactory fopFactory = FopFactory.newInstance(new URI("/"));
			
			FOUserAgent userAgent = fopFactory.newFOUserAgent();
			userAgent.setAuthor(PDF_AUTHOR);
			userAgent.setCreationDate(Calendar.getInstance().getTime());
			userAgent.setTitle("Contract ¹" + contract.getNumber());

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
