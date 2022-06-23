package com.example.n1_henriquegoebel;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toolbar;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.ArrayList;
import java.util.List;

public class TelaListaJogadores extends AppCompatActivity {

    private ListView lvJogadores;
    //private AdapterJogadores adapter;
    private ArrayAdapter adapter;
    private List<Jogador> listaJogadores;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference reference;
    ChildEventListener childEventListener;
    Query query;

    private FirebaseAuth auth;
    private FirebaseAuth.AuthStateListener authStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_lista_jogadores);

        firebaseDatabase = FirebaseDatabase.getInstance();
        reference = firebaseDatabase.getReference();

        listaJogadores = new ArrayList<Jogador>();
        lvJogadores = findViewById(R.id.lvJogadores);
        //carregarJogadores();
        configurarListView();

        Button btnAdicionar = findViewById(R.id.btnAdicionar);
        btnAdicionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TelaListaJogadores.this, MainActivity.class);
                intent.putExtra("acao", "novo");
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
        listaJogadores.clear();

        query = reference.child("jogadores");//.orderByChild("nomeCompleto");
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

                        listaJogadores.add(jogador);
                        adapter.notifyDataSetChanged();
                    }

                } catch (Exception e) {

                }

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                String idJogadores = snapshot.getKey();

                for (Jogador j : listaJogadores) {
                    if (j.getId().equals(snapshot.getKey())) {
                        j.setNomeCompleto(snapshot.child("nomeCompledo").getValue(String.class));
                        j.setNomeCamiseta(snapshot.child("nomeCamiseta").getValue(String.class));
                        j.setNumeroCamiseta(snapshot.child("numeroCamiseta").getValue(String.class));
                        j.setPePreferencial(snapshot.child("pePreferencial").getValue(String.class));
                        j.setGoleiro(snapshot.child("goleiro").getValue(String.class));
                        j.setZagueiro(snapshot.child("zagueiro").getValue(String.class));
                        j.setMeia(snapshot.child("meia").getValue(String.class));
                        j.setLateral(snapshot.child("lateral").getValue(String.class));
                        j.setAtacante(snapshot.child("atacante").getValue(String.class));
                        break;

                    }
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {
                String idJogadores = snapshot.getKey();

                for (Jogador j : listaJogadores) {
                    if (j.getId().equals(idJogadores)) {
                        listaJogadores.remove(j);
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

    private void configurarListView(){

        lvJogadores.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Jogador jogadorSelecionado = listaJogadores.get(position);
                Intent intent = new Intent(TelaListaJogadores.this, MainActivity.class);
                intent.putExtra("acao", "editar");
                intent.putExtra("idJogador", jogadorSelecionado.getId());
                intent.putExtra("nomeCompleto", jogadorSelecionado.getNomeCompleto());
                intent.putExtra("nomeCamiseta", jogadorSelecionado.getNomeCamiseta());
                intent.putExtra("numeroCamiseta", jogadorSelecionado.getNumeroCamiseta());
                intent.putExtra("pePreferencial", jogadorSelecionado.getPePreferencial());
                intent.putExtra("goleiro", jogadorSelecionado.getGoleiro());
                intent.putExtra("zagueiro", jogadorSelecionado.getZagueiro());
                intent.putExtra("lateral", jogadorSelecionado.getLateral());
                intent.putExtra("meia", jogadorSelecionado.getMeia());
                intent.putExtra("atacante", jogadorSelecionado.getAtacante());

                startActivity(intent);
            }
        });

        lvJogadores.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long id) {
                Jogador jogadorSelecionado = listaJogadores.get(position);
                excluirJogador(jogadorSelecionado);
                return true;
            }
        });

    }


    private void excluirJogador(Jogador jogador){
        AlertDialog.Builder alerta = new AlertDialog.Builder(this);
        alerta.setIcon(android.R.drawable.ic_input_delete);
        alerta.setTitle(getString(R.string.txt_excluir_atencao));
        alerta.setMessage(getString(R.string.txt_excluir_message) + " " + jogador.nomeCamiseta);
        alerta.setNeutralButton(getString(R.string.txt_excluir_cancelar), null);
        alerta.setPositiveButton(getString(R.string.txt_excluir_confirmar), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //JogadorDAO.excluir(TelaListaJogadores.this, jogador.id);
                reference.child("jogadores").child( jogador.getId() ).removeValue();
                //carregarJogadores();
            }
        });
        alerta.show();
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
       //listaJogadores = JogadorDAO.getJogadores(this);
        /*if (listaJogadores.size() == 0){
            Jogador fake = new Jogador("", "Lista Vazia ");
            listaJogadores.add( fake );
            lvJogadores.setEnabled(false);
        }else{
            //adapter = new AdapterJogadores(this,listaJogadores);
            //lvJogadores.setAdapter(adapter);
            adapter = new AdapterJogadores(this,android.R.layout.simple_list_item_1, listaJogadores);
            lvJogadores.setAdapter(adapter);
        }*/
        adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1, listaJogadores);
        lvJogadores.setAdapter(adapter);

    }

    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.menuSair){
            auth.signOut();
        }
        return super.onOptionsItemSelected(item);

    }


}