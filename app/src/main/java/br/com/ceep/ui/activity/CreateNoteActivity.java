package br.com.ceep.ui.activity;

import static br.com.ceep.ui.activity.StringActivity.KEY_NOTE;
import static br.com.ceep.ui.activity.StringActivity.NOTE_POSITION;
import static br.com.ceep.ui.activity.StringActivity.POSITION_INVALID;
import static br.com.ceep.ui.activity.UtilActivity.hasDataExtra;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;

import br.com.ceep.R;
import br.com.ceep.model.Note;

public class CreateNoteActivity extends AppCompatActivity {
	private int position = POSITION_INVALID;
	private TextView inputText;
	private TextView inputDescription;
	private ImageView imageView;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.activity_create_note);
		this.initializeFields();
		this.hideActionBar();
		this.setColorStatusBar();
		this.onSubmitNote();
		this.setFillFields();
	}

	private void setFillFields() {
		Intent intent = this.getIntent();
		if (hasDataExtra(intent)) {
			Note note = (Note) intent.getSerializableExtra(KEY_NOTE);
			this.position = intent.getIntExtra(NOTE_POSITION, POSITION_INVALID);
			this.updateNoteFields(note);
		}
	}

	private void updateNoteFields(Note note) {
		this.inputText.setText(note.getTitle());
		this.inputDescription.setText(note.getDescription());
	}

	private void initializeFields() {
		this.inputText = this.findViewById(R.id.create_note_input_title);
		this.inputDescription = this.findViewById(R.id.create_note_input_description);
		this.imageView = this.findViewById(R.id.create_note_icon_save);
	}

	private void onSubmitNote() {
		this.imageView.setOnClickListener(this::afterOnSubmit);
	}

	private void afterOnSubmit(View view) {
		Note newNote = this.createNewNote();
		this.intentPutExtra(newNote);
		this.finish();
	}

	private void intentPutExtra(Note note) {
		Intent intent = new Intent();
		intent.putExtra(KEY_NOTE, note);
		intent.putExtra(NOTE_POSITION, this.position);
		this.setResult(Activity.RESULT_OK, intent);
	}

	private Note createNewNote() {
		return new Note(this.inputText.getText().toString(), this.inputDescription.getText().toString());
	}

	private void hideActionBar() {
		Objects.requireNonNull(this.getSupportActionBar()).hide();
	}

//	private void setColorActionBar() {
//		Objects.requireNonNull(this.getSupportActionBar())
//			.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#FFFFFF")));
//	}

	private void setColorStatusBar() {
		Window window = this.getWindow();
		window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
		window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
		window.setStatusBarColor(this.getResources().getColor(R.color.white));
	}
}