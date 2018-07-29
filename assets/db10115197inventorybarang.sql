/*
Navicat MySQL Data Transfer

Source Server         : db
Source Server Version : 100116
Source Host           : localhost:3306
Source Database       : db10115197inventorybarang

Target Server Type    : MYSQL
Target Server Version : 100116
File Encoding         : 65001

Date: 2017-07-23 18:19:48
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for tb_databarang
-- ----------------------------
DROP TABLE IF EXISTS `tb_databarang`;
CREATE TABLE `tb_databarang` (
  `kd_barang` varchar(10) NOT NULL,
  `nama_barang` varchar(50) DEFAULT NULL,
  `harga_beli` int(11) DEFAULT NULL,
  `harga_jual` int(11) DEFAULT NULL,
  `satuan` varchar(70) DEFAULT NULL,
  `kd_kategoriBarang` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`kd_barang`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of tb_databarang
-- ----------------------------
INSERT INTO `tb_databarang` VALUES ('BD-0001', 'Keyboard', '50000', '70000', 'pcs', 'KB-0001');
INSERT INTO `tb_databarang` VALUES ('BD-0002', 'Asus X455 LN', '400000', '600000', 'pcs', 'KB-0002');
INSERT INTO `tb_databarang` VALUES ('BD-0003', 'Samsung Galaxy S8', '6000000', '8000000', 'pcs', 'KB-0005');
INSERT INTO `tb_databarang` VALUES ('BD-0004', 'Nvidia Geforce GT-758', '600000', '1000000', 'pcs', 'KB-0004');

-- ----------------------------
-- Table structure for tb_datapelanggan
-- ----------------------------
DROP TABLE IF EXISTS `tb_datapelanggan`;
CREATE TABLE `tb_datapelanggan` (
  `kd_pelanggan` varchar(10) NOT NULL,
  `nama_pelanggan` varchar(50) DEFAULT NULL,
  `alamat` varchar(100) DEFAULT NULL,
  `no_telepon` varchar(15) DEFAULT NULL,
  PRIMARY KEY (`kd_pelanggan`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of tb_datapelanggan
-- ----------------------------
INSERT INTO `tb_datapelanggan` VALUES ('KP-0001', 'Bayu Setiaji', 'Ujung Berung', '082218206669');

-- ----------------------------
-- Table structure for tb_datasupplier
-- ----------------------------
DROP TABLE IF EXISTS `tb_datasupplier`;
CREATE TABLE `tb_datasupplier` (
  `kd_supplier` varchar(10) NOT NULL,
  `nama_supplier` varchar(50) DEFAULT NULL,
  `alamat` varchar(100) DEFAULT NULL,
  `no_telepon` varchar(15) DEFAULT NULL,
  PRIMARY KEY (`kd_supplier`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of tb_datasupplier
-- ----------------------------
INSERT INTO `tb_datasupplier` VALUES ('KS-0001', 'Enter Komputer', 'Jl. Purnawarman BEC - Bandung', '022 02109281');
INSERT INTO `tb_datasupplier` VALUES ('KS-0002', 'saddasd', 'assasd', '213123');

-- ----------------------------
-- Table structure for tb_kategoribarang
-- ----------------------------
DROP TABLE IF EXISTS `tb_kategoribarang`;
CREATE TABLE `tb_kategoribarang` (
  `kd_kategoriBarang` varchar(10) NOT NULL,
  `nama_kategoriBarang` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`kd_kategoriBarang`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of tb_kategoribarang
-- ----------------------------
INSERT INTO `tb_kategoribarang` VALUES ('KB-0001', 'Accessoris');
INSERT INTO `tb_kategoribarang` VALUES ('KB-0002', 'Notebook');
INSERT INTO `tb_kategoribarang` VALUES ('KB-0003', 'Motherboard');
INSERT INTO `tb_kategoribarang` VALUES ('KB-0004', 'VGA');
INSERT INTO `tb_kategoribarang` VALUES ('KB-0005', 'Handphone');

-- ----------------------------
-- Table structure for tb_pembelian
-- ----------------------------
DROP TABLE IF EXISTS `tb_pembelian`;
CREATE TABLE `tb_pembelian` (
  `no_faktur` varchar(20) NOT NULL,
  `tgl_masuk` date DEFAULT NULL,
  `harga_barang` int(11) DEFAULT NULL,
  `jumlah_barang` int(11) DEFAULT NULL,
  `kd_supplier` varchar(10) DEFAULT NULL,
  `kd_barang` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`no_faktur`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of tb_pembelian
-- ----------------------------
INSERT INTO `tb_pembelian` VALUES ('FAKPBL00001', '2017-07-23', '50000', '3', 'KS-0001', 'BD-0001');
INSERT INTO `tb_pembelian` VALUES ('FAKPBL00002', '2017-07-21', '400000', '9', 'KS-0001', 'BD-0002');

-- ----------------------------
-- Table structure for tb_penjualan
-- ----------------------------
DROP TABLE IF EXISTS `tb_penjualan`;
CREATE TABLE `tb_penjualan` (
  `no_faktur` varchar(20) NOT NULL,
  `tgl_keluar` date DEFAULT NULL,
  `harga_barang` int(11) DEFAULT NULL,
  `jumlah_barang` int(11) DEFAULT NULL,
  `kd_pelanggan` varchar(10) DEFAULT NULL,
  `kd_barang` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`no_faktur`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of tb_penjualan
-- ----------------------------
INSERT INTO `tb_penjualan` VALUES ('FAKPNJL00001', '2017-07-23', '600000', '3', 'KP-0001', 'BD-0002');
INSERT INTO `tb_penjualan` VALUES ('safasafs', '2017-07-05', '70000', '3', 'KP-0001', 'BD-0001');

-- ----------------------------
-- Table structure for tb_stokbarang
-- ----------------------------
DROP TABLE IF EXISTS `tb_stokbarang`;
CREATE TABLE `tb_stokbarang` (
  `no_faktur` varchar(20) NOT NULL,
  `kd_barang` varchar(10) DEFAULT NULL,
  `stok` int(11) DEFAULT NULL,
  `tgl_input` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`no_faktur`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of tb_stokbarang
-- ----------------------------
INSERT INTO `tb_stokbarang` VALUES ('FAKPBL00001', 'BD-0001', '3', '2017-07-23 02:01:08');
INSERT INTO `tb_stokbarang` VALUES ('FAKPBL00002', 'BD-0002', '9', '2017-07-23 02:07:12');
INSERT INTO `tb_stokbarang` VALUES ('FAKPNJL00001', 'BD-0002', '-3', '2017-07-23 02:07:56');

-- ----------------------------
-- Table structure for tb_user
-- ----------------------------
DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user` (
  `username` varchar(20) NOT NULL,
  `password` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of tb_user
-- ----------------------------
INSERT INTO `tb_user` VALUES ('admin', 'admin');
SET FOREIGN_KEY_CHECKS=1;
