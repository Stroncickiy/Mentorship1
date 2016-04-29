package com.epam.ws.xml.processor;

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
		return new ElementBuilder(newElement, document.getDocumentElement(), null);
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
			criterias = new ArrayList<>();
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

			public NodeCriteriaBuilder tagName(String tagname) {
				criterias.add(new NodeCriteria() {
					
					@Override
					public boolean accept(Element element) {
						return element.getTagName().equals(tagname);
					}
				});
				return this;
			
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
				Node item = listChildNodes.item(i);
				if (item instanceof Element && examine(item)) {
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

		private Element elementToBuild;
		private Element father;
		private ElementBuilder outerBuilder;

		private ElementBuilder(Element element, Element father, ElementBuilder outerBuilder) {
			if(outerBuilder==null){
				this.outerBuilder = this;
			}else{				
				this.outerBuilder = outerBuilder;
			}
			this.elementToBuild = element;
			this.father = father;
	
		}

		public ElementBuilder addAttribute(String name, Object value) {
			elementToBuild.setAttribute(name, String.valueOf(value));
			return this;
		}

		public ElementBuilder setValue(Object value) {
			elementToBuild.setTextContent(String.valueOf(value));
			return this;
		}

		public Element build() {
			return elementToBuild;
		}

		public DOMSelector insertInto() {
			return new DOMSelector(father);
		}

		public ElementBuilder addChild(String childName) {
			Element child = document.createElement(childName);
			ElementBuilder elementBuilder = new ElementBuilder(child, elementToBuild, outerBuilder);
			return elementBuilder;
		}

		public class DOMSelector {

			private Element targetElementToInsertInto;
			private Element ellementToInsert;

			public DOMSelector(Element parent) {
				this.targetElementToInsertInto = parent;
			}

			public ElementBuilder outerElement() {
				ellementToInsert = ElementBuilder.this.elementToBuild;
				targetElementToInsertInto.appendChild(ellementToInsert);
				return ElementBuilder.this.outerBuilder;
			}

			public DOMSelector moveInsideInto(String tagNameToMoveCursor) {
				targetElementToInsertInto = (Element) targetElementToInsertInto
						.getElementsByTagName(tagNameToMoveCursor).item(0);
				return this;
			}

		}

	}

}
