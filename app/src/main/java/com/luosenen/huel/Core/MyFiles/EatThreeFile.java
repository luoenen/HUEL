package com.luosenen.huel.Core.MyFiles;

import java.io.Serializable;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BmobFile;

public class EatThreeFile extends BmobObject implements Serializable {

    public String School;
    public String College;
    public String Address;
    public String Title;
    public String Desc;
    public String Name;
    public String Account;
    public String Tell;
    public String Sex;
    public double Price;
    public String floot;
    public BmobFile icon;


    public String getSchool() {
        return School;
    }

    public void setSchool(String school) {
        School = school;
    }

    public String getCollege() {
        return College;
    }

    public void setCollege(String college) {
        College = college;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDesc() {
        return Desc;
    }

    public void setDesc(String desc) {
        Desc = desc;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getAccount() {
        return Account;
    }

    public void setAccount(String account) {
        Account = account;
    }

    public String getTell() {
        return Tell;
    }

    public void setTell(String tell) {
        Tell = tell;
    }

    public String getSex() {
        return Sex;
    }

    public void setSex(String sex) {
        Sex = sex;
    }

    public String getFloot() {
        return floot;
    }

    public void setFloot(String floot) {
        this.floot = floot;
    }

    public double getPrice() {
        return Price;
    }

    public void setPrice(double price) {
        Price = price;
    }

    public BmobFile getIcon() {
        return icon;
    }

    public void setIcon(BmobFile icon) {
        this.icon = icon;
    }
}
