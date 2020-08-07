package co.com.smartgeeks;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;


import com.bumptech.glide.Glide;

import java.util.List;


public class MiAdaptador  extends RecyclerView.Adapter<MiAdaptador.ListaViewHolder>{


    List<ElementosLista> misDatos;
    Context context;

    public MiAdaptador(List<ElementosLista> misDatos) {
        this.misDatos = misDatos;
    }

    @NonNull
    @Override
    public MiAdaptador.ListaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        context = parent.getContext();

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.vista_lista, parent, false);

        ListaViewHolder listaViewHolder = new ListaViewHolder(v);
        return listaViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MiAdaptador.ListaViewHolder holder, final int position) {

        Glide.with(context).load(misDatos.get(position).getImagen()).into(holder.imagenLista);
        holder.textoEspecieCodigo.setText(misDatos.get(position).getId()+": "+misDatos.get(position).getEspecieCodigo());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context contexto = v.getContext();
                String url = "https://smartgeeks.com.co/SpeciesQrScanner/getData.php?id="+misDatos.get(position).getId();
                Intent intent = new Intent(context, datosespecie.class);
                intent.putExtra("url",url);
                contexto.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return misDatos.size();
    }

    public static class ListaViewHolder extends RecyclerView.ViewHolder{


        ImageView imagenLista;
        TextView textoEspecieCodigo;
        CardView  cardView;

        public ListaViewHolder(@NonNull View itemView) {
            super(itemView);
            imagenLista = (ImageView)itemView.findViewById(R.id.ivLista);
            textoEspecieCodigo = (TextView)itemView.findViewById(R.id.tvLista);
            cardView = (CardView) itemView.findViewById(R.id.miCardView);
        }
    }
}
