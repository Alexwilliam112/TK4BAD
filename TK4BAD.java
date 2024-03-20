import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

//MAINCLASS
public class TK4BAD extends JFrame implements ActionListener {

    //LOGICAL VARIABLES
    private static int id_start = 271100001;
    private String value_id = null;
    private String value_name = null;
    private String value_nim = null;
    private String value_tugas = null;
    private String value_quiz = null;
    private String value_uts = null;
    private String value_uas = null;
    private String value_keterangan = null;
    private String value_grade = null;
    private double int_average = 0.0;
    private double int_tugas = 0.0;
    private double int_quiz = 0.0;
    private double int_uts = 0.0;
    private double int_uas = 0.0;

    //scope declare
    private JPanel panel_title, panel_buttons;
    private JScrollPane panel_table;
    private JLabel label_title, label_id, label_name, label_nim, label_tugas, label_quiz, label_uts, label_uas, label_average, label_grade, label_keterangan, label_view;
    private JTextField txt_name, txt_nim, txt_tugas, txt_quiz, txt_uts, txt_uas, txt_average, txt_grade, txt_keterangan, txt_id;
    private JButton button_create, button_update, button_delete, button_view, button_exit, button_ok, button_save, button_cancel;
    private JTable table;
    private DefaultTableModel model;

    //text content
    final String title = "title";
    final String title_view = "View Only";
    final String id_columnLabel = "record_id";
    final String name = "name";
    final String nim = "nim";
    final String tugas = "tugas";
    final String quiz =  "quiz";
    final String uts = "uts";
    final String uas = "uas";
    final String average = "average";
    final String grade = "grade";
    final String keterangan = "keterangan";
    //button naming
    final String create = "create";
    final String update = "update";
    final String delete = "delete";
    final String view = "view";
    final String exit = "exit";
    final String ok = "ok";
    final String save = "save";
    final String cancel = "cancel";

    //MAIN FRAME
    public TK4BAD() {
        //frame setup
        setTitle(title);
        setSize(800,600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        //TABLE component create
        model = new DefaultTableModel();
        table = new JTable(model);
        table.setDefaultEditor(Object.class, null);
        TableColumnModel columnModel = table.getColumnModel();
        model.addColumn(id_columnLabel);
        columnModel.getColumn(0).setPreferredWidth(5);
        model.addColumn(name);
        columnModel.getColumn(1).setPreferredWidth(5);
        model.addColumn(nim);
        columnModel.getColumn(2).setPreferredWidth(5);
        model.addColumn(average);
        columnModel.getColumn(3).setPreferredWidth(5);
        model.addColumn(grade);
        columnModel.getColumn(4).setPreferredWidth(5);
        model.addColumn(keterangan);
        columnModel.getColumn(5).setPreferredWidth(200);
        table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                button_view.setEnabled(true);
            }
        });

        //LABEL component create
        label_id = new JLabel(id_columnLabel);
        label_title = new JLabel(title);
        label_name = new JLabel(name);
        label_nim = new JLabel(nim);
        label_tugas = new JLabel(tugas);
        label_quiz = new JLabel(quiz);
        label_uts = new JLabel(uts);
        label_uas = new JLabel(uas);
        label_average = new JLabel(average);
        label_grade = new JLabel(grade);
        label_keterangan = new JLabel(keterangan);
        label_view = new JLabel(title_view);

        //TEXT FIELD component create
        txt_name = new JTextField(20);
        txt_nim = new JTextField(20);
        txt_tugas = new JTextField(20);
        txt_quiz = new JTextField(20);
        txt_uts = new JTextField(20);
        txt_uas = new JTextField(20);
        txt_average = new JTextField(20);
        txt_average.setEditable(false);
        txt_grade = new JTextField(20);
        txt_grade.setEditable(false);
        txt_keterangan = new JTextField(20);
        txt_keterangan.setEditable(false);
        txt_id = new JTextField(20);
        txt_id.setEditable(false);

        //BUTTONS component create
        button_view = new JButton(view);
        button_view.addActionListener(this);
        button_create = new JButton(create);
        button_create.addActionListener(this);
        button_update = new JButton(update);
        button_update.addActionListener(this);
        button_delete = new JButton(delete);
        button_delete.addActionListener(this);
        button_exit = new JButton(exit);
        button_exit.addActionListener(this);
        button_save = new JButton(save);
        button_save.addActionListener(this);
        button_cancel = new JButton(cancel);
        button_cancel.addActionListener(this);

        //PANEL create
        panel_title = new JPanel(new FlowLayout());
        panel_table = new JScrollPane(table);
        panel_buttons = new JPanel(new FlowLayout());

        //components add
        panel_title.add(label_title);
        panel_buttons.add(button_view);
        panel_buttons.add(button_create);
        panel_buttons.add(button_update);
        panel_buttons.add(button_delete);
        panel_buttons.add(button_exit);

        //borderlayout setup
        add(panel_title, BorderLayout.NORTH);
        add(panel_table, BorderLayout.CENTER);
        add(panel_buttons, BorderLayout.SOUTH);
        setVisible(true);
        
    }


    //VIEW FRAME
    class view_overlay extends JFrame implements ActionListener{
        public view_overlay() {
            setTitle("View Document");
            setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            setLayout(new BorderLayout());
            //PANEL GROUPING, SPLIT HORIZONTAL
            JPanel left_panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            left_panel.setPreferredSize(new Dimension(220,300));
            left_panel.setBorder(new EmptyBorder(0, 10, 10, 10));
            JPanel right_panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            right_panel.setPreferredSize(new Dimension(220,300));
            right_panel.setBorder(new EmptyBorder(0, 10, 10, 10));
            JPanel empty_right = new JPanel();
            empty_right.setPreferredSize(new Dimension(20, 20));
            JPanel panel_group = new JPanel(new FlowLayout(FlowLayout.CENTER));
            panel_group.setLayout(new BorderLayout());
            panel_group.setBorder(new EmptyBorder(10, 10, 10, 10));
            JPanel panel_button = new JPanel();
            panel_button.setBorder(new EmptyBorder(10, 0, 15, 0));
            panel_button.setBackground(Color.WHITE);
            panel_button.setBorder(new LineBorder(Color.LIGHT_GRAY));
            JPanel panel_frametitle = new JPanel();
            panel_frametitle.setBorder(new EmptyBorder(0, 0, 25, 0));
            panel_frametitle.setBackground(Color.WHITE);
            panel_frametitle.setBorder(new LineBorder(Color.LIGHT_GRAY));
            button_ok = new JButton(ok);
            button_ok.addActionListener(this);
            //component layouts
            left_panel.add(label_name);
            left_panel.add(txt_name);
            left_panel.add(label_nim);
            left_panel.add(txt_nim);
            left_panel.add(label_tugas);
            left_panel.add(txt_tugas);
            left_panel.add(label_quiz);
            left_panel.add(txt_quiz);
            left_panel.add(label_uts);
            left_panel.add(txt_uts);
            left_panel.add(label_uas);
            left_panel.add(txt_uas);
            right_panel.add(label_id);
            right_panel.add(txt_id);
            right_panel.add(label_average);
            right_panel.add(txt_average);
            right_panel.add(label_grade);
            right_panel.add(txt_grade);
            right_panel.add(label_keterangan);
            right_panel.add(txt_keterangan);
            panel_button.add(button_ok);
            panel_frametitle.add(label_view);
            //PANEL layouts
            add(panel_frametitle, BorderLayout.NORTH);
            panel_group.add(left_panel, BorderLayout.WEST);
            panel_group.add(right_panel, BorderLayout.CENTER);
            panel_group.add(empty_right, BorderLayout.EAST);
            add(panel_button, BorderLayout.SOUTH);
            add(panel_group, BorderLayout.CENTER);
            setVisible(true);
            pack();
            setLocationRelativeTo(null);
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getActionCommand().equals(ok)) {
                dispose();
            }
        }
    }

    //DATA OBJECT FOR MODIF OVERLAY
    public Object[] input_field(JTextField txt_id, JTextField txt_name, JTextField txt_nim, JTextField txt_tugas, JTextField txt_quiz, JTextField txt_uts, JTextField txt_uas) {
        return new Object[]{
            label_id, txt_id,
            name, txt_name,
            nim, txt_nim,
            tugas, txt_tugas,
            quiz, txt_quiz,
            uts, txt_uts,
            uas, txt_uas
        };
    }
    //MODIF OVERLAY SUMMON
    public int modification_overlay(JFrame frame, Object[] content) {
        return JOptionPane.showConfirmDialog(frame, content, "Modification", JOptionPane.OK_CANCEL_OPTION);
    }

    //METHOD RECORD_ID Generator
    public static int generate_id() {
        int record_id = id_start;
        id_start++;
        return record_id;
    }

    //METHOD CANCEL ID GENERATOR
    public static int cancel_id() {
        int record_id = id_start;
        id_start--;
        return record_id;
    }

    //METHOD CLEAR TEXTFIELD
    public void clear_textfield() {
        txt_id.setText("");
        txt_name.setText("");
        txt_nim.setText("");
        txt_tugas.setText("");
        txt_quiz.setText("");
        txt_uts.setText("");
        txt_uas.setText("");
        txt_average.setText("");
        txt_grade.setText("");
        txt_keterangan.setText("");
    }

    //METHOD DISABLE TEXTFIELD EDITING
    public void textfield_disable() {
        txt_name.setEditable(false);
        txt_nim.setEditable(false);
        txt_tugas.setEditable(false);
        txt_quiz.setEditable(false);
        txt_uts.setEditable(false);
        txt_uas.setEditable(false);
    }

    //METHOD ENABLE TEXTFIELD EDITING
    public void textfield_enable() {
        txt_name.setEditable(true);
        txt_nim.setEditable(true);
        txt_tugas.setEditable(true);
        txt_quiz.setEditable(true);
        txt_uts.setEditable(true);
        txt_uas.setEditable(true);
    }

    //METHOD EXIT APP
    public static void exit_app() {
        int confirmation = JOptionPane.showConfirmDialog(null, "Confirm exiting the application.", "EXIT", JOptionPane.YES_NO_OPTION);
        if (confirmation == JOptionPane.YES_OPTION) {
        System.exit(0);
        }
    }

    //TODO METHOD CREATE NEW RECORD
    public void database_new() {
        //add logic: create new data(selectedRow)
    }

    //TODO METHOD EDIT DATA
    public void database_modify() {
        //add logic: modify and insert data(selectedRow)
    }

    //TODO METHOD DELETE DATA
    public void database_delete() {
        int confirmation = JOptionPane.showConfirmDialog(null, "Confirm Deleting This Document?", "DELETE DOCUMENT", JOptionPane.YES_NO_OPTION);
            if (confirmation == JOptionPane.YES_OPTION) {
                //add logic: delete data(selectedRow)
            }
    }

    //TODO (adjust) METHOD CALCULATE AVG
    public double calculate_average() {
        int_tugas = Double.parseDouble(value_tugas);
        int_quiz = Double.parseDouble(value_quiz);
        int_uts = Double.parseDouble(value_uts);
        int_uas = Double.parseDouble(value_uas);
        int_average = ((int_tugas + int_quiz + int_uas + int_uts) / 4);
        return int_average;
    }

    //TODO METHOD calculate grade
    public String calculate_grade() {
        //grade from average
        return value_grade;
    }

    //TODO METHOD KETERANGAN
    public String generate_keterangan() {
        //keterangan from GRADE
        return value_keterangan;
    }

    //TODO METHOD REFRESH TABLE
    public void refresh_table() {
        //add logic: refresh table{get data from db, display to table}
    }

    //TEMPORARY TEST METHOD ADD MANUAL TO TABLE
    public void test_add() {
        model.addRow(new Object[]{value_id, value_name, value_nim});
    }

    //ACTIONLISTENER METHODS
    @Override
    public void actionPerformed(ActionEvent e) {
        int selectedRow = table.getSelectedRow();
        if (e.getActionCommand().equals(view)) {
            if (selectedRow != -1) {
                //TODO no method: DATA DB TO TXTFIELD
                new view_overlay();
            }
        }

        else if (e.getActionCommand().equals(create)) {
            clear_textfield();
            textfield_enable();
            int record_id = generate_id();
            String id_string = String.valueOf(record_id);
            txt_id.setText(id_string);
            Object[] content = input_field(txt_id, txt_name, txt_nim, txt_tugas, txt_quiz, txt_uts, txt_uas);
            int new_data = modification_overlay((JFrame) SwingUtilities.getWindowAncestor((Component) e.getSource()), content);
            if (new_data == JOptionPane.OK_OPTION) {
                //GET TEXT FIELD DATA
                value_id = txt_id.getText();
                value_name = txt_name.getText();
                value_nim = txt_nim.getText();
                value_tugas = txt_tugas.getText();
                value_quiz = txt_quiz.getText();
                value_uts = txt_uts.getText();
                value_uas = txt_uas.getText();
                database_new(); //TODO adjust parameter
                refresh_table(); //TODO adjust parameter
            } else {
                cancel_id();
            }
        }

        else if (e.getActionCommand().equals(update)) {
            if(selectedRow != -1) {
                clear_textfield();
                txt_id.setText(model.getValueAt(selectedRow, 0).toString());
                //TODO GET DATA FROM DATABASE TO TEXTFIELD (no method)
                txt_name.setText(value_name);
                txt_nim.setText(value_nim);
                txt_tugas.setText(value_tugas);
                txt_quiz.setText(value_quiz);
                txt_uts.setText(value_uts);
                txt_uas.setText(value_uas);
                Object[] content = input_field(txt_id, txt_name, txt_nim, txt_tugas, txt_quiz, txt_uts, txt_uas);
                int update_data = modification_overlay((JFrame) SwingUtilities.getWindowAncestor((Component) e.getSource()), content);
                if (update_data == JOptionPane.OK_OPTION) {
                    database_modify(); //TODO adjust parameter
                    refresh_table(); //TODO adjust parameter
                }
            }
        }

        else if (e.getActionCommand().equals(delete)) {
            database_delete(); //TODO adjust parameter
            refresh_table(); //TODO adjust parameter
        }

        else if (e.getActionCommand().equals(exit)) {
            exit_app();
        }
    }

    public static void main (String[] args) {
        SwingUtilities.invokeLater(() -> {
            new TK4BAD();
        });
    }
}
