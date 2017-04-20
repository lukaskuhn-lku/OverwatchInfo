package io.rocketfox.overwatchinfo;


import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import io.rocketfox.overwatchinfo.HTTPReq.AsyncResponsePlayer;
import io.rocketfox.overwatchinfo.HTTPReq.DownloadImageTask;
import io.rocketfox.overwatchinfo.HTTPReq.PlayerReq;
import io.rocketfox.overwatchinfo.Player.Player;


public class PlayerStatsFragment extends Fragment implements AsyncResponsePlayer {



    public PlayerStatsFragment() {
    }

    public static PlayerStatsFragment newInstance() {
        PlayerStatsFragment fragment = new PlayerStatsFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_player_stats, container, false);
    }

    EditText etPlayerName;
    @Override
    public void onViewCreated(View v, Bundle savedInstance){
        super.onViewCreated(v, savedInstance);

        etPlayerName = (EditText) v.findViewById(R.id.et_playerName);
        Typeface overwatchfont = Typeface.createFromAsset(getContext().getAssets(), "fonts/big_noodle_titling.ttf");
        etPlayerName.setTypeface(overwatchfont);

        Button btnSearch = (Button) v.findViewById(R.id.btnSearchPlayer);
        btnSearch.setTypeface(overwatchfont);
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPlayerStats();
            }
        });
    }

    private void showPlayerStats() {
        new PlayerReq(etPlayerName.getText().toString().replace('#', '-'),this).execute();
    }

    @Override
    public void processFinish(Player player) {
        ImageView playerAvatar = (ImageView) getView().findViewById(R.id.imgAvatarPlayer);
        new DownloadImageTask(playerAvatar).execute(player.getRegion().getStats().getCompetitive().getOverall_stats().getAvatar());

        TextView txtPlayerRank = (TextView) getView().findViewById(R.id.txtPlayerRank);
        txtPlayerRank.setText(String.valueOf(player.getRegion().getStats().getCompetitive().getOverall_stats().getComprank()));
    }
}
