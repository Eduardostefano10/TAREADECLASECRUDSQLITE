package com.example.bdqlite.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


import com.example.bdqlite.DatabaseHelper;
import com.example.bdqlite.model.Alumnos;

import java.util.ArrayList;
import java.util.List;

public class AlumnoDataSource {
    private SQLiteDatabase database;
    private DatabaseHelper dbHelper;

    public AlumnoDataSource(Context context) {
        dbHelper = new DatabaseHelper(context);
    }

    public void open() {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public long agregarAlumno(Alumnos alumno) {
        ContentValues values = new ContentValues();
        values.put("nombre", alumno.getNombre());
        values.put("apellidos", alumno.getApellidos());
        values.put("correo", alumno.getCorreo());
        values.put("carrera_id", alumno.getCarrera_id());
        return database.insert("alumnos", null, values);
    }
    public boolean eliminarAlumnoPorId(int alumnoId) {
        return database.delete("alumnos", "idAlumno = ?", new String[]{String.valueOf(alumnoId)}) > 0;
    }


    public Alumnos buscarAlumnoPorID(int id) {
        String[] projection = {"idAlumno", "nombre", "apellidos", "correo", "carrera_id"};
        String selection = "idAlumno = ?";
        String[] selectionArgs = {String.valueOf(id)};

        Cursor cursor = database.query(
                "alumnos",  // Nombre de la tabla
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        if (cursor != null) {
            cursor.moveToFirst();
            Alumnos alumno = cursorToAlumno(cursor);
            cursor.close();
            return alumno;
        } else {
            return null;
        }
    }

    private Alumnos cursorToAlumno(Cursor cursor) {
        int idIndex = cursor.getColumnIndex("idAlumno");
        int nombreIndex = cursor.getColumnIndex("nombre");
        int apellidoIndex = cursor.getColumnIndex("apellidos");
        int correoIndex = cursor.getColumnIndex("correo");
        int carreraIdIndex = cursor.getColumnIndex("carrera_id");

        int id = cursor.getInt(idIndex);
        String nombre = cursor.getString(nombreIndex);
        String apellidos = cursor.getString(apellidoIndex);
        String correo = cursor.getString(correoIndex);
        int carreraId = cursor.getInt(carreraIdIndex);

        return new Alumnos(id, nombre, apellidos, correo, carreraId);
    }

    public List<Alumnos> listarAlumnos() {
        List<Alumnos> listaAlumnos = new ArrayList<>();
        String[] projection = {"idAlumno", "nombre", "apellidos", "correo", "carrera_id"};

        Cursor cursor = database.query(
                "alumnos",  // Nombre de la tabla
                projection,
                null,
                null,
                null,
                null,
                null
        );

        if (cursor != null) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                Alumnos alumno = cursorToAlumno(cursor);
                listaAlumnos.add(alumno);
                cursor.moveToNext();
            }
            cursor.close();
        }

        return listaAlumnos;
    }

    public int editarAlumno(Alumnos alumno) {
        ContentValues values = new ContentValues();
        values.put("nombre", alumno.getNombre());
        values.put("apellidos", alumno.getApellidos());
        values.put("correo", alumno.getCorreo());
        values.put("carrera_id", alumno.getCarrera_id());

        String whereClause = "idAlumno = ?";
        String[] whereArgs = {String.valueOf(alumno.getIdAlumno())};

        return database.update("alumnos", values, whereClause, whereArgs);
    }

    public int eliminarAlumno(int id) {
        String whereClause = "idAlumno = ?";
        String[] whereArgs = {String.valueOf(id)};

        return database.delete("alumnos", whereClause, whereArgs);
    }


}
