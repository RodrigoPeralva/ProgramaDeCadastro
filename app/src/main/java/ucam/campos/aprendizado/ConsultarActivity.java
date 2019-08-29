package ucam.campos.aprendizado;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class ConsultarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultar);
        Intent intent = getIntent();

        PessoaDB db = new PessoaDB(getApplicationContext());

        List<Pessoa> listaContendoTodasAsPessoasNoBanco = db.listar();

        for (Pessoa p : listaContendoTodasAsPessoasNoBanco){
            Log.d("P", String.format("%s, %s, %s, %s", p.getNome(), p.getEmail(), p.getCpf(), p.getSenha()));
        }

    }


}
