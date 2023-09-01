package com.socialmedia.constant;

public class EndPoint {

    public static final String VERSIYON = "api/v1";
    public static final String AUTH= VERSIYON+"/auth";

    //Genel

    public static final String FIND_ALL = "/find_all";
    public static final String UPDATE = "/update";
    public static final String FIND_BY_ID = "/find_by_id";
    public static final String DELETE_BYID = "/delete_by_id";
    public static final String SAVE = "/save";

    // Auth
    public static final String REGISTER = "/register";
    public static final String LOGIN ="/login";
    public static final String ACTIVATE_STATUS = "/activate_status";
}
