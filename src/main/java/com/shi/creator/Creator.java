package com.shi.creator;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Calendar;

import javax.activation.DataHandler;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.Marshaller;
import javax.xml.namespace.QName;
import javax.xml.soap.SOAPConstants;
import javax.xml.soap.SOAPFactory;
import javax.xml.soap.SOAPFault;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Templates;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.sax.SAXResult;
import javax.xml.transform.stream.StreamSource;
import javax.xml.ws.soap.SOAPFaultException;

import org.apache.fop.apps.FOUserAgent;
import org.apache.fop.apps.Fop;
import org.apache.fop.apps.FopFactory;
import org.apache.xmlgraphics.util.MimeConstants;

import com.shi.creator.domain.ContractType;
import com.sun.istack.ByteArrayDataSource;

public class Creator {
	
	public DataHandler createPdf(ContractType contract) {
		try {
			ByteArrayOutputStream pdfBaos = new ByteArrayOutputStream();		
			
			Source xsltSource = new StreamSource(this.getClass().getClassLoader().getResourceAsStream("template.xsl"));
			TransformerFactory transFact = TransformerFactory.newInstance();
			Templates cachedXSLT = transFact.newTemplates(xsltSource);
			Transformer transformer = cachedXSLT.newTransformer();
			
			JAXBElement<ContractType> jx = new JAXBElement<ContractType>(new QName("contract"), ContractType.class, contract);
			
			JAXBContext context = JAXBContext.newInstance(ContractType.class);
            Marshaller marshaller = context.createMarshaller();
            
            
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            StringWriter sw = new StringWriter();
            marshaller.marshal(jx, sw);
            String xmlString = sw.toString();

			File file = new File(".");
			FopFactory fopFactory = FopFactory.newInstance(file.toURI());
			
			FOUserAgent userAgent = fopFactory.newFOUserAgent();
			userAgent.setAuthor("shiradean");
			userAgent.setCreationDate(Calendar.getInstance().getTime());
			userAgent.setTitle("Contract ¹" + contract.getNumber());
			userAgent.setSubject("contract pdf");

			Fop fop = fopFactory.newFop(MimeConstants.MIME_PDF, userAgent, pdfBaos);
			

			Result res = new SAXResult(fop.getDefaultHandler());
			transformer.transform(new StreamSource(new StringReader(xmlString)), res);

			OutputStream out = new FileOutputStream("out.pdf");
			out.write(pdfBaos.toByteArray());
			out.close();

			ByteArrayDataSource ds = new ByteArrayDataSource(pdfBaos.toByteArray(), "application/pdf");

			return new DataHandler(ds);
		} catch (Exception e) {
			try {
				SOAPFault fault = SOAPFactory.newInstance().createFault();
				fault.setFaultString(e.getMessage());
				fault.setFaultCode(new QName(SOAPConstants.URI_NS_SOAP_ENVELOPE, "Server"));
				throw new SOAPFaultException(fault);
			} catch (Exception e2) {
				throw new RuntimeException(
						"downloadPDF: Problem processing SOAP Fault on service-side: " + e2.getMessage());
			}
		}
	}
}
