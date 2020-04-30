package game.t.notes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.Calendar;

public class AddNote extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);
        this.getSupportActionBar().hide();


        ImageView Ssave=findViewById(R.id.save);
        final EditText atitle=findViewById(R.id.title);
        final EditText acontent=findViewById(R.id.content);
        final TextView headin=findViewById(R.id.heading);
        Calendar c;
        final String todaydate,currenttime;

        if(headin.getText().toString().isEmpty()){
            headin.setError("Headin can't be blank");
            return;
        }

        c= Calendar.getInstance();

        todaydate=c.get(Calendar.YEAR)+"/"+(c.get(Calendar.MONTH)+1)+"/"+c.get(Calendar.DAY_OF_MONTH);
        currenttime=c.get(Calendar.HOUR)+":"+c.get(Calendar.MINUTE);

        atitle.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(charSequence.length()<16){
                    headin.setTextSize(30);
                    headin.setText(""+charSequence);
                }
                else if(charSequence.length()<25){
                    headin.setTextSize(20);
                    headin.setText(""+charSequence);
                }
                else{
                    Toast.makeText(AddNote.this, "Tile size exceeded", Toast.LENGTH_SHORT).show();
                }


            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        Ssave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ///Message to save a note into a database
                Note note=new Note(atitle.getText().toString(),acontent.getText().toString(),todaydate,currenttime);
                Database db=new Database(AddNote.this);
                db.addNote(note);
                MainActivity mn=new MainActivity();
                mn.launch();
                AddNote.super.onBackPressed();
                //startActivity(new Intent(getApplicationContext(),MainActivity.class));
                //startActivity(new Intent(getApplicationContext(),Delete_update.class));
                Toast.makeText(AddNote.this, "Saved :)", Toast.LENGTH_SHORT).show();
            }
        });



    }


}
