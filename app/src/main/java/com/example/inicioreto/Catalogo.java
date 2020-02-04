package com.example.inicioreto;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.File;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import static java.lang.Integer.parseInt;

public class Catalogo extends AppCompatActivity {
    ListView listav;
    Button crear;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalogo);

        final Articulo articulos[];
        articulos=RecogerArticulos();
        int cantidad []=new int[articulos.length];


        listav=(ListView)findViewById(R.id.ViewRealizar);
        AdaptadorArticulo adapt=new AdaptadorArticulo(getApplicationContext(),articulos);
        listav.setAdapter(adapt);

    }

    private Articulo[] RecogerArticulos() {
        Articulo [] articulos= new Articulo[0];

        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();

            Document doc = builder.parse(getResources().openRawResource(R.raw.articulos));

            Element raiz = doc.getDocumentElement();

            NodeList items = raiz.getElementsByTagName("ARTICULO");

            articulos = new Articulo[items.getLength()];
            for( int i = 0; i < items.getLength(); i++ ) {
                Node nodoArticulo = items.item(i);

                if (nodoArticulo.getNodeType() == Node.ELEMENT_NODE) {

                    Element articulo = (Element) nodoArticulo;
                    String cod = articulo.getElementsByTagName("CodArticulo").item(0).getTextContent();
                    String desc = articulo.getElementsByTagName("Descripcion").item(0).getTextContent();
                    String precio = articulo.getElementsByTagName("PrecioVenta").item(0).getTextContent();
                    String Categoria = articulo.getElementsByTagName("CodCategoria").item(0).getTextContent();
                    articulos[i]=new Articulo(cod,desc,precio,Categoria,"0");
                }
            }
            for(int i =0;i<articulos.length;i++){
                BDSQLiteArticulos abrirBD = new BDSQLiteArticulos(getApplicationContext(), "DBArticulo", null, 6);
                SQLiteDatabase db = abrirBD.getWritableDatabase();
                db.execSQL("INSERT INTO Articulos (idArticulo, PrecioVenta,Descripcion) " +
                        "VALUES ( NULL , '" + articulos[i].getPrecioVenta() + "', '" + articulos[i].getDescripcion()+"'");
            }
        } catch (Exception ex) {

        }

        return articulos;
    }

    public static void lecturaCatalogo(){
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
    }
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
