package com.vrainz.co.claro.ws.syncsubsc;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.vrainz.co.claro.ws.syncsubsc.SyncOrderRelationshipRequest.OPERATION;
import com.vrainz.co.claro.ws.syncsubsc.generated.UserID;

public class SyncOrderRelationshipRequestParse {

	public SyncOrderRelationshipRequestParse() {
		// TODO Auto-generated constructor stub
	}

	private Object parse(InputStreamReader inputStreamReader) throws JAXBException {
			//TODO completar
		JAXBContext jaxbContext = JAXBContext.newInstance(SyncOrderRelationshipRequest.class);

		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		SyncOrderRelationshipRequest request = (SyncOrderRelationshipRequest) jaxbUnmarshaller.unmarshal(inputStreamReader);

		return request;
	}


	public SyncOrderRelationshipRequest parseToSyncOrderRelationshipRequest(String requestSoap)  throws ParserConfigurationException, SAXException, IOException {
		SyncOrderRelationshipRequest beanResult = new SyncOrderRelationshipRequest();
		
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		ByteArrayInputStream input =  new ByteArrayInputStream(requestSoap.getBytes());
		Document doc = builder.parse(input);
		Element root = doc.getDocumentElement();
		NodeList bodyTags = root.getElementsByTagName("soapenv:Body");
		Node bodyNode =bodyTags.item(0);
		
		Node nodeSyncOrderRelationship = getNodeChildByName(bodyNode,"ns1:syncOrderRelationship");
		Node nodeUser = getNodeChildByName(nodeSyncOrderRelationship,"ns1:userID");
		Node nodeID = getNodeChildByName(nodeUser,"ID");
		Node nodeProductID = getNodeChildByName(nodeSyncOrderRelationship,"ns1:productID");
		Node nodeExtensionInfo  = getNodeChildByName(nodeSyncOrderRelationship,"ns1:extensionInfo");
		
		NodeList infoNodes = nodeExtensionInfo.getChildNodes();
		for(int x = 0; x < infoNodes.getLength();  x++){
			Node nodeParam = infoNodes.item(x);
		
			Node nodeOperation = getNodeChildByName(nodeParam,"key");
			if(nodeOperation == null){
				continue;
			}
			if("commandForSub".equals(nodeOperation.getTextContent())){
				beanResult.setOperation(OPERATION.SUBSCRIBE);
				break;
			} else 	if("commandForUnsub".equals(nodeOperation.getTextContent())){
				beanResult.setOperation(OPERATION.UNSUBSCRIBE);
				break;
			}
		}
		
		UserID userID = new UserID();
		userID.setID(nodeID.getTextContent());
		beanResult.setUserID(userID);
		beanResult.setProductID(nodeProductID.getTextContent());

		return beanResult;
	}
	
	private  Node getNodeChildByName(Node node, String nodeName){
		if(!node.hasChildNodes()){
			return null;
		}
		NodeList childs = node.getChildNodes();
		for(int x = 0; x < childs.getLength(); x++){
			Node item = childs.item(x);
			if(nodeName.equals(item.getNodeName()) ){
				return item;
				
			}
		}		
		return null;
	}
}
