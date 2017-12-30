package com.projet.florianepeltier.mobileproject.Controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.projet.florianepeltier.mobileproject.R;

/**
 * Created by root on 27/12/17.
 */

public class ShowAllActivity extends AppCompatActivity implements ShowAllAdapter.PrenomLoader {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_all);
        setTitle(R.string.list);

        // Empêche l'overlap de fragment à la rotation d'un écran
        if (findViewById(R.id.listfragment) != null) {
            if (savedInstanceState != null) return;

            // Lancement de ShowAllFragment
            getFragmentManager()
                    .beginTransaction()
                    .add(R.id.listfragment, new ShowAllFragment())
                    .commit()
            ;
        }
    }

    // Selon le fait que le FrameLayout "prenomfragment" existe ou non (=selon que l'on soit sur tablette ou sur smartphone)
    // on lance directement ShowOneFragment ou on lance une nouvelle activité
    @Override
    public void load(long id) {
        if (findViewById(R.id.prenomfragment) != null){
            ShowOneFragment fragment = ShowOneFragment.create(id);
            getFragmentManager()
                    .beginTransaction()
                    .replace(R.id.prenomfragment, fragment)
                    .addToBackStack(null)
                    .commit()
            ;
        }
        else {
            Intent intent = new Intent(this, ShowOneActivity.class);
            intent.putExtra("id", id);
            startActivity(intent);
        }
    }

    // Si on appuie sur le bouton retour, on relance le menu de départ
    @Override
    public void onBackPressed() {
        /*if (getFragmentManager().getBackStackEntryCount() > 1) getFragmentManager().popBackStack();
        else super.onBackPressed();*/
        startActivity(new Intent(ShowAllActivity.this, MainActivity.class));
    }
}
