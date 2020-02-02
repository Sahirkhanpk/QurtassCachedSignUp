package com.example.myqurtassassignment;

public class Node {


    public static final String TABLE_SIGN_UP = "signup";

    public static final String COLUMN_BODY = "body";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_TIMESTAMP = "timestamp";

    private int id;
    private String timestamp;
    private String body;
    // Create table for notifications SQL query
    public static final String CREATE_TABLE__SIGN_UP =
            "CREATE TABLE " + TABLE_SIGN_UP + "("
                    + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + COLUMN_BODY + " TEXT,"
                    + COLUMN_TIMESTAMP + " DATETIME DEFAULT CURRENT_TIMESTAMP"
                    + ")";

    public Node() {
    }

    public Node(int id, String body) {
        this.id = id;
        this.body = body;
        this.timestamp = " DATETIME DEFAULT CURRENT_TIMESTAMP";
    }


    public int getId() {
        return id;
    }
    public String getBody() {
        return body;
    }
    public void setBody(String body) {
        this.body = body;
    }
    public String getTimestamp() {
        return timestamp;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }


}
