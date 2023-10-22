package com.example.constant;

public class Endpoints {
    public static final String MOMENT = "/moment";
	public static final String TIMELINE = "/timeline";
	public static final String USER = "/user";
	public static final String USER_ID = "/user/{id}";
	public static final String USER_ID_TIMELINE = "/user/{id}/timeline";
	public static final String USER_ID_FOLLOW = "/user/{id}/follow";
	public static final String USER_ID_UNFOLLOW = "/user/{id}/unfollow";
	public static final String USER_ID_FOLLOWER = "/user/{id}/follower";
	public static final String USER_ID_FOLLOWING = "/user/{id}/following";
	public static final String USER_ID_MOMENT = "/user/{id}/moment";
	public static final String USER_ID_MOMENT_ID = "/user/{id}/moment/{momentId}";
	public static final String USER_ID_MOMENT_ID_LIKE = "/user/{id}/moment/{momentId}/like";
	public static final String USER_ID_MOMENT_ID_UNLIKE = "/user/{id}/moment/{momentId}/unlike";
	public static final String USER_ID_MOMENT_ID_COMMENT = "/user/{id}/moment/{momentId}/comment";
	public static final String USER_ID_MOMENT_ID_COMMENT_ID = "/user/{id}/moment/{momentId}/comment/{commentId}";
	public static final String USER_ID_MOMENT_ID_COMMENT_ID_LIKE = "/user/{id}/moment/{momentId}/comment/{commentId}/like";
	public static final String USER_ID_MOMENT_ID_COMMENT_ID_UNLIKE = "/user/{id}/moment/{momentId}/comment/{commentId}/unlike";
	public static final String USER_ID_MOMENT_ID_COMMENT_ID_REPLY = "/user/{id}/moment/{momentId}/comment/{commentId}/reply";
	public static final String USER_ID_MOMENT_ID_COMMENT_ID_REPLY_ID = "/user/{id}/moment/{momentId}/comment/{commentId}/reply/{replyId}";
	public static final String USER_ID_MOMENT_ID_COMMENT_ID_REPLY_ID_LIKE = "/user/{id}/moment/{momentId}/comment/{commentId}/reply/{replyId}/like";
	public static final String USER_ID_MOMENT_ID_COMMENT_ID_REPLY_ID_UNLIKE = "/user/{id}/moment/{momentId}/comment/{commentId}/reply/{replyId}/unlike";

}
