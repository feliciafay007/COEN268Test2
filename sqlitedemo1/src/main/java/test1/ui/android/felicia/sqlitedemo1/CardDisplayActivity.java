package test1.ui.android.felicia.sqlitedemo1;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;


public class CardDisplayActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_display);

        // TodoDatabaseHandler is a SQLiteOpenHelper class connecting to SQLite
        ZooDbHelper handler = new ZooDbHelper(this);

        // Get access to the underlying writeable database
        SQLiteDatabase db = handler.getWritableDatabase();

        // Query for items from the database and get a cursor back
        String[] resultColumns = {ZooDbHelper.ID_COLUMN, ZooDbHelper.NAME_COLUMN, ZooDbHelper.DESCRIPTION_COLUMN, ZooDbHelper.FILE_PATH_COLUMN};
        Cursor cursor = db.query(ZooDbHelper.DATABASE_TABLE, resultColumns, null, null, null, null, null);

        // Attach cursor adapter to the ListView
        // Setup cursor adapter using cursor from last step
        ZooAdapter adapter = new ZooAdapter(this, cursor);
        ListView listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_card_display, menu);
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
        } else if (id == R.id.card_view) {
            return true;
        } else if (id == R.id.CRUD_view) {
            Intent intent = new Intent(CardDisplayActivity.this, MainActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
