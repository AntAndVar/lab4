package com.example.kryptonote;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kryptonote_layout);
    }

   public void onEncrypt(View v) {
        EditText D = ((EditText)findViewById(R.id.key));
        EditText K = ((EditText)findViewById(R.id.key));
        String newdata = D.getText().toString();
        String newkey = K.getText().toString();
        Cipher result = new Cipher(newkey);
        String update = result.encrypt(newdata);
        D.setText(update);
    }

    public void onDecrypt(View v) {
        EditText D = ((EditText)findViewById(R.id.key));
        EditText K = ((EditText)findViewById(R.id.key));
        String newdata = D.getText().toString();
        String newkey = K.getText().toString();
        Cipher result = new Cipher(newkey);
        String update = result.decrypt(newdata);
        D.setText(update);
    }

    public void onSave(View v) {
        try {
            String name = ((EditText) findViewById(R.id.file)).getText().toString();
            File dir = this.getFilesDir();
            File file = new File(dir, name);
            FileWriter fw = new FileWriter(file);
            fw.write(((EditText) findViewById(R.id.data)).getText().toString());
            fw.close();
            Toast.makeText(this, "Note Saved.", Toast.LENGTH_LONG);
        } catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG);
        }
    }

    public void onLoad(View v) {
        try {
            String name = ((EditText) findViewById(R.id.file)).getText().toString();
            File dir = this.getFilesDir();
            File file = new File(dir, name);
            FileReader fr = new FileReader(file);
            String show = "";
            for (int c = fr.read(); c != -1; c = fr.read()) {
                show = show + (char) c;

            }
            fr.close();
            ((EditText) findViewById(R.id.data)).setText(show);

            Toast.makeText(this, "Note Loaded", Toast.LENGTH_LONG);
        } catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG);
        }
   }
}
