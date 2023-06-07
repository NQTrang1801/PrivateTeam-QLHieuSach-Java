/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

import dao.ConnectionUtils;
import java.beans.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.sql.CallableStatement;
import oracle.jdbc.OracleTypes;


/**
 *
 * @author NTVy
 */
public class BaoCaoThongKe extends javax.swing.JFrame {
    private String maTK;
    private String loaiTK;
    /**
     * Creates new form BaoCaoThongKe
     */
    public BaoCaoThongKe() {
        initComponents();
        this.setLocationRelativeTo(this);
    }

    public BaoCaoThongKe(String maTK, String loaiTK) {
        initComponents();
        this.setLocationRelativeTo(this);
        blockTextField();
        this.maTK = maTK;
        this.loaiTK = loaiTK;
        loadThongSo();
        loadBangThanhTich();
        loadSachSapHet();
        
    }
    
    public void loadThongSo(){
        try (java.sql.Connection con = ConnectionUtils.getMyConnection())   //bắt đầu kết nối database
        {
            // tạo câu truy vấn SQL để lấy thông tin
            String SQL1 = "select sum(ThanhTien) DT from HOADON";
            String SQL2 = "select sum(TongTien) TT from PHIEUNHAP";
            String SQL3 = "select sum(Luong) L from NHANVIEN";
            //khởi tạo đối tượng PreparedStatement cho câu truy vấn SQL
            PreparedStatement ps1 = con.prepareStatement(SQL1);
            PreparedStatement ps2 = con.prepareStatement(SQL2);
            PreparedStatement ps3 = con.prepareStatement(SQL3);
            //thực hiện truy vấn và lưu kết quả vào đối tượng ResultSet
            ResultSet rs1 = ps1.executeQuery();
            ResultSet rs2 = ps2.executeQuery(); 
            ResultSet rs3 = ps3.executeQuery(); 

            if (rs1.next())    
            {
                txtTongDoanhThu.setText(rs1.getString("DT"));   //Hiển thị thông tin Mã nhân viên
            } 
            if (rs2.next())    
            {
                txtTongTienNhapHang.setText(rs2.getString("TT"));   //Hiển thị thông tin Mã nhân viên
            } 
            if (rs3.next())    
            {
                txtTongLuongNhanVien.setText(rs3.getString("L"));   //Hiển thị thông tin Mã nhân viên
            } 
       
            con.close();
            //  JOptionPane.showMessageDialog(this,"Sửa thành công!");
        } catch (SQLException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }   //Xử lý ngoại lệ SQLException khi có lỗi xảy ra trong quá trình truy vấn
        catch (ClassNotFoundException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }   //Xử lý ngoại lệ ClassNotFoundException khi không tìm thấy lớp Driver để kết nối với database
    }
    
    public void loadSachSapHet(){
        try (java.sql.Connection con = ConnectionUtils.getMyConnection())   //bắt đầu kết nối database
        {
            String SQL_SachSapHet = "select S.MaSach, sum(CT.SL) as SLDB, S.SL\n"
                    + "from (select MaSach, SL, GIA from SACH)  S\n"
                    + "        join (select MaSach, SL from CTHD) CT\n"
                    + "        on S.MaSach = CT.MaSach\n"
                    + "where S.SL < 10\n"
                    + "group by S.MaSach, S.SL";   // tạo câu truy vấn SQL để lấy thông tin 
            PreparedStatement ps = con.prepareStatement(SQL_SachSapHet);   //khởi tạo đối tượng PreparedStatement cho câu truy vấn SQL   
            ResultSet rsSSH = ps.executeQuery();   //thực hiện truy vấn và lưu kết quả vào đối tượng ResultSet

            ResultSetMetaData rsdSSH = rsSSH.getMetaData();    //Lấy thông tin về các cột của bảng dữ liệu
            int cSSH = rsdSSH.getColumnCount();    //Lấy số lượng cột của bảng dữ liệu

            DefaultTableModel model = (DefaultTableModel) jTSachSapHet.getModel();    //Lấy mô hình bảng danh sách hóa đơn sách từ giao diện `HoaDon`
            model.setRowCount(0);  //Xóa toàn bộ dữ liệu trong bảng danh sách Hóa đơn 

            while (rsSSH.next()) {
                Vector v1 = new Vector();  //Khởi tạo một vector `v1` để lưu thông tin của một hóa đơn
                for (int i = 1; i <= cSSH; i++) //Duyệt qua từng cột của bảng dữ liệu và thêm dữ liệu vào vector `v1`
                {
                    v1.add(rsSSH.getString("MaSach"));
                    v1.add(rsSSH.getString("SLDB"));
                    v1.add(rsSSH.getString("SL"));
                }
                model.addRow(v1);  //Thêm vector `v1` vào `DefaultTableModel
                jTSachSapHet.setModel(model);  // Cập nhật lại bảng danh sách hóa đơn
            }
            con.close();
            //  JOptionPane.showMessageDialog(this,"Sửa thành công!");
        } catch (SQLException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }   //Xử lý ngoại lệ SQLException khi có lỗi xảy ra trong quá trình truy vấn
        catch (ClassNotFoundException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }   //Xử lý ngoại lệ ClassNotFoundException khi không tìm thấy lớp Driver để kết nối với database
    }
    
    public void loadBangThanhTich(){
        try (java.sql.Connection con = ConnectionUtils.getMyConnection())   //bắt đầu kết nối database
        {
            String SQL_BangThanhTich = "SELECT nv.MaNV, COUNT(MaHD) as SLHD, SUM(hd.ThanhTien) as DT, RANK() OVER (ORDER BY SUM(hd.ThanhTien) DESC) AS XH\n"
                    + "FROM NhanVien nv JOIN HoaDon hd ON nv.MaNV = hd.MaNV\n"
                    + "GROUP BY nv.MaNV ORDER BY XH ASC";   // tạo câu truy vấn SQL để lấy thông tin 
            PreparedStatement ps = con.prepareStatement(SQL_BangThanhTich);   //khởi tạo đối tượng PreparedStatement cho câu truy vấn SQL   
            ResultSet rsBTT = ps.executeQuery();   //thực hiện truy vấn và lưu kết quả vào đối tượng ResultSet

            ResultSetMetaData rsdBTT = rsBTT.getMetaData();    //Lấy thông tin về các cột của bảng dữ liệu
            int cBTT = rsdBTT.getColumnCount();    //Lấy số lượng cột của bảng dữ liệu

            DefaultTableModel model = (DefaultTableModel) jTBangThanhTich.getModel();    //Lấy mô hình bảng danh sách hóa đơn sách từ giao diện `HoaDon`
            model.setRowCount(0);  //Xóa toàn bộ dữ liệu trong bảng danh sách Hóa đơn 

            while (rsBTT.next()) {
                Vector v1 = new Vector();  //Khởi tạo một vector `v1` để lưu thông tin của một hóa đơn
                for (int i = 1; i <= cBTT; i++) //Duyệt qua từng cột của bảng dữ liệu và thêm dữ liệu vào vector `v1`
                {
                    v1.add(rsBTT.getString("MaNV"));
                    v1.add(rsBTT.getString("SLHD"));
                    v1.add(rsBTT.getString("DT"));
                    v1.add(rsBTT.getString("XH"));
                }
                model.addRow(v1);  //Thêm vector `v1` vào `DefaultTableModel
                jTBangThanhTich.setModel(model);  // Cập nhật lại bảng danh sách hóa đơn
            }
            con.close();
            //  JOptionPane.showMessageDialog(this,"Sửa thành công!");
        } catch (SQLException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }   //Xử lý ngoại lệ SQLException khi có lỗi xảy ra trong quá trình truy vấn
        catch (ClassNotFoundException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }   //Xử lý ngoại lệ ClassNotFoundException khi không tìm thấy lớp Driver để kết nối với database
    }
    
    public void blockTextField(){
        txtTongDoanhThu.setEditable(false);
        txtTongDoanhThu.setEnabled(false);
        txtTongTienNhapHang.setEditable(false);
        txtTongTienNhapHang.setEnabled(false);
        txtTongLuongNhanVien.setEditable(false);
        txtTongLuongNhanVien.setEnabled(false);
        txtAreaBaoCaoTheoNamThang.setEditable(false);
        txtAreaBaoCaoTheoNamThang.setEnabled(false);
    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        txtTongDoanhThu = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtTongLuongNhanVien = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtTongTienNhapHang = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jCMonth = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtAreaBaoCaoTheoNamThang = new javax.swing.JTextArea();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jCYear = new javax.swing.JComboBox<>();
        jBComfirm = new javax.swing.JButton();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTTopSach = new javax.swing.JTable();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTTopKH = new javax.swing.JTable();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTSachSapHet = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTBangThanhTich = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jBack = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        txtTongDoanhThu.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txtTongDoanhThu.setForeground(new java.awt.Color(51, 0, 255));
        txtTongDoanhThu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTongDoanhThuActionPerformed(evt);
            }
        });

        jLabel1.setText("Tổng doanh thu:");

        jPanel2.setBackground(new java.awt.Color(241, 202, 164));

        jLabel2.setBackground(new java.awt.Color(241, 202, 164));
        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("THỐNG KÊ");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jLabel3.setText("Tổng lương nhân viên:");

        txtTongLuongNhanVien.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txtTongLuongNhanVien.setForeground(new java.awt.Color(0, 0, 255));
        txtTongLuongNhanVien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTongLuongNhanVienActionPerformed(evt);
            }
        });

        jLabel4.setText("Tổng tiền nhập hàng:");

        txtTongTienNhapHang.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txtTongTienNhapHang.setForeground(new java.awt.Color(0, 51, 255));
        txtTongTienNhapHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTongTienNhapHangActionPerformed(evt);
            }
        });

        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jCMonth.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", " " }));

        txtAreaBaoCaoTheoNamThang.setColumns(20);
        txtAreaBaoCaoTheoNamThang.setRows(5);
        jScrollPane1.setViewportView(txtAreaBaoCaoTheoNamThang);

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("BÁO CÁO THEO THÁNG");

        jLabel6.setText("month:");

        jLabel7.setText("year:");

        jCYear.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "2022", "2023", "2024", "2025", "2026", "2027", "2028", "2029", "2030" }));
        jCYear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCYearActionPerformed(evt);
            }
        });

        jBComfirm.setBackground(new java.awt.Color(204, 204, 204));
        jBComfirm.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jBComfirm.setForeground(new java.awt.Color(197, 110, 51));
        jBComfirm.setText("COMFIRM");
        jBComfirm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBComfirmActionPerformed(evt);
            }
        });

        jTabbedPane2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jTTopSach.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Mã sách", "Tên sách", "Số lượng tồn", "Số lượng bán", "Giá"
            }
        ));
        jScrollPane4.setViewportView(jTTopSach);

        jTabbedPane2.addTab("TOP  5 SÁCH BÁN CHẠY", jScrollPane4);

        jTTopKH.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Mã khách hàng", "Họ tên", "Loại khách hàng", "Doanh thu"
            }
        ));
        jScrollPane5.setViewportView(jTTopKH);

        jTabbedPane2.addTab("TOP KHÁCH HÀNG", jScrollPane5);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTabbedPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 496, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 43, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jCMonth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jCYear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jBComfirm, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jCMonth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(jCYear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jBComfirm, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 235, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jTabbedPane1.setFont(new java.awt.Font("Segoe UI", 2, 10)); // NOI18N

        jTSachSapHet.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Mã sách", "Số lượng đã bán", "Số lượng tồn"
            }
        ));
        jScrollPane2.setViewportView(jTSachSapHet);

        jTabbedPane1.addTab("SÁCH SẮP HẾT", jScrollPane2);

        jTBangThanhTich.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Mã nhân viên", "Số lượng HD", "Doanh thu", "Xếp hạng"
            }
        ));
        jScrollPane3.setViewportView(jTBangThanhTich);

        jTabbedPane1.addTab("BẢNG THÀNH TÍCH", jScrollPane3);

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/LogoNSHA.PNG"))); // NOI18N

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jButton2.setBackground(new java.awt.Color(204, 204, 204));
        jButton2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton2.setForeground(new java.awt.Color(0, 0, 255));
        jButton2.setText("Reload");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jBack.setBackground(new java.awt.Color(102, 102, 102));
        jBack.setForeground(new java.awt.Color(255, 255, 255));
        jBack.setText("BACK");
        jBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBackActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtTongLuongNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTongDoanhThu, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTongTienNhapHang, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jBack, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtTongDoanhThu, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel1))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtTongTienNhapHang, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtTongLuongNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jBack, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtTongDoanhThuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTongDoanhThuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTongDoanhThuActionPerformed

    private void txtTongLuongNhanVienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTongLuongNhanVienActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTongLuongNhanVienActionPerformed

    private void txtTongTienNhapHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTongTienNhapHangActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTongTienNhapHangActionPerformed

    private void jCYearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCYearActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCYearActionPerformed

    private void jBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBackActionPerformed
        // TODO add your handling code here:
        QuanLy ql = new QuanLy(maTK, loaiTK); //khởi tạo đối tượng QuanLy
        ql.setVisible(true); //hiển thị giao diện quản lý
        this.dispose(); //đóng giao diện hiện tại
    }//GEN-LAST:event_jBackActionPerformed

    private void jBComfirmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBComfirmActionPerformed
        // TODO add your handling code here:
        loadTopBanSach();
        loadTopKhachHang();
        

    }//GEN-LAST:event_jBComfirmActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        loadThongSo();
        loadBangThanhTich();
        loadSachSapHet();
        loadTopBanSach();
        loadTopKhachHang();
    }//GEN-LAST:event_jButton2ActionPerformed
    public void loadTopBanSach(){
        try (java.sql.Connection con = ConnectionUtils.getMyConnection()) //bắt đầu kết nối database
        {
            // tạo câu truy vấn SQL để lấy thông tin
            String SQL = "select S.MaSach, TenSach, S.SL, sum(CT.SL) as SLB, Gia\n"
                    + "from (select MAHD, NGAYHD from HOADON) HD\n"
                    + "        join CTHD CT on HD.MAHD = CT.MAHD\n"
                    + "        join SACH S on S.MaSach = CT.MaSach\n"
                    + "where extract(Month from NGAYHD) = " + jCMonth.getSelectedItem().toString() + " and extract(Year from NGAYHD) = " + jCYear.getSelectedItem().toString() + " and rownum <= 5\n"
                    + "group by S.MaSach, TenSach, S.Sl, Gia\n" 
                    + "order by SLB desc";
            PreparedStatement ps = con.prepareStatement(SQL);   //khởi tạo đối tượng PreparedStatement cho câu truy vấn SQL   
            ResultSet rs = ps.executeQuery();   //thực hiện truy vấn và lưu kết quả vào đối tượng ResultSet

            ResultSetMetaData rsdSSH = rs.getMetaData();    //Lấy thông tin về các cột của bảng dữ liệu
            int c = rsdSSH.getColumnCount();    //Lấy số lượng cột của bảng dữ liệu

            DefaultTableModel model = (DefaultTableModel) jTTopSach.getModel();    //Lấy mô hình bảng danh sách hóa đơn sách từ giao diện
            model.setRowCount(0);  //Xóa toàn bộ dữ liệu trong bảng danh sách Hóa đơn 

            while (rs.next()) {
                Vector v1 = new Vector();  //Khởi tạo một vector `v1` để lưu thông tin của một hóa đơn
                for (int i = 1; i <= c; i++) //Duyệt qua từng cột của bảng dữ liệu và thêm dữ liệu vào vector `v1`
                {
                    v1.add(rs.getString("MaSach"));
                    v1.add(rs.getString("TenSach"));
                    v1.add(rs.getString("SL"));
                    v1.add(rs.getString("SLB"));
                    v1.add(rs.getString("Gia"));
                }
                model.addRow(v1);  //Thêm vector `v1` vào `DefaultTableModel
                jTTopSach.setModel(model);  // Cập nhật lại bảng danh sách hóa đơn
            }
            con.close();
            //  JOptionPane.showMessageDialog(this,"Sửa thành công!");
        } catch (SQLException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        } //Xử lý ngoại lệ SQLException khi có lỗi xảy ra trong quá trình truy vấn
        catch (ClassNotFoundException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }   //Xử lý ngoại lệ ClassNotFoundException khi không tìm thấy lớp Driver để kết nối với database
    }
    
    public void loadTopKhachHang(){
        try (java.sql.Connection con = ConnectionUtils.getMyConnection()) //bắt đầu kết nối database
        {
            // tạo câu truy vấn SQL để lấy thông tin
            String SQL = "select KH.MaKH, Ho || ' ' || Ten as HOTEN, LoaiKH, sum(CT.SL*GIA) as DT\n"
                    + "from KHACHHANG KH join HOADON HD on KH.MAKH = HD.MAKH\n"
                    + "    join CTHD CT on CT.MAHD = HD.MAHD\n"
                    + "    join SACH S on CT.MASACH = S.MASACH\n"
                    + "where extract(Month from NGAYHD) = " + jCMonth.getSelectedItem().toString() + " and extract(Year from NGAYHD) = " + jCYear.getSelectedItem().toString() + " and rownum <= 10\n"
                    + "group by KH.MAKH, HO, TEN, LOAIKH\n"
                    + "order by DT desc";
            PreparedStatement ps = con.prepareStatement(SQL);   //khởi tạo đối tượng PreparedStatement cho câu truy vấn SQL   
            ResultSet rs = ps.executeQuery();   //thực hiện truy vấn và lưu kết quả vào đối tượng ResultSet

            ResultSetMetaData rsdSSH = rs.getMetaData();    //Lấy thông tin về các cột của bảng dữ liệu
            int c = rsdSSH.getColumnCount();    //Lấy số lượng cột của bảng dữ liệu

            DefaultTableModel model = (DefaultTableModel) jTTopKH.getModel();    //Lấy mô hình bảng danh sách hóa đơn sách từ giao diện
            model.setRowCount(0);  //Xóa toàn bộ dữ liệu trong bảng danh sách Hóa đơn 

            while (rs.next()) {
                Vector v1 = new Vector();  //Khởi tạo một vector `v1` để lưu thông tin của một hóa đơn
                for (int i = 1; i <= c; i++) //Duyệt qua từng cột của bảng dữ liệu và thêm dữ liệu vào vector `v1`
                {
                    v1.add(rs.getString("MaKH"));
                    v1.add(rs.getString("HOTEN"));
                    v1.add(rs.getString("LOAIKH"));
                    v1.add(rs.getString("DT"));
                }
                model.addRow(v1);  //Thêm vector `v1` vào `DefaultTableModel
                jTTopKH.setModel(model);  // Cập nhật lại bảng danh sách hóa đơn
            }
            con.close();
            //  JOptionPane.showMessageDialog(this,"Sửa thành công!");
        } catch (SQLException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        } //Xử lý ngoại lệ SQLException khi có lỗi xảy ra trong quá trình truy vấn
        catch (ClassNotFoundException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }   //Xử lý ngoại lệ ClassNotFoundException khi không tìm thấy lớp Driver để kết nối với database
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String maTK, String loaiTK) {
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new BaoCaoThongKe(maTK, loaiTK).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBComfirm;
    private javax.swing.JButton jBack;
    private javax.swing.JButton jButton2;
    private javax.swing.JComboBox<String> jCMonth;
    private javax.swing.JComboBox<String> jCYear;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTable jTBangThanhTich;
    private javax.swing.JTable jTSachSapHet;
    private javax.swing.JTable jTTopKH;
    private javax.swing.JTable jTTopSach;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTextArea txtAreaBaoCaoTheoNamThang;
    private javax.swing.JTextField txtTongDoanhThu;
    private javax.swing.JTextField txtTongLuongNhanVien;
    private javax.swing.JTextField txtTongTienNhapHang;
    // End of variables declaration//GEN-END:variables
}
