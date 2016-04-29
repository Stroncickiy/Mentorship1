package com.epam.ws.dao.impl;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import com.epam.ws.dao.CurrencyDAO;
import com.epam.ws.dao.TransactionDAO;
import com.epam.ws.dao.holder.DAOFactory;
import com.epam.ws.model.Transaction;
import com.epam.ws.model.Transaction.OperationType;
import com.epam.ws.xml.mapper.XmlToJavaDomMapper;
import com.epam.ws.xml.mapper.XmlToJavaDomMapper.XmlToJavaMapper;
import com.epam.ws.xml.processor.DOMProcessor;



public class TransactionDAOImpl implements TransactionDAO {

	private static final String TRANSACTIONS_XML_FILE_NAME = "transactions.xml";
	private DocumentBuilderFactory dbFactory;
	private DocumentBuilder dBuilder;
	private File transactionsXmlStorageFile;
	private Document transactionsXmlStorageDocument;
	private XmlToJavaDomMapper<Transaction> xmlToJavaDomMapper;

	private CurrencyDAO currencyDao;

	public TransactionDAOImpl() throws ParserConfigurationException, IOException, TransformerException{
	currencyDao = DAOFactory.getInstance().getCurrencyDAO();
	dbFactory = DocumentBuilderFactory.newInstance();
	dBuilder = dbFactory.newDocumentBuilder();
	transactionsXmlStorageFile = new File(TRANSACTIONS_XML_FILE_NAME);
	if (transactionsXmlStorageFile.exists()) {
		updateDocumentFromFile();
	} else {
		transactionsXmlStorageFile.createNewFile();
		transactionsXmlStorageDocument = dBuilder.newDocument();
		Element transactionsElement = transactionsXmlStorageDocument.createElement("transactions");
		transactionsXmlStorageDocument.appendChild(transactionsElement);
		saveDocumentToFile(transactionsXmlStorageDocument);
	}
	
	xmlToJavaDomMapper = new XmlToJavaDomMapper<>(new XmlToJavaMapper<Transaction>() {

		@Override
		public Transaction convert(Element element) {
			Transaction transaction = new Transaction();
			transaction.setAmmount(Double.valueOf(element.getElementsByTagName("ammount").item(0).getNodeValue()));
			transaction.setAmountInDefaultCurrency(Double.valueOf(element.getElementsByTagName("ammountInDefaultCurrency").item(0).getNodeValue()));
			transaction.setConvertedToDefaultCurrency(Boolean.valueOf(element.getElementsByTagName("isConverted").item(0).getNodeValue()));
			transaction.setCurrency(currencyDao.getByAlias(element.getElementsByTagName("currency").item(0).getNodeValue()));
			transaction.setId(Long.valueOf(element.getAttribute("id")));
			transaction.setUserId(Long.valueOf(element.getAttribute("userId")));
			transaction.setOperationType(OperationType.valueOf(element.getAttribute("operation")));
			return transaction;
		}
	});
	}
	

	private void updateDocumentFromFile() {
		try {
			transactionsXmlStorageDocument = dBuilder.parse(transactionsXmlStorageFile);
		} catch (SAXException | IOException e) {
			e.printStackTrace();
		}
	}
	
	private void commitData(){
		try {
			saveDocumentToFile(transactionsXmlStorageDocument);
		} catch (TransformerException e) {
			e.printStackTrace();
		}
	}
	
	private void saveDocumentToFile(Document document) throws TransformerException  {
		Transformer transformer = TransformerFactory.newInstance().newTransformer();
		Result output = new StreamResult(transactionsXmlStorageFile);
		Source input = new DOMSource(document);
		transformer.transform(input, output);
	}

	@Override
	public Transaction add(Transaction transaction) {
		updateDocumentFromFile();
		Element root = transactionsXmlStorageDocument.getDocumentElement();
		
		int itemsInFile = root.getElementsByTagName("transaction").getLength();
		transaction.setId((long) ++itemsInFile);
		Element newTransactionNode =new DOMProcessor(transactionsXmlStorageDocument)
		.newElement("transaction")
		.addAttribute("id", transaction.getId())
		.addAttribute("userId", transaction.getUserId())
		.addAttribute("operation", transaction.getOperationType().name())
		.addChild("currency")
			.setValue(transaction.getCurrency().getAlias())
			.insertInto()
			.outerElement()
		.addChild("isConverted")
			.setValue(transaction.isConvertedToDefaultCurrency())
			.insertInto()
			.outerElement()
		.addChild("ammount")	
			.setValue(transaction.getAmmount())
			.insertInto()
			.outerElement()
		.addChild("ammountInDefaultCurrency")
			.setValue(transaction.getAmountInDefaultCurrency())
			.insertInto()
			.outerElement()
		.build();
			
		root.appendChild(newTransactionNode);
		commitData();
		return transaction;
	}

	@Override
	public void update(Transaction transaction) {
		Element root = transactionsXmlStorageDocument.getDocumentElement();
				
		Element newTransactionNode =new DOMProcessor(transactionsXmlStorageDocument)
				.newElement("transaction")
				.addAttribute("id", transaction.getId())
				.addAttribute("userId", transaction.getUserId())
				.addAttribute("operation", transaction.getOperationType().name())
				.addChild("currency")
					.setValue(transaction.getCurrency().getAlias())
					.insertInto()
					.outerElement()
				.addChild("isConverted")
					.setValue(transaction.isConvertedToDefaultCurrency())
					.insertInto()
					.outerElement()
				.addChild("ammount")	
					.setValue(transaction.getAmmount())
					.insertInto()
					.outerElement()
				.addChild("ammountInDefaultCurrency")
					.setValue(transaction.getAmountInDefaultCurrency())
					.insertInto()
					.outerElement()
				.build();

		
		Element nodeOfCurrentTransaction = new DOMProcessor(transactionsXmlStorageDocument)
		.findChildByCriteria(root)
		.defineCriterias()
		.attributeIsEqualTo("id", transaction.getId())
		.enought()
		.getSingleResult();
	
		root.replaceChild(newTransactionNode,nodeOfCurrentTransaction);
		commitData();
		
	}

	@Override
	public void remove(Long key) {
		Element root = transactionsXmlStorageDocument.getDocumentElement();
		
		Element nodeOfCurrentTransaction = new DOMProcessor(transactionsXmlStorageDocument)
				.findChildByCriteria(root)
				.defineCriterias()
				.attributeIsEqualTo("id", key)
				.enought()
				.getSingleResult();
		
		root.removeChild(nodeOfCurrentTransaction);
		commitData();
		
	}

	@Override
	public List<Transaction> getAll() {
		Element root = transactionsXmlStorageDocument.getDocumentElement();
		return xmlToJavaDomMapper.mapXmlElementsToListOfClasses(root.getChildNodes()) ;
	}

	@Override
	public Transaction getById(Long key) {
		return xmlToJavaDomMapper.mapXmlElementToObject(new DOMProcessor(transactionsXmlStorageDocument)
		.findChildByCriteria(transactionsXmlStorageDocument.getDocumentElement())
		.defineCriterias()
		.attributeIsEqualTo("id", key)
		.enought()
		.getSingleResult());
		
		
	}

	@Override
	public List<Transaction> getAllForUser(Long userId) {
		return xmlToJavaDomMapper.mapXmlElementsToListOfClasses(new DOMProcessor(transactionsXmlStorageDocument)
				.findChildByCriteria(transactionsXmlStorageDocument.getDocumentElement())
				.defineCriterias()
				.attributeIsEqualTo("userId", userId)
				.enought()
				.getResultList());
	}

}
