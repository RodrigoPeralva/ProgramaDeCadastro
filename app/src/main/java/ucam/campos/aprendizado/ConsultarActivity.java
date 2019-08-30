package ucam.campos.aprendizado;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TableLayout;
import android.widget.TableRow;

import java.util.ArrayList;
import java.util.List;

public class ConsultarActivity extends AppCompatActivity {
    TableLayout pessoasTable;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        pessoasTable = findViewById(R.id.pessoasTable);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultar);
        Intent intent = getIntent();

        PessoaDB db = new PessoaDB(getApplicationContext());

        List<Pessoa> listaContendoTodasAsPessoasNoBanco = db.listar();

        for (Pessoa p : listaContendoTodasAsPessoasNoBanco){
            Log.d("PessoaDb", String.format("%s, %s, %s, %s", p.getNome(), p.getEmail(), p.getCpf(), p.getSenha()));
//            TableRow pessoaRow = new TableRow(getApplicationContext());
//            pessoaRow.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
//            pessoasTable.addView(pessoaRow, new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT));
        }


    }


}
