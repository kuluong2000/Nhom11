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
        List<User> users = userService.getAllUser();
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
        List<Thongke> thongkes =  ThongkeService.getAlltk();
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
        jDate_ngaysinh = new com.toedter.calendar.JDateChooser();
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
        diem_refresh = new javax.swing.JButton();
        jBt_tk_d = new javax.swing.JButton();
        jT_tk_diem = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
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
        jPanel3 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable_thongke = new javax.swing.JTable();
        jButton_xuatfile = new javax.swing.JButton();
        jLabel32 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        jMenu3 = new javax.swing.JMenu();
        jMenu4 = new javax.swing.JMenu();
        jMenu5 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setFont(new java.awt.Font("Times New Roman", 0, 15)); // NOI18N

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 51, 51));
        jLabel1.setText("QUẢN LÝ NHÀ TRẺ, MẦM NON");
        jLabel1.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/logo.png"))); // NOI18N

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel2.setText("Nhóm 11:");

        jLabel4.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel4.setText("Hồ Thị Tuyết");

        jLabel5.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel5.setText("Trần Hữu Lương");

        jLabel6.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel6.setText("Ngô Thanh Tuấn");

        jLabel7.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel7.setText("Nguyễn Thị Diệu Ý");

        jLabel8.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel8.setText("Dương Thị Thùy Dung");

        jLabel9.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel9.setText("GVHD:");

        jLabel10.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel10.setText("Đỗ Phú Huy");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(257, 257, 257)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 550, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addComponent(jLabel3)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(223, 223, 223)
                                .addComponent(jLabel2)
                                .addGap(30, 30, 30)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel8)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(249, 249, 249)
                                .addComponent(jLabel9)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel10)))))
                .addContainerGap(280, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel3))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(85, 85, 85)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel7)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel8)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jLabel10))
                .addContainerGap(53, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("WELLCOME", jPanel1);

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/logo.png"))); // NOI18N

        jLabel15.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel15.setText("Mã trẻ:");

        jLabel16.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel16.setText("Mã lớp:");

        jLabel17.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel17.setText("Họ tên:");

        jLabel18.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel18.setText("Giới tính:");

        jLabel19.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel19.setText("Ngày sinh:");

        jLabel20.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel20.setText("Địa chỉ:");

        jLabel21.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel21.setText("Dân tộc:");

        jLabel22.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel22.setText("Tôn giáo");

        jCbb_lop.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "08M2      ", "08M3      ", "08M4      ", "08M1      " }));
        jCbb_lop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCbb_lopActionPerformed(evt);
            }
        });

        buttonGroup1.add(jRadio_nam);
        jRadio_nam.setText("Nam");

        buttonGroup1.add(jRadio_nu);
        jRadio_nu.setText("Nữ");

        jDate_ngaysinh.setDate(new java.util.Date(1620960876000L));
        jDate_ngaysinh.setDateFormatString(" dd-MM-YYYY");

        jT_dc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jT_dcActionPerformed(evt);
            }
        });

        jT_tentre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jT_tentreActionPerformed(evt);
            }
        });

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

        jButton1.setText("Thêm");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Sửa");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Xóa");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        user_refresh.setText("refresh");
        user_refresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                user_refreshActionPerformed(evt);
            }
        });

        jLabel33.setText("Nhập mã cần tìm:");

        jBt_tk.setText("Tìm kiếm");
        jBt_tk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBt_tkActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 849, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(178, 178, 178))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel18)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(106, 106, 106)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel15)
                                    .addComponent(jLabel16)
                                    .addComponent(jLabel17)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton1)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jCbb_lop, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jT_tentre, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jRadio_nam)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jRadio_nu))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(jButton2)
                        .addGap(18, 18, 18)
                        .addComponent(jButton3))
                    .addComponent(jT_matre, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(17, 17, 17)
                                .addComponent(jLabel20))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel19)
                                    .addComponent(jLabel21, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel22, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jT_dc)
                                    .addComponent(jT_dt)
                                    .addComponent(jT_tg)
                                    .addComponent(jDate_ngaysinh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel33)
                                        .addGap(18, 18, 18)
                                        .addComponent(jT_tk, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE))))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(user_refresh)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jBt_tk)
                .addGap(140, 140, 140))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel15)
                            .addComponent(jT_matre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(9, 9, 9)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel17)
                            .addComponent(jT_tentre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel16)
                            .addComponent(jCbb_lop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel18)
                            .addComponent(jRadio_nam)
                            .addComponent(jRadio_nu))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton1)
                            .addComponent(jButton2)
                            .addComponent(jButton3)
                            .addComponent(user_refresh)
                            .addComponent(jLabel33)
                            .addComponent(jT_tk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jBt_tk)))
                    .addComponent(jLabel19)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addGap(28, 28, 28)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel20)
                                    .addComponent(jT_dc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jDate_ngaysinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel21)
                            .addComponent(jT_dt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(16, 16, 16)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel22)
                            .addComponent(jT_tg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 168, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("QUẢN LÝ TRẺ", jPanel2);

        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/logo.png"))); // NOI18N

        jLabel23.setFont(new java.awt.Font("Times New Roman", 1, 36)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(255, 51, 51));
        jLabel23.setText("QUẢN LÝ NHÀ TRẺ, MẦM NON");
        jLabel23.setVerticalAlignment(javax.swing.SwingConstants.TOP);

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

        jLabel24.setText("Mã Trẻ: ");

        jCbb_matre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCbb_matreActionPerformed(evt);
            }
        });

        jLabel25.setText("Điểm Môn Toán:");

        jLabel26.setText("Mỹ thuật:");

        jLabel27.setText("Thể dục:");

        jLabel28.setText("Văn Nghệ:");

        jT_toan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jT_toanActionPerformed(evt);
            }
        });

        jBt_them_diem.setText("Thêm");
        jBt_them_diem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBt_them_diemActionPerformed(evt);
            }
        });

        jBt_sua_diem.setText("Sửa");
        jBt_sua_diem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBt_sua_diemActionPerformed(evt);
            }
        });

        jBt_xoa_d.setText("Xóa");
        jBt_xoa_d.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBt_xoa_dActionPerformed(evt);
            }
        });

        diem_refresh.setText("refresh");
        diem_refresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                diem_refreshActionPerformed(evt);
            }
        });

        jBt_tk_d.setText("Tim kiếm");
        jBt_tk_d.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBt_tk_dActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jLabel13)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(70, 70, 70)
                        .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 550, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(63, 63, 63)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel25)
                            .addComponent(jLabel24)
                            .addComponent(jLabel26))
                        .addGap(28, 28, 28)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jCbb_matre, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jT_mythuat, javax.swing.GroupLayout.DEFAULT_SIZE, 83, Short.MAX_VALUE)
                                    .addComponent(jT_toan))
                                .addGap(48, 48, 48)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel27, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel28))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jT_vannghe, javax.swing.GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE)
                                    .addComponent(jT_td)))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jT_tk_diem, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jBt_tk_d)))
                        .addGap(88, 88, 88)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jBt_sua_diem)
                            .addComponent(jBt_them_diem)
                            .addComponent(jBt_xoa_d)
                            .addComponent(diem_refresh))))
                .addContainerGap(232, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 623, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(180, 180, 180))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addComponent(jLabel13))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel24)
                                            .addComponent(jCbb_matre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel25)
                                            .addComponent(jLabel27)))
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addComponent(jBt_them_diem)
                                        .addGap(20, 20, 20)
                                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jT_td, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jBt_sua_diem)))))
                            .addComponent(jT_toan, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel26)
                                .addComponent(jLabel28)
                                .addComponent(jT_mythuat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jT_vannghe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jBt_xoa_d))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(diem_refresh)
                                .addComponent(jBt_tk_d))
                            .addComponent(jT_tk_diem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19))
        );

        jTabbedPane1.addTab("QUẢN LÝ ĐIỂM", jPanel4);

        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/logo.png"))); // NOI18N

        jLabel12.setFont(new java.awt.Font("Times New Roman", 1, 36)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 51, 51));
        jLabel12.setText("QUẢN LÝ NHÀ TRẺ, MẦM NON");
        jLabel12.setVerticalAlignment(javax.swing.SwingConstants.TOP);

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

        jLabel29.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel29.setText("Mã Trẻ:");

        jLabel30.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel30.setText("Chiều Cao:");

        jLabel31.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel31.setText("Cân Nặng:");

        jT_cannang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jT_cannangActionPerformed(evt);
            }
        });

        jButton4.setText("Thêm");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setText("Sửa");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setText("Xóa");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        sk_refresh.setText("refresh");
        sk_refresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sk_refreshActionPerformed(evt);
            }
        });

        jBt_tk_sk.setText("Tìm kiếm");
        jBt_tk_sk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBt_tk_skActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jLabel14)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(76, 76, 76)
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 550, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(100, 100, 100)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel29)
                            .addComponent(jLabel30)
                            .addComponent(jLabel31))
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addComponent(jCbb_matre_sk, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jT_cannang, javax.swing.GroupLayout.DEFAULT_SIZE, 152, Short.MAX_VALUE)
                                    .addComponent(jT_chieucao)
                                    .addComponent(jT_tksk))))
                        .addGap(41, 41, 41)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jBt_tk_sk)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jButton4)
                                .addGap(32, 32, 32)
                                .addComponent(sk_refresh))
                            .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(226, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(265, 265, 265))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addComponent(jLabel14)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jCbb_matre_sk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel29)
                            .addComponent(jButton4)
                            .addComponent(sk_refresh))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel30)
                        .addGap(7, 7, 7))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jT_chieucao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel31)
                    .addComponent(jT_cannang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jT_tksk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jBt_tk_sk))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 19, Short.MAX_VALUE)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32))
        );

        jTabbedPane1.addTab("QUẢN LÝ SỨC KHỎE", jPanel5);

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

        jButton_xuatfile.setText("Xuất file excel");
        jButton_xuatfile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_xuatfileActionPerformed(evt);
            }
        });

        jLabel32.setFont(new java.awt.Font("Times New Roman", 1, 36)); // NOI18N
        jLabel32.setForeground(new java.awt.Color(255, 51, 51));
        jLabel32.setText("QUẢN LÝ NHÀ TRẺ, MẦM NON");
        jLabel32.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton_xuatfile)
                .addGap(289, 289, 289))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(119, 119, 119)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 731, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(157, 157, 157)
                        .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 550, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(237, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton_xuatfile)
                .addGap(34, 34, 34)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(56, 56, 56))
        );

        jTabbedPane1.addTab("THỐNG KÊ", jPanel3);

        jMenuBar1.setFont(new java.awt.Font("Times New Roman", 0, 15)); // NOI18N

        jMenu1.setText("HOME");
        jMenu1.setFont(new java.awt.Font("Times New Roman", 0, 15)); // NOI18N
        jMenuBar1.add(jMenu1);

        jMenu2.setText("Quản Lý Doanh Mục");
        jMenu2.setFont(new java.awt.Font("Times New Roman", 0, 15)); // NOI18N
        jMenuBar1.add(jMenu2);

        jMenu3.setText("Tìm Kiếm");
        jMenu3.setFont(new java.awt.Font("Times New Roman", 0, 15)); // NOI18N
        jMenuBar1.add(jMenu3);

        jMenu4.setText("Đăng xuất");
        jMenu4.setFont(new java.awt.Font("Times New Roman", 0, 15)); // NOI18N
        jMenuBar1.add(jMenu4);

        jMenu5.setText("Help");
        jMenu5.setFont(new java.awt.Font("Times New Roman", 0, 15)); // NOI18N
        jMenuBar1.add(jMenu5);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1092, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 20, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(60, Short.MAX_VALUE)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 437, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

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

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        try{
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
        }catch(Exception e){
            
        }
        if(jT_matre.getText().isEmpty() || jT_tentre.getText().isEmpty()){
            JOptionPane.showMessageDialog(this,"Không được để trống","Thông báo", JOptionPane.INFORMATION_MESSAGE);
        }
         int dem=1;
          List<User> users =  userService.getAllUser();
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
    
        List<User> users = userService.getAllUser();
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
    
        List<Thongke> thongkes =  ThongkeService.getAlltk();
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

    private void user_refreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_user_refreshActionPerformed
        // TODO add your handling code here:
        jT_matre.setText(null);
        jT_tentre.setText(null);
        
//               jDate_ngaysinh.setDate(ngaysi
       jCbb_lop.setSelectedItem(null);
        jT_dc.setText(null);
        jT_dt.setText(null);
        jT_tg.setText(null);
    }//GEN-LAST:event_user_refreshActionPerformed

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

    private void jBt_tkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBt_tkActionPerformed
         int dem=1;
          List<User> users =  userService.getAllUser();
          for (User user : users) {
             if(user.getMahs().trim().equals(jT_tk.getText().trim())) {
                 dem=dem+1;
                  jT_matre.setText(user.getMahs());
                if(user.getGiotinh().equals("nam")){
                   jRadio_nam.setSelected(true);
               }
               else
                   jRadio_nu.setSelected(true);
              
               jT_tentre.setText(user.getHoten());
               jCbb_lop.setSelectedItem(user.getMalop());
               jDate_ngaysinh.setDateFormatString(user.getNgaysinh());
               jT_dc.setText(user.getDiachi());
               jT_dt.setText(user.getDantoc());
               jT_tg.setText(user.getTongiao());
              
                 break;
             }  
             }
          if(dem==1 && !jT_tk.getText().isEmpty() ){
              JOptionPane.showMessageDialog(this,"Không có sv này","Thông báo", JOptionPane.INFORMATION_MESSAGE);
          }
          if( jT_tk.getText().isEmpty() ){
              JOptionPane.showMessageDialog(this,"cần nhập mã cần tìm","Thông báo", JOptionPane.INFORMATION_MESSAGE);
          }
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
                new Home().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
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
    private javax.swing.JComboBox<String> jCbb_lop;
    private javax.swing.JComboBox<String> jCbb_matre;
    private javax.swing.JComboBox<String> jCbb_matre_sk;
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
    private javax.swing.JLabel jLabel4;
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
