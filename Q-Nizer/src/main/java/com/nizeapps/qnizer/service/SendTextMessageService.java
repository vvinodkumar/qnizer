package com.nizeapps.qnizer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nexmo.messaging.sdk.NexmoSmsClient;
import com.nexmo.messaging.sdk.NexmoSmsClientSSL;
import com.nexmo.messaging.sdk.SmsSubmissionResult;
import com.nexmo.messaging.sdk.messages.TextMessage;
import com.nizeapps.qnizer.dom.AppConfig;
import com.nizeapps.qnizer.dom.Customer;
import com.nizeapps.qnizer.util.LookupUtility;


/**
 * SendTextMessageHttps.java<br><br>
 *
 * An example of using the nexmo sms api to submit a simple text message ...<br><br>
 * Uses a https connection rather than the default http connection
 *
 * Created on 5 January 2011, 17:34
 *
 * @author  Paul Cook
 * @version 1.0
 */
@Service("SendTextMessageService")
public class SendTextMessageService {

	@Autowired
	AppConfig config;
	
  

  	private final String COUNTRY ="COUNTRY";
	private final String CUST_NAME_REPLACE ="~CustomerName~";
	private final String SERVICE_TIME_REPLACE ="~ServiceInTime~";
	private final String QNO = "~QNO~";
    public boolean sendTextNotification(Customer customer, boolean welcomeMessage) {

    	
    	//Dont want to send text messages during development.
      	if(!config.isSendText())
      		return true;
      	
        // Create a client for submitting to Nexmo
        NexmoSmsClient client = null;
        try {
            client = new NexmoSmsClientSSL(config.getApiKey(), config.getApiSecret());
        } catch (Exception e) {
            System.err.println("Failed to instanciate a Nexmo Client");
            e.printStackTrace();
            throw new RuntimeException("Failed to instanciate a Nexmo Client");
        }

        // Create a Text SMS Message request object ...

        String textToNo = LookupUtility.lookup(COUNTRY, "SG") + customer.getMobile();
        String textMessage = "";
        if(welcomeMessage) {
        	textMessage = config.getTextWelcomeMessage().replace(CUST_NAME_REPLACE, customer.getName());
        	textMessage = textMessage.replace(QNO, customer.getToken() + "");
        }
        else {
        	textMessage = config.getTextMessage().replace(CUST_NAME_REPLACE, customer.getName());
        	textMessage = textMessage.replace(SERVICE_TIME_REPLACE, customer.getServiceInTime()+"");
        	textMessage = textMessage.replace(QNO, customer.getToken() + "");
        }
        TextMessage message = new TextMessage(config.getTextFromNo(), textToNo, textMessage);

        // Use the Nexmo client to submit the Text Message ...
        SmsSubmissionResult[] results = null;
        try {
            results = client.submitMessage(message);
        } catch (Exception e) {
            System.err.println("Failed to communicate with the Nexmo Client");
            e.printStackTrace();
            throw new RuntimeException("Failed to communicate with the Nexmo Client");
        }
        
        
        // Evaluate the results of the submission attempt ...
        System.out.println("... Message submitted in [ " + results.length + " ] parts");
        for (int i=0;i<results.length;i++) {
            System.out.println("--------- part [ " + (i + 1) + " ] ------------");
            System.out.println("Status [ " + results[i].getStatus() + " ] ...");
            if (results[i].getStatus() == SmsSubmissionResult.STATUS_OK)
                System.out.println("SUCCESS");
            else if (results[i].getTemporaryError())
                System.out.println("TEMPORARY FAILURE - PLEASE RETRY");
            else
                System.out.println("SUBMISSION FAILED!");
            System.out.println("Message-Id [ " + results[i].getMessageId() + " ] ...");
            System.out.println("Error-Text [ " + results[i].getErrorText() + " ] ...");

            if (results[i].getMessagePrice() != null)
                System.out.println("Message-Price [ " + results[i].getMessagePrice() + " ] ...");
            if (results[i].getRemainingBalance() != null)
                System.out.println("Remaining-Balance [ " + results[i].getRemainingBalance() + " ] ...");
        }
        return true;
    }
    
    

}
