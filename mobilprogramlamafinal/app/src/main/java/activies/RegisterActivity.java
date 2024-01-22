package activies;

import  androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mobil_programlama_final.Model.userModel;
import com.example.mobil_programlama_final.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;


public class RegisterActivity extends AppCompatActivity {

   EditText firstNameEditText, lastNameEditText, emailEditText, passwordEditText;
   Button registerSignUpButton;
   Button registerLogInButton;
   FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        firstNameEditText = findViewById(R.id.register_editTextFirstName);
        lastNameEditText = findViewById(R.id.register_editTextLastName);
        emailEditText = findViewById(R.id.register_editTextEmail);
        passwordEditText = findViewById(R.id.register_editTextPassword);

        registerSignUpButton = findViewById(R.id.register_buttonSignUp);
        registerLogInButton = findViewById(R.id.register_buttonLogin);

        auth = FirebaseAuth.getInstance();

        registerLogInButton.setOnClickListener(v -> {
            startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
            finish();
        });

        registerSignUpButton.setOnClickListener(v -> {
            String email = emailEditText.getText().toString();
            String password = passwordEditText.getText().toString();
            String firstName = firstNameEditText.getText().toString();
            String lastName = lastNameEditText.getText().toString();

            if (email.isEmpty() || password.isEmpty() || firstName.isEmpty() || lastName.isEmpty()) {
                Toast.makeText(RegisterActivity.this, "Lütfen tüm alanları doldurun", Toast.LENGTH_SHORT).show();
                return;
            }
            if (password.length() < 6) {
                Toast.makeText(RegisterActivity.this, "Şifreniz en az 6 karakter olmalıdır", Toast.LENGTH_SHORT).show();
                return;
            }

            auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    String uid = task.getResult().getUser().getUid();
                    Toast.makeText(RegisterActivity.this, "Kaydınız başarıyla gerçekleşmiştir", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(RegisterActivity.this, LoginActivity.class));

                    FirebaseFirestore db = FirebaseFirestore.getInstance();
                    CollectionReference ref = db.collection("UserModel");
                    userModel user = new userModel(firstName, lastName, email, uid);
                    ref.add(user);
                } else {
                    Toast.makeText(RegisterActivity.this, "Kayıt başarısız", Toast.LENGTH_SHORT).show();
                    finish();
                }
            });
        }); }}
