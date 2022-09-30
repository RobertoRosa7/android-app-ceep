package br.com.ceep.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.Objects;

import br.com.ceep.R;
import br.com.ceep.dto.NoteDto;
import br.com.ceep.model.Note;
import br.com.ceep.ui.recyclerview.ListNoteAdapter;

public class ListNoteActivity extends MainActivity {
	public static final int CODE_RESULT = 2;
	public static final String KEY_NOTE = "note";
	private final NoteDto noteDto = new NoteDto();
	private ListNoteAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.activity_list_note);
		this.setRecyclerView(this.noteDto.all());
		this.gotoCreateNote();
	}

	protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
		if (this.validActivityResult(resultCode, data)) {
			this.addNote(this.noteSerialized(data));
		}
		super.onActivityResult(requestCode, resultCode, data);
	}

	private Note noteSerialized(@Nullable Intent data) {
		return (Note) Objects.requireNonNull(data).getSerializableExtra(KEY_NOTE);
	}

	private void addNote(Note note) {
		new NoteDto().insert(note);
		this.adapter.add(note);
	}

	private boolean validActivityResult(int resultCode, @Nullable Intent data) {
		return resultCode == CODE_RESULT && Objects.requireNonNull(data).hasExtra(KEY_NOTE);
	}

	private void gotoCreateNote() {
		TextView inputTitle = findViewById(R.id.lista_notas_insere_nota);
		inputTitle.setOnClickListener(view -> this.startActivityForResult(new Intent(this, CreateNoteActivity.class), 1));
	}

	private void setRecyclerView(List<Note> notes) {
		RecyclerView listNotes = findViewById(R.id.list_note_recyclerView);
		adapter = new ListNoteAdapter(this, notes);
		listNotes.setAdapter(adapter);
	}
}