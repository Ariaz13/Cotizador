package com.example.cotizador;

public class Estructura_BBDD {

    private Estructura_BBDD() {
    }

    public static final String TABLE_NAME = "nuevaCotizacion";
    public static final String COLUMNA1 = "Id";
    public static final String COLUMNA2 = "Cliente";
    public static final String COLUMNA3 = "Uso";
    public static final String COLUMNA4 = "FechaEntrega";
    public static final String COLUMNA5 = "Tamano";
    public static final String COLUMNA6 = "CantidadPersonajes";
    public static final String COLUMNA7 = "NivelDetalle";
    public static final String COLUMNA8 = "DescripcionDetalle";
    public static final String COLUMNA9 = "Referencia";
    public static final String COLUMNA10 = "Impreso";
    public static final String COLUMNA11 = "Especificaciones";
    public static final String COLUMNA12 = "NombreIdentificador";
    //public static final String COLUMNA13 = "Costo";

    private static final String TEXT_TYPE = " TEXT";
    private static final String COMMA_SEP = ",";
    public static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + Estructura_BBDD.TABLE_NAME + " (" +
                    Estructura_BBDD.COLUMNA1 + " INTEGER IDENTITY(1,1) PRIMARY KEY," +
                    Estructura_BBDD.COLUMNA2 + TEXT_TYPE + COMMA_SEP +
                    Estructura_BBDD.COLUMNA3 + TEXT_TYPE + COMMA_SEP +
                    Estructura_BBDD.COLUMNA4 + TEXT_TYPE + COMMA_SEP +
                    Estructura_BBDD.COLUMNA5 + TEXT_TYPE + COMMA_SEP +
                    Estructura_BBDD.COLUMNA6 + TEXT_TYPE + COMMA_SEP +
                    Estructura_BBDD.COLUMNA7 + TEXT_TYPE + COMMA_SEP +
                    Estructura_BBDD.COLUMNA8 + TEXT_TYPE + COMMA_SEP +
                    Estructura_BBDD.COLUMNA9 + TEXT_TYPE + COMMA_SEP +
                    Estructura_BBDD.COLUMNA10 + TEXT_TYPE + COMMA_SEP +
                    Estructura_BBDD.COLUMNA11 + TEXT_TYPE + COMMA_SEP +
                    Estructura_BBDD.COLUMNA12 + TEXT_TYPE + " )";
                    //Estructura_BBDD.COLUMNA13 + " INTEGER" + " )";

    public static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + Estructura_BBDD.TABLE_NAME;
}
