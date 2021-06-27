package View;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import core.Person;

public class AppointmentTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 1L;
	public static final int OBJECT_COL = -1;
	public static final int ID_COL = 0;
	public static final int FIRSTNAME_COL = 1;
	public static final int LASTNAME_COL = 2;
	public static final int EMAIL_COL = 3;

	private String[] columnNames = { "ID", "FirstName", "LastName", "Email" };
	private List<Person> Persons;

	public AppointmentTableModel(List<Person> thePersons) {
		Persons = thePersons;
	}

	@Override
	public int getRowCount() {
		return Persons.size();
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

		Person tempPerson = Persons.get(row);

		switch (col) {
		case OBJECT_COL:
			return tempPerson;
		case ID_COL:
			return tempPerson.getID();
		case FIRSTNAME_COL:
			return tempPerson.getFirstName();
		case LASTNAME_COL:
			return tempPerson.getLastName();
		case EMAIL_COL:
			return tempPerson.getEmail();
		default:
			return tempPerson;

		}
	}

	@Override
	public Class<? extends Object> getColumnClass(int c) {
		return getValueAt(0, c).getClass();
	}
}
