package com.example.inicioreto;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class Adaptador extends BaseAdapter {

      private static LayoutInflater inflater = null;

    Context contexto;
    ArrayList<Partner> cliente = new ArrayList<Partner>();

    Comercial comer[];

    public Adaptador(Context contexto, ArrayList<Partner> cli, Comercial[] comer)
    {
        this.contexto = contexto;
        this.cliente = cli;
        this.comer=comer;

        inflater = (LayoutInflater)contexto.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int i, View convertView, ViewGroup parent) {

        View vista = convertView;

        vista = inflater.inflate(R.layout.item, null);



        TextView nom = (TextView) vista.findViewById(R.id.txtNombre);
        TextView ape = (TextView) vista.findViewById(R.id.txtApellidos);
        TextView tel= (TextView) vista.findViewById(R.id.txtTelefono);
        ImageView im=(ImageView) vista.findViewById((R.id.image));




        nom.setText(cliente.get(i).getNombre());
        tel.setText(String.valueOf(cliente.get(i).getTelefono()));
        ape.setText(cliente.get(i).getApellido());
        im.setImageResource(R.drawable.usu);

        vista.setTag(i);

       vista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                Intent intent = new Intent(contexto, Detalles_cliente.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("nombre",cliente.get((Integer)v.getTag()).getNombre());
                intent.putExtra("apellidos", cliente.get((Integer)v.getTag()).getApellido());
                intent.putExtra("empresa", cliente.get((Integer)v.getTag()).getEmpresa());
                intent.putExtra("direcE", cliente.get((Integer)v.getTag()).getDireccionE());
                intent.putExtra("direcF", cliente.get((Integer)v.getTag()).getDireccionF());
                intent.putExtra("pobla", cliente.get((Integer)v.getTag()).getPoblacion());
                intent.putExtra("tele", String.valueOf(cliente.get((Integer)v.getTag()).getTelefono()));
                intent.putExtra("item", v.getTag().toString());

                for (int j=0;j<comer.length;j++) {
                    if (cliente.get((Integer)v.getTag()).getCodComer() == comer[j].getCodigo()) {
                        intent.putExtra("comer", comer[j].getNombre().toString() + " " + comer[j].getApellido().toString());
                    }
                }
                contexto.startActivity(intent);
            }
        });




        return vista;
    }

    @Override
    public int getCount() {
        return cliente.size();
    }

    @Override
    public Object getItem(int position) {
        return cliente.get(position);
    }

    @Override
    public long getItemId(int position) {
        return cliente.get(position).getCodigo();
    }

}