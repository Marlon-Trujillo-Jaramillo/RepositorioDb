package com.example.inventariodb;


public class Product {
    private String idproduct;
    private String productname;
    private int precio;
    private int existencia;

    // Constructor
    public Product(String idproduct, String productname, int precio, int existencia) {
        this.idproduct = idproduct;
        this.productname = productname;
        this.precio = precio;
        this.existencia = existencia;
    }

    // Getters
    public String getidproduct() {
        return idproduct;
    }

    public String getproductname() {
        return productname;
    }

    public int getprice() {
        return precio;
    }

    public int getexistencia() {
        return existencia;
    }

    // Setters
    public void setidproduct(String idproduct) {
        this.idproduct = idproduct;
    }

    public void setproductname(String productname) {
        this.productname = productname;
    }

    public void setprecio(int precio) {
        this.precio = precio;
    }

    public void setexistencia(int existencia) {
        this.existencia = existencia;
    }
}
