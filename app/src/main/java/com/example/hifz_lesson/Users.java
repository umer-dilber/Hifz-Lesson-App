package com.example.hifz_lesson;

public class Users {
    private String name;
    private String date;
    private Integer sabqi;
    private Integer sabaq;
    private Integer manzil;
    public Users(){
        name = "";
        sabaq = 0; manzil = 0; sabqi = 0;
        date = "";
    }
    public Users(String a_name){
        name = a_name;
        sabaq = 0; manzil = 0; sabqi = 0;
        date = "14-02-2023";
    }

    public void setName(String a_name){
        name = a_name;
    }
    public void setDate(String a_date) { date = a_date; }
    public void setSabqi(Integer a_sabqi){
        sabqi = a_sabqi;
    }
    public void setSabaq(Integer a_sabaq){
        sabaq = a_sabaq;
    }
    public void setManzil(Integer a_manzil){
        manzil = a_manzil;
    }

    public String getName(){
        return  name;
    }
    public String getDate(){
        return  date;
    }
    public Integer getSabqi(){
        return  sabqi;
    }
    public Integer getSabaq(){
        return  sabaq;
    }
    public Integer getManzil(){
        return  manzil;
    }
}
