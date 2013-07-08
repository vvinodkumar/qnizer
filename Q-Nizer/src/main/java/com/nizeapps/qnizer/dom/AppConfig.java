package com.nizeapps.qnizer.dom;

public class AppConfig implements Nizer{
	
	private String textFromNo ;
	private String textMessage;
	private String textWelcomeMessage;
	private String apiKey;
	private String apiSecret;
	private boolean sendText;
	
	public String getTextFromNo() {
		return textFromNo;
	}
	public void setTextFromNo(String textFromNo) {
		this.textFromNo = textFromNo;
	}
	public String getTextMessage() {
		return textMessage;
	}
	public void setTextMessage(String textMessage) {
		this.textMessage = textMessage;
	}
	public String getApiSecret() {
		return apiSecret;
	}
	public void setApiSecret(String apiSecret) {
		this.apiSecret = apiSecret;
	}
	public String getApiKey() {
		return apiKey;
	}
	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}
	public boolean isSendText() {
		return sendText;
	}
	public void setSendText(boolean sendText) {
		this.sendText = sendText;
	}
	public String getTextWelcomeMessage() {
		return textWelcomeMessage;
	}
	public void setTextWelcomeMessage(String textWelcomeMessage) {
		this.textWelcomeMessage = textWelcomeMessage;
	}

}
