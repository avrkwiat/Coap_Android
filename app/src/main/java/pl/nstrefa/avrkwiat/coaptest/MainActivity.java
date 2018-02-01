package pl.nstrefa.avrkwiat.coaptest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.eclipse.californium.core.CoapClient;
import org.eclipse.californium.core.CoapHandler;
import org.eclipse.californium.core.CoapResponse;

public class MainActivity extends AppCompatActivity {
    Button przycisk;
    TextView log;
    CoapClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        przycisk = (Button)findViewById(R.id.przycisk);
        log = (TextView)findViewById(R.id.log);

        client = new CoapClient("coap", "192.168.0.80", 5683, "light");
        przycisk.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                client.post(new CoapHandler() { // e.g., anonymous inner class
                    @Override public void onLoad(CoapResponse response) { // also error resp.
                        //System.out.println( response.getResponseText() );
                        log.setText("Code: "+response.getCode().toString()+", isOK: "+response.isSuccess());
                    }

                    @Override public void onError() { // I/O errors and timeouts
                        System.err.println("Failed");
                    }
                },"1", 0);

            }
        });



    }
}
