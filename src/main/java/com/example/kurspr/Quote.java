package com.example.kurspr;

public class Quote {
    public int id;
    public String quote;
    public int professor_id;
    public int subject_id;
    public String date;
    public int publisher_id;

    public Quote(int id, String quote, int professor_id, int subject_id, String date, int publisher_id) {
        this.id = id;
        this.quote = quote;
        this.professor_id = professor_id;
        this.subject_id = subject_id;
        this.date = date;
        this.publisher_id = publisher_id;
    }

    public String getQuote(){
        return quote;
    }
    public int getProfessor_id(){
        return professor_id;
    }
    public int getSubject_id(){
        return subject_id;
    }
    public String getDate(){
        return date;
    }

}
