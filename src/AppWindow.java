import javax.swing.*;
import java.awt.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.xml.bind.annotation.XmlSchemaType.DEFAULT;

import java.awt.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
public class AppWindow extends JFrame
{
	JButton btnNew,btnRemove,btnSearch,btnClose;
	JLabel lblID,lblName,lblGender,lblStatus,lblEmail;
	JTextField txtName,txtID,txtEmail;
	JComboBox cbGender,cbStatus;
	String gender[] = {"","Male","Female"};
	String status[] ={"","Single","Married"};
	String[] headOfTable = {"ID","Name","Gender","Status","Email"};
	DefaultTableModel tbl;
	JTable jtbl;
	JScrollPane jscp;
	StudentDataBase stdbase;
	AppWindow() 
	{	
		super("Record GUI");
		this.setSize(900, 900);
		this.addWindowListener(new WindowListener()
		{
			public void windowActivated(WindowEvent arg0) {}
			public void windowClosed(WindowEvent arg0) {}
			public void windowClosing(WindowEvent arg0) {
				try {
					stdbase.bw.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				System.exit(0);	
			}
			public void windowDeactivated(WindowEvent arg0) {}
			public void windowDeiconified(WindowEvent arg0) {}
			public void windowIconified(WindowEvent arg0) {}
			public void windowOpened(WindowEvent arg0) {}
		});
		Container appFrame = this.getContentPane();
		stdbase = new StudentDataBase();
	appFrame.setLayout(null);
		btnNew = new JButton("Save & New");
		btnRemove = new JButton("Remove ");
		btnSearch = new JButton("Search");
		btnClose = new JButton("Close");
		lblID = new JLabel("ID: ");
		lblName = new JLabel("Name: ");
		lblGender = new JLabel("Gender: ");
		lblStatus = new JLabel("Status: ");
		lblEmail = new JLabel("Email: ");
		txtName = new JTextField(10);
		txtID = new JTextField(10);
		txtEmail = new JTextField(10);
		cbGender = new JComboBox(gender);
		cbStatus = new JComboBox(status);
		appFrame.add(btnNew);btnNew.setBounds(30, 30,200, 30);
		appFrame.add(btnRemove);btnRemove.setBounds(260, 30, 200, 30);
		appFrame.add(btnSearch);btnSearch.setBounds(490, 30,200,30);
		appFrame.add(btnClose);btnClose.setBounds(720, 30, 200,30);
		appFrame.add(lblID);lblID.setBounds(30, 90, 200, 30);
		appFrame.add(lblName);lblName.setBounds(30, 150, 200, 30);
		appFrame.add(lblGender);lblGender.setBounds(30, 210, 200, 30);
		appFrame.add(txtID);txtID.setBounds(260, 90, 200, 30);
		appFrame.add(txtName);txtName.setBounds(260, 150,200, 30);
		appFrame.add(cbGender);cbGender.setBounds(260, 210, 200,30);
		appFrame.add(lblStatus);lblStatus.setBounds(490, 90, 200,30);
		appFrame.add(lblEmail);lblEmail.setBounds(490, 150, 200, 30);
		appFrame.add(cbStatus);cbStatus.setBounds(720, 90,200, 30);
		appFrame.add(txtEmail);txtEmail.setBounds(720,150,200, 30);
	
		tbl = new DefaultTableModel(headOfTable,0);
		
		jtbl = new JTable(tbl);
		jscp = new JScrollPane(jtbl);
		appFrame.add(jscp);jscp.setLocation(50, 300);jscp.setSize(500,50);
		this.setVisible(true);
		try {
			while(stdbase.br.readLine()!=null)
			{
				String[] temp = stdbase.br.readLine().split(" ");
				this.addStudentinTable(temp);
			}
			stdbase.br.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		btnNew.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
			addNewStudent(txtID.getText(),txtName.getText(),cbGender.getSelectedItem().toString(),cbStatus.getSelectedItem().toString(),txtEmail.getText());
			}
		}
		);
		
		
	}
	public void addNewStudent(String Id,String name,String gender,String status,String Email)
	{
		String[] studentData = {Id,name,gender,status,Email};
		tbl.addRow(studentData);
		stdbase.addStudent(Id, name, gender, status, Email);
	}
	public void addStudentinTable(String[] temp)
	{
		String[] studentData = {temp[0],temp[1],temp[2],temp[3],temp[4]};
		tbl.addRow(studentData);
	}
	public void searchStudent(String Id)
	{
		for(int i=0;i<tbl.getRowCount();i++)
		{
			if(tbl.getValueAt(i,0).toString().equals(Id))
			{
				this.txtID.setText(Id);
				this.txtName.setText(tbl.getValueAt(i, 1).toString());
				this.cbGender.setSelectedItem(tbl.getValueAt(i, 2).toString());
				this.cbStatus.setSelectedItem(tbl.getValueAt(i, 3).toString());
			}
		}
	}

	
}
