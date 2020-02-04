package com.example.inicioreto;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import androidx.appcompat.app.AppCompatActivity;

public class VisualizarPartners extends AppCompatActivity {

    ArrayList<Partner> partners = new ArrayList<Partner>();

    Comercial[] comercials;
    Adaptador adapter;
    ListView listav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visualizar_partners);


        partners= leerPartners();
        comercials=leerComercial();


        listav=findViewById(R.id.ListView);

        adapter=new Adaptador(getApplicationContext(), partners, comercials);

        listav.setAdapter(adapter);

        BDSQLitePartners abrirBD = new BDSQLitePartners(this, "DBClientes", null, 1);
        SQLiteDatabase db = abrirBD.getWritableDatabase();

        Cursor c = db.rawQuery(" SELECT idCliente, nombreCliente,apellidosCli,empresaCli,direccionEntrega,direccionFatura,poblacionCli,telefonoCli,codComer FROM clientes", null);

        if (c.moveToFirst()) {
            do {
                for(int i=0;i<partners.size();i++){
                if (!(c.getString(1).equalsIgnoreCase(partners.get(i).getNombre()) & c.getString(2).equalsIgnoreCase(partners.get(i).getApellido())& c.getInt(7)==partners.get(i).getTelefono() )  ) {
                        db.execSQL("INSERT INTO clientes (idCliente, nombreCliente,apellidosCli,empresaCli,direccionEntrega,direccionFatura,poblacionCli,telefonoCli,codComer) " +
                                "VALUES ( NULL , '" + partners.get(i).getNombre()  + "', '" + partners.get(i).getNombre() +"', '" + partners.get(i).getApellido() +"', '"+ partners.get(i).getEmpresa() +"', '"+partners.get(i).getDireccionE()+"','"+ partners.get(i).getDireccionE() + "', "+partners.get(i).getTelefono()+", "+partners.get(i).getCodComer()+")");

                    }
                }
            } while (c.moveToNext());
        }


        listav.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                final int posicion=i;

                AlertDialog.Builder dialogo1 = new AlertDialog.Builder(VisualizarPartners.this);
                dialogo1.setTitle("Importante");
                dialogo1.setMessage("¿ Elimina este cliente ?");
                dialogo1.setCancelable(false);
                dialogo1.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogo1, int id) {
                        partners.remove(posicion);
                        adapter.notifyDataSetChanged();

                    }
                });
                dialogo1.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogo1, int id) {
                    }
                });
                dialogo1.show();

                return true;
            }
        });
    }

    public Comercial[] leerComercial(){

        Comercial[] comercial= new Comercial[0];

        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();

            Document doc = builder.parse(getResources().openRawResource(R.raw.comerciales));

            Element raiz = doc.getDocumentElement();

            NodeList items = raiz.getElementsByTagName("COMERCIAL");

            comercial = new Comercial[items.getLength()];
            for( int i = 0; i < items.getLength(); i++ ) {
                Node nodoCliente = items.item(i);

                if (nodoCliente.getNodeType() == Node.ELEMENT_NODE) {

                    Element partner1 = (Element) nodoCliente;

                    String cod = partner1.getElementsByTagName("CodComercial").item(0).getTextContent();
                    //cliente[i].setCodigo(Integer.parseInt(cod));
                    String nombre = partner1.getElementsByTagName("Nombre").item(0).getTextContent();
                    // cliente[i].setNombre(nombre);
                    String apellidos = partner1.getElementsByTagName("Apellidos").item(0).getTextContent();
                    //cliente[i].setApellido(apellidos);

                    comercial[i]=new Comercial(Integer.parseInt(cod),nombre,apellidos);
                }
            }

        } catch (Exception ex) {

        }
        return comercial;

    }

    public ArrayList<Partner> leerPartners(){
        ArrayList<Partner> libros = new ArrayList<Partner>();


        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();

            Document doc = builder.parse(getResources().openRawResource(R.raw.partners));

            Element raiz = doc.getDocumentElement();

            NodeList items = raiz.getElementsByTagName("CLIENTES");


            for( int i = 0; i < items.getLength(); i++ ) {
                Node nodoCliente = items.item(i);
                Partner cliente = new Partner();

                if (nodoCliente.getNodeType() == Node.ELEMENT_NODE) {

                    Element partner1 = (Element) nodoCliente;

                    String cod = partner1.getElementsByTagName("CodCliente").item(0).getTextContent();
                    cliente.setCodigo(Integer.parseInt(cod));
                    String nombre = partner1.getElementsByTagName("Nombre").item(0).getTextContent();
                    cliente.setNombre(nombre);
                    String apellidos = partner1.getElementsByTagName("Apellidos").item(0).getTextContent();
                    cliente.setApellido(apellidos);
                    String empresa = partner1.getElementsByTagName("Empresa").item(0).getTextContent();
                    cliente.setEmpresa(empresa);
                    String direccionEntrega = partner1.getElementsByTagName("DireccionEntrega").item(0).getTextContent();
                    cliente.setDireccionE(direccionEntrega);
                    String direccionFactura = partner1.getElementsByTagName("DireccionFactura").item(0).getTextContent();
                    cliente.setDireccionF(direccionFactura);
                    String telefono = partner1.getElementsByTagName("Telefono").item(0).getTextContent();
                    cliente.setTelefono(Integer.parseInt(telefono));
                    String poblacion = partner1.getElementsByTagName("Poblacion").item(0).getTextContent();
                    cliente.setPoblacion(poblacion);
                    String codComer = partner1.getElementsByTagName("IDComercial").item(0).getTextContent();
                    cliente.setPoblacion(poblacion);

                    libros.add(cliente);
                }
            }

        } catch (Exception ex) {

        }
        return libros;
    }
}
