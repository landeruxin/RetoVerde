package com.example.inicioreto;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.File;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import static java.lang.Integer.parseInt;

public class Pedido extends AppCompatActivity {
    Button pedido;
    TextView cant1;
    TextView cant2;
    TextView cant3;
    TextView total;
    TextView codped;
    TextView cliente;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedido);
        pedido=findViewById(R.id.btnEnviar);
        cant1=findViewById(R.id.txtCant1);
        cant2=findViewById(R.id.txtCant2);
        cant3=findViewById(R.id.txtCant3);
        total=findViewById(R.id.txtCantTotal);
        codped =findViewById(R.id.txtCod);
        cliente=findViewById(R.id.txtCliente);



        cant1.setOnFocusChangeListener(new View.OnFocusChangeListener(){

            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                final int ncant1;
                if(isEmpty(cant1)){
                    ncant1=0;
                }else{
                    ncant1=parseInt(cant1.getText().toString());

                }
                final int ncant2;
                if(isEmpty(cant2)){
                    ncant2=0;
                }else{
                    ncant2=parseInt(cant2.getText().toString());

                }
                final int ncant3;
                if(isEmpty(cant3)){
                    ncant3=0;
                }else{
                    ncant3=parseInt(cant3.getText().toString());

                }
                double tot;
                tot=ncant1*40+ncant2*30+ncant3*80;
                total.setText(String.valueOf(tot));
            }
        });
        cant2.setOnFocusChangeListener(new View.OnFocusChangeListener(){

            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                final int ncant1;
                if(isEmpty(cant1)){
                    ncant1=0;
                }else{
                    ncant1=parseInt(cant1.getText().toString());

                }
                final int ncant2;
                if(isEmpty(cant2)){
                    ncant2=0;
                }else{
                    ncant2=parseInt(cant2.getText().toString());

                }
                final int ncant3;
                if(isEmpty(cant3)){
                    ncant3=0;
                }else{
                    ncant3=parseInt(cant3.getText().toString());

                }
                double tot;
                tot=ncant1*40+ncant2*30+ncant3*80;
                total.setText(String.valueOf(tot));
            }
        });
        cant3.setOnFocusChangeListener(new View.OnFocusChangeListener(){

            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                final int ncant1;
                if(isEmpty(cant1)){
                    ncant1=0;
                }else{
                    ncant1=parseInt(cant1.getText().toString());

                }
                final int ncant2;
                if(isEmpty(cant2)){
                    ncant2=0;
                }else{
                    ncant2=parseInt(cant2.getText().toString());

                }
                final int ncant3;
                if(isEmpty(cant3)){
                    ncant3=0;
                }else{
                    ncant3=parseInt(cant3.getText().toString());

                }
                double tot;
                tot=ncant1*40+ncant2*30+ncant3*80;
                total.setText(String.valueOf(tot));
            }
        });
        pedido.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(isEmpty(codped)) {
                    Toast aviso = Toast.makeText(getApplicationContext(), "Introduce un nombre para el pedido", Toast.LENGTH_SHORT);
                    aviso.show();
                }else if(isEmpty(cliente)){
                    Toast aviso = Toast.makeText(getApplicationContext(), "Introduce un cliente", Toast.LENGTH_SHORT);
                    aviso.show();
                }else if(isEmpty(cant1)&&isEmpty(cant2)&&isEmpty(cant3)){
                    Toast aviso = Toast.makeText(getApplicationContext(), "Introduce una cantidad", Toast.LENGTH_SHORT);
                    aviso.show();
                }else{
                    Toast aviso = Toast.makeText(getApplicationContext(), "Pedido completado", Toast.LENGTH_SHORT);
                    aviso.show();

                    finish();
                }
            }
        });
    }
    /*public static void lecturaCatalogo(){
        try {
            ArrayList<Integer> id = new ArrayList<>();
            ArrayList<String> desc = new ArrayList<>();
            File archivo = new File("C:\\Users\\adminportatil\\AndroidStudioProjects\\Pedidos\\app\\src\\main\\res\\raw\\categorias.xml");
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = dbf.newDocumentBuilder();
            Document document = documentBuilder.parse(archivo);
            document.getDocumentElement().normalize();
            System.out.println("Elemento raiz:" + document.getDocumentElement().getNodeName());
            NodeList listaCategorias = document.getElementsByTagName("CATEGORIAS");
            for (int temp = 0; temp < listaCategorias.getLength(); temp++) {
                Node nodo = listaCategorias.item(temp);
                if (nodo.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) nodo;
                    id.add(parseInt(element.getElementsByTagName("CodCategoria").item(0).getTextContent()));
                    desc.add(element.getElementsByTagName("Descripcion").item(0).getTextContent());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/
    private boolean isEmpty(TextView etText) {
        return etText.getText().toString().trim().length() == 0;
    }
    /*public  void escribir(Linea lineas[]) {
        try {

            File archivo=new File(".xml");

            DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance();
            DocumentBuilder db=dbf.newDocumentBuilder();
            Document doc=db.newDocument();

            Element liburuak=doc.createElement("libros");
            doc.appendChild(liburuak);

            for(Linea l: lineas) {

                Element libro=doc.createElement("libro");
                liburuak.appendChild(libro);

                Element titulo=doc.createElement("titulo");
                titulo.setTextContent(l.getTitulo());
                libro.appendChild(titulo);

                Element autor=doc.createElement("autor");
                autor.setTextContent(l.getAutor());
                libro.appendChild(autor);

                Element hojas=doc.createElement("hojas");
                hojas.setTextContent(String.valueOf(l.getHojas()));
                libro.appendChild(hojas);

                Element anio=doc.createElement("año");
                anio.setTextContent(String.valueOf(l.getAnio()));
                libro.appendChild(anio);

            }

            TransformerFactory tf=TransformerFactory.newInstance();
            Transformer trans=tf.newTransformer();
            DOMSource dsrc=new DOMSource(doc);
            StreamResult strr=new StreamResult(archivo);

            trans.transform(dsrc, strr);




        }catch(Exception e) {
            System.out.println("Error: "+e);
        }

    }*/
}
