package br.com.ceep.ui.activity;

import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;

import br.com.ceep.R;

public class CreateNoteActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.activity_create_note);
		this.hideActionBar();
		this.changeColorStatusBar();
	}

	private void changeColorStatusBar() {
		Window window = this.getWindow();
		window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
		window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
		window.setStatusBarColor(this.getResources().getColor(R.color.white));
	}

	private void hideActionBar() {
		Objects.requireNonNull(this.getSupportActionBar()).hide();
	}


}