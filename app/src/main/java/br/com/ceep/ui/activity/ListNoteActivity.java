package br.com.ceep.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import br.com.ceep.R;
import br.com.ceep.dto.NoteDto;
import br.com.ceep.model.Note;
import br.com.ceep.ui.recyclerview.ListNoteAdapter;

public class ListNoteActivity extends MainActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.activity_list_note);
		this.setRecyclerView(this.getNoteDto());

		this.gotoCreateNote();
	}

	private void gotoCreateNote() {
		TextView inputTitle = findViewById(R.id.lista_notas_insere_nota);
		inputTitle.setOnClickListener(view -> this.startActivity(new Intent(this, CreateNoteActivity.class)));
	}

	@NonNull
	private List<Note> getNoteDto() {
		NoteDto noteDto = new NoteDto();
		noteDto.insert(
			new Note("Título ", "primeira descrição "),
			new Note("Título ", "primeira descrição é bem maior que a primeira nota ")
		);

		return noteDto.all();
	}

	private void setRecyclerView(List<Note> notes) {
		RecyclerView listNotes = findViewById(R.id.list_note_recyclerView);
		listNotes.setAdapter(new ListNoteAdapter(this, notes));
	}

//	private void setLayoutManager(RecyclerView listNotes) {
//		listNotes.setLayoutManager(new LinearLayoutManager(ListNoteActivity.this));
//	}
}