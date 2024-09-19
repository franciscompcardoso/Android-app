package Zarco.com;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.RelativeLayout;

import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements FuncionariosListener {

    //TextView textView;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.lista_funcionarios)
    RecyclerView lista_funcionarios;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.layout_inicial)
    RelativeLayout layout_inicial;

    FuncionariosListener funcionariosLoadListener;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


      /*  textView = findViewById(R.id.textView);

        Calendar calendar = Calendar.getInstance();
        @SuppressLint("SimpleDateFormat") SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEEE, dd/MMMM/yyyy, HH:mm:ss");
        String dateTime = simpleDateFormat.format(calendar.getTime());
        textView.setText(dateTime);*/

        init();
        loadFuncionariosFromFirebase();

    }

    private void loadFuncionariosFromFirebase() {
        List<ModeloFuncionarios> modeloFuncionarios = new ArrayList<>();
        FirebaseDatabase.getInstance("https://seguitex-default-rtdb.europe-west1.firebasedatabase.app/")
                .getReference("Funcionário")
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(snapshot.exists())
                        {
                            for(DataSnapshot funcionarioSnapshot:snapshot.getChildren())
                            {
                                ModeloFuncionarios modeloFuncionario = funcionarioSnapshot.getValue(ModeloFuncionarios.class);
                                assert modeloFuncionario != null;
                                modeloFuncionario.setKey(funcionarioSnapshot.getKey());
                                modeloFuncionarios.add(modeloFuncionario);
                            }
                            funcionariosLoadListener.onFuncionariosLoadSuccess(modeloFuncionarios);
                        }
                        else
                            funcionariosLoadListener.onFuncionariosLoadFailed("Não foi possível encontrar funcionários");

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        funcionariosLoadListener.onFuncionariosLoadFailed(error.getMessage());
                    }
                });
    }

    public void init(){
        ButterKnife.bind(this);

        funcionariosLoadListener = this;
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        lista_funcionarios.setLayoutManager(gridLayoutManager);
        lista_funcionarios.addItemDecoration(new SpaceItemDecoration());
    }

    @Override
    public void onFuncionariosLoadSuccess(List<ModeloFuncionarios> funcionariosList) {
        MyFuncionarioAdapter adapter = new MyFuncionarioAdapter(this, funcionariosList);
        lista_funcionarios.setAdapter(adapter);


    }

    public void onFuncionariosLoadFailed(String mensagem){
        Snackbar.make(layout_inicial, mensagem, Snackbar.LENGTH_LONG).show();

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}