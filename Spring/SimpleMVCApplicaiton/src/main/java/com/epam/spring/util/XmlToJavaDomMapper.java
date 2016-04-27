package com.epam.spring.util;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class XmlToJavaDomMapper<T> {
	private XmlToJavaMapper<T> mapper;

	public XmlToJavaDomMapper(XmlToJavaMapper<T> mapper) {
		this.mapper = mapper;
	}

	public List<T> mapXmlElementsToListOfClasses(List<Element> elements) {
		List<T> listOfObjects = new ArrayList<>();
		for (Element element : elements) {
			listOfObjects.add(mapper.convert(element));
		}
		return listOfObjects;
	}

	public T mapXmlElementToObject(Element element) {
		return mapper.convert(element);

	}

	public static interface XmlToJavaMapper<T> {
		public T convert(Element element);

	}

	public List<T> mapXmlElementsToListOfClasses(NodeList childNodes) {
		ArrayList<Element> result = new ArrayList<>();
		for (int i = 0; i < childNodes.getLength(); i++) {
			result.add((Element) childNodes.item(i));
		}
		return mapXmlElementsToListOfClasses(result);
	}
}
