/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

import dao.ConnectionUtils;
import java.awt.Color;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.PhieuNhapClass;
import model.PhieuNhapCtr;

/**
 *
 * @author NQTrang
 */
public class QuanLyPN extends javax.swing.JFrame {

    private String maTK;
    private String loaiTK;
    /**
     * Creates new form QuanLyPN
     */
    Connection conn;
    PreparedStatement ps;
    ResultSet rs;
    String maNV = "";

    public QuanLyPN() {
        initComponents();
        KetNoiCSDL();
        Load_data_PN();
    }

    public QuanLyPN(String maTK, String loaiTK) {
        initComponents();
        this.maTK = maTK;
        this.loaiTK = loaiTK;
        this.setLocationRelativeTo(this);
        if (loaiTK.equals("quan ly") == false) {
            jCMaNV.setEditable(false);
        }
        KetNoiCSDL();
        Load_data_PN();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel4 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTListPN = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtMaPN = new javax.swing.JTextField();
        jDNgayNhap = new com.toedter.calendar.JDateChooser();
        jBThem = new javax.swing.JButton();
        jBXoa = new javax.swing.JButton();
        jBSua = new javax.swing.JButton();
        jBTimKiem = new javax.swing.JButton();
        jbTroLai = new javax.swing.JButton();
        txtTongTien = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jBLamMoi = new javax.swing.JButton();
        jBChiTiet = new javax.swing.JButton();
        jCMaNV = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        txtSearchMaPN = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel4.setBackground(new java.awt.Color(243, 231, 231));

        jPanel1.setBackground(new java.awt.Color(197, 110, 51));
        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("THÔNG TIN PHIẾU NHẬP");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(197, 110, 51));
        jPanel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("DANH SÁCH PHIẾU NHẬP");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 543, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jTListPN.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        jTListPN.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Mã phiếu nhập", "Ngày nhập ", "Mã nhân viên", "Tổng tiền"
            }
        ));
        jTListPN.setGridColor(new java.awt.Color(204, 204, 204));
        jTListPN.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTListPNMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTListPN);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 557, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 386, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jLabel4.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel4.setText("Mã phiếu nhập");

        jLabel5.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel5.setText("Ngày nhập");

        jLabel6.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel6.setText("Mã nhân viên");

        txtMaPN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMaPNActionPerformed(evt);
            }
        });

        jBThem.setBackground(new java.awt.Color(103, 242, 158));
        jBThem.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jBThem.setText("INSERT");
        jBThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBThemActionPerformed(evt);
            }
        });

        jBXoa.setBackground(new java.awt.Color(241, 72, 72));
        jBXoa.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jBXoa.setText("DELETE");
        jBXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBXoaActionPerformed(evt);
            }
        });

        jBSua.setBackground(new java.awt.Color(220, 220, 85));
        jBSua.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jBSua.setText("UPDATE");
        jBSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBSuaActionPerformed(evt);
            }
        });

        jBTimKiem.setBackground(new java.awt.Color(196, 101, 38));
        jBTimKiem.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jBTimKiem.setText("S");
        jBTimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBTimKiemActionPerformed(evt);
            }
        });

        jbTroLai.setBackground(new java.awt.Color(204, 101, 67));
        jbTroLai.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jbTroLai.setForeground(new java.awt.Color(255, 255, 255));
        jbTroLai.setText("TRỞ LẠI");
        jbTroLai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbTroLaiActionPerformed(evt);
            }
        });

        txtTongTien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTongTienActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel8.setText("Tổng tiền");

        jBLamMoi.setBackground(new java.awt.Color(204, 204, 204));
        jBLamMoi.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jBLamMoi.setText("REFRESH");
        jBLamMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBLamMoiActionPerformed(evt);
            }
        });

        jBChiTiet.setBackground(new java.awt.Color(196, 101, 38));
        jBChiTiet.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jBChiTiet.setText("Detail");
        jBChiTiet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBChiTietActionPerformed(evt);
            }
        });

        jCMaNV.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", " " }));
        jCMaNV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCMaNVActionPerformed(evt);
            }
        });

        jButton1.setText("+");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        txtSearchMaPN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSearchMaPNActionPerformed(evt);
            }
        });

        jLabel7.setText("<< SearchMaPN :");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jbTroLai))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel4Layout.createSequentialGroup()
                                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel4)
                                        .addComponent(jLabel5)
                                        .addComponent(jBThem))
                                    .addGap(23, 23, 23)
                                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(jPanel4Layout.createSequentialGroup()
                                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                .addComponent(jDNgayNhap, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(txtTongTien, javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel4Layout.createSequentialGroup()
                                                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel4Layout.createSequentialGroup()
                                                            .addComponent(txtMaPN, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addComponent(jCMaNV, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jBChiTiet, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                    .addComponent(txtSearchMaPN, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                    .addComponent(jBTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                        .addGroup(jPanel4Layout.createSequentialGroup()
                                            .addComponent(jBXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(18, 18, 18)
                                            .addComponent(jBSua)
                                            .addGap(32, 32, 32)
                                            .addComponent(jBLamMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                            .addComponent(jLabel8)
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jbTroLai)
                        .addGap(7, 7, 7))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtMaPN, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jBTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4)
                            .addComponent(jButton1)
                            .addComponent(txtSearchMaPN, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7))
                        .addGap(31, 31, 31)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jDNgayNhap, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(36, 36, 36)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(jCMaNV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(36, 36, 36)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(txtTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jBChiTiet, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(52, 52, 52)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jBThem, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jBXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jBSua, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jBLamMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        jPanel3.setBackground(new java.awt.Color(241, 202, 164));

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("QUẢN LÝ PHIẾU NHẬP");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public void KetNoiCSDL() {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");   //Load lớp Oracle JDBC driver bằng cách sử dụng phương thức static `forName()` của lớp Class
            System.out.print("Ket noi thanh cong");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(QuanLyPN.class.getName()).log(Level.SEVERE, null, ex);    //Xử lý ngoại lệ ClassNotFoundException và hiển thị chi tiết lỗi lên bảng điều khiển
        }   // Bắt đầu khối try-catch để bắt ngoại lệ ClassNotFoundException, trong trường hợp không tìm thấy lớp jdbc driver để kết nối với cơ sở dữ liệu
        try {

            conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl21", "PrivateTeam", "12345678");
            //Thiết lập cấu trúc URL của cơ sở dữ liệu và bắt đầu kết nối đến cơ sở dữ liệu sử dụng tên người dùng và mật khẩu tương ứng
        } catch (SQLException ex) {
            Logger.getLogger(QuanLyPN.class.getName()).log(Level.SEVERE, null, ex);
        }   //  Bắt đầu khối try-catch để bắt ngoại lệ SQLException, trong trường hợp kết nối cơ sở dữ liệu thất bại

    }

    public void Load_data_PN() {
        try {
            boolean flag = true;
            if (loaiTK.equals("quan ly"))   // Nếu là "quan ly", thực hiện truy vấn SQL để lấy toàn bộ thông tin phiếu nhập sách
            {
                ps = conn.prepareStatement("SELECT * FROM PHIEUNHAP ");
            } else   //thực hiện truy vấn SQL để lấy thông tin phiếu nhập sách chỉ liên quan đến tài khoản người dùng đang đăng nhập
            {
                ps = conn.prepareStatement("SELECT PN.MaPN, PN.NgayNhap, PN.MaNV, PN.TONGTIEN FROM PHIEUNHAP PN join NHANVIEN NV ON PN.MANV = NV.MANV JOIN TAIKHOAN TK on NV.MaNV = TK.MANV where MaTK = " + maTK);
                flag = false;
            }
            rs = ps.executeQuery();   //Thực thi truy vấn SQL và lấy dữ liệu trả về

            ResultSetMetaData rsd = rs.getMetaData();    //Lấy thông tin về các cột của bảng dữ liệu
            int c = rsd.getColumnCount();    //Lấy số lượng cột của bảng dữ liệu

            DefaultTableModel model = (DefaultTableModel) jTListPN.getModel();    //Lấy mô hình bảng danh sách phiếu nhập sách từ giao diện `QuanLyPN`
            model.setRowCount(0);  //Xóa toàn bộ dữ liệu trong bảng danh sách phiếu nhập sách
            while (rs.next()) {
                Vector v1 = new Vector();   //Khởi tạo một vector `v1` để lưu thông tin của một phiếu nhập sách
                for (int i = 1; i <= c; i++)  //Duyệt qua từng cột của bảng dữ liệu và thêm dữ liệu vào vector `v1`
                {
                    if (flag == false) {
                        maNV = rs.getString("MaNV");   // lấy mã nhân viên của người dùng đang đăng nhập
                    }
                    v1.add(rs.getString("MaPN"));
                    v1.add(rs.getDate("NgayNhap"));
                    v1.add(rs.getString("MaNV"));
                    v1.add(rs.getString("TongTien"));
                }
                model.addRow(v1);  //Thêm vector `v1` vào `DefaultTableModel
                jTListPN.setModel(model);  // Cập nhật lại bảng danh sách phiếu nhập sách
            }
        } catch (SQLException ex) {
            Logger.getLogger(QuanLyPN.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void LamMoi() {
        //làm mới các trường nhập liệu
        txtMaPN.setText("");
        jDNgayNhap.setDate(null);
        jCMaNV.setSelectedItem(null);
        txtTongTien.setText("");
    }
    private void jTListPNMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTListPNMouseClicked
        // đoạn code này dùng để hiển thị thông tin phiếu nhập khi người dùng click chuột vào một dòng trong bảng jTListPN
        try {
            LamMoi(); //làm mới các trường nhập liệu và các nút trên giao diện `QuanLyPN`
            int selectedIndex = jTListPN.getSelectedRow();  // Lấy chỉ số của dòng được chọn trong bảng `jTListPN`
            jTListPN.setColumnSelectionInterval(0, 3);   //Thiết lập cột được chọn trong bảng `jTListPN` từ cột 0 đến cột 3
            //hiển thị các giá trị 
            txtMaPN.setText(jTListPN.getValueAt(selectedIndex, 0).toString());
            jDNgayNhap.setDate((java.util.Date) jTListPN.getValueAt(selectedIndex, 1));
            String manv= jTListPN.getValueAt(selectedIndex,2).toString();
            if (manv.equals("1")) {
                jCMaNV.setSelectedItem("1");
            } else if (manv.equals("2")) {
                jCMaNV.setSelectedItem("2");
            } else if (manv.equals("3")) {
                jCMaNV.setSelectedItem("3");
            } else if (manv.equals("4")) {
                jCMaNV.setSelectedItem("4");

            } else {
                jCMaNV.setSelectedItem(null);
            }

            txtTongTien.setText(jTListPN.getValueAt(selectedIndex, 3).toString());

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Lỗi : " + e.getMessage());
        }
    }//GEN-LAST:event_jTListPNMouseClicked

    private void txtMaPNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMaPNActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMaPNActionPerformed

    private void jBThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBThemActionPerformed
        // Đoạn code này được sử dụng để thêm một phiếu nhập mới vào cơ sở dữ liệu 
        StringBuilder sb = new StringBuilder();  //Khởi tạo một đối tượng `StringBuilder` để xây dựng thông báo lỗi nếu có lỗi nhập liệu từ người dùng
        if (txtMaPN.getText().equals(""))  //Kiểm tra nếu mã phiếu nhập để trống, thì thêm thông báo lỗi vào `StringBuilder
        {
            sb.append("!!Mã phiếu nhập không được để trống!!");
            txtMaPN.setBackground(Color.red);
        } else {
            txtMaPN.setBackground(Color.white);
        }
        if (sb.length() > 0)  //Kiểm tra nếu `StringBuilder` lưu trữ thông báo lỗi có độ dài lớn hơn 0, thì hiển thị thông báo lỗi đó và không thực hiện thêm phiếu nhập mới vào cơ sở dữ liệu
        {
            JOptionPane.showMessageDialog(this, sb);
            return;
        }
        try {
            PhieuNhapClass pn = new PhieuNhapClass();
            //thiết lập các giá trị 
            pn.setMaPN(txtMaPN.getText());
            if (jDNgayNhap.getDate() != null) {
                java.util.Date ngn = jDNgayNhap.getDate();
                java.sql.Date sqlbd = new java.sql.Date(ngn.getTime()); //chuyển đổi giá trị của trường "jDNgayNhap" từ định dạng `java.util.Date` sang định dạng `java.sql.Date`
                pn.setNgayNhap(sqlbd);
            }
            
            pn.setMaNV(jCMaNV.getSelectedItem().toString());
            if (txtTongTien.getText().equals("")) {
                pn.setTongTien("0");
            } else {
                pn.setTongTien(txtTongTien.getText());
            }
            PhieuNhapCtr.InsertPN(pn);  //Gọi phương thức `InsertPN()` trong lớp `PhieuNhapCtr` để thêm đối tượng `PhieuNhapClass` vừa tạo vào cơ sở dữ liệu
            JOptionPane.showMessageDialog(this, "Đã thêm thành công.");   //Hiển thị thông báo xác nhận nếu thêm phiếu nhập mới thành công
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error : " + e.getMessage());    //Hiển thị thông báo lỗi và in ra thông tin chi tiết về lỗi nếu có lỗi khi thêm phiếu nhập mới
            e.printStackTrace();
        }
    }//GEN-LAST:event_jBThemActionPerformed

    private void jBXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBXoaActionPerformed
        // Đoạn code này được sử dụng để xóa một phiếu nhập trong cơ sở dữ liệu
        StringBuilder sb = new StringBuilder();
        if (txtMaPN.getText().equals("")) {
            sb.append("!!Mã phiếu nhập không được để trống!!");
            txtMaPN.setBackground(Color.red);
        } else {
            txtMaPN.setBackground(Color.white);
        }
        if (sb.length() > 0) {
            JOptionPane.showMessageDialog(this, sb);
            return;
        }
        try {
            int result = JOptionPane.showConfirmDialog(null, "Bạn có muốn xóa phiếu nhập không?", "Xóa", JOptionPane.YES_NO_OPTION);
            if (result == JOptionPane.YES_OPTION)   //Hiển thị hộp thoại xác nhận để xác định xem người dùng có muốn xóa phiếu nhập hay không
            {
                PhieuNhapCtr.DeletePN(txtMaPN.getText());  //gọi phương thức `DeletePN()` trong lớp `PhieuNhapCtr` để xóa nhà cung cấp tương ứng khỏi cơ sở dữ liệu
                JOptionPane.showMessageDialog(this, "Đã xóa phiếu nhập thành công");
            } else {
                JOptionPane.showMessageDialog(this, "phiếu nhập chưa được xóa");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error : " + e.getMessage());
            e.printStackTrace();
        }

    }//GEN-LAST:event_jBXoaActionPerformed

    private void jBSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBSuaActionPerformed
        // Đoạn code này được sử dụng để cập nhật một phiếu nhập trong cơ sở dữ liệu
        StringBuilder sb = new StringBuilder();
        if (txtMaPN.getText().equals("")) {
            sb.append("!!Mã phiếu nhập không được để trống!!");
            txtMaPN.setBackground(Color.red);
        } else {
            txtMaPN.setBackground(Color.white);
        }
        if (sb.length() > 0) {
            JOptionPane.showMessageDialog(this, sb);
            return;
        }
        try {
            PhieuNhapClass pn = new PhieuNhapClass();
            pn.setMaPN(txtMaPN.getText());
            if (jDNgayNhap.getDate() != null) {
                java.util.Date ngn = jDNgayNhap.getDate();
                java.sql.Date sqlbd = new java.sql.Date(ngn.getTime());  //chuyển đổi giá trị của trường "jDNgayNhap" từ định dạng `java.util.Date` sang định dạng `java.sql.Date`
                pn.setNgayNhap(sqlbd);
            }
            pn.setMaNV(jCMaNV.getSelectedItem().toString());
            pn.setTongTien(txtTongTien.getText());
            int result = JOptionPane.showConfirmDialog(null, "Bạn có muốn cập nhật phiếu nhập không?", "cập nhật", JOptionPane.YES_NO_OPTION);
            if (result == JOptionPane.YES_OPTION) {
                PhieuNhapCtr.UpdatePN(pn);  //Gọi phương thức `UpdatePN()` trong lớp `PhieuNhapCtr` để cập nhật đối tượng `PhieuNhapClass` vừa tạo vào cơ sở dữ liệu
                JOptionPane.showMessageDialog(this, "Đã Cập nhật thành công.");
            } else {
                JOptionPane.showMessageDialog(this, "Phiếu nhập chưa được cập nhật");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error : " + e.getMessage());
            e.printStackTrace();
        }

    }//GEN-LAST:event_jBSuaActionPerformed

    private void jBTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBTimKiemActionPerformed
        // Đoạn code này dùng để tìm kiếm một phiếu nhập trong cơ sở dữ liệu
        StringBuilder sb = new StringBuilder();
        if (txtSearchMaPN.getText().equals("")) {
            sb.append("Mã phiếu nhập không được để trống!");
            txtSearchMaPN.setBackground(Color.red);
        } else {
            txtSearchMaPN.setBackground(Color.white);
        }
        if (sb.length() > 0) {
            JOptionPane.showMessageDialog(this, sb);
            return;
        }
        try {
            PhieuNhapCtr pnc = new PhieuNhapCtr();
            PhieuNhapClass nv = pnc.find(txtSearchMaPN.getText());  //sử dụng phương thức `find()` để tìm kiếm phiếu nhập có mã `txtMaPN` trong cơ sở dữ liệu
            if (nv != null)   //Kiểm tra xem phiếu nhập được tìm thấy hay không
            {
                //hiển thị thông tin của phiếu nhập
                int selectedIndex = jTListPN.getSelectedRow();
                txtMaPN.setText(nv.getMaPN());

                jDNgayNhap.setDate(nv.getNgayNhap());

                jCMaNV.setSelectedItem(nv.getMaNV());

                txtTongTien.setText(nv.getTongTien());
            } else {
                JOptionPane.showMessageDialog(this, "Phiếu nhập tìm kiếm không tồn tại!");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
            e.printStackTrace();
        }
    }//GEN-LAST:event_jBTimKiemActionPerformed

    private void jbTroLaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbTroLaiActionPerformed
        // TODO add your handling code here:
        QuanLy ql = new QuanLy(maTK, loaiTK); //khởi tạo đối tượng QuanLy
        ql.setVisible(true); //hiển thị giao diện quản lý
        this.dispose(); //đóng giao diện hiện tại
    }//GEN-LAST:event_jbTroLaiActionPerformed

    private void jBLamMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBLamMoiActionPerformed
        // đoạn code này dùng để làm mới dữ liệu trên bảng danh sách phiếu nhập và các trường nhập liệu
        LamMoi();  //làm mới các trường nhập liệu
        Load_data_PN();  //tải lại dữ liệu danh sách tài khoản từ cơ sở dữ liệu và hiển thị lên bảng danh sách

    }//GEN-LAST:event_jBLamMoiActionPerformed

    private void jBChiTietActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBChiTietActionPerformed
        // Đoạn code này được sử dụng để xử lý sự kiện khi người dùng nhấp vào nút "Detail" trên giao diện
        String maPN = txtMaPN.getText();  //Lấy mã phiếu nhập sách từ trường "txtMaPN"
        CTPN ct = new CTPN(maNV, maTK, loaiTK, maPN);  //Khởi tạo một đối tượng `CTPN` (Giao diện Chi tiết phiếu nhập sách)
        ct.setVisible(true);  //Hiển thị giao diện "Chi tiết phiếu nhập"
        this.dispose(); //đóng giao diện hiện tại
    }//GEN-LAST:event_jBChiTietActionPerformed

    private void txtTongTienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTongTienActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTongTienActionPerformed

    private void jCMaNVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCMaNVActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCMaNVActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // Đoạn code này được sử dụng để tạo một mã phiếu nhập mới và hiển thị mã đó lên trường nhập liệu "txtMaPN"
        try (java.sql.Connection con = ConnectionUtils.getMyConnection()) //Tạo một đối tượng `Connection` để kết nối đến cơ sở dữ liệu
        {
            String SQL = "select insert_new_MaPN() as newMaPN from dual";  //Tạo câu truy vấn SQL để tìm kiếm một mã phiếu nhập mới
            PreparedStatement ps = con.prepareStatement(SQL);  //Tạo một đối tượng `PreparedStatement` để chuẩn bị câu truy vấn
            ResultSet rs = ps.executeQuery();  //Thực thi câu truy vấn và lưu kết quả vào đối tượng `ResultSet` để xử lý
            if (rs.next())   //Kiểm tra xem có kết quả trả về từ cơ sở dữ liệu hay không
            {
                txtMaPN.setText(rs.getString("NEWMAPN"));   //Lấy giá trị của cột "newMaPN" trong dòng kết quả của `ResultSet` và hiển thị giá trị đó lên trường nhập liệu "txtMaPN"
            }
        } catch (SQLException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void txtSearchMaPNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSearchMaPNActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSearchMaPNActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String maTK, String loaiTK) {
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
            java.util.logging.Logger.getLogger(QuanLyPN.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(QuanLyPN.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(QuanLyPN.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(QuanLyPN.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new QuanLyPN(maTK, loaiTK).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBChiTiet;
    private javax.swing.JButton jBLamMoi;
    private javax.swing.JButton jBSua;
    private javax.swing.JButton jBThem;
    private javax.swing.JButton jBTimKiem;
    private javax.swing.JButton jBXoa;
    private javax.swing.JButton jButton1;
    private javax.swing.JComboBox<String> jCMaNV;
    private com.toedter.calendar.JDateChooser jDNgayNhap;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTListPN;
    private javax.swing.JButton jbTroLai;
    private javax.swing.JTextField txtMaPN;
    private javax.swing.JTextField txtSearchMaPN;
    private javax.swing.JTextField txtTongTien;
    // End of variables declaration//GEN-END:variables
}
