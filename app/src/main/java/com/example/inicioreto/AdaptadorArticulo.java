package com.example.inicioreto;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class AdaptadorArticulo extends BaseAdapter {
    TextView txtCnt;
    TextView txtPrc;
    TextView txtDesc;
    TextView txtId;


    private static LayoutInflater inflater = null;

    Context contexto;
    Articulo articulos[];
    int cant[];

    public AdaptadorArticulo(Context contexto, Articulo [] art)  {
        this.contexto = contexto;
        this.articulos = art;

        inflater = (LayoutInflater)contexto.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(final int i, View convertView, ViewGroup parent) {

        View vista = convertView;

        vista = inflater.inflate(R.layout.itemarticulo, null);
        final int[] cantTotal = new int[1];
        txtDesc = (TextView) vista.findViewById(R.id.txtNombre);
        txtPrc=vista.findViewById(R.id.txtPrecio);
        txtId=vista.findViewById(R.id.txtId);
        txtDesc.setText(articulos[i].getDescripcion());
        txtPrc.setText(articulos[i].getPrecioVenta());
        txtId.setText(articulos[i].getCodArticulo());
        return vista;

    }

    @Override
    public int getCount() {
        return articulos.length;
    }

    @Override
    public Object getItem(int position) {
        return articulos[position];
    }

    @Override
    public long getItemId(int position) {
        return Integer.parseInt(articulos[position].getCodArticulo());
    }

}

