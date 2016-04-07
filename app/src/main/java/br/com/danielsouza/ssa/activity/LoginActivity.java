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
 * Created by Daniel Jorge on 31/03/2016.
 */
public class LoginActivity extends AppCompatActivity {

    private RestInterface restInterface;
    private EditText txtMatricula;
    private EditText txtPassword;
    private Button btnLoggar;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        txtMatricula = (EditText) findViewById(R.id.txt_matricula);
        txtPassword = (EditText) findViewById(R.id.txt_password);
        btnLoggar = (Button) findViewById(R.id.btn_loggar);

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

    @Override
    protected void onRestart() {
        super.onRestart();

        Prefs.getString("matricula", "");
        Prefs.getString("senha", "");
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }

    public void verificarLogin(UsuariosResponse usuariosResponse, View v){
        for (Usuarios u : usuariosResponse.getUsuarios()) {
            try {
                if (u.getMatricula().equals(txtMatricula.getText().toString()) && u.getSenha().equals(Encripta.encripta(txtPassword.getText().toString()))) {
                    Intent intent = new Intent(v.getContext(), MainActivity.class);
                    startActivity(intent);
                    salvarUsuario(u.getMatricula(), u.getSenha(), u.getNome());
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

    private void salvarUsuario(String matricula, String senha, String nome) {
        String[] nomeCompleto = nome.split(" ");
        String primeiroNome = nomeCompleto[0];
        String sobrenome = nomeCompleto[1];
        String nomeSalvar = primeiroNome + " " + sobrenome;

        Prefs.putString("matricula", matricula);
        Prefs.putString("senha", senha);
        Prefs.putString("nome", nomeSalvar);
    }
}
