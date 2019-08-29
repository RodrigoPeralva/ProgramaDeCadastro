package ucam.campos.aprendizado;

public class Pessoa {
    private String nome;
    private String email;
    private String cpf;
    private String senha;

    // Isso é o metodo construtor , que ao mesmo tempo que instacia a classe , você pode setar seus parametros
    public  Pessoa(String nome,String email, String cpf, String senha){
        this.nome = nome;
        this.email = email;
        this.cpf = cpf;
        this.senha = senha;
    }

    public void setNome(String nome){
        this.nome = nome;
    }
    public String getNome(){
        return this.nome;
    }

    public void setEmail(String email){
        this.email = email;
    }
    public String getEmail(){
        return this.email;
    }

    public void setCpf(String cpf){
        this.cpf = cpf;
    }
    public String getCpf(){
        return this.cpf;
    }

    public void setSenha(String senha){
        this.senha= senha;
    }
    public String getSenha(){
        return this.senha;
    }


}
