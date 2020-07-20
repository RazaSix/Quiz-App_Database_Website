package magic3q.version2;

import android.app.Application;



public class GlobalClass extends Application {
    private String questionnaireID;

    // Question One
    private String question1_ID;
    private String question1_Text;


    //Question Two
    private String question2_ID;
    private String question2_Text;


    //Question Three
    private String question3_ID;
    private String question3_Text;


    //Selected Options
    private String q1_selectedText;
    private String q2_selectedText;
    private String q3_selectedText;

    private String q1_selectedOption;
    private String q2_selectedOption;
    private String q3_selectedOption;

    private double latitude;
    private double longitude;


    //Questionnaire ID
    public void setQuestionnaireID(String questionnaireID) {
        this.questionnaireID = questionnaireID;
    }

    public String getQuestionnaireID(){
        return questionnaireID;
    }


    //Question One
    public String getQuestion1_ID() {
        return question1_ID;
    }

    public void setQuestion1_ID(String question1_ID) {
        this.question1_ID = question1_ID;
    }

    public String getQuestion1_Text() {
        return question1_Text;
    }

    public void setQuestion1_Text(String question1_Text) {
        this.question1_Text = question1_Text;
    }


    //Question Two
    public String getQuestion2_ID() {
        return question2_ID;
    }

    public void setQuestion2_ID(String question2_ID) {
        this.question2_ID = question2_ID;
    }

    public String getQuestion2_Text() {
        return question2_Text;
    }

    public void setQuestion2_Text(String question2_Text) {
        this.question2_Text = question2_Text;
    }



    //Question Three
    public String getQuestion3_ID() {
        return question3_ID;
    }

    public void setQuestion3_ID(String question3_ID) {
        this.question3_ID = question3_ID;
    }

    public String getQuestion3_Text() {
        return question3_Text;
    }

    public void setQuestion3_Text(String question3_Text) {
        this.question3_Text = question3_Text;
    }


    // Selected Options by User (Submit)

    //Option Text for display in Submit Activity
    public String getQ1_selectedText() {
        return q1_selectedText;
    }

    public void setQ1_selectedText(String q1_selectedText) {
        this.q1_selectedText = q1_selectedText;
    }

    public String getQ2_selectedText() {
        return q2_selectedText;
    }

    public void setQ2_selectedText(String q2_selectedText) {
        this.q2_selectedText = q2_selectedText;
    }

    public String getQ3_selectedText() {
        return q3_selectedText;
    }

    public void setQ3_selectedText(String q3_selectedText) {
        this.q3_selectedText = q3_selectedText;
    }

    // Option Letters for Insert to Database
    public String getQ1_selectedOption() {
        return q1_selectedOption;
    }

    public void setQ1_selectedOption(String q1_selectedOption) {
        this.q1_selectedOption = q1_selectedOption;
    }

    public String getQ2_selectedOption() {
        return q2_selectedOption;
    }

    public void setQ2_selectedOption(String q2_selectedOption) {
        this.q2_selectedOption = q2_selectedOption;
    }

    public String getQ3_selectedOption() {
        return q3_selectedOption;
    }

    public void setQ3_selectedOption(String q3_selectedOption) {
        this.q3_selectedOption = q3_selectedOption;
    }


    // Set & Get Longitude and Latitude

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}
