package RMIC;

import java.io.Serializable;
import java.sql.Date;

public class Message implements Serializable {
    private static final long serialVersionUID = 1L;
    private String pseudo, body;
    private Date day;

    public Message() {
    }
    
    public Message(String body, String pseudo) {
        this.body = body;
        this.pseudo = pseudo;
    }
    
    public String toString() {
        return "Message{" +
                "pseudo='" + pseudo + '\'' +
                ", body='" + body + '\'' +
                ", day=" + day +
                '}';
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Date getDay() {
        return day;
    }

    public void setDay(Date day) {
        this.day = day;
    }
}
