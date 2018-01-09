package Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.vivek.android2.gullu.R;

import java.util.ArrayList;
import java.util.List;

import Model.ModelName;

/**
 * Created by android2 on 18/4/16.
 */
public class SelectListAdapter extends ArrayAdapter<ModelName> implements Filterable {
    Context context;
    int resource, textViewResourceId;
    List<ModelName> items, tempItems, suggestions;

    public SelectListAdapter(Context context, int resource, /*int textViewResourceId,*/ List<ModelName> items) {
        super(context, resource, /*textViewResourceId,*/ items);
        this.context = context;
        this.resource = resource;
        // this.textViewResourceId = textViewResourceId;
        this.items = items;
        tempItems = new ArrayList<ModelName>(items); // this makes the difference.
        suggestions = new ArrayList<ModelName>();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(resource, parent, false);
        }
        ModelName modelName = items.get(position);
        if (modelName != null) {
            TextView name = (TextView) view.findViewById(R.id.name);
            TextView address = (TextView) view.findViewById(R.id.address);
            if (name != null) {
                name.setText(modelName.getName());
                address.setText(modelName.getAddress());
            }

        }
        return view;
    }

    @Override
    public Filter getFilter() {
        return nameFilter;
    }

    /**
     * Custom Filter implementation for custom suggestions we provide.
     */
    Filter nameFilter = new Filter() {
        @Override
        public CharSequence convertResultToString(Object resultValue) {
            String str = ((ModelName) resultValue).getName();
            return str;
        }

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            if (constraint != null) {
                suggestions.clear();
                for (ModelName modelName : tempItems) {
                    if (modelName.getName().toLowerCase().contains(constraint.toString().toLowerCase())) {
                        suggestions.add(modelName);
                    }
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = suggestions;
                filterResults.count = suggestions.size();
                return filterResults;
            } else {
                return new FilterResults();
            }
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            List<ModelName> filterList = (ArrayList<ModelName>) results.values;
            if (results != null && results.count > 0) {
                clear();
                for (ModelName modelName : filterList) {
                    add(modelName);
                    notifyDataSetChanged();
                }
            }
        }
    };
}
