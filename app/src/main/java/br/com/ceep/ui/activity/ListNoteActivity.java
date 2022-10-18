package br.com.ceep.ui.activity;

import static br.com.ceep.ui.activity.StringActivity.ERROR_CHANGE_NOTE;
import static br.com.ceep.ui.activity.StringActivity.KEY_NOTE;
import static br.com.ceep.ui.activity.StringActivity.NOTE_POSITION;
import static br.com.ceep.ui.activity.StringActivity.REQUEST_CODE_CREATE;
import static br.com.ceep.ui.activity.StringActivity.REQUEST_CODE_UPDATE;
import static br.com.ceep.ui.activity.UtilActivity.isIntentResultOk;
import static br.com.ceep.ui.activity.UtilActivity.isPositionValid;
import static br.com.ceep.ui.activity.UtilActivity.noteGetPosition;
import static br.com.ceep.ui.activity.UtilActivity.noteSerialized;
import static br.com.ceep.ui.activity.UtilActivity.validResultCreate;
import static br.com.ceep.ui.activity.UtilActivity.validResultUpdate;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import br.com.ceep.R;
import br.com.ceep.dto.NoteDto;
import br.com.ceep.model.Note;
import br.com.ceep.ui.recyclerview.ListNoteAdapter;
import br.com.ceep.ui.recyclerview.helper.callback.NoteItemTouchHelperCallBack;

public class ListNoteActivity extends MainActivity {
	private final NoteDto noteDto = new NoteDto();
	private ListNoteAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.activity_list_note);
		this.setRecyclerView(getAll());
		this.gotoCreateNote();
	}

	private List<Note> getAll() {
		for (int i = 0; i < 10; i++) {
			this.noteDto.insert(new Note("Título: " + (i + 1), "Descrição: " + (i + 1)));
		}
		return this.noteDto.all();
	}

	protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
		if (validResultCreate(requestCode, data)) {
			if (isIntentResultOk(resultCode)) {
				this.addNote(noteSerialized(data));
			} else if (resultCode == Activity.RESULT_CANCELED) {
				System.out.println("here");
			}
		}

		if (validResultUpdate(requestCode, resultCode, data)) {
			Note note = noteSerialized(data);
			int position = noteGetPosition(data);
			this.setUpdateNoteOrShowError(note, position);
		}
		super.onActivityResult(requestCode, resultCode, data);
	}

	private void setUpdateNoteOrShowError(Note note, int position) {
		if (isPositionValid(position)) {
			this.noteDto.update(position, note);
			this.adapter.update(position, note);
		} else {
			Toast.makeText(this, ERROR_CHANGE_NOTE, Toast.LENGTH_SHORT).show();
		}
	}

	private void addNote(Note note) {
		new NoteDto().insert(note);
		this.adapter.add(note);
	}

	private void gotoCreateNote() {
		TextView inputTitle = findViewById(R.id.lista_notas_insere_nota);
		inputTitle.setOnClickListener(this::openPageCreateNoteToCreate);
	}

	private void openPageCreateNoteToCreate(View view) {
		this.startActivityForResult(new Intent(this, CreateNoteActivity.class), REQUEST_CODE_CREATE);
	}

	private void setRecyclerView(List<Note> notes) {
		RecyclerView listNotes = findViewById(R.id.list_note_recyclerView);
		adapter = new ListNoteAdapter(this, notes);
		listNotes.setAdapter(adapter);
		adapter.setOnItemClickListener(this::openPageCreateNoteToUpdate);
		ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new NoteItemTouchHelperCallBack());
		itemTouchHelper.attachToRecyclerView(listNotes);
	}

	private void openPageCreateNoteToUpdate(Note note, int position) {
		Intent intent = new Intent(ListNoteActivity.this, CreateNoteActivity.class);
		intent.putExtra(KEY_NOTE, note);
		intent.putExtra(NOTE_POSITION, position);
		this.startActivityForResult(intent, REQUEST_CODE_UPDATE);
	}
}