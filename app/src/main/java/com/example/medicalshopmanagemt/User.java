package com.example.medicalshopmanagemt;

public class User {

    private String name, email, userId, gender, address, age, pincode, phone, aadhaar;



    int check=0;

    public User() {
    }

    public User(String name, String email, String userId, String gender,String address, String age, String pincode, String phone, String aadhaar,int check) {
        this.name = name;
        this.email = email;
      //  this.imageURL = imageURL;
        this.userId = userId;
        this.gender = gender;
        //this.fathername = fathername;
        this.address = address;
        this.age = age;
        this.pincode = pincode;
        this.phone = phone;
        //this.fax = fax;
        this.aadhaar = aadhaar;
        //this.notificationId = notificationId;
    this.check=check;
    }

    public User(String name, String email, String userId) {
        this.name = name;
        this.email = email;
        //this.imageURL = imageURL;
        this.userId = userId;
    }





    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }




    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }



    public String getAadhaar() {
        return aadhaar;
    }

    public void setAadhaar(String aadhaar) {
        this.aadhaar = aadhaar;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

//    public String getImageURL() {
//        return imageURL;
//    }
//
//    public void setImageURL(String imageURL) {
//        this.imageURL = imageURL;
//    }

    public int getCheck() {
        return check;
    }

    public void setCheck(int check) {
        this.check = check;
    }
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

//    public String getNotificationId() {
//        return notificationId;
//    }
//
//    public void setNotificationId(String notificationId) {
//        this.notificationId = notificationId;
//    }
}
