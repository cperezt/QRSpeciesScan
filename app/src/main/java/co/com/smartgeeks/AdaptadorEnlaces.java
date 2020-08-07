package co.com.smartgeeks;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
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

public class AdaptadorEnlaces extends RecyclerView.Adapter<AdaptadorEnlaces.ViewHolder> {
    public AdaptadorEnlaces(List<ElementosEnlaces> misDatos) {
        this.misDatos = misDatos;
    }

    List<ElementosEnlaces> misDatos;
    Context context;

    @NonNull
    @Override
    public AdaptadorEnlaces.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        context = parent.getContext();

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.vista_documentos, parent, false);
        AdaptadorEnlaces.ViewHolder viewHolder = new AdaptadorEnlaces.ViewHolder(v);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull AdaptadorEnlaces.ViewHolder holder, int position) {
        holder.tvTituloArticulo.setText(misDatos.get(position).getTituloArticulo());
        holder.tvContenidoArticulo.setText(misDatos.get(position).getDescripcionArticulo());
        Glide.with(context).load(misDatos.get(position).getImagenArticulo()).into(holder.ivArticulo);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context contexto = view.getContext();
                String url = misDatos.get(position).getEnlaceArticulo();
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                contexto.startActivity(i);


            }
        });
    }

    @Override
    public int getItemCount() {
        return misDatos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView ivArticulo;
        TextView tvTituloArticulo, tvContenidoArticulo;
        CardView cardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivArticulo = (ImageView)itemView.findViewById(R.id.ivdocumentos);
            tvTituloArticulo = (TextView)itemView.findViewById(R.id.tvTituloDoc);
            tvContenidoArticulo = (TextView)itemView.findViewById(R.id.tvDescripcionDoc);
            cardView = (CardView)itemView.findViewById(R.id.cardview2);

        }
    }
}
