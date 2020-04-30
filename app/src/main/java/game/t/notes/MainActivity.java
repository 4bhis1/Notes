package game.t.notes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    public void launch(){
        final RecyclerView recyclerView=findViewById(R.id.rvv);
        Database db=new Database(this);


        //Recyclerview code starts here
        List<Note> notes=db.getNotes();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        Adapter adapter=new Adapter(this,notes);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.getSupportActionBar().hide();

        final ImageView addu=findViewById(R.id.add);


        launch();


        //Recyclerview code ends here

        addu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(MainActivity.this, "Clicked", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(),AddNote.class));
            }
        });

    }



}
