package com.epam.spring.util;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class DOMProcessor {

	private Document document;

	public DOMProcessor(Document document) {
		this.document = document;
	}

	public ElementBuilder newElement(String tagName) {
		Element newElement = document.createElement(tagName);
		return new ElementBuilder(newElement);
	}

	public ElementFinder findChildByCriteria(Element father) {
		return new ElementFinder(father);
	}
	
	

	public static interface NodeCriteria {
		boolean accept(Element element);
	}

	public class ElementFinder {
		private NodeList listChildNodes;
		private List<NodeCriteria> criterias;

		public ElementFinder(Element father) {
			this.listChildNodes = father.getChildNodes();

		}
		
		

		public NodeCriteriaBuilder defineCriterias() {
			return new NodeCriteriaBuilder();
		}

		public class NodeCriteriaBuilder {
			public NodeCriteriaBuilder attributeIsEqualTo(String attrName, Object value) {
				criterias.add(new NodeCriteria() {
					@Override
					public boolean accept(Element element) {
						return element.getAttribute(attrName).equals(String.valueOf(value));
					}
				});
				return this;
			}

			public ElementFinder enought() {
				return ElementFinder.this;
			}
		}

		public List<Element> getResultList() {
			return filter();
		}

		public Element getSingleResult() {
			return filter().get(0);
		}

		private List<Element> filter() {
			List<Element> resultList = new ArrayList<>();
			for (int i = 0; i < listChildNodes.getLength(); i++) {
				if (examine(listChildNodes.item(i))) {
					resultList.add((Element) listChildNodes.item(i));
				}
			}
			return resultList;
		}

		private boolean examine(Node item) {
			for (NodeCriteria nodeCriteria : criterias) {
				if (!nodeCriteria.accept((Element) item)) {
					return false;
				}
			}
			return true;
		}

	}

	public class ElementBuilder {

		private Element element;

		private ElementBuilder(Element element) {
			this.element = element;
		}

		public ElementBuilder addAttribute(String name, Object value) {
			element.setAttribute(name, String.valueOf(value));
			return this;
		}

		public ElementBuilder setValue(Object value) {
			element.setNodeValue(String.valueOf(value));
			return this;
		}

		public Element build() {
			return element;
		}

		public DOMSelector insertInto() {
			return new DOMSelector();
		}

		public ElementBuilder addChild(String childName) {
			Element child = document.createElement(childName);
			return new ElementBuilder(child);
		}

		public class DOMSelector {

			private Element currentElement;
			private Element ellementToInsert;

			public ElementBuilder outerElement() {
				currentElement = element;
				currentElement.appendChild(ellementToInsert);
				return ElementBuilder.this;

			}

			public DOMSelector moveInsideInto(String tagNameToMoveCursor) {
				currentElement = (Element) currentElement.getElementsByTagName(tagNameToMoveCursor).item(0);
				return this;
			}

		}
	}

}
