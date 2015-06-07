package test1.ui.android.felicia.sqlitedemo1;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class MainActivity extends Activity {
    private Button buttonShow;
    private Button buttonAdd ;
    private Button buttonDelete;
    SQLiteDatabase db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttonShow = (Button) findViewById(R.id.buttonShow);
        buttonAdd = (Button) findViewById(R.id.buttonAdd);
        buttonDelete = (Button) findViewById(R.id.buttonDelete);
        db = new ZooDbHelper(getApplicationContext()).getWritableDatabase();

        buttonShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String where = null;
                String whereArgs[] = null;
                String groupBy = null;
                String having = null;
                String order = null;
                String[] resultColumns = {ZooDbHelper.ID_COLUMN, ZooDbHelper.NAME_COLUMN,
                        ZooDbHelper.DESCRIPTION_COLUMN, ZooDbHelper.FILE_PATH_COLUMN};
                Cursor cursor = db.query(ZooDbHelper.DATABASE_TABLE, resultColumns, where,
                        whereArgs, groupBy, having, order);
                while (cursor.moveToNext()) {
                    int id = cursor.getInt(0);
                    String name = cursor.getString(1);
                    String description = cursor.getString(2);
                    String filepath = cursor.getString(3);
                    Log.d("ZOO", String.format("%s,%s,%s,%s", id, name, description,
                            filepath));
                }
            }
        });

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = new ZooDbHelper(getApplicationContext()).getWritableDatabase();
                ContentValues newValues = new ContentValues();
                newValues.put(ZooDbHelper.NAME_COLUMN, "alpaca");
                newValues.put(ZooDbHelper.DESCRIPTION_COLUMN, "An alpaca looks like a lama.");
                newValues.put(ZooDbHelper.FILE_PATH_COLUMN, "/storage/alpaca.png");
                db.insert(ZooDbHelper.DATABASE_TABLE, null, newValues);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
