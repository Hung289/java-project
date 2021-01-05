/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bkap.utils;

import com.toedter.calendar.JDateChooser;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;

/**
 *
 * @author admi
 */
public class Helper {
    DatabaseHelper db = null;
    Statement st = null;
    Constant constant;
    ArrayList<JTextField> jTextList;
    ArrayList<JFormattedTextField> jFormatList;
    ArrayList<JPasswordField> jPassList;
    ArrayList<JComboBox> jComboList;
    ArrayList<JDateChooser> jDateList;
    public Helper() {
        db = new DatabaseHelper();
    }
    
    private ArrayList<Component> getAllComponentInPanel(JPanel panel) {
        ArrayList<Component> arr = new ArrayList<>();
        
        for (Component component : panel.getComponents()) {
            arr.add(component);
        }
        
        return arr;
    }
    
    private ArrayList<Component> getAllInputComponentInPanel(JPanel panel) {
        ArrayList<Component> arr = new ArrayList<>();
        
        for (Component component : panel.getComponents()) {
            if(component.isEnabled()) {
                String nameControl = component.getClass().getName();
                if (nameControl.endsWith("JTextField") || nameControl.endsWith("JPasswordField") || nameControl.endsWith("JComboBox")
                        || nameControl.endsWith("JFormattedTextField") || nameControl.endsWith("JDateChooser")
                        || nameControl.endsWith("JCheckBox") || nameControl.endsWith("JTextArea")) {
                    arr.add(component);
                }
            }
        }
        return arr;
    }
    
    public void validateFieldInPanel(JPanel panel) {
        ArrayList<Component> arr = getAllInputComponentInPanel(panel);
        
        for (Component component : arr) {
            if (component.getClass().getName().endsWith("JTextField")) {
                JTextField txt = (JTextField)component;
                
                txt.addKeyListener(new KeyAdapter() {
                    @Override
                    public void keyPressed(KeyEvent e) {
                        txt.setBorder(UIManager.getBorder("TextField.border"));
                    }
                });
            } else if (component.getClass().getName().endsWith("JFormattedTextField")) {
                JFormattedTextField txt = (JFormattedTextField)component;
                
                txt.addKeyListener(new KeyAdapter() {
                    @Override
                    public void keyPressed(KeyEvent e) {
                        txt.setBorder(UIManager.getBorder("FormattedTextField.border"));
                    }
                });
            } else if (component.getClass().getName().endsWith("JPasswordField")) {
                JPasswordField txt = (JPasswordField)component;
                
                txt.addKeyListener(new KeyAdapter() {
                    @Override
                    public void keyPressed(KeyEvent e) {
                        txt.setBorder(UIManager.getBorder("PasswordField.border"));
                    }
                });
            } else if (component.getClass().getName().endsWith("JComboBox")) {
                JComboBox cbo = (JComboBox)component;
                
                cbo.addItemListener(new ItemListener() {
                    @Override
                    public void itemStateChanged(ItemEvent e) {
                        cbo.setBorder(UIManager.getBorder("ComboBox.border"));
                    }
                });
            }
        }
    }
    
    public boolean checkFormValid(JPanel panel) {
        ArrayList<Component> arr = getAllInputComponentInPanel(panel);
        
        jTextList = new ArrayList<>();
        jFormatList = new ArrayList<>();
        jPassList = new ArrayList<>();
        jComboList = new ArrayList<>();
        jDateList = new ArrayList<>();
        
        for (Component component : arr) {
            String nameControl = component.getClass().getName();
            if (nameControl.endsWith("JTextField")) {
                jTextList.add((JTextField)component);
            } else if (nameControl.endsWith("JFormattedTextField")) {
                jFormatList.add((JFormattedTextField)component);
            } else if (nameControl.endsWith("JPasswordField")) {
                jPassList.add((JPasswordField)component);
            } else if (component.getClass().getName().endsWith("JComboBox")) {
                jComboList.add((JComboBox)component);
            } else if (component.getClass().getName().endsWith("JDateChooser")) {
                jDateList.add((JDateChooser)component);
            }
        }
        
        boolean check = false, checkJText, checkJFormattedText, 
                checkJPassword, checkJCombo, checkJDate;
        checkJText = checkJFormattedText = checkJPassword
                = checkJCombo = checkJDate = false;
        
        int count1 = 0;
        for (JTextField item : jTextList) {
            if (!item.getText().isEmpty()) {
                count1 ++;
            }
        }
        if (count1 == jTextList.size()) {
            checkJText = true;
        }
        
        int count2 = 0;
        for (JFormattedTextField item : jFormatList) {
            if (!item.getText().isEmpty()) {
                count2 ++;
            }
        }
        if (count2 == jFormatList.size()) {
            checkJFormattedText = true;
        }
        
        int count3 = 0;
        for (JPasswordField item : jPassList) {
            if (!item.getText().isEmpty()) {
                count3 ++;
            }
        }
        if (count3 == jPassList.size()) {
            checkJPassword = true;
        }
        
        int count4 = 0;
        for (JComboBox item : jComboList) {
            if (item.getSelectedIndex() != 0) {
                count4 ++;
            }
        }
        if (count4 == jComboList.size()) {
            checkJCombo = true;
        }
        
        int count5=0;
        for (JDateChooser item : jDateList) {
            if (item.getDate() != null) {
                count5 ++;
            }
        }
        if (count5 == jDateList.size()) {
            checkJDate = true;
        }
        
        
        if (jFormatList.isEmpty()) {
            if (checkJText == true && checkJPassword == true && checkJCombo == true && checkJDate == true) {
                check = true;
            } else {
                check = false;
            }
        } else if (jDateList.isEmpty()) {
            if (checkJText == true && checkJFormattedText == true && checkJPassword == true && checkJCombo == true) {
                check = true;
            } else {
                check = false;
            }
        } else if (jPassList.isEmpty()) {
            if (checkJText == true && checkJFormattedText == true && checkJCombo == true && checkJDate == true) {
                check = true;
            } else {
                check = false;
            }
        } else if (jPassList.isEmpty() && jComboList.isEmpty()) { // check for KhachHang frame
            if (checkJText == true && checkJFormattedText == true && checkJDate == true) {
                check = true;
            } else {
                check = false;
            }
        } else if (jFormatList.isEmpty() && jPassList.isEmpty() && jComboList.isEmpty() && jDateList.isEmpty()) {
            if (checkJText == true) {
                check = true;
            } else {
                check = false;
            }
        } else {
            if (checkJText == true && checkJFormattedText == true && checkJPassword == true && checkJCombo == true && checkJDate == true) {
                check = true;
            } else {
                check = false;
            }
        }
        
        return check;
    }
    
    public void messageNullValuesForForm(JPanel panel) {
        ArrayList<Component> arr = getAllInputComponentInPanel(panel);
        
        for (Component component : arr) {
            String nameControl = component.getClass().getName();
            if (nameControl.endsWith("JTextField")) {
                JTextField txt = (JTextField)component;
                if (txt.getText().isEmpty()) {
                    txt.setBorder(new LineBorder(Color.red));
                    txt.requestFocus();
                }
            } else if (nameControl.endsWith("JFormattedTextField")) {
                JFormattedTextField txt = (JFormattedTextField)component;
                if (txt.getText().isEmpty()) {
                    txt.setBorder(new LineBorder(Color.red));
                }
            } else if (nameControl.endsWith("JPasswordField")) {
                JPasswordField txt = (JPasswordField)component;
                if (txt.getText().isEmpty()) {
                    txt.setBorder(new LineBorder(Color.red));
                }
            } else if (component.getClass().getName().endsWith("JComboBox")) {
                JComboBox cbo = (JComboBox)component;
                if(cbo.getSelectedIndex() == 0) {
                    cbo.setBorder(new LineBorder(Color.red));
                }
            } else if (component.getClass().getName().endsWith("JDateChooser")) {
                JDateChooser dateChooer = (JDateChooser)component;
                jDateList.add(dateChooer);
                if (dateChooer.getDate() == null) {
                    dateChooer.setBorder(new LineBorder(Color.red));
                }
            }
        }
    }
    
    public Object getFieldByField(String table, String fieldSelect, String fieldWhere, int fieldWhereValue) throws SQLException{
        
        String id = "";
        String param[] = new String[]{};
        ResultSet rs = db.selectData("SELECT "+ fieldSelect +" FROM "+ table +" WHERE "+ fieldWhere +"="+ fieldWhereValue,param);

        while (rs.next()) {            
            id = rs.getString(fieldSelect);
        }
        return id;
    }
    
    public Object getFieldByFieldString(String table, String fieldSelect, String fieldWhere, String fieldWhereValue) throws SQLException{
        
        int id = 0;
        String param[] = new String[]{};
        ResultSet rs = db.selectData("SELECT "+ fieldSelect +" FROM "+ table +" WHERE "+ fieldWhere +"=N'"+ fieldWhereValue +"'",param);

        while (rs.next()) {            
            id = rs.getInt(fieldSelect);
        }
        return id;
    }
    
    public void loadDataIntoComboBox(JComboBox cbo, String sql, String field) {
        
        try {
            String param[] = new String[]{};
            ResultSet rs = db.selectData(sql, param);
            DefaultComboBoxModel model = new DefaultComboBoxModel();
            if(cbo.getName().equals("vaitro")) {
                model.addElement("--Vai Trò--");
            }else if(cbo.getName().equals("phongban")) {
                model.addElement("--Phòng Ban--");
            }else if(cbo.getName().equals("cateRoom")) {
                model.addElement("--Danh Mục Phòng--");
            }else if(cbo.getName().equals("loaiphongtk")) {
                model.addElement("--Loại Phòng--");
            }else if(cbo.getName().equals("cateRoomAllRoom")) {
                model.addElement("--Loại Phòng--");
            }else if(cbo.getName().equals("cateShowAllRoom")) {
                model.addElement("--Loại Phòng--");
            }
            while (rs.next()) {                
                model.addElement(rs.getString(field));
            }
            cbo.setModel(model);
        } catch (SQLException ex) {
            Logger.getLogger(Helper.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public ResultSet loadDataIntoList(String sql) throws SQLException {
        
        String param[] = new String[]{};
        ResultSet rs = db.selectDataCall(sql, param);
        return rs;
        
    }
//    
    
    public int checkUser(String username, String pass) throws SQLException {
        boolean check = false;
        String param[] = new String[]{
            username, pass
        };   
        int Id_role_group = 0;
        ResultSet rs = db.selectData(Constant.SQL_ROLE_BY_USER_PASS, param);
        while (rs.next()) {            
            Id_role_group = rs.getInt("Id_role_group");
        }
        return Id_role_group;
    }
}
