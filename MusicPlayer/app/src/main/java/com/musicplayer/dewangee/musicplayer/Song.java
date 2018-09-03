package com.musicplayer.dewangee.musicplayer;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class Song extends BaseAdapter {

    public ArrayList<Integer> Rawarray;
        public Context context;
        public ArrayList<String> Namearray;
        Checkii lay;
        public int see;




        public Song(Context context,int see,ArrayList<String> Namearray,ArrayList<Integer> Rawarray)
        {
            this.Namearray=Namearray;
            this.see=see;

            this.Rawarray=Rawarray;
            int a=20;
            if(a==0){
                a++;
            }
            this.context=context;
        }




        @Override
        public int getCount() {
            return Rawarray.size();
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }


    public class Checkii{
        TextView Song;
        Button play,stop;
    }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup)
        {
            lay=new Checkii();
            if(view!=null) {
                lay = (Checkii) view.getTag();
            }
           else
            {

                LayoutInflater layoutInflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

                view =layoutInflater.inflate(see,null);
                lay.play = (Button) view.findViewById(R.id.play);
                lay.stop = (Button) view.findViewById(R.id.stop);
                int a=20;
                if(a==0){
                    a++;
                }
                lay.Song = (TextView) view.findViewById(R.id.Song);


                view.setTag(lay);
            }


            final Intent in = new Intent(view.getContext(), Datafil.class);

            final String name_song = Namearray.get(i);
            lay.Song.setText(name_song);

            final Integer music_song = Rawarray.get(i);


            final Bundle bundle=new Bundle();
            bundle.putInt("SongMusic",music_song);

            lay.stop.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    context.stopService(in);

                }
            });

            lay.play.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    in.putExtras(bundle);
                    context.startService(in);


                }
            });



            return view;
        }


}
