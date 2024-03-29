package com.amrdeveloper.gitecho.view;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.amrdeveloper.gitecho.model.events.LoginFailureEvent;
import com.amrdeveloper.gitecho.model.events.LoginSuccessEvent;
import com.amrdeveloper.gitecho.utils.Consts;
import com.amrdeveloper.gitecho.R;
import com.amrdeveloper.gitecho.utils.Session;
import com.amrdeveloper.gitecho.databinding.ActivityLoginBinding;
import com.amrdeveloper.gitecho.model.contract.LoginContract;
import com.amrdeveloper.gitecho.presenter.LoginPresenter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class LoginActivity extends AppCompatActivity implements LoginContract.View {

    private LoginPresenter presenter;
    private ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this,R.layout.activity_login);
        presenter = new LoginPresenter(this,this);

        isUserLogged();

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

        goToMainActivity(username);
    }

    @Override
    public void onLoginFailure() {
        binding.usernameInputLayout.setError(getString(R.string.invalid_username));
        binding.usernameInputLayout.requestFocus();
    }

    @Override
    public void showProgressBar() {
        binding.loadingIndicator.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        binding.loadingIndicator.setVisibility(View.INVISIBLE);
    }

    private void isUserLogged(){
        Session session = new Session(this);
        boolean isLogged = session.isLogged();
        if(isLogged){
            String username = session.getUsername();
            goToMainActivity(username);
        }
    }

    private void goToMainActivity(String username){
        Intent intent = new Intent(this,MainActivity.class);
        intent.putExtra(Consts.USERNAME,username);
        startActivity(intent);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onLoginSuccessEvent(LoginSuccessEvent successEvent){
        hideProgressBar();
        onLoginSuccess(successEvent.getUsername());
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void OnLoginFeilureEvent(LoginFailureEvent loginFailure){
        hideProgressBar();
        onLoginFailure();
    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        EventBus.getDefault().unregister(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }
}
