/**
 * Copyright 2018 bejson.com
 */
package com.ivan.timingsystem.model;

import java.util.Date;
import java.util.List;

/**
 * Auto-generated: 2018-01-02 23:5:3
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class GetBillListModel {

    private String ResultStatus;
    private List<ResultBean> Result;

    public void setResultStatus(String ResultStatus) {
        this.ResultStatus = ResultStatus;
    }

    public String getResultStatus() {
        return ResultStatus;
    }

    public void setResult(List<ResultBean> Result) {
        this.Result = Result;
    }

    public List<ResultBean> getResult() {
        return Result;
    }

    /**
     * Auto-generated: 2018-01-02 23:5:59
     *
     * @author bejson.com (i@bejson.com)
     * @website http://www.bejson.com/java2pojo/
     */
    public class ResultBean {

        private int ID;
        private int Car_ID;
        private int TestType_ID;
        private boolean bReservation;
        private String dModDate;
        private Date dReservationDay;
        private String dStartTime;
        private String dEndTime;
        private boolean bDelFlag;
        private int BillStatus;
        private String cMemo;
        private String BillCode;
        private int iAppTimeNum;
        private int MakeUserID;
        private Date dCreateDate;
        private double iTotalMon;
        private double iRelMon;
        private int iIntegralUse;
        private String PayWay;
        private boolean bReturn;
        private Date PayDate;
        private String RetrunDate;
        private String WeiXinOrderID;
        private String CoachUserName;
        private String ContactPhone;
        private String AppType;
        private Date dOverdueTime;
        private boolean bModify;
        private boolean bOverTime;
        private int iPracticeNum;
        private String PracticeUserIDs;
        private String PracticeUserName;
        private String BJPracticeUserIDs;
        private String BJPracticeUserName;
        private boolean bOverTimeFree;
        private String iOverTimeMon;
        private boolean bOverPay;
        private boolean bAllowMoreCar;
        private Date dInTime;
        private String dOutTime;
        private double iPreMon;
        private int Car_SortNo;
        private int cRealCarInfo;

        public int getcRealCarInfo() {
            return cRealCarInfo;
        }

        public void setcRealCarInfo(int cRealCarInfo) {
            this.cRealCarInfo = cRealCarInfo;
        }

        private String Car_Type;
        private String Car_Brand;
        private String cCode;
        private String User_Name;
        private String User_Phone;
        private String DrivSch_Name;
        private String BillStatusType;
        private String OverType;
        private long iOverMINUTE;
        private Date dPreEndTime;

        public long getiOverMINUTE() {
            return iOverMINUTE;
        }

        public void setiOverMINUTE(long iOverMINUTE) {
            this.iOverMINUTE = iOverMINUTE;
        }

        public Date getdPreEndTime() {
            return dPreEndTime;
        }

        public void setdPreEndTime(Date dPreEndTime) {
            this.dPreEndTime = dPreEndTime;
        }

        public void setID(int ID) {
            this.ID = ID;
        }

        public int getID() {
            return ID;
        }

        public void setCar_ID(int Car_ID) {
            this.Car_ID = Car_ID;
        }

        public int getCar_ID() {
            return Car_ID;
        }

        public void setTestType_ID(int TestType_ID) {
            this.TestType_ID = TestType_ID;
        }

        public int getTestType_ID() {
            return TestType_ID;
        }

        public void setBReservation(boolean bReservation) {
            this.bReservation = bReservation;
        }

        public boolean getBReservation() {
            return bReservation;
        }

        public void setDModDate(String dModDate) {
            this.dModDate = dModDate;
        }

        public String getDModDate() {
            return dModDate;
        }

        public void setDReservationDay(Date dReservationDay) {
            this.dReservationDay = dReservationDay;
        }

        public Date getDReservationDay() {
            return dReservationDay;
        }

        public void setDStartTime(String dStartTime) {
            this.dStartTime = dStartTime;
        }

        public String getDStartTime() {
            return dStartTime;
        }

        public void setDEndTime(String dEndTime) {
            this.dEndTime = dEndTime;
        }

        public String getDEndTime() {
            return dEndTime;
        }

        public void setBDelFlag(boolean bDelFlag) {
            this.bDelFlag = bDelFlag;
        }

        public boolean getBDelFlag() {
            return bDelFlag;
        }

        public void setBillStatus(int BillStatus) {
            this.BillStatus = BillStatus;
        }

        public int getBillStatus() {
            return BillStatus;
        }

        public void setCMemo(String cMemo) {
            this.cMemo = cMemo;
        }

        public String getCMemo() {
            return cMemo;
        }

        public void setBillCode(String BillCode) {
            this.BillCode = BillCode;
        }

        public String getBillCode() {
            return BillCode;
        }

        public void setIAppTimeNum(int iAppTimeNum) {
            this.iAppTimeNum = iAppTimeNum;
        }

        public int getIAppTimeNum() {
            return iAppTimeNum;
        }

        public void setMakeUserID(int MakeUserID) {
            this.MakeUserID = MakeUserID;
        }

        public int getMakeUserID() {
            return MakeUserID;
        }

        public void setDCreateDate(Date dCreateDate) {
            this.dCreateDate = dCreateDate;
        }

        public Date getDCreateDate() {
            return dCreateDate;
        }

        public void setITotalMon(double iTotalMon) {
            this.iTotalMon = iTotalMon;
        }

        public double getITotalMon() {
            return iTotalMon;
        }

        public void setIRelMon(double iRelMon) {
            this.iRelMon = iRelMon;
        }

        public double getIRelMon() {
            return iRelMon;
        }

        public void setIIntegralUse(int iIntegralUse) {
            this.iIntegralUse = iIntegralUse;
        }

        public int getIIntegralUse() {
            return iIntegralUse;
        }

        public void setPayWay(String PayWay) {
            this.PayWay = PayWay;
        }

        public String getPayWay() {
            return PayWay;
        }

        public void setBReturn(boolean bReturn) {
            this.bReturn = bReturn;
        }

        public boolean getBReturn() {
            return bReturn;
        }

        public void setPayDate(Date PayDate) {
            this.PayDate = PayDate;
        }

        public Date getPayDate() {
            return PayDate;
        }

        public void setRetrunDate(String RetrunDate) {
            this.RetrunDate = RetrunDate;
        }

        public String getRetrunDate() {
            return RetrunDate;
        }

        public void setWeiXinOrderID(String WeiXinOrderID) {
            this.WeiXinOrderID = WeiXinOrderID;
        }

        public String getWeiXinOrderID() {
            return WeiXinOrderID;
        }

        public void setCoachUserName(String CoachUserName) {
            this.CoachUserName = CoachUserName;
        }

        public String getCoachUserName() {
            return CoachUserName;
        }

        public void setContactPhone(String ContactPhone) {
            this.ContactPhone = ContactPhone;
        }

        public String getContactPhone() {
            return ContactPhone;
        }

        public void setAppType(String AppType) {
            this.AppType = AppType;
        }

        public String getAppType() {
            return AppType;
        }

        public void setDOverdueTime(Date dOverdueTime) {
            this.dOverdueTime = dOverdueTime;
        }

        public Date getDOverdueTime() {
            return dOverdueTime;
        }

        public void setBModify(boolean bModify) {
            this.bModify = bModify;
        }

        public boolean getBModify() {
            return bModify;
        }

        public void setBOverTime(boolean bOverTime) {
            this.bOverTime = bOverTime;
        }

        public boolean getBOverTime() {
            return bOverTime;
        }

        public void setIPracticeNum(int iPracticeNum) {
            this.iPracticeNum = iPracticeNum;
        }

        public int getIPracticeNum() {
            return iPracticeNum;
        }

        public void setPracticeUserIDs(String PracticeUserIDs) {
            this.PracticeUserIDs = PracticeUserIDs;
        }

        public String getPracticeUserIDs() {
            return PracticeUserIDs;
        }

        public void setPracticeUserName(String PracticeUserName) {
            this.PracticeUserName = PracticeUserName;
        }

        public String getPracticeUserName() {
            return PracticeUserName;
        }

        public void setBJPracticeUserIDs(String BJPracticeUserIDs) {
            this.BJPracticeUserIDs = BJPracticeUserIDs;
        }

        public String getBJPracticeUserIDs() {
            return BJPracticeUserIDs;
        }

        public void setBJPracticeUserName(String BJPracticeUserName) {
            this.BJPracticeUserName = BJPracticeUserName;
        }

        public String getBJPracticeUserName() {
            return BJPracticeUserName;
        }

        public void setBOverTimeFree(boolean bOverTimeFree) {
            this.bOverTimeFree = bOverTimeFree;
        }

        public boolean getBOverTimeFree() {
            return bOverTimeFree;
        }

        public void setIOverTimeMon(String iOverTimeMon) {
            this.iOverTimeMon = iOverTimeMon;
        }

        public String getIOverTimeMon() {
            return iOverTimeMon;
        }

        public void setBOverPay(boolean bOverPay) {
            this.bOverPay = bOverPay;
        }

        public boolean getBOverPay() {
            return bOverPay;
        }

        public void setBAllowMoreCar(boolean bAllowMoreCar) {
            this.bAllowMoreCar = bAllowMoreCar;
        }

        public boolean getBAllowMoreCar() {
            return bAllowMoreCar;
        }

        public void setDInTime(Date dInTime) {
            this.dInTime = dInTime;
        }

        public Date getDInTime() {
            return dInTime;
        }

        public void setDOutTime(String dOutTime) {
            this.dOutTime = dOutTime;
        }

        public String getDOutTime() {
            return dOutTime;
        }

        public void setIPreMon(double iPreMon) {
            this.iPreMon = iPreMon;
        }

        public double getIPreMon() {
            return iPreMon;
        }

        public void setCar_SortNo(int Car_SortNo) {
            this.Car_SortNo = Car_SortNo;
        }

        public int getCar_SortNo() {
            return Car_SortNo;
        }

        public void setCar_Type(String Car_Type) {
            this.Car_Type = Car_Type;
        }

        public String getCar_Type() {
            return Car_Type;
        }

        public void setCar_Brand(String Car_Brand) {
            this.Car_Brand = Car_Brand;
        }

        public String getCar_Brand() {
            return Car_Brand;
        }

        public void setCCode(String cCode) {
            this.cCode = cCode;
        }

        public String getCCode() {
            return cCode;
        }

        public void setUser_Name(String User_Name) {
            this.User_Name = User_Name;
        }

        public String getUser_Name() {
            return User_Name;
        }

        public void setUser_Phone(String User_Phone) {
            this.User_Phone = User_Phone;
        }

        public String getUser_Phone() {
            return User_Phone;
        }

        public void setDrivSch_Name(String DrivSch_Name) {
            this.DrivSch_Name = DrivSch_Name;
        }

        public String getDrivSch_Name() {
            return DrivSch_Name;
        }

        public void setBillStatusType(String BillStatusType) {
            this.BillStatusType = BillStatusType;
        }

        public String getBillStatusType() {
            return BillStatusType;
        }

        public void setOverType(String OverType) {
            this.OverType = OverType;
        }

        public String getOverType() {
            return OverType;
        }

    }
}