package com.example.n1_henriquegoebel;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    private EditText etNomeCompleto, etNomeCamiseta, etNumeroCamiseta;
    private RadioGroup rgPe;
    private RadioButton rbCanhoto, rbDestro, rbAmbidestro, rbVazio;
    private CheckBox cbGoleiro, cbLateral, cbZagueiro, cbMeia, cbAtacante;
    private Button btSalvar;
    private String acao;
    private Jogador jogador;
    private CheckBox cbTitular;

    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference reference;

    FirebaseAuth auth;
    FirebaseAuth.AuthStateListener authStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        etNomeCompleto = findViewById(R.id.etNomeCompleto);
        etNomeCamiseta = findViewById(R.id.etNomeCamiseta);
        etNumeroCamiseta = findViewById(R.id.etNumeroCamiseta);
        rgPe = findViewById(R.id.rgPe);
        rbCanhoto = findViewById(R.id.rbCanhoto);
        rbDestro = findViewById(R.id.rbDestro);
        rbAmbidestro = findViewById(R.id.rbAmbidestro);
        cbGoleiro = findViewById(R.id.cbGoleiro);
        cbLateral = findViewById(R.id.cbLateral);
        cbZagueiro = findViewById(R.id.cbZagueiro);
        cbMeia = findViewById(R.id.cbMeia);
        cbAtacante = findViewById(R.id.cbAtacante);
        btSalvar = findViewById(R.id.btSalvar);
        cbTitular = findViewById(R.id.cbTitular);

        rbVazio = findViewById(R.id.rbVazio);
        rbVazio.setVisibility(View.INVISIBLE);
        rbVazio.setChecked(true);

        acao = getIntent().getStringExtra("acao");
        if(acao.equals("editar")){
            //carregarFormulario();
            jogador = new Jogador();
            jogador.setId(getIntent().getExtras().getString("idJogador"));

            jogador.setNomeCompleto(getIntent().getExtras().getString("nomeCompleto"));
            etNomeCompleto.setText(jogador.nomeCompleto);
            etNomeCamiseta.setText(getIntent().getExtras().getString("nomeCamiseta"));
            etNumeroCamiseta.setText(getIntent().getExtras().getString("numeroCamiseta"));

            jogador.setTitular(getIntent().getExtras().getString("titular"));
            if(jogador.titular.equals("1")){
                cbTitular.setChecked(true);
            }

            jogador.setPePreferencial(getIntent().getExtras().getString("pePreferencial"));

            if (jogador.pePreferencial.equals(getString(R.string.rb_canhoto))){
                rbCanhoto.setChecked(true);
            }else if (jogador.pePreferencial.equals(getString(R.string.rb_destro))){
                rbDestro.setChecked(true);
            }else if (jogador.pePreferencial.equals(getString(R.string.rb_ambidestro))){
                rbAmbidestro.setChecked(true);
            }else if (jogador.pePreferencial.equals("Vazio")){
                rbVazio.setChecked(true);
            }

            jogador.setGoleiro(getIntent().getExtras().getString("goleiro"));
            if(jogador.goleiro.equals("1")){
                cbGoleiro.setChecked(true);
            }

            jogador.setZagueiro(getIntent().getExtras().getString("zagueiro"));
            if(jogador.zagueiro.equals("1")){
                cbZagueiro.setChecked(true);
            }

            jogador.setLateral(getIntent().getExtras().getString("lateral"));
            if(jogador.lateral.equals("1")){
                cbLateral.setChecked(true);
            }

            jogador.setMeia(getIntent().getExtras().getString("meia"));
            if(jogador.meia.equals("1")){
                cbMeia.setChecked(true);
            }

            jogador.setAtacante(getIntent().getExtras().getString("atacante"));
            if(jogador.atacante.equals("1")){
                cbAtacante.setChecked(true);
            }
        }

        btSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                salvar();
            }
        });

        auth = FirebaseAuth.getInstance();
        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if( auth.getCurrentUser() == null ){
                    finish();
                }
            }
        };
        auth.addAuthStateListener( authStateListener );

    }

    private void carregarFormulario(){
        int idJogador = getIntent().getIntExtra("idJogador", 0);
        if(idJogador != 0){
            //jogador = JogadorDAO.getJogadorbyID(this, idJogador);
            jogador = new Jogador();

            etNomeCompleto.setText(jogador.nomeCompleto);
            etNomeCamiseta.setText(jogador.nomeCamiseta);
            etNumeroCamiseta.setText(jogador.getNumeroCamiseta());

            if (jogador.pePreferencial.equals(getString(R.string.rb_canhoto))){
                rbCanhoto.setChecked(true);
            }else if (jogador.pePreferencial.equals(getString(R.string.rb_destro))){
                rbDestro.setChecked(true);
            }else if (jogador.pePreferencial.equals(getString(R.string.rb_ambidestro))){
                rbAmbidestro.setChecked(true);
            }else if (jogador.pePreferencial.equals("Vazio")){
                rbVazio.setChecked(true);
            }
            if(jogador.goleiro.equals(1)){
                cbGoleiro.setChecked(true);
            }
            if(jogador.zagueiro.equals(1)){
                cbZagueiro.setChecked(true);
            }
            if(jogador.lateral.equals(1)){
                cbLateral.setChecked(true);
            }
            if(jogador.meia.equals(1)){
                cbMeia.setChecked(true);
            }
            if(jogador.atacante.equals(1)){
                cbAtacante.setChecked(true);
            }

        }
    }

    private void salvar(){

        if(acao.equals("novo")){
            jogador = new Jogador();
        }

        String nomeCamiseta = etNomeCamiseta.getText().toString();
        String numeroCamiseta = etNumeroCamiseta.getText().toString();

        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference reference = firebaseDatabase.getReference();


        if(!(nomeCamiseta.isEmpty() || numeroCamiseta.isEmpty())){


            jogador.nomeCompleto = etNomeCompleto.getText().toString();
            if(jogador.nomeCompleto.equals(null)){
                jogador.nomeCompleto = "";
            }
            jogador.nomeCamiseta = etNomeCamiseta.getText().toString();
            jogador.numeroCamiseta = etNumeroCamiseta.getText().toString();
            jogador.setIdUsuario(auth.getCurrentUser().getUid());

            if(cbTitular.isChecked()){
                jogador.titular = "1";
            }else{
                jogador.titular = "0";
            }


            int radioButtonId = rgPe.getCheckedRadioButtonId();
            String tempPe = ((RadioButton) findViewById(radioButtonId)).getText().toString();
            if(tempPe.equals(getString(R.string.rb_canhoto))){
                jogador.pePreferencial = "Canhoto";
            }else if(tempPe.equals(getString(R.string.rb_destro))){
                jogador.pePreferencial = "Destro";
            }else if(tempPe.equals(getString(R.string.rb_ambidestro))){
                jogador.pePreferencial = "Ambidestro";
            }else{
                jogador.pePreferencial = "Vazio";
            }


            //jogador.pePreferencial = ((RadioButton) findViewById(radioButtonId)).getText().toString();

            if(cbGoleiro.isChecked()){
                jogador.goleiro = "1";
            }else{
                jogador.goleiro = "0";
            }
            if(cbLateral.isChecked()){
                jogador.lateral = "1";
            }else{
                jogador.lateral = "0";
            }
            if(cbZagueiro.isChecked()){
                jogador.zagueiro = "1";
            }else{
                jogador.zagueiro = "0";
            }
            if(cbMeia.isChecked()){
                jogador.meia = "1";
            }else{
                jogador.meia = "0";
            }
            if(cbAtacante.isChecked()){
                jogador.atacante = "1";
            }else{
                jogador.atacante = "0";
            }

            if(acao.equals("novo")){
                reference.child("jogadores").push().setValue( jogador );
            }else{
                // String idJogador =  jogador.getId();
                // jogador.setId( null );
                reference.child("jogadores").child( jogador.getId() ).setValue( jogador );

            }
            finish();

            /* if(acao.equals("editar")){
                JogadorDAO.editar(this, jogador);
                finish();
            }else {
                JogadorDAO.inserir(this, jogador);
            } */

            Intent intent = new Intent(MainActivity.this, TelaListaJogadores.class);
            intent.putExtra("acao", "salvar");
            startActivity(intent);


        }else{
            Toast.makeText(MainActivity.this, "Nome e número da Camiseta são campos obrigatórios.", Toast.LENGTH_LONG).show();

        }



    }

    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_formulario, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.menuSair){
            auth.signOut();
        }else if (id == R.id.menuVoltarLista){
            Intent intent = new Intent(MainActivity.this, TelaListaJogadores.class);
            intent.putExtra("acao", "voltar");
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);

    }
}