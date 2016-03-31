package br.com.danielsouza.ssa.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import br.com.danielsouza.ssa.R;
import br.com.danielsouza.ssa.entity.Usuarios;
import br.com.danielsouza.ssa.extensions.RestService;
import br.com.danielsouza.ssa.interfaces.RestInterface;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by daniel.souza on 31/03/2016.
 */
public class LoginActivity extends AppCompatActivity {

    private RestInterface restInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final EditText txtMatricula = (EditText) findViewById(R.id.txt_matricula);
        final EditText txtPassword = (EditText) findViewById(R.id.txt_password);
        Button btnLoggar = (Button) findViewById(R.id.btn_loggar);

        restInterface = RestService.getRestInterface();
        btnLoggar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                restInterface.getUsuariosJson(new Callback<List<Usuarios>>() {
                    @Override
                    public void success(List<Usuarios> usuarios, Response response) {
                        for (Usuarios u : usuarios) {
                            if (u.getMatricula().equals(txtMatricula.getText().toString()) && u.getSenha().equals(txtPassword.getText().toString())) {
                                Intent intent = new Intent(v.getContext(), MainActivity.class);
                                startActivity(intent);
                            } else {
                                Toast.makeText(v.getContext(), "Usuario e/ou senha incorretos", Toast.LENGTH_LONG).show();
                            }
                        }
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        Toast.makeText(v.getContext(), "Failure: " + error.getMessage(), Toast.LENGTH_LONG).show();
                        error.printStackTrace();
                    }
                });

            }
        });

    }
}
