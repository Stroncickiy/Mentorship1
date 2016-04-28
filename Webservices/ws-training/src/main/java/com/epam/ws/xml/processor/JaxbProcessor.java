package com.epam.ws.xml.processor;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

@SuppressWarnings("unchecked")
public class JaxbProcessor<T> {

	private File xmlFile;
	private T data;
	private JAXBContext context;
	private Unmarshaller unmarshaller;
	private Marshaller marshaller;
	private boolean insuficientFile;

	public JaxbProcessor<T> forClass(Class<?> clazz) throws JAXBException {
		this.context = JAXBContext.newInstance(clazz);
		unmarshaller = context.createUnmarshaller();
		marshaller = context.createMarshaller();
		return this;
	}

	public JaxbProcessor<T> from(File location) throws JAXBException, IOException {
		this.xmlFile = location;
		if (!xmlFile.exists()) {
			xmlFile.createNewFile();
		}
		try {
			data = (T) unmarshaller.unmarshal(new FileInputStream(xmlFile));
		} catch (JAXBException exception) {
			insuficientFile = true;
		}
		return this;
	}

	public JaxbProcessor<T> withInitialData(T initialData) throws FileNotFoundException, JAXBException {
		if (insuficientFile) {
			marshaller.marshal(initialData, new FileOutputStream(xmlFile));
			this.data = initialData;
		} else {
			data = (T) unmarshaller.unmarshal(new FileInputStream(xmlFile));
		}
		return this;
	}

	public boolean writeToFile() {
		try {
			marshaller.marshal(data, new FileOutputStream(xmlFile));
			return true;
		} catch (FileNotFoundException | JAXBException e) {
			e.printStackTrace();
		}
		return false;
	}

	public T getDataFromFile() {
		return data;
	}

}
