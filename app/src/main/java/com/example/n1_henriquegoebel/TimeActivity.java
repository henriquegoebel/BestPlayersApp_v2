package com.example.n1_henriquegoebel;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.ArrayList;
import java.util.List;

public class TimeActivity extends AppCompatActivity {

    private ListView lvTime;
    private Button btnAdicionarJogador;

    //private AdapterJogadores adapter;
    private ArrayAdapter adapter;
    private List<Jogador> listaJogadoresTime = new ArrayList<>();

    FirebaseDatabase firebaseDatabase;
    DatabaseReference reference;
    ChildEventListener childEventListener;
    Query query;

    private FirebaseAuth auth;
    private FirebaseAuth.AuthStateListener authStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time);

        firebaseDatabase = FirebaseDatabase.getInstance();
        reference = firebaseDatabase.getReference();

        //listaJogadoresTime = new ArrayList<Jogador>();
        lvTime = findViewById(R.id.lvTime);

        //configurarListView();

        btnAdicionarJogador = findViewById(R.id.btnAdicionarJogador);
        btnAdicionarJogador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TimeActivity.this, TelaListaJogadores.class);
                intent.putExtra("acao", "adicionar");
                startActivity(intent);
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

    protected void onStart() {
        super.onStart();
        carregarJogadores();
        listaJogadoresTime.clear();

        query = reference.child("jogadores").orderByChild("titular").equalTo("1");
        childEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                try {
                    String idUser = snapshot.child("idUsuario").getValue(String.class);

                    if(idUser.equals(auth.getCurrentUser().getUid())){
                        Jogador jogador = new Jogador();
                        jogador.setId(snapshot.getKey());
                        jogador.setNomeCompleto(snapshot.child("nomeCompleto").getValue(String.class));
                        jogador.setNomeCamiseta(snapshot.child("nomeCamiseta").getValue(String.class));
                        jogador.setNumeroCamiseta(snapshot.child("numeroCamiseta").getValue(String.class));
                        jogador.setPePreferencial(snapshot.child("pePreferencial").getValue(String.class));
                        jogador.setGoleiro(snapshot.child("goleiro").getValue(String.class));
                        jogador.setZagueiro(snapshot.child("zagueiro").getValue(String.class));
                        jogador.setMeia(snapshot.child("meia").getValue(String.class));
                        jogador.setLateral(snapshot.child("lateral").getValue(String.class));
                        jogador.setAtacante(snapshot.child("atacante").getValue(String.class));
                        jogador.setTitular(snapshot.child("titular").getValue(String.class));

                        listaJogadoresTime.add(jogador);
                        adapter.notifyDataSetChanged();
                    }

                } catch (Exception e) {

                }

            }


            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                String idJogadores = snapshot.getKey();

                for (Jogador j : listaJogadoresTime) {
                    if (j.getId().equals(snapshot.getKey())) {
                        j.setNomeCompleto(snapshot.child("nomeCompleto").getValue(String.class));
                        j.setNomeCamiseta(snapshot.child("nomeCamiseta").getValue(String.class));
                        j.setNumeroCamiseta(snapshot.child("numeroCamiseta").getValue(String.class));
                        j.setPePreferencial(snapshot.child("pePreferencial").getValue(String.class));
                        j.setGoleiro(snapshot.child("goleiro").getValue(String.class));
                        j.setZagueiro(snapshot.child("zagueiro").getValue(String.class));
                        j.setMeia(snapshot.child("meia").getValue(String.class));
                        j.setLateral(snapshot.child("lateral").getValue(String.class));
                        j.setAtacante(snapshot.child("atacante").getValue(String.class));
                        j.setTitular(snapshot.child("titular").getValue(String.class));
                        break;

                    }
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {
                String idJogadores = snapshot.getKey();

                for (Jogador j : listaJogadoresTime) {
                    if (j.getId().equals(idJogadores)) {
                        listaJogadoresTime.remove(j);
                        break;

                    }
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };
        query.addChildEventListener(childEventListener);
    }

    protected void onStop() {
        super.onStop();
        query.removeEventListener( childEventListener );
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        carregarJogadores();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void carregarJogadores(){
        adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1, listaJogadoresTime);
        lvTime.setAdapter(adapter);
    }

    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_formulario, menu);
        return true;
    }

    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.menuSair){
            auth.signOut();
        }else if (id == R.id.menuVoltarLista){
            Intent intent = new Intent(TimeActivity.this, TelaListaJogadores.class);
            intent.putExtra("acao", "voltar");
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);

    }
}