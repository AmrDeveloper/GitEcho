package com.amrdeveloper.gitecho.view;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.amrdeveloper.gitecho.R;
import com.amrdeveloper.gitecho.utils.Session;
import com.amrdeveloper.gitecho.databinding.ActivityLoginBinding;
import com.amrdeveloper.gitecho.model.LoginContract;
import com.amrdeveloper.gitecho.presenter.LoginPresenter;

public class LoginActivity extends AppCompatActivity implements LoginContract.View {

    private LoginPresenter presenter;
    private ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_login);
        presenter = new LoginPresenter(this,this);

        binding.loginButton.setOnClickListener(view -> {
            String username = binding.usernameEdit.getText().toString().trim();
            presenter.onStartLogin(username);
        });
    }

    @Override
    public void onLoginSuccess(String username) {
        Toast.makeText(this, "Welcome", Toast.LENGTH_SHORT).show();

        Session session = new Session(this);
        session.login(username);

        Intent intent = new Intent(this,MainActivity.class);
        intent.putExtra("username",username);
        startActivity(intent);
    }

    @Override
    public void onLoginFailure() {
        binding.usernameInputLayout.setError(getString(R.string.invalid_username));
    }

    @Override
    public void showProgressBar() {
        binding.loadingIndicator.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        binding.loadingIndicator.setVisibility(View.INVISIBLE);
    }
}
