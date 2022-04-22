package com.example.ejercicio3_1.Configuration;

public class Transactions
{
    // Nombre de la base de datos
    public static final String NameDatabase = "empleados3_1";

    //Nombre de la tabla en la base de datos
    public static final String tablaEmpleados = "employees";

    //Creacion de los atributos
    public static final String empleado_id = "id";
    public static final String empleado_nombres = "nombres";
    public static final String empleado_apellidos = "apellidos";
    public static final String empleado_direccion = "direccion";
    public static final String empleado_puesto = "puesto";
    public static final String empleado_edad = "edad";

    //Creacion de la tabla
    public static final String CREATE_TABLE_EMPLEADO = "CREATE TABLE " + tablaEmpleados +
            "("+
            empleado_id +" INTEGER PRIMARY KEY AUTOINCREMENT, "+
            empleado_nombres +" TEXT, "+
            empleado_apellidos +" TEXT, "+
            empleado_direccion +" TEXT, "+
            empleado_puesto +" TEXT, "+
            empleado_edad +" INTEGER"+
            ")";

    public static final String DROP_TABLE_EMPLEADO = "DROP TABLE IF EXIST " + tablaEmpleados;
}
