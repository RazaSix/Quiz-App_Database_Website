package magic3q.version2;


import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class QSubmit extends AppCompatActivity{
    private String questionnaire_ID;
    private String q1_ID;
    private String q1Text;
    private String q1OptionText;
    private String q1OptionLetter;

    private String q2_ID;
    private String q2Text;
    private String q2OptionText;
    private String q2OptionLetter;

    private String q3_ID;
    private String q3Text;
    private String q3OptionText;
    private String q3OptionLetter;

    private String json_string;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qsubmit);

        GlobalClass getSubmit = (GlobalClass) getApplicationContext();

        questionnaire_ID = getSubmit.getQuestionnaireID();

        //Question 1
        q1_ID = getSubmit.getQuestion1_ID();
        q1Text = getSubmit.getQuestion1_Text();
        q1OptionText = getSubmit.getQ1_selectedText();
        q1OptionLetter = getSubmit.getQ1_selectedOption();


        TextView textView = (TextView)findViewById(R.id.textView5);
        textView.setText(q1Text);

        RadioButton radioButton1 = (RadioButton) findViewById(R.id.radioButton11);
        radioButton1.setText(q1OptionText);


        //Question 2
        q2_ID = getSubmit.getQuestion2_ID();
        q2Text = getSubmit.getQuestion2_Text();
        q2OptionText = getSubmit.getQ2_selectedText();
        q2OptionLetter = getSubmit.getQ2_selectedOption();


        TextView textView2 = (TextView)findViewById(R.id.textView6);
        textView2.setText(q2Text);

        RadioButton radioButton2 = (RadioButton) findViewById(R.id.radioButton12);
        radioButton2.setText(q2OptionText);


        //Question 3
        q3_ID = getSubmit.getQuestion3_ID();
        q3Text = getSubmit.getQuestion3_Text();
        q3OptionText = getSubmit.getQ3_selectedText();
        q3OptionLetter = getSubmit.getQ3_selectedOption();


        TextView textView3 = (TextView)findViewById(R.id.textView7);
        textView3.setText(q3Text);

        RadioButton radioButton3 = (RadioButton) findViewById(R.id.radioButton13);
        radioButton3.setText(q3OptionText);


        Button btnSubmit = (Button) findViewById(R.id.button3);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){

                new BackGroundTask_Submit().execute();

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        startActivity(new Intent(QSubmit.this, Home.class));
                    }
                },1000);
            }
        });

        Button btnPrev = (Button) findViewById(R.id.button6);
        btnPrev.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                startActivity(new Intent(QSubmit.this, QThree.class));
            }
        });

    }


    class BackGroundTask_Submit extends AsyncTask<Void, Void, String> {
        String url_submit;

        //String questionID = question1_ID;

        protected  void onPreExecute(){
            url_submit = "http://194.81.104.22/~14438658/csy4010/app_php/options_submit.php";

            json_string = "{\"answer_options\":[";
            JSONObject ansJsonObj = new JSONObject();

            try {
                //Question 1
                ansJsonObj.put("Q_Option", q1OptionLetter);
                ansJsonObj.put("Question_ID",q1_ID);
                ansJsonObj.put("Questionnaire_ID",questionnaire_ID);
                json_string = json_string + ansJsonObj.toString()+",";

                //Question 2
                ansJsonObj.put("Q_Option", q2OptionLetter);
                ansJsonObj.put("Question_ID",q2_ID);
                ansJsonObj.put("Questionnaire_ID",questionnaire_ID);
                json_string = json_string + ansJsonObj.toString()+",";

                //Question 3
                ansJsonObj.put("Q_Option", q3OptionLetter);
                ansJsonObj.put("Question_ID",q3_ID);
                ansJsonObj.put("Questionnaire_ID",questionnaire_ID);
                json_string = json_string + ansJsonObj.toString()+",";


                //Closing the String Build
                json_string = json_string.substring(0,json_string.length()-1);
                json_string += "]}";

                //TextView textView = (TextView)findViewById(R.id.textView10);
                //textView.setText(json_string);

            }catch(JSONException e) {
                e.printStackTrace();
            }
        }

        protected String doInBackground(Void... voids){

            try {

                    URL url = new URL(url_submit);
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoOutput(true);
                    OutputStream OS = httpURLConnection.getOutputStream();
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(OS, "UTF-8"));
                    String data = URLEncoder.encode("ansJson", "UTF-8") + "=" + URLEncoder.encode(json_string, "UTF-8");
                    bufferedWriter.write(data);
                    bufferedWriter.flush();
                    bufferedWriter.close();
                    OS.close();

                    InputStream IS = httpURLConnection.getInputStream();
                    IS.close();
                    return "Option Sent";
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }


            return null;
        }

        protected void onProgressUpdate(Void... values){
            super.onProgressUpdate(values);
        }

        protected void onPostExecute(String result){

        }
    }
}
