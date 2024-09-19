package Zarco.com;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class MyFuncionarioViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.imageView)
    ImageView imageView;
    @BindView(R.id.txtName)
    TextView txtName;


    private Unbinder unbinder;

    public MyFuncionarioViewHolder(@NonNull View itemView) {

        super(itemView);
        unbinder = ButterKnife.bind(this, itemView);
    }
}
