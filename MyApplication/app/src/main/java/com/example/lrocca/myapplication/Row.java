package com.example.lrocca.myapplication;
import com.example.lrocca.myapplication.modelo.Jugador;

/**
 * Created by lrocca on 24/04/2017.
 */

    public class Row
    {
        private String title;

        private String subtitle;

        private boolean checked=false;
        private Jugador player;

        public String getTitle()
        {
            return title;
        }

        public void setTitle(String title)
        {
            this.title = title;
        }

        public String getSubtitle()
        {
            return subtitle;
        }

        public void setSubtitle(String subtitle)
        {
            this.subtitle = subtitle;
        }

        public boolean isChecked()
        {
            return checked;
        }

        public void setChecked(boolean checked)
        {
            this.checked = checked;
        }
        public void changeCheck()
        {
            this.checked = !this.isChecked();
        }
        public void setPlayer(Jugador player) {
            this.player = player;
            this.setTitle(player.getName());
          //  this.setSubtitle("   -   "+String.valueOf(player.getHability()));
            this.setSubtitle("");
        }

        public Jugador getPlayer() {
            return player;
        }

    }
