package com.example.meddata;


//import android.support.annotation.NonNull;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.DocumentSnapshot;

public class New_VisitAdapter extends FirestoreRecyclerAdapter<Visit, New_VisitAdapter.VisitHolder> {
    private OnItemClickListener listener;

    public New_VisitAdapter(@NonNull FirestoreRecyclerOptions<Visit> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull VisitHolder holder, int position, @NonNull Visit model) {
        holder.textViewDate.setText(model.getDate());
        holder.textViewHospital.setText(model.getHospital());
//        holder.textViewDoctor.setText(model.getDoctor());
//        holder.textViewTests.setText(model.getTests());
//        holder.textViewDiagnosis.setText(model.getDiagnosis());
//        holder.textViewTreatment.setText(model.getTreatment());
//        holder.textViewComments.setText(model.getComments());

    }

    @NonNull
    @Override
    public VisitHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.display_visit,
                parent, false);
        return new VisitHolder(v);
    }

    public void deleteItem(int position) {
        getSnapshots().getSnapshot(position).getReference().delete();
    }

    class VisitHolder extends RecyclerView.ViewHolder {
        TextView textViewDate;
        TextView textViewHospital;
//        TextView textViewDoctor;
//        TextView textViewTests;
//        TextView textViewDiagnosis;
//        TextView textViewTreatment;
//        TextView textViewComments;


        public VisitHolder(View itemView) {
            super(itemView);
            textViewDate = itemView.findViewById(R.id.date);
            textViewHospital = itemView.findViewById(R.id.hospital);
//            textViewDoctor = itemView.findViewById(R.id.doctor);
//            textViewTests = itemView.findViewById(R.id.tests);
//            textViewDiagnosis = itemView.findViewById(R.id.diagnosis);
//            textViewTreatment = itemView.findViewById(R.id.treatment);
//            textViewComments = itemView.findViewById(R.id.comments);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION && listener != null) {
                        listener.onItemClick(getSnapshots().getSnapshot(position), position);
                    }
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(DocumentSnapshot documentSnapshot, int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
}
