package com.musicplayer.dewangee.musicplayer;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;



public class MusicList extends Fragment {

    ArrayList<String> Namearray = new ArrayList<String>();
    ArrayList singer=new ArrayList();
    ArrayList<Integer> Rawarray=new ArrayList<Integer>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View vi=inflater.inflate(R.layout.fragment_music_list, container, false);

        ListView songList = (ListView) vi.findViewById(R.id.list);


        Namearray.add("One more night");
        Namearray.add("Wake me up");
        Namearray.add("Pumped up kicks");
       // Namearray.add("songname");
        Rawarray.add(R.raw.one_more_night);
        Rawarray.add(R.raw.wake_me_up);
        Rawarray.add(R.raw.pumped_up_kicks);
       // Rawarray.add(R.raw.songname);




        Song music_song=new Song(this.getContext(), R.layout.song,Namearray,Rawarray);

        songList.setAdapter(music_song);
        return vi;
    }


}
