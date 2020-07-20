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


public class QOne extends AppCompatActivity{

    private String question1_ID;
    private String question1_Text;
    private String JSon_Qlist;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qone);

        // Get Question 1 ID from QLoad
        GlobalClass getQone = (GlobalClass) getApplicationContext();
        question1_ID = getQone.getQuestion1_ID();

        // Get Question 1 Text from QLoad
        question1_Text = getQone.getQuestion1_Text();

        // Set Question to Screen
        TextView textView = (TextView)findViewById(R.id.textView2);
        textView.setText(question1_Text);


        new BackGroundTask_Q1_op().execute();



        /*
        There'll be a Check in Next Button Here
        Set the text and letter with a "switch case" or "if statement"
        Perhaps disallow next if selection if blank?
         */

        // Setting the selected Text and Option to be displayed on the Submit Screen
        RadioGroup radioGroup = (RadioGroup)findViewById(R.id.RadioGroup);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup group, int checkedID){
                GlobalClass setQ1_sel = (GlobalClass) getApplicationContext();

                RadioButton radioButton = (RadioButton) findViewById(checkedID);
                setQ1_sel.setQ1_selectedText(radioButton.getText().toString());

                switch(checkedID){
                    case R.id.radioButton:
                        setQ1_sel.setQ1_selectedOption("A");
                        break;
                    case R.id.radioButton2:
                        setQ1_sel.setQ1_selectedOption("B");
                        break;
                    case R.id.radioButton3:
                        setQ1_sel.setQ1_selectedOption("C");
                        break;
                    case R.id.radioButton4:
                        setQ1_sel.setQ1_selectedOption("D");
                        break;
                    case R.id.radioButton5:
                        setQ1_sel.setQ1_selectedOption("E");
                        break;
                }
            }
        });


        Button btnNext = (Button) findViewById(R.id.button2);
        btnNext.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){

                startActivity(new Intent(QOne.this, QTwo.class));
            }
        });
    }





    class BackGroundTask_Q1_op extends AsyncTask<Void, Void, String> {
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
                String data = URLEncoder.encode("qo_id", "UTF-8") + "=" + URLEncoder.encode(question1_ID, "UTF-8");
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
            }catch (NullPointerException ne){
                ne.printStackTrace();
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
                    String q1o_A = jsonArray.getString(1);
                    String q1o_B = jsonArray.getString(3);
                    String q1o_C = jsonArray.getString(5);
                    String q1o_D = jsonArray.getString(7);
                    String q1o_E = jsonArray.getString(9);


                    RadioButton radioButton1 = (RadioButton) findViewById(R.id.radioButton);
                    radioButton1.setText(q1o_A);

                    RadioButton radioButton2 = (RadioButton) findViewById(R.id.radioButton2);
                    radioButton2.setText(q1o_B);

                    RadioButton radioButton3 = (RadioButton) findViewById(R.id.radioButton3);
                    radioButton3.setText(q1o_C);

                    RadioButton radioButton4 = (RadioButton) findViewById(R.id.radioButton4);
                    radioButton4.setText(q1o_D);

                    RadioButton radioButton5 = (RadioButton) findViewById(R.id.radioButton5);
                    radioButton5.setText(q1o_E);



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
