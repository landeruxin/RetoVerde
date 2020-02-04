package com.example.inicioreto;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Detalles_cliente extends AppCompatActivity {

    Bundle extra;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalles_cliente);

        TextView nom = (TextView)findViewById(R.id.txtNom);
        TextView ape = (TextView)findViewById(R.id.txtApe);
        TextView tel= (TextView)findViewById(R.id.txtTele);
        TextView emp = (TextView)findViewById(R.id.txtEmp);
        TextView direce = (TextView)findViewById(R.id.txtDirecE);
        TextView direcf= (TextView)findViewById(R.id.txtDirecF);
        TextView pobla= (TextView)findViewById(R.id.txtPobla);
        TextView comer= (TextView)findViewById(R.id.txtComercial);
        ImageView imL=findViewById(R.id.imageView);
        ImageView lla=findViewById(R.id.imageView5);



       extra=this.getIntent().getExtras();
        if(extra!=null){
            nom.setText(extra.getString("nombre"));
            ape.setText(extra.getString("apellidos"));
            tel.setText(extra.getString("tele"));
            emp.setText(extra.getString("empresa"));
            direce.setText(extra.getString("direcE"));
            direcf.setText(extra.getString("direcF"));
            pobla.setText(extra.getString("pobla"));
            comer.setText(extra.getString("comer"));
            imL.setImageResource(R.drawable.usu);
            lla.setImageResource(R.drawable.llama);

        }
        Log.e("error","info");

        imL.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                String num=extra.getString("tele");
                Uri number = Uri.parse("tel:"+num);
                Intent callIntent = new Intent(Intent.ACTION_DIAL, number);

                startActivity(callIntent);
            }
        });



    }
}
