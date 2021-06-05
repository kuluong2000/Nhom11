package com.quanlynhatre.view;

import com.quanlynhatre.model.QL_DIEM;
import com.quanlynhatre.model.Thongke;
import com.quanlynhatre.model.User;
import com.quanlynhatre.model.suckhoe;
import com.quanlynhatre.service.DiemService;
import com.quanlynhatre.service.SuckhoeService;
import com.quanlynhatre.service.ThongkeService;
import com.quanlynhatre.service.UserService;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;
import javax.swing.ComboBoxModel;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.SpinnerListModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import org.apache.poi.sl.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author PC
 */
public class Home extends javax.swing.JFrame {
    UserService userService;
    DiemService diemService;
    SuckhoeService skService;
    ThongkeService ThongkeService;
    DefaultTableModel defaultTableModel;
    DefaultTableModel defaultTableModel1;
    DefaultTableModel defaultTableModel2;
    DefaultTableModel defaultTableModeltk;
    //model
        User user = new User();
        QL_DIEM ql_diem = new QL_DIEM();
        suckhoe sk = new suckhoe();
    /**
     * Creates new form Home
     */
    public Home() {
        initComponents();
       
        //Quản lý trẻ
        userService = new UserService();
        defaultTableModel = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        

        };
    
        
        jTable1.setModel(defaultTableModel);
        defaultTableModel.addColumn("Mã Trẻ");
        defaultTableModel.addColumn("Họ Tên");
        defaultTableModel.addColumn("Mã Lớp");
        defaultTableModel.addColumn("Ngày Sinh");
        defaultTableModel.addColumn("Giới Tính");
        defaultTableModel.addColumn("Địa Chỉ");
        defaultTableModel.addColumn("Dân Tộc");
        defaultTableModel.addColumn("Tôn Giáo");
        List<User> users = userService.getAllUser(SQL_tre);
        for (User user : users) {
            defaultTableModel.addRow(new Object[]{
                user.getMahs(), user.getHoten(), user.getMalop(), user.getNgaysinh(), user.getGiotinh(), user.getDiachi(), user.getDantoc(), user.getTongiao()
            });
            jCbb_matre.addItem(user.getMahs());
            jCbb_matre_sk.addItem(user.getMahs());
        }
        jRadio_nam.setSelected(true);
         //chọn itemtable_qltre
//        ListSelectionModel listselect=jTable1.getSelectionModel();
//        listselect.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
//        listselect.addListSelectionListener(new ListSelectionListener() {
//            @Override
//            public void valueChanged(ListSelectionEvent e) {
//                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//                int[] rows=jTable1.getSelectedRows();
//                int [] cols=jTable1.getSelectedColumns();
//               String matre=String.valueOf(jTable1.getValueAt(rows[0],0));
//               String tentre=String.valueOf(jTable1.getValueAt(rows[0],1));
//               String malop=String.valueOf(jTable1.getValueAt(rows[0],2));
//               String gioitinh=String.valueOf(jTable1.getValueAt(rows[0],4));
//               String ngaysinh=String.valueOf(jTable1.getValueAt(rows[0],3));
//               String diachi=String.valueOf(jTable1.getValueAt(rows[0],5));
//               String dantoc=String.valueOf(jTable1.getValueAt(rows[0],6));
//               String tongiao=String.valueOf(jTable1.getValueAt(rows[0],7));
//               if(gioitinh.trim().equals("nam")){
//                   jRadio_nam.setSelected(true);
//               }
//               else
//                   jRadio_nu.setSelected(true);
////               jDate_ngaysinh.setDate(ngaysinh);
//               jT_matre.setText(matre);
//               jT_tentre.setText(tentre);
//               jCbb_lop.setSelectedItem(malop);
//               jDate_ngaysinh.setDateFormatString(ngaysinh.trim());
//               jT_dc.setText(diachi);
//               jT_dt.setText(dantoc);
//               jT_tg.setText(tongiao);
//            }
//        });
        //kết thúc form quản lý trẻ
        // start form Quản lý điẻm
//        UserService service = null;
//        SpinnerListModel listmodel= new SpinnerListModel(service.getAllUser());
//        jCbb_matre.setModel((ComboBoxModel<String>) listmodel);
        ThongkeService = new ThongkeService();
        defaultTableModeltk = new DefaultTableModel()  {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }

        };
        jTable_thongke.setModel(defaultTableModeltk);
        defaultTableModeltk.addColumn("Mã Trẻ");
        defaultTableModeltk.addColumn("Họ Tên");
        defaultTableModeltk.addColumn("lớp");
        defaultTableModeltk.addColumn("Giới tính");
        defaultTableModeltk.addColumn("Ngày sinh");
        defaultTableModeltk.addColumn("Tổng điểm");
        defaultTableModeltk.addColumn("Học Lực");
        
        String SQL = "select * from tongdiem,hocsinh where tongdiem.mahs = hocsinh.mahs";
        List<Thongke> thongkes =  ThongkeService.getAlltk(SQL);
        for (Thongke tk : thongkes) {
            defaultTableModeltk.addRow(new Object[]{
                tk.getMahs(),tk.getTenhs(),tk.getLop(),tk.getGioitinh(),tk.getNgaysinh(),tk.getDiem(),tk.getXeploai()
            });
        }
         diemService = new DiemService();
        defaultTableModel1 = new DefaultTableModel()  {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }

        };
        jTable2.setModel(defaultTableModel1);
        defaultTableModel1.addColumn("Mã Trẻ");
        defaultTableModel1.addColumn("Họ Tên");
        defaultTableModel1.addColumn("Toán");
        defaultTableModel1.addColumn("Văn Nghệ");
        defaultTableModel1.addColumn("Thể Dục");
        defaultTableModel1.addColumn("Mỹ Thuật");
        defaultTableModel1.addColumn("Tổng Điểm");
        defaultTableModel1.addColumn("Học Lực");
        List<QL_DIEM> diems =  diemService.getAllDiem();
        for (QL_DIEM diem : diems) {
            defaultTableModel1.addRow(new Object[]{
                diem.getMahs(),diem.getHoten(), diem.getToan(), diem.getVannghe(), diem.getTheduc(), diem.getMythuat(), diem.getTongdiem(), diem.getHocluc()
            });
        }
        
////        list chọn item
//        ListSelectionModel listselect_diem=jTable2.getSelectionModel();
//        listselect_diem.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
//        listselect_diem.addListSelectionListener(new ListSelectionListener() {
//            @Override
//            public void valueChanged(ListSelectionEvent e) {
//                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//                int[] rows=jTable2.getSelectedRows();
//                int [] cols=jTable2.getSelectedColumns();
//               String matre=String.valueOf(jTable2.getValueAt(rows[0],0));
//               String toan=String.valueOf(jTable2.getValueAt(rows[0],2));
//               String vannghe=String.valueOf(jTable2.getValueAt(rows[0],3));
//               String theduc=String.valueOf(jTable2.getValueAt(rows[0],4));
//               String mythuat=String.valueOf(jTable2.getValueAt(rows[0],5));
//               
//               jCbb_matre.setSelectedItem(matre);
//               jT_toan.setText(toan);
//               jT_vannghe.setText(vannghe);
//               jT_td.setText(theduc);
//               jT_mythuat.setText(mythuat);
//            }
//        });
        //end form quan ly diem
        //start form quan ly sk
         skService = new SuckhoeService();
        defaultTableModel2 = new DefaultTableModel()  {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }

        };
        jTable3.setModel(defaultTableModel2);
        defaultTableModel2.addColumn("Mã hs");
        defaultTableModel2.addColumn("Ten Hs");
        defaultTableModel2.addColumn("Chiều cao");
        defaultTableModel2.addColumn("Cân nặng");
        defaultTableModel2.addColumn("Trạng thái");
        
        List<suckhoe> sks =  skService.getAllSuckhoe();
        for (suckhoe sk : sks) {
            defaultTableModel2.addRow(new Object[]{
                sk.getMahs(),sk.getTenhs(),sk.getChieucao(),sk.getCannang(),sk.getTrangthai()
            });
        }
       
        //end form ql sk
        //list chon item
//        ListSelectionModel listselect_sk=jTable3.getSelectionModel();
//        listselect_sk.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
//        listselect_sk.addListSelectionListener(new ListSelectionListener() {
//            @Override
//            public void valueChanged(ListSelectionEvent e) {
//                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//                int[] rows=jTable3.getSelectedRows();
//                int [] cols=jTable3.getSelectedColumns();
//               String matre=String.valueOf(jTable3.getValueAt(rows[0],0));
//               String chieucao=String.valueOf(jTable3.getValueAt(rows[0],1));
//               String cannang=String.valueOf(jTable3.getValueAt(rows[0],2));
//               
//               jCbb_matre_sk.setSelectedItem(matre);
//               jT_chieucao.setText(chieucao);
//               jT_cannang.setText(cannang);
//               
//            }
//        });
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        buttonGroup3 = new javax.swing.ButtonGroup();
        buttonGroup4 = new javax.swing.ButtonGroup();
        buttonGroup5 = new javax.swing.ButtonGroup();
        buttonGroup6 = new javax.swing.ButtonGroup();
        jCalendar1 = new com.toedter.calendar.JCalendar();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        diem_refresh = new javax.swing.JButton();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jT_dt = new javax.swing.JTextField();
        jT_matre = new javax.swing.JTextField();
        jCbb_lop = new javax.swing.JComboBox<String>();
        jRadio_nam = new javax.swing.JRadioButton();
        jRadio_nu = new javax.swing.JRadioButton();
        jT_dc = new javax.swing.JTextField();
        jT_tentre = new javax.swing.JTextField();
        jT_tg = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        user_refresh = new javax.swing.JButton();
        jLabel33 = new javax.swing.JLabel();
        jT_tk = new javax.swing.JTextField();
        jBt_tk = new javax.swing.JButton();
        jDate_ngaysinh = new com.toedter.calendar.JDateChooser();
        jLabel41 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jLabel24 = new javax.swing.JLabel();
        jCbb_matre = new javax.swing.JComboBox<String>();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jT_toan = new javax.swing.JTextField();
        jT_mythuat = new javax.swing.JTextField();
        jT_td = new javax.swing.JTextField();
        jT_vannghe = new javax.swing.JTextField();
        jBt_them_diem = new javax.swing.JButton();
        jBt_sua_diem = new javax.swing.JButton();
        jBt_xoa_d = new javax.swing.JButton();
        jBt_tk_d = new javax.swing.JButton();
        jT_tk_diem = new javax.swing.JTextField();
        jLabel37 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jT_chieucao = new javax.swing.JTextField();
        jT_cannang = new javax.swing.JTextField();
        jCbb_matre_sk = new javax.swing.JComboBox<String>();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        sk_refresh = new javax.swing.JButton();
        jT_tksk = new javax.swing.JTextField();
        jBt_tk_sk = new javax.swing.JButton();
        jLabel34 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable_thongke = new javax.swing.JTable();
        jButton_xuatfile = new javax.swing.JButton();
        jLabel32 = new javax.swing.JLabel();
        jTK_TK = new javax.swing.JButton();
        JT_tktk = new javax.swing.JTextField();
        jCBB_lop_tk = new javax.swing.JComboBox();
        jLabel39 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        jMenu3 = new javax.swing.JMenu();
        jMenu4 = new javax.swing.JMenu();
        jMenu5 = new javax.swing.JMenu();

        diem_refresh.setBackground(new java.awt.Color(255, 255, 153));
        diem_refresh.setText("refresh");
        diem_refresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                diem_refreshActionPerformed(evt);
            }
        });

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setFont(new java.awt.Font("Times New Roman", 0, 15)); // NOI18N
        getContentPane().setLayout(null);

        jTabbedPane1.setBackground(new java.awt.Color(255, 204, 204));

        jPanel1.setBackground(new java.awt.Color(204, 255, 204));
        jPanel1.setLayout(null);

        jLabel1.setFont(new java.awt.Font("SVN-Whimsy", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 51, 51));
        jLabel1.setText("QUẢN LÝ NHÀ TRẺ, MẦM NON");
        jLabel1.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jPanel1.add(jLabel1);
        jLabel1.setBounds(368, 70, 660, 80);

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/logo.png"))); // NOI18N
        jPanel1.add(jLabel3);
        jLabel3.setBounds(100, 30, 246, 255);

        jLabel2.setFont(new java.awt.Font("Times-Narrow", 1, 36)); // NOI18N
        jLabel2.setText("Nhóm 11:");
        jPanel1.add(jLabel2);
        jLabel2.setBounds(420, 240, 165, 56);

        jLabel4.setFont(new java.awt.Font("Times", 0, 24)); // NOI18N
        jLabel4.setText("Hồ Thị Tuyết");
        jPanel1.add(jLabel4);
        jLabel4.setBounds(620, 240, 137, 34);

        jLabel5.setFont(new java.awt.Font("Times", 0, 24)); // NOI18N
        jLabel5.setText("Trần Hữu Lương");
        jPanel1.add(jLabel5);
        jLabel5.setBounds(620, 300, 183, 33);

        jLabel6.setFont(new java.awt.Font("Times", 0, 24)); // NOI18N
        jLabel6.setText("Ngô Thanh Tuấn");
        jPanel1.add(jLabel6);
        jLabel6.setBounds(620, 360, 182, 33);

        jLabel7.setFont(new java.awt.Font("Times", 0, 24)); // NOI18N
        jLabel7.setText("Nguyễn Thị Diệu Ý");
        jPanel1.add(jLabel7);
        jLabel7.setBounds(620, 410, 220, 34);

        jLabel8.setFont(new java.awt.Font("Times", 0, 24)); // NOI18N
        jLabel8.setText("Dương Thị Thùy Dung");
        jPanel1.add(jLabel8);
        jLabel8.setBounds(620, 480, 244, 32);

        jLabel9.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel9.setText("GVHD:");
        jPanel1.add(jLabel9);
        jLabel9.setBounds(420, 580, 79, 28);

        jLabel10.setFont(new java.awt.Font("Times", 0, 24)); // NOI18N
        jLabel10.setText("Đỗ Phú Huy");
        jPanel1.add(jLabel10);
        jLabel10.setBounds(630, 570, 131, 39);

        jLabel35.setIcon(new javax.swing.ImageIcon("C:\\Users\\PC\\Downloads\\thi\\Nhom11-develop\\Nhom11-update\\demojava\\JavaLibrary16\\src\\com\\quanlytre\\imager\\1.png")); // NOI18N
        jLabel35.setText("jLabel35");
        jPanel1.add(jLabel35);
        jLabel35.setBounds(0, -90, 1550, 1070);

        jLabel38.setText("jLabel38");
        jPanel1.add(jLabel38);
        jLabel38.setBounds(0, 0, 40, 14);

        jTabbedPane1.addTab("WELLCOME", jPanel1);

        jPanel2.setBackground(new java.awt.Color(204, 255, 204));
        jPanel2.setLayout(null);

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/logo.png"))); // NOI18N
        jPanel2.add(jLabel11);
        jLabel11.setBounds(50, 150, 207, 171);

        jLabel15.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel15.setText("Mã trẻ:");
        jPanel2.add(jLabel15);
        jLabel15.setBounds(320, 90, 56, 22);

        jLabel16.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel16.setText("Mã lớp:");
        jPanel2.add(jLabel16);
        jLabel16.setBounds(320, 170, 59, 22);

        jLabel17.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel17.setText("Họ tên:");
        jPanel2.add(jLabel17);
        jLabel17.setBounds(320, 130, 59, 22);

        jLabel18.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel18.setText("Giới tính:");
        jPanel2.add(jLabel18);
        jLabel18.setBounds(320, 220, 72, 22);

        jLabel19.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel19.setText("Ngày sinh:");
        jPanel2.add(jLabel19);
        jLabel19.setBounds(640, 90, 100, 22);

        jLabel20.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel20.setText("Địa chỉ:");
        jPanel2.add(jLabel20);
        jLabel20.setBounds(640, 130, 80, 22);

        jLabel21.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel21.setText("Dân tộc:");
        jPanel2.add(jLabel21);
        jLabel21.setBounds(640, 180, 80, 22);

        jLabel22.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel22.setText("Tôn giáo");
        jPanel2.add(jLabel22);
        jLabel22.setBounds(640, 220, 90, 22);
        jPanel2.add(jT_dt);
        jT_dt.setBounds(760, 170, 141, 30);
        jPanel2.add(jT_matre);
        jT_matre.setBounds(410, 90, 148, 30);

        jCbb_lop.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "08M2      ", "08M3      ", "08M4      ", "08M1      " }));
        jCbb_lop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCbb_lopActionPerformed(evt);
            }
        });
        jPanel2.add(jCbb_lop);
        jCbb_lop.setBounds(410, 170, 148, 30);

        buttonGroup1.add(jRadio_nam);
        jRadio_nam.setText("Nam");
        jPanel2.add(jRadio_nam);
        jRadio_nam.setBounds(400, 220, 60, 23);

        buttonGroup1.add(jRadio_nu);
        jRadio_nu.setText("Nữ");
        jPanel2.add(jRadio_nu);
        jRadio_nu.setBounds(470, 220, 50, 23);

        jT_dc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jT_dcActionPerformed(evt);
            }
        });
        jPanel2.add(jT_dc);
        jT_dc.setBounds(760, 120, 141, 30);

        jT_tentre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jT_tentreActionPerformed(evt);
            }
        });
        jPanel2.add(jT_tentre);
        jT_tentre.setBounds(410, 130, 148, 30);

        jT_tg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jT_tgActionPerformed(evt);
            }
        });
        jPanel2.add(jT_tg);
        jT_tg.setBounds(760, 220, 141, 30);

        jTable1.setBackground(new java.awt.Color(255, 255, 204));
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jPanel2.add(jScrollPane1);
        jScrollPane1.setBounds(150, 460, 1040, 276);

        jButton1.setBackground(new java.awt.Color(102, 255, 102));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/quanlytre/imager/add-goods.png"))); // NOI18N
        jButton1.setText("Thêm");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton1);
        jButton1.setBounds(470, 350, 110, 51);

        jButton2.setBackground(new java.awt.Color(153, 255, 102));
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/quanlytre/imager/edit.png"))); // NOI18N
        jButton2.setText("Sửa");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton2);
        jButton2.setBounds(670, 350, 87, 51);

        jButton3.setBackground(new java.awt.Color(153, 255, 102));
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/quanlytre/imager/xoa.png"))); // NOI18N
        jButton3.setText("Xóa");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton3);
        jButton3.setBounds(830, 350, 85, 54);

        user_refresh.setBackground(new java.awt.Color(153, 255, 102));
        user_refresh.setText("refresh");
        user_refresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                user_refreshActionPerformed(evt);
            }
        });
        jPanel2.add(user_refresh);
        user_refresh.setBounds(990, 350, 67, 54);

        jLabel33.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel33.setText("Nhập mã cần tìm:");
        jPanel2.add(jLabel33);
        jLabel33.setBounds(620, 290, 160, 20);
        jPanel2.add(jT_tk);
        jT_tk.setBounds(810, 280, 118, 40);

        jBt_tk.setBackground(new java.awt.Color(0, 255, 51));
        jBt_tk.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jBt_tk.setText("Tìm kiếm");
        jBt_tk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBt_tkActionPerformed(evt);
            }
        });
        jPanel2.add(jBt_tk);
        jBt_tk.setBounds(970, 290, 87, 30);

        jDate_ngaysinh.setDateFormatString(" dd-MM-YYYY");
        jPanel2.add(jDate_ngaysinh);
        jDate_ngaysinh.setBounds(760, 80, 141, 30);

        jLabel41.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel41.setForeground(new java.awt.Color(255, 51, 51));
        jLabel41.setText("QUẢN LÝ THÔNG TIN TRẺ");
        jLabel41.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jPanel2.add(jLabel41);
        jLabel41.setBounds(390, 10, 579, 60);

        jLabel42.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel42.setIcon(new javax.swing.ImageIcon("C:\\Users\\PC\\Downloads\\thi\\Nhom11-develop\\Nhom11-update\\demojava\\JavaLibrary16\\src\\com\\quanlytre\\imager\\2.jpg")); // NOI18N
        jLabel42.setText("jLabel42");
        jPanel2.add(jLabel42);
        jLabel42.setBounds(-10, -50, 1560, 960);

        jTabbedPane1.addTab("QUẢN LÝ TRẺ", jPanel2);

        jPanel4.setBackground(new java.awt.Color(204, 255, 204));
        jPanel4.setLayout(null);

        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/logo.png"))); // NOI18N
        jPanel4.add(jLabel13);
        jLabel13.setBounds(75, 55, 207, 171);

        jLabel23.setFont(new java.awt.Font("UTM Alberta Heavy", 1, 36)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(255, 51, 51));
        jLabel23.setText("QUẢN LÝ ĐIỂM");
        jLabel23.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jPanel4.add(jLabel23);
        jLabel23.setBounds(472, 13, 579, 60);

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTable2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable2MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTable2);

        jPanel4.add(jScrollPane2);
        jScrollPane2.setBounds(130, 430, 1010, 240);

        jLabel24.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel24.setText("Mã Trẻ: ");
        jPanel4.add(jLabel24);
        jLabel24.setBounds(340, 140, 67, 22);

        jCbb_matre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCbb_matreActionPerformed(evt);
            }
        });
        jPanel4.add(jCbb_matre);
        jCbb_matre.setBounds(460, 130, 184, 33);

        jLabel25.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel25.setText("Điểm Môn Toán:");
        jPanel4.add(jLabel25);
        jLabel25.setBounds(310, 191, 133, 22);

        jLabel26.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel26.setText("Mỹ thuật:");
        jPanel4.add(jLabel26);
        jLabel26.setBounds(310, 267, 111, 22);

        jLabel27.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel27.setText("Thể dục:");
        jPanel4.add(jLabel27);
        jLabel27.setBounds(711, 191, 70, 22);

        jLabel28.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel28.setText("Văn Nghệ:");
        jPanel4.add(jLabel28);
        jLabel28.setBounds(698, 267, 83, 22);

        jT_toan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jT_toanActionPerformed(evt);
            }
        });
        jPanel4.add(jT_toan);
        jT_toan.setBounds(461, 183, 180, 43);
        jPanel4.add(jT_mythuat);
        jT_mythuat.setBounds(461, 259, 180, 42);
        jPanel4.add(jT_td);
        jT_td.setBounds(836, 183, 121, 43);
        jPanel4.add(jT_vannghe);
        jT_vannghe.setBounds(836, 258, 121, 44);

        jBt_them_diem.setBackground(new java.awt.Color(255, 255, 204));
        jBt_them_diem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/quanlytre/imager/add-goods.png"))); // NOI18N
        jBt_them_diem.setText("Thêm");
        jBt_them_diem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBt_them_diemActionPerformed(evt);
            }
        });
        jPanel4.add(jBt_them_diem);
        jBt_them_diem.setBounds(190, 370, 105, 41);

        jBt_sua_diem.setBackground(new java.awt.Color(255, 255, 153));
        jBt_sua_diem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/quanlytre/imager/edit.png"))); // NOI18N
        jBt_sua_diem.setText("Sửa");
        jBt_sua_diem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBt_sua_diemActionPerformed(evt);
            }
        });
        jPanel4.add(jBt_sua_diem);
        jBt_sua_diem.setBounds(330, 370, 105, 40);

        jBt_xoa_d.setBackground(new java.awt.Color(255, 255, 204));
        jBt_xoa_d.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/quanlytre/imager/xoa.png"))); // NOI18N
        jBt_xoa_d.setText("Xóa");
        jBt_xoa_d.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBt_xoa_dActionPerformed(evt);
            }
        });
        jPanel4.add(jBt_xoa_d);
        jBt_xoa_d.setBounds(470, 370, 105, 40);

        jBt_tk_d.setBackground(new java.awt.Color(255, 255, 102));
        jBt_tk_d.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jBt_tk_d.setText("Tim kiếm");
        jBt_tk_d.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBt_tk_dActionPerformed(evt);
            }
        });
        jPanel4.add(jBt_tk_d);
        jBt_tk_d.setBounds(920, 370, 102, 39);
        jPanel4.add(jT_tk_diem);
        jT_tk_diem.setBounds(700, 370, 204, 38);

        jLabel37.setIcon(new javax.swing.ImageIcon("C:\\Users\\PC\\Downloads\\thi\\Nhom11-develop\\Nhom11-update\\demojava\\JavaLibrary16\\src\\com\\quanlytre\\imager\\1.png")); // NOI18N
        jLabel37.setText("jLabel37");
        jPanel4.add(jLabel37);
        jLabel37.setBounds(-160, -50, 1644, 960);

        jTabbedPane1.addTab("QUẢN LÝ ĐIỂM", jPanel4);

        jPanel5.setBackground(new java.awt.Color(204, 255, 204));
        jPanel5.setLayout(null);

        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/logo.png"))); // NOI18N
        jPanel5.add(jLabel14);
        jLabel14.setBounds(71, 44, 207, 171);

        jTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTable3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable3MouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(jTable3);

        jPanel5.add(jScrollPane3);
        jScrollPane3.setBounds(350, 340, 750, 220);

        jLabel29.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel29.setText("Mã Trẻ:");
        jPanel5.add(jLabel29);
        jLabel29.setBounds(360, 140, 70, 22);

        jLabel30.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel30.setText("Chiều Cao:");
        jPanel5.add(jLabel30);
        jLabel30.setBounds(350, 190, 100, 22);

        jLabel31.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel31.setText("Cân Nặng:");
        jPanel5.add(jLabel31);
        jLabel31.setBounds(360, 250, 100, 22);
        jPanel5.add(jT_chieucao);
        jT_chieucao.setBounds(470, 180, 152, 44);

        jT_cannang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jT_cannangActionPerformed(evt);
            }
        });
        jPanel5.add(jT_cannang);
        jT_cannang.setBounds(470, 240, 152, 40);

        jPanel5.add(jCbb_matre_sk);
        jCbb_matre_sk.setBounds(470, 130, 152, 35);

        jButton4.setBackground(new java.awt.Color(51, 255, 204));
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/quanlytre/imager/add-goods.png"))); // NOI18N
        jButton4.setText("Thêm");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel5.add(jButton4);
        jButton4.setBounds(690, 190, 110, 41);

        jButton5.setBackground(new java.awt.Color(102, 255, 204));
        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/quanlytre/imager/edit.png"))); // NOI18N
        jButton5.setText("Sửa");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jPanel5.add(jButton5);
        jButton5.setBounds(840, 190, 100, 40);

        jButton6.setBackground(new java.awt.Color(153, 255, 204));
        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/quanlytre/imager/xoa.png"))); // NOI18N
        jButton6.setText("Xóa");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        jPanel5.add(jButton6);
        jButton6.setBounds(690, 240, 110, 40);

        sk_refresh.setBackground(new java.awt.Color(153, 255, 204));
        sk_refresh.setText("refresh");
        sk_refresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sk_refreshActionPerformed(evt);
            }
        });
        jPanel5.add(sk_refresh);
        sk_refresh.setBounds(840, 240, 100, 40);
        jPanel5.add(jT_tksk);
        jT_tksk.setBounds(690, 120, 201, 50);

        jBt_tk_sk.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/quanlytre/imager/search.png"))); // NOI18N
        jBt_tk_sk.setText("Tìm kiếm");
        jBt_tk_sk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBt_tk_skActionPerformed(evt);
            }
        });
        jPanel5.add(jBt_tk_sk);
        jBt_tk_sk.setBounds(900, 120, 150, 50);

        jLabel34.setFont(new java.awt.Font("UTM Alberta Heavy", 1, 36)); // NOI18N
        jLabel34.setForeground(new java.awt.Color(255, 51, 51));
        jLabel34.setText("QUẢN LÝ SỨC KHỎE");
        jLabel34.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jPanel5.add(jLabel34);
        jLabel34.setBounds(396, 11, 579, 60);

        jLabel36.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel36.setIcon(new javax.swing.ImageIcon("C:\\Users\\PC\\Downloads\\thi\\Nhom11-develop\\Nhom11-update\\demojava\\JavaLibrary16\\src\\com\\quanlytre\\imager\\3.png")); // NOI18N
        jLabel36.setText("jLabel36");
        jPanel5.add(jLabel36);
        jLabel36.setBounds(-60, -560, 1530, 1480);

        jTabbedPane1.addTab("QUẢN LÝ SỨC KHỎE", jPanel5);

        jPanel3.setBackground(new java.awt.Color(153, 204, 255));
        jPanel3.setLayout(null);

        jTable_thongke.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane4.setViewportView(jTable_thongke);

        jPanel3.add(jScrollPane4);
        jScrollPane4.setBounds(160, 310, 840, 260);

        jButton_xuatfile.setBackground(new java.awt.Color(0, 255, 204));
        jButton_xuatfile.setText("Xuất file excel");
        jButton_xuatfile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_xuatfileActionPerformed(evt);
            }
        });
        jPanel3.add(jButton_xuatfile);
        jButton_xuatfile.setBounds(830, 610, 130, 50);

        jLabel32.setFont(new java.awt.Font("Times New Roman", 1, 36)); // NOI18N
        jLabel32.setForeground(new java.awt.Color(255, 51, 51));
        jLabel32.setText("QUẢN LÝ NHÀ TRẺ, MẦM NON");
        jLabel32.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jPanel3.add(jLabel32);
        jLabel32.setBounds(310, 90, 550, 60);

        jTK_TK.setBackground(new java.awt.Color(204, 255, 204));
        jTK_TK.setText("Tìm Kiếm");
        jTK_TK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTK_TKActionPerformed(evt);
            }
        });
        jPanel3.add(jTK_TK);
        jTK_TK.setBounds(830, 170, 100, 50);
        jPanel3.add(JT_tktk);
        JT_tktk.setBounds(580, 170, 230, 50);

        jCBB_lop_tk.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "08M2", "08M3", "08M4", "08M1" }));
        jCBB_lop_tk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCBB_lop_tkActionPerformed(evt);
            }
        });
        jPanel3.add(jCBB_lop_tk);
        jCBB_lop_tk.setBounds(290, 170, 160, 40);

        jLabel39.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel39.setText("Mã lớp");
        jPanel3.add(jLabel39);
        jLabel39.setBounds(200, 180, 80, 22);

        jLabel12.setIcon(new javax.swing.ImageIcon("C:\\Users\\PC\\Downloads\\thi\\Nhom11-develop\\Nhom11-update\\demojava\\JavaLibrary16\\src\\com\\quanlytre\\imager\\5.png")); // NOI18N
        jPanel3.add(jLabel12);
        jLabel12.setBounds(100, 0, 990, 770);

        jTabbedPane1.addTab("THỐNG KÊ", jPanel3);

        getContentPane().add(jTabbedPane1);
        jTabbedPane1.setBounds(0, 0, 1510, 930);

        jMenuBar1.setFont(new java.awt.Font("Times New Roman", 0, 15)); // NOI18N

        jMenu1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/quanlytre/imager/baseline_home_black_24dp.png"))); // NOI18N
        jMenu1.setText("HOME");
        jMenu1.setFont(new java.awt.Font("Times New Roman", 0, 15)); // NOI18N
        jMenuBar1.add(jMenu1);

        jMenu2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/quanlytre/imager/baseline_account_circle_black_24dp.png"))); // NOI18N
        jMenu2.setText("Quản Lý Doanh Mục");
        jMenu2.setFont(new java.awt.Font("Times New Roman", 0, 15)); // NOI18N
        jMenuBar1.add(jMenu2);

        jMenu3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/quanlytre/imager/Zoom-icon.png"))); // NOI18N
        jMenu3.setText("Tìm Kiếm");
        jMenu3.setFont(new java.awt.Font("Times New Roman", 0, 15)); // NOI18N
        jMenuBar1.add(jMenu3);

        jMenu4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/quanlytre/imager/thoat.png"))); // NOI18N
        jMenu4.setText("Đăng xuất");
        jMenu4.setFont(new java.awt.Font("Times New Roman", 0, 15)); // NOI18N
        jMenuBar1.add(jMenu4);

        jMenu5.setText("Help");
        jMenu5.setFont(new java.awt.Font("Times New Roman", 0, 15)); // NOI18N
        jMenuBar1.add(jMenu5);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jT_tentreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jT_tentreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jT_tentreActionPerformed

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField2ActionPerformed

    private void jT_toanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jT_toanActionPerformed
       // TODO add your handling code here:
    }//GEN-LAST:event_jT_toanActionPerformed

    private void jT_dcActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jT_dcActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jT_dcActionPerformed

    private void jCbb_lopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCbb_lopActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCbb_lopActionPerformed

    public void FillCombo() {
        try {
            
            
        }catch(Exception e) {
        }
    }
    String SQL_tre = " select * from hocsinh";
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        try{
        user.setMahs(jT_matre.getText());
        user.setHoten(jT_tentre.getText());
        SimpleDateFormat sdf = new SimpleDateFormat("dd-mm-yyyy", Locale.getDefault());
        String d = sdf.format(jDate_ngaysinh.getDate());
        user.setNgaysinh(d );
        user.setDiachi(jT_dc.getText());
        user.setDantoc(jT_dt.getText());
        user.setTongiao(jT_tg.getText());
        if(jRadio_nam.isSelected())
            user.setGiotinh("Nam");
               
            else
               user.setGiotinh("Nữ");
        
        user.setMalop((String)jCbb_lop.getSelectedItem());
        }catch(Exception e){
            
        }
        if(jT_matre.getText().isEmpty() || jT_tentre.getText().isEmpty()){
            JOptionPane.showMessageDialog(this,"Không được để trống","Thông báo", JOptionPane.INFORMATION_MESSAGE);
        }
         int dem=1;
          List<User> users =  userService.getAllUser(SQL_tre);
          for (User user : users) {
             if(user.getMahs().equals(jT_matre.getText())) {
                 dem=dem+1;
             }  
             }
          if(dem==1 && !jT_matre.getText().isEmpty() && !jT_tentre.getText().isEmpty()){
              userService.insertSp(user);
             jCbb_matre.addItem(user.getMahs());
                jCbb_matre_sk.addItem(user.getMahs());
                refresh();
            JOptionPane.showMessageDialog(this,"Thêm thành công","Thông báo", JOptionPane.INFORMATION_MESSAGE);}
//         
         if(dem!=1 && !jT_matre.getText().isEmpty() ){
                JOptionPane.showMessageDialog(this,"Trùng mã mời bạn nhập lại","Thông báo", JOptionPane.INFORMATION_MESSAGE);  
          }
        
        
        
        
    
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jT_cannangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jT_cannangActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jT_cannangActionPerformed
 
    private void jBt_them_diemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBt_them_diemActionPerformed
        // TODO add your handling code here:
        
        try{
        ql_diem.setMahs((String)jCbb_matre.getSelectedItem());
        ql_diem.setToan( Float.parseFloat(jT_toan.getText()) );
        ql_diem.setVannghe( Float.parseFloat(jT_vannghe.getText()));
        ql_diem.setTheduc( Float.parseFloat(jT_td.getText()));
        ql_diem.setMythuat(Float.parseFloat(jT_mythuat.getText()));
        float td=0;
        td=(ql_diem.getToan()+ql_diem.getVannghe()+ql_diem.getTheduc()+ql_diem.getMythuat())/4;
        ql_diem.setTongdiem(td);
        if(td>=8.5){
            ql_diem.setHocluc("Giỏi");
        }
        else if (td>=6.5){
            ql_diem.setHocluc("Khá");
        }
        else if (td>=5){
            ql_diem.setHocluc("Trung bình");
        }
        else
            ql_diem.setHocluc("Yếu");
        }catch(Exception e){
            
        };
//        
         int dem=1;
          List<QL_DIEM> diems =  diemService.getAllDiem();
          for (QL_DIEM diem : diems) {
             if(diem.getMahs().equals((String)jCbb_matre.getSelectedItem())) {
                 dem=dem+1;
             }  
             }
//          String ktr=(String)jCbb_matre.getSelectedItem();
//          System.out.println((String)jCbb_matre.getSelectedItem());
          if(dem==1 ){
                diemService.insertdiem(ql_diem);
                refresh_QLdiem();
                refresh_tk();
            JOptionPane.showMessageDialog(this,"Thêm thành công","Thông báo", JOptionPane.INFORMATION_MESSAGE);}
//         
         if(dem!=1 ){
                JOptionPane.showMessageDialog(this,"Trùng mã mời bạn nhập lại","Thông báo", JOptionPane.INFORMATION_MESSAGE);  
          }
        
        
    }//GEN-LAST:event_jBt_them_diemActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
//        User user = new User();
        int row = jTable1.getSelectedRow();
        if(row == -1){
            JOptionPane.showMessageDialog(Home.this, "vui long chon user truoc", "loi", JOptionPane.ERROR_MESSAGE);
        }else{
            int confirm = JOptionPane.showConfirmDialog(Home.this, "ban chac chan muốn sửa khong? ");
            if (confirm == JOptionPane.YES_OPTION) {
                user.setMahs(jT_matre.getText());
                user.setHoten(jT_tentre.getText());
                SimpleDateFormat sdf = new SimpleDateFormat("dd/mm/yyyy", Locale.getDefault());
                String d = sdf.format(jDate_ngaysinh.getDate());
                user.setNgaysinh(d );
                user.setDiachi(jT_dc.getText());
                user.setDantoc(jT_dt.getText());
                user.setTongiao(jT_tg.getText());
                if(jRadio_nam.isSelected())
                    user.setGiotinh("Nam");

                    else
                       user.setGiotinh("Nữ");

                user.setMalop((String)jCbb_lop.getSelectedItem());
                userService.updateUser(user);
            }
        }
        refresh_tk();
        refresh();
       
        
    }//GEN-LAST:event_jButton2ActionPerformed
private void refresh(){
    defaultTableModel.setRowCount(0);
    
        List<User> users = userService.getAllUser(SQL_tre);
                for (User user : users) {
            defaultTableModel.addRow(new Object[]{
                user.getMahs(), user.getHoten(), user.getMalop(), user.getNgaysinh(), user.getGiotinh(), user.getDiachi(), user.getDantoc(), user.getTongiao()
            });
           
        }
}
private void refresh_QLdiem(){
    defaultTableModel1.setRowCount(0);
    
        List<QL_DIEM> ql_diem = diemService.getAllDiem();
               for (QL_DIEM diem : ql_diem) {
            defaultTableModel1.addRow(new Object[]{
                diem.getMahs(),diem.getHoten(), diem.getToan(), diem.getVannghe(), diem.getTheduc(), diem.getMythuat(), diem.getTongdiem(), diem.getHocluc()
            });
        }
}
private void refresh_sk(){
    defaultTableModel2.setRowCount(0);
    
        List<suckhoe> suckhoe = skService.getAllSuckhoe();
               for (suckhoe sk : suckhoe) {
            defaultTableModel2.addRow(new Object[]{
                sk.getMahs(),sk.getTenhs(),sk.getChieucao(), sk.getCannang(),sk.getTrangthai()
            });
        }
}
private void refresh_tk(){
    defaultTableModeltk.setRowCount(0);
        String SQL="select * from tongdiem,hocsinh where tongdiem.mahs = hocsinh.mahs";
        List<Thongke> thongkes =  ThongkeService.getAlltk(SQL);
        for (Thongke tk : thongkes) {
            defaultTableModeltk.addRow(new Object[]{
                tk.getMahs(),tk.getTenhs(),tk.getLop(),tk.getGioitinh(),tk.getNgaysinh(),tk.getDiem(),tk.getXeploai()
            });
        }
}

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here:
        try{
         defaultTableModel = (DefaultTableModel) jTable1.getModel();
        String matre = defaultTableModel.getValueAt(jTable1.getSelectedRow(),0).toString();
        String tentre = defaultTableModel.getValueAt(jTable1.getSelectedRow(),1).toString();
        String lop = defaultTableModel.getValueAt(jTable1.getSelectedRow(),2).toString();
        String ngaysinh = defaultTableModel.getValueAt(jTable1.getSelectedRow(),3).toString();
        String gioitinh = defaultTableModel.getValueAt(jTable1.getSelectedRow(),4).toString();
        String diachi = defaultTableModel.getValueAt(jTable1.getSelectedRow(),5).toString();
        String dantoc = defaultTableModel.getValueAt(jTable1.getSelectedRow(),6).toString();
        String tongiao = defaultTableModel.getValueAt(jTable1.getSelectedRow(),7).toString();
       
            if(gioitinh.trim().equals("nam")){
                   jRadio_nam.setSelected(true);
               }
               else
                   jRadio_nu.setSelected(true);
//               jDate_ngaysinh.setDate(ngaysinh);
               jT_matre.setText(matre);
               jT_tentre.setText(tentre);
               jCbb_lop.setSelectedItem(lop);
               jDate_ngaysinh.setDateFormatString(ngaysinh.trim());
               jT_dc.setText(diachi);
               jT_dt.setText(dantoc);
               jT_tg.setText(tongiao);}catch(Exception e){}
    }//GEN-LAST:event_jTable1MouseClicked

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        int row = jTable1.getSelectedRow();
        if(row == -1){
            JOptionPane.showMessageDialog(Home.this, "vui long chon user truoc", "loi", JOptionPane.ERROR_MESSAGE);
        }else{
            int confirm = JOptionPane.showConfirmDialog(Home.this, "ban chac chan xoa khong? ");
            if (confirm == JOptionPane.YES_OPTION) {
                String masp = String.valueOf(String.valueOf(jTable1.getValueAt(row, 0)));
                try{
                diemService.deleteDiem(masp); 
                skService.deletesk(masp);}catch(Exception e){}
                userService.deleteUser(masp); 
                jCbb_matre.removeItem(masp);
                jCbb_matre_sk.removeItem(masp);
                
        }
       }
             refresh();
             refresh_QLdiem();
             refresh_sk();
             refresh_tk();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        sk.setMahs((String)jCbb_matre_sk.getSelectedItem());
        sk.setChieucao(Float.parseFloat(jT_chieucao.getText()));
        sk.setCannang(Float.parseFloat(jT_cannang.getText()));
        float sum=0;
        sum=((sk.getCannang()*10000)/(sk.getChieucao()*sk.getChieucao()));
        sk.setBIM(sum);
        if(sum<18.5){
            sk.setTrangthai("Gầy");
        }
        else if (sum>=18.5 &&sum <=24.9){
             sk.setTrangthai("Bình Thường");
        }
        else if (sum>=25.0 &&sum <=29.9){
             sk.setTrangthai("Thừa Cân");
        }
        
        skService.insertsk(sk); 
       refresh_sk();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jBt_xoa_dActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBt_xoa_dActionPerformed
        // TODO add your handling code here
         int row = jTable2.getSelectedRow();
        if(row == -1){
            JOptionPane.showMessageDialog(Home.this, "vui long chon user truoc", "loi", JOptionPane.ERROR_MESSAGE);
        }else{
            int confirm = JOptionPane.showConfirmDialog(Home.this, "ban chac chan xoa khong? ");
            if (confirm == JOptionPane.YES_OPTION) {
                String masp = String.valueOf(String.valueOf(jTable2.getValueAt(row, 0)));
                diemService.deleteDiem(masp); 
        }
       }
            refresh_QLdiem();
            refresh_tk();
    }//GEN-LAST:event_jBt_xoa_dActionPerformed

    private void jTable2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable2MouseClicked
        // TODO add your handling code here:
         defaultTableModel = (DefaultTableModel) jTable2.getModel();
        String mahs = defaultTableModel.getValueAt(jTable2.getSelectedRow(),0).toString();
        String toan = defaultTableModel.getValueAt(jTable2.getSelectedRow(),2).toString();
        String vannghe = defaultTableModel.getValueAt(jTable2.getSelectedRow(),3).toString();
        String theduc = defaultTableModel.getValueAt(jTable2.getSelectedRow(),4).toString();
        String mythuat = defaultTableModel.getValueAt(jTable2.getSelectedRow(),5).toString();
       
           
               jCbb_matre.setSelectedItem(mahs);
               jT_toan.setText(toan);
               jT_vannghe.setText(vannghe);
               jT_td.setText(theduc);
               jT_mythuat.setText(mythuat);
           
    }//GEN-LAST:event_jTable2MouseClicked

    private void jBt_sua_diemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBt_sua_diemActionPerformed
        // TODO add your handling code here:
        int row = jTable2.getSelectedRow();
        if(row == -1){
            JOptionPane.showMessageDialog(Home.this, "vui long chon user truoc", "loi", JOptionPane.ERROR_MESSAGE);
        }else{
            int confirm = JOptionPane.showConfirmDialog(Home.this, "ban chac chan muon suas khong? ");
            if (confirm == JOptionPane.YES_OPTION) {
                ql_diem.setMahs((String)jCbb_matre.getSelectedItem());
                ql_diem.setToan(Float.parseFloat(jT_toan.getText()));
                ql_diem.setVannghe(Float.parseFloat(jT_vannghe.getText()));
                ql_diem.setTheduc(Float.parseFloat(jT_td.getText()));
                ql_diem.setMythuat(Float.parseFloat(jT_mythuat.getText()));

                float td=0;
                td=(ql_diem.getToan()+ql_diem.getVannghe()+ql_diem.getTheduc()+ql_diem.getMythuat())/4;
                ql_diem.setTongdiem(td);

                if(td>=8.5){
                    ql_diem.setHocluc("Giỏi");

                }
                else if (td>=6.5){
                    ql_diem.setHocluc("Khá");
                }
                else if (td>=5){
                    ql_diem.setHocluc("Trung bình");
                }
                else{
                    ql_diem.setHocluc("Yếu");
                }
                diemService.updateDiem(ql_diem);
            }
        }
        refresh_QLdiem();
        refresh_tk();
    }//GEN-LAST:event_jBt_sua_diemActionPerformed

    private void jTable3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable3MouseClicked
        // TODO add your handling code here:
         defaultTableModel2 = (DefaultTableModel) jTable3.getModel();
        String mahs = defaultTableModel2.getValueAt(jTable3.getSelectedRow(),0).toString();
        String chieucao = defaultTableModel2.getValueAt(jTable3.getSelectedRow(),2).toString();
        String cannang = defaultTableModel2.getValueAt(jTable3.getSelectedRow(),3).toString();
               jCbb_matre_sk.setSelectedItem(mahs);
               jT_chieucao.setText(chieucao);
               jT_cannang.setText(cannang);
    }//GEN-LAST:event_jTable3MouseClicked

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here
         int row = jTable3.getSelectedRow();
        if(row == -1){
            JOptionPane.showMessageDialog(Home.this, "vui long chon user truoc", "loi", JOptionPane.ERROR_MESSAGE);
        }else{
            int confirm = JOptionPane.showConfirmDialog(Home.this, "ban chac chan xoa khong? ");
            if (confirm == JOptionPane.YES_OPTION) {
                String masp = String.valueOf(String.valueOf(jTable3.getValueAt(row, 0)));
                skService.deletesk(masp);
        }
       }
            refresh_sk();
    }//GEN-LAST:event_jButton6ActionPerformed

    private void sk_refreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sk_refreshActionPerformed
        // TODO add your handling code here:
        jT_chieucao.setText(null);
        jT_cannang.setText(null);
        jCbb_matre_sk.setSelectedItem(null);
    }//GEN-LAST:event_sk_refreshActionPerformed

    private void diem_refreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_diem_refreshActionPerformed
        // TODO add your handling code here:
        jCbb_matre.setSelectedItem(null);
        jT_toan.setText(null);
        jT_vannghe.setText(null);
        jT_td.setText(null);
        jT_mythuat.setText(null);
        
    }//GEN-LAST:event_diem_refreshActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        int row = jTable3.getSelectedRow();
        if(row == -1){
            JOptionPane.showMessageDialog(Home.this, "vui long chon user truoc", "loi", JOptionPane.ERROR_MESSAGE);
        }else{
            int confirm = JOptionPane.showConfirmDialog(Home.this, "ban chac chan muon sua khong? ");
            if (confirm == JOptionPane.YES_OPTION) {
                 sk.setMahs((String)jCbb_matre_sk.getSelectedItem());
        sk.setChieucao(Float.parseFloat(jT_chieucao.getText()));
        sk.setCannang(Float.parseFloat(jT_cannang.getText()));
        float sum=0;
        sum=((sk.getCannang()*10000)/(sk.getChieucao()*sk.getChieucao()));
        sk.setBIM(sum);
        if(sum<18.5){
            sk.setTrangthai("Gầy");
        }
        else if (sum>=18.5 &&sum <=24.9){
             sk.setTrangthai("Bình Thường");
        }
        else if (sum>=25.0 &&sum <=29.9){
             sk.setTrangthai("Thừa Cân");
        }
        skService.updatesk(sk);
            }
        }
        refresh_sk();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton_xuatfileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_xuatfileActionPerformed
        try {
 
            //Choosing Saving Location
            //Set default location to C:\Users\Authentic\Desktop or your preferred location
            JFileChooser excelFileChooser = new JFileChooser("C:\\Users\\Authentic\\Desktop");
            excelFileChooser.showSaveDialog(this);
            File saveFile=excelFileChooser.getSelectedFile();
            if(saveFile!=null){
                saveFile=new File(saveFile.toString()+".xlsx");
                Workbook wb = new SXSSFWorkbook();
                org.apache.poi.ss.usermodel.Sheet sheet = wb.createSheet("customer");
                 
                Row rowcol= sheet.createRow(0);
                 for (int i = 0; i < jTable_thongke.getColumnCount(); i++){
                     Cell cell=rowcol.createCell(i);
                     cell.setCellValue(jTable_thongke.getColumnName(i));
                     
                 }
                 for (int j = 0; j < jTable_thongke.getRowCount(); j++) {
                    Row row=sheet.createRow(j);
                    for (int k= 0; k < jTable_thongke.getColumnCount(); k++){
                        Cell cell = row.createCell(k);
                        if(jTable_thongke.getValueAt(j, k)!=null){
                            cell.setCellValue(jTable_thongke.getValueAt(j, k).toString());
                            
                        }
                        
                    }
                 }
                 FileOutputStream out = new FileOutputStream(new File(saveFile.toString()));
                 wb.write(out);
                 wb.close();
                 out.close();
            JOptionPane.showMessageDialog(this,"Xuất thành công","Thông báo", JOptionPane.INFORMATION_MESSAGE);
                 
                
            }else
            {
                
            }
        
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, ex);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, ex);
        } 
    
    
    }//GEN-LAST:event_jButton_xuatfileActionPerformed
    
    public void tk_tre(String ten){
//        SQL_tre = "select * from hocsinh where hoten like '% "+ten+"%'";
        defaultTableModel.setRowCount(0);
    
        String SQL_tre = "select * from hocsinh where hoten like '%"+ten+"%' or mahs like '%"+ten+"%'" ;
         List<User> users = userService.getAllUser(SQL_tre);
        for (User user : users) {
            defaultTableModel.addRow(new Object[]{
                user.getMahs(), user.getHoten(), user.getMalop(), user.getNgaysinh(), user.getGiotinh(), user.getDiachi(), user.getDantoc(), user.getTongiao()
            });
           
        }
    }
    private void jBt_tkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBt_tkActionPerformed
        String ten =jT_tk.getText();
        tk_tre(ten);
    }//GEN-LAST:event_jBt_tkActionPerformed

    private void jBt_tk_dActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBt_tk_dActionPerformed
        int dem=1;
          List<QL_DIEM> diems =diemService.getAllDiem();
          for (QL_DIEM diem : diems) {
             if(diem.getMahs().trim().equals(jT_tk_diem.getText().trim())) {
                 dem=dem+1;
                  jCbb_matre.setSelectedItem(diem.getMahs());
               jT_toan.setText(Float.toString(diem.getToan()));
               jT_vannghe.setText(Float.toString(diem.getVannghe()));
               jT_td.setText(Float.toString(diem.getTheduc()));
               jT_mythuat.setText(Float.toString(diem.getMythuat()));
              
                 break;
             }  
             }
          if(dem==1 && !jT_tk_diem.getText().isEmpty() ){
              JOptionPane.showMessageDialog(this,"Không có sv này","Thông báo", JOptionPane.INFORMATION_MESSAGE);
          }
          if( jT_tk_diem.getText().isEmpty() ){
              JOptionPane.showMessageDialog(this,"cần nhập mã cần tìm","Thông báo", JOptionPane.INFORMATION_MESSAGE);
          }
    }//GEN-LAST:event_jBt_tk_dActionPerformed

    private void jBt_tk_skActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBt_tk_skActionPerformed
       int dem=1;
          List<suckhoe> sks =skService.getAllSuckhoe();
          for (suckhoe sk : sks) {
             if(sk.getMahs().trim().equals(jT_tksk.getText().trim())) {
                 dem=dem+1;
                 jCbb_matre_sk.setSelectedItem(sk.getMahs());
               jT_chieucao.setText(Float.toString(sk.getChieucao()));
               jT_cannang.setText(Float.toString(sk.getCannang()));
              
                 break;
             }  
             }
          if(dem==1 && !jT_tksk.getText().isEmpty() ){
              JOptionPane.showMessageDialog(this,"Không có sv này","Thông báo", JOptionPane.INFORMATION_MESSAGE);
          }
          if( jT_tksk.getText().isEmpty() ){
              JOptionPane.showMessageDialog(this,"cần nhập mã cần tìm","Thông báo", JOptionPane.INFORMATION_MESSAGE);
          }
    }//GEN-LAST:event_jBt_tk_skActionPerformed

    private void jCbb_matreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCbb_matreActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jCbb_matreActionPerformed

    private void user_refreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_user_refreshActionPerformed
        // TODO add your handling code here:
        jT_matre.setText(null);
        jT_tentre.setText(null);

        //               jDate_ngaysinh.setDate(ngaysi
            jCbb_lop.setSelectedItem(null);
            jT_dc.setText(null);
            jT_dt.setText(null);
            jT_tg.setText(null);
            jT_tg.setText(null);
            
         defaultTableModel.setRowCount(0);
    
        String SQL_tre = "select * from hocsinh " ;
         List<User> users = userService.getAllUser(SQL_tre);
        for (User user : users) {
            defaultTableModel.addRow(new Object[]{
                user.getMahs(), user.getHoten(), user.getMalop(), user.getNgaysinh(), user.getGiotinh(), user.getDiachi(), user.getDantoc(), user.getTongiao()
            });
           
        }

    }//GEN-LAST:event_user_refreshActionPerformed
private void timkimtk(String tenhs , String lop){
    defaultTableModeltk.setRowCount(0);
    
        String SQL="select * from tongdiem,hocsinh where tongdiem.mahs = hocsinh.mahs and hoten like'%"+tenhs+"%' and malop like '%"+lop+"%'";
        List<Thongke> thongkes =  ThongkeService.getAlltk(SQL);
        for (Thongke tk : thongkes) {
            defaultTableModeltk.addRow(new Object[]{
                tk.getMahs(),tk.getTenhs(),tk.getLop(),tk.getGioitinh(),tk.getNgaysinh(),tk.getDiem(),tk.getXeploai()
            });
        }
}
   
    private void jTK_TKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTK_TKActionPerformed
       String maloptk = (String)jCBB_lop_tk.getSelectedItem();
       
        timkimtk(JT_tktk.getText(),maloptk);
    }//GEN-LAST:event_jTK_TKActionPerformed

    private void jCBB_lop_tkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCBB_lop_tkActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCBB_lop_tkActionPerformed

    private void jT_tgActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jT_tgActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jT_tgActionPerformed

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                
               //new Home().setExtendedState(JFrame.MAXIMIZED_BOTH);
               // new Home().setUndecorated(true);
                new Home().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField JT_tktk;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.ButtonGroup buttonGroup4;
    private javax.swing.ButtonGroup buttonGroup5;
    private javax.swing.ButtonGroup buttonGroup6;
    private javax.swing.JButton diem_refresh;
    private javax.swing.JButton jBt_sua_diem;
    private javax.swing.JButton jBt_them_diem;
    private javax.swing.JButton jBt_tk;
    private javax.swing.JButton jBt_tk_d;
    private javax.swing.JButton jBt_tk_sk;
    private javax.swing.JButton jBt_xoa_d;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton_xuatfile;
    private javax.swing.JComboBox jCBB_lop_tk;
    private com.toedter.calendar.JCalendar jCalendar1;
    private javax.swing.JComboBox<String> jCbb_lop;
    private javax.swing.JComboBox<String> jCbb_matre;
    private javax.swing.JComboBox<String> jCbb_matre_sk;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private com.toedter.calendar.JDateChooser jDate_ngaysinh;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JRadioButton jRadio_nam;
    private javax.swing.JRadioButton jRadio_nu;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JButton jTK_TK;
    private javax.swing.JTextField jT_cannang;
    private javax.swing.JTextField jT_chieucao;
    private javax.swing.JTextField jT_dc;
    private javax.swing.JTextField jT_dt;
    private javax.swing.JTextField jT_matre;
    private javax.swing.JTextField jT_mythuat;
    private javax.swing.JTextField jT_td;
    private javax.swing.JTextField jT_tentre;
    private javax.swing.JTextField jT_tg;
    private javax.swing.JTextField jT_tk;
    private javax.swing.JTextField jT_tk_diem;
    private javax.swing.JTextField jT_tksk;
    private javax.swing.JTextField jT_toan;
    private javax.swing.JTextField jT_vannghe;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable3;
    private javax.swing.JTable jTable_thongke;
    private javax.swing.JButton sk_refresh;
    private javax.swing.JButton user_refresh;
    // End of variables declaration//GEN-END:variables
}
