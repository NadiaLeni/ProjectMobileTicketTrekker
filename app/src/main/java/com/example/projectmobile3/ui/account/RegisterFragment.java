package com.example.projectmobile3.ui.account;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.projectmobile3.R;
import com.example.projectmobile3.User;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RegisterFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RegisterFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public RegisterFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RegisterFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RegisterFragment newInstance(String param1, String param2) {
        RegisterFragment fragment = new RegisterFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_register, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        TextView textViewGoToLogin = view.findViewById(R.id.tvLogin);
        textViewGoToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_fragmentRegister_to_fragmentAccount);
            }
        });

        EditText name = view.findViewById(R.id.etName);
        EditText email = view.findViewById(R.id.etEmail);
        EditText password = view.findViewById(R.id.etPassword);
        Button btnRegister = view.findViewById(R.id.btnRegister);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameValue = name.getText().toString();
                String emailValue = email.getText().toString();
                String passwordValue = password.getText().toString();

                if (isEditTextNotEmpty(name) && isEditTextNotEmpty(email) && isEditTextNotEmpty(password)) {

                    User newUser = new User(nameValue, emailValue, passwordValue);

                    saveUserData(newUser);
                    Toast.makeText(getContext(), "Berhasil Register", Toast.LENGTH_SHORT).show();
                    Navigation.findNavController(view).navigate(R.id.action_fragmentRegister_to_fragmentAccount);
                } else {
                    Toast.makeText(getContext(), "Harap isi semua field", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void saveUserData(User user){
        SharedPreferences sharedPreferences = getActivity().getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("user_name", user.getName());
        editor.putString("user_email", user.getEmail());
        editor.putString("user_password", user.getPassword());
        editor.putBoolean("is_logged_in", false);
        editor.putBoolean("is_buyTicket", false);
        editor.apply();
    }

    private boolean isEditTextNotEmpty(EditText editText) {
        return editText.getText().toString().trim().length() > 0;
    }

}