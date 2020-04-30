package game.t.notes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class Delete_update extends AppCompatActivity {

    boolean edit=true;

    Calendar c;
    String todaydate,currenttime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_update);
        this.getSupportActionBar().hide();


        TextView headinh=findViewById(R.id.fheading);
        final EditText du_content_du=findViewById(R.id.content);
        final ImageView du_update=findViewById(R.id.edit);
        final ImageView du_delete=findViewById(R.id.delete);

        Intent i=getIntent();
        final int ID=i.getIntExtra("ID",0);
        final String dtitle=i.getStringExtra("title");
        String dcontent=i.getStringExtra("content");

        headinh.setText(dtitle);
        du_content_du.setText(dcontent);

        c= Calendar.getInstance();
        todaydate=c.get(Calendar.YEAR)+"/"+(c.get(Calendar.MONTH)+1)+"/"+c.get(Calendar.DAY_OF_MONTH);
        currenttime=c.get(Calendar.HOUR)+":"+c.get(Calendar.MINUTE);

        du_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(edit){
                    edit=false;
                    du_content_du.setEnabled(true);
                    du_update.setImageResource(R.drawable.ic_check_black_24dp);
                }
                else if(edit==false){
                    //coding for updating the text;
                    Note note=new Note();
                    Database db=new Database(Delete_update.this);
                    //note.setTitle(dtitle);
                    note.setID(ID);
                    note.setContent(du_content_du.getText().toString());
                    note.setDate(todaydate);
                    note.setTime(currenttime);
                    db.update_content(note);


                    Toast.makeText(Delete_update.this, "Succesfully updated", Toast.LENGTH_SHORT).show();

                    MainActivity mn=new MainActivity();
                    mn.launch();
                    Delete_update.super.onBackPressed();
                }

            }
        });

        du_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Message to delete a code
                Note gs=new Note();
                Database db=new Database(Delete_update.this);
                gs.setID(ID);
                db.delete_content(gs);
                Toast.makeText(Delete_update.this, "Delete button clicked", Toast.LENGTH_SHORT).show();

                MainActivity mn=new MainActivity();
                mn.launch();
                Delete_update.super.onBackPressed();

                Toast.makeText(mn, "Successfully deleted", Toast.LENGTH_SHORT).show();
                //startActivity(new Intent(getApplicationContext(),MainActivity.class));
                //startActivity(new Intent(getApplicationContext(),MainActivity.class));
            }
        });



    }
/*
    public void Delete(View view){

    }

    public void update(View view){
        if(edit){
            edit=false;

        }

    }

 */
}
