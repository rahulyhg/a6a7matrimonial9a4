package com.techno.matrimonial.Model.boys_girls;

import java.io.Serializable;
import java.util.List;

public class CandidateListMain implements Serializable{
	public int id;
	public String first_name;
	public String middle_name;
	public String last_name;
	public String full_name;
	public String father_name;
	public String mother_name;
	public String gender_name;
	public int gender;
	public String blood_group;
	public String date_of_birth;
	public String time_of_birth;
	public String birth_place;
	public String lives_in;
	public List<ContactItem> contact;
	public String education_group;
	public String education_degree;
	public String height;
	public String hobbies;
	public String profile_media;
	public String age;
	public String weight;
	public int is_short_listed;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getMiddle_name() {
		return middle_name;
	}

	public void setMiddle_name(String middle_name) {
		this.middle_name = middle_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getFull_name() {
		return full_name;
	}

	public void setFull_name(String full_name) {
		this.full_name = full_name;
	}

	public String getFather_name() {
		return father_name;
	}

	public void setFather_name(String father_name) {
		this.father_name = father_name;
	}

	public String getMother_name() {
		return mother_name;
	}

	public void setMother_name(String mother_name) {
		this.mother_name = mother_name;
	}

	public String getGender_name() {
		return gender_name;
	}

	public void setGender_name(String gender_name) {
		this.gender_name = gender_name;
	}

	public int getGender() {
		return gender;
	}

	public void setGender(int gender) {
		this.gender = gender;
	}

	public String getBlood_group() {
		return blood_group;
	}

	public void setBlood_group(String blood_group) {
		this.blood_group = blood_group;
	}

	public String getDate_of_birth() {
		return date_of_birth;
	}

	public void setDate_of_birth(String date_of_birth) {
		this.date_of_birth = date_of_birth;
	}

	public String getTime_of_birth() {
		return time_of_birth;
	}

	public void setTime_of_birth(String time_of_birth) {
		this.time_of_birth = time_of_birth;
	}

	public String getBirth_place() {
		return birth_place;
	}

	public void setBirth_place(String birth_place) {
		this.birth_place = birth_place;
	}

	public String getLives_in() {
		return lives_in;
	}

	public void setLives_in(String lives_in) {
		this.lives_in = lives_in;
	}

	public List<ContactItem> getContact() {
		return contact;
	}

	public void setContact(List<ContactItem> contact) {
		this.contact = contact;
	}

	public String getEducation_group() {
		return education_group;
	}

	public void setEducation_group(String education_group) {
		this.education_group = education_group;
	}

	public String getEducation_degree() {
		return education_degree;
	}

	public void setEducation_degree(String education_degree) {
		this.education_degree = education_degree;
	}

	public String getHeight() {
		return height;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	public String getHobbies() {
		return hobbies;
	}

	public void setHobbies(String hobbies) {
		this.hobbies = hobbies;
	}

	public String getProfile_media() {
		return profile_media;
	}

	public void setProfile_media(String profile_media) {
		this.profile_media = profile_media;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public int getIs_short_listed() {
		return is_short_listed;
	}

	public void setIs_short_listed(int is_short_listed) {
		this.is_short_listed = is_short_listed;
	}
}