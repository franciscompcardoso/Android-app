package Zarco.com;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyFuncionarioAdapter extends RecyclerView.Adapter<MyFuncionarioViewHolder>{

    private final Context context;
    private final List<ModeloFuncionarios> modeloFuncionariosList;

    public MyFuncionarioAdapter(Context context, List<ModeloFuncionarios> modeloFuncionariosList){
        this.context = context;
        this.modeloFuncionariosList = modeloFuncionariosList;
    }

    @NonNull
    @Override
    public MyFuncionarioViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyFuncionarioViewHolder(LayoutInflater.from(context)
        .inflate(R.layout.layout_funcionario, parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyFuncionarioViewHolder holder, int position) {
        Glide.with(context)
                .load(modeloFuncionariosList.get(position).getImage())
                .into(holder.imageView);
        holder.txtName.setText(new StringBuilder().append(modeloFuncionariosList.get(position).getNome()));

    }

    @Override
    public int getItemCount() {
        return modeloFuncionariosList.size();
    }

}
