package com.sas.mq.client;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class MessageQueueServiceTest {

	@Autowired
//	MessageQueueService messageQueueService;
	
	@Test
    public void sendSwiftMsg() {
    	String xml = "<aml>" +
    			" <interface_name>AML_SWIFTCheck</interface_name>" +
    			"            <calling_system>01</calling_system>" +
    			"            <screen_process>1</screen_process>" +       
    			"            <calling_user>10101</calling_user>" +
    			"            <business_unit_id>10</business_unit_id>" +
    			"            <branch_number>10</branch_number>" +
    			"            <transaction_date>20171012</transaction_date>" +
    			"            <unique_key>20171012016</unique_key>" +
    			"            <swift_rje>{1:F21ICBCTWTPA0028849467815}{4:{177:1311071539}{451:0}{108:                }}{1:F01ICBCTWTPA0028849467815}{2:I103BKTWTWTPX011N}{3:{108:                }}{4:" +
    			":20:AAAKTS30011805" +
    			":23B:CRED" +
    			":32A:131107USD1351565,10" +
    			":50K:/30414175" +
    			"CHINA STEEL CORP." +
    			"NO.1 CHUNGKANG RD HSIAOKANG DIST." +
    			"KAOHSIUNG CITY 812 TAIWAN (R.O.C)" +
    			":53A:ICBCTWTP011" +
    			":57A:BKTWTWTP011" +
    			":59:/011007304542" +
    			"CHINA STEEL CORPORATION" +
    			":71A:SHA" +
    			"-}{5:{MAC:00000000}{CHK:7C43A73ACE58}}</swift_rje>" +
    			"</aml>";
//    	messageQueueService.sendMessage("VA.SASAMLOB.SWT.HK.QL", xml);
    }
}
