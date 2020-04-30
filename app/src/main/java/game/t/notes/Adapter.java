package game.t.notes;

import android.content.Context;
import android.content.Intent;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    LayoutInflater inflater;
    List<Note> notes;

    public Adapter(MainActivity context, List<Note> notes) {
        this.inflater=LayoutInflater.from(context);
        this.notes=notes;
    }

    @NonNull
    @Override
    public Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=inflater.inflate(R.layout.recycler_view,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter.ViewHolder holder, int position) {
        String title=notes.get(position).getTitle();
        String date=notes.get(position).getDate();
        String time=notes.get(position).getTime();
        holder.nTitle.setText(title);
        holder.nTime.setText(time);
        holder.nDate.setText(date);
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView nTitle,nDate,nTime;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            nTitle=itemView.findViewById(R.id.nTitle);
            nDate=itemView.findViewById(R.id.ndate);
            nTime=itemView.findViewById(R.id.ntime);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i=new Intent(view.getContext(),Delete_update.class);

                    //Toast.makeText(, "", Toast.LENGTH_SHORT).show();
                    //i.putExtra()
                    i.putExtra("title",notes.get(getAdapterPosition()).getTitle());
                    i.putExtra("content",notes.get(getAdapterPosition()).getContent());
                    i.putExtra("ID",notes.get(getAdapterPosition()).getID());
                    //Toast.makeText(view, " ", Toast.LENGTH_SHORT).show();
                    view.getContext().startActivity(i);
                    //Toast.makeText(, "", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
