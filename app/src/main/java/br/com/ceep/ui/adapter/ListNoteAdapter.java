package br.com.ceep.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import br.com.ceep.R;
import br.com.ceep.model.Note;

public class ListNoteAdapter extends BaseAdapter {
	private final Context context;
	private final List<Note> notes;

	public ListNoteAdapter(Context context, List<Note> notes) {
		this.context = context;
		this.notes = notes;
	}

	@Override
	public int getCount() {
		return notes.size();
	}

	@Override
	public Object getItem(int i) {
		return notes.get(i);
	}

	@Override
	public long getItemId(int i) {
		return 0;
	}

	@Override
	public View getView(int i, View view, ViewGroup viewGroup) {
		View viewCreated = getInflate(viewGroup);
		Note note = notes.get(i);

		this.setTitle(viewCreated, note);
		this.setDescription(viewCreated, note);

		return viewCreated;
	}

	private void setDescription(View viewCreated, Note note) {
		TextView textDescription = viewCreated.findViewById(R.id.item_nota_descricao);
		textDescription.setText(note.getDescription());
	}

	private void setTitle(View viewCreated, Note note) {
		TextView textTitle = viewCreated.findViewById(R.id.item_nota_titulo);
		textTitle.setText(note.getTitle());
	}

	private View getInflate(ViewGroup viewGroup) {
		return LayoutInflater.from(this.context).inflate(R.layout.item_note, viewGroup, false);
	}
}
