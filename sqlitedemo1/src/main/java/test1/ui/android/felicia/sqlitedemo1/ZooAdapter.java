package test1.ui.android.felicia.sqlitedemo1;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

/**
 * Created by feliciafay on 6/7/15.
 */
public class ZooAdapter extends CursorAdapter {
    public ZooAdapter(Context context, Cursor cursor) {
        super(context, cursor, 0);
    }

    // The newView method is used to inflate a new view and return it,
    // you don't bind any data to the view at this point.
    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.item, parent, false);
    }

    // The bindView method is used to bind all data to a given view
    // such as setting the text on a TextView.
    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        // Find fields to populate in inflated template
        TextView nameTextView = (TextView) view.findViewById(R.id.name);
        TextView descriptionTextView = (TextView)view.findViewById(R.id.description);
        // Extract properties from cursor
        String name =
                cursor.getString(cursor.getColumnIndexOrThrow(ZooDbHelper.NAME_COLUMN));
        String description =
                cursor.getString(cursor.getColumnIndexOrThrow(ZooDbHelper.DESCRIPTION_COLUMN));
        // Populate fields with extracted properties
        nameTextView.setText(name);
        descriptionTextView.setText(description);
    }
}