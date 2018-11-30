package com.example.vedovate.projetofilme;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;

import java.util.List;


public class CategoryAdapter extends ArrayAdapter <Category> {


    private class ViewHolder{
        public Button genreButton;
    }


    public CategoryAdapter(MainActivity context, List<Category> category) {
        super(context, -1, category);
    }

    @NonNull
    @Override
    public View getView(int position,@Nullable View convertView, @NonNull ViewGroup parent){

        Context context = getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        ViewHolder viewHolder = null;
        if(convertView == null){
            convertView = inflater.inflate(R.layout.activity_gen_list, parent, false);
            viewHolder = new ViewHolder();
            convertView.setTag(viewHolder);
            viewHolder.genreButton = convertView.findViewById(R.id.genreButton);
        }
        viewHolder = (ViewHolder) convertView.getTag();
        Category caraDaVez = getItem(position);

        viewHolder.genreButton.setText(caraDaVez.toString());

        //viewHolder.genreButton.setTag(context.getString(R.string.textButtonGenre, caraDaVez.genre));



        return convertView;
    }

}
