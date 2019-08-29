package ucam.campos.aprendizado;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    EditText nomeEtv,
             emailEtv,
             senhaEtv,
             cpfEtv;
    Pessoa pessoa = new Pessoa();
    ArrayList<Pessoa> listaDePessoa = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nomeEtv = findViewById(R.id.nomeEtv);
        cpfEtv = findViewById(R.id.cpfEtv);
        emailEtv = findViewById(R.id.emailEtv);
        senhaEtv = findViewById(R.id.senhaEtv);
        //cadastrarBtn = (View) findViewById(R.id.cadastrarBtn);
    }

    public void cadastrar(View view){
        // Converte a classe Editable do "Etv" para uma variavel que é uma string
        String nome = nomeEtv.getText().toString();
        String cpf = cpfEtv.getText().toString();
        String senha = senhaEtv.getText().toString();
        String email = emailEtv.getText().toString();

        // Converte string p/ double
       // Double cpfDouble = Double.parseDouble(cpf); // Integer.parseInt(), Float.parseFloat(), String.valueOf(//QUALQUER COISA);

        // Isso de baixo , eh a mesma coisa de cima , so que comprimido

        //pessoa.setNome(nomeEtv.getText().toString());
        //pessoa.setCpf(Double.parseDouble(cpf));

        //Log.d("Classes", "Class de nomeEtv: " + nomeEtv.getText().getClass().getName());

        pessoa.setNome(nome);
        pessoa.setCpf(cpf);
        pessoa.setEmail(email);
        pessoa.setSenha(senha);

        listaDePessoa.add(pessoa);

        PessoaDB db = new PessoaDB(getApplicationContext());

        if (db.criar(pessoa)){
            Toast.makeText(getApplicationContext(), "Pessoa " + pessoa.getNome() + " criado!", Toast.LENGTH_LONG).show();
        }
        else {
            Toast.makeText(getApplicationContext(), "Falha ao criar!", Toast.LENGTH_LONG).show();
        }


        nomeEtv.setText("");
        senhaEtv.setText("");
        cpfEtv.setText("");
        emailEtv.setText("");
    }

    public void consultar(View view){
        Intent intent = new Intent(MainActivity.this, ConsultarActivity.class);
        MainActivity.this.startActivity(intent);

        for (Pessoa i : listaDePessoa){
            // Aqui é o "i" não é um numero , mas sim uma "pessoa" então se usa o "i" para pegar o nome , email , cpf , senha , da "pessoa" naquela posição do array
            Log.d("Pessoa", String.format("ID: %s, Nome: %s, Senha: %s, Cpf: %s, Email: %s", listaDePessoa.indexOf(i),  i.getNome(), i.getSenha(), i.getCpf(), i.getEmail()));
        }

//        for (int i = 0; i < listaDePessoa.size(); i++){
//            // listaDePessoa.get(i) -> listaDePessoa[i]
//            // Aqui é organizado por numero então ele tem que pegar o numero para poder pegar os dados da "pessoa" que está naquela posição
//            Log.d("Pessoa", String.format("ID: %s, Nome: %s, Senha: %s, Cpf: %s, Email: %s", listaDePessoa.indexOf(listaDePessoa.get(i)),  listaDePessoa.get(i).getNome(), listaDePessoa.get(i).getSenha(), listaDePessoa.get(i).getCpf(), listaDePessoa.get(i).getEmail()));
//        }


    }
}

