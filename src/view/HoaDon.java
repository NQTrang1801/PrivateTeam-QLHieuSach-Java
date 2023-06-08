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
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.HoaDonClass;
import model.HoaDonCtr;

/**
 *
 * @author NQTrang
 */

public class HoaDon extends javax.swing.JFrame {

    private String maTK;
    private String loaiTK;
    Connection conn;
    PreparedStatement ps;
    ResultSet rs;
    String maNV = "";

    public HoaDon() {
        initComponents();
        KetNoiCSDL();
        Load_data_HD();
    }

    public HoaDon(String maTK, String loaiTK) {
        initComponents();
        this.setLocationRelativeTo(this);
        this.maTK = maTK;
        this.loaiTK = loaiTK;
        if (loaiTK.equals("quan ly") == false) {
            txtMaNV.setEditable(false);
            txtMaNV.setEnabled(false);
        }
        txtThanhTien.setEditable(false);
        txtThanhTien.setEnabled(false);
        txtMaHD.setEditable(false);
        txtMaHD.setEnabled(false);
        KetNoiCSDL();
        Load_data_HD();
    }

    public void KetNoiCSDL() {
        try {
            conn = dao.ConnectionOracle.getOracleConnection();
            //Thiết lập cấu trúc URL của cơ sở dữ liệu và bắt đầu kết nối đến cơ sở dữ liệu sử dụng tên người dùng và mật khẩu tương ứng
        } catch (SQLException ex) {
            Logger.getLogger(CTPN.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CTPN.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void Load_data_HD() {
        try {
            boolean flag = true;
            if (loaiTK.equals("quan ly")) // Nếu là "quan ly", thực hiện truy vấn SQL để lấy toàn bộ thông tin hóa đơn sách
            {
                ps = conn.prepareStatement("SELECT * FROM HOADON ");
            } else //thực hiện truy vấn SQL để lấy thông tin phiếu nhập sách chỉ liên quan đến tài khoản người dùng đang đăng nhập
            { // user nhan vien
                ps = conn.prepareStatement("SELECT MaHD, NgayHD, HD.MaNV, MaKH, ThanhTien FROM HOADON HD join TAIKHOAN TK on HD.MaNV = TK.MANV where MaTK = " + maTK);
                flag = false;
            }
            rs = ps.executeQuery();  //Thực thi truy vấn SQL và lấy dữ liệu trả về

            ResultSetMetaData rsd = rs.getMetaData();    //Lấy thông tin về các cột của bảng dữ liệu
            int c = rsd.getColumnCount();    //Lấy số lượng cột của bảng dữ liệu

            DefaultTableModel model = (DefaultTableModel) jTListHD.getModel();    //Lấy mô hình bảng danh sách hóa đơn sách từ giao diện `HoaDon`
            model.setRowCount(0);  //Xóa toàn bộ dữ liệu trong bảng danh sách Hóa đơn 

            while (rs.next()) {
                Vector v1 = new Vector();  //Khởi tạo một vector `v1` để lưu thông tin của một hóa đơn
                for (int i = 1; i <= c; i++) //Duyệt qua từng cột của bảng dữ liệu và thêm dữ liệu vào vector `v1`
                {
                    if (flag == false) {
                        maNV = rs.getString("MaNV");  // lấy mã nhân viên của người dùng đang đăng nhập
                    }

                    v1.add(rs.getString("MaHD"));
                    v1.add(rs.getDate("NgayHD"));
                    v1.add(rs.getString("MaNV"));
                    v1.add(rs.getString("MaKH"));
                    v1.add(rs.getString("ThanhTien"));
                }
                model.addRow(v1);  //Thêm vector `v1` vào `DefaultTableModel
                jTListHD.setModel(model);  // Cập nhật lại bảng danh sách hóa đơn
            }
            txtMaNV.setText(maNV);
        } catch (SQLException ex) {
            Logger.getLogger(HoaDon.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void LamMoi() {
        //làm mới các trường nhập liệu
        txtMaHD.setText("");
        jDNgayHD.setDate(null);

        txtMaKH.setText("");
        txtMaNV.setText("");
        txtThanhTien.setText("");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTListHD = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtMaHD = new javax.swing.JTextField();
        jDNgayHD = new com.toedter.calendar.JDateChooser();
        jBThem = new javax.swing.JButton();
        jBXoa = new javax.swing.JButton();
        jBSua = new javax.swing.JButton();
        jBTimKiem = new javax.swing.JButton();
        jbTroLai = new javax.swing.JButton();
        txtMaKH = new javax.swing.JTextField();
        txtMaNV = new javax.swing.JTextField();
        txtThanhTien = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jBLamMoi = new javax.swing.JButton();
        jBChiTiet = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        txtSearchMaHD = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel3.setBackground(new java.awt.Color(241, 202, 164));

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("QUẢN LÝ  HÓA ĐƠN");

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

        jPanel4.setBackground(new java.awt.Color(243, 231, 231));

        jPanel1.setBackground(new java.awt.Color(197, 110, 51));
        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("THÔNG TIN HÓA ĐƠN ");

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
        jLabel3.setText("DANH SÁCH HÓA ĐƠN");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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

        jTListHD.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        jTListHD.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Mã hóa đơn", "Ngày mua ", "Mã nhân viên", "Mã khách hàng", "Thành tiền"
            }
        ));
        jTListHD.setGridColor(new java.awt.Color(204, 204, 204));
        jTListHD.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTListHDMouseClicked(evt);
            }
        });
        jTListHD.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTListHDKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(jTListHD);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 681, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 386, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jLabel4.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel4.setText("Mã hóa đơn");

        jLabel5.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel5.setText("Ngày mua");

        jLabel6.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel6.setText("Mã nhân viên");

        jLabel7.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel7.setText("Mã khách hàng");

        txtMaHD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMaHDActionPerformed(evt);
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
        jBTimKiem.setText("Search");
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

        txtThanhTien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtThanhTienActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel8.setText("Thành tiền");

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

        jButton1.setText("+");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel9.setText("SearchMAHD >>");

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
                                        .addComponent(jLabel7)
                                        .addGroup(jPanel4Layout.createSequentialGroup()
                                            .addGap(24, 24, 24)
                                            .addComponent(jBThem))
                                        .addComponent(jLabel5))
                                    .addGap(23, 23, 23)
                                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(jPanel4Layout.createSequentialGroup()
                                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                .addComponent(jBLamMoi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jBXoa, javax.swing.GroupLayout.DEFAULT_SIZE, 93, Short.MAX_VALUE))
                                            .addGap(50, 50, 50)
                                            .addComponent(jBSua))
                                        .addGroup(jPanel4Layout.createSequentialGroup()
                                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                .addComponent(jDNgayHD, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(txtThanhTien, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 142, Short.MAX_VALUE)
                                                .addComponent(txtMaNV, javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(txtMaKH, javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(jPanel4Layout.createSequentialGroup()
                                                    .addComponent(txtMaHD)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                    .addComponent(jBChiTiet, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGroup(jPanel4Layout.createSequentialGroup()
                                                    .addGap(29, 29, 29)
                                                    .addComponent(jBTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                        .addGroup(jPanel4Layout.createSequentialGroup()
                                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(txtSearchMaHD, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)))))
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
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtSearchMaHD, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtMaHD, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jBTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4)
                            .addComponent(jButton1))
                        .addGap(31, 31, 31)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jDNgayHD, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(31, 31, 31)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(txtMaNV, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(30, 30, 30)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtMaKH, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jBChiTiet, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(41, 41, 41)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(txtThanhTien, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jBXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jBSua, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jBThem, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addComponent(jbTroLai)
                        .addGap(7, 7, 7))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addComponent(jBLamMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(17, 17, 17))))
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

    private void jBXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBXoaActionPerformed
        // Đoạn code này được sử dụng để xóa một hóa đơn trong cơ sở dữ liệu
        StringBuilder sb = new StringBuilder(); //Khởi tạo một đối tượng `StringBuilder` để xây dựng thông báo lỗi nếu có lỗi nhập liệu từ người dùng
        if (txtMaHD.getText().equals("")) //Kiểm tra nếu mã hóa đơn để trống, thì thêm thông báo lỗi vào `StringBuilder
        {
            sb.append("!!Mã hóa đơn không được để trống!!");
            txtMaHD.setBackground(Color.red);
        } else {
            txtMaHD.setBackground(Color.white);
        }
        if (sb.length() > 0)  //Kiểm tra nếu `StringBuilder` lưu trữ thông báo lỗi có độ dài lớn hơn 0, thì hiển thị thông báo lỗi đó 
        {
            JOptionPane.showMessageDialog(this, sb);
            return;
        }
        try {
            int result = JOptionPane.showConfirmDialog(null, "Bạn có muốn xóa hóa đơn không?", "Xóa", JOptionPane.YES_NO_OPTION);
            if (result == JOptionPane.YES_OPTION)  //Hiển thị hộp thoại xác nhận để xác định xem người dùng có muốn xóa hóa đơn hay không
            {
                HoaDonCtr.DeleteHD(txtMaHD.getText());  //gọi phương thức `DeleteHD()` trong lớp `HoaDonCtr` để xóa tài khoản tương ứng khỏi cơ sở dữ liệu
                JOptionPane.showMessageDialog(this, "Đã xóa  thành công");
            } else {
                JOptionPane.showMessageDialog(this, "hóa đơn chưa được xóa");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error : " + e.getMessage());
            e.printStackTrace();
        }
    }//GEN-LAST:event_jBXoaActionPerformed

    private void jBTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBTimKiemActionPerformed
        //  Đoạn code này dùng để tìm kiếm một hóa đơn trong cơ sở dữ liệu
        StringBuilder sb = new StringBuilder();
        if (txtSearchMaHD.getText().equals("")) {
            sb.append("Nhập mã hóa đơn cần tìm!");
            txtSearchMaHD.setBackground(Color.red);
        } else {
            txtSearchMaHD.setBackground(Color.white);
        }
        if (sb.length() > 0) {
            JOptionPane.showMessageDialog(this, sb);
            return;
        }
        try {
            HoaDonCtr hdc = new HoaDonCtr();
            HoaDonClass hd = hdc.find(txtSearchMaHD.getText());  //sử dụng phương thức `find()` để tìm kiếm hóa đơn có mã `txtMaHD` trong cơ sở dữ liệu
            if (hd != null)  //Kiểm tra xem hóa đơn được tìm thấy hay không
            {
                //hiển thị thông tin của hóa đơn
                int selectedIndex = jTListHD.getSelectedRow();
                txtMaHD.setText(hd.getMaHD());

                jDNgayHD.setDate(hd.getNgayHD());

                txtMaNV.setText(hd.getMaNV());
                txtMaKH.setText(hd.getMaKH());
                txtThanhTien.setText(hd.getThanhTien());
            } else {
                JOptionPane.showMessageDialog(this, "Hóa đơn tìm kiếm không tồn tại!");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
            e.printStackTrace();
        }
    }//GEN-LAST:event_jBTimKiemActionPerformed

    private void jbTroLaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbTroLaiActionPerformed
        // TODO add your handling code here:
        HomePage hp = new HomePage(maTK, loaiTK); //khởi tạo đối tượng Homepage
        hp.setVisible(true);  // hiển thị giao diện homepage
        this.dispose();  // đóng giao diện hiện tại
    }//GEN-LAST:event_jbTroLaiActionPerformed

    private void jBThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBThemActionPerformed
        // Đoạn code này được sử dụng để thêm một hóa đơn mới vào cơ sở dữ liệu
        StringBuilder sb = new StringBuilder();
        if (txtMaHD.getText().equals("")) {
            sb.append("!!Mã hóa đơn không được để trống!!");
            txtMaHD.setBackground(Color.red);
        } else {
            txtMaHD.setBackground(Color.white);
        }
        if (sb.length() > 0) {
            JOptionPane.showMessageDialog(this, sb);
            return;
        }
        try {
            HoaDonClass hd = new HoaDonClass();
            //thiết lập các giá trị
            hd.setMaHD(txtMaHD.getText());
            if (jDNgayHD.getDate() != null) {
                java.util.Date nghd = jDNgayHD.getDate();
                java.sql.Date sqlbd = new java.sql.Date(nghd.getTime());  //chuyển đổi giá trị của trường "jDNgayHD" từ định dạng `java.util.Date` sang định dạng `java.sql.Date`
                hd.setNgayHD(sqlbd);
            }

            hd.setMaKH(txtMaKH.getText());
            hd.setMaNV(txtMaNV.getText());
            if (txtThanhTien.getText().equals("")) {
                hd.setThanhTien("0");
            } else {
                hd.setThanhTien(txtThanhTien.getText());
            }
            HoaDonCtr.InsertHD(hd);  //Gọi phương thức `InsertHD()` trong lớp `HoaDonCtr` để thêm đối tượng `HoaDonClass` vừa tạo vào cơ sở dữ liệu
            JOptionPane.showMessageDialog(this, "Đã thêm thành công.");  //Hiển thị thông báo xác nhận nếu thêm hóa đơn mới thành công
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error : " + e.getMessage());   //Hiển thị thông báo lỗi và in ra thông tin chi tiết về lỗi nếu có lỗi khi thêm hóa đơn mới 
            e.printStackTrace();
        }

    }//GEN-LAST:event_jBThemActionPerformed

    private void jBSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBSuaActionPerformed
        // Đoạn code này được sử dụng để cập nhật một hóa đơn trong cơ sở dữ liệu
        StringBuilder sb = new StringBuilder();
        if (txtMaHD.getText().equals("")) {
            sb.append("!!Mã hóa đơn không được để trống!!");
            txtMaHD.setBackground(Color.red);
        } else {
            txtMaHD.setBackground(Color.white);
        }
        if (sb.length() > 0) {
            JOptionPane.showMessageDialog(this, sb);
            return;
        }
        try {
            HoaDonClass hd = new HoaDonClass();
            hd.setMaHD(txtMaHD.getText());
            if (jDNgayHD.getDate() != null) {
                java.util.Date nghd = jDNgayHD.getDate();
                java.sql.Date sqlbd = new java.sql.Date(nghd.getTime());
                hd.setNgayHD(sqlbd);
            }

            hd.setMaKH(txtMaKH.getText());
            hd.setMaNV(txtMaNV.getText());

            hd.setThanhTien(txtThanhTien.getText());
            int result = JOptionPane.showConfirmDialog(null, "Bạn có muốn cập nhật hóa đơn không?", "cập nhật", JOptionPane.YES_NO_OPTION);
            if (result == JOptionPane.YES_OPTION) {
                HoaDonCtr.UpdateHD(hd);  //Gọi phương thức `UpdateHD()` trong lớp `HoaDonCtr` để cập nhật đối tượng `HoaDonClass` vừa tạo vào cơ sở dữ liệu
                JOptionPane.showMessageDialog(this, "Đã Cập nhật thành công.");
            } else {
                JOptionPane.showMessageDialog(this, "hóa đơn chưa được cập nhật");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error : " + e.getMessage());
            e.printStackTrace();
        }
    }//GEN-LAST:event_jBSuaActionPerformed

    private void jBLamMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBLamMoiActionPerformed
        // đoạn code này dùng để làm mới dữ liệu trên bảng danh sách hóa đơn và các trường nhập liệu
        LamMoi();  //làm mới các trường nhập liệu
        Load_data_HD();  //tải lại dữ liệu danh sách tài khoản từ cơ sở dữ liệu và hiển thị lên bảng danh sách

    }//GEN-LAST:event_jBLamMoiActionPerformed

    private void jTListHDMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTListHDMouseClicked
        // đoạn code này dùng để hiển thị thông tin hóa đơn khi người dùng click chuột vào một dòng trong bảng jTListHD
        try {
            LamMoi(); //làm mới các trường nhập liệu và các nút trên giao diện `HoaDon`
            int selectedIndex = jTListHD.getSelectedRow();  // Lấy chỉ số của dòng được chọn trong bảng `jTListHD`
            jTListHD.setColumnSelectionInterval(0, 4);  //Thiết lập cột được chọn trong bảng `jTListHD` từ cột 0 đến cột 4
            //hiển thị các giá trị 
            txtMaHD.setText(jTListHD.getValueAt(selectedIndex, 0).toString());
            jDNgayHD.setDate((java.util.Date) jTListHD.getValueAt(selectedIndex, 1));

            txtMaNV.setText(jTListHD.getValueAt(selectedIndex, 2).toString());
            txtMaKH.setText(jTListHD.getValueAt(selectedIndex, 3).toString());

            txtThanhTien.setText(jTListHD.getValueAt(selectedIndex, 4).toString());

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Lỗi : " + e.getMessage());
        }
    }//GEN-LAST:event_jTListHDMouseClicked

    private void jBChiTietActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBChiTietActionPerformed
        // Đoạn code này được sử dụng để xử lý sự kiện khi người dùng nhấp vào nút "Detail" trên giao diện
        String maHD = txtMaHD.getText();  //Lấy mã hóa đơn từ trường "txtMaHD"
        CTHD ct = new CTHD(maNV, maTK, loaiTK, maHD);  //Khởi tạo một đối tượng `CTHD` (Giao diện Chi tiết hóa đơn)
        ct.setVisible(true); //Hiển thị giao diện "Chi tiết hóa đơn"
        this.dispose();  //đóng giao diện hiện tại
    }//GEN-LAST:event_jBChiTietActionPerformed

    private void txtMaHDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMaHDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMaHDActionPerformed

    private void txtThanhTienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtThanhTienActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtThanhTienActionPerformed

    private void jTListHDKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTListHDKeyReleased
        // Đoạn code này được sử dụng để xử lý sự kiện khi người dùng chọn một hóa đơn trong bảng danh sách hóa đơn trên giao diện `HoaDon` bằng cách sử dụng bàn phím
        try {
            LamMoi();
            int selectedIndex = jTListHD.getSelectedRow();
            jTListHD.setColumnSelectionInterval(0, 4);
            txtMaHD.setText(jTListHD.getValueAt(selectedIndex, 0).toString());
            jDNgayHD.setDate((java.util.Date) jTListHD.getValueAt(selectedIndex, 1));

            txtMaNV.setText(jTListHD.getValueAt(selectedIndex, 2).toString());
            txtMaKH.setText(jTListHD.getValueAt(selectedIndex, 3).toString());

            txtThanhTien.setText(jTListHD.getValueAt(selectedIndex, 4).toString());

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Lỗi : " + e.getMessage());
        }
    }//GEN-LAST:event_jTListHDKeyReleased

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // Đoạn code này được sử dụng để tạo một mã phiếu nhập mới và hiển thị mã đó lên trường nhập liệu "txtMaPN"
        try (java.sql.Connection con = ConnectionUtils.getMyConnection())  //Tạo một đối tượng `Connection` để kết nối đến cơ sở dữ liệu
        {
            String SQL = "select insert_new_MaHD() as newMaHD from dual";  //Tạo câu truy vấn SQL để tìm kiếm một mã hóa đơn mới
            PreparedStatement ps = con.prepareStatement(SQL);  //Tạo một đối tượng `PreparedStatement` để chuẩn bị câu truy vấn
            ResultSet rs = ps.executeQuery();   //Thực thi câu truy vấn và lưu kết quả vào đối tượng `ResultSet` để xử lý
            if (rs.next())  //Kiểm tra xem có kết quả trả về từ cơ sở dữ liệu hay không
            {
                txtMaHD.setText(rs.getString("NEWMAHD"));    //Lấy giá trị của cột "newMaHD" trong dòng kết quả của `ResultSet` và hiển thị giá trị đó lên trường nhập liệu "txtMaHD"
            }
        } catch (SQLException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(HoaDon.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(HoaDon.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(HoaDon.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(HoaDon.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new HoaDon(maTK, loaiTK).setVisible(true);
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
    private com.toedter.calendar.JDateChooser jDNgayHD;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTListHD;
    private javax.swing.JButton jbTroLai;
    private javax.swing.JTextField txtMaHD;
    private javax.swing.JTextField txtMaKH;
    private javax.swing.JTextField txtMaNV;
    private javax.swing.JTextField txtSearchMaHD;
    private javax.swing.JTextField txtThanhTien;
    // End of variables declaration//GEN-END:variables
}
