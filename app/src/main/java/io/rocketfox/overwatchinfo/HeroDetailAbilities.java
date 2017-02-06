package io.rocketfox.overwatchinfo;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import io.paperdb.Paper;
import io.rocketfox.overwatchinfo.Objects.Hero;
import io.rocketfox.overwatchinfo.Objects.HeroExtra.Ability;
import io.rocketfox.overwatchinfo.adapters.HeroAbilityAdapter;

public class HeroDetailAbilities extends Fragment {

    private int heroID;
    private Hero hero;

    public HeroDetailAbilities() {
        // Required empty public constructor
    }

    public static HeroDetailAbilities newInstance(int heroID) {
        HeroDetailAbilities fragment = new HeroDetailAbilities();
        Bundle args = new Bundle();
        args.putInt("heroID", heroID);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments() != null){
            Bundle args = getArguments();
            heroID = args.getInt("heroID");
        }
    }

     @Override
    public void onViewCreated(View v, Bundle savedInstance){
        super.onViewCreated(v, savedInstance);

         hero = Paper.book().read("hero_" + heroID);

         if(hero != null) {
             Typeface overwatchfont = Typeface.createFromAsset(getContext().getAssets(), "fonts/big_noodle_titling.ttf");

             ListView abilityList = (ListView) getView().findViewById(R.id.ability_list);
             Ability[] abilities = hero.abilities.toArray(new Ability[hero.abilities.size()]);
             abilityList.setAdapter(new HeroAbilityAdapter(getContext(), abilities));

             TextView abilitiesHeader = (TextView) getView().findViewById(R.id.txtAbilitiesHeader);
             abilitiesHeader.setTypeface(overwatchfont);
             abilitiesHeader.setText("Abilities: " + hero.name);

             getActivity().setTitle("Abilities");
         }else{
             Snackbar.make(getView(), "Missing connection", Snackbar.LENGTH_LONG);
         }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_hero_detail_abilities, container, false);
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();
    }


}
