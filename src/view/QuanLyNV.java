/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

import dao.ConnectionUtils;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.io.File;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import model.NhanVienCtr;
import model.NhanVienClass;

/**
 *
 * @author HLPhong
 */
public class QuanLyNV extends javax.swing.JFrame {

    private String maTK;
    private String loaiTK;
    /**
     * Creates new form QuanLyNV
     */
    Connection conn;
    PreparedStatement ps;
    ResultSet rs;
    String imgPath;
    File file = null;
    String filename;

    public QuanLyNV(String maTK, String loaiTK) {
        initComponents();
        this.setLocationRelativeTo(this);
        this.maTK = maTK;
        this.loaiTK = loaiTK;
        if (loaiTK.equals("quan ly") == false) {
            txtMaNV.setEditable(false); // không cho phép chỉnh sửa
            txtMaNV.setEnabled(false); // không cho phép nhập liệu
            txtLuong.setEditable(false); // không cho phép chỉnh sửa
            txtLuong.setEnabled(false); // không cho phép nhập liệu
        }
        KetNoiCSDL();
        NhanVien_Load();
    }

    public QuanLyNV() {
        initComponents();
        this.setLocationRelativeTo(this);
        KetNoiCSDL();
        NhanVien_Load();
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

    public void NhanVien_Load() {
        //đoạn code sử dụng để tải dữ liệu từ cơ sở dữ liệu và hiển thị chúng trong bảng `jTListNV
        try {
            if (this.loaiTK.equals("quan ly"))  //Nếu loại tài khoản là "quan ly", thì lấy tất cả các bản ghi trong bảng
            {
                ps = conn.prepareStatement("SELECT * FROM NHANVIEN ");
            } else  //nếu loại tài khoản là "nhan vien ban hang" hoặc "nhan vien kho", thì chỉ lấy bản ghi có mã tài khoản tương ứng với mã tài khoản của người dùng đăng nhập
            {
                ps = conn.prepareStatement("SELECT NV.MANV, NV.Ho, NV.Ten,NV.GioiTinh, NV.DiaChi, NV.CMND, NV.NgaySinh, NV.NgayBD, NV.SoDT, NV.Luong, NV.HinhAnh FROM NHANVIEN NV JOIN TAIKHOAN TK ON TK.MANV = NV.MANV WHERE MATK = " + maTK);
            }
            rs = ps.executeQuery();   //thực hiện câu truy vấn lưu kết quả vào rs

            ResultSetMetaData rsd = rs.getMetaData();  //lấy thông tin về các cột trong kết quả truy vấn
            int c = rsd.getColumnCount();  //lưu trữ số lượng cột vào biến `c`

            DefaultTableModel model = (DefaultTableModel) jTListNV.getModel();  // Lấy một tham chiếu đến mô hình dữ liệu của bảng `jTListNV`
            model.setRowCount(0);  // xóa tất cả các dòng trong bảng để chuẩn bị cho việc tải dữ liệu mới

            while (rs.next()) {
                Vector v1 = new Vector();  //tạo một đối tượng `Vector` và thêm các giá trị của các cột trong bản ghi vào đó
                for (int i = 1; i <= c; i++) {
                    v1.add(rs.getString("MaNV"));
                    v1.add(rs.getString("Ho"));
                    v1.add(rs.getString("Ten"));
                    v1.add(rs.getString("GioiTinh"));
                    v1.add(rs.getString("DiaChi"));
                    v1.add(rs.getString("CMND"));
                    v1.add(rs.getDate("NgaySinh"));
                    v1.add(rs.getDate("NgayBD"));
                    v1.add(rs.getString("SoDT"));
                    v1.add(rs.getString("Luong"));
                    v1.add(rs.getString("HinhAnh"));

                }
                model.addRow(v1);  //Thêm đối tượng `Vector` vừa tạo vào `DefaultTableModel`
                jTListNV.setModel(model);  //cập nhật lại bảng `jTListNV` để hiển thị tất cả các bản ghi được tải
            }

        } catch (SQLException ex) {
            Logger.getLogger(QuanLyNV.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void LamMoi() {
        //làm mới các trường nhập liệu
        txtSearch.setText("");
        txtMaNV.setText("");
        txtHoNV.setText("");
        txtTenNV.setText("");
        gtNam.setSelected(false);
        gtNu.setSelected(false);
        txtDiaChi.setText("");
        txtCMND.setText("");
        jDNgSinh.setDate(null);
        jDNgBD.setDate(null);
        txtSoDT.setText("");
        txtLuong.setText("");
        txtHinhAnh.setText("");
        txtHinhAnh.setIcon(null);
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTListNV = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtMaNV = new javax.swing.JTextField();
        jDNgSinh = new com.toedter.calendar.JDateChooser();
        jBTimKiem = new javax.swing.JButton();
        txtTenNV = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtDiaChi = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jDNgBD = new com.toedter.calendar.JDateChooser();
        jLabel11 = new javax.swing.JLabel();
        txtLuong = new javax.swing.JTextField();
        jbTroLai1 = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        txtSoDT = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtHoNV = new javax.swing.JTextField();
        txtCMND = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtSearch = new javax.swing.JTextField();
        jPanel6 = new javax.swing.JPanel();
        txtHinhAnh = new javax.swing.JLabel();
        jBtnBrowser = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jBLamMoi = new javax.swing.JButton();
        jBSua = new javax.swing.JButton();
        jBXoa = new javax.swing.JButton();
        jBThem = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();
        gtNam = new javax.swing.JRadioButton();
        gtNu = new javax.swing.JRadioButton();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel3.setBackground(new java.awt.Color(241, 202, 164));

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("QUẢN LÝ  NHÂN VIÊN");

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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(0, 6, Short.MAX_VALUE)
                .addComponent(jLabel1))
        );

        jPanel4.setBackground(new java.awt.Color(243, 231, 231));

        jPanel1.setBackground(new java.awt.Color(197, 110, 51));
        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("THÔNG TIN NHÂN VIÊN ");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 460, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addContainerGap())
        );

        jPanel2.setBackground(new java.awt.Color(197, 110, 51));
        jPanel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("DANH SÁCH NHÂN VIÊN");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 609, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jTListNV.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        jTListNV.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã NV", "Họ NV", "Tên NV", "Giới tính", "Địa chỉ", "CMND", "Ngày sinh", "Ngày vào làm", "Số ĐT", "Lương"
            }
        ));
        jTListNV.setGridColor(new java.awt.Color(204, 204, 204));
        jTListNV.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTListNVMouseClicked(evt);
            }
        });
        jTListNV.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTListNVKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(jTListNV);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 610, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 537, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jLabel4.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel4.setText("Mã nhân viên");

        jLabel5.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel5.setText("Ngày sinh");

        jLabel6.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel6.setText("Tên nhân viên");

        jBTimKiem.setBackground(new java.awt.Color(196, 101, 38));
        jBTimKiem.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jBTimKiem.setText("Tìm kiếm");
        jBTimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBTimKiemActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel8.setText("Địa chỉ");

        jLabel10.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel10.setText("Ngày vào làm ");

        jLabel11.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel11.setText("Lương");

        jbTroLai1.setBackground(new java.awt.Color(195, 105, 75));
        jbTroLai1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jbTroLai1.setForeground(new java.awt.Color(255, 255, 255));
        jbTroLai1.setText("TRỞ LẠI");
        jbTroLai1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbTroLai1ActionPerformed(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel13.setText("Số ĐT");

        txtSoDT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSoDTActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel7.setText("Họ nhân viên");

        txtCMND.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        jLabel12.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel12.setText("CMND");

        jLabel9.setText("SEARCH");

        txtSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSearchActionPerformed(evt);
            }
        });
        txtSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSearchKeyReleased(evt);
            }
        });

        txtHinhAnh.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jBtnBrowser.setText("browser");
        jBtnBrowser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnBrowserActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtHinhAnh, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jBtnBrowser, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtHinhAnh, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jBtnBrowser))
        );

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jBLamMoi.setBackground(new java.awt.Color(204, 204, 204));
        jBLamMoi.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jBLamMoi.setText("REFRESH");
        jBLamMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBLamMoiActionPerformed(evt);
            }
        });

        jBSua.setBackground(new java.awt.Color(255, 255, 0));
        jBSua.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jBSua.setText("UPDATE");
        jBSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBSuaActionPerformed(evt);
            }
        });

        jBXoa.setBackground(new java.awt.Color(255, 0, 0));
        jBXoa.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jBXoa.setText("DELETE");
        jBXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBXoaActionPerformed(evt);
            }
        });

        jBThem.setBackground(new java.awt.Color(106, 228, 155));
        jBThem.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jBThem.setText("INSERT");
        jBThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBThemActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jBThem, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jBSua, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jBXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jBLamMoi)
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jBLamMoi)
                    .addComponent(jBSua)
                    .addComponent(jBXoa)
                    .addComponent(jBThem))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        jLabel14.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel14.setText("Giới tính");

        buttonGroup1.add(gtNam);
        gtNam.setText("Nam");

        buttonGroup1.add(gtNu);
        gtNu.setText("Nữ");

        jButton1.setText("+");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addComponent(jLabel13)
                                        .addGap(83, 83, 83)
                                        .addComponent(txtSoDT))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                        .addComponent(jLabel10)
                                        .addGap(38, 38, 38)
                                        .addComponent(jDNgBD, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel11)
                                            .addComponent(jLabel5)
                                            .addComponent(jLabel6)
                                            .addComponent(jLabel12)
                                            .addComponent(jLabel7))
                                        .addGap(39, 39, 39)
                                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtLuong)
                                            .addComponent(txtCMND)
                                            .addComponent(jDNgSinh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(69, 69, 69)
                                .addComponent(txtDiaChi))
                            .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel4Layout.createSequentialGroup()
                                            .addComponent(jLabel9)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(txtSearch))
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel4Layout.createSequentialGroup()
                                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jLabel4)
                                                .addComponent(jLabel14))
                                            .addGap(43, 43, 43)
                                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(txtHoNV)
                                                .addGroup(jPanel4Layout.createSequentialGroup()
                                                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel4Layout.createSequentialGroup()
                                                                .addComponent(gtNam)
                                                                .addGap(33, 33, 33)
                                                                .addComponent(gtNu))
                                                            .addComponent(txtTenNV, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(jPanel4Layout.createSequentialGroup()
                                                            .addComponent(txtMaNV, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                            .addComponent(jButton1)
                                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                            .addComponent(jBTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                    .addGap(0, 0, Short.MAX_VALUE))))))
                                .addGap(0, 11, Short.MAX_VALUE)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jbTroLai1)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txtMaNV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jBTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton1))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(txtHoNV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(txtTenNV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel14)
                            .addComponent(gtNam)
                            .addComponent(gtNu))
                        .addGap(29, 29, 29)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8))
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(24, 24, 24)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel12)
                                    .addComponent(txtCMND, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5)
                                    .addComponent(jDNgSinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(23, 23, 23)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jDNgBD, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(30, 30, 30)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel13)
                                    .addComponent(txtSoDT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(27, 27, 27)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtLuong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel11))
                                .addGap(20, 20, 20))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jbTroLai1)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jBXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBXoaActionPerformed
        // Đoạn code này được sử dụng để xóa một nhân viên trong cơ sở dữ liệu
        StringBuilder sb = new StringBuilder();  //Khởi tạo một đối tượng `StringBuilder` để xây dựng thông báo lỗi nếu có lỗi nhập liệu từ người dùng
        if (txtMaNV.getText().equals(""))   //Kiểm tra nếu mã nhân viên để trống, thì thêm thông báo lỗi vào `StringBuilder
        {
            sb.append("Mã nhân viên không được để trống!!!");
            txtMaNV.setBackground(Color.red);
        } else {
            txtMaNV.setBackground(Color.white);
        }
        if (sb.length() > 0)   //Kiểm tra nếu `StringBuilder` lưu trữ thông báo lỗi có độ dài lớn hơn 0, thì hiển thị thông báo lỗi đó
        {
            JOptionPane.showMessageDialog(this, sb);
            return;
        }
        try {

            NhanVienCtr dao = new NhanVienCtr();
            int result = JOptionPane.showConfirmDialog(this, "Bạn chắc chắn muốn xóa nhân viên này?", "Xác nhận", JOptionPane.YES_NO_OPTION);
            if (result == JOptionPane.YES_OPTION)  //Hiển thị hộp thoại xác nhận để xác định xem người dùng có muốn xóa tài khoản hay không
            {
                dao.delete(txtMaNV.getText());  ////gọi phương thức `delete()` trong lớp `NhanVienCtr` để xóa tài khoản tương ứng khỏi cơ sở dữ liệu
                JOptionPane.showMessageDialog(this, "Nhân viên đã xóa thành công!");  //hiển thị thông báo đã xóa thành công
            } else {
                JOptionPane.showMessageDialog(this, "Nhân viên chưa được xóa!");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
            e.printStackTrace();
        }
    }//GEN-LAST:event_jBXoaActionPerformed

    private void jBTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBTimKiemActionPerformed
        // Đoạn code này dùng để tìm kiếm một nhân viên trong cơ sở dữ liệu
        StringBuilder sb = new StringBuilder();
        if (txtMaNV.getText().equals("")) {
            sb.append("Mã nhân viên không được để trống!");
            txtMaNV.setBackground(Color.red);
        } else {
            txtMaNV.setBackground(Color.white);
        }
        if (sb.length() > 0) {
            JOptionPane.showMessageDialog(this, sb);
            return;
        }
        try {
            NhanVienCtr nnv = new NhanVienCtr();
            NhanVienClass nv = nnv.find(txtMaNV.getText());  //sử dụng phương thức `find()` để tìm kiếm nhân viên có mã `txtMaNV` trong cơ sở dữ liệu
            if (nv != null) //Kiểm tra xem tài khoản được tìm thấy hay không
            {
                //hiển thị thông tin của nhân viên
                int selectedIndex = jTListNV.getSelectedRow();
                txtMaNV.setText(nv.getMaNV());
                txtHoNV.setText(nv.getHo());
                txtTenNV.setText(nv.getTen());
                if (nv.getGioiTinh().equals("Nam")) {
                    gtNu.setSelected(false);
                    gtNam.setSelected(true);
                } else {
                    gtNu.setSelected(true);
                    gtNam.setSelected(false);
                }
                txtDiaChi.setText(nv.getDiaChi());
                txtCMND.setText(nv.getCMND());
                jDNgSinh.setDate(nv.getNgaySinh());
                jDNgBD.setDate(nv.getNgayBD());
                txtSoDT.setText(nv.getSoDT());
                txtLuong.setText(String.valueOf(nv.getLuong()));
                filename = nv.getHinhAnh();
                if (filename.equals("") == false) {
                    showAnh(filename);
                } else {
                    txtHinhAnh.setIcon(null);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Nhân viên tìm kiếm không tồn tại!");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
            e.printStackTrace();
        }
    }//GEN-LAST:event_jBTimKiemActionPerformed

    private void jbTroLai1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbTroLai1ActionPerformed
        // TODO add your handling code here:
        QuanLy ql = new QuanLy(maTK, loaiTK); //khởi tạo đối tượng QuanLy
        ql.setVisible(true); //hiển thị giao diện quản lý
        this.dispose(); //đóng giao diện hiện tại
    }//GEN-LAST:event_jbTroLai1ActionPerformed

    private void jBLamMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBLamMoiActionPerformed
        //  đoạn code này dùng để làm mới dữ liệu trên bảng danh sách nhân viên và các trường nhập liệu
        LamMoi();   //làm mới các trường nhập liệu
        NhanVien_Load();   //tải lại dữ liệu danh sách nhân viên từ cơ sở dữ liệu và hiển thị lên bảng danh sách

    }//GEN-LAST:event_jBLamMoiActionPerformed

    private void txtSoDTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSoDTActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSoDTActionPerformed

    private void showAnh(String name) 
    {
        //Đoạn code được sử dụng để hiển thị hình ảnh 
        ImageIcon MyImage = new ImageIcon(getClass().getResource("/icon/" + name));  //Tạo một đối tượng `ImageIcon` từ tập tin hình ảnh `name` được định nghĩa trong thư mục `/icon/`
        Image img = MyImage.getImage();   //Lấy `Image` từ đối tượng `ImageIcon` bằng phương thức `getImage()` và lưu trữ vào biến `img`
        Image newImg = img.getScaledInstance(txtHinhAnh.getWidth(), txtHinhAnh.getHeight(), Image.SCALE_SMOOTH);  //Tạo một bản sao của hình ảnh được lưu trữ trong biến `img` với kích thước cho phù hợp với vùng khu vực hiển thị hình ảnh `txtHinhAnh` bằng phương thức `getScaledInstance()`
        ImageIcon image = new ImageIcon(newImg);   //Khởi tạo một `ImageIcon` mới từ bản sao hình ảnh `newImg`
        txtHinhAnh.setIcon(image);   //Thiết lập `image` làm hình ảnh hiển thị trong vùng khu vực `txtHinhAnh`
    }
    private void showAnh(File file) 
    {
        //đoạn code này được dùng để hiển thị một hình ảnh từ đường dẫn tệp được chọn bởi người dùng
        ImageIcon MyImage = new ImageIcon(file.getAbsolutePath());  //Tạo một đối tượng ImageIcon có chứa hình ảnh được chọn từ đường dẫn tệp được truyền vào
        Image img = MyImage.getImage();
        Image newImg = img.getScaledInstance(txtHinhAnh.getWidth(), txtHinhAnh.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon image = new ImageIcon(newImg);
        txtHinhAnh.setIcon(image);
    }
    private void jBThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBThemActionPerformed
        // Đoạn code này được sử dụng để thêm một nhân viên mới vào cơ sở dữ liệu  
        StringBuilder sb = new StringBuilder();
        if (txtMaNV.getText().equals("")) {
            sb.append("Mã nhân viên không được để trống!!!");
            txtMaNV.setBackground(Color.red);
        } else {
            txtMaNV.setBackground(Color.white);
        }
        if (sb.length() > 0) {
            JOptionPane.showMessageDialog(this, sb);
            return;
        }
        try {
            NhanVienClass NV = new NhanVienClass();
            //thiết lập các giá trị
            NV.setMaNV(txtMaNV.getText());
            NV.setHo(txtHoNV.getText());
            NV.setTen(txtTenNV.getText());
            if(gtNam.isSelected()){
                NV.setGioiTinh(gtNam.getText());
            }
            if (gtNu.isSelected()) {
                NV.setGioiTinh(gtNu.getText());
            }
            NV.setDiaChi(txtDiaChi.getText());
            NV.setCMND(txtCMND.getText());

            if (jDNgSinh.getDate() != null) {
                java.util.Date utilStartDate = jDNgSinh.getDate();
                java.sql.Date sqlStartDate = new java.sql.Date(utilStartDate.getTime()); //chuyển đổi giá trị của trường "jDNgSinh" từ định dạng `java.util.Date` sang định dạng `java.sql.Date`
                NV.setNgaySinh(sqlStartDate);
            }
            if (jDNgBD.getDate() != null) {
                java.util.Date utilStartDate = jDNgBD.getDate();
                java.sql.Date sqlStartDate = new java.sql.Date(utilStartDate.getTime());
                NV.setNgayBD(sqlStartDate);
            }
            NV.setSoDT(txtSoDT.getText());
            NV.setLuong(Integer.valueOf(txtLuong.getText()));
            if (file != null) {
                filename = file.getName();
                NV.setHinhAnh(filename);
            }

            NhanVienCtr dao = new NhanVienCtr();
            dao.insert(NV); //Gọi phương thức `insert()` trong lớp `NhanVienCtr` để thêm đối tượng 'NhanVienClass` vừa tạo vào cơ sở dữ liệu
            JOptionPane.showMessageDialog(this, "Nhân viên được thêm vào thành công!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());

        }
    }//GEN-LAST:event_jBThemActionPerformed

    private void jBSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBSuaActionPerformed
        // Đoạn code này được sử dụng để cập nhật một nhân viên trong cơ sở dữ liệu
        StringBuilder sb = new StringBuilder();
        if (txtMaNV.getText().equals("")) {
            sb.append("Mã nhân viên không được để trống!!!");
            txtMaNV.setBackground(Color.red);
        } else {
            txtMaNV.setBackground(Color.white);
        }
        if (sb.length() > 0) {
            JOptionPane.showMessageDialog(this, sb);
            return;
        }

        try {

            NhanVienCtr dao = new NhanVienCtr();
            NhanVienClass NV = new NhanVienClass();
            NV.setMaNV(txtMaNV.getText());
            NV.setHo(txtHoNV.getText());
            NV.setTen(txtTenNV.getText());
            if(gtNam.isSelected()){
                NV.setGioiTinh(gtNam.getText());
            }
            if (gtNu.isSelected()) {
                NV.setGioiTinh(gtNu.getText());
            }

            NV.setDiaChi(txtDiaChi.getText());
            NV.setCMND(txtCMND.getText());

            if (jDNgSinh.getDate() != null) {
                java.util.Date utilStartDate = jDNgSinh.getDate();
                java.sql.Date sqlStartDate = new java.sql.Date(utilStartDate.getTime());
                NV.setNgaySinh(sqlStartDate);
            }
            if (jDNgBD.getDate() != null) {
                java.util.Date utilStartDate = jDNgBD.getDate();
                java.sql.Date sqlStartDate = new java.sql.Date(utilStartDate.getTime());
                NV.setNgayBD(sqlStartDate);
            }
            NV.setSoDT(txtSoDT.getText());
            NV.setLuong(Integer.valueOf(txtLuong.getText()));
            NV.setHinhAnh(txtHinhAnh.getText());
            System.out.println("HA: " + filename);
            if (file != null) {
                filename = file.getName();
                NV.setHinhAnh(filename);
            } else if (filename.isEmpty() == false) {
                NV.setHinhAnh(filename);
            }

            int result = JOptionPane.showConfirmDialog(this, "Bạn chắc chắn muốn cập nhật không?", "Xác nhận", JOptionPane.YES_NO_OPTION);
            if (result == JOptionPane.YES_OPTION) {
                dao.update(NV); //Gọi phương thức `update()` trong lớp `NhanVienCtr` để cập nhật đối tượng `NhanVienClass` vừa tạo vào cơ sở dữ liệu
                JOptionPane.showMessageDialog(this, "Nhân viên được cập nhật vào thành công!");
            } else {
                JOptionPane.showMessageDialog(this, "Nhân viên chưa được cập nhật!");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());

        }
    }//GEN-LAST:event_jBSuaActionPerformed

    
    private void jTListNVMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTListNVMouseClicked
        // đoạn code này dùng để hiển thị thông tin tài khoản khi người dùng click chuột vào một dòng trong bảng jTListNV
        LamMoi();   //làm mới các trường nhập liệu và các nút trên giao diện `QuanLyNV`
        int selectedIndex = jTListNV.getSelectedRow(); // Lấy chỉ số của dòng được chọn trong bảng `jTListNV`
        try (Connection con = ConnectionUtils.getMyConnection()) {
            String manv = (String) (jTListNV.getValueAt(selectedIndex, 0)); //Lấy giá trị mã nhân viên tương ứng của hàng được chọn.
            jTListNV.setColumnSelectionInterval(0, 9);  //Thiết lập cột được chọn trong bảng `jTListNV` từ cột 0 đến cột 9
            txtMaNV.setText(jTListNV.getValueAt(selectedIndex, 0).toString());
            txtHoNV.setText(jTListNV.getValueAt(selectedIndex, 1).toString());
            txtTenNV.setText(jTListNV.getValueAt(selectedIndex, 2).toString());
            String sex = jTListNV.getValueAt(selectedIndex, 3).toString();
            if (sex.equals("Nữ")) {
                gtNu.setSelected(true);
                gtNam.setSelected(false);
            } else if (sex.equals("Nam")) {
                gtNam.setSelected(true);
                gtNu.setSelected(false);
            }
            txtDiaChi.setText(jTListNV.getValueAt(selectedIndex, 4).toString());
            txtCMND.setText(jTListNV.getValueAt(selectedIndex, 5).toString());
            jDNgSinh.setDate((Date) jTListNV.getValueAt(selectedIndex, 6));
            jDNgBD.setDate((Date) jTListNV.getValueAt(selectedIndex, 7));
            txtSoDT.setText(jTListNV.getValueAt(selectedIndex, 8).toString());
            txtLuong.setText(jTListNV.getValueAt(selectedIndex, 9).toString());
            // txtHinhAnh.setText(jTListNV.getValueAt(selectedIndex, 9).toString());

            String SQL = "SELECT HINHANH FROM NHANVIEN WHERE MANV=?"; //Tạo một câu truy vấn SQL để lấy dữ liệu hình ảnh của nhân viên tương ứng từ cơ sở dữ liệu
            PreparedStatement ps = conn.prepareStatement(SQL);  //Tạo đối tượng `PreparedStatement` để chuẩn bị cho câu truy vấn SQL
            ps.setString(1, manv);
            ResultSet rs = ps.executeQuery();    //Thực thi câu truy vấn SQL và lưu kết quả vào đối tượng `ResultSet`
            if (rs.next())  //Kiểm tra xem có kết quả trả về từ cơ sở dữ liệu hay không
            {
                filename = rs.getString("HinhAnh");   // Lấy giá trị hình ảnh của sách tương ứng từ kết quả của câu truy vấn SQL
                if (filename != null) {
                    showAnh(filename);
                } else {
                    txtHinhAnh.setIcon(null);
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }


    }//GEN-LAST:event_jTListNVMouseClicked

    private void jTListNVKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTListNVKeyReleased
        // Đoạn code này được sử dụng để xử lý sự kiện khi người dùng chọn một nhân viên trong bảng danh sách nhân viên trên giao diện `QuanLySach` bằng cách sử dụng bàn phím
        LamMoi();

        if (evt.getKeyCode() == KeyEvent.VK_UP || evt.getKeyCode() == KeyEvent.VK_DOWN) {
            int selectedIndex = jTListNV.getSelectedRow();
            try (Connection con = ConnectionUtils.getMyConnection()) {
                String mas = (String) (jTListNV.getValueAt(selectedIndex, 0));

                jTListNV.setColumnSelectionInterval(0, 9);
                txtMaNV.setText(jTListNV.getValueAt(selectedIndex, 0).toString());
                txtHoNV.setText(jTListNV.getValueAt(selectedIndex, 1).toString());
                txtTenNV.setText(jTListNV.getValueAt(selectedIndex, 2).toString());
                String sex = jTListNV.getValueAt(selectedIndex, 3).toString();
                if (sex.equals("Nữ")) {
                    gtNu.setSelected(true);
                    gtNam.setSelected(false);
                } else if (sex.equals("Nam")) {
                    gtNam.setSelected(true);
                    gtNu.setSelected(false);
                }
                txtDiaChi.setText(jTListNV.getValueAt(selectedIndex, 4).toString());
                txtCMND.setText(jTListNV.getValueAt(selectedIndex, 5).toString());
                jDNgSinh.setDate((Date) jTListNV.getValueAt(selectedIndex, 6));
                jDNgBD.setDate((Date) jTListNV.getValueAt(selectedIndex, 7));
                txtSoDT.setText(jTListNV.getValueAt(selectedIndex, 8).toString());
                txtLuong.setText(jTListNV.getValueAt(selectedIndex, 9).toString());
                String SQL = "SELECT HINHANH FROM NHANVIEN WHERE MANV=?";
                PreparedStatement ps = conn.prepareStatement(SQL);
                ps.setString(1, mas);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    filename = rs.getString("HinhAnh");
                    if (filename != null) {
                        showAnh(filename);
                    } else {
                        txtHinhAnh.setIcon(null);
                    }
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }//GEN-LAST:event_jTListNVKeyReleased

    private void txtSearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchKeyReleased
        // Đoạn code này được sử dụng để tìm kiếm thông tin của nhân viên trong bảng `jTListNV` và cập nhật lại bảng sau khi lọc
        DefaultTableModel SearchNhanVien = (DefaultTableModel) jTListNV.getModel();  //Khởi tạo một đối tượng `DefaultTableModel` từ bảng `jTListNV`
        String search = txtSearch.getText(); //Lấy nội dung tìm kiếm từ `txtSearch` và lưu vào biến `search`
        TableRowSorter<DefaultTableModel> tr = new TableRowSorter<>(SearchNhanVien);  // Khởi tạo một đối tượng `TableRowSorter` được đặt tên là `tr`, sử dụng lớp `DefaultTableModel` và áp dụng cho bảng `SearchNhanVien`
        jTListNV.setRowSorter(tr);   //Thiết lập `tr` làm sắp xếp hàng để bảng `jTSach` có thể sử dụng kết quả tìm kiếm được lọc
        tr.setRowFilter(RowFilter.regexFilter(search));  //Áp dụng bộ lọc hàng tìm kiếm với giá trị của biến `search` bằng phương thức `regexFilter` của lớp `RowFilter`. Bộ lọc hàng này được sử dụng để tìm kiếm các hàng chứa chuỗi giống với giá trị `search`

    }//GEN-LAST:event_txtSearchKeyReleased

    private void jBtnBrowserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnBrowserActionPerformed
        //  Đoạn code này được sử dụng để chọn tệp tin hình ảnh từ thiết bị và hiển thị hình ảnh
        JFileChooser fc = new JFileChooser(); //Khởi tạo một đối tượng `JFileChooser` để cho phép người dùng chọn tệp tin hình ản
        try {
            fc.setCurrentDirectory(new File(getClass().getResource("/icon/").toURI()));  //Thiết lập thư mục mặc định cho hộp thoại chọn tệp tin là thư mục  icon trong dự án
        } catch (URISyntaxException ex) {
            Logger.getLogger(QuanLyNV.class.getName()).log(Level.SEVERE, null, ex);
        }
        //Tạo một bộ lọc định dạng tệp tin để chỉ cho phép người dùng chọn các tệp tin hình ảnh có định dạng là JPG, GIF hoặc PNG
        FileNameExtensionFilter filter = new FileNameExtensionFilter("*.Images", "jpg", "gif", "png");
        fc.addChoosableFileFilter(filter);   //Thêm bộ lọc định dạng tệp tin vào hộp thoại chọn tệp tin
        int result = fc.showSaveDialog(fc);   //Mở hộp thoại chọn tệp tin và lấy kết quả trả về từ người dùng
        if (result == JFileChooser.APPROVE_OPTION) {
            file = fc.getSelectedFile();   //Lấy tệp tin hình ảnh được chọn
            String path = file.getAbsolutePath();  //Lấy đường dẫn đến tệp tin hình ảnh được chọn
            filename = path.substring(path.lastIndexOf("\\") + 1);
            txtHinhAnh.setText(filename);
            txtHinhAnh.setIcon(ResizeCover(path, null));  // Hiển thị hình ảnh được chọn lên trường "txtHinhAnh"
            imgPath = path;  // Lưu đường dẫn đến tệp tin hình ảnh được chọn vào biến `imgPath
        } else if (result == JFileChooser.CANCEL_OPTION) {
            JOptionPane.showMessageDialog(this, "No File Selected");  //Nếu người dùng không chọn tệp tin hình ảnh, hiển thị thông báo cho người dùng
        }
    }//GEN-LAST:event_jBtnBrowserActionPerformed

    private void txtSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSearchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSearchActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // Đoạn code này được sử dụng để tạo một mã nhân viên mới và hiển thị mã đó lên trường nhập liệu "txtMaNV"
        try (java.sql.Connection con = ConnectionUtils.getMyConnection())  //Tạo một đối tượng `Connection` để kết nối đến cơ sở dữ liệu
        {
            String SQL = "select insert_new_MaNV() as newMaNV from dual";  //Tạo câu truy vấn SQL để tìm kiếm một mã nhân viên mới
            PreparedStatement ps = con.prepareStatement(SQL);   //Tạo một đối tượng `PreparedStatement` để chuẩn bị câu truy vấn
            ResultSet rs = ps.executeQuery();   //Thực thi câu truy vấn và lưu kết quả vào đối tượng `ResultSet` để xử lý
            if (rs.next()) //Kiểm tra xem có kết quả trả về từ cơ sở dữ liệu hay không
            {
                txtMaNV.setText(rs.getString("NEWMANV"));  //Lấy giá trị của cột "newMaNV" trong dòng kết quả của `ResultSet` và hiển thị giá trị đó lên trường nhập liệu "txtMaNV"
            }
        } catch (SQLException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    public ImageIcon ResizeCover(String ImagePath, byte[] pic)  //có hai đối số đầu vào: `ImagePath` và `pic` để truyền đường dẫn tới file hình ảnh và một mảng byte chứa dữ liệu hình ản
    {
        
        ImageIcon MyImage = null;
        if (ImagePath != null)
        {
            MyImage = new ImageIcon(ImagePath);  //phương thức sử dụng đường dẫn tương ứng để tạo một đối tượng ImageIcon
        } else
        {
            MyImage = new ImageIcon(pic);  //phương thức sử dụng mảng byte chứa dữ liệu hình ảnh để tạo đối tượng ImageIcon.
        }
        Image img = MyImage.getImage();
        Image newImg = img.getScaledInstance(txtHinhAnh.getWidth(), txtHinhAnh.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon image = new ImageIcon(newImg);
        return image;
    }

    /**
     * @param args the command line arguments
     */
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
            java.util.logging.Logger.getLogger(QuanLyNV.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(QuanLyNV.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(QuanLyNV.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(QuanLyNV.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new QuanLyNV().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JRadioButton gtNam;
    private javax.swing.JRadioButton gtNu;
    private javax.swing.JButton jBLamMoi;
    private javax.swing.JButton jBSua;
    private javax.swing.JButton jBThem;
    private javax.swing.JButton jBTimKiem;
    private javax.swing.JButton jBXoa;
    private javax.swing.JButton jBtnBrowser;
    private javax.swing.JButton jButton1;
    private com.toedter.calendar.JDateChooser jDNgBD;
    private com.toedter.calendar.JDateChooser jDNgSinh;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
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
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTListNV;
    private javax.swing.JButton jbTroLai1;
    private javax.swing.JTextField txtCMND;
    private javax.swing.JTextField txtDiaChi;
    private javax.swing.JLabel txtHinhAnh;
    private javax.swing.JTextField txtHoNV;
    private javax.swing.JTextField txtLuong;
    private javax.swing.JTextField txtMaNV;
    private javax.swing.JTextField txtSearch;
    private javax.swing.JTextField txtSoDT;
    private javax.swing.JTextField txtTenNV;
    // End of variables declaration//GEN-END:variables
}
