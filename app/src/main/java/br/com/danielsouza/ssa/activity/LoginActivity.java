package br.com.danielsouza.ssa.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.pixplicity.easyprefs.library.Prefs;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import br.com.danielsouza.ssa.R;
import br.com.danielsouza.ssa.entity.Usuarios;
import br.com.danielsouza.ssa.entity.UsuariosResponse;
import br.com.danielsouza.ssa.restImpl.RestService;
import br.com.danielsouza.ssa.restInteface.RestInterface;
import br.com.danielsouza.ssa.utils.Encripta;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Classe responsavel por iniciar a tela de login para o usuario
 * @author Daniel Jorge
 * Created by Daniel Jorge on 31/03/2016.
 */
public class LoginActivity extends AppCompatActivity {

    private RestInterface restInterface;
    private EditText txtMatricula;
    private EditText txtPassword;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        txtMatricula = (EditText) findViewById(R.id.txt_matricula);
        txtPassword = (EditText) findViewById(R.id.txt_password);
        Button btnLoggar = (Button) findViewById(R.id.btn_loggar);

        //Verifica se existe matricula e senha cadastrada no smartphone
        //Se existir, inicia a main activity
        //Se não, inicia a tela de login do usuario.
        if(Prefs.getString("senha", null) != null){
            if(Prefs.getString("matricula", null) != null) {
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                return;
            }
        }

        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("SSA Mobile");
        progressDialog.setMessage("Aguarde...");

        restInterface = RestService.getRestInterface();
        btnLoggar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                progressDialog.show();
                restInterface.getUsuariosJson(new Callback<UsuariosResponse>() {
                    @Override
                    public void success(UsuariosResponse usuariosResponse, Response response) {
                        verificarLogin(usuariosResponse, v);
                        progressDialog.dismiss();
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        progressDialog.dismiss();
                        Toast.makeText(v.getContext(), "FAILURE: "+error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
    }

    /**
     * Método responsavel por verificar se existe o usuário na base de dados do serviço e pela iniciacao da main activity
     * @param usuariosResponse
     * @param v
     */
    public void verificarLogin(UsuariosResponse usuariosResponse, View v){
        for (Usuarios u : usuariosResponse.getUsuarios()) {
            try {
                if (u.getMatricula().equals(txtMatricula.getText().toString()) && u.getSenha().equals(Encripta.encripta(txtPassword.getText().toString()))) {
                    Intent intent = new Intent(v.getContext(), MainActivity.class);
                    startActivity(intent);
                    salvarUsuario(u);
                    break;
                } else {
                    Toast.makeText(v.getContext(), "Usuario e/ou senha incorretos", Toast.LENGTH_LONG).show();
                    break;
                }
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Método responsavel por salvar usuario na base de dados para utilização posterior dentro da aplicacao
     * e para a reinicializacao do mesmo
     * @param u
     */
    private void salvarUsuario(Usuarios u) {
        String[] nomeCompleto = u.getNome().split(" ");
        String primeiroNome = nomeCompleto[0];
        String sobrenome = nomeCompleto[1];
        String nomeSalvar = primeiroNome + " " + sobrenome;

        Prefs.putString("matricula", u.getMatricula());
        Prefs.putString("senha", u.getSenha());
        Prefs.putString("nome", nomeSalvar);
        Prefs.putInt("id", u.getId());
    }
}