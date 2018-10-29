package com.sas.util;

import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;

public class XmlParsingUtil {
	
	public static Document getXmlAsDOMDocument(String xmlString) throws Exception {
		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory
				.newInstance();
		DocumentBuilder documentBuilder = documentBuilderFactory
				.newDocumentBuilder();
		return documentBuilder.parse(
                new InputSource(new StringReader(xmlString)));
	}

}
