package Fragments;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;

import com.vivek.android2.gullu.R;

import java.util.ArrayList;

import Adapter.FragmentAdapter;
import Model.ModelName;

/**
 * Created by android2 on 30/4/16.
 */
public class Fragment2 extends Fragment {
    ListView listView;
    EditText edit;

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View rootView = layoutInflater.inflate(R.layout.fragment2, viewGroup, false);
        edit = (EditText) rootView.findViewById(R.id.edit);
        listView = (ListView) rootView.findViewById(R.id.list);
        ArrayList<ModelName> modelNames = new ArrayList<>();
        for (int i = 0; i <= 4; i++) {
            ModelName modelName = new ModelName();
            modelName.setProduct_name("Pizza" + "" + i + "");
            modelName.setProduct_old_price("150.00");
            modelName.setProduct_new_price("140.00");
            modelName.setImage_url("https://www.iconfinder.com/icons/18194/blue_user_png_head_man_personal_user_icon#size=128");
            modelNames.add(modelName);
        }

        System.out.println("modelNames.size" + modelNames.size());
        final FragmentAdapter selectListAdapter = new FragmentAdapter(getContext(), R.layout.list_item_fragment, modelNames);
        listView.setAdapter(selectListAdapter);
        listView.setDivider(new ColorDrawable(Color.parseColor("#ff87c3")));
        listView.setDividerHeight(2);
        edit.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                System.out.println("Text [" + s + "]");

                selectListAdapter.getFilter().filter(s.toString());
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {

            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        return rootView;
    }
}

