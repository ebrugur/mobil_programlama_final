package com.example.mobil_programlama_final.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobil_programlama_final.Model.GalleryModel;
import com.example.mobil_programlama_final.R;
import com.example.mobil_programlama_final.adaptors.GalleryModelAdaptor;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class GalleryFragment extends Fragment {

    private static final String TAG = "CARD";

    ArrayList<GalleryModel> cardItems;
    GalleryModelAdaptor cardItemAdaptor;
    RecyclerView cardRecyclerView;
    FirebaseFirestore ff = FirebaseFirestore.getInstance();


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_gallery, container, false);

        cardRecyclerView = root.findViewById(R.id.card_rw);
        LinearLayoutManager  llm = new LinearLayoutManager(getActivity());
        cardRecyclerView.setLayoutManager((llm));

        cardItems = new ArrayList<>();
        cardItemAdaptor = new GalleryModelAdaptor(getActivity(), cardItems);
        cardRecyclerView.setAdapter((cardItemAdaptor));
        
        getCardItems();

        return root;

    }

    private void getCardItems() {
        ff.collection("Gallery").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isComplete()){
                    Log.d(TAG, "Veri geldi");
                    for (QueryDocumentSnapshot eleman: task.getResult()){
                        GalleryModel cm = eleman.toObject(GalleryModel.class);
                        cardItems.add(cm);
                    }
                    cardItemAdaptor.notifyDataSetChanged();
                }else{
                    Log.d(TAG, task.getException().toString());
                }
            }
        });
    }
}