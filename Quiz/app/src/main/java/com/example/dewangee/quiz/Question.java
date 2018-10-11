package com.example.dewangee.quiz;

public class Question {

    public int id;
    public String stmt;
    public String expected_ans;
    public String given_ans;


    public Question(){

    }

    public int getId(){
        return id;
    }

    public String getStmt() {
        return stmt;
    }

    public void setStmt(String stmt) {
        this.stmt = stmt;
    }

    public void setId(int id) {
        this.id = id;
    }
}
