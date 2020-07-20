package magic3q.version2;


import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
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

public class QTwo extends AppCompatActivity{

    private String question2_ID;
    private String question2_Text;
    private String JSon_Qlist;

   protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qtwo);

        // Get Question 2 ID from QLoad
        GlobalClass getQTwo = (GlobalClass) getApplicationContext();
        question2_ID = getQTwo.getQuestion2_ID();

        // Get Question 2 Text from QLoad
        question2_Text = getQTwo.getQuestion2_Text();

        // Set Question to Screen
        TextView textView = (TextView)findViewById(R.id.textView4);
        textView.setText(question2_Text);


        new BackGroundTask_Q2_op().execute();


        /*
        There'll be a Check in Next Button Here
        Set the text and letter with a "switch case" or "if statement"
        Perhaps disallow next if selection if blank?
         */



       // Setting the selected Text and Option to be displayed on the Submit Screen
       RadioGroup radioGroup = (RadioGroup)findViewById(R.id.RadioGroup);

       radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
           public void onCheckedChanged(RadioGroup group, int checkedID){
               GlobalClass setQ2_sel = (GlobalClass) getApplicationContext();

               RadioButton radioButton = (RadioButton) findViewById(checkedID);
               setQ2_sel.setQ2_selectedText(radioButton.getText().toString());

               switch(checkedID){
                   case R.id.radioButton6:
                       setQ2_sel.setQ2_selectedOption("A");
                       break;
                   case R.id.radioButton7:
                       setQ2_sel.setQ2_selectedOption("B");
                       break;
                   case R.id.radioButton8:
                       setQ2_sel.setQ2_selectedOption("C");
                       break;
                   case R.id.radioButton9:
                       setQ2_sel.setQ2_selectedOption("D");
                       break;
                   case R.id.radioButton10:
                       setQ2_sel.setQ2_selectedOption("E");
                       break;
               }
           }
       });


        Button btnNext = (Button) findViewById(R.id.button3);
        btnNext.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                startActivity(new Intent(QTwo.this, QThree.class));
            }
        });

        Button btnPrev = (Button) findViewById(R.id.button4);
        btnPrev.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                startActivity(new Intent(QTwo.this, QOne.class));
            }
        });
    }





    class BackGroundTask_Q2_op extends AsyncTask<Void, Void, String> {
        String url_qlist;

        //String questionID = question1_ID;

        protected  void onPreExecute(){
            url_qlist = "http://194.81.104.22/~14438658/csy4010/app_php/quest_op_retrieve1.php";
        }

        protected String doInBackground(Void... voids){
            try {


                URL url = new URL(url_qlist);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                OutputStream OS = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(OS, "UTF-8"));
                String data = URLEncoder.encode("qo_id", "UTF-8") + "=" + URLEncoder.encode(question2_ID, "UTF-8");
                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                OS.close();

                InputStream inputStream = httpURLConnection.getInputStream();

                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                StringBuilder stringBuilder = new StringBuilder();

                while ((JSon_Qlist = bufferedReader.readLine()) != null) {

                    stringBuilder.append(JSon_Qlist + "\n");
                }

                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();

                return stringBuilder.toString().trim();

            }catch (MalformedURLException e){
                e.printStackTrace();
            }catch (IOException e){
                e.printStackTrace();
            }

            return null;
        }

        protected void onProgressUpdate(Void... values){
            super.onProgressUpdate(values);
        }

        protected void onPostExecute(String result){
            //If query returns nothing
            if(!result.isEmpty() && result != null && !result.equals("null")){

                JSONObject jsonObject = null;

                try{
                    jsonObject = new JSONObject(result);

                    JSONArray jsonArray = jsonObject.getJSONArray("qoptions");


                    //Variables from db as JSON array- Options
                    String q2o_A = jsonArray.getString(1);
                    String q2o_B = jsonArray.getString(3);
                    String q2o_C = jsonArray.getString(5);
                    String q2o_D = jsonArray.getString(7);
                    String q2o_E = jsonArray.getString(9);

                    RadioButton radioButton1 = (RadioButton) findViewById(R.id.radioButton6);
                    radioButton1.setText(q2o_A);

                    RadioButton radioButton2 = (RadioButton) findViewById(R.id.radioButton7);
                    radioButton2.setText(q2o_B);

                    RadioButton radioButton3 = (RadioButton) findViewById(R.id.radioButton8);
                    radioButton3.setText(q2o_C);

                    RadioButton radioButton4 = (RadioButton) findViewById(R.id.radioButton9);
                    radioButton4.setText(q2o_D);

                    RadioButton radioButton5 = (RadioButton) findViewById(R.id.radioButton10);
                    radioButton5.setText(q2o_E);

                }catch(JSONException ej){
                    ej.printStackTrace();
                }catch(NullPointerException ne){
                    ne.printStackTrace();
                }
            }
            else{
                //Do Nothing

            }



        }
    }
}
