package com.green.news;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;
import java.util.Random;

public class ListAdapter extends ArrayAdapter<NewsData>
{
    public ListAdapter(Context context,List<NewsData> list) {
        super(context,0,list);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        View listItemView=convertView;
        if(convertView==null)
        {
            listItemView= LayoutInflater.from(getContext()).inflate(R.layout.listview,parent,false);
        }
        NewsData currentNewsData=getItem(position);
        TextView number=(TextView)listItemView.findViewById(R.id.number);
        number.setText(""+(currentNewsData.getIndex()+1));
        TextView Title=(TextView)listItemView.findViewById(R.id.headline);
        Title.setText(currentNewsData.getTitle());
        GradientDrawable circle=(GradientDrawable)number.getBackground();
        int nColor=getColor();
        circle.setColor(nColor);
        return listItemView;
    }
    int getColor()
    {
        Random rand=new Random();
        int n= rand.nextInt(5)+1;
        int colorId;
        switch(n)
        {
            case 1:
                colorId=R.color.c1;
                break;
            case 2:colorId=R.color.c2;
                break;
            case 3:colorId=R.color.c3;
                break;
            case 4:colorId=R.color.c4;
                break;
            case 5:colorId=R.color.c5;
                break;
            default:colorId=R.color.c6;
        }
        return ContextCompat.getColor(getContext(),colorId);

    }
}
