package br.com.ceep.ui.activity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;

import br.com.ceep.R;
import br.com.ceep.model.Note;

public class CreateNoteActivity extends AppCompatActivity {
	public static final String KEY_NOTE = "note";
	public static final int RESULT_CODE = 2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.activity_create_note);
		this.hideActionBar();
		this.setColorStatusBar();
		this.onSubmitNote();
	}

	private void onSubmitNote() {
		ImageView imageView = findViewById(R.id.create_note_icon_save);
		imageView.setOnClickListener(view -> {
			Note newNote = this.createNewNote();
			this.returnNote(newNote);
			this.finish();
		});
	}

	private void returnNote(Note note) {
		Intent result = new Intent();
		result.putExtra(KEY_NOTE, note);
		this.setResult(RESULT_CODE, result);
	}

	private Note createNewNote() {
		TextView inputText = findViewById(R.id.create_note_input_title);
		TextView inputDescription = findViewById(R.id.create_note_input_description);
		return new Note(inputText.getText().toString(), inputDescription.getText().toString());
	}

	private void hideActionBar() {
		Objects.requireNonNull(this.getSupportActionBar()).hide();
	}

	private void setColorActionBar() {
		Objects.requireNonNull(this.getSupportActionBar())
			.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#FFFFFF")));
	}

	private void setColorStatusBar() {
		Window window = this.getWindow();
		window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
		window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
		window.setStatusBarColor(this.getResources().getColor(R.color.white));
	}
}