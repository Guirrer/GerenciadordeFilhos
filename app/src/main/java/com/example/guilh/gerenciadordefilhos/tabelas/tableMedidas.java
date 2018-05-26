package com.example.guilh.gerenciadordefilhos.tabelas;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.guilh.gerenciadordefilhos.Util.Database;

public class tableMedidas extends Database {
    private static  final String TABLE = "medidas";

    private Integer filho_id;
    private String data_dado;
    private Float peso;
    private Integer tam_pe;
    private Float altura;
    private SQLiteDatabase db;

    public tableMedidas(Context context) {
        super(context);
        db = this.getWritableDatabase();
    }

    public Integer getFilho_id() {
        return filho_id;
    }

    public void setFilho_id(Integer filho_id) {
        this.filho_id = filho_id;
    }

    public String getData_dado() {
        return data_dado;
    }

    public void setData_dado(String data_dado) {
        this.data_dado = data_dado;
    }

    public Float getPeso() {
        return peso;
    }

    public void setPeso(Float peso) {
        this.peso = peso;
    }

    public Integer getTam_pe() {
        return tam_pe;
    }

    public void setTam_pe(Integer tam_pe) {
        this.tam_pe = tam_pe;
    }

    public Float getAltura() {
        return altura;
    }

    public void setAltura(Float altura) {
        this.altura = altura;
    }

    private static final String CREATE =
            "CREATE TABLE IF NOT EXISTS " + TABLE + " ( " +
                    "id INT(10) UNSIGNED NOT NULL AUTO_INCREMENT, " +
                    "filho_id INT(10) UNSIGNED NOT NULL, " +
                    "data_dado DATE NOT NULL, " +
                    "peso FLOAT UNSIGNED NULL DEFAULT NULL, " +
                    "tam_pe INT(11) UNSIGNED NULL DEFAULT NULL, " +
                    "altura FLOAT NULL DEFAULT NULL, " +
                    "PRIMARY KEY (id), " +
                    "INDEX filho_FKIndex1 (filho_id), " +
                    "CONSTRAINT medidas_ibfk_1 FOREIGN KEY (filho_id) REFERENCES filho (id) ON DELETE NO ACTION ON UPDATE NO ACTION" +
                    ")";

    private static final String DROP = "DROP TABLE IF EXISTS " + TABLE;

    public String create() { return CREATE; }

    public String upgrade() { return DROP; }

    public long insert()
    {
        return db.insert(TABLE, null, retornaValues());
    }

    private ContentValues retornaValues()
    {
        ContentValues values = new ContentValues();

        values.put("filho_id", filho_id);
        values.put("data_dado", data_dado);
        values.put("peso", peso);
        values.put("tam_pe", tam_pe);
        values.put("altura", altura);

        return  values;
    }
}