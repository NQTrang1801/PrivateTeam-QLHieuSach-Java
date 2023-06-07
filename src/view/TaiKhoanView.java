/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

import dao.ConnectionUtils;
import java.awt.Image;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author NTVy
 */
public class TaiKhoanView extends javax.swing.JFrame {

    private String maTK;
    private String loaiTK;

    /**
     * Creates new form TaiKhoan
     */
    public TaiKhoanView() {
        initComponents();
        this.setLocationRelativeTo(this);
    }

    public TaiKhoanView(String maTK, String loaiTK) {
        initComponents();    //dùng khởi tạo các thành phần giao diện người dùng và các sự kiện
        blockTextField();    //dùng để ngăn chặn việc chỉnh sửa thông tin tài khoản 
        this.setLocationRelativeTo(this);   // đặt cửa sổ hiển thị ở trung tâm màn hình
        this.maTK = maTK;
        this.loaiTK = loaiTK;
        txtMaTK.setText(maTK);
        try (java.sql.Connection con = ConnectionUtils.getMyConnection())   //bắt đầu kết nối database
        {
            String SQL = "SELECT * FROM NhanVien NV JOIN TAIKHOAN TK on NV.MaNV = TK.MaNV WHERE MaTK = ?";   // tạo câu truy vấn SQL để lấy thông tin 
            PreparedStatement ps = con.prepareStatement(SQL);   //khởi tạo đối tượng PreparedStatement cho câu truy vấn SQL
            ps.setString(1, txtMaTK.getText());   
            ResultSet rs = ps.executeQuery();   //thực hiện truy vấn và lưu kết quả vào đối tượng ResultSet

            if (rs.next())   //kiểm tra xem rs có chứa dữ liệu hay không. Nếu có, lấy thông tin hiển thị trên giao diện 
            {
                txtMaNV.setText(rs.getString("MaNV"));   //Hiển thị thông tin Mã nhân viên
                txtName.setText(rs.getString("Ho") + " " + rs.getString("Ten"));
                txtGioiTinh.setText(rs.getString("GioiTinh"));
                txtDiaChi.setText(rs.getString("DiaChi"));
                txtCMND.setText(rs.getString("CMND"));
                txtNgaySinh.setText(rs.getString("NgaySinh"));
                txtNgayBD.setText(rs.getString("NgayBD"));
                txtSDT.setText(rs.getString("SODT"));
                txtLuong.setText(rs.getString("Luong"));
                if (rs.getString("HinhAnh") != null)   //Kiểm tra xem có thông tin về hình ảnh hay không. Nếu có, hiển thị hình ảnh tương ứng
                {
                    ImageIcon MyImage = new ImageIcon(getClass().getResource("/icon/" + rs.getString("HinhAnh")));
                    Image img = MyImage.getImage();
                    Image newImg = img.getScaledInstance(txtHinhAnh.getWidth(), txtHinhAnh.getHeight(), Image.SCALE_SMOOTH);
                    ImageIcon image = new ImageIcon(newImg);
                    txtHinhAnh.setIcon(image);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Tài khoản khong thuoc nhan vien nao", "NULL", JOptionPane.ERROR_MESSAGE);
            }

            String SQL2 = "select XH, DT, SLHD\n"
                        + "from (  SELECT nv.MaNV, COUNT(MaHD) as SLHD, SUM(hd.ThanhTien) as DT, RANK() OVER (ORDER BY SUM(hd.ThanhTien) DESC) AS XH \n"
                        + "        FROM NhanVien nv JOIN HoaDon hd ON nv.MaNV = hd.MaNV \n"
                        + "        GROUP BY nv.MaNV ORDER BY XH ASC) \n"
                        + "where MaNV = ?";   //Tạo câu truy vấn SQL để lấy thông tin số lượng hóa đơn, doanh thu và xếp hạng bán hàng của nhân viên
            PreparedStatement ps2 = con.prepareStatement(SQL2);
            ps2.setString(1, txtMaNV.getText());
            ResultSet rs2 = ps2.executeQuery();
            if (rs2.next()) {
                txtRank.setText(rs2.getString("XH"));
                txtSLHD.setText(rs2.getString("SLHD"));
                txtDT.setText(rs2.getString("DT"));
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

    public void blockTextField() {
        txtMaTK.setEditable(false); // không cho phép chỉnh sửa
        txtMaTK.setEnabled(false); // không cho phép nhập liệu

        txtMaNV.setEditable(false); // không cho phép chỉnh sửa
        txtMaNV.setEnabled(false); // không cho phép nhập liệu

        txtName.setEditable(false); // không cho phép chỉnh sửa
        txtName.setEnabled(false); // không cho phép nhập liệu

        txtGioiTinh.setEditable(false); // không cho phép chỉnh sửa
        txtGioiTinh.setEnabled(false); // không cho phép nhập liệu

        txtSDT.setEditable(false); // không cho phép chỉnh sửa
        txtSDT.setEnabled(false); // không cho phép nhập liệu

        txtCMND.setEditable(false); // không cho phép chỉnh sửa
        txtCMND.setEnabled(false); // không cho phép nhập liệu

        txtNgaySinh.setEditable(false); // không cho phép chỉnh sửa
        txtNgaySinh.setEnabled(false); // không cho phép nhập liệu

        txtDiaChi.setEditable(false); // không cho phép chỉnh sửa
        txtDiaChi.setEnabled(false); // không cho phép nhập liệu

        txtNgayBD.setEditable(false); // không cho phép chỉnh sửa
        txtNgayBD.setEnabled(false); // không cho phép nhập liệu

        txtLuong.setEditable(false); // không cho phép chỉnh sửa
        txtLuong.setEnabled(false); // không cho phép nhập liệu

        txtRank.setEditable(false); // không cho phép chỉnh sửa
        txtRank.setEnabled(false); // không cho phép nhập liệu

        txtSLHD.setEditable(false); // không cho phép chỉnh sửa
        txtSLHD.setEnabled(false); // không cho phép nhập liệu

        txtDT.setEditable(false); // không cho phép chỉnh sửa
        txtDT.setEnabled(false); // không cho phép nhập liệu
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jbTroLai = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        txtHinhAnh = new javax.swing.JLabel();
        txtMaTK = new javax.swing.JTextField();
        txtMaNV = new javax.swing.JTextField();
        txtCMND = new javax.swing.JTextField();
        txtName = new javax.swing.JTextField();
        txtGioiTinh = new javax.swing.JTextField();
        txtNgaySinh = new javax.swing.JTextField();
        txtDiaChi = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txtNgayBD = new javax.swing.JTextField();
        txtLuong = new javax.swing.JTextField();
        txtSDT = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtRank = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        txtDT = new javax.swing.JTextField();
        txtSLHD = new javax.swing.JTextField();

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(241, 202, 164));

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel1.setText("Thông tin tài khoản");

        jbTroLai.setBackground(new java.awt.Color(204, 101, 67));
        jbTroLai.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jbTroLai.setForeground(new java.awt.Color(255, 255, 255));
        jbTroLai.setText("TRỞ LẠI");
        jbTroLai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbTroLaiActionPerformed(evt);
            }
        });

        jPanel3.setBackground(new java.awt.Color(243, 231, 231));

        txtHinhAnh.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtHinhAnh.setPreferredSize(new java.awt.Dimension(40, 30));

        txtMaTK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMaTKActionPerformed(evt);
            }
        });

        txtMaNV.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        txtMaNV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMaNVActionPerformed(evt);
            }
        });

        txtName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNameActionPerformed(evt);
            }
        });

        txtDiaChi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDiaChiActionPerformed(evt);
            }
        });

        jLabel3.setText("Name:");

        jLabel4.setText("MaNV:");

        jLabel5.setText("MaTK:");

        jLabel6.setText("GioiTinh:");

        jLabel7.setText("NgaySinh:");

        jLabel8.setText("NgayBD:");

        jLabel9.setText("Luong:");

        jLabel10.setText("DiaChi:");

        jLabel11.setText("CMND:");

        jLabel12.setText("SĐT:");

        txtNgayBD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNgayBDActionPerformed(evt);
            }
        });

        txtLuong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtLuongActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Arial Black", 1, 24)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("RANK");

        txtRank.setFont(new java.awt.Font("Arial Black", 1, 48)); // NOI18N
        txtRank.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtRank.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtRankActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtMaTK, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtMaNV, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtHinhAnh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(24, 24, 24)))
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtDiaChi)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtCMND, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtNgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(txtLuong, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 142, Short.MAX_VALUE)
                                        .addComponent(txtNgayBD, javax.swing.GroupLayout.Alignment.LEADING)))
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addComponent(jLabel11)
                    .addComponent(jLabel8)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addGap(16, 16, 16)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addComponent(txtGioiTinh, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel12)
                                .addGap(18, 18, 18)
                                .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, 328, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtRank)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtMaTK, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5)
                            .addComponent(txtMaNV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4)
                            .addComponent(jLabel3)
                            .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel6)
                                    .addComponent(txtGioiTinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel12)
                                    .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel11)
                                    .addComponent(txtCMND, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtNgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addGap(3, 3, 3)
                                        .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtDiaChi, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtNgayBD)
                                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel9)
                                    .addComponent(txtLuong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(txtHinhAnh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtRank)))
                .addContainerGap())
        );

        jLabel13.setBackground(new java.awt.Color(236, 236, 150));
        jLabel13.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(224, 72, 34));
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setText("Thống Kê Bán Hàng");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLabel14.setText("Số lượng hóa đơn:");

        jLabel15.setText("Doanh thu:");

        txtDT.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtDT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDTActionPerformed(evt);
            }
        });

        txtSLHD.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtSLHD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSLHDActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtDT, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtSLHD, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSLHD, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDT, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(25, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(267, 267, 267)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 164, Short.MAX_VALUE)
                        .addComponent(jbTroLai))
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jbTroLai, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbTroLaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbTroLaiActionPerformed
        // TODO add your handling code here:
        HomePage hp = new HomePage(maTK, loaiTK); 
        hp.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jbTroLaiActionPerformed

    private void txtDiaChiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDiaChiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDiaChiActionPerformed

    private void txtMaNVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMaNVActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMaNVActionPerformed

    private void txtNgayBDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNgayBDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNgayBDActionPerformed

    private void txtLuongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtLuongActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtLuongActionPerformed

    private void txtNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNameActionPerformed

    private void txtRankActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtRankActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtRankActionPerformed

    private void txtMaTKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMaTKActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMaTKActionPerformed

    private void txtDTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDTActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDTActionPerformed

    private void txtSLHDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSLHDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSLHDActionPerformed


    public static void main(String maTK, String loaiTK) {
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TaiKhoanView(maTK, loaiTK).setVisible(true);
            }
        });
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
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
    private javax.swing.JButton jbTroLai;
    private javax.swing.JTextField txtCMND;
    private javax.swing.JTextField txtDT;
    private javax.swing.JTextField txtDiaChi;
    private javax.swing.JTextField txtGioiTinh;
    private javax.swing.JLabel txtHinhAnh;
    private javax.swing.JTextField txtLuong;
    private javax.swing.JTextField txtMaNV;
    private javax.swing.JTextField txtMaTK;
    private javax.swing.JTextField txtName;
    private javax.swing.JTextField txtNgayBD;
    private javax.swing.JTextField txtNgaySinh;
    private javax.swing.JTextField txtRank;
    private javax.swing.JTextField txtSDT;
    private javax.swing.JTextField txtSLHD;
    // End of variables declaration//GEN-END:variables
}
