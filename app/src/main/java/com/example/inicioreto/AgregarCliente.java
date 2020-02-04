package com.example.inicioreto;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.File;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import androidx.appcompat.app.AppCompatActivity;

public class AgregarCliente extends AppCompatActivity {

    Spinner spi;
    Comercial[] comercial;

    EditText nom;
    EditText ape;
    EditText emp;
    EditText direcE;
    EditText direcF;
    EditText pobla;
    EditText tele;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_cliente);

        spi=findViewById(R.id.spiComer);
        nom=findViewById(R.id.txtNom);
        ape=findViewById(R.id.txtApe);
        emp=findViewById(R.id.txtEmp);
        direcE=findViewById(R.id.txtDirecEnt);
        direcF=findViewById(R.id.txtDirecFac);
        pobla=findViewById(R.id.txtPobla);
        tele=findViewById(R.id.txtTele);

        comercial=leerComercial();

        String []noms = new String[comercial.length];
        for(int i=0;i<comercial.length;i++){
            noms[i]=comercial[i].getNombre()+" "+comercial[i].getApellido();
        }
        ArrayAdapter adapter=new ArrayAdapter(AgregarCliente.this,android.R.layout.simple_spinner_dropdown_item, noms);
        spi.setAdapter(adapter);



        Button agregar=(Button)findViewById(R.id.agregar);
        agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (!(nom.getText().toString().isEmpty()) & !(ape.getText().toString().isEmpty())
                        & !(emp.getText().toString().isEmpty()) & !(direcE.getText().toString().isEmpty())
                        & !(direcF.getText().toString().isEmpty()) & !(pobla.getText().toString().isEmpty())
                        & !(tele.getText().toString().isEmpty())){

                    Pattern p=Pattern.compile("[6789]");
                    Matcher matcher=p.matcher(String.valueOf(tele.getText()).substring(0,1));

                    if(isNumeric(nom.getText())==false & nom.getText().toString().matches("[a-zA-Z]+")) {
                        if (isNumeric(ape.getText()) == false & ape.getText().toString().matches("[a-zA-Z ]+")) {
                            if (isNumeric(emp.getText()) == false & emp.getText().toString().matches("[a-zA-Z]+")) {
                                if (isNumeric(pobla.getText()) == false & pobla.getText().toString().matches("[a-zA-Z]+")) {
                                    if (isNumeric(tele.getText()) == true /*& matcher.matches() & tele.getText().toString().matches("9[0-9]+")*/) {

                                        //agregarCli(nom.getText().toString(), ape.getText().toString(), emp.getText().toString(), direcE.getText().toString(), direcF.getText().toString(), pobla.getText().toString(), tele.getText().toString(), spi.getSelectedItemPosition()+1);
                                        insertCli(nom.getText().toString(), ape.getText().toString(), emp.getText().toString(), direcE.getText().toString(), direcF.getText().toString(), pobla.getText().toString(), tele.getText().toString(), spi.getSelectedItemPosition()+1);

                                    } else {
                                        Toast toast = Toast.makeText(getApplicationContext(), "Introduce el formato correcto en telefono. 'Ej:telefono(697234096)'", Toast.LENGTH_SHORT);
                                        toast.show();
                                    }
                                }
                                else {
                                    Toast toast = Toast.makeText(getApplicationContext(), "Introduce el formato correcto en poblacion. 'Ej:telefono(697234096)'", Toast.LENGTH_SHORT);
                                    toast.show();
                                }
                            } else {
                                Toast toast = Toast.makeText(getApplicationContext(), "Introduce el formato correcto en empresa. 'Ej:telefono(697234096)'", Toast.LENGTH_SHORT);
                                toast.show();
                            }
                        } else {
                            Toast toast = Toast.makeText(getApplicationContext(), "Introduce el formato correcto en apellido. 'Ej:telefono(697234096)'", Toast.LENGTH_SHORT);
                            toast.show();
                        }
                    } else {
                        Toast toast = Toast.makeText(getApplicationContext(), "Introduce el formato correcto en nombre. 'Ej:telefono(697234096)'", Toast.LENGTH_SHORT);
                        toast.show();
                    }
                }else{
                    Toast toast = Toast.makeText(getApplicationContext(), "No dejes casillas en blanco", Toast.LENGTH_SHORT);
                    toast.show();
                }

            }
        });

    }

   /*void agregarCli(String nom,String ape, String emp, String dirE,String dirF, String pob,String tel,int codCom){
        Partner nuevo_part= new Partner(nom, ape,emp, dirE, dirF, pob, Integer.parseInt(tel), codCom);
        ArrayList<Partner> cli= leerPartners();
        cli.add(nuevo_part);
        XmlSerializer serializer= Xml.newSerializer();
        OutputStreamWriter fout= new OutputStreamWriter(openFileOutput("partners.xml", Context.MODE_PRIVATE));
       serializer.setOutput(fout);
        StringWriter writer=new StringWriter();
        try{
            serializer.setOutput(writer);
            serializer.startDocument("UTF-8",true);
            serializer.startTag("","LuisRetoBBDDDataSet");

            for(int i=0;i<cli.size();i++){
                serializer.startTag("","CLIENTES");
                serializer.startTag("","Nombre");
                serializer.text(cli.get(i).getNombre());
                serializer.endTag("","Nombre");
                serializer.startTag("","Apellidos");
                serializer.text(cli.get(i).getNombre());
                serializer.endTag("","Apellidos");
                serializer.startTag("","Empresa");
                serializer.text(cli.get(i).getNombre());
                serializer.endTag("","Empresa");
                serializer.startTag("","DireccionEntrega");
                serializer.text(cli.get(i).getNombre());
                serializer.endTag("","DireccionEntrega");
                serializer.startTag("","DireccionFactura");
                serializer.text(cli.get(i).getNombre());
                serializer.endTag("","DireccionFactura");
                serializer.startTag("","Poblacion");
                serializer.text(cli.get(i).getNombre());
                serializer.endTag("","Poblacion");
                serializer.startTag("","Telefono");
                serializer.text(cli.get(i).getNombre());
                serializer.endTag("","Telefono");
                serializer.startTag("","IDComercial");
                serializer.text(cli.get(i).getNombre());
                serializer.endTag("","IDComercial");

            }
            serializer.endTag("","LuisRetoBBDDDataSet");
            serializer.endDocument();
            String result=writer.toString();
            IOHelper.writeToFile(this,"partners.xml",result);
            txtXml.setText();
        }catch (Exception e){

        }
    }*/
        public  boolean isNumeric(Editable cadena) {

        boolean resultado;
        try {
            Integer.parseInt(String.valueOf(cadena));
            resultado = true;
        } catch (NumberFormatException excepcion) {
            resultado = false;
        }
        return resultado;
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
    public void agregarCli(String nom,String ape, String emp, String dirE,String dirF, String pob,String tel,int codCom) {



        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = dbf.newDocumentBuilder();

            OutputStreamWriter fout = new OutputStreamWriter(openFileOutput("partners.xml", Context.MODE_PRIVATE));
            Document document = documentBuilder.parse(getResources().openRawResource(R.raw.partners));

            document.getDocumentElement().normalize();

            Partner nuevo_part = new Partner(nom, ape, emp, dirE, dirF, pob, Integer.parseInt(tel), codCom);
            ArrayList<Partner> cli = leerPartners();
            cli.add(nuevo_part);

            Node raiz = document.getDocumentElement();

            for (int i = 0; i < cli.size(); i++) {


                Element nuevoCli = document.createElement("CLIENTE");

                Element eNom = document.createElement("Nombre");
                eNom.setTextContent(cli.get(i).getNombre());
                Element eApe = document.createElement("Apellidos");
                eApe.setTextContent(cli.get(i).getApellido());
                Element eEmp = document.createElement("Empresa");
                eEmp.setTextContent(cli.get(i).getEmpresa());
                Element eDirecE = document.createElement("DireccionEntrega");
                eDirecE.setTextContent(cli.get(i).getDireccionE());
                Element eDirecF = document.createElement("DireccionFactura");
                eDirecF.setTextContent(cli.get(i).getDireccionF());
                Element ePobla = document.createElement("Poblacion");
                ePobla.setTextContent(cli.get(i).getPoblacion());
                Element eTele = document.createElement("Telefono");
                eTele.setTextContent(String.valueOf(cli.get(i).getTelefono()));
                Element eCodComer = document.createElement("IDComercial");
                eCodComer.setTextContent(String.valueOf(cli.get(i).getCodComer()));

                nuevoCli.appendChild(eNom);
                nuevoCli.appendChild(eApe);
                nuevoCli.appendChild(eEmp);
                nuevoCli.appendChild(eDirecE);
                nuevoCli.appendChild(eDirecF);
                nuevoCli.appendChild(ePobla);
                nuevoCli.appendChild(eTele);
                nuevoCli.appendChild(eCodComer);

                raiz.appendChild(nuevoCli);
            }
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(document);
            StreamResult result = new StreamResult(new File(String.valueOf(getResources().openRawResource(R.raw.partners))));

            transformer.transform(source, result);

        } catch (Exception ex) {
            Toast toast = Toast.makeText(getApplicationContext(), ex.toString(), Toast.LENGTH_SHORT);
            toast.show();
        }
    }
    public void insertCli(String nom,String ape, String emp, String dirE,String dirF, String pob,String tel,int codCom){
        BDSQLitePartners abrirBD = new BDSQLitePartners(this, "DBClientes", null, 1);
        SQLiteDatabase db = abrirBD.getWritableDatabase();


        try{

            Cursor c = db.rawQuery(" SELECT telefonoCli FROM clientes", null);

            if (c.moveToFirst()) {
                do {
                    if (c.getInt(0)!= Integer.parseInt(tel) ) {

                       db.execSQL("INSERT INTO clientes (idCliente, nombreCliente,apellidosCli,empresaCli,direccionEntrega,direccionFatura,poblacionCli,telefonoCli,codComer) " +
                                "VALUES ( NULL , '" + nom + "', '" + ape + "', '" + emp + "', '" + dirE + "', '" + dirF + "','" + pob + "', " + Integer.parseInt(tel) + ", " + codCom + ")");

                   }
                } while (c.moveToNext());
            }


        }catch (Exception e){
            Toast.makeText(getApplication(), "Error: "+e.getMessage(),Toast.LENGTH_SHORT).show();
        }


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

