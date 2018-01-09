package Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.vivek.android2.gullu.R;

import java.util.ArrayList;
import java.util.List;

import Model.ModelName;

/**
 * Created by android2 on 3/5/16.
 */
public class FragmentAdapter extends ArrayAdapter<ModelName> implements Filterable {
    Context context;
    int resource, textViewResourceId;
    List<ModelName> items, tempItems, suggestions;

    public FragmentAdapter(Context context, int resource, List<ModelName> items) {
        super(context, resource, /*textViewResourceId,*/ items);
        this.context = context;
        this.resource = resource;
        this.items = items;
        tempItems = new ArrayList<ModelName>(items); // this makes the difference.
        suggestions = new ArrayList<ModelName>();
        System.out.println("this.items" + this.items.size());
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Holder holder;
        View view = convertView;
        if (convertView == null) {
            // holder = new Holder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(resource, parent, false);

            ModelName modelName = items.get(position);
            if (modelName != null) {
                TextView product_name = (TextView) view.findViewById(R.id.product_name);
                TextView old_price = (TextView) view.findViewById(R.id.old_price);
                TextView new_price = (TextView) view.findViewById(R.id.new_price);
                ImageView image_fragment1 = (ImageView) view.findViewById(R.id.image_fragment1);
                if (product_name != null) {
                    product_name.setText(modelName.getProduct_name());
                    old_price.setText(modelName.getProduct_old_price());
                    new_price.setText(modelName.getProduct_new_price());
                    System.out.println("modelName.getImage_url().toString()"+modelName.getImage_url());
                    Picasso.with(context).load(modelName.getImage_url()).error(R.drawable.y_icon).into(image_fragment1);
                }

            }
        }
        return view;
    }

   /* public class Holder {
        TextView product_name, old_price, new_price;
        ImageView image_fragment1;
    }*/

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
