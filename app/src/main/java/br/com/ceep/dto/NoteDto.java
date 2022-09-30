package br.com.ceep.dto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import br.com.ceep.model.Note;

public class NoteDto {
	private final static ArrayList<Note> notes = new ArrayList<>();

	public void remove(int index) {
		NoteDto.notes.remove(index);
	}

	public List<Note> all() {
		return (List<Note>) NoteDto.notes.clone();
	}

	public void addAll(List<Note> notes) {
		notes.addAll(notes);
	}

	public void insert(Note... notes) {
		NoteDto.notes.addAll(Arrays.asList(notes));
	}

	public void update(int index, Note note) {
		NoteDto.notes.set(index, note);
	}

	public void change(int indexStart, int indexEnd) {
		Collections.swap(notes, indexStart, indexEnd);
	}

	public void clear() {
		NoteDto.notes.clear();
	}
}
