/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modul7;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Muhammad Yafi
 */
public class TugasModul7 extends JFrame {
    private JTextField txtNama;
    private JTextField txtNilai;
    private JComboBox <String> cmbMatkul;
    private JTable tableData;
    private DefaultTableModel tableModel;
    private JTabbedPane tabbedPane;
    
    // Method untuk membuat desain Tab Input
    private JPanel createInputPanel() {
        JPanel mainPanel = new JPanel(new BorderLayout());
        JPanel formPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Komponen Nama
        formPanel.add(new JLabel("Nama Siswa:"));
        txtNama = new JTextField();
        formPanel.add(txtNama);

        // Komponen Mata Pelajaran (ComboBox)
        formPanel.add(new JLabel("Mata Pelajaran:"));
        String[] matkul = {"Matematika Dasar", "Bahasa Indonesia",
                            "Algoritma dan Pemrograman I", "Praktikum Pemrograman II"};
        cmbMatkul = new JComboBox<>(matkul);
        formPanel.add(cmbMatkul);

        // Komponen Nilai
        formPanel.add(new JLabel("Nilai (0-100):"));
        txtNilai = new JTextField();
        formPanel.add(txtNilai);

        // Panel untuk tombol Simpan dan Reset 
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0)); 
        
        // Tombol Reset 
        JButton btnReset = new JButton("Reset Form");
        btnReset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetForm(); // Panggil method reset
            }
        });
        
        // Tombol Simpan
        JButton btnSimpan = new JButton("Simpan Data");
        btnSimpan.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                prosesSimpan();
            }
        });

        buttonPanel.add(btnReset);
        buttonPanel.add(btnSimpan);
        
        // Tambahkan tombol panel ke baris terakhir
        formPanel.add(new JLabel("")); 
        formPanel.add(buttonPanel);

        mainPanel.add(formPanel, BorderLayout.NORTH);
        return mainPanel;
    }
    
    // Method untuk membuat desain Tab Tabel
    private JPanel createTablePanel() {
        JPanel panel = new JPanel(new BorderLayout());

        // Setup Model Tabel (Kolom)
        String[] kolom = {"Nama Siswa", "Mata Pelajaran", "Nilai", "Grade"};
        tableModel = new DefaultTableModel(kolom, 0);
        tableData = new JTable(tableModel);

        // Membungkus tabel dengan ScrollPane
        JScrollPane scrollPane = new JScrollPane(tableData);
        panel.add(scrollPane, BorderLayout.CENTER);

        // Panel tombol Hapus 
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton btnHapus = new JButton("Hapus Baris Terpilih");
        
        // Event Handling Tombol Hapus
        btnHapus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                prosesHapus();
            }
        });
        
        bottomPanel.add(btnHapus);
        panel.add(bottomPanel, BorderLayout.SOUTH);

        return panel;
    }
    
    //  Method untuk mereset input form
    private void resetForm() {
        txtNama.setText("");
        txtNilai.setText("");
        cmbMatkul.setSelectedIndex(0);
    }
    
    // Logika Penghapusan Data
    private void prosesHapus() {
        int selectedRow = tableData.getSelectedRow();

        if (selectedRow >= 0) { // Jika ada baris yang terpilih
            int dialogResult = JOptionPane.showConfirmDialog(this, 
                    "Yakin ingin menghapus data ini?", "Konfirmasi Hapus", 
                    JOptionPane.YES_NO_OPTION);
            
            if (dialogResult == JOptionPane.YES_OPTION) {
                // Gunakan tableModel.removeRow(indeks)
                tableModel.removeRow(selectedRow); 
                JOptionPane.showMessageDialog(this, "Data berhasil dihapus!");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Pilih baris yang ingin dihapus terlebih dahulu!",
                    "Error Hapus", JOptionPane.WARNING_MESSAGE);
        }
    }
    
    // Logika Validasi dan Penyimpanan Data
    private void prosesSimpan() {

        // 1. Ambil data dari input
        String nama = txtNama.getText().trim(); // Tambahkan trim() untuk kebersihan
        String matkul = (String) cmbMatkul.getSelectedItem();
        String strNilai = txtNilai.getText().trim();

        // 2. VALIDASI INPUT

        // Validasi 1: Cek nama kosong dan minimal 3 karakter (
        if (nama.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nama tidak boleh kosong!",
                    "Error Validasi", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (nama.length() < 3) { // Requirement 3
            JOptionPane.showMessageDialog(this, "Nama minimal terdiri dari 3 karakter!",
                    "Error Validasi", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Validasi 2: Cek nilai (berupa angka dan range)
        int nilai;
        try {
            nilai = Integer.parseInt(strNilai);
            if (nilai < 0 || nilai > 100) {
                JOptionPane.showMessageDialog(this, "Nilai harus antara 0 - 100!",
                        "Error Validasi", JOptionPane.WARNING_MESSAGE);
                return;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Nilai harus berupa angka!",
                    "Error Validasi", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        // 3. Logika Bisnis (Menentukan Grade) - Menggunakan switch case
        String grade;
        int nilaiRange = nilai / 10; 
        
        switch (nilaiRange) {
            case 10: // Khusus nilai 100
            case 9:  // Nilai 90-99
            case 8:  // Nilai 80-89
                grade = "A";
                break;
            case 7:  // Nilai 70-79
                grade = "AB";
                break;
            case 6:  // Nilai 60-69
                grade = "B"; 
                break;
            case 5:  // Nilai 50-59
                grade = "C"; 
                break;
            case 4:  // Nilai 40-49
                grade = "D";
                break;
            default: // Nilai 0-39
                grade = "E";
                break;
        }

        // 4. Masukkan ke Tabel (Update Model)
        Object[] dataBaris = {nama, matkul, nilai, grade};
        tableModel.addRow(dataBaris);
 
        // 5. Reset Form dan Pindah Tab
        resetForm(); // Gunakan method reset yang baru

        JOptionPane.showMessageDialog(this, "Data Berhasil Disimpan!");
        tabbedPane.setSelectedIndex(1); // Otomatis pindah ke tab tabel
    }
    
    public TugasModul7(){
        //1. konfiugrasi Frame utama
        setTitle("Aplikasi Manajemen Nilai Siswa");
        setSize(550,450); // Ukuran sedikit diperbesar
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); 
        
        //2. Inisialisasi Tabbed Pane
        tabbedPane = new JTabbedPane();
        
        //3. Membuat panel untuk tab 1 (Form Input)
        JPanel panelInput = createInputPanel();
        tabbedPane.addTab("Input Data", panelInput);

        // 4. Membuat Panel untuk Tab 2 (Tabel Data)
        JPanel panelTabel = createTablePanel();
        tabbedPane.addTab("Daftar Nilai", panelTabel);

        // Menambahkan TabbedPane ke Frame
        add(tabbedPane);
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(()->{
            new TugasModul7().setVisible(true);
        });
    }
}