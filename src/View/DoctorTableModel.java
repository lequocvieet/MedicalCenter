package View;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import core.Doctor;

class DoctorTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 1L; //sử dụng thuộc tính serialVersionUID để định danh và kiểm tra sự tương thích với các object được tuần tự hoá.
	public static final int OBJECT_COL = -1;
	private static final int STT_COL = 0;
	private static final int ID_COL = 1;
	private static final int LAST_NAME_COL = 2;
	private static final int FIRST_NAME_COL = 3;
	private static final int DATE_OF_BIRTH_COL = 4;
	private static final int ADDRESS_COL = 5;
	private static final int EMAIL_COL = 6;
	private static final int PHONE_COL = 7;
	
	private SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");  //Định dạng ngày tháng
	
	private String[] columnNames = { "STT", "DoctorID", "LastName", "FirstName", "DateOfBirth", "Address", "Email","Phone" }; //Set tên cột
	private List<Doctor> Doctors;

	public DoctorTableModel(List<Doctor> theDoctors) {
		Doctors = theDoctors;
	}

	@Override
	public int getRowCount() {
		return Doctors.size();
	}

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	@Override
	public String getColumnName(int col) {
		return columnNames[col];
	}
	
	//thêm đối tượng vào chỉ định vị trí row và col trong đối tượng dữ liệu
	@Override
	public Object getValueAt(int row, int col) {     
		Doctor tempDoctor = Doctors.get(row);

		switch (col) {
		case OBJECT_COL:
			return tempDoctor;
		case STT_COL:
			return row + 1;
		case ID_COL:
			return tempDoctor.getID();
		case LAST_NAME_COL:
			return tempDoctor.getLastName();
		case FIRST_NAME_COL:
			return tempDoctor.getFirstName();
		case EMAIL_COL:
			return tempDoctor.getEmail();
		case DATE_OF_BIRTH_COL:
			Date tempDate = tempDoctor.getDateOfBirth();
			String tempDateInString = formatter.format(tempDate);
			return tempDateInString;
		case ADDRESS_COL:
			return tempDoctor.getAddress();
		case PHONE_COL:
			return tempDoctor.getPhoneNum();
		default:
			return tempDoctor.getID();
		}
	}

	@Override
	public Class<? extends Object> getColumnClass(int c) {
		return getValueAt(0, c).getClass();
	}

}
