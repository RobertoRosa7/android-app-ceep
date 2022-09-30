package br.com.ceep.ui.recyclerview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import br.com.ceep.R;
import br.com.ceep.model.Note;

public class ListNoteAdapter extends RecyclerView.Adapter<ListNoteAdapter.NoteViewHolder> {
	private final List<Note> notes;
	private final Context context;

	public ListNoteAdapter(Context context, List<Note> notes) {
		this.notes = notes;
		this.context = context;
	}

	@NonNull
	@Override
	public ListNoteAdapter.NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		View viewCreated = LayoutInflater.from(this.context).inflate(R.layout.item_note, parent, false);
		return new NoteViewHolder(viewCreated);
	}

	@Override
	public void onBindViewHolder(@NonNull ListNoteAdapter.NoteViewHolder holder, int position) {
		this.executeBindView(holder, this.notes.get(position));
	}

	private void executeBindView(ListNoteAdapter.NoteViewHolder holder, Note note) {
		holder.execute(note);
	}

	@Override
	public int getItemCount() {
		return this.notes.size();
	}

	public void add(Note note) {
		this.notes.add(note);
	}

	static class NoteViewHolder extends RecyclerView.ViewHolder {
		private final TextView title;
		private final TextView description;

		public NoteViewHolder(@NonNull View itemView) {
			super(itemView);
			this.title = itemView.findViewById(R.id.item_nota_titulo);
			this.description = itemView.findViewById(R.id.item_nota_descricao);
		}

		public void execute(Note note) {
			this.setTitle(note);
			this.setDescription(note);
		}

		private void setTitle(Note note) {
			this.title.setText(note.getTitle());
		}

		private void setDescription(Note note) {
			this.description.setText(note.getDescription());
		}
	}
}
