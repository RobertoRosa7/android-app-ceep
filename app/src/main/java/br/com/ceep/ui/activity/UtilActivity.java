package br.com.ceep.ui.activity;

import static br.com.ceep.ui.activity.StringActivity.KEY_NOTE;
import static br.com.ceep.ui.activity.StringActivity.NOTE_POSITION;
import static br.com.ceep.ui.activity.StringActivity.REQUEST_CODE_CREATE;
import static br.com.ceep.ui.activity.StringActivity.REQUEST_CODE_UPDATE;

import android.app.Activity;
import android.content.Intent;

import androidx.annotation.Nullable;

import java.util.Objects;

import br.com.ceep.model.Note;

public class UtilActivity {
	public static boolean hasIntentCodeCreate(int requestCode) {
		return requestCode == REQUEST_CODE_CREATE;
	}

	public static boolean hasIntentCodeToUpdate(int requestCode, int resultCode) {
		return requestCode == REQUEST_CODE_UPDATE && resultCode == Activity.RESULT_OK;
	}

	public static boolean hasNote(Intent data) {
		return Objects.requireNonNull(data).hasExtra(KEY_NOTE);
	}

	public static Note noteSerialized(@Nullable Intent data) {
		return (Note) Objects.requireNonNull(data).getSerializableExtra(KEY_NOTE);
	}

	public static boolean hasIntentData(Intent data) {
		return hasNote(data) && hasPosition(data);
	}

	public static boolean hasPosition(Intent data) {
		return Objects.requireNonNull(data).hasExtra(NOTE_POSITION);
	}

	public static boolean validResultCreate(int requestCode, @Nullable Intent data) {
		return hasIntentCodeCreate(requestCode) && hasNote(data);
	}

	public static boolean validResultUpdate(int requestCode, int resultCode, Intent data) {
		return hasIntentCodeToUpdate(requestCode, resultCode) && hasIntentData(data);
	}

	public static int noteGetPosition(Intent data) {
		return (int) Objects.requireNonNull(data).getSerializableExtra(NOTE_POSITION);
	}

	public static boolean isIntentResultOk(int resultCode) {
		return resultCode == Activity.RESULT_OK;
	}

	public static boolean isPositionValid(int position) {
		return position > -1;
	}

	public static boolean hasDataExtra(Intent intent) {
		return intent.hasExtra(KEY_NOTE) && intent.hasExtra(NOTE_POSITION);
	}

}
