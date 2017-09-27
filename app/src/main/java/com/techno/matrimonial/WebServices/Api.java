package com.techno.matrimonial.WebServices;

/**
 * Created by arbaz on 19/7/16.
 */
public class Api {

    //Dump Condition
    public static final int ConnectionTimeout = 30000; // = 30 seconds
    public static final int ConnectionSoTimeout = 60000; // = 60 seconds

    public static final String HTTP_HEADER = "httpx-thetatech-accesstoken";
    //response code
    public static final int ResponseOk = 200;
    public static final int ResponsePageError = 400;
    public static final int ResponseUnauthorized = 401;
    public static final int ResponseServerError = 500;

    //ExpediaCheckRoomAvailability Params
    public static final String data = "data";
    public static final String code = "code";
    public static String message = "message";
    public static final String error = "error";
    public static final String description = "description";
    public static final String validateKeys = "validate_keys";
    public static final String validateDescription = "validate_description";

    // Local Url
    public static final String MainUrl = "http://192.168.1.199/matrimonial/webservices/";


    //Api List
    public static final String ActionLogin = "user/login";
    public static final String ActionCandidateList = "candidate/candidate";
    public static final String ActionCandidateShortedList = "candidate/short-list-candidate";


}
