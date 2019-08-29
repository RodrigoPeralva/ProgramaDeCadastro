package ucam.campos.aprendizado;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class PessoaDB extends SQLiteOpenHelper {
    public static final String DB_NAME = "cadastro";
    public static final String TABLE_NAME = "pessoa";
    public static final String NOME_COLUMN = "nome";
    public static final String EMAIL_COLUMN = "email";
    public static final String CPF_COLUMN = "cpf";
    public static final String SENHA_COLUMN = "senha";

    private static final String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " + TABLE_NAME;

    public PessoaDB(Context context){
        super(context, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(String.format("create table %s(%s text, %s text, %s text, %s text)",
                TABLE_NAME,
                NOME_COLUMN,
                EMAIL_COLUMN,
                CPF_COLUMN,
                SENHA_COLUMN
                )
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }

    public boolean criar(Pessoa pessoa){
        SQLiteDatabase db = getWritableDatabase();
        try{
            ContentValues valores = new ContentValues();
            //NOME_DA_INSTANCIA.put(NOME_DA_COLUNA,VALOR_A_SER_INSERIDO);
            valores.put(NOME_COLUMN, pessoa.getNome());
            valores.put(SENHA_COLUMN, pessoa.getSenha());
            valores.put(EMAIL_COLUMN, pessoa.getEmail());
            valores.put(CPF_COLUMN, pessoa.getCpf());

            return db.insertWithOnConflict(TABLE_NAME, null, valores, SQLiteDatabase.CONFLICT_REPLACE) > 0;
        }
        catch (SQLException ex){
            Log.e("CriarPessoa", ex.getMessage());
            return false;
        }
        finally {
            db.close();
        }
    }

    public List<Pessoa> listar(){
        ArrayList<Pessoa> pessoas = new ArrayList<>();
        try{
            SQLiteDatabase db = getReadableDatabase();
            Cursor cursor = db.rawQuery("select * from " + TABLE_NAME, null);
            if (cursor.moveToFirst()){
                do {
                    //Só usa a instacia dessa maneira caso tenha um metodo construtor(Ver na classe Pessoa)
                    Pessoa pessoa = new Pessoa(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3));
                    //Se não tiver o metodo construtor usa dessa maneira aqui :
//                    Pessoa pessoa = new Pessoa();
//                    pessoa.setNome(cursor.getString(0));
//                    pessoa.setEmail(cursor.getString(1));
//                    pessoa.setCpf(cursor.getString(2));
//                    pessoa.setSenha(cursor.getString(3));
                    continue;
                } while (cursor.moveToNext());
            }
        }
        catch (SQLException ex){
            pessoas = null;
        }
        return pessoas;
    }
}
