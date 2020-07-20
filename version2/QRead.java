package magic3q.version2;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.SparseArray;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.TextView;

import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;
import com.google.android.gms.vision.barcode.internal.client.BarcodeDetectorOptions;

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

public class QRead extends AppCompatActivity{
    String JSon_Qlist;
    String questionnaireID = null;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qread);


        //SurfaceView for camera
        final SurfaceView cameraView = (SurfaceView)findViewById(R.id.cameraView);


        BarcodeDetector barcodeDetector = new BarcodeDetector.Builder(this)
                .setBarcodeFormats(Barcode.QR_CODE).build();

        final CameraSource cameraSource = new CameraSource.Builder(this,barcodeDetector)
                .setRequestedPreviewSize(640, 480).build();


        cameraView.getHolder().addCallback(new SurfaceHolder.Callback(){
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                try{
                    cameraSource.start(cameraView.getHolder());
                }catch (IOException ie){
                    Log.e("Camera Error Source", ie.getMessage());
                }
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                cameraSource.stop();
            }
        });

        // For reading the QR Code
        barcodeDetector.setProcessor(new Detector.Processor<Barcode>() {
            @Override
            public void release() {

            }

            @Override
            public void receiveDetections(Detector.Detections<Barcode> detections) {
                final SparseArray<Barcode> barcodesRead = detections.getDetectedItems();

                if (barcodesRead.size() != 0) {

                    final TextView textView = (TextView)findViewById(R.id.textView11);
                    textView.post(new Runnable() {
                        @Override
                        public void run() {
                            textView.setText(barcodesRead.valueAt(0).displayValue);

                            questionnaireID = textView.getText().toString();

                            GlobalClass gbset = (GlobalClass) getApplicationContext();
                            gbset.setQuestionnaireID(questionnaireID);

                            BackGroundTask_Qs backGroundTask_qs = new BackGroundTask_Qs();
                            backGroundTask_qs.execute(questionnaireID);

                            // Delay before loading the Question activities
                            // so data can be retrieved from db in time
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {

                                    startActivity(new Intent(QRead.this, QOne.class));
                                }
                            },1000);
                        }
                    });


                }
            }
        });
    }


    class BackGroundTask_Qs extends AsyncTask<String, Void, String> {
        String url_qlist;



        protected  void onPreExecute(){
            url_qlist = "http://194.81.104.22/~14438658/csy4010/app_php/quest_retrieve.php";

        }

        protected String doInBackground(String... params){
            questionnaireID = params[0];
            if(questionnaireID != null) {
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

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            return null;
        }

        protected void onProgressUpdate(Void... values){
            super.onProgressUpdate(values);
        }

        protected void onPostExecute(String result){
            //Check If query returns nothing
            if(!result.isEmpty() && result != null && !result.equals("null")){

                JSONObject jsonObject = null;

                try{
                    jsonObject = new JSONObject(result);

                    JSONArray jsonArray = jsonObject.getJSONArray("questions");

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
