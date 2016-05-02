@XmlSchema(
	    namespace = "http://impl.services.soap.ws.epam.com/",
	    elementFormDefault = XmlNsForm.QUALIFIED,
	    xmlns = {
	        @XmlNs(prefix="epm", namespaceURI="http://impl.services.soap.ws.epam.com/")
	    }
	) 
package com.epam.ws.model;

import javax.xml.bind.annotation.XmlNs;
import javax.xml.bind.annotation.XmlNsForm;
import javax.xml.bind.annotation.XmlSchema;
