package com.example.mobil_programlama_final.ui;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.mobil_programlama_final.R;


public class AboutFragment extends Fragment {

    Button btnGithub, btnLinkedin, btnYoutube;


    @SuppressLint("MissingInflatedId")
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_nav_about, container,false);

        btnGithub = root.findViewById(R.id.btnGithub);
        btnLinkedin = root.findViewById(R.id.btnLinkedin);
        btnYoutube = root.findViewById(R.id.btnYoutube);

        btnGithub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentYtb = new Intent();
                intentYtb.setAction(Intent.ACTION_VIEW);
                intentYtb.addCategory(Intent.CATEGORY_BROWSABLE);
                intentYtb.setData(Uri.parse("https://github.com/ebrugur"));
                startActivity(intentYtb);

            }
        });

        btnLinkedin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentYtb = new Intent();
                intentYtb.setAction(Intent.ACTION_VIEW);
                intentYtb.addCategory(Intent.CATEGORY_BROWSABLE);
                intentYtb.setData(Uri.parse("https://www.linkedin.com/in/ebruugur/"));
                startActivity(intentYtb);
            }
        });

        btnYoutube.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentYtb = new Intent();
                intentYtb.setAction(Intent.ACTION_VIEW);
                intentYtb.addCategory(Intent.CATEGORY_BROWSABLE);
                intentYtb.setData(Uri.parse("https://www.youtube.com/channel/UCbidDpA_wwtjFhqEsS4b-Dg"));
                startActivity(intentYtb);
            }
        });

        return root;
    }



}