package com.nizeapps.qnizer.service;

import com.nizeapps.qnizer.dom.NizerHeader;
import com.nizeapps.qnizer.util.DateUtility;

public class BaseService {
	
	protected NizerHeader setAllHeaders(NizerHeader header, String user) {
		header.setCreatedBy(user);
		header.setModifiedBy(user);
		header.setModifiedDateTime(DateUtility.getBusinessDateTime());
		header.setCreatedDateTime(DateUtility.getBusinessDateTime());
		return header;
	}
	
	protected NizerHeader setModifyHeaders(NizerHeader header, String user) {

		header.setModifiedBy(user);
		header.setModifiedDateTime(DateUtility.getBusinessDateTime());
		return header;
	}
	

}
