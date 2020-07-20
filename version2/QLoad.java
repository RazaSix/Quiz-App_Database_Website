package magic3q.version2;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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


public class QLoad  extends AppCompatActivity{

    String JSon_Qlist;
    EditText text;
    String questionnaireID;
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qload);


        Button btnLoad = (Button) findViewById(R.id.button2);
        btnLoad.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                new BackGroundTask_Qs().execute();

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        startActivity(new Intent(QLoad.this, QOne.class));
                    }
                },1000);

            }
        });
    }


    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



    class BackGroundTask_Qs extends AsyncTask<Void, Void, String>{
        String url_qlist;



        protected  void onPreExecute(){
            url_qlist = "http://194.81.104.22/~14438658/csy4010/app_php/quest_retrieve.php";

            text = (EditText)findViewById(R.id.editText);
            questionnaireID = text.getText().toString();

            GlobalClass gbset = (GlobalClass) getApplicationContext();
            gbset.setQuestionnaireID(questionnaireID);

        }

        protected String doInBackground(Void... voids){
            try {

                URL url = new URL(url_qlist);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                OutputStream OS = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(OS, "UTF-8"));
                String data = URLEncoder.encode("qid", "UTF-8") + "=" + URLEncoder.encode(questionnaireID, "UTF-8");
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
            //Check If query returns nothing
            if(!result.isEmpty() && result != null && !result.equals("null")){
                TextView textView = (TextView)findViewById(R.id.textView9);
                textView.setText(result);

                JSONObject jsonObject = null;

                try{
                    jsonObject = new JSONObject(result);

                    JSONArray jsonArray = jsonObject.getJSONArray("questions");
                    //textView.setText(jsonArray.getString(0));

                    /*
                    Now set the variables
                    Question Texts and IDs
                    And Questionnaire ID
                     */

                    //Variables from db as JSON array
                    String sQ1_ID = jsonArray.getString(0);
                    String sQ1_Text = jsonArray.getString(1);

                    String sQ2_ID = jsonArray.getString(2);
                    String sQ2_Text = jsonArray.getString(3);

                    String sQ3_ID = jsonArray.getString(4);
                    String sQ3_Text = jsonArray.getString(5);

                    //Setters
                    // Question 1
                    GlobalClass gbQLoad = (GlobalClass) getApplicationContext();

                    gbQLoad.setQuestion1_ID(sQ1_ID);
                    textView.setText(sQ1_Text);
                    gbQLoad.setQuestion1_Text(sQ1_Text);

                    // Question 2
                    gbQLoad.setQuestion2_ID(sQ2_ID);
                    gbQLoad.setQuestion2_Text(sQ2_Text);

                    // Question 3
                    gbQLoad.setQuestion3_ID(sQ3_ID);

                    gbQLoad.setQuestion3_Text(sQ3_Text);



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
