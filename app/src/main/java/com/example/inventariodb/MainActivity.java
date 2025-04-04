package com.example.inventariodb;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText idproduct, productname, precio, existencia;
    Button save;
    TextView message;
    dbProduct sohLibrary = new dbProduct(this, "dbProduct", null, 1);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        idproduct = findViewById(R.id.etidProduct);
        productname = findViewById(R.id.etProductname);
        precio = findViewById(R.id.etPrecio);
        existencia = findViewById(R.id.etExistencia);
        save = findViewById(R.id.save);
        message = findViewById(R.id.tvMessage);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String midproduct = idproduct.getText().toString().trim();
                String mproductname = productname.getText().toString().trim();

                String precioStr = precio.getText().toString().trim();
                String existenciaStr = existencia.getText().toString().trim();

                if (midproduct.isEmpty() || mproductname.isEmpty() || precioStr.isEmpty() || existenciaStr.isEmpty()) {
                    message.setTextColor(Color.parseColor("#b82809"));
                    message.setText("Todos los campos son obligatorios.");
                    return;
                }

                int mprecio = Integer.parseInt(precioStr);
                int mexistencia = Integer.parseInt(existenciaStr);

                // Validaciones de precio y existencia
                if (mprecio < 120000 || mprecio > 800000) {
                    message.setTextColor(Color.parseColor("#b82809"));
                    message.setText("El precio debe estar entre 120000 y 800000.");
                    return;
                }
                if (mexistencia < 1 || mexistencia > 20) {
                    message.setTextColor(Color.parseColor("#b82809"));
                    message.setText("La existencia debe estar entre 1 y 20.");
                    return;
                }

                // Validar si el ID ya existe en la base de datos
                if (SearchProductId(midproduct).size() > 0) {
                    message.setTextColor(Color.parseColor("#b82809"));
                    message.setText("ID del producto EXISTENTE. Inténtelo con otro.");
                    return;
                }

                SQLiteDatabase db = sohLibrary.getWritableDatabase();
                ContentValues cvProduct = new ContentValues();

                Product oProduct = new Product(midproduct, mproductname, mprecio, mexistencia);
                cvProduct.put("idproduct", oProduct.getidproduct());
                cvProduct.put("productname", oProduct.getproductname());
                cvProduct.put("precio", oProduct.getprice());
                cvProduct.put("existencia", oProduct.getexistencia());

                db.insert("product", null, cvProduct);
                db.close();

                // Mostrar mensaje de éxito
                message.setTextColor(Color.parseColor("#48950c"));
                message.setText("Producto guardado de manera exitosa.");
            }
        });
        Button search = findViewById(R.id.search);

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String midproduct = idproduct.getText().toString().trim();

                if (midproduct.isEmpty()) {
                    message.setTextColor(Color.parseColor("#b82809"));
                    message.setText("Ingrese un ID de producto para buscar.");
                    return;
                }

                ArrayList<Product> resultado = SearchProductId(midproduct);

                if (resultado.size() > 0) {
                    Product encontrado = resultado.get(0);
                    productname.setText(encontrado.getproductname());
                    precio.setText(String.valueOf(encontrado.getprice()));
                    existencia.setText(String.valueOf(encontrado.getexistencia()));

                    message.setTextColor(Color.parseColor("#48950c"));
                    message.setText("Producto encontrado.");
                } else {
                    message.setTextColor(Color.parseColor("#b82809"));
                    message.setText("Producto no encontrado.");
                }
            }
        });

    }

    private ArrayList<Product> SearchProductId(String midproduct) {
        ArrayList<Product> arListReturn = new ArrayList<>();
        SQLiteDatabase odbSearch = sohLibrary.getReadableDatabase();
        String query = "SELECT productname, precio, existencia FROM Product WHERE idproduct = ?";

        Cursor cursorProduct = odbSearch.rawQuery(query, new String[]{midproduct});
        if (cursorProduct.moveToFirst()) {
            Product findProduct = new Product(
                    midproduct,
                    cursorProduct.getString(0),
                    cursorProduct.getInt(1),
                    cursorProduct.getInt(2)
            );
            arListReturn.add(findProduct);
        }
        cursorProduct.close();
        odbSearch.close();
        return arListReturn;
    }
}