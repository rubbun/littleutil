package com.littleutil.bean;

public class BookingReqBean {

	public String CUS_ID,NAME,EMAIL,MOBILE,ADDRESS1,AREA1,CITY1,
	STATE1,ADDRESS2,AREA2,CITY2,STATE2,PIN,PASSWORD,ID,SERVICE_NO,STATUS,
	REQUEST_DATE,CLOSE_DATE,Agent_ID,COST,VENDOR_SERCHARGE,CUS_FEEDBACK,CUS_DESC,
	LABOR_COST,MATERIAL_COST,COMMISION,INTER_FEEDBACK,APPOINTMENT_DATE,APPOINTMENT_TIME,AGENT_NAME;

	public String Service_name;
	
	public BookingReqBean(String cUS_ID, String nAME, String eMAIL, String mOBILE, String aDDRESS1, String aREA1, String cITY1, String sTATE1, String aDDRESS2, String aREA2, String cITY2, String sTATE2, String pIN, String pASSWORD, String iD, String sERVICE_NO, String sTATUS, String rEQUEST_DATE, String cLOSE_DATE, String agent_ID, String cOST, String vENDOR_SERCHARGE, String cUS_FEEDBACK, String cUS_DESC, String lABOR_COST, String mATERIAL_COST, String cOMMISION, String iNTER_FEEDBACK, String aPPOINTMENT_DATE, String aPPOINTMENT_TIME, String aGENT_NAME,String Service_name) {
		super();
		CUS_ID = cUS_ID;
		NAME = nAME;
		EMAIL = eMAIL;
		MOBILE = mOBILE;
		ADDRESS1 = aDDRESS1;
		AREA1 = aREA1;
		CITY1 = cITY1;
		STATE1 = sTATE1;
		ADDRESS2 = aDDRESS2;
		AREA2 = aREA2;
		CITY2 = cITY2;
		STATE2 = sTATE2;
		PIN = pIN;
		PASSWORD = pASSWORD;
		ID = iD;
		SERVICE_NO = sERVICE_NO;
		STATUS = sTATUS;
		REQUEST_DATE = rEQUEST_DATE;
		CLOSE_DATE = cLOSE_DATE;
		Agent_ID = agent_ID;
		COST = cOST;
		VENDOR_SERCHARGE = vENDOR_SERCHARGE;
		CUS_FEEDBACK = cUS_FEEDBACK;
		CUS_DESC = cUS_DESC;
		LABOR_COST = lABOR_COST;
		MATERIAL_COST = mATERIAL_COST;
		COMMISION = cOMMISION;
		INTER_FEEDBACK = iNTER_FEEDBACK;
		APPOINTMENT_DATE = aPPOINTMENT_DATE;
		APPOINTMENT_TIME = aPPOINTMENT_TIME;
		AGENT_NAME = aGENT_NAME;
		this.Service_name = Service_name;
	}
	
	public String getService_name() {
		return Service_name;
	}



	public void setService_name(String service_name) {
		Service_name = service_name;
	}



	public String getCUS_ID() {
		return CUS_ID;
	}

	public void setCUS_ID(String cUS_ID) {
		CUS_ID = cUS_ID;
	}

	public String getNAME() {
		return NAME;
	}

	public void setNAME(String nAME) {
		NAME = nAME;
	}

	public String getEMAIL() {
		return EMAIL;
	}

	public void setEMAIL(String eMAIL) {
		EMAIL = eMAIL;
	}

	public String getMOBILE() {
		return MOBILE;
	}

	public void setMOBILE(String mOBILE) {
		MOBILE = mOBILE;
	}

	public String getADDRESS1() {
		return ADDRESS1;
	}

	public void setADDRESS1(String aDDRESS1) {
		ADDRESS1 = aDDRESS1;
	}

	public String getAREA1() {
		return AREA1;
	}

	public void setAREA1(String aREA1) {
		AREA1 = aREA1;
	}

	public String getCITY1() {
		return CITY1;
	}

	public void setCITY1(String cITY1) {
		CITY1 = cITY1;
	}

	public String getSTATE1() {
		return STATE1;
	}

	public void setSTATE1(String sTATE1) {
		STATE1 = sTATE1;
	}

	public String getADDRESS2() {
		return ADDRESS2;
	}

	public void setADDRESS2(String aDDRESS2) {
		ADDRESS2 = aDDRESS2;
	}

	public String getAREA2() {
		return AREA2;
	}

	public void setAREA2(String aREA2) {
		AREA2 = aREA2;
	}

	public String getCITY2() {
		return CITY2;
	}

	public void setCITY2(String cITY2) {
		CITY2 = cITY2;
	}

	public String getSTATE2() {
		return STATE2;
	}

	public void setSTATE2(String sTATE2) {
		STATE2 = sTATE2;
	}

	public String getPIN() {
		return PIN;
	}

	public void setPIN(String pIN) {
		PIN = pIN;
	}

	public String getPASSWORD() {
		return PASSWORD;
	}

	public void setPASSWORD(String pASSWORD) {
		PASSWORD = pASSWORD;
	}

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public String getSERVICE_NO() {
		return SERVICE_NO;
	}

	public void setSERVICE_NO(String sERVICE_NO) {
		SERVICE_NO = sERVICE_NO;
	}

	public String getSTATUS() {
		return STATUS;
	}

	public void setSTATUS(String sTATUS) {
		STATUS = sTATUS;
	}

	public String getREQUEST_DATE() {
		return REQUEST_DATE;
	}

	public void setREQUEST_DATE(String rEQUEST_DATE) {
		REQUEST_DATE = rEQUEST_DATE;
	}

	public String getCLOSE_DATE() {
		return CLOSE_DATE;
	}

	public void setCLOSE_DATE(String cLOSE_DATE) {
		CLOSE_DATE = cLOSE_DATE;
	}

	public String getAgent_ID() {
		return Agent_ID;
	}

	public void setAgent_ID(String agent_ID) {
		Agent_ID = agent_ID;
	}

	public String getCOST() {
		return COST;
	}

	public void setCOST(String cOST) {
		COST = cOST;
	}

	public String getVENDOR_SERCHARGE() {
		return VENDOR_SERCHARGE;
	}

	public void setVENDOR_SERCHARGE(String vENDOR_SERCHARGE) {
		VENDOR_SERCHARGE = vENDOR_SERCHARGE;
	}

	public String getCUS_FEEDBACK() {
		return CUS_FEEDBACK;
	}

	public void setCUS_FEEDBACK(String cUS_FEEDBACK) {
		CUS_FEEDBACK = cUS_FEEDBACK;
	}

	public String getCUS_DESC() {
		return CUS_DESC;
	}

	public void setCUS_DESC(String cUS_DESC) {
		CUS_DESC = cUS_DESC;
	}

	public String getLABOR_COST() {
		return LABOR_COST;
	}

	public void setLABOR_COST(String lABOR_COST) {
		LABOR_COST = lABOR_COST;
	}

	public String getMATERIAL_COST() {
		return MATERIAL_COST;
	}

	public void setMATERIAL_COST(String mATERIAL_COST) {
		MATERIAL_COST = mATERIAL_COST;
	}

	public String getCOMMISION() {
		return COMMISION;
	}

	public void setCOMMISION(String cOMMISION) {
		COMMISION = cOMMISION;
	}

	public String getINTER_FEEDBACK() {
		return INTER_FEEDBACK;
	}

	public void setINTER_FEEDBACK(String iNTER_FEEDBACK) {
		INTER_FEEDBACK = iNTER_FEEDBACK;
	}

	public String getAPPOINTMENT_DATE() {
		return APPOINTMENT_DATE;
	}

	public void setAPPOINTMENT_DATE(String aPPOINTMENT_DATE) {
		APPOINTMENT_DATE = aPPOINTMENT_DATE;
	}

	public String getAPPOINTMENT_TIME() {
		return APPOINTMENT_TIME;
	}

	public void setAPPOINTMENT_TIME(String aPPOINTMENT_TIME) {
		APPOINTMENT_TIME = aPPOINTMENT_TIME;
	}

	public String getAGENT_NAME() {
		return AGENT_NAME;
	}

	public void setAGENT_NAME(String aGENT_NAME) {
		AGENT_NAME = aGENT_NAME;
	}
}
