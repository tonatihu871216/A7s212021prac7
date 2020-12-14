package mx.edu.tesoem.isc.ctol.a7s212021prac7;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {

    TextView txtnombre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtnombre = findViewById(R.id.txtnombre);

        if (verificaArchivo(this)) Toast.makeText(this, "El archivo existe", Toast.LENGTH_SHORT).show();
        else Toast.makeText(this, "El archivo no existe", Toast.LENGTH_LONG).show();
    }
    public void procesoGraba(View View){
        Context context = this;
        try{
            OutputStreamWriter archivo = new OutputStreamWriter(context.openFileOutput("archivo.txt", Activity.MODE_PRIVATE));
            archivo.write(txtnombre.getText().toString());
            archivo.flush();
            archivo.close();
        }catch (Exception ex){
            Toast.makeText( this, "No se pudo crear archivo", Toast.LENGTH_SHORT).show();
        }
    }
    public void procesoLeer(View view){
        Context context = this;
        try {
            InputStreamReader archivo = new InputStreamReader(context.openFileInput("archivo.txt"));
            BufferedReader br = new BufferedReader(archivo);
            String lineatexto="";
            while ((lineatexto = br.readLine()) !=null){
                Toast.makeText(this, "informacion: "+ lineatexto, Toast.LENGTH_SHORT).show();
            }

        }catch (Exception ex){
            Toast.makeText(this, "No se pudo leer la informacion", Toast.LENGTH_SHORT).show();
        }
    }

    public Boolean verificaArchivo(Context context){
        String[] archivos = context.fileList();
        for (String archivo : archivos)
            if (archivo.equals("archivo.txt")) return true;
     return false;
    }
}