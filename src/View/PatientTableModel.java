package View;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import core.Patient;

class PatientTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 1L;
	public static final int OBJECT_COL = -1;
	private static final int STT_COL = 0;
	private static final int ID_COL = 1;
	private static final int LAST_NAME_COL = 2;
	private static final int FIRST_NAME_COL = 3;
	private static final int DATE_OF_BIRTH_COL = 4;
	private static final int GENDER_COL = 5;
	private static final int ADDRESS_COL = 6;
	private static final int EMAIL_COL = 7;
	private static final int PHONE_COL = 8;
	private static final int DOCTORNAME_COL = 9;
	
	private SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

	private String[] columnNames = { "STT", "PatientID", "LastName", "FirstName", "DateOfBirth", "Gender", "Address","Email", "Phone", "DoctorName" };
	private List<Patient> Patients;

	public PatientTableModel(List<Patient> thePatients) {
		Patients = thePatients;
	}

	@Override
	public int getRowCount() {
		return Patients.size();
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

		Patient tempPatient = Patients.get(row);

		switch (col) {
		case OBJECT_COL:
			return tempPatient;
		case STT_COL:
			return row + 1;
		case ID_COL:
			return tempPatient.getID();
		case LAST_NAME_COL:
			return tempPatient.getLastName();
		case FIRST_NAME_COL:
			return tempPatient.getFirstName();
		case GENDER_COL:
			return tempPatient.getGender();
		case EMAIL_COL:
			return tempPatient.getEmail();
		case DATE_OF_BIRTH_COL:
			Date tempDate = tempPatient.getDateOfBirth();
			String tempDateInString = formatter.format(tempDate);
			return tempDateInString;
		case ADDRESS_COL:
			return tempPatient.getAddress();
		case PHONE_COL:
			return tempPatient.getPhoneNum();
		case DOCTORNAME_COL:
			return tempPatient.getDoctorName();
		default:
			return tempPatient.getID();
		}
	}

	@Override
	public Class<? extends Object> getColumnClass(int c) {
		return getValueAt(0, c).getClass();
	}
}
