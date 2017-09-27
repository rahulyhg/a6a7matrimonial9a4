package com.techno.matrimonial.Model.common;

import java.io.Serializable;

/**
 * Created by arbaz on 19/7/16.
 */
public class LoginDetails implements Serializable {
    public String profile_media;
    public String last_name;
    public String first_name;
    public String gender_name;
    public String gender;
    public String full_name;
    public String email;
    public String date_of_birth;
    public String access_token;

    public LoginDetails(String profile_media, String last_name, String first_name, String gender_name, String gender, String full_name, String email, String date_of_birth, String access_token) {
        this.profile_media = profile_media;
        this.last_name = last_name;
        this.first_name = first_name;
        this.gender_name = gender_name;
        this.gender = gender;
        this.full_name = full_name;
        this.email = email;
        this.date_of_birth = date_of_birth;
        this.access_token = access_token;
    }


    public String getProfile_media() {
        return profile_media;
    }

    public void setProfile_media(String profile_media) {
        this.profile_media = profile_media;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getGender_name() {
        return gender_name;
    }

    public void setGender_name(String gender_name) {
        this.gender_name = gender_name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDate_of_birth() {
        return date_of_birth;
    }

    public void setDate_of_birth(String date_of_birth) {
        this.date_of_birth = date_of_birth;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }
}
