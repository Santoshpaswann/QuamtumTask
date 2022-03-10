package com.example.quamtumtask;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;

public class SignUpFragment extends Fragment  implements AdapterView.OnItemSelectedListener{
    String[] emp_type = { "+91", "01"};
    Spinner spin;
    ArrayAdapter aa;
    TextView tv_term, tv_reg;
    EditText edt_name, edt_email, edt_mobile, edt_passwprd;
    FirebaseAuth mAuth;
    public SignUpFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_sign_up, container, false);
        spin = view.findViewById(R.id.spr_type);
        edt_name = view.findViewById(R.id.edt_name);
        edt_email = view.findViewById(R.id.edt_email);
        edt_mobile = view.findViewById(R.id.edt_mobile);
        edt_passwprd = view.findViewById(R.id.edt_passwprd);
        tv_reg = view.findViewById(R.id.tv_reg);
        tv_term = view.findViewById(R.id.tv_term);
        mAuth = FirebaseAuth.getInstance();
        spin.setOnItemSelectedListener(this);
        aa = new ArrayAdapter(getContext(),android.R.layout.simple_spinner_item,emp_type);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        spin.setAdapter(aa);
        String htmlString="<u> term &amp; Condition</u>";
        tv_term.setText(Html.fromHtml(htmlString));
        tv_reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Register();
            }
        });
        return view;
    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }
    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    private void Register(){
        String email = edt_email.getText().toString();
        String password = edt_passwprd.getText().toString();
        if (email.isEmpty()){
            edt_email.setError("Email can't be empty");
        }
        if (password.isEmpty()){
            edt_passwprd.setError("Password can't be empty");
        }else {
            mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()){
                        Toast.makeText(getContext(), "Register Successfully", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getContext(), HomeActivity.class));
                    }else {
                        Toast.makeText(getContext(), "Register Failed"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

    }
}