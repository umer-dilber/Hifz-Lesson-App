package com.example.hifz_lesson;

public class Users {
    private String name;
    private Integer sabqi;
    private Integer sabaq;
    private Integer manzil;
    public Users(){
        name = "";
        sabaq = manzil = sabaq = 0;
    }
    public Users(String a_name){
        name = a_name;
        sabaq = manzil = sabaq = 0;
    }

    public void setName(String a_name){
        name = a_name;
    }
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
