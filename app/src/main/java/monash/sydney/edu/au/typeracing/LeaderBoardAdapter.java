package monash.sydney.edu.au.typeracing;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/***
 * @author Akash
 *
 *  To do item adapter
 */
public class LeaderBoardAdapter extends ArrayAdapter<LItem> {

    private Context mContext;
    private List<LItem> toDoItemsList = new ArrayList<>();

    public LeaderBoardAdapter(@NonNull Context context, ArrayList<LItem> list) {
        super(context, 0, list);
        mContext = context;
        toDoItemsList = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;
        if (listItem == null)
            listItem = LayoutInflater.from(mContext).inflate(R.layout.list_view_layout, parent, false);

        LItem currentToDoItem = toDoItemsList.get(position);

        if (currentToDoItem.getRank() == -1) {
            ((TextView) listItem.findViewById(R.id.rank)).setText("Rank");
            ((TextView) listItem.findViewById(R.id.name)).setText("Name");
            ((TextView) listItem.findViewById(R.id.level)).setText("Level");
            ((TextView) listItem.findViewById(R.id.points)).setText("Points");
        } else {
            ((TextView) listItem.findViewById(R.id.rank)).setText(currentToDoItem.getRank() + "");
            ((TextView) listItem.findViewById(R.id.name)).setText(currentToDoItem.getUserName());
            ((TextView) listItem.findViewById(R.id.level)).setText(currentToDoItem.getLevel() + "");
            ((TextView) listItem.findViewById(R.id.points)).setText(currentToDoItem.getPoints() + "");
        }

        return listItem;
    }
}