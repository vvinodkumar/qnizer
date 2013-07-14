package com.nizeapps.qnizer.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.codec.binary.Base64;
import org.jasypt.util.password.StrongPasswordEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cribbstechnologies.clients.mandrill.exception.RequestFailedException;
import com.cribbstechnologies.clients.mandrill.model.MandrillMessage;
import com.cribbstechnologies.clients.mandrill.model.MandrillRecipient;
import com.cribbstechnologies.clients.mandrill.model.MandrillTemplatedMessageRequest;
import com.cribbstechnologies.clients.mandrill.model.MergeVar;
import com.cribbstechnologies.clients.mandrill.model.TemplateContent;
import com.cribbstechnologies.clients.mandrill.request.MandrillMessagesRequest;
import com.nizeapps.qnizer.dom.MailConfig;
import com.nizeapps.qnizer.dom.NizerUser;
import com.nizeapps.qnizer.dom.UserStatus;
import com.nizeapps.qnizer.exception.NizerApplicationException;
import com.nizeapps.qnizer.repository.BusinessRepository;
import com.nizeapps.qnizer.util.DateUtility;

@Service("RegistrationService")
public class RegistrationServiceImpl extends BaseService implements RegistrationService {

	@Autowired
	private BusinessRepository businessRepo;
	
	@Autowired
	private MandrillMessagesRequest messagesRequest;
	
	@Autowired
	private MailConfig config;
	
	
	 
	
	
	@Override
	public void signupUser(NizerUser user) throws NizerApplicationException {
		
		if(businessRepo.findBusinessUserByUserName(user.getUserName())!=null) {
    		throw new NizerApplicationException("Email Id used is already registered.",true);
    	}

		StrongPasswordEncryptor encryptor = new StrongPasswordEncryptor();
		user.setPassword((encryptor.encryptPassword(user.getPassword())));
		String activationKey = UUID.randomUUID().toString();
		user.setActivationKey(encryptor.encryptPassword(activationKey));
		user.setStatus(UserStatus.INACTIVE);
		setAllHeaders(user.getBusiness().getBusinessAddress(), user.getUserName());
		setAllHeaders(user.getBusiness(), user.getUserName());
		setAllHeaders(user, user.getUserName());
		
		businessRepo.registerBusinessUser(user);
		boolean initiateRollback = false;
		try {
			sendRegistrationEmail(user,activationKey);
		}catch(RequestFailedException re) {
			initiateRollback = true;
			throw new NizerApplicationException(re);
		}
		catch(Exception e) {
			initiateRollback = true;
			throw new NizerApplicationException(e);
		}
		finally {
			if(initiateRollback) {
				businessRepo.removeBusinessAddress(user.getBusiness().getBusinessAddress());
				businessRepo.removeBusiness(user.getBusiness());
				businessRepo.removeBusinessUser(user);
			}
		}
 	}
	
	
	private void sendRegistrationEmail(NizerUser user, String activationKey) throws RequestFailedException {
		 if(!config.isSendMail())
		       return;
	
		MandrillTemplatedMessageRequest templateRequest = new MandrillTemplatedMessageRequest();
		MandrillMessage message = new MandrillMessage();
		
		Map<String, String> headers = new HashMap<String, String>();
		message.setFrom_email(config.getRegistrationFromEmailAddress());
		message.setFrom_name(config.getRegistrationFromName());
		message.setHeaders(headers);
		message.setSubject(config.getRegistrationSubject());
		MandrillRecipient[] recipients = new MandrillRecipient[]{new MandrillRecipient(user.getFirstName() + " " + user.getLastName(), user.getUserName())};
		message.setTo(recipients);
		message.setTrack_clicks(true);
		message.setTrack_opens(true);
		templateRequest.setMessage(message);
		List<TemplateContent> content = new ArrayList<TemplateContent>();
		templateRequest.setTemplate_content(content);
		templateRequest.setTemplate_name(config.getRegistrationTemplate());
        List<MergeVar> globalMergeVars = new ArrayList<MergeVar>();
        globalMergeVars.add(new MergeVar("Name", user.getFirstName()));
        globalMergeVars.add(new MergeVar("activationurl", config.getActivationURL()+Base64.encodeBase64URLSafeString(user.getUserName().getBytes())+"/"+Base64.encodeBase64URLSafeString(activationKey.getBytes())));
        globalMergeVars.add(new MergeVar("unsubscribeurl", config.getUnsubscribeURL()+Base64.encodeBase64URLSafeString(user.getUserName().getBytes())));
        globalMergeVars.add(new MergeVar("customeremail", user.getUserName()));
        globalMergeVars.add(new MergeVar("copyrightmessage", config.getCopyrightMessage()));
        message.setGlobal_merge_vars(globalMergeVars);
        messagesRequest.sendTemplatedMessage(templateRequest);
     	
	}


	@Override
	public void activateUser(String emailId, String activationKey)
			throws NizerApplicationException {
		 NizerUser user = businessRepo.findBusinessUserByUserName(emailId);
		 
		 if(user==null) {
	    	throw new NizerApplicationException("OOPS!! We have a problem with your activation. Please contact support.",true , "Email Id  " + emailId + " Not Found!!!");
	     }
		
		 if(!user.getStatus().equals(UserStatus.INACTIVE)) {
			 throw new NizerApplicationException("OOPS!! We have a problem with your activation. The account you are trying to activate is already active. If you think you have not activated it earlier, please contact support immediately.",true);
		 }
		 
		 String encryptedActivationKey = user.getActivationKey();
		 StrongPasswordEncryptor encryptor = new StrongPasswordEncryptor();
		 if(encryptor.checkPassword(activationKey, encryptedActivationKey)) {
			 user.setActivationKey(null);
			 user.setActivationDateTime(DateUtility.getBusinessDateTime());
			 setModifyHeaders(user, user.getUserName());
			 user.setStatus(UserStatus.ACTIVE);
			 businessRepo.activateUser(user);

		 } else {
			 throw new NizerApplicationException("OOPS!! We have a problem with your activation. Please contact support.",true, "Activation Key " + activationKey + " appears to have been tampered");
		 }
		 
		 
		
	}

}
