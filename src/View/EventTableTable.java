package View;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import core.Event;

class EventTableTable extends AbstractTableModel {

	private static final long serialVersionUID = 1L;
	public static final int OBJECT_COL = -1;
	private static final int ID_COL = 0;
	private static final int NAME_COL = 1;
	private static final int DATE_COL = 2;
	private static final int DESCRIPTION_COL = 3;

	private SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

	private String[] columnNames = { "STT", "Name", "Date", "Description" };
	private List<Event> Events;

	public EventTableTable(List<Event> theEvents) {
		Events = theEvents;
	}

	@Override
	public int getRowCount() {
		return Events.size();
	}

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	@Override
	public String getColumnName(int col) {
		return columnNames[col];
	}

	@Override
	public Object getValueAt(int row, int col) {

		Event tempEvent = Events.get(row);

		switch (col) {
		case OBJECT_COL:
			return tempEvent;
		case ID_COL:
			return row + 1;
		case NAME_COL:
			return tempEvent.getName();
		case DATE_COL:
			Date tempDate = tempEvent.getDate();
			String tempDateInString = formatter.format(tempDate);
			return tempDateInString;
		case DESCRIPTION_COL:
			return tempEvent.getDescription();
		default:
			return tempEvent;
		}
	}

	@Override
	public Class<? extends Object> getColumnClass(int c) {
		return getValueAt(0, c).getClass();
	}

}
