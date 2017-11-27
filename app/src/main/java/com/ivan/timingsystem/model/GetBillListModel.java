package com.ivan.timingsystem.model;

import java.util.List;



public class GetBillListModel
{


    /**
     * ResultStatus : Success
     * Result : [{"Car_ID":3,"TestType_ID":9,"dStartTime":"08:00:00","dEndTime":"11:00:00","BillStatus":2,"cMemo":null,"BillCode":"31509370515182","iAppTimeNum":3,"MakeUserID":6,"iAppMon":630,"Car_Brand":"这f54587","Car_SortNo":2,"User_Name":"学员五","User_Phone":"56897854166"},{"Car_ID":2,"TestType_ID":9,"dStartTime":"08:00:00","dEndTime":"13:00:00","BillStatus":2,"cMemo":"1,2,3","BillCode":"11509370499750","iAppTimeNum":5,"MakeUserID":1,"iAppMon":1050,"Car_Brand":null,"Car_SortNo":null,"User_Name":"黄教练","User_Phone":"13456954587"}]
     */

    private String ResultStatus;
    private List<ResultBean> Result;

    public String getResultStatus() {
        return ResultStatus;
    }

    public void setResultStatus(String ResultStatus) {
        this.ResultStatus = ResultStatus;
    }

    public List<ResultBean> getResult() {
        return Result;
    }

    public void setResult(List<ResultBean> Result) {
        this.Result = Result;
    }

    public static class ResultBean {
        /**
         * Car_ID : 3
         * TestType_ID : 9
         * dStartTime : 08:00:00
         * dEndTime : 11:00:00
         * BillStatus : 2
         * cMemo : null
         * BillCode : 31509370515182
         * iAppTimeNum : 3
         * MakeUserID : 6
         * iAppMon : 630
         * Car_Brand : 这f54587
         * Car_SortNo : 2
         * User_Name : 学员五
         * User_Phone : 56897854166
         */

        private int Car_ID;
        private int TestType_ID;
        private String dStartTime;
        private String dEndTime;
        private int BillStatus;
        private Object cMemo;
        private String BillCode;
        private int iAppTimeNum;
        private int MakeUserID;
        private int iAppMon;
        private String Car_Brand;
        private int Car_SortNo;
        private String User_Name;
        private String User_Phone;

        public int getCar_ID() {
            return Car_ID;
        }

        public void setCar_ID(int Car_ID) {
            this.Car_ID = Car_ID;
        }

        public int getTestType_ID() {
            return TestType_ID;
        }

        public void setTestType_ID(int TestType_ID) {
            this.TestType_ID = TestType_ID;
        }

        public String getDStartTime() {
            return dStartTime;
        }

        public void setDStartTime(String dStartTime) {
            this.dStartTime = dStartTime;
        }

        public String getDEndTime() {
            return dEndTime;
        }

        public void setDEndTime(String dEndTime) {
            this.dEndTime = dEndTime;
        }

        public int getBillStatus() {
            return BillStatus;
        }

        public void setBillStatus(int BillStatus) {
            this.BillStatus = BillStatus;
        }

        public Object getCMemo() {
            return cMemo;
        }

        public void setCMemo(Object cMemo) {
            this.cMemo = cMemo;
        }

        public String getBillCode() {
            return BillCode;
        }

        public void setBillCode(String BillCode) {
            this.BillCode = BillCode;
        }

        public int getIAppTimeNum() {
            return iAppTimeNum;
        }

        public void setIAppTimeNum(int iAppTimeNum) {
            this.iAppTimeNum = iAppTimeNum;
        }

        public int getMakeUserID() {
            return MakeUserID;
        }

        public void setMakeUserID(int MakeUserID) {
            this.MakeUserID = MakeUserID;
        }

        public int getIAppMon() {
            return iAppMon;
        }

        public void setIAppMon(int iAppMon) {
            this.iAppMon = iAppMon;
        }

        public String getCar_Brand() {
            return Car_Brand;
        }

        public void setCar_Brand(String Car_Brand) {
            this.Car_Brand = Car_Brand;
        }

        public int getCar_SortNo() {
            return Car_SortNo;
        }

        public void setCar_SortNo(int Car_SortNo) {
            this.Car_SortNo = Car_SortNo;
        }

        public String getUser_Name() {
            return User_Name;
        }

        public void setUser_Name(String User_Name) {
            this.User_Name = User_Name;
        }

        public String getUser_Phone() {
            return User_Phone;
        }

        public void setUser_Phone(String User_Phone) {
            this.User_Phone = User_Phone;
        }
    }
}

