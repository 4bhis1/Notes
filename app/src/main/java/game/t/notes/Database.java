package game.t.notes;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class Database extends SQLiteOpenHelper {

    //BASIC DETAILS OF DATABASE;
    public static final int DATABASE_VERSION=1;
    public static final String DATABASE_NAME="NOTES";
    public static final String TABLE_NAME="NOTE";

    //BASIC DETAILS OF TABLE;

    public static final String KEY_ID="id";
    public static final String KEY_TITLE="title";
    public static final String KEY_CONTENT="content";
    public static final String KEY_DATE="date";
    public static final String KEY_TIME="time";

    public Database(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query="CREATE TABLE "+TABLE_NAME+" ("+KEY_ID+" INTEGER PRIMARY KEY, "+KEY_TITLE+" TEXT,"+KEY_CONTENT+" TEXT,"+KEY_DATE+" TEXT,"+KEY_TIME+" TEXT);";
        sqLiteDatabase.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void  addNote(Note note){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues c=new ContentValues();
        c.put(KEY_TITLE,note.getTitle());
        c.put(KEY_CONTENT,note.getContent());
        c.put(KEY_DATE,note.getDate());
        c.put(KEY_TIME,note.getTime());
        //c.put(KEY_TITLE,note.getID());
        db.insert(TABLE_NAME,null,c);

    }

    public List<Note> getNotes(){
        SQLiteDatabase db=this.getReadableDatabase();
        List<Note> allNotes=new ArrayList<>();
        String query="SELECT * FROM "+TABLE_NAME+" ORDER BY "+KEY_ID+" DESC";
        Cursor cursor=db.rawQuery(query,null);

        if(cursor.moveToFirst()){
            do{
                Note note=new Note();
                note.setID(cursor.getInt(0));
                note.setTitle(cursor.getString(1));
                note.setContent(cursor.getString(2));
                note.setDate(cursor.getString(3));
                note.setTime(cursor.getString(4));

                allNotes.add(note);

            }while (cursor.moveToNext());
        }

        return allNotes;
    };


    public  int update_content(Note note){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        //values.put(name,gs.getName());
        //values.put(KEY_TITLE,note.getTitle());
        values.put(KEY_CONTENT,note.getContent());
        values.put(KEY_DATE,note.getDate());
        values.put(KEY_TIME,note.getTime());
        return db.update(TABLE_NAME,values,"ID =? ",new String[]{String.valueOf(note.getID())});
    }

    public void delete_content(Note note){
        SQLiteDatabase db=this.getWritableDatabase();
        db.delete(TABLE_NAME,"ID=? ",new String[]{String.valueOf(note.getID())});
    }



}

