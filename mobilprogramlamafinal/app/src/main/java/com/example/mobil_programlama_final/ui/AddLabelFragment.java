package com.example.mobil_programlama_final.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobil_programlama_final.Model.LabelModel;
import com.example.mobil_programlama_final.R;
import com.example.mobil_programlama_final.adaptors.LabelAdaptor;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class AddLabelFragment extends Fragment {

    private static final String TAG = "LABEL";
    ArrayList<LabelModel> labelItems;
    LabelAdaptor labelItemAdaptor;
    RecyclerView labelRV;
    EditText edtLabel, edtDesc;
    Button btnAdd;

    FirebaseFirestore ff = FirebaseFirestore.getInstance();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_add_label, container, false);

        edtLabel = root.findViewById(R.id.edtAddLabel);
        edtDesc = root.findViewById(R.id.edtAddDescription);
        btnAdd = root.findViewById(R.id.button_add);

        labelRV = root.findViewById(R.id.label_rw);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        labelRV.setLayoutManager(llm);

        labelItems = new ArrayList<>();
        labelItemAdaptor = new LabelAdaptor(getActivity(), labelItems);
        labelRV.setAdapter(labelItemAdaptor);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addLabels();
            }


        });

        getLabelItems();

        return root;

    }
    private void addLabels() {
        String label = edtLabel.getText().toString();
        String desc = edtDesc.getText().toString();

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        CollectionReference ref = db.collection("Labels");
        LabelModel labelItem = new LabelModel(label, desc);
        ref.add(labelItem);
    }


    private void getLabelItems() {
        ff.collection("Labels").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isComplete()) {
                    for(QueryDocumentSnapshot eleman: task.getResult()){
                        LabelModel lb = eleman.toObject(LabelModel.class);
                        labelItems.add(lb);
                    }
                }else{
                    Log.d(TAG, task.getException().toString());
                }
            }
        });
    }
}